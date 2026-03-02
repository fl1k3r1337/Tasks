package Payment.View;

import Payment.Controller.PaymentController;
import Payment.Model.ValidationException;

import java.util.Scanner;

public class ConsoleManager
{
    Scanner scanner = new Scanner(System.in);
    PaymentController controller = new PaymentController();

    public void handleUser()
    {
        boolean running = true;

        while (running)
        {
            printMenu();
            int value = getIntFromUser();

            switch (value)
            {
                case 1:
                    handleCreditCardPayment();
                    break;

                case 2:
                    handlePayPalPayment();
                    break;

                case 3:
                    handleCryptoPayment();
                    break;

                case 0:
                    System.out.println("До свидания");
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор! Выберите 0, 1, 2 или 3");
            }
        }
    }

    public int getIntFromUser()
    {
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    public String getStringFromUser()
    {
        return scanner.nextLine();
    }

    public double getDoubleFromUser()
    {
        return scanner.nextDouble();
    }

    public void askUser(String message)
    {
        System.out.println(message);
    }

    public void printMenu()
    {
        System.out.println("\nВыберите способ оплаты:\n" +
                            "1. Кредитная карта\n" +
                            "2. PayPal\n" +
                            "3. Криптовалюта\n" +
                            "0. Выход");
    }

    public void handleCreditCardPayment()
    {
        askUser("Введите номер карты");
        String cardNumber = getStringFromUser();
        askUser("Введите имя владельца (в формате IVAN PETROV)");
        String cardHolderName = getStringFromUser();
        askUser("Введите дату до которой действует карта (в формате 03/30)");
        String expiryDate = getStringFromUser();
        askUser("Введите cvv код");
        String cvv = getStringFromUser();
        askUser("Введите сумму операции");
        double amount = getDoubleFromUser();

        try
        {
            controller.processCreditCardPayment(cardNumber, cardHolderName, expiryDate, cvv, amount);
            System.out.println("Операция успешно завершена");
        }
        catch (ValidationException e)
        {
            System.out.println("Ошибка: " + e.getMessage());
        }


    }

    public void handlePayPalPayment()
    {
        askUser("Введите email");
        String email = getStringFromUser();
        askUser("Введите пароль");
        String password = getStringFromUser();
        askUser("Введите сумму операции");
        double amount = getDoubleFromUser();

        try
        {
            controller.processPayPalPayment(email, password, amount);
        } catch (ValidationException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public void handleCryptoPayment()
    {
        askUser("Введите адрес криптокошелька");
        String walletAddress = getStringFromUser();
        askUser("Выберите криптовалюту (USDT, BTC, ETH)");
        String cryptoCurrency = getStringFromUser();
        askUser("Введите комиссию платформы (в формате 0,03)");
        double transactionFee = getDoubleFromUser();
        askUser("Введите сумму операции");
        double amount = getDoubleFromUser();

        try
        {
            controller.processCryptoPayment(walletAddress, cryptoCurrency, transactionFee, amount);
        } catch (ValidationException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }



    public static void main(String[] args)
    {
        ConsoleManager manager = new ConsoleManager();
        manager.handleUser();
    }
}
