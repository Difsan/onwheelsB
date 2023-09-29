package co.com.onwheels.onwheelsb.domain.usecase.product.saveproduct;

import co.com.onwheels.onwheelsb.domain.model.product.Product;
import co.com.onwheels.onwheelsb.domain.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class SaveProductUseCase implements Function<Product, Mono<Product>> {

    private final ProductRepository productRepository;

    @Override
    public Mono<Product> apply(Product product) {
        return productRepository.saveProduct(product);
    }
}
