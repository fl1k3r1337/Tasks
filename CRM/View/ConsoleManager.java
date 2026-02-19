package CRM.View;

import CRM.Controller.CrmService;
import CRM.Model.Client;
import CRM.Model.Deal;
import CRM.Model.Employee;

import java.util.Scanner;

public class ConsoleManager
{
    private Scanner scanner;

    public ConsoleManager()
    {
        this.scanner = new Scanner(System.in);
    }

    public void printMenu()
    {
        System.out.println("\nДобро пожаловать в CRM! Выберите действие:\n" +
                            "1. Добавить сделку\n" +
                            "2. Вывести все сделки\n" +
                            "3. Отсортировать сделки по статусу\n" +
                            "4. Изменить статус сделки\n" +
                            "5. Посчитать комиссию сделки\n" +
                            "0. Выход");
    }

    public void printExitMessage()
    {
        System.out.println("До свидания!");
    }

    public Deal printAddDealMessage(int id)
    {

        Client client = CrmService.getClientInformation(scanner);

        requireInfo("Введите имя, фамилию и комиссию (в формате 0.5) Работника");
        Employee employee = CrmService.getEmployeeInformation(scanner);

        requireInfo("Введите сумму сделки:");
        int value = CrmService.getValueInformation(scanner);

        return new Deal(client, employee, value, id);
    }

    public void printSuccess(String message) {System.out.println(message);}

    public void requireInfo(String message) {System.out.println(message);}

    public void printError(String message) {System.out.println(message);}

    public int getIdFromUser()
    {
        int id = scanner.nextInt();
        scanner.nextLine();

        return id;
    }

    public String getStringFromUser()
    {
        return this.scanner.nextLine();
    }

    public String getClientInfoFromUser()
    {
        requireInfo("Введите имя и фамилию Клиента");
        return this.scanner.nextLine();
    }

    public String getEmployeeInfoFromUser()
    {
        requireInfo("Введите имя, фамилию и комиссию (в формате 0.5) Работника");
        return this.scanner.nextLine();
    }

    public int getDealValueFromUser()
    {
        requireInfo("Введите сумму сделки:");
        int value = this.scanner.nextInt();
        this.scanner.nextLine();

        return value;
    }

    public int getDealIdFromUser()
    {
        requireInfo("Введите id сделки:");
        int id = scanner.nextInt();
        scanner.nextLine();

        return id;
    }

}
