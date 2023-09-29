package co.com.onwheels.onwheelsb.infrastructure.drivenadapters.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document(collection = "items")
@NoArgsConstructor
public class ItemData {

    @Id
    private String id;
    private ProductData product;
    private Integer quantity;
    private Double subTotal;

    public ItemData(ProductData product) {
        this.id = UUID.randomUUID().toString();
        this.product = product;
        this.quantity = 1;
        this.calculateSubTotal();
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
        this.calculateSubTotal();
    }

    private void calculateSubTotal() {
        if (product != null && product.getUnitaryPrice() != null) {
            this.subTotal = this.quantity * product.getUnitaryPrice();
        } else {
            this.subTotal = 0.0;
        }
    }
}

