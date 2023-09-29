package co.com.onwheels.onwheelsb.domain.usecase.user.updateuser;

import co.com.onwheels.onwheelsb.domain.model.user.User;
import co.com.onwheels.onwheelsb.domain.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public class UpdateUserUseCase implements BiFunction<String, User, Mono<User>> {

    private final UserRepository userRepository;

    @Override
    public Mono<User> apply(String userId, User user) {
        return userRepository.updateUser(userId, user);
    }
}
