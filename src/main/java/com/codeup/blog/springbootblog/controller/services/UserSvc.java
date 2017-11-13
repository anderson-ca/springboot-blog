package com.codeup.blog.springbootblog.controller.services;

import com.codeup.blog.springbootblog.controller.models.User;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

//////////////////////////////////////////////////////////////////////////////////
// I should do most of my more complex logic inside the services class.
//////////////////////////////////////////////////////////////////////////////////
@Service("userSvc")
public class UserSvc {
    public boolean isLoggedIn() {
        boolean isAnonymousUser = SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken;
        return !isAnonymousUser;
    }

    public User loggedInUser() {
        if (!isLoggedIn()) {
            return null;
        }
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public boolean isLoggedInAndPostMatchesUser(User user) {
        return isLoggedIn() && (loggedInUser().getId() == user.getId());
    }
}
