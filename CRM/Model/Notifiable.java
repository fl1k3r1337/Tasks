package CRM.CRM.Model;

interface Notifiable
{
    void notifyObserver(Deal deal, String newStatus);
}
