package secondfactor.service;

import lombok.RequiredArgsConstructor;
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

    }


    private void sendOTPViaSms(String OTP, String phoneNumber) {

    }
}
