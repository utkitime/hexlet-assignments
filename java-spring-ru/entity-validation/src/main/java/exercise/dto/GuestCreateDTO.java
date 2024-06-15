package exercise.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;

public class GuestCreateDTO {

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

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getClubCard() {
        return clubCard;
    }

    public void setClubCard(String clubCard) {
        this.clubCard = clubCard;
    }

    public LocalDate getCardValidUntil() {
        return cardValidUntil;
    }

    public void setCardValidUntil(LocalDate cardValidUntil) {
        this.cardValidUntil = cardValidUntil;
    }
}
