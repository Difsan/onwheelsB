package co.com.onwheels.onwheelsb.infrastructure.drivenadapters.data;

import co.com.onwheels.onwheelsb.infrastructure.drivenadapters.util.validators.CategoryEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document(collection = "products")
@NoArgsConstructor
public class ProductData {

    @Id
    private String id= UUID.randomUUID().toString();

    @NotNull(message = "Name can't be null")
    @NotBlank(message = "Name can't be empty")
    @Pattern(regexp = "^[A-Z][a-z]*$", message = "First letter must be uppercase, and others must be lowercase")
    private String name;

    @NotNull(message = "Brand can't be null")
    @NotBlank(message = "Brand can't be empty")
    @Pattern(regexp = "^[A-Z][a-z]*$", message = "First letter must be uppercase, and others must be lowercase")
    private String brand;

    @NotNull(message = "model can't be null")
    @NotBlank(message = "model can't be empty")
    @Pattern(regexp = "^[0-9]{4}$", message = "model must contain only 4 numbers")
    private String model;

    private String description;

    @NotNull(message = "Image can't be null")
    @NotBlank(message = "Image can't be empty")
    private String image;

    @NotNull(message = "Category can't be null")
    @NotBlank(message = "Category can't be empty")
    @CategoryEnum(message = "category should be 'Hatchback', 'Sedan', 'MPV'," +
            " 'Sports', 'pickup', 'Coupe'")
    private String category;

    @NotNull(message = "UnitaryPrice can't be null")
    private Double unitaryPrice;

    @NotNull(message = "UnitaryPrice can't be null")
    private Integer ivaValue;

    @NotNull(message = "Inventory can't be null")
    private Integer inventory;

    private boolean inStock= true;


}
