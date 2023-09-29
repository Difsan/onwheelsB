package co.com.onwheels.onwheelsb.domain.usecase.item.deleteitem;

import co.com.onwheels.onwheelsb.domain.model.item.gateways.ItemRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class DeleteItemUseCase implements Function<String, Mono<Void>> {

    private final ItemRepository itemRepository;

    @Override
    public Mono<Void> apply(String itemId) {
        return itemRepository.deleteItem(itemId);
    }
}
