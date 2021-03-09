package secondfactor.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class GenerateOTPUtil {

    public static String generateOTP() {
        try {
            SecureRandom secureRandom = SecureRandom.getInstanceStrong();

            String OTP = String.valueOf(secureRandom.ints(10_000, 100_000)
                    .findFirst().getAsInt());

            return OTP;

        } catch (NoSuchAlgorithmException e) {
            return String.valueOf(new Random().nextInt(100_000));
        }
    }
}
