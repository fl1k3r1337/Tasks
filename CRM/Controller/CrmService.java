package CRM.Controller;

import CRM.Model.Client;
import CRM.Model.Deal;
import CRM.Model.Employee;

import CRM.View.ConsoleManager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;


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

    public void calculateCommission()
    {
        // принимает сделку и считает комиссию (сумма * ставка сотрудника)
        int id = console.getDealIdFromUser();

        Deal deal = findDealById(id);
        if (deal == null)
        {
            console.printError("Сделка с ID " + id + " не найдена");
            return;
        }

        console.printSuccess(String.valueOf(deal.getCommission()));
    }

    private Deal findDealById(int id) {
        for (Deal deal : deals) {
            if (deal.getId() == id) {
                return deal;
            }
        }
        return null;
    }

    public void changeStatus()
    {
        // меняет статус конкретной сделки (и через нее триггерится уведомление)
        console.requireInfo("Введите id сделки:");
        int id = this.console.getIdFromUser();

        Deal deal = findDealById(id);
        if (deal == null)
        {
            console.printError("Сделка с ID " + id + " не найдена");
            return;
        }

        console.requireInfo("Введите новый статус сделки " + id + " (Выберите из PENDING, CLOSED, CANCELED):");
        String status = console.getStringFromUser();
        if (!statuses.containsKey(status))
        {
            console.printError("Недопустимый статус! Доступны: PENDING, CLOSED, CANCELED");
            return;
        }

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

    public void handleUserCommands(int command)
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
                changeStatus();
                break;

            case 5:
                calculateCommission();
                break;

            case 0:
                console.printExitMessage();
                this.running = false;
        }
    }


    public static void main(String[] args)
    {
        CrmService CRM = new CrmService(new ConsoleManager());

        while (CRM.isRunning())
        {
            CRM.console.printMenu();
            int command = CRM.console.getCommandFromUser();
            CRM.handleUserCommands(command);
        }
    }
}
