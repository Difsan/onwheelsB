package co.com.onwheels.onwheelsb.domain.model.user;

import co.com.onwheels.onwheelsb.domain.model.cart.Cart;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {
    private String id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private Cart cart;
    private Boolean admin;
}
