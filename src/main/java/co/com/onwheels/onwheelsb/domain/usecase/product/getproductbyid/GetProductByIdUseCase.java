package co.com.onwheels.onwheelsb.domain.usecase.product.getproductbyid;

import co.com.onwheels.onwheelsb.domain.model.product.Product;
import co.com.onwheels.onwheelsb.domain.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class GetProductByIdUseCase implements Function<String, Mono<Product>> {

    private final ProductRepository productRepository;

    @Override
    public Mono<Product> apply(String productId) {
        return productRepository.getProductById(productId);
    }
}
