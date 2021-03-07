package secondfactor.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OtpGenerateUserRequest {

    private Long id;
    private String phoneNumber;

}
