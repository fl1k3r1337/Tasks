package Payment.Model;

import java.util.ArrayList;
import java.util.List;

public abstract class PaymentMethod
{
    private List<TransactionObserver> observers = new ArrayList<>();

    public void addObserver(TransactionObserver observer) {
        observers.add(observer);
    }

    protected void notifyObservers(TransactionEvent event) {
        for (TransactionObserver observer : observers) {
            observer.onTransactionCompleted(event);
        }
    }


    public abstract void processPayment(double amount) throws ValidationException;
}
