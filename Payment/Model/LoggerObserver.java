package Payment.Model;

public class LoggerObserver implements TransactionObserver
{
    @Override
    public void onTransactionCompleted(TransactionEvent event)
    {
        System.out.println("[LOG] " + event.toString());
    }
}
