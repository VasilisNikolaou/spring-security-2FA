package secondfactor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import secondfactor.model.Otp;

public interface OtpRepository extends JpaRepository<Otp, Long> {
}
