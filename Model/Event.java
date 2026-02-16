package ToDoList.Model;

public class Event
{
    String eventMessage;
    boolean active;

    public Event(String eventMessage)
    {
        this.eventMessage = eventMessage;
        this.active = true;
    }

    public String getMessage()
    {
        return eventMessage;
    }

    public boolean isActive()
    {
        return active;
    }
}