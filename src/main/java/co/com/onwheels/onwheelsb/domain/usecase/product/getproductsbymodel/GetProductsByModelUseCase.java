package co.com.onwheels.onwheelsb.domain.usecase.product.getproductsbymodel;

import co.com.onwheels.onwheelsb.domain.model.product.Product;
import co.com.onwheels.onwheelsb.domain.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@RequiredArgsConstructor
public class GetProductsByModelUseCase implements Function <String, Flux<Product>> {

    private final ProductRepository productRepository;

    @Override
    public Flux<Product> apply(String model) {
        return productRepository.getProductsByModel(model);
    }

}
