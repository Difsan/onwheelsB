package co.com.onwheels.onwheelsb.domain.model.product.receipt.getallreceiptsbyuserid;

import co.com.onwheels.onwheelsb.domain.model.receipt.Receipt;
import co.com.onwheels.onwheelsb.domain.model.receipt.gateways.ReceiptRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@RequiredArgsConstructor
public class GetAllReceiptsByUserIdUseCase implements Function<String, Flux<Receipt>> {

    private final ReceiptRepository receiptRepository;

    @Override
    public Flux<Receipt> apply(String userId) {
        return receiptRepository.getAllReceiptsByUserId(userId);
    }
}
