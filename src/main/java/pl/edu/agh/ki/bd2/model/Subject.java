package pl.edu.agh.ki.bd2.model;

import java.util.Date;
import java.util.Set;

public class Subject {
    private String subjectName;
    private Teacher teachedBy;
    private String courseYear;

    public String getCourseYear() {
        return courseYear;
    }

    public void setCourseYear(String courseYear) {
        this.courseYear = courseYear;
    }

    public Subject(String subjectName, String courseYear) {
        this.subjectName = subjectName;
        this.courseYear = courseYear;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }


    public Teacher getTeachedBy() {
        return teachedBy;
    }

    public void setTeachedBy(Teacher teachedBy) {
        this.teachedBy = teachedBy;
    }

    public Set<Student> getParticipatedBy() {
        return participatedBy;
    }

    public void setParticipatedBy(Set<Student> participatedBy) {
        this.participatedBy = participatedBy;
    }

    private Set<Student> participatedBy;
}
