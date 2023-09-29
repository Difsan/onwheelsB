package co.com.onwheels.onwheelsb.domain.model.user.gateways;

import co.com.onwheels.onwheelsb.domain.model.user.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository {

    Flux<User> getAllUsersByAdmin(Boolean admin);
    Mono<User> getUserById(String userId);

    //It should be implemented in cart(?)
    Mono<User> getUserByCartId(String cartId);

    Mono<User> getUserByEmail(String email);

    Mono<User> saveUser( User user);

    Mono<User> updateUser( String userId, User user);

    Mono<Void> deleteUser( String userId);
}
