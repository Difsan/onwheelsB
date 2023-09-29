package co.com.onwheels.onwheelsb.domain.usecase.user.getalluserbyadmin;

import co.com.onwheels.onwheelsb.domain.model.user.User;
import co.com.onwheels.onwheelsb.domain.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@RequiredArgsConstructor
public class GetAllUserByAdminUseCase implements Function<Boolean, Flux<User>> {

    private final UserRepository userRepository;

    @Override
    public Flux<User> apply(Boolean admin) {
        return userRepository.getAllUsersByAdmin(admin);
    }
}
