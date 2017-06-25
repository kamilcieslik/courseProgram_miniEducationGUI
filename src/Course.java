/*
  Created by mrfarinq on 25.06.17.
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Course implements Comparable<Course>, Serializable {
    private static final long serialVersionUID = -7963279805278185119L;
    private String name;
    private int ects;

    private List<Student> listOfCourseStudents = new ArrayList<>();

    Course(String name) {
        this.name = name;
        _setEcts(ReadData.GetIntEctsJFC("Jaka wage punktow ects ma miec ten kurs? (1-10)"));
    }

    Course(String name, int _ects) {
        this.name = name;
        ects = _ects;
    }

    String getName() {
        return name;
    }

    int GetEcts() {
        return ects;
    }

    private void _setEcts(int _ects) {
        ects = _ects;
    }

    public String toString() {
        return (name + ", ects: " + ects);
    }

    @Override
    public int compareTo(Course o) {
        return name.compareTo(o.getName());
    }

    List<Student> getListOfCourseStudents() {
        return listOfCourseStudents;
    }

    void AddStudent(Student student) {
        listOfCourseStudents.add(student);
    }

    void RemoveStudent(Student student) {
        listOfCourseStudents.remove(student);
    }

    String ShowListOfCourseStudents() {
        StringBuilder tmp = new StringBuilder();
        if (listOfCourseStudents.size() != 0) {
            for (Student a : listOfCourseStudents) {
                tmp.append("- ").append(a.toString()).append("\n");
            }
        } else tmp = new StringBuilder("- na kurs nie zostal wpisany jeszcze zaden student.\n");
        return tmp.toString();
    }
}