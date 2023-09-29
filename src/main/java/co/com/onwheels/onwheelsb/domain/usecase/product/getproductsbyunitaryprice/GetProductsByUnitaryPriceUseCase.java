package co.com.onwheels.onwheelsb.domain.usecase.product.getproductsbyunitaryprice;

import co.com.onwheels.onwheelsb.domain.model.product.Product;
import co.com.onwheels.onwheelsb.domain.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@RequiredArgsConstructor
public class GetProductsByUnitaryPriceUseCase implements Function <Double, Flux<Product>> {

    private final ProductRepository productRepository;

    @Override
    public Flux<Product> apply(Double unitaryPrice) {
        return productRepository.getProductsByUnitaryPrice(unitaryPrice);
    }
}
