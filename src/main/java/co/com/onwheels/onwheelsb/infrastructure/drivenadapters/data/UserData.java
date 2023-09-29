package co.com.onwheels.onwheelsb.infrastructure.drivenadapters.data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document(collection = "users")
@NoArgsConstructor
public class UserData {

    @Id
    private String id= UUID.randomUUID().toString().substring(0,10);

    @NotNull(message = "Name can't be null")
    @NotBlank(message = "Name can't be empty")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must contain only letters")
    private String name;

    @NotNull(message = "LastName can't be null")
    @NotBlank(message = "LastName can't be empty")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must contain only letters")
    private String lastName;

    @NotNull(message = "Email can't be null")
    @NotBlank(message = "Email can't be empty")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email address format")
    @Indexed(unique = true)
    private String email;

    @NotNull(message = "Password can't be null")
    @NotBlank(message = "Password can't be empty")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*(),.?\":{}|<>])(?=\\S+$).{10}$",
            message = "password must be 15 characters long, include at least one number, one capital letter, and one special character")
    private String password;

    private CartData cart;

    @NotNull(message = "admin can't be null")
    @NotBlank(message = "admin can't be empty")
    private boolean admin;

}
