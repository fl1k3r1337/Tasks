package Payment.Model;

public class PayPalPayment extends PaymentMethod
{
    private String email;
    private String password;

    public PayPalPayment(String email, String password)
    {
        this.email = email;
        this.password = password;
    }

    @Override
    public void processPayment(double amount) throws ValidationException
    {
        validate(amount);

        System.out.println("Обработка платежа через PayPal...");
        System.out.println("Сумма: " + amount);

        try
        {
            Thread.sleep(2000);
        }
        catch (InterruptedException e) {}

        System.out.println("Платеж успешно обработан");

        TransactionEvent event = new TransactionEvent("PayPal", amount, "SUCCESS");
        notifyObservers(event);
    }

    private void validate(double amount) throws ValidationException
    {
        if (email.isEmpty())
            throw new ValidationException("Пустое поле email");
        if (!(email.contains("@") && email.contains(".")))
            throw new ValidationException("Некорректный email");
        if (password.length() < 6)
            throw new ValidationException("Пароль слишком короткий");
        if (amount <= 0)
            throw new ValidationException("Некорректная сумма операции");
    }
}
