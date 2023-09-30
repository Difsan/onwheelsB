package co.com.onwheels.onwheelsb.infrastructure.drivenadapters;

import co.com.onwheels.onwheelsb.domain.model.item.Item;
import co.com.onwheels.onwheelsb.domain.model.item.gateways.ItemRepository;
import co.com.onwheels.onwheelsb.infrastructure.drivenadapters.data.ItemData;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class MongoRepositoryAdapterItem implements ItemRepository
{
    private final MongoDBRepositoryItem repository;

    private final ObjectMapper mapper;

    @Override
    public Mono<Item> getItemById(String itemId) {

        return this.repository
                .findById(itemId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("There is not " +
                        "item with id: " + itemId)))
                .map(itemData -> mapper.map(itemData, Item.class));
    }

    @Override
    public Mono<Item> saveItem(Item item) {
        return this.repository
                .save(mapper.map(item, ItemData.class))
                .switchIfEmpty(Mono.empty())
                .map(itemData -> mapper.map(itemData, Item.class));
    }

    @Override
    public Mono<Item> updateItem (String itemId, Item item) {

        return this.repository
                .findById(itemId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("There is not " +
                        "item with id: " + itemId)))
                .flatMap(itemData -> {
                    item.setId(itemData.getId());
                    if (item.getQuantity() > item.getProduct().getInventory()){
                        return Mono.error(new IllegalArgumentException("Item Quantity can't be > product Inventory "));
                    }else {
                        var newSubTotal = item.getSubTotal();
                        item.setSubTotal(newSubTotal = (item.getProduct().getUnitaryPrice()*item.getQuantity()));
                        return repository.save(mapper.map(item, ItemData.class));
                    }
                })
                .map(userData -> mapper.map(userData, Item.class));
    }

    @Override
    public Mono<Void> deleteItem (String itemId) {

        return this.repository
                .findById(itemId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("There is not " +
                        "Item with id: " + itemId)))
                .flatMap(itemData -> this.repository.deleteById(itemData.getId()));
    }
}
