package ToDoList.View;

import ToDoList.Control.UserControl;
import ToDoList.Model.Event;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleView
{
    public static void main(String[] args)
    {
        UserControl control = new UserControl();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running)
        {
            printMenu();

            String input = scanner.nextLine().trim();
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
                        printEvents(control.getEvents(), scanner);
                        break;

                    case 2:
                        addEvent(control, scanner);
                        break;

                    case 3:
                        deleteEvent(control, scanner);
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

        scanner.close();
    }

    public static void printMenu()
    {
        System.out.println("TODO List\n" +
                "1. Показать текущие задачи\n" +
                "2. Добавить задачу\n" +
                "3. Удалить задачу\n" +
                "4. Отметить задачу как выполненную\n" +
                "5. Показать активные задачи\n" +
                "6. Показать выполненные задачи\n" +
                "0. Выход\n" +
                "Выберите необходимую функцию");
    }

    public static void printEvents(ArrayList<Event> events, Scanner scanner)
    {
        if (events.isEmpty())
            System.out.println("Список пуст");
        else
        {
            System.out.println("Задачи:");
            for (int i = 0; i < events.size(); i++)
            {
                Event event = events.get(i);
                System.out.println(i + 1 + ". " + event.getMessage());
            }
        }
        waitForEnter(scanner);
    }

    public static void addEvent(UserControl control, Scanner scanner)
    {
        System.out.println("Введите текст задачи");
        String eventMessage = scanner.nextLine();

        control.addEvent(eventMessage);
    }

    public static void deleteEvent(UserControl control, Scanner scanner)
    {
        System.out.println("Введите номер задачи для удаления");
        int index = scanner.nextInt() - 1;
        control.removeEvent(index);
    }

    private static void waitForEnter(Scanner scanner) {
        System.out.print("\nНажмите Enter для продолжения...");
        scanner.nextLine();
    }
}