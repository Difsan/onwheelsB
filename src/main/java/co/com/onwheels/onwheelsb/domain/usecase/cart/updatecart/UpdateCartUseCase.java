package co.com.onwheels.onwheelsb.domain.usecase.cart.updatecart;

import co.com.onwheels.onwheelsb.domain.model.cart.Cart;
import co.com.onwheels.onwheelsb.domain.model.cart.gateways.CartRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public class UpdateCartUseCase implements BiFunction<String, Cart, Mono<Cart>> {

    private final CartRepository cartRepository;

    @Override
    public Mono<Cart> apply(String cartId, Cart cart) {
        return cartRepository.updateCart(cartId, cart);
    }
}
