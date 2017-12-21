package pl.edu.agh.ki.bd2.model;

import java.util.HashSet;
import java.util.Set;

public class Student {
    private String studentName;
    private String studentSurname;
    private String pesel;
    private Set<Student> friends = new HashSet<>();
    private Set<Subject> subjectSet = new HashSet<>();

    public Student(String studentName, String studentSurname, String pesel) {
        this.studentName = studentName;
        this.studentSurname = studentSurname;
        this.pesel = pesel;
    }

    public Set<Subject> getSubjectSet() {
        return subjectSet;
    }

    public void setSubjectSet(Set<Subject> subjectSet) {
        this.subjectSet = subjectSet;
    }


    public String getStudentSurname() {
        return studentSurname;
    }

    public void setStudentSurname(String studentSurname) {
        this.studentSurname = studentSurname;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public Set<Student> getFriends() {
        return friends;
    }

    public void setFriends(Set<Student> friends) {
        this.friends = friends;
    }
}
