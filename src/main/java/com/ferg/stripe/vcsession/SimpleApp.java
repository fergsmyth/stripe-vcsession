package com.ferg.stripe.vcsession;

import java.util.*;

public class SimpleApp {

    public static void main(String[] args) {

        SimpleApp simpleApp = new SimpleApp();

        // First case
        Map<String, Integer> firstCaseFirstRow = new HashMap<>();
        firstCaseFirstRow.put("a", 1);
        firstCaseFirstRow.put("b", 2);
        Map<String, Integer> firstCaseSecondRow = new HashMap<>();
        firstCaseSecondRow.put("a",  3);
        firstCaseSecondRow.put("b", 0);
        List<Map<String, Integer>> firstCase = Arrays.asList(firstCaseFirstRow, firstCaseSecondRow);
        Set<Map.Entry<String, Integer>> minValueRow = simpleApp.minByColumn(firstCase, "b");


        // Second case
        Map<String, Integer> secondCaseFirstRow = new HashMap<>();
        secondCaseFirstRow.put("a", 1);
        Map<String, Integer> secondCaseSecondRow = new HashMap<>();
        secondCaseSecondRow.put("a",  1);
        Map<String, Integer> secondCaseThirdRow = new HashMap<>();
        secondCaseThirdRow.put("a",  1);
        List<Map<String, Integer>> secondCase = Arrays.asList(secondCaseFirstRow, secondCaseSecondRow, secondCaseThirdRow);
        Set<Map.Entry<String, Integer>> secondCaseMinValueRow = simpleApp.minByColumn(secondCase, "a");

        // Third case
        Map<String, Integer> thirdCaseFirstRow = new HashMap<>();
        thirdCaseFirstRow.put("a", 1);
        thirdCaseFirstRow.put("b", -2);
        Map<String, Integer> thirdCaseSecondRow = new HashMap<>();
        thirdCaseSecondRow.put("a", 3);
        List<Map<String, Integer>> thirdCase = Arrays.asList(thirdCaseFirstRow, thirdCaseSecondRow);
        Set<Map.Entry<String, Integer>> thirdCaseMinValueRow = simpleApp.minByColumn(thirdCase, "b");

        // Fourth case

        Map<String, Integer> fourthCaseFirstRow = new HashMap<>();
        fourthCaseFirstRow.put("x", 1);
        fourthCaseFirstRow.put("y", 3);

        Map<String, Integer> fourthCaseSecondRow = new HashMap<>();
        fourthCaseFirstRow.put("x", 1);
        fourthCaseFirstRow.put("y", 0);

        List<Map<String, Integer>> fourthCaseDB = Arrays.asList(fourthCaseFirstRow, fourthCaseSecondRow);
        Set<Map.Entry<String, Integer>> fourthCaseEntry = simpleApp.orderByColumn(fourthCaseDB, Arrays.asList("x", "y"));


        // Fifth Case

        Map<String, Integer> fifthCaseFirstRow = new HashMap<>();
        fourthCaseFirstRow.put("x", 3);
        fourthCaseFirstRow.put("y", -1);
        fourthCaseFirstRow.put("z", 0);

        Map<String, Integer> fifthCaseSecondRow = new HashMap<>();
        fourthCaseFirstRow.put("x", 1);
        fourthCaseFirstRow.put("y", 10);
        fourthCaseFirstRow.put("z", 1);


        Map<String, Integer> fifthCaseThirdRow = new HashMap<>();
        fourthCaseFirstRow.put("x", 1);
        fourthCaseFirstRow.put("y", 10);
        fourthCaseFirstRow.put("z", 0);

        List<Map<String, Integer>> fifthCaseDB = Arrays.asList(fifthCaseFirstRow, fifthCaseSecondRow, fifthCaseThirdRow);
        Set<Map.Entry<String, Integer>> fifthCaseEntry = simpleApp.orderByColumn(fifthCaseDB, Arrays.asList("x", "y", "z"));


        if(minValueRow != null) {
            System.out.println(minValueRow);
            System.out.println(secondCaseMinValueRow);
            System.out.println(thirdCaseMinValueRow);
            System.out.println(fourthCaseEntry);
//            System.out.println(fifthCaseEntry);
        }

    }


    public Set<Map.Entry<String, Integer>> minByColumn(List<Map<String, Integer>> databaseTable, String column) {
        Integer minValue = null;
        Set<Map.Entry<String, Integer>> rowContainMinValue = null;
        for (Map<String, Integer> row : databaseTable) {
            Integer value = row.get(column);
            if (value != null) {
                if (minValue == null || minValue > value) {
                    minValue = value;
                    rowContainMinValue = row.entrySet();
                }
            } else if(rowContainMinValue == null){
                rowContainMinValue = row.entrySet();
            }
        }
        return rowContainMinValue;
    }

    public Set<Map.Entry<String, Integer>> orderByColumn(List<Map<String, Integer>> databaseTable, List<String> columns) {
        Integer minValue = null;
        Map<String, Integer> rowContainMinValue = null;
        for (Map<String, Integer> row : databaseTable) {
            Iterator<String> iterator = columns.iterator();
            Integer value = row.get(iterator.next());
            if (value != null) {
                if (minValue == null || minValue > value) {
                    minValue = value;
                    rowContainMinValue = row;
                } else if(minValue == value){
                    while (iterator.hasNext()){
                        String nextColumn = iterator.next();
                        Integer nextMinValue = row.get(nextColumn);
                        Integer curMinValue = rowContainMinValue.get(nextColumn);
                        if (nextMinValue < curMinValue){
                            rowContainMinValue = row;
                            break;
                        }
                    }
                }
            } else if(rowContainMinValue == null){
                rowContainMinValue = row;
            }
        }
        return rowContainMinValue == null ? null : rowContainMinValue.entrySet();
    }
}
