package ca.jrvs.practice.codingChallenge;

import java.util.Map;



public class compareMap {

    /***
     *
     * This approach uses the equals method in the appropriate map
     * implementation. e.g. if we are using the hashmap implementation
     * hashmap inherits the abstractMap equal method. Each of the entry in
     * hashmap is a map.entry object, the implementation of that entry object
     * is a nested class in hashmap. the abstarctmap equals call the equals method
     * of map.entry. each of the subclasses of object have a overriden equals method
     * and that is responsible to check the value.
     *
     * Complexity: it will check all the entry of map both key and value. if suppose,
     * the maximum total entry in both of the maps in n. then complexity will be
     * O(n). checking each of the key and value is a constant time operation.
     */
    public static boolean compareMapRunner(Map<?, ? extends Object> m1,
                                           Map<?, ? extends Object> m2 ){
        return m1.equals(m2);
    };

    public static boolean compareMapRunnerWithHashJMap(Map<?, ? extends Object> m1,
                                           Map<?, ? extends Object> m2 ){
        return m1.equals(m2);
    }
    public static boolean compareMapRunnerWithStream(Map<?, ? extends Object> m1,
                                                       Map<?, ? extends Object> m2 ){
        return m1.entrySet().stream().allMatch(k->m1.get(k).equals(m2.get(k)));
    }

}
