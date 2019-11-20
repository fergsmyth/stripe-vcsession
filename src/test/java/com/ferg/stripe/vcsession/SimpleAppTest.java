package com.ferg.stripe.vcsession;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.hamcrest.collection.IsMapContaining;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleAppTest {

    @Test
    public void minByColumnTestCase1(){
        SimpleApp simpleApp = new SimpleApp();
        List<Map<String, Integer>> table = Arrays.asList(
                Map.of("a", 1, "b", 3),
                Map.of("a", 3, "b", 0)
        );
        Map<String, Integer> minValueRow = simpleApp.minByColumn(table, "b");
        MatcherAssert.assertThat(minValueRow, IsMapContaining.hasEntry("a", 3));
        MatcherAssert.assertThat(minValueRow, IsMapContaining.hasEntry("b", 0));
    }

    @Test
    public void minByColumnTestCase2(){
        SimpleApp simpleApp = new SimpleApp();
        List<Map<String, Integer>> table = Arrays.asList(
                Map.of("a", 1),
                Map.of("a", 1),
                Map.of("a", 1)
        );
        Map<String, Integer> minValueRow = simpleApp.minByColumn(table, "a");
        MatcherAssert.assertThat(minValueRow, IsMapContaining.hasEntry("a", 1));
    }

    @Test
    public void minByColumnTestCase3(){
        SimpleApp simpleApp = new SimpleApp();
        List<Map<String, Integer>> table = Arrays.asList(
                Map.of("a", 1, "b", -2),
                Map.of("a", 3)
        );
        Map<String, Integer> minValueRow = simpleApp.minByColumn(table, "b");
        MatcherAssert.assertThat(minValueRow, IsMapContaining.hasEntry("a", 1));
        MatcherAssert.assertThat(minValueRow, IsMapContaining.hasEntry("b", -2));
    }

    @Test
    public void orderByColumnTestCase1(){
        SimpleApp simpleApp = new SimpleApp();
        List<Map<String, Integer>> table = Arrays.asList(
                Map.of("x", 1, "y", 3),
                Map.of("x", 1, "y", 0)
        );
        Map<String, Integer> minValueRow = simpleApp.orderByColumn(table, Arrays.asList("x", "y"));
        MatcherAssert.assertThat(minValueRow, IsMapContaining.hasEntry("x", 1));
        MatcherAssert.assertThat(minValueRow, IsMapContaining.hasEntry("y", 0));
    }

    @Test
    public void orderByColumnTestCase2(){
        SimpleApp simpleApp = new SimpleApp();
        List<Map<String, Integer>> table = Arrays.asList(
                Map.of("x", 3, "y", -1, "z", 0),
                Map.of("x", 1, "y", 10, "z", 1),
                Map.of("x", 1, "y", 10, "z", 0)
        );
        Map<String, Integer> minValueRow = simpleApp.orderByColumn(table, Arrays.asList("x", "y", "z"));
        MatcherAssert.assertThat(minValueRow, IsMapContaining.hasEntry("x", 1));
        MatcherAssert.assertThat(minValueRow, IsMapContaining.hasEntry("y", 10));
        MatcherAssert.assertThat(minValueRow, IsMapContaining.hasEntry("z", 0));
    }

    @Test
    public void orderByColumnTestCase3(){
        SimpleApp simpleApp = new SimpleApp();
        List<Map<String, Integer>> table = Arrays.asList(
                Map.of("x", 1, "y", 10, "z", 1),
                Map.of("x", 1, "y", 10, "z", 0)
        );
        Map<String, Integer> minValueRow = simpleApp.orderByColumn(table, Arrays.asList("x", "k"));
        MatcherAssert.assertThat(minValueRow, IsMapContaining.hasEntry("x", 1));
        MatcherAssert.assertThat(minValueRow, IsMapContaining.hasEntry("y", 10));
        MatcherAssert.assertThat(minValueRow, IsMapContaining.hasEntry("z", 1));
    }

    @Test
    public void orderByColumnTestCase4(){
        SimpleApp simpleApp = new SimpleApp();
        List<Map<String, Integer>> table = Arrays.asList(
                Map.of("x", 1000, "y", 1),
                Map.of("x", 1000, "y", 0)
        );
        Map<String, Integer> minValueRow = simpleApp.orderByColumn(table, Arrays.asList("x", "y"));
        MatcherAssert.assertThat(minValueRow, IsMapContaining.hasEntry("x", 1000));
        MatcherAssert.assertThat(minValueRow, IsMapContaining.hasEntry("y", 0));
    }


}
