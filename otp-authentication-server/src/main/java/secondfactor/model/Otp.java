package secondfactor.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Otp {

    @Id
    private Long id;

    private String otp;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;
}
