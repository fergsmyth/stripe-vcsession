package com.ferg.stripe.vcsession;

import java.util.*;

public class SimpleApp {

    public Map<String, Integer> minByColumn(List<Map<String, Integer>> databaseTable, String column) {
        Integer minValue = null;
        Map<String, Integer> rowContainMinValue = null;
        for (Map<String, Integer> row : databaseTable) {
            Integer value = row.get(column);
            if (value != null) {
                if (minValue == null || minValue > value) {
                    minValue = value;
                    rowContainMinValue = row;
                }
            } else if(rowContainMinValue == null){
                rowContainMinValue = row;
            }
        }
        return rowContainMinValue;
    }

    public Map<String, Integer> orderByColumn(List<Map<String, Integer>> databaseTable, List<String> columns) {
        Integer minValue = null;
        Map<String, Integer> rowContainMinValue = null;
        for (Map<String, Integer> row : databaseTable) {
            Iterator<String> iterator = columns.iterator();
            Integer value = row.get(iterator.next());
            if (value != null) {
                if (minValue == null || minValue > value) {
                    minValue = value;
                    rowContainMinValue = row;
                } else if(minValue.equals(value)){
                    while (iterator.hasNext()){
                        String nextColumn = iterator.next();
                        Integer nextMinValue = row.get(nextColumn);
                        Integer curMinValue = rowContainMinValue.get(nextColumn);
                        if (nextMinValue != null && nextMinValue < curMinValue){
                            rowContainMinValue = row;
                            break;
                        }
                    }
                }
            } else if(rowContainMinValue == null){
                rowContainMinValue = row;
            }
        }

        return rowContainMinValue;
    }
}
