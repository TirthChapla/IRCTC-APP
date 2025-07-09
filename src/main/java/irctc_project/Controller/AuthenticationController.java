package irctc_project.Controller;

import irctc_project.Service.UserService;
import irctc_project.dto.UserDto;
import irctc_project.record.ErrorResponse;
import irctc_project.record.JwtResponse;
import irctc_project.record.LoginRequest;
import irctc_project.security.JwtHelper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@EnableMethodSecurity
public class AuthenticationController
{

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtHelper jwtHelper;
    private UserService userService;

    //Construction Autowired
    public AuthenticationController(AuthenticationManager authenticationManager,
                                    UserDetailsService userDetailsService,
                                    JwtHelper jwtHelper,
                                    UserService userService)
    {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtHelper = jwtHelper;
        this.userService = userService;
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


    @PostMapping("/register")
    public ResponseEntity<?> registerRequest(
         @Valid @RequestBody UserDto userDto)
    {
        UserDto savedUserDto = userService.registerUser(userDto);

        return new ResponseEntity<>(savedUserDto,HttpStatus.CREATED);

    }

    @GetMapping("/role2")
    @PreAuthorize("hasRole('USER')")
    public String test()
    {
        return "Random URL Role Base Auth.";
    }



}
