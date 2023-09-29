package co.com.onwheels.onwheelsb.domain.model.item;

import co.com.onwheels.onwheelsb.domain.model.product.Product;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Item {

    private String id;
    private Product product;
    private Integer quantity;
    private Double subTotal;
}
