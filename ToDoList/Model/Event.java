package ToDoList.Model;

public class Event
{
    private String eventMessage;
    private boolean active;


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

    public void makeDone()
    {
        this.active = false;
    }
}