package secondfactor.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import secondfactor.payload.OTPCheckUserRequest;
import secondfactor.payload.OTPGenerateUserRequest;
import secondfactor.service.OTPService;

@RestController
@RequiredArgsConstructor
public class OTPController {

    private final OTPService otpService;

    @PostMapping("/generateOTP")
    public void generateOTP(@RequestBody OTPGenerateUserRequest otpGenerateUserRequest) {
        this.otpService.generateOTP(otpGenerateUserRequest);
    }

    @PostMapping("/checkOTP")
    public boolean checkOTP(@RequestBody OTPCheckUserRequest otpCheckUserRequest) {
        
    }
}
