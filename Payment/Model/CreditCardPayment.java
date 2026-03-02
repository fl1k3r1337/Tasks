package Payment.Model;

import java.util.List;

public class CreditCardPayment extends PaymentMethod
{
    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String cvv;

    public CreditCardPayment(String cardNumber, String cardHolderName, String expiryDate, String cvv)
    {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    @Override
    public void processPayment(double amount) throws ValidationException
    {
        validate(amount);

        System.out.println("Обработка платежа через кредитную карту...");
        System.out.println("Сумма: " + amount);

        try
        {
            Thread.sleep(2000);
        }
        catch (InterruptedException e) {}

        System.out.println("Платеж успешно обработан");

        TransactionEvent event = new TransactionEvent("CreditCard", amount, "SUCCESS");
        notifyObservers(event);
    }

    private void validate(double amount) throws ValidationException
    {
        if (!(cardNumber.length() == 16))
            throw new ValidationException("Некорректная длина номера карты");
        if ((cvv.length() != 3))
            throw new ValidationException("Некорректный cvv код");
        if (cardHolderName.isEmpty())
            throw new ValidationException("Имя владельца пустое");
        validateExpiryDate();
        if (amount <= 0)
            throw new ValidationException("Некорректная сумма операции");
    }

    private void validateExpiryDate() throws ValidationException
    {
        String[] date = expiryDate.split("/");

        int cardMonth = Integer.parseInt(date[0]);
        int cardYear = Integer.parseInt(date[1]);

        int currentYear = 26;
        int currentMonth = 3;

        if (cardYear < currentYear) {
            throw new ValidationException("Срок действия карты истек");
        }

        if (cardYear == currentYear && cardMonth < currentMonth) {
            throw new ValidationException("Срок действия карты истек");
        }

        if (cardMonth < 1 || cardMonth > 12) {
            throw new ValidationException("Некорректный месяц");
        }
    }

}
