package ToDoList.Control;

import ToDoList.Model.Event;
import ToDoList.Model.EventList;

import java.util.ArrayList;

public class UserControl {
    private final EventList eventList;

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

//    public static void parseUserMessage(int userMessage)
//    {
//        EventList eventList = new EventList();
//        ArrayList<Event> list = eventList.getEventList();
//        switch (userMessage)
//        {
//            case 0:
//                System.exit(0);
//                break;
//
//            case 1:
//                printEvents(list);
//                break;
//
//            case 2:
//                addEvent(list);
//                break;
//
//            case 3:
//
//                break;
//
//            case 4:
//
//                break;
//
//            case 5:
//
//                break;
//
//            case 6:
//
//                break;
//
//            default:
//                System.out.println("Введена некорректная команда");
//                break;
//        }
//  }
}

