

public class Transformations {
    private String roman = "";

    private static int letterToNumber(char letter) {
        switch (letter) {
            case 'M':
                return 1000;
            case 'D':
                return 500;
            case 'C':
                return 100;
            case 'L':
                return 50;
            case 'X':
                return 10;
            case 'V':
                return 5;
            case 'I':
                return 1;
            default:
                return 0;
        }
    }

    public static int convertRomanToInt(String romanNumeral) {
        int result = 0;
        String uRoman = romanNumeral.toUpperCase(); //case-insensitive
        for (int i = 0; i < uRoman.length() - 1; i++) {//loop over all but the last character
            if (letterToNumber(uRoman.charAt(i)) < letterToNumber(uRoman.charAt(i + 1))) {
                result -= letterToNumber(uRoman.charAt(i));
            } else {
                result += letterToNumber(uRoman.charAt(i));
            }
        }
        result += letterToNumber(uRoman.charAt(uRoman.length() - 1));
        return result;
    }

    public int result(String messageRoman) {
        for (int i = 0; i < messageRoman.length(); i++) {
            int codeOfAscii = messageRoman.codePointAt(i);
            if (codeOfAscii == 73 || codeOfAscii == 86 || codeOfAscii == 88 || codeOfAscii == 76
                    || codeOfAscii == 67 || codeOfAscii == 68 || codeOfAscii == 77) {
                roman += (char) (messageRoman.codePointAt(i));
            } else {
                break;
            }
        }
        return convertRomanToInt(roman);
    }

    static int[] numbers = {1, 4, 5, 9, 10, 50, 100, 500, 1000};
    static String[] letters = {"I", "IV", "V", "IX", "X", "L", "C", "D", "M"};

    public static String convertIntegerToRoman(int number) {
        String romanValue = "";
        int N = number;
        while (N > 0) {
            for (int i = 0; i < numbers.length; i++) {
                if (N < numbers[i]) {
                    N -= numbers[i - 1];
                    romanValue += letters[i - 1];
                    break;
                }
            }
        }
        return romanValue;
    }
}
//    static int last = 2000;
//    public static int convertRomanToInt(String romanNumeral) throws NumberFormatException {
//
//        int integerValue = 0;
//        for (int i = 0; i < romanNumeral.length(); i++) {
//            char ch = romanNumeral.charAt(i);
//            int number = letterToNumber(ch);
//            if (number == -1) {
//                throw new NumberFormatException("Invalid format");
//            }
//            if (last<number){
//			number-=last;
//			integerValue += number;
//			last = number;}
//        }
//        return integerValue;
//    }

