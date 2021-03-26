package firstfactor.security.proxy;

import firstfactor.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Component
public class OTPAuthenticationServerProxy {

    @Value("${otp.server.baseUrl}")
    private String baseUrl;

    @Autowired
    private RestTemplate restTemplate;

    public void sendOTP(Long id, String phoneNumber) {
        var url = baseUrl + "/generateOTP";
        var body = new HashMap<String, String>();

        body.put("id", String.valueOf(id));
        body.put("phoneNumber", phoneNumber);

        var request = new HttpEntity<>(body);

        restTemplate.postForEntity(url, request, Void.class);
    }

    public boolean validateOTP(Long id, String otp) {
        var url = baseUrl + "/checkOTP";
        var body = new HashMap<String, String>();

        body.put("id", String.valueOf(id));
        body.put("otp", otp);

        var request = new HttpEntity<>(body);

        var response = restTemplate.postForEntity(url, request, Boolean.class);

        return response.getBody();
    }
}
