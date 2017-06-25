import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Student implements Comparable<Student>, Serializable {
    private static final long serialVersionUID = -8809444346662435457L;
    private String name;
    private String surname;
    private int index;
    private int yearOfCommencement;
    private int internship;
    private int totalECTS = 0;
    private List<Course> listOfStudentCourses = new ArrayList<>();

    Student(int index) {
        this.index = index;
        _setName(ReadData.GetNameJFC("Podaj imie: "));
        _setSurname(ReadData.GetNameJFC("Podaj nazwisko: "));
        yearOfCommencement = ReadData.GetYearOfCommencement("Podaj rok w ktorym zaczales studiowac. Jezeli " +
                "nasza uczelnia jest Twoja pierwsza, podaj biezacy rok.");
        CalculateInternship();
    }

    Student(int index, String name, String surname, int yearOfTheBeginning) {
        this.index = index;
        this.name = name;
        this.surname = surname;
        yearOfCommencement = yearOfTheBeginning;
        CalculateInternship();
    }

    void setTotalECTS(int totalECTS) {
        this.totalECTS += totalECTS;
    }

    int getTotalECTS() {
        return totalECTS;
    }

    String getName() {
        return name;
    }

    private void _setName(String imie) {
        name = imie;
    }

    String getSurname() {
        return surname;
    }

    private void _setSurname(String nazwisko) {
        surname = nazwisko;
    }

    int GetIndexNumber() {
        return index;
    }

    int GetInternship() {
        return internship;
    }

    private void CalculateInternship() {
        Calendar calendar = Calendar.getInstance();
        internship = (calendar.get(Calendar.YEAR) - yearOfCommencement);
    }

    public String toString() {
        return (surname + " " + name + ", INDEKS: " + index + ", STAZ: " + internship + ", ECTS: " + totalECTS);
    }

    @Override
    public int compareTo(Student o) {
        int comparedNames = surname.compareTo(o.getSurname());

        if (comparedNames == 0) {
            return name.compareTo(o.getName());
        } else {
            return comparedNames;
        }
    }

    List<Course> GetListOfStudentCourses() {
        return listOfStudentCourses;
    }

    void AddCourse(Course kurs) {
        listOfStudentCourses.add(kurs);
    }

    void RemoveCourse(Course kurs) {
        listOfStudentCourses.remove(kurs);
    }

    String ShowListOfStudentCourses() {
        StringBuilder tmp = new StringBuilder();
        if (listOfStudentCourses.size() != 0) {
            for (Course a : listOfStudentCourses) {
                tmp.append("- ").append(a.toString()).append("\n");
            }
        } else tmp = new StringBuilder("- student nie zostać zapisany na żaden kurs.\n");
        return tmp.toString();
    }
}
