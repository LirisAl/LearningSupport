import java.util.Scanner;

public class Support {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        System.out.println("Привет, ты со всем справишься!");
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> Randomizer();
                //case "2" ->
                case "3" -> {
                    System.out.println("Выход из программы. Ты молодец, хорошего дня!");
                    running = false;
                }
                default -> System.out.println("Неверный выбор. Попробуй снова.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("Выбери действие:");
        System.out.println("1. Рандомайзер \"Да/Нет\";");
        System.out.println("2. Еще не придумала что тут будет");
        //System.out.println();
        System.out.println("3. Выход");
    }
    private static void Randomizer() {
        String[] answers = {"Нет", "Да", "Может быть"};
        int randomNumber = (int) (Math.random() * 3);

            /*String result = switch (randomNumber) {
                case 0 -> "Нет";
                case 1 -> "Да";
                case 2 -> "Может быть";
            };
            System.out.println("Ответ: " + randomNumber);*/
        System.out.println("Ответ: " + answers[randomNumber]);

    }
}
