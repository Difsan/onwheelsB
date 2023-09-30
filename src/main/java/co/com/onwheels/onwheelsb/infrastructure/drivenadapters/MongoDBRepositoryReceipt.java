package co.com.onwheels.onwheelsb.infrastructure.drivenadapters;

import co.com.onwheels.onwheelsb.infrastructure.drivenadapters.data.ReceiptData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MongoDBRepositoryReceipt extends ReactiveMongoRepository<ReceiptData, String> {
}
