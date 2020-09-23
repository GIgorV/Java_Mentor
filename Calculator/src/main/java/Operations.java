import java.util.Arrays;

public class Operations {
    private final int[] value = new int[2];
    private String operation;
    private String[] expressions;
    int resultInt;
    String[] testOfReiteration = new String[2];

    public void result(String messageFromConsole) {
        int codeOfAscii;
        for (int i = 0; i < messageFromConsole.length(); i++) {
            codeOfAscii = messageFromConsole.codePointAt(i);
            if (codeOfAscii == 42) {
                expressions = messageFromConsole.split("[*]", 2);
                operation = "multiplication";
            }
            if (codeOfAscii == 43) {
                expressions = messageFromConsole.split("[+]", 2);
                operation = "addition";
            }
            if (codeOfAscii == 45) {
                expressions = messageFromConsole.split("[-]", 2);
                operation = "subtraction";
            }
            if (codeOfAscii == 47) {
                expressions = messageFromConsole.split("[/]", 2);
                operation = "division";
            }
        }
        try {
            for (int i = 0; i < expressions.length; i++) {
                codeOfAscii = expressions[i].charAt(0);
                if (codeOfAscii >= 48 & codeOfAscii <= 57) {
                    value[i] = Integer.parseInt(expressions[i]);
                    testOfReiteration[i] = "int";
                }
                if (codeOfAscii >= 65 & codeOfAscii <= 122) {
                    Transformations transformations = new Transformations();
                    value[i] = transformations.result(expressions[i]);
                    testOfReiteration[i] = "roman";
                }
            }
        } catch (StringIndexOutOfBoundsException | NullPointerException e) {
            System.out.println("Не достаточно данных для расчета или вы остановили выполнение программы");
            System.exit(0);
        }
        if (testOfReiteration[0].equals(testOfReiteration[1])) {
            calculation(operation);
        } else throw new IllegalArgumentException("Введенные данные имеют разный тип цифр");

        codeOfAscii = expressions[0].charAt(0);
        if (codeOfAscii >= 48 && codeOfAscii <= 57) {
            System.out.println(resultInt);
        } else {
            System.out.println(Transformations.convertIntegerToRoman(resultInt));
        }
        Arrays.fill(value, 0);
        Arrays.fill(expressions, "");
    }

    void calculation(String operation) {
        if (operation.equals("addition")) {
            resultInt = value[0] + value[1];
        }
        if (operation.equals("subtraction")) {
            resultInt = value[0] - value[1];
        }
        if (operation.equals("multiplication")) {
            resultInt = value[0] * value[1];
        }
        if (operation.equals("division")) {
            if (value[1] != 0) {
                resultInt = value[0] / value[1];
            } else {
                System.err.println("Деление на ноль");
            }
        }
    }
}

