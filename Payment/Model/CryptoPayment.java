package Payment.Model;

public class CryptoPayment extends PaymentMethod
{
    private String walletAddress;
    private String cryptoCurrency; //BTC, ETH, USDT
    private double transactionFee;

    public CryptoPayment(String walletAddress, String cryptoCurrency, double transactionFee)
    {
        this.walletAddress = walletAddress;
        this.cryptoCurrency = cryptoCurrency;
        this.transactionFee = transactionFee;
    }

    @Override
    public void processPayment(double amount) throws ValidationException
    {
        validate(amount);

        System.out.println("Обработка платежа через криптовалюту...");
        System.out.println("Сумма: " + amount);

        try
        {
            Thread.sleep(2000);
        }
        catch (InterruptedException e) {}

        System.out.println("Платеж успешно обработан");
        TransactionEvent event = new TransactionEvent("Сrypto", amount, "SUCCESS");
        notifyObservers(event);

    }

    private void validate(double amount) throws ValidationException
    {
        if (transactionFee < 0)
            throw new ValidationException("Некорректная комиссия");
        if (!(cryptoCurrency.equals("ETH") || cryptoCurrency.equals("BTC") || cryptoCurrency.equals("USDT")))
            throw new ValidationException("Неподдерживаемая криптовалюта");
        if (walletAddress.isEmpty())
            throw new ValidationException("Пустой адрес кошелька");
        if (amount <= 0)
            throw new ValidationException("Некорректная сумма операции");
    }
}
