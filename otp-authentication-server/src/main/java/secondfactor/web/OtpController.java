package secondfactor.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import secondfactor.payload.OtpCheckUserRequest;
import secondfactor.payload.OtpGenerateUserRequest;
import secondfactor.service.OtpService;

@RestController
@RequiredArgsConstructor
public class OtpController {

    private final OtpService otpService;

    @PostMapping("/generateOTP")
    public void generateOTP(@RequestBody OtpGenerateUserRequest otpGenerateUserRequest) {

    }

    @PostMapping("/checkOTP")
    public boolean checkOTP(@RequestBody OtpCheckUserRequest otpCheckUserRequest) {

    }
}
