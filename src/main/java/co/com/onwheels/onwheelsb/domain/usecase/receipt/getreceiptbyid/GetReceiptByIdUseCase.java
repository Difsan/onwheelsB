package co.com.onwheels.onwheelsb.domain.usecase.receipt.getreceiptbyid;

import co.com.onwheels.onwheelsb.domain.model.receipt.Receipt;
import co.com.onwheels.onwheelsb.domain.model.receipt.gateways.ReceiptRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class GetReceiptByIdUseCase implements Function<String, Mono<Receipt>> {

    private final ReceiptRepository receiptRepository;

    @Override
    public Mono<Receipt> apply(String receiptId) {
        return receiptRepository.getReceiptById(receiptId);
    }
}
