package co.com.onwheels.onwheelsb.infrastructure.drivenadapters;

import co.com.onwheels.onwheelsb.infrastructure.drivenadapters.data.ProductData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface MongoDBRepositoryProduct extends ReactiveMongoRepository<ProductData, String> {
    Flux<ProductData> findByName(String productName);
    Flux<ProductData> findByModel(String model);
    Flux<ProductData> findByUnitaryPrice(Double unitaryPrice);
    Flux<ProductData> findByCategory(String productCategory);
}
