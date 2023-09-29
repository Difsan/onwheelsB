package co.com.onwheels.onwheelsb.domain.usecase.item.getitembyid;

import co.com.onwheels.onwheelsb.domain.model.item.Item;
import co.com.onwheels.onwheelsb.domain.model.item.gateways.ItemRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class GetItemByIdUseCase  implements Function<String, Mono<Item>> {

    private final ItemRepository itemRepository;

    @Override
    public Mono<Item> apply(String itemId) {
        return itemRepository.getItemById(itemId);
    }
}
