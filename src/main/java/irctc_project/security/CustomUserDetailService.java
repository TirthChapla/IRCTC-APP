package irctc_project.security;

import irctc_project.Repository.UserRepo;
import irctc_project.entity.User;
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

    private final UserRepo userRepo;

    public CustomUserDetailService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {


        User user = userRepo.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("This email is not found"));


        CustomUserDetail customUserDetail = new CustomUserDetail(user);




        return customUserDetail;





        //✅✅✅✅✅✅✅✅✅✅✅ Satic Username and password

//        UserDetails user = User.builder()
//                .username("user")
//                .password("{noop}user123")
//                .roles("USER")
//                .build();
//
//        if (user.getUsername().equals(username))
//        {
//            System.out.println(user.getUsername());
//
//            return user;
//        }
//        else
//        {
//            throw new UsernameNotFoundException("user not found with this username" + username);
//        }
    }
}
