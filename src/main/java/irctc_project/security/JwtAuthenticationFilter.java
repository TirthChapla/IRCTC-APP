package irctc_project.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class  JwtAuthenticationFilter extends OncePerRequestFilter
{

    JwtHelper jwtHelper;

    UserDetailsService userDetailsService;

    //Construction Autowired
    public JwtAuthenticationFilter(JwtHelper jwtHelper, UserDetailsService userDetailsService) {
        this.jwtHelper = jwtHelper;
        this.userDetailsService = userDetailsService;
    }


    //👉👉 For every request it will work

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException
    {
        // Bearer vdbfbvkjbvfwgbvbbcgerigvkvbhwtgadfvklfiherh
        String authorisedHeader= request.getHeader("Authorization");

        String username = null;
        String token = null;

        if(authorisedHeader != null && authorisedHeader.startsWith("Bearer "))
        {
            try
            {
                // Remove "Bearer "  prefix
                token = authorisedHeader.substring(7);

                username = jwtHelper.getUsernameFromToken(token);

                //👉👉 Username not equal to null
                //👉👉 Security Context ni ander Authentication na hovu joye

                if(username != null && SecurityContextHolder.getContext().getAuthentication() == null)
                {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                    //👉👉 if token is valid then we will Set it TO SECURIETY CONTEXT

                    if(jwtHelper.isTokenValid(token,userDetails))
                    {
                        //👉👉 we have to add data in SecurityCotext Holder:

                        //👉 Create Authentication by passing User details
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(
                                        userDetails ,
                                        null ,
                                        userDetails.getAuthorities());

                        //👉 it will add ip related info , session related EXTRA INFORMATIONS
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));


                        //👉 Set the authentication to SecurityContextHolder
                        SecurityContextHolder.getContext().setAuthentication(authentication);

                        //✅✅✅✅✅✅✅

                        // IF this authetication not get set to the SecurityContext then it is not authenticated.

                        //✅✅✅✅✅✅✅
                    }

                }


            }//👇👇👇👇👇👇👇 This type of SPECIFIC EXCEPTION will come 👇👇👇👇👇👇👇👇👇

            catch (IllegalArgumentException ex)
            {
                System.out.println("Unable to get JWT Token");
                ex.printStackTrace();
            }
            catch (ExpiredJwtException ex)
            {
                System.out.println("JWT Token has expired");
                ex.printStackTrace();
            }
            catch (MalformedJwtException ex)
            {
                System.out.println("Invalid JWT Token");
                ex.printStackTrace();
            }
            catch (Exception e)
            {
                System.out.println("Invalid Token");
                System.out.println(e.getStackTrace());
            }


        }
        else
        {
            System.out.println("Invalid Authorization Header");
        }

        //👇👇 It will passs the request as it get Authenticated

        filterChain.doFilter(request,response);


    }
}
