package pl.machnikovsky.bookdesk.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;

public enum UserRole implements GrantedAuthority {

    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    String role;

    UserRole(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return this.role;
    }
}
