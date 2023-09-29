package co.com.onwheels.onwheelsb.domain.model.cart;

import co.com.onwheels.onwheelsb.domain.model.item.Item;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Cart {

    private String id = UUID.randomUUID().toString().substring(0,10);
    private Set<Item> items = new HashSet<>();
    private Double totalPrice;
}
