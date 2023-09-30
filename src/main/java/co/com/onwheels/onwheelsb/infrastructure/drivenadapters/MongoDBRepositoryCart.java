package co.com.onwheels.onwheelsb.infrastructure.drivenadapters;


import co.com.onwheels.onwheelsb.infrastructure.drivenadapters.data.CartData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MongoDBRepositoryCart extends ReactiveMongoRepository<CartData, String> {
}
