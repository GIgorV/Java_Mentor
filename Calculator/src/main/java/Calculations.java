import lombok.*;

@Builder
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Calculations {
    private int value1;
    private int value2;
    private int counter = 0;
    private int codeOfAscii;
    private String operation;
    private String value = "";
    boolean valueIsInt;

    public void result(char[] chars) {
        valueIsInt = false;
        for (int i = 0; i < chars.length; i++) {
            codeOfAscii = chars[i];
            if (codeOfAscii >= 48 && codeOfAscii <= 57) {
                value += Character.getNumericValue(chars[i]);
                valueIsInt = value.matches("[-+]?\\d+");
            } else {
                if (codeOfAscii == 43) {
                    operation = "addition";
                }
                if (codeOfAscii == 45) {
                    operation = "subtraction";
                }
                if (codeOfAscii == 42) {
                    operation = "multiplication";
                }
                if (codeOfAscii == 47) {
                    operation = "division";
                }
                if (counter == 0) {
                    if (valueIsInt) {
                        value1 = Integer.parseInt(value);
                        value = "";
                    } else {
                        System.out.println("Не достаточно данных для расчета или вы остановили выполнение программы");
                        System.exit(0);
                    }
                }
                counter++;
            }
            if (counter != 0 && !value.equals("")) {
                if (valueIsInt) {
                    value2 = Integer.parseInt(value);
                    value = "";
                } else {
                    System.out.println("Не достаточно данных для расчета или вы остановили выполнение программы");
                    System.exit(0);
                }
            }
        }
        if (operation.equals("addition")) {
            System.out.println(value1 + value2);
        }
        if (operation.equals("subtraction")) {
            System.out.println(value1 - value2);
        }
        if (operation.equals("multiplication")) {
            System.out.println(value1 * value2);
        }
        if (operation.equals("division")) {
            if (value2 != 0) {
                System.out.println(value1 / value2);
            } else {
                System.err.println("Деление на ноль");
            }
        }
        value1 = 0;
        value2 = 0;
        counter = 0;
    }
}

//private void transformations

