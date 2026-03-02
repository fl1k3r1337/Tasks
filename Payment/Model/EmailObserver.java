package Payment.Model;

public class EmailObserver implements TransactionObserver
{
    @Override
    public void onTransactionCompleted(TransactionEvent event) {
        System.out.println("[EMAIL] Отправлен чек на почту");
    }
}
