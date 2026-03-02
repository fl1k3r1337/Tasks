package Payment.Controller;

import Payment.Model.CreditCardPayment;
import Payment.Model.PayPalPayment;
import Payment.Model.CryptoPayment;
import Payment.Model.PaymentMethod;

public class PaymentFactory
{

//    public PaymentMethod createPayment(String type)
//    {
//        switch (type.toLowerCase())
//        {
//            case "credit_card":
//                return createCreditCardPayment();
//            case "crypto":
//                return createCryptoPayment();
//            case "paypal":
//                return createPayPalPayment();
//            default:
//                throw new IllegalArgumentException("Неизвестный тип: " + type);
//        }
//    }


    public CreditCardPayment createCreditCardPayment(String cardNumber, String cardHolderName, String expiryDate, String cvv)
    {
        return new CreditCardPayment(cardNumber, cardHolderName, expiryDate, cvv);
    }

    public PayPalPayment createPayPalPayment(String email, String password)
    {
        return new PayPalPayment(email, password);
    }

    public CryptoPayment createCryptoPayment(String walletAddress, String cryptoCurrency, double transactionFee)
    {
        return new CryptoPayment(walletAddress, cryptoCurrency, transactionFee);
    }

}
