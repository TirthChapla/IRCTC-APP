package irctc_project.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecuurityConfig
{

    private JwtAuthenticationFilter authenticationFilter;
    private JWTAuthenticationEntryPoint authenticationEntryPoint;

    //Construction Autowire
    public SecuurityConfig(JwtAuthenticationFilter authenticationFilter,
                           JWTAuthenticationEntryPoint authenticationEntryPoint)
    {
        this.authenticationFilter = authenticationFilter;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception
    {
        // Make the login url public
        // and
        // others are private
        httpSecurity.csrf(e->e.disable()).authorizeHttpRequests( request ->{
            request.requestMatchers("/auth/login","/auth/register")
                    .permitAll()
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .requestMatchers("/user/**").hasRole("USER")
                    .anyRequest()
                    .authenticated();}
        );

        //Backend will not store any thing
        //We make Session stateless
        httpSecurity.sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        //If error occur then this class will run
        httpSecurity.exceptionHandling(e-> e.authenticationEntryPoint(authenticationEntryPoint));

        //authenticationFilter will run before
        httpSecurity.addFilterBefore(authenticationFilter , UsernamePasswordAuthenticationFilter.class);



        return httpSecurity.build();


    }
}
