package co.com.onwheels.onwheelsb.domain.model.product;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Product {

    private String id;
    private String name;
    private String brand;
    private String model;
    private String description;
    private String image;
    private String category;
    private Double unitaryPrice;
    private Double ivaValue;
    private Integer inventory;
    private boolean inStock;
}
