package firstfactor.security.provider;

import firstfactor.model.User;
import firstfactor.repository.UserRepository;
import firstfactor.security.authentication.OTPAuthentication;
import firstfactor.security.authentication.UsernamePasswordAuthentication;
import firstfactor.security.proxy.OTPAuthenticationServerProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OTPAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private OTPAuthenticationServerProxy proxy;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = String.valueOf(authentication.getPrincipal());
        String otp = String.valueOf(authentication.getCredentials());

        if (username != null && otp != null) {
            Optional<User> optionalUser = this.userRepository.findByUsername(username);

            if (optionalUser.isEmpty()) {
                throw new UsernameNotFoundException("Wrong Credentials");
            }

            User user = optionalUser.get();

            boolean result = proxy.validateOTP(user.getId(), otp);

            if (result) {
                return new OTPAuthentication(username, otp);
            }

            throw new BadCredentialsException("Wrong OTP provided ..."); 

        } else {
            throw new BadCredentialsException("Username and OTP must not be null");
        }
    }

    @Override
    public boolean supports(Class<?> authType) {
        return OTPAuthentication.class.equals(authType);
    }
}
