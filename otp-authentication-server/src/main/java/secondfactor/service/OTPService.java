package secondfactor.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import secondfactor.model.Otp;
import secondfactor.payload.OTPCheckUserRequest;
import secondfactor.payload.OTPGenerateUserRequest;
import secondfactor.repository.OTPRepository;
import secondfactor.util.GenerateOTPUtil;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class OTPService {

    private final OTPRepository otpRepository;
    private final Environment environment;

    public void generateOTP(OTPGenerateUserRequest otpGenerateUserRequest) {
        Optional<Otp> optionalOTPFor = this.otpRepository.findById(otpGenerateUserRequest.getId());
        String OTP = GenerateOTPUtil.generateOTP();

        if (optionalOTPFor.isPresent()) {
            var existingOTPFor = optionalOTPFor.get();
            existingOTPFor.setOtp(OTP);
            existingOTPFor.setExpiresAt(LocalDateTime.now().plusMinutes(5L));
        } else {

            Otp newOTP = new Otp();
            newOTP.setId(otpGenerateUserRequest.getId());
            newOTP.setOtp(OTP);
            newOTP.setExpiresAt(LocalDateTime.now().plusMinutes(5L));

            this.otpRepository.save(newOTP);
        }

        sendOTPViaSms(OTP, otpGenerateUserRequest.getPhoneNumber());

    }

    public boolean checkOTP(OTPCheckUserRequest otpCheckUserRequest) {
        Optional<Otp> optionalOtp = this.otpRepository.findById(otpCheckUserRequest.getId());

        if (optionalOtp.isPresent()) {
            var existingOTP = optionalOtp.get();

            return existingOTP.getOtp().equals(otpCheckUserRequest.getOtp())
                           && existingOTP.getExpiresAt().isAfter(LocalDateTime.now());
        }

        return false;
    }


    private void sendOTPViaSms(String OTP, String phoneNumber) {
        Twilio.init(environment.getProperty("twilio.accountSid"), environment.getProperty("twilio.authToken"));
        Message message = Message.creator(
                new PhoneNumber(phoneNumber),
                new PhoneNumber(environment.getProperty("twilio.phoneNumber")),
                "Your OTP is: " + OTP + ", and it's valid for 5 minutes."
        ).create();
    }
}
