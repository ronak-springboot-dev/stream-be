package com.stream.misc;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamOp<T> {


    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8);

        var summerizeValues = summerizeValues(numbers);
        System.out.println("summerizeValues = " + summerizeValues);
    }

    /**
     * Method to partition data using stream
     * return values like booleanListMap = {false=[1, 3, 5, 7], true=[2, 4, 6, 8]}
     */
    public static Map<Boolean, List<Integer>> partitionData(List<Integer> list) {
        return list.stream()
                .collect(Collectors.partitioningBy(l -> l % 2 == 0));

    }

    /**
     * @param list
     * @return element in the collection e.g 8
     */
    public static Long countElements(List<Integer> list) {
//        This can be done in multiple ways
        return list.stream()
//                .collect(Collectors.counting());
                .count();
//                list.size();

    }

    /**
     *
     * @param list
     * @return IntSummaryStatistics e.g IntSummaryStatistics{count=8, sum=36, min=1, average=4.500000, max=8}
     *
     */
    public static IntSummaryStatistics summerizeValues(List<Integer> list) {
        return list.stream()
                .collect(Collectors.summarizingInt(l -> l));
    }

}
