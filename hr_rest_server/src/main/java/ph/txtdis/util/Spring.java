package ph.txtdis.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ph.txtdis.domain.Authority;
import ph.txtdis.domain.User;

public class Spring {

    public static User user() {
        return (User) authentication().getPrincipal();
    }

    public static String encode(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    public static boolean matchPasswords(Authentication authenticate,
            User user) {
        return new BCryptPasswordEncoder().matches(authenticate.getCredentials()
                .toString(), user.getPassword());
    }

    public static void setPassword(String password) {
        setAuthentication(authenticate(password));
    }

    public static boolean isAuthenticated() {
        return authentication() == null ? false
                : authentication().isAuthenticated();
    }

    public static String username() {
        return authentication().getName();
    }

    public static String password() {
        return authentication().getCredentials().toString();
    }

    public static void setCredentialsForValidation(String username,
            String password) {
        setAuthentication(authenticate(username, password));
    }

    public static void setAuthentication(User user, String password,
            List<GrantedAuthority> roles) {
        setAuthentication(authenticate(user, password, roles));
    }

    public static UsernamePasswordAuthenticationToken authorize(User user,
            Authentication authenticate, List<GrantedAuthority> roles) {
        return new UsernamePasswordAuthenticationToken(user, authenticate
                .getCredentials(), roles);
    }

    public static void updateUser(User user) {
        setAuthentication(authenticate(user, password(), getRoles()));
    }

    @SuppressWarnings("unchecked")
    private static List<GrantedAuthority> getRoles() {
        return (List<GrantedAuthority>) authentication().getAuthorities();
    }

    public static List<GrantedAuthority> toGranted(List<Authority> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role
                .getAuthority().toString())));
        return authorities;
    }

    private static void setAuthentication(
            UsernamePasswordAuthenticationToken token) {
        security().setAuthentication(token);
    }

    private static UsernamePasswordAuthenticationToken authenticate(User user,
            String password, List<GrantedAuthority> roles) {
        return new UsernamePasswordAuthenticationToken(user, password, roles);
    }

    private static UsernamePasswordAuthenticationToken authenticate(
            String password) {
        return new UsernamePasswordAuthenticationToken(user(), password,
                getRoles());
    }

    private static UsernamePasswordAuthenticationToken authenticate(
            String username, String password) {
        return new UsernamePasswordAuthenticationToken(username, password);
    }

    private static Authentication authentication() {
        return security().getAuthentication();
    }

    private static SecurityContext security() {
        return SecurityContextHolder.getContext();
    }
}
