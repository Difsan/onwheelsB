package co.com.onwheels.onwheelsb.domain.model.item.gateways;

import co.com.onwheels.onwheelsb.domain.model.item.Item;
import reactor.core.publisher.Mono;

public interface ItemRepository {

    Mono<Item> getItemById(String itemId);

    Mono<Item> saveItem(Item item);

    Mono<Item> updateItem ( String itemId, Item item);

    Mono<Void> deleteItem( String itemId);
}
