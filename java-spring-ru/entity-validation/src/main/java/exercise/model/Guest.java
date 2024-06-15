package exercise.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;


@Entity
@Table(name = "guests")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Guest {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Email(message = "Email should be valid")
    private String email;

    @Pattern(regexp = "\\+\\d{10,12}", message = "Phone number must start with + and contain 11 to 13 digits")
    private String phoneNumber;

    @Pattern(regexp = "\\d{4}", message = "Club card number must be exactly 4 digits")
    private String clubCard;

    @Future(message = "Card validity date must be in the future")
    private LocalDate cardValidUntil;

    @CreatedDate
    private LocalDate createdAt;
}
