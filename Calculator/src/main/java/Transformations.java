import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Builder
@ToString
@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor

public class Transformations {
    private int value1;
    private int value2;
    private int counter = 0;
    private int codeOfAscii;
    private String operation;
    private String roman = "";

    Map<String, Integer> v = new HashMap<>();

    public Transformations() {
        v.put("IV", 4);
        v.put("IX", 9);
        v.put("XL", 40);
        v.put("CD", 400);
        v.put("CM", 900);
        v.put("C", 100);
        v.put("M", 1000);
        v.put("I", 1);
        v.put("V", 5);
        v.put("X", 10);
        v.put("L", 50);
        v.put("D", 500);
    }

    int result = 0;

    public int convertFromRoman(char[] chars) {
        for (int i = 0; i < chars.length; i++) {
            codeOfAscii = chars[i];
//            System.out.print(chars[i]);
            if (codeOfAscii == 73 || codeOfAscii == 86 || codeOfAscii == 88 || codeOfAscii == 76
                    || codeOfAscii == 67 || codeOfAscii == 68 || codeOfAscii == 77) {
                roman += (char) (chars[i]);
            } else {
                break;
            }
        }
        for (String s : v.keySet()) {
            result += countOccurrences(roman, s) * v.get(s);
            roman = roman.replaceAll(s, "");
        }
//        System.out.println(result);
        return result;
    }

    public static int countOccurrences(String main, String sub) {
        return (main.length() - main.replace(sub, "").length()) / sub.length();
    }

}
