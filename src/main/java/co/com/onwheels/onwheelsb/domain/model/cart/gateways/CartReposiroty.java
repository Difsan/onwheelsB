package co.com.onwheels.onwheelsb.domain.model.cart.gateways;

import co.com.onwheels.onwheelsb.domain.model.cart.Cart;
import co.com.onwheels.onwheelsb.domain.model.item.Item;
import reactor.core.publisher.Mono;

public interface CartReposiroty {

    Mono<Cart> getCartById(String cartId);

    Mono<Cart> saveCart(Cart cart);

    Mono<Cart> addItemToList(String cartId, Item item);

    Mono<Cart> removeItemFromList(String cartId, Item item);

    Mono<Cart> updateCart(String cartId, Cart cart);

    Mono<Void> deleteCart(String cartId);
}
