package co.com.onwheels.onwheelsb.domain.usecase.product.getproductbycategory;

import co.com.onwheels.onwheelsb.domain.model.product.Product;
import co.com.onwheels.onwheelsb.domain.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@RequiredArgsConstructor
public class GetProductByCategoryUseCase implements Function<String, Flux<Product>> {

    private final ProductRepository productRepository;

    @Override
    public Flux<Product> apply(String productCategory) {
        return productRepository.getProductsByCategory(productCategory);
    }
}
