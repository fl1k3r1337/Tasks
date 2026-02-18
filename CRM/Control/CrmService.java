package CRM.CRM.Model.Control;

import CRM.CRM.Model.Client;
import CRM.CRM.Model.Deal;
import CRM.CRM.Model.Employee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;


public class CrmService {

    private ArrayList<Deal> deals;
    private Map<String, Integer> statuses;

    public CrmService()
    {
        this.deals = new ArrayList<>();
        this.statuses = new HashMap<>();

        statuses.put("PENDING", 1);
        statuses.put("CLOSED", 2);
        statuses.put("CANCELED", 3);
    }

    public void addDeal(Deal deal)
    {
        // добавить сделку в список
        this.deals.add(deal);
    }

    public void sortByStatus()
    {
        // отсортировать сделки по статусу

        this.deals.sort(Comparator.comparing(aDeal -> this.statuses.get(aDeal.getStatus())));
        System.out.println("Сделки отсортированы по статусам");
    }

    public float calculateCommission(Deal deal)
    {
        // принимает сделку и считает комиссию (сумма * ставка сотрудника)

        return deal.getCommission();
    }

    public void changeStatus(Deal deal, String status)
    {
        // меняет статус конкретной сделки (и через нее триггерится уведомление)
        deal.changeStatus(status);
        deal.getClient().notifyObserver(deal, status);
        deal.getEmployee().notifyObserver(deal, status);
    }

    public void printAllDeals()
    {
        for (Deal deal : deals)
        {
            System.out.println("Сделка " + deal.getId() + ". Работник: " + deal.getEmployee().getName() + " " +
                    deal.getEmployee().getSurname() + ". Клиент: " + deal.getClient().getName() + " " +
                    deal.getClient().getSurname() + ". Сумма: " + deal.getValue() + ". Статус: " +
                    deal.getStatus());
        }
    }


    public static void main(String[] args)
    {
        Client client1 = new Client("Иван", "Иванов");
        Employee employee1 = new Employee("Сергей", "Сидоров", 0.2f);
        Deal deal1 = new Deal(client1, employee1, 100000, 124);

        Client client2 = new Client("Артем", "Вознесенский");
        Employee employee2 = new Employee("Олег", "Нечипоренко", 0.3f);
        Deal deal2 = new Deal(client2, employee2, 11500, 125);

        CrmService CRM = new CrmService();
        CRM.addDeal(deal1);
        CRM.addDeal(deal2);

        System.out.println(CRM.calculateCommission(CRM.deals.get(0)));
        CRM.changeStatus(CRM.deals.get(0), "CANCELED");
        CRM.changeStatus(CRM.deals.get(0), "PENDING");
        CRM.changeStatus(CRM.deals.get(0), "CLOSED");

        CRM.printAllDeals();

        CRM.sortByStatus();

        CRM.printAllDeals();
    }
}
