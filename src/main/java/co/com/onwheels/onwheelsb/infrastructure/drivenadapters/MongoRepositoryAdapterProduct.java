package co.com.onwheels.onwheelsb.infrastructure.drivenadapters;

import co.com.onwheels.onwheelsb.domain.model.product.Product;
import co.com.onwheels.onwheelsb.domain.model.product.gateways.ProductRepository;
import co.com.onwheels.onwheelsb.infrastructure.drivenadapters.data.ProductData;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class MongoRepositoryAdapterProduct implements ProductRepository
{
    private final MongoDBRepositoryProduct repository;

    private final ObjectMapper mapper;

    @Override
    public Flux<Product> getAllProducts() {
        return this.repository
                .findAll()
                .switchIfEmpty(Flux.empty())
                .map(productData -> mapper.map(productData, Product.class));
    }

    @Override
    public Mono<Product>getProductById(String productId) {

        return this.repository
                .findById(productId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("There is not " +
                        "Product with id: " + productId)))
                .map(productData -> mapper.map(productData, Product.class));
    }

    @Override
    public Flux<Product> getProductsByName(String productName) {
        return this.repository
                .findByName(productName)
                .switchIfEmpty(Flux.empty())
                .map(productData -> mapper.map(productData, Product.class));
    }

    @Override
    public Flux<Product> getProductsByModel(String model) {
        return this.repository
                .findByModel(model)
                .switchIfEmpty(Flux.empty())
                .map(productData -> mapper.map(productData, Product.class));
    }

    @Override
    public Flux<Product> getProductsByUnitaryPrice(Double unitaryPrice) {
        return repository.findByUnitaryPrice(unitaryPrice)
                .switchIfEmpty(Flux.empty())
                .map(productData -> mapper.map(productData, Product.class));
    }

    @Override
    public Flux<Product> getProductsByCategory(String productCategory) {
        return this.repository
                .findByCategory(productCategory)
                .switchIfEmpty(Flux.empty())
                .map(productData -> mapper.map(productData, Product.class));
    }


    @Override
    public Mono<Product> saveProduct(Product product) {
        return this.repository
                .save(mapper.map(product, ProductData.class))
                .switchIfEmpty(Mono.empty())
                .map(productData -> mapper.map(productData, Product.class));
    }

    @Override
    public Mono<Product> updateProduct(String productId, Product product) {

        return this.repository
                .findById(productId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("There is not " +
                        "product with id: " + productId)))
                .flatMap(productData -> {
                    product.setId(productData.getId());
                    if(product.getInventory()==0) product.setInStock(false);
                    return repository.save(mapper.map(product, ProductData.class));
                })
                .map(productData -> mapper.map(productData, Product.class));
    }

    @Override
    public Mono<Void> deleteProduct (String productId) {

        return this.repository
                .findById(productId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("There is not " +
                        "product with id: " + productId)))
                .flatMap(userData -> this.repository.deleteById(userData.getId()));
    }
}
