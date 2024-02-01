package org.fedorovigord.calculator;

import java.util.HashMap;
import java.util.Map;

class NumberModel {

    private String inputValue;
    private int value;
    private boolean isArabic = true;
    private boolean isCorrect = true;
    private Map<String, Integer> romanMap = getRomanMap();

    NumberModel(String inputValue) {
        this.inputValue = inputValue;

        if (romanMap.containsKey(inputValue)) {
            this.isArabic = false;
            this.value = romanMap.get(inputValue);
        }
        else if (inputValue.matches("-?\\d+")) {
            this.isArabic = true;
            try {
                value = Integer.parseInt(inputValue);
            } catch (Exception e) {
                throw e;
            }
        }
        else
            this.isCorrect = false;
    }

    private Map<String, Integer> getRomanMap() {
        var romanMap = new HashMap<String, Integer>();

        romanMap.put("I", 1);
        romanMap.put("II", 2);
        romanMap.put("III", 3);
        romanMap.put("IV", 4);
        romanMap.put("V", 5);
        romanMap.put("VI", 6);
        romanMap.put("VII", 7);
        romanMap.put("VIII", 8);
        romanMap.put("IX", 9);
        romanMap.put("X", 10);

        return romanMap;
    }

    public String getInputValue() {
        return inputValue;
    }

    public int getValue() {
        return value;
    }

    public boolean isArabic() {
        return isArabic;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    @Override
    public String toString() {
        return "NumberModel{" +
                "inputValue='" + inputValue + '\'' +
                ", value=" + value +
                ", isArabic=" + isArabic +
                ", isCorrect=" + isCorrect +
                '}';
    }
}
