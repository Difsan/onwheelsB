package co.com.onwheels.onwheelsb.infrastructure.drivenadapters;

import co.com.onwheels.onwheelsb.domain.model.user.User;
import co.com.onwheels.onwheelsb.domain.model.user.gateways.UserRepository;
import co.com.onwheels.onwheelsb.infrastructure.drivenadapters.data.UserData;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class MongoRepositoryAdapterUser implements UserRepository
{
    private final MongoDBRepositoryUser repository;

    private final ObjectMapper mapper;

    @Override
    public Flux<User> getAllUsersByAdmin(Boolean admin) {
        return repository.findByAdmin(admin)
                .switchIfEmpty(Mono.error(new Throwable("No users available")))
                .map(userData -> mapper.map(userData, User.class));
    }

    @Override
    public Mono<User> getUserById(String userId) {
        return this.repository
                .findById(userId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("There is not " +
                        "user with id: " + userId)))
                .map(userData -> mapper.map(userData, User.class));
    }

    @Override
    public Mono<User> getUserByCartId(String cartId) {
        return this.repository
                .findByCartId(cartId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("There is not " +
                        "user with cartId: " + cartId)))
                .map(userData -> mapper.map(userData, User.class));
    }

    @Override
    public Mono<User> getUserByEmail(String email) {
        return this.repository.findByEmail(email)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("There is not " +
                        "user with email: " + email)))
                .map(userData -> mapper.map(userData, User.class));
    }

    @Override
    public Mono<User> saveUser(User user) {
        return this.repository
                .save(mapper.map(user, UserData.class))
                .switchIfEmpty(Mono.empty())
                .map(userData -> mapper.map(userData, User.class));
    }

    @Override
    public Mono<User> updateUser(String userId, User user) {

        return this.repository
                .findById(userId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("There is not " +
                        "user with id: " + userId)))
                .flatMap(userData -> {
                    user.setId(userData.getId());
                    return repository.save(mapper.map(user, UserData.class));
                })
                .map(userData -> mapper.map(userData, User.class));
    }

    @Override
    public Mono<Void> deleteUser(String userId) {

        return this.repository
                .findById(userId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("There is not " +
                        "user with id: " + userId)))
                .flatMap(userData -> this.repository.deleteById(userData.getId()));
    }
}
