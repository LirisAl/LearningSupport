import java.util.Scanner;

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
                case "2" -> System.out.println("Ещё не придумала что тут будет. Выбери другой пункт.\n");
                case "3" -> System.out.println("Выход из программы. Ты молодец, хорошего дня!");
                default -> System.out.println("Неверный выбор. Попробуй снова.\n");
            }
        } while (!choice.equals("3"));
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("Выбери действие:");
        System.out.println("1. Рандомайзер \"Да/Нет\";");
        System.out.println("2. Еще не придумала что тут будет;");
        //System.out.println();
        System.out.println("3. Выход.");
    }

    private static void randomizer() {
        String[] answers = {"Нет", "Да", "Может быть", "Спроси позже"};
        int randomNumber = (int) (Math.random() * answers.length);
            /*String result = switch (randomNumber) {
                case 0 -> "Нет";
                case 1 -> "Да";
                case 2 -> "Может быть";
                default -> "Ошибка";
            };
            System.out.println("Ответ: " + randomNumber);*/
        System.out.println("Ответ: " + answers[randomNumber] + "\n");
    }
}
