package ca.jrvs.practice.dataStructure.map;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class testMap {

  public static void main(String[] args) {
    Map<Employee, List<String>> empMap = new HashMap<Employee, List<String>>();
    List<String> prevCompanies = Arrays.asList("1", "2", "3");
    Employee Bob = new Employee(1, "Bob", 41, 5000);
    empMap.put(Bob, prevCompanies);
    System.out.println(Bob.hashCode());
  }

}
