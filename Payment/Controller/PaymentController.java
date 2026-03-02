package Payment.Controller;

import Payment.Model.*;

public class PaymentController
{
    PaymentFactory factory = new PaymentFactory();

    public void processCreditCardPayment(String cardNumber, String cardHolderName, String expiryDate, String cvv, double amount) throws ValidationException
    {
        CreditCardPayment payment = factory.createCreditCardPayment(cardNumber, cardHolderName, expiryDate, cvv);

        payment.addObserver(new LoggerObserver());
        payment.addObserver(new EmailObserver());

        payment.processPayment(amount);
    }

    public void processPayPalPayment(String email, String password, double amount) throws ValidationException
    {
        PayPalPayment payment = factory.createPayPalPayment(email, password);

        payment.addObserver(new LoggerObserver());
        payment.addObserver(new EmailObserver());

        payment.processPayment(amount);
    }

    public void processCryptoPayment(String walletAddress, String cryptoCurrency, double transactionFee, double amount) throws ValidationException
    {
        CryptoPayment payment = factory.createCryptoPayment(walletAddress, cryptoCurrency, transactionFee);

        payment.addObserver(new LoggerObserver());
        payment.addObserver(new EmailObserver());

        payment.processPayment(amount);
    }
}
