package co.com.onwheels.onwheelsb.domain.model.product.receipt.updatereceipt;

import co.com.onwheels.onwheelsb.domain.model.receipt.Receipt;
import co.com.onwheels.onwheelsb.domain.model.receipt.gateways.ReceiptRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public class UpdateReceiptUseCase implements BiFunction<String, Receipt, Mono<Receipt>> {

    private final ReceiptRepository receiptRepository;

    @Override
    public Mono<Receipt> apply(String receiptId, Receipt receipt) {
        return receiptRepository.updateReceipt(receiptId, receipt);
    }
}
