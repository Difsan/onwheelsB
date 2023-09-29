package co.com.onwheels.onwheelsb.domain.usecase.user.getuserbycartid;

import co.com.onwheels.onwheelsb.domain.model.user.User;
import co.com.onwheels.onwheelsb.domain.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class GetUserByCartIdUseCase implements Function<String, Mono<User>> {

    private final UserRepository userRepository;

    @Override
    public Mono<User> apply(String cartId) {
        return userRepository.getUserByCartId(cartId);
    }
}
