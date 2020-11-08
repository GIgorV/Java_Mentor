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
/*
Кроме того, использование byte в неправильном месте может привести к неловким моментам:

public static void main(String []args){
        for (byte i = 1; i <= 200; i++) {
            System.out.println(i);
        }
}
Тут будет зацикливание. Потому что значение счётчика дойдёт до максимума (127),
произойдёт переполнение и значение станет -128. И мы никогда не выйдем из цикла.
 */
