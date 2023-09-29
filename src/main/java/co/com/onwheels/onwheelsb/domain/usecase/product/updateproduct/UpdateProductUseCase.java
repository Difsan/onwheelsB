package co.com.onwheels.onwheelsb.domain.usecase.product.updateproduct;

import co.com.onwheels.onwheelsb.domain.model.product.Product;
import co.com.onwheels.onwheelsb.domain.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public class UpdateProductUseCase implements BiFunction<String, Product, Mono<Product>> {

    private final ProductRepository productRepository;


    @Override
    public Mono<Product> apply(String productId, Product product) {
        return productRepository.updateProduct(productId, product);
    }
}
