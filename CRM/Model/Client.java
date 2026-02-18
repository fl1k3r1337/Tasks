package CRM.CRM.Model;

public class Client implements Notifiable
{
    private String name;
    private String surname;


    public Client(String name, String surname)
    {
        this.name = name;
        this.surname = surname;
    }

    public String getName()
    {
        return this.name;
    }

    public String getSurname()
    {
        return this.surname;
    }

    @Override
    public void notifyObserver(Deal deal, String newStatus) {
        System.out.println("Клиент " + this.name + " " + this.surname + " уведомлен, что статус сделки " + deal.getId() + " изменен на " + newStatus);
    }
}
