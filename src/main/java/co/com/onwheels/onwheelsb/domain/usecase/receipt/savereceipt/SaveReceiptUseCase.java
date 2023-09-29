package co.com.onwheels.onwheelsb.domain.usecase.receipt.savereceipt;

import co.com.onwheels.onwheelsb.domain.model.receipt.Receipt;
import co.com.onwheels.onwheelsb.domain.model.receipt.gateways.ReceiptRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class SaveReceiptUseCase implements Function<Receipt, Mono<Receipt>> {

    private final ReceiptRepository receiptRepository;

    @Override
    public Mono<Receipt> apply(Receipt receipt) {
        return receiptRepository.saveReceipt(receipt);
    }
}
