import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        System.out.println("Введите выражение для вычисления, для выхода '-1'");
        Scanner scanner = new Scanner(System.in);
//        char[] messageFromConsole = scanner.nextLine().toCharArray();
        String messageFromConsole = scanner.nextLine();
        Calculations calculations = new Calculations();
        while (true) {
            calculations.result(messageFromConsole);
            System.out.println("Введите выражение для вычисления, для выхода '-1'");
//            messageFromConsole = scanner.nextLine().toCharArray();
            messageFromConsole = scanner.nextLine();
        }
    }
}
