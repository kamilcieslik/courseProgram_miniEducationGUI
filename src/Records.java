/*
  Created by mrfarinq on 25.06.17.
 */

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Records {
    private List<Student> listOfStudents = new ArrayList<>();
    private List<Course> listOfCourses = new ArrayList<>();
    private String fileNameStudents = "students.bin";
    private String fileNameCourses = "courses.bin";

    List<Student> GetStudentsList() {
        return listOfStudents;
    }

    List<Course> GetCoursesList() {
        return listOfCourses;
    }

    private boolean FindStudentByIndex(int index) {
        boolean option = false;
        for (Student a : listOfStudents)
            if (a.GetIndexNumber() == index) option = true;

        return option;
    }

    private void AddStudent() throws Exception {
        boolean error;
        int index;
        do {
            error = false;
            try {
                index = ReadData.GetStudentIndexJFC("Podaj numer indeksu");
                if (!FindStudentByIndex(index)) {
                    Student newStudent = new Student(index);
                    listOfStudents.add(newStudent);
                    SortStudentByName();
                } else throw new Exception("Student o podanym indeksie juz istnieje!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                error = true;
            }

        } while (error);
    }

    void AddStudentSWING(String index, String name, String surname, String yearOfTheBeginning) {
        if ((index.isEmpty()) || (name.isEmpty()) || (surname.isEmpty()) ||
                (yearOfTheBeginning.isEmpty())) {
            JOptionPane.showMessageDialog(null, "Nie podano wystarczających danych!");
        } else {
            if (index.matches("^[0-9][0-9][0-9][0-9][0-9][0-9]")) {
                if ((name.matches("^[A-Z]{1}[a-z]+"))) {
                    if ((surname.matches("^[A-Z]{1}[a-z]+"))) {
                        if (!FindStudentByIndex(Integer.parseInt(index))) {
                            Student nowystudent = new Student(Integer.parseInt(index), name, surname, Integer
                                    .parseInt(yearOfTheBeginning));
                            listOfStudents.add(nowystudent);
                            JOptionPane.showMessageDialog(null, "Student dodany pomyslnie.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Student o podanym numerze indeksu juz istnieje!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Nie podano poprawnego nazwiska. Pamietaj ze musi " +
                                "rozpoczac sie od duzej litery!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nie podano poprawnego imienia. Pamietaj ze musi rozpoczac " +
                            "sie od duzej litery!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Numer indeksu musi si� sk�ada� z 6 cyfr!!");
            }
        }
    }

    void AddCourseSWING(String name, String _ects) {
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nie podano wystarczaj�cych danych!");
        } else if (name.matches("^[A-Z]{1}[a-z]+")) {
            if (!FindCourseByName(name)) {
                Course newCourse = new Course(name, Integer.parseInt(_ects));
                listOfCourses.add(newCourse);
                JOptionPane.showMessageDialog(null, "Course dodany pomyslnie.");
            } else {
                JOptionPane.showMessageDialog(null, "Course o podanej nazwie juz istnieje!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nie podano poprawnej nazwy. Pamietaj ze musi rozpoczac sie od duzej " +
                    "litery!");
        }
    }

    private void RemoveStudent() throws Exception {
        StringBuilder tmp = new StringBuilder();
        boolean error = false;
        int i = 0;
        try {
            for (Student listOfStudent : listOfStudents) {
                i++;
                tmp.append("\n").append(i).append(") ").append(listOfStudent);
            }
            if (listOfStudents.size() == 0) throw new Exception("Nie ma co usuwac. Brak studentow!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            error = true;
        }
        if (!error) {
            tmp.append("\n\nKtórego studenta usunac? ");
            int n = ReadData.GetIntJFC(tmp.toString());
            if (n < 1 || n > listOfStudents.size()) {
                JOptionPane.showMessageDialog(null, "Brak studenta o numerze " + n);
                return;

            }

            for (Course listOfCourse : listOfCourses) {
                if (listOfCourse.getListOfCourseStudents().contains(listOfStudents.get(n - 1)))
                    listOfCourse.RemoveStudent(listOfStudents.get(n - 1));
            }
            listOfStudents.remove(n - 1);


        }
    }

    void RemoveStudentSWING(int index) throws Exception {
        for (Course listOfCourse : listOfCourses) {
            if (listOfCourse.getListOfCourseStudents().contains(listOfStudents.get(index)))
                listOfCourse.RemoveStudent(listOfStudents.get(index));
        }
        listOfStudents.remove(index);
    }

    void RemoveCourseSWING(int index) throws Exception {
        for (Student listOfStudent : listOfStudents) {
            if (listOfStudent.GetListOfStudentCourses().contains(listOfCourses.get(index))) {
                listOfStudent.RemoveCourse(listOfCourses.get(index));
                listOfStudent.setTotalECTS(-listOfCourses.get(index).GetEcts());
            }
        }
        listOfCourses.remove(index);
    }

    private void ShowStudents() {
        StringBuilder tmp;
        if (listOfStudents.size() != 0) {
            tmp = new StringBuilder("Studenci: ");
            int i = 0;
            for (Student a : listOfStudents) {
                i++;
                tmp.append("\n").append(i).append(") ").append(a.toString());
            }
        } else tmp = new StringBuilder("Brak studentow!");
        JOptionPane.showMessageDialog(null, tmp.toString());
        SortStudentByName();
    }

    void SortStudentByName() {
        Collections.sort(listOfStudents);
    }

    void SortStudentsByInternship() {
        listOfStudents.sort(new ComparatorInternship());
    }

    private class ComparatorInternship implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            int Internship = o1.GetInternship() - o2.GetInternship();
            if (Internship == 0) {
                return o1.compareTo(o2);
            }
            return Internship;
        }
    }

    private boolean FindCourseByName(String name) {
        boolean option = false;
        for (Course a : listOfCourses)
            if (a.getName().equals(name)) option = true;

        return option;
    }

    private void AddCourse() throws Exception {
        boolean error;
        String name;
        do {
            error = false;
            try {
                name = ReadData.GetNameJFC("Podaj nazwe kursu: ");
                if (!FindCourseByName(name)) {
                    Course newCourse = new Course(name);
                    listOfCourses.add(newCourse);
                    SortCoursesByName();
                } else throw new Exception("Kurs o podanej nazwie juz istnieje!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                error = true;
            }

        } while (error);
    }

    private void RemoveCourse() throws Exception {
        StringBuilder tmp = new StringBuilder();
        boolean error = false;
        int i = 0;
        try {
            for (Course listOfCourse : listOfCourses) {
                i++;
                tmp.append("\n").append(i).append(") ").append(listOfCourse);
            }
            if (listOfCourses.size() == 0) throw new Exception("Nie ma co usuwac. Brak kursow!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            error = true;
        }
        if (!error) {
            tmp.append("\n\nKtóry kurs usunac? ");
            int n = ReadData.GetIntJFC(tmp.toString());
            if (n < 1 || n > listOfCourses.size()) {
                JOptionPane.showMessageDialog(null, "Brak kursu o numerze " + n);
                return;

            }

            for (Student listOfStudent : listOfStudents) {
                if (listOfStudent.GetListOfStudentCourses().contains(listOfCourses.get(n - 1)))
                    listOfStudent.RemoveCourse(listOfCourses.get(n - 1));
            }
            listOfCourses.remove(n - 1);

        }
    }

    private void ShowCourses() {
        StringBuilder tmp;
        if (listOfCourses.size() != 0) {
            tmp = new StringBuilder("Kursy: ");
            int i = 0;
            for (Course a : listOfCourses) {
                i++;
                tmp.append("\n").append(i).append(") ").append(a.toString());
            }
        } else tmp = new StringBuilder("Brak kursow!");
        JOptionPane.showMessageDialog(null, tmp.toString());
        SortCoursesByName();
    }

    void SortCoursesByName() {
        Collections.sort(listOfCourses);
    }

    void SortCoursesByECTS() {
        listOfCourses.sort(new ComparatorECTS());
    }

    private class ComparatorECTS implements Comparator<Course> {

        @Override
        public int compare(Course o1, Course o2) {
            int ects = o1.GetEcts() - o2.GetEcts();
            if (ects == 0) {
                return o1.compareTo(o2);
            }
            return ects;
        }
    }

    private void AddAssignment() {
        StringBuilder tmp = new StringBuilder();
        boolean error = false;
        int i = 0;
        try {
            for (Student listOfStudent : listOfStudents) {
                i++;
                tmp.append("\n").append(i).append(") ").append(listOfStudent);
            }
            if (listOfStudents.size() == 0) throw new Exception("Nie ma kogo przyporzadkowywac. Brak studentow!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            error = true;
        }
        if (!error) {
            tmp.append("\n\nKtórego studenta przyporzadkowac? ");
            int n = ReadData.GetIntJFC(tmp.toString());
            if (n < 1 || n > listOfStudents.size()) {
                JOptionPane.showMessageDialog(null, "Brak studenta o numerze " + n);
                return;

            }
            StringBuilder tmp2 = new StringBuilder();
            error = false;
            i = 0;
            try {
                for (Course listOfCourse : listOfCourses) {
                    i++;
                    tmp2.append("\n").append(i).append(") ").append(listOfCourse);
                }
                if (listOfCourses.size() == 0)
                    throw new Exception("Niestety w chwili obecnej nie mamy dla Ciebie zadnego kursu");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                error = true;
            }
            if (!error) {
                tmp2.append("\n\nKtory kurs wybierasz? ");
                int n2 = ReadData.GetIntJFC(tmp2.toString());
                if (n2 < 1 || n2 > listOfCourses.size()) {
                    JOptionPane.showMessageDialog(null, "Brak kursu o numerze " + n2);
                    return;

                }

                if (listOfStudents.get(n - 1).GetListOfStudentCourses().contains(listOfCourses.get(n2 - 1))) {
                    JOptionPane.showMessageDialog(null, "Zapis nieudany. Student nie moze byc zapisany na dwa " +
                            "indentyczne kursy!");
                } else {
                    if ((listOfStudents.get(n - 1)).getTotalECTS() + listOfCourses.get(n2 - 1).GetEcts() > 30) {
                        JOptionPane.showMessageDialog(null, "Zapis nieudany. Student moze miec maksymalnie 30 pkt " +
                                "ECTS.");
                    } else {
                        listOfStudents.get(n - 1).AddCourse(listOfCourses.get(n2 - 1));
                        listOfCourses.get(n2 - 1).AddStudent(listOfStudents.get(n - 1));
                        JOptionPane.showMessageDialog(null, "Zapisano pomyslnie.");
                        listOfStudents.get(n - 1).setTotalECTS(listOfCourses.get(n2 - 1).GetEcts());
                    }
                }

            }
        }
    }

    void AddAssignmentSWING(int selectedRowStudent, int selectedRowCourse) {

        if (listOfStudents.get(selectedRowStudent).GetListOfStudentCourses().contains(listOfCourses.get
                (selectedRowCourse))) {
            JOptionPane.showMessageDialog(null, "Zapis nieudany. Student nie moze byc zapisany na dwa indentyczne " +
                    "kursy!");
        } else {
            if ((listOfStudents.get(selectedRowStudent).getTotalECTS() + listOfCourses.get(selectedRowCourse).GetEcts()
            ) > 30) {
                JOptionPane.showMessageDialog(null, "Zapis nieudany. Student moze miec maksymalnie 30 pkt ECTS.");
            } else {
                listOfStudents.get(selectedRowStudent).AddCourse(listOfCourses.get(selectedRowCourse));
                listOfCourses.get(selectedRowCourse).AddStudent(listOfStudents.get(selectedRowStudent));
                JOptionPane.showMessageDialog(null, "Zapisano pomyslnie.");
                listOfStudents.get(selectedRowStudent).setTotalECTS(listOfCourses.get(selectedRowCourse).GetEcts());
            }
        }
    }

    private void ShowCoursesWithStudents() {
        String tmp = "";
        if (listOfCourses.size() != 0) {
            for (Course a : listOfCourses) {
                tmp += a.toString() + ":\n";
                tmp += a.ShowListOfCourseStudents();
            }
        } else tmp = "Brak kursow!";
        JOptionPane.showMessageDialog(null, tmp);
    }

    String ShowCoursesWithStudentsSWING() {
        String tmp = "";
        if (listOfCourses.size() != 0) {
            for (Course a : listOfCourses) {
                tmp += a.toString() + ":\n";
                tmp += a.ShowListOfCourseStudents();
            }
        } else tmp = "Brak kursow!";
        return tmp;
    }

    private void ShowStudentsWithCourses() {
        String tmp = "";
        if (listOfStudents.size() != 0) {
            for (Student a : listOfStudents) {
                tmp += a.toString() + ":\n";
                tmp += a.ShowListOfStudentCourses();
            }
        } else tmp = "Brak studentow!";
        JOptionPane.showMessageDialog(null, tmp);
    }

    String ShowStudentsWithCoursesSWING() {
        String tmp = "";
        if (listOfStudents.size() != 0) {
            for (Student a : listOfStudents) {
                tmp += a.toString() + ":\n";
                tmp += a.ShowListOfStudentCourses();
            }
        } else tmp = "Brak studentow!";
        return tmp;
    }

    void SaveMiniEducation() {

        try {
            ObjectOutputStream osStudents = new ObjectOutputStream(new FileOutputStream(fileNameStudents));
            osStudents.writeObject(listOfStudents);
            osStudents.close();
            ObjectOutputStream osCourses = new ObjectOutputStream(new FileOutputStream(fileNameCourses));
            osCourses.writeObject(listOfCourses);
            osCourses.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    void ReadMiniEducation() throws ClassNotFoundException {
        try {
            ObjectInputStream isStudents = new ObjectInputStream(new FileInputStream(fileNameStudents));
            listOfStudents = (List<Student>) isStudents.readObject();
            isStudents.close();
            ObjectInputStream isCourses = new ObjectInputStream(new FileInputStream(fileNameCourses));
            listOfCourses = (List<Course>) isCourses.readObject();
            isCourses.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Nie znaleziono pliku.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        Records newRecord = new Records();

        /*
          Możliwość wyboru rodzaju otwieranej aplikacji.
          Użytkownik może korzystać z poprzedniej wersji lub z nowej z GUI.
         */
        int selectedOption = JOptionPane.showConfirmDialog(null,
                "Wybierz wersję aplikacji: \nVERSION 2.0 - GUI SWING - Yes.\nVERSION 1.0 - JFC - No.",
                "Wybierz",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (selectedOption == JOptionPane.NO_OPTION) {
            while (true) {
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                System.out.println(newRecord);
                String menu = "M E N U\n" +
                        "1 - Wczytaj MiniEdukację\n" +
                        "2 - Zapisz/nadpisz bieżącą MiniEdukację\n" +
                        "3 - Dodaj Studenta\n" +
                        "4 - Usun Studenta\n" +
                        "5 - Wyswietl Studentow\n" +
                        "6 - Sortuj Studentow wg lat stazu\n" +
                        "7 - Dodaj Course\n" +
                        "8 - Usun Course\n" +
                        "9 - Wyswietl Kursy\n" +
                        "10 - Sortuj Kursy wg ECTS\n" +
                        "11 - Dodaj Przyporzadkowanie: Student->Course\n" +
                        "12 - Wyswietl Kursy ze Studentami\n" +
                        "13 - Wyswietl Studentow z Kursami\n" +
                        "0 - Zakoncz program";
                switch (ReadData.GetIntJFC(menu)) {
                    case 1:
                        newRecord.ReadMiniEducation();
                        break;
                    case 2:
                        newRecord.SaveMiniEducation();
                        break;
                    case 3:
                        newRecord.AddStudent();
                        break;
                    case 4:
                        newRecord.RemoveStudent();
                        break;
                    case 5:
                        newRecord.ShowStudents();
                        break;
                    case 6:
                        newRecord.SortStudentsByInternship();
                        break;
                    case 7:
                        newRecord.AddCourse();
                        break;
                    case 8:
                        newRecord.RemoveCourse();
                        break;
                    case 9:
                        newRecord.ShowCourses();
                        break;
                    case 10:
                        newRecord.SortCoursesByECTS();
                        break;
                    case 11:
                        newRecord.AddAssignment();
                        break;
                    case 12:
                        newRecord.ShowCoursesWithStudents();
                        break;
                    case 13:
                        newRecord.ShowStudentsWithCourses();
                        break;
                    case 0:
                        System.exit(0);
                }
            }
        } else if (selectedOption == JOptionPane.YES_OPTION) {
            MainFrame mainFrame = new MainFrame(newRecord);
            mainFrame.setVisible(true);
        } else if (selectedOption == JOptionPane.CANCEL_OPTION) {
            System.exit(0);
        }
    }
}
