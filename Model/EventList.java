package ToDoList.Model;

import java.util.ArrayList;

public class EventList
{
    private final ArrayList<Event> eventList;

    public EventList()
    {
        this.eventList = new ArrayList<>();
    }

    public ArrayList<Event> getEventList()
    {
        return eventList;
    }

    public void addEvent(Event event)
    {
        eventList.add(event);
    }

    public boolean removeEvent(int index)
    {
        if (index >= 0 && index <= eventList.size())
        {
            eventList.remove(index);
            return true;
        }
        return false;
    }



}