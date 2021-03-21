package firstfactor.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginRequest {

    private Long id;
    private String username;
    private String password;
    private String otp;
}
