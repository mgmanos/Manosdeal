package com.deal.Manosdeal.service;

import com.deal.Manosdeal.dto.CartItemRequest;
import com.deal.Manosdeal.dto.CartItemResponse;
import com.deal.Manosdeal.model.Cart;
import com.deal.Manosdeal.model.CartItems;
import com.deal.Manosdeal.model.Products;
import com.deal.Manosdeal.model.User;
import com.deal.Manosdeal.repository.CartItemRepository;
import com.deal.Manosdeal.repository.CartRepository;
import com.deal.Manosdeal.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository repository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    public CartService(CartRepository repository) {

        this.repository = repository;
    }

    @Transactional
    public String addCart( int productid, User user) {
        Products product = productRepository.findById(productid).orElseThrow(() -> new RuntimeException("Product not found"));
        Cart cart = repository.findCartByUser(user);

        CartItems item = cartItemRepository.findCartItemByProduct(product);

        if (cart == null)
            cart = new Cart();
        cart.setUser(user);

        if (item == null) {
            item = new CartItems();
            item.setQuantity(1);
        } else item.setQuantity(item.getQuantity() + 1);
        item.setCart(cart);
        item.setProduct(product);
        List<CartItems> items = new ArrayList<>();
        items.add(item);
        cart.setCartItems(items);

        repository.save(cart);
        return "Cart is added";
    }


    public Cart getCart(int id) {
        return repository.findById(id).orElse(new Cart());
    }

    public List<CartItemResponse> getCartItems(User user) {
        Cart cart = repository.findCartByUser(user);
        List<CartItems> items = cartItemRepository.findByCart(cart);

        List<CartItemResponse> itemResponses = new ArrayList<>();

        for (CartItems cItem : items) {
            CartItemResponse res = new CartItemResponse();
            res.setCartitemid(cItem.getId());
            res.setProductid(cItem.getProduct().getId());
            res.setName(cItem.getProduct().getName());
            res.setPrice(cItem.getProduct().getPrice());
            res.setQuantity(cItem.getQuantity());
            res.setTotalprice(cItem.getQuantity() * cItem.getProduct().getPrice());
            itemResponses.add(res);

        }
        return itemResponses;

    }

    public ResponseEntity<?> removeFromCart(@PathVariable int cartitemid) {
        CartItems cartItem = cartItemRepository.findById(cartitemid)
                .orElseThrow(() -> new RuntimeException("CartItem not found"));

        Cart cart = cartItem.getCart();
        cart.removeCartItem(cartItem);
        repository.save(cart);
        cartItemRepository.delete(cartItem);
        return ResponseEntity.ok("Cart item removed successfully!");
    }
}
