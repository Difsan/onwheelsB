package co.com.onwheels.onwheelsb.infrastructure.drivenadapters.data;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Document(collection = "carts")
public class CartData {

    @Id
    private String id;
    private Set<ItemData> items = new HashSet<>();
    private Double totalPrice;

    public CartData() {
        this.id = UUID.randomUUID().toString();
        this.items = new HashSet<>();
        this.totalPrice = calculateTotalPrice();
    }

    private Double calculateTotalPrice() {
        return items.stream()
                .mapToDouble(item -> item.getSubTotal() != null ? item.getSubTotal() : 0.0)
                .sum();
    }

}
