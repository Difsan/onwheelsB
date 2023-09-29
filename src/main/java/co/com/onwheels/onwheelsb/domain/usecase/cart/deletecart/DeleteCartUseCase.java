package co.com.onwheels.onwheelsb.domain.usecase.cart.deletecart;

import co.com.onwheels.onwheelsb.domain.model.cart.gateways.CartRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class DeleteCartUseCase implements Function<String, Mono<Void>> {

    private final CartRepository cartRepository;

    @Override
    public Mono<Void> apply(String cartId) {
        return cartRepository.deleteCart(cartId);
    }
}
