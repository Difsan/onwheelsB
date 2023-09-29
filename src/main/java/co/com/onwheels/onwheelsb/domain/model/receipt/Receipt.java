package co.com.onwheels.onwheelsb.domain.model.receipt;

import co.com.onwheels.onwheelsb.domain.model.cart.Cart;
import co.com.onwheels.onwheelsb.domain.model.user.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Receipt {
    private String id;
    private Cart cart;
    private LocalDateTime createDate;
    private User user;
    private String phone;
    private String address;
}
