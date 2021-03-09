package secondfactor.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OTPGenerateUserRequest {

    private Long id;
    private String phoneNumber;

}
