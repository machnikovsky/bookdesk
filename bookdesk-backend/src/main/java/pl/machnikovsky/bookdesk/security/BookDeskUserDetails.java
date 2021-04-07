package pl.machnikovsky.bookdesk.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.machnikovsky.bookdesk.model.BookDeskUser;

import java.util.Collection;
import java.util.stream.Collectors;

public class BookDeskUserDetails implements UserDetails {

    BookDeskUser user;

    public BookDeskUserDetails(BookDeskUser bookDeskUser) {
        this.user = bookDeskUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user
                .getRoles()
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
