package com.basakpie.security;

import com.basakpie.security.type.RoleType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by daesungkim on 2017. 5. 11..
 */
@SuppressWarnings("unchecked")
public final class SecurityContextUtils implements Serializable {

    private static final Long serialVersionUID = 1L;

    public SecurityContextUtils() {
    }

    public static boolean isLoggedIn() {
        Authentication authentication = authentication();
        if(authentication==null) {
            return false;
        }
        return authentication.isAuthenticated();
    }

    public static boolean hasRole(RoleType type) {
        Authentication authentication = authentication();
        if(authentication==null) {
            return false;
        }
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(type.name());
        boolean hasRole = authentication.getAuthorities().contains(grantedAuthority);
        return hasRole;
    }

    public static User getUser() {
        Authentication authentication = authentication();
        if(authentication==null) {
            return new User();
        }
        User user = (User)authentication.getPrincipal();
        return user;
    }

    public static List<RoleType> getRoleTypes() {
        Authentication authentication = authentication();
        if(authentication==null) {
            return new ArrayList<>();
        }
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) authentication.getAuthorities();
        List<RoleType> roleTypes = authorities.stream().map(x -> RoleType.findByType(x.getAuthority())).collect(Collectors.toList());
        return roleTypes;
    }

    @SuppressWarnings("unchecked")
    private static Authentication authentication() {
        final SecurityContext securityContext = SecurityContextHolder.getContext();
        if(securityContext==null) {
            throw new SecurityContextNotFoundException("SecurityContext Not Found Exception");
        }

        final Authentication authentication = securityContext.getAuthentication();
        if(authentication==null) {
            throw new SecurityContextNotFoundException("SecurityContext Authentication Not Found Exception");
        }
        return authentication;
    }

}
