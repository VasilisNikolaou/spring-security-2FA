package firstfactor.security.provider;

import firstfactor.model.User;
import firstfactor.repository.UserRepository;
import firstfactor.security.authentication.UsernamePasswordAuthentication;
import firstfactor.security.proxy.OTPAuthenticationServerProxy;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsernamePasswordProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OTPAuthenticationServerProxy proxy;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = String.valueOf(authentication.getPrincipal());
        String password = String.valueOf(authentication.getCredentials());

        if(username != null && password != null) {
            Optional<User> optionalUser = this.userRepository.findByUsername(username);

            if(optionalUser.isEmpty()) {
                throw new UsernameNotFoundException("Wrong Credentials");
            }

            User user = optionalUser.get();

            if(!passwordEncoder.matches(password, user.getPassword())) {
                throw new BadCredentialsException("Wrong Credentials");
            }

            //Before we return we should send the OTP to user's phone.
            proxy.sendOTP(user.getId(), user.getPhoneNumber());

            return new UsernamePasswordAuthentication(username, password);


        } else {
            throw new BadCredentialsException("Username and password must not be null");
        }
    }

    @Override
    public boolean supports(Class<?> authType) {
        return authType.equals(UsernamePasswordAuthentication.class);
    }
}
