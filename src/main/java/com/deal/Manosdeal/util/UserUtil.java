package com.deal.Manosdeal.util;

import com.deal.Manosdeal.model.Role;
import com.deal.Manosdeal.model.User;
import com.deal.Manosdeal.model.UserPrincipal;
import com.deal.Manosdeal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

public class UserUtil {
    @Autowired
    private static UserRepository userRepository;

    public static User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            User user = ((UserPrincipal) principal).getUser();
            return user;
        }
        return null;

    }

    public static boolean isAdmin(User user) {
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            if (role.getName().equalsIgnoreCase("ADMIN"))
                return true;
        }
        return false;
    }


}
