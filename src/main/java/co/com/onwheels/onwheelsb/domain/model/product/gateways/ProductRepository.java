package co.com.onwheels.onwheelsb.domain.model.product.gateways;

import co.com.onwheels.onwheelsb.domain.model.product.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepository {
    Flux<Product> getAllProducts();

    Mono<Product> getProductById(String productId);

    // to search just with some letter
    Flux<Product> getProductsByName(String productName);

    Flux<Product> getProductsByModel(String model);

    Flux<Product> getProductsByUnitaryPrice (Double unitaryPrice);

    Flux<Product> getProductsByCategory(String productCategory);

    Mono<Product> saveProduct( Product product);

    Mono<Product> updateProduct ( String productId, Product product);

    Mono<Void> deleteProduct( String productId);
}
