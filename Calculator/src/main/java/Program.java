import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        System.out.println("Введите выражение для вычисления, для выхода '0'");
        Scanner scanner = new Scanner(System.in);
        String messageFromConsole = scanner.nextLine();
        Operations operations = new Operations();
        while (true) {
            operations.result(messageFromConsole);
            System.out.println("Введите выражение для вычисления, для выхода '0'");
            messageFromConsole = scanner.nextLine();
        }
    }
}
