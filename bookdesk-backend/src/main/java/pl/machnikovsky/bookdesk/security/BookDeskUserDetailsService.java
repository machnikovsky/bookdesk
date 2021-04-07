package pl.machnikovsky.bookdesk.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;
import pl.machnikovsky.bookdesk.repository.BookDeskUserRepository;

@Service
public class BookDeskUserDetailsService implements UserDetailsService {

    BookDeskUserRepository bookDeskUserRepository;


    public BookDeskUserDetailsService(BookDeskUserRepository bookDeskUserRepository) {
        this.bookDeskUserRepository = bookDeskUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return bookDeskUserRepository
                .findByUsernameIgnoreCase(s)
                .map(BookDeskUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(s));

    }
}
