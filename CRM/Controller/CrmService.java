package CRM.Controller;

import CRM.Model.Client;
import CRM.Model.Deal;
import CRM.Model.Employee;

import CRM.View.ConsoleManager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class CrmService {

    private ArrayList<Deal> deals;
    private Map<String, Integer> statuses;
    private boolean running;
    private int idCounter;
    private ConsoleManager console;

    public CrmService(ConsoleManager console)
    {
        this.deals = new ArrayList<>();
        this.statuses = new HashMap<>();
        this.idCounter = 1;
        this.running = true;
        this.console = console;

        statuses.put("PENDING", 1);
        statuses.put("CLOSED", 2);
        statuses.put("CANCELED", 3);
    }

    public void addDeal()
    {
        // добавить сделку в список
        try
        {
            String[] clientInfo = this.console.getClientInfoFromUser().split(" ");
            Client client = new Client(clientInfo[0], clientInfo[1]);

            String[] employeeInfo = this.console.getEmployeeInfoFromUser().split(" ");
            Employee employee = new Employee(employeeInfo[0], employeeInfo[1], Float.parseFloat(employeeInfo[2]));

            int dealValue = console.getDealValueFromUser();

            Deal deal = new Deal(client, employee, dealValue, this.idCounter);

            this.deals.add(deal);
            this.idCounter++;
            console.printSuccess("Сделка " + deal.getId() + " успешно добавлена");
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            console.printError("Введена некорректная информация");
        }
    }

    public void sortByStatus()
    {
        // отсортировать сделки по статусу

        this.deals.sort(Comparator.comparing(aDeal -> this.statuses.get(aDeal.getStatus())));
        console.printSuccess("Сделки успешно отсортированы по статусам");
    }

    public void calculateCommission(Scanner scanner)
    {
        // принимает сделку и считает комиссию (сумма * ставка сотрудника)
        int id = console.getDealIdFromUser();

        Deal deal = deals.get(id - 1);

        console.printSuccess(String.valueOf(deal.getCommission()));
    }

    public void changeStatus(Scanner scanner)
    {
        // меняет статус конкретной сделки (и через нее триггерится уведомление)
        console.requireInfo("Введите id сделки:");
        int id = this.console.getIdFromUser();

        console.requireInfo("Введите новый статус сделки " + id + " (Выберите из PENDING, CLOSED, CANCELED):");
        String status = console.getStringFromUser();

        Deal deal = deals.get(id - 1);
        //TODO: добавить проверку на корректность id

        if (deal.changeStatus(status))
        {
            deal.getClient().notifyObserver(deal, status);
            deal.getEmployee().notifyObserver(deal, status);
        }
        else
            console.printError("Статус сделки не был изменен");
    }

    public void printAllDeals()
    {
        if (this.deals.isEmpty()) {
            console.printError("Сделок нет");
            return;
        }
        for (Deal deal : this.deals)
        {
            console.printSuccess("Сделка " + deal.getId() + ". Работник: " + deal.getEmployee().getName() + " " +
                    deal.getEmployee().getSurname() + ". Клиент: " + deal.getClient().getName() + " " +
                    deal.getClient().getSurname() + ". Сумма: " + deal.getValue() + ". Статус: " +
                    deal.getStatus());
        }
    }

    public boolean isRunning()
    {
        return this.running;
    }

    public void handleUserCommands(int command, Scanner scanner)
    {
        switch (command)
        {
            case 1:
                addDeal();
                break;

            case 2:
                printAllDeals();
                break;

            case 3:
                sortByStatus();
                break;

            case 4:
                changeStatus(scanner);
                break;

            case 5:
                calculateCommission(scanner);
                break;

            case 0:
                console.printExitMessage();
                this.running = false;
        }
    }

    public static Client getClientInformation(Scanner scanner)
    {
        String data = scanner.nextLine();
        String[] info = data.split(" ");

        return new Client(info[0], info[1]);
    }

    public static Employee getEmployeeInformation(Scanner scanner)
    {
        String data = scanner.nextLine();
        String[] info = data.split(" ");

        return new Employee(info[0], info[1], Float.parseFloat(info[2]));
    }

    public static int getValueInformation(Scanner scanner)
    {
        int value = scanner.nextInt();
        scanner.nextLine(); // убрать \n
        return value;
    }

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        CrmService CRM = new CrmService(new ConsoleManager());



        while (CRM.isRunning())
        {
            CRM.console.printMenu();
            int command = scanner.nextInt();
            scanner.nextLine(); // убрать \n
            CRM.handleUserCommands(command, scanner);
        }





//        Client client1 = new Client("Иван", "Иванов");
//        Employee employee1 = new Employee("Сергей", "Сидоров", 0.2f);
//        Deal deal1 = new Deal(client1, employee1, 100000, 124);
//
//        Client client2 = new Client("Артем", "Вознесенский");
//        Employee employee2 = new Employee("Олег", "Нечипоренко", 0.3f);
//        Deal deal2 = new Deal(client2, employee2, 11500, 125);
//
//        CrmService CRM = new CrmService();
//        CRM.addDeal(deal1);
//        CRM.addDeal(deal2);
//
//        System.out.println(CRM.calculateCommission(CRM.deals.get(0)));
//        CRM.changeStatus(CRM.deals.get(0), "CANCELED");
//        CRM.changeStatus(CRM.deals.get(0), "PENDING");
//        CRM.changeStatus(CRM.deals.get(0), "CLOSED");
//
//        CRM.printAllDeals();
//
//        CRM.sortByStatus();
//
//        CRM.printAllDeals();
    }
}
