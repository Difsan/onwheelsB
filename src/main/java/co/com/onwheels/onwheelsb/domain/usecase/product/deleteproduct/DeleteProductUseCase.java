package co.com.onwheels.onwheelsb.domain.usecase.product.deleteproduct;

import co.com.onwheels.onwheelsb.domain.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class DeleteProductUseCase implements Function<String, Mono<Void>> {

    private final ProductRepository productRepository;

    @Override
    public Mono<Void> apply(String productId ) {
        return productRepository.deleteProduct(productId);
    }
}
