package firstfactor.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import firstfactor.payload.ApiError;
import firstfactor.payload.UserLoginRequest;
import firstfactor.security.authentication.UsernamePasswordAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {



        //Extract username/password and OTP
        //We should check also the inputstream format and reply accordingly in case of error, for simplicity we don't
        UserLoginRequest userLoginRequest = new ObjectMapper()
                .readValue(httpServletRequest.getInputStream(), UserLoginRequest.class);

        try {
            if (userLoginRequest.getOtp() == null) {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthentication(userLoginRequest.getUsername(), userLoginRequest.getPassword()));

                // if we reached here the user is validated
                httpServletResponse.setStatus(HttpServletResponse.SC_OK);

            } else {
                // Here we should call the appropriate provider for validating the OTP
            }

        } catch (AuthenticationException authExc) {
            ApiError apiError = new ApiError();
            apiError.setErrorResponse(authExc.getMessage());

            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpServletResponse.setContentType("application/json");
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.getWriter().write(apiError.toString());
        }

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/login");
    }
}
