package co.com.onwheels.onwheelsb.domain.usecase.item.saveitem;

import co.com.onwheels.onwheelsb.domain.model.item.Item;
import co.com.onwheels.onwheelsb.domain.model.item.gateways.ItemRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class SaveItemUseCase implements Function<Item, Mono<Item>> {

    private final ItemRepository itemRepository;

    @Override
    public Mono<Item> apply(Item item) {
        return itemRepository.saveItem(item);
    }
}
