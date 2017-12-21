package pl.edu.agh.ki.bd2.model;

public class Teacher {
    private String name;

    public Teacher(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Subject getTeaching() {
        return teaching;
    }

    public void setTeaching(Subject teaching) {
        this.teaching = teaching;
    }

    private String surname;
    private Subject teaching;

}
