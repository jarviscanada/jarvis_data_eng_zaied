package ca.jrvs.practice.dataStructure.map;

import java.util.Objects;

public class Employee {

  private int id;
  private String name;
  private int age;
  private long salary;

  public Employee() {

  }

  ;

  public Employee(int id, String name, int age, long salary) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.salary = salary;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Employee employee = (Employee) o;
    return id == employee.id &&
        age == employee.age &&
        salary == employee.salary &&
        Objects.equals(name, employee.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, age, salary);
  }
}
