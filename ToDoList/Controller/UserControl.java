package ToDoList.Controller;

import ToDoList.Model.Event;
import ToDoList.Model.EventList;

import java.util.ArrayList;

public class UserControl {
    private EventList eventList;

    public UserControl() {
        this.eventList = new EventList();
    }

    public ArrayList<Event> getEvents()
    {
        return eventList.getEventList();
    }

    public void addEvent(String message) {
        if (message != null) {
            eventList.addEvent(new Event(message));
        }
    }

    public void removeEvent(int index)
    {
        eventList.removeEvent(index);
    }
}

