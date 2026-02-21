package ToDoList.View;

import ToDoList.Controller.UserControl;
import ToDoList.Model.Event;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleView
{
    private Scanner scanner;

    public ConsoleView()
    {
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args)
    {
        UserControl control = new UserControl();
        ConsoleView console = new ConsoleView();
        boolean running = true;

        while (running)
        {
            printMenu();

            String input = console.scanner.nextLine().trim();
            if (input.isEmpty())
                continue;

            try
            {
                int value = Integer.parseInt(input);
                switch (value)
                {
                    case 0:
                        System.out.println("До свидания");
                        running = false;
                        break;

                    case 1:
                        console.printEvents(control.getEvents());
                        break;

                    case 2:
                        console.addEvent(control);
                        break;

                    case 3:
                        console.deleteEvent(control);
                        break;

                    case 4:
                        console.markAsDone(control);
                        break;

                    case 5:
                        console.printAllActiveTasks(control);
                        break;

                    case 6:
                        console.printAllDoneTasks(control);
                        break;

                    default:
                        System.out.println("Ввод некорректен. Введите число от 0 до 6");
                        break;
                }
            }
            catch (NumberFormatException e)
            {
                System.out.println("Введите число от 0 до 6");
            }

        }

        console.scanner.close();
    }

    public static void printMenu()
    {
        System.out.println("\nTODO List\n" +
                "1. Показать текущие задачи\n" +
                "2. Добавить задачу\n" +
                "3. Удалить задачу\n" +
                "4. Отметить задачу как выполненную\n" +
                "5. Показать активные задачи\n" +
                "6. Показать выполненные задачи\n" +
                "0. Выход\n" +
                "Выберите необходимую функцию");
    }

    public void printEvents(ArrayList<Event> events)
    {
        if (events.isEmpty())
            System.out.println("Список пуст");
        else
        {
            System.out.println("Задачи:");
            for (int i = 0; i < events.size(); i++)
            {
                Event event = events.get(i);
                if (event.isActive())
                    System.out.println(i + 1 + ". " + event.getMessage() + ". Статус: активна");
                else
                    System.out.println(i + 1 + ". " + event.getMessage() + ". Статус: выполнена");
            }
        }
        waitForEnter();
    }

    public void addEvent(UserControl control)
    {
        System.out.println("Введите текст задачи");
        String eventMessage = this.scanner.nextLine();

        control.addEvent(eventMessage);
        System.out.println("Задача \"" +  eventMessage + "\" добавлена\n");
    }

//    public void deleteEvent(UserControl control)
//    {
//        System.out.println("Введите номер задачи для удаления");
//        int index = this.scanner.nextInt() - 1;
//        this.scanner.nextLine();
//        control.removeEvent(index);
//    }

    public void deleteEvent(UserControl control)
    {
        System.out.println("Введите номер задачи для удаления");

        try
        {
            int index = this.scanner.nextInt() - 1;
            this.scanner.nextLine();

            String message = control.getMessageByIndex(index);
            if (message != null)
            {
                control.removeEvent(index);
                System.out.println("Задача \"" + message + "\" удалена");
            }
            else
                System.out.println("Ошибка: задача с таким номером не найдена");
        }
        catch (Exception e)
        {
            System.out.println("Ошибка ввода");
            this.scanner.nextLine();
        }


    }

    private void waitForEnter() {
        System.out.print("\nНажмите Enter для продолжения...");
        this.scanner.nextLine();
    }

    public void markAsDone(UserControl control)
    {
        System.out.println("Введите номер задачи, которую вы хотите отметить как выполненную");

        try
        {
            int index = this.scanner.nextInt() - 1;
            this.scanner.nextLine();

            String message = control.getMessageByIndex(index);
            if (message != null)
            {
                control.markEventAsDone(index);
                System.out.println("Задача \"" + message + "\" выполнена");
            }
            else
                System.out.println("Ошибка: задача с таким номером не найдена");
        }
        catch (Exception e)
        {
            System.out.println("Ошибка ввода");
            this.scanner.nextLine();
        }
    }

    public void printAllActiveTasks(UserControl control)
    {
        ArrayList<Event> events = control.getEvents();
        System.out.println("Список активных задач:\n");
        for (int i = 0; i < events.size(); i++)
        {
            Event event = events.get(i);
            if (event.isActive())
            {
                System.out.println(i + 1 + ". " + event.getMessage());
            }
        }

        waitForEnter();
    }

    public void printAllDoneTasks(UserControl control)
    {
        ArrayList<Event> events = control.getEvents();
        System.out.println("Список выполненных задач:\n");
        for (int i = 0; i < events.size(); i++)
        {
            Event event = events.get(i);
            if (!event.isActive())
            {
                System.out.println(i + 1 + ". " + event.getMessage());
            }
        }

        waitForEnter();
    }
}