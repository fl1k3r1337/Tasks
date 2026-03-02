package Payment.Model;

public class TransactionEvent
{
    private String transactionType;
    private double amount;
    private String status;

    public TransactionEvent(String transactionType, double amount, String status)
    {
        this.amount = amount;
        this.transactionType = transactionType;
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "Транзакция: " + transactionType +
                ", Сумма: " + amount +
                ", Статус: " + status;
    }
}
