import lombok.*;

@Builder
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Calculations {
    private int[] value = new int[2];
    //    private int value2;
//    private int counter = 0;
    private int codeOfAscii;
    private String operation;
    //    private StringBuilder expression;
    private String[] expressions;
    private String expression;
    boolean valueIsInt;

    public void result(String messageFromConsole) {
        valueIsInt = false;
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
//        for (String expression : expressions) {
//            System.out.print(expression + " ");
//        }

        for (int i = 0; i < expressions.length; i++) {
            try{
            codeOfAscii = expressions[i].charAt(0);
            if (codeOfAscii >= 48 && codeOfAscii <= 57) {
                value[i] = Integer.parseInt(expressions[i]);
            }
        }catch (StringIndexOutOfBoundsException e){
                System.out.println("Не достаточно данных для расчета или вы остановили выполнение программы");
                System.exit(0);
            }
        }

        if (operation.equals("addition")) {
            System.out.println(value[0] + value[1]);
        }
        if (operation.equals("subtraction")) {
            System.out.println(value[0] - value[1]);
        }
        if (operation.equals("multiplication")) {
            System.out.println(value[0] * value[1]);
        }
        if (operation.equals("division")) {
            if (value[1] != 0) {
                System.out.println(value[0] / value[1]);
            } else {
                System.err.println("Деление на ноль");
            }
        }

        value[0] = 0;
        value[1] = 0;
//        counter = 0;
    }
}
//            while (codeOfAscii != 42 || codeOfAscii != 43 || codeOfAscii != 45 || codeOfAscii != 47 || codeOfAscii != 32) {
//                if (codeOfAscii >= 65 && codeOfAscii <= 122) {
//                    expressions.append(messageFromConsole.charAt(i));
//                }
//                Transformations transformations = new Transformations();
//                if (counter == 0) {
//                    value1 = transformations.convertFromRoman(chars);
////                    break;
//                } else {
//                    value2 = transformations.convertFromRoman(chars);
////                    break;
//                }
//            } else{
//                if (codeOfAscii >= 48 && codeOfAscii <= 57) {
//                    value += Character.getNumericValue(chars[i]);
//                    valueIsInt = value.matches("[-+]?\\d+");
//                } else {
//                    counter++;
//                }
//                if (counter == 0) {
//                    if (valueIsInt) {
//                        value1 = Integer.parseInt(value);
//                        value = "";
//                    } else {
//                        System.out.println("Не достаточно данных для расчета или вы остановили выполнение программы");
//                        System.exit(0);
//                    }
//                }
//                if (counter != 0 && !value.equals("")) {
//                    if (valueIsInt) {
//                        value2 = Integer.parseInt(value);
//                        value = "";
//                    } else {
//                        System.out.println("Не достаточно данных для расчета или вы остановили выполнение программы");
//                        System.exit(0);
//                    }
//                }
//            }
//
//        }


