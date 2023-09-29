package co.com.onwheels.onwheelsb.domain.usecase.product.getallproducts;

import co.com.onwheels.onwheelsb.domain.model.product.Product;
import co.com.onwheels.onwheelsb.domain.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class GetAllProductsUseCase implements Supplier<Flux<Product>> {

    private final ProductRepository productRepository;

    @Override
    public Flux<Product> get() {
        return productRepository.getAllProducts();
    }
}
