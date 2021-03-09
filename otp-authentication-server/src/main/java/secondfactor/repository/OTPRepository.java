package secondfactor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import secondfactor.model.Otp;

public interface OTPRepository extends JpaRepository<Otp, Long> {
}
