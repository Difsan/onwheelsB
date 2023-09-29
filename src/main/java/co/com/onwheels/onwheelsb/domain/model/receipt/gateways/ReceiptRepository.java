package co.com.onwheels.onwheelsb.domain.model.receipt.gateways;


import co.com.onwheels.onwheelsb.domain.model.receipt.Receipt;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReceiptRepository {
    Mono<Receipt> getReceiptById(String receiptId);
    Flux<Receipt> getAllReceiptsByUserId(String userId);
    Mono<Receipt> saveReceipt( Receipt receipt);
    Mono<Receipt> updateReceipt ( String receiptId, Receipt receipt);
    Mono<Void> deleteReceipt(String receiptId);
}
