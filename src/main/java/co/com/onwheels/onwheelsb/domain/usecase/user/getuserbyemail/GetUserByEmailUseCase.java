package co.com.onwheels.onwheelsb.domain.usecase.user.getuserbyemail;

import co.com.onwheels.onwheelsb.domain.model.user.User;
import co.com.onwheels.onwheelsb.domain.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class GetUserByEmailUseCase implements Function<String, Mono<User>> {

    private final UserRepository userRepository;

    @Override
    public Mono<User> apply(String email) {
        return userRepository.getUserByEmail(email);
    }
}
