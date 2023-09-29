package co.com.onwheels.onwheelsb.domain.usecase.cart.savecart;

import co.com.onwheels.onwheelsb.domain.model.cart.Cart;
import co.com.onwheels.onwheelsb.domain.model.cart.gateways.CartRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class SaveCartUseCase implements Function<Cart, Mono<Cart>> {

    private final CartRepository cartRepository;

    @Override
    public Mono<Cart> apply(Cart cart) {
        return cartRepository.saveCart(cart);
    }
}
