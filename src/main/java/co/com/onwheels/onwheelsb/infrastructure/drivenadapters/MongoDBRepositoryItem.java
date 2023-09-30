package co.com.onwheels.onwheelsb.infrastructure.drivenadapters;


import co.com.onwheels.onwheelsb.infrastructure.drivenadapters.data.ItemData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MongoDBRepositoryItem extends ReactiveMongoRepository<ItemData, String> {
}
