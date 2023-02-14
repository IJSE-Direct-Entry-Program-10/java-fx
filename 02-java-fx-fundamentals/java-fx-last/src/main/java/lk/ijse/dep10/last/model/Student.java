package lk.ijse.dep10.last.model;

public class Student {
    public String id;
    public String name;
    public String address;

    public Student(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format("%-5s\t%-15s\t%-20s", id, name, address);
    }
}
