package co.com.onwheels.onwheelsb.infrastructure.drivenadapters;

import co.com.onwheels.onwheelsb.domain.model.user.User;
import co.com.onwheels.onwheelsb.infrastructure.drivenadapters.data.UserData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MongoDBRepositoryUser extends ReactiveMongoRepository<UserData, String> {
    Mono<User> findByEmail(String email);
    Flux<User> findByAdmin(boolean admin);
    Mono<User> findByCartId(String cartId);
}
