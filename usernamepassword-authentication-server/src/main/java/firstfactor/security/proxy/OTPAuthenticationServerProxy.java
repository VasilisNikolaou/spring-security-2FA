package firstfactor.security.proxy;

import firstfactor.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OTPAuthenticationServerProxy {

    @Value("${otp.server.baseUrl}")
    private String baseUrl;

    @Autowired
    private RestTemplate restTemplate;

    public void sendOTP(Long id, String phoneNumber) {
        var url = baseUrl + "/generateOTP";

        var body = new User();
        body.setId(id);
        body.setPhoneNumber(phoneNumber);

        var request = new HttpEntity<>(body);

        restTemplate.postForEntity(url, request, Void.class);
    }
}
