package CRM.CRM.Model;

public class Employee implements Notifiable
{
    private String name;
    private String surname;
    private float commission;

    public Employee(String name, String surname, float comission)
    {
        this.name = name;
        this.surname = surname;
        this.commission = comission;
    }

    public String getName()
    {
        return this.name;
    }

    public String getSurname()
    {
        return this.surname;
    }

    public float getCommission()
    {
        return this.commission;
    }

    @Override
    public void notifyObserver(Deal deal, String newStatus) {
        System.out.println("Работник " + this.name + " " + this.surname + " уведомлен, что статус сделки " + deal.getId() + " изменен на " + newStatus);
    }
}
