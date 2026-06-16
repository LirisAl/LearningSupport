import java.util.Scanner;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Support {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String choice;
        System.out.println("Привет, ты со всем справишься!");

        do {
            printMenu();
            choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> randomizer();
                case "2" -> showDayInfo();
                //case "3" -> weather();
                case "4" -> System.out.println("Ещё не придумала что тут будет. Выбери другой пункт.\n");
                case "5" -> System.out.println("Выход из программы. Ты молодец, хорошего дня!");
                default -> System.out.println("Неверный выбор. Попробуй снова.\n");
            }
        } while (!choice.equals("5"));
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("Выбери действие:");
        System.out.println("1. Рандомайзер \"Да/Нет\";");
        System.out.println("2. День недели;");
        System.out.println("3. Погода в моем регионе;");
        System.out.println("4. Еще не придумала что тут будет;");
        System.out.println("5. Выход.");
    }

    private static void randomizer() {
        String[] answers = {"Нет", "Да", "Может быть", "Спроси позже"};
        int randomNumber = (int) (Math.random() * answers.length);

        System.out.println("Ответ: " + answers[randomNumber] + "\n");
    }
    private static void showDayInfo() {
        ZonedDateTime now = java.time.ZonedDateTime.now();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm:ss (z)");
        String formatedDayAndTime = now.format(dateFormatter);

        System.out.println("Сегодня " + formatedDayAndTime);
    }
}
