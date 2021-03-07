package secondfactor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import secondfactor.payload.OtpCheckUserRequest;
import secondfactor.payload.OtpGenerateUserRequest;
import secondfactor.repository.OtpRepository;

@Service
@RequiredArgsConstructor
public class OtpService {

    private final OtpRepository otpRepository;

    public void generateOTP(OtpGenerateUserRequest otpGenerateUserRequest) {

    }

    public boolean checkOTP(OtpCheckUserRequest otpCheckUserRequest) {

    }
}
