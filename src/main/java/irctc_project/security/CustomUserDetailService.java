package irctc_project.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService
{

//    ❌❌  It is giving Circular Depedenciey

//    PasswordEncoder passwordEncoder;
//
//    // Constructor Autowired
//    public CustomUserDetailService(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        UserDetails user = User.builder()
                .username("user")
                .password("{noop}user123")
                .roles("USER")
                .build();

        if (user.getUsername().equals(username))
        {
            return user;
        }
        else
        {
            throw new UsernameNotFoundException("user not found with this username" + username);
        }
    }
}
