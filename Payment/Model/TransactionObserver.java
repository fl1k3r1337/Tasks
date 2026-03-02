package Payment.Model;

public interface TransactionObserver
{
    void onTransactionCompleted(TransactionEvent event);
}
