package CRM.CRM.Model;

public class Deal
{
//    private List<Notifiable> observer;
//    private String status;

    private Client client;
    private Employee employee;
    private int value;
    private String status;
    private int id;

    public Deal(Client client, Employee employee, int value, int id)
    {
        this.client = client;
        this.employee = employee;
        this.value = value;
        this.id = id;
        this.status = "PENDING";
    }

    public float getCommission()
    {
        return this.value * this.employee.getCommission();
    }

    public int getId()
    {
        return this.id;
    }

    public Client getClient()
    {
        return this.client;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public String getStatus()
    {
        return this.status;
    }

    public int getValue()
    {
        return this.value;
    }

    public void changeStatus(String newStatus)
    {
        this.status = newStatus;
    }
}
