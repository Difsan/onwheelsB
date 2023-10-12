package co.com.onwheels.onwheelsb.infrastructure.drivenadapters.data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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
    private String id = UUID.randomUUID().toString();
    @NotNull(message = "product can't be null")
    private ProductData product;
    @NotNull(message = "quantity can't be null")
    @PositiveOrZero(message = "quantity must be a positive number")
    private Integer quantity;
    private Double subTotal;

    public void setQuantity(Integer quantity) {
        this.quantity = quantity != null ? quantity : 1;
    }


    public void setSubTotal(Double subTotal) {
        this.subTotal = (this.quantity != null) ?
                 this.quantity * this.product.getUnitaryPrice() : 0.0;
    }
}

