package irctc_project.Controller;

import irctc_project.record.ErrorResponse;
import irctc_project.record.JwtResponse;
import irctc_project.record.LoginRequest;
import irctc_project.security.JwtHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController
{

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtHelper jwtHelper;

    //Construction Autowired
    public AuthenticationController(AuthenticationManager authenticationManager,
                                    UserDetailsService userDetailsService,
                                    JwtHelper jwtHelper)
    {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtHelper = jwtHelper;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loging(@RequestBody LoginRequest loginRequest)
    {
        // 👉👉👉 First we authenticate the user.

        //👉 AuthenticationManager will authenticate the user.

        try
        {
            //👉👉 This will create bunch of Username and Password
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken
                            (loginRequest.username(),
                                    loginRequest.password());

            //👉👉 we will pass it to Authenticate it.
            this.authenticationManager.authenticate(authentication);


            //✅✅✅✅ NOW we will Geerate Token

            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.username());

            String token = this.jwtHelper.generateToken(userDetails);

            JwtResponse jwtResponse = new JwtResponse(
                    token,
                    loginRequest.username()
            );

            return new ResponseEntity<>(jwtResponse , HttpStatus.OK);


        }catch (BadCredentialsException e)
        {
            ErrorResponse errorResponse = new ErrorResponse(
                    "Invalid credencials",
                    "403",
                    false);

            e.printStackTrace();
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

}
