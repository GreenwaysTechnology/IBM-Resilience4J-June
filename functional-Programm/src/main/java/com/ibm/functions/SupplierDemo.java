package com.ibm.functions;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

class Employee {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee() {

    }

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

public class SupplierDemo {
    public static void main(String[] args) {
        Supplier supplier = null;
        supplier = new Supplier() {
            @Override
            public Object get() {
                return "Hello";
            }
        };
        System.out.println(supplier.get());
        supplier = () -> "Hello Supplier";
        System.out.println(supplier.get());

        //return object
        Supplier<Employee> employeeSupplier = () -> new Employee(1, "Subramanian");

        System.out.println(employeeSupplier.get().toString());

        //Task create emp list , return via supplier and iterate

        Supplier<List<Employee>> empList = () -> Arrays.asList(new Employee(1, "Subramanian"),
                new Employee(2, "Ram"));

        empList.get().forEach(System.out::println);
        empList.get().forEach(emp -> System.out.println(emp.getId() + " " + emp.getName()));

    }
}
