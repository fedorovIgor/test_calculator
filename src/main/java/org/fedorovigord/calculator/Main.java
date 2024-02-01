package org.fedorovigord.calculator;

import org.fedorovigord.calculator.NumberModel;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        for (;;) {
            Scanner in = new Scanner(System.in);
            System.out.println("Введите выражение:");
            var res = calc(in.nextLine());
            System.out.println("Ответ: " + res);
        }
    }

    public static String calc(String input) {
        if (input == null)
            throw new IllegalArgumentException("input string is null");

        String[] parts = input.split(" ");

        if (parts.length != 3) {
            throw new IllegalArgumentException("input is incorrect, should be 3 arguments divided by space, given " + parts.length);
        }

        if (parts[0].isBlank() || parts[1].isBlank() || parts[2].isBlank()) {
            throw new IllegalArgumentException("given numbers are empty");
        }

        if (!isOperator(parts[1])) {
            throw new IllegalArgumentException("Operator incorrect, should be + - / *");
        }

        var firstNumber = new NumberModel(parts[0]);
        var secondNumber = new NumberModel(parts[2]);

        if (!firstNumber.isCorrect() || !secondNumber.isCorrect()) {
            throw new IllegalArgumentException("arguments is incorrect: \n" + firstNumber + "\n" + secondNumber);
        }

        if (!((firstNumber.isArabic() && secondNumber.isArabic()) || (!firstNumber.isArabic() && !secondNumber.isArabic()))) {
            throw new IllegalArgumentException("both arguments should be Arabic or Roman: \n" + firstNumber + "\n" + secondNumber);
        }

        if (firstNumber.getValue() > 10 || firstNumber.getValue() < 1) {
            throw new IllegalArgumentException("need to be between 1 and 10: " + firstNumber);
        }

        if (secondNumber.getValue() > 10 || secondNumber.getValue() < 1) {
            throw new IllegalArgumentException("need to be between 1 and 10: " + secondNumber);
        }

        int result = calculate(firstNumber.getValue(), secondNumber.getValue(), parts[1]);

        String answer = String.valueOf(result);

        if (!firstNumber.isArabic()) {
            if (result < 1) {
                throw new RuntimeException("in roman result should be more than 0, result: " + result);
            }
            answer = convertToRoman(result);
        }

        return answer;
    }

    static String convertToRoman(int num) {
        String[] romanSymbols = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C"};
        int[] arabicValues = {1, 4, 5, 9, 10, 40, 50, 90, 100};

        StringBuilder romanNumber = new StringBuilder();

        int i = 8;

        while (num > 0) {
            int div = num / arabicValues[i];
            num %= arabicValues[i];
            while (div-- > 0) {
                romanNumber.append(romanSymbols[i]);
            }
            i--;
        }

        return romanNumber.toString();
    }

    static int calculate(int firstNumber, int secondNumber, String operator) {

        int result = 0;

        if (operator.equals("+"))
            result = firstNumber + secondNumber;
        if (operator.equals("-"))
            result = firstNumber - secondNumber;
        if (operator.equals("*"))
            result = firstNumber * secondNumber;
        if (operator.equals("/"))
            result = firstNumber / secondNumber;

        return result;
    }

    static boolean isOperator(String input) {
        return input.equals("+") || input.equals("-") || input.equals("*") || input.equals("/");
    }


}