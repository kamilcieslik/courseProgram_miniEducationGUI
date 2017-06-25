/**
 * Created by mrfarinq on 25.06.17.
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JOptionPane;

public class Records {

    private List<Student> ListaStudentow = new ArrayList<Student>();
    private List<Course> ListaKursow = new ArrayList<Course>();
    private String fileNameStudents = "students.bin";
    private String fileNameCourses = "courses.bin";

    public List<Student> getListaStudentow() {
        return ListaStudentow;
    }

    public List<Course> getListaKursow() {
        return ListaKursow;
    }

    public boolean Znajdz_Studenta_Po_Indeksie(int indeks) {
        boolean opcja = false;
        for (Student a : ListaStudentow)
            if (a.getNr_Indeksu() == indeks) opcja = true;

        return opcja;
    }

    public void Dodaj_Studenta() throws Exception {
        boolean blad;
        int indeks;
        do {
            blad = false;
            try {
                indeks = CzytajDane.getNumer_IndeksuJFC("Podaj numer indeksu");
                if (Znajdz_Studenta_Po_Indeksie(indeks) == false) {
                    Student nowystudent = new Student(indeks);
                    ListaStudentow.add(nowystudent);
                    Sortuj_Alfabetycznie_Studentow();
                } else throw new Exception("Student o podanym indeksie juz istnieje!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                blad = true;
            }

        } while (blad);
    }

    public void Dodaj_Studenta_SWING(String index, String name, String surname, String yearOfTheBeginning) {
        if ((index.isEmpty() == true) || (name.isEmpty() == true) || (surname.isEmpty() == true) ||
                (yearOfTheBeginning.isEmpty() == true)) {
            JOptionPane.showMessageDialog(null, "Nie podano wystarczających danych!");
        } else {
            if (index.matches("^[0-9][0-9][0-9][0-9][0-9][0-9]")) {
                if ((name.matches("^[A-Z]{1}[a-z]+"))) {
                    if ((surname.matches("^[A-Z]{1}[a-z]+"))) {
                        if (Znajdz_Studenta_Po_Indeksie(Integer.parseInt(index)) == false) {
                            Student nowystudent = new Student(Integer.parseInt(index), name, surname, Integer
                                    .parseInt(yearOfTheBeginning));
                            ListaStudentow.add(nowystudent);
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

    public void Dodaj_Kurs_SWING(String name, String _ECTS) {
        if (name.isEmpty() == true) {
            JOptionPane.showMessageDialog(null, "Nie podano wystarczaj�cych danych!");
        } else if (name.matches("^[A-Z]{1}[a-z]+")) {
            if (Znajdz_Kurs_Po_Nazwie(name) == false) {
                Course nowykurs = new Course(name, Integer.parseInt(_ECTS));
                ListaKursow.add(nowykurs);
                JOptionPane.showMessageDialog(null, "Course dodany pomyslnie.");
            } else {
                JOptionPane.showMessageDialog(null, "Course o podanej nazwie juz istnieje!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nie podano poprawnej nazwy. Pamietaj ze musi rozpoczac sie od duzej " +
                    "litery!");
        }
    }

    public void Usun_Studenta() throws Exception {
        String tmp = "";
        boolean blad = false;
        int i = 0;
        try {
            Iterator<Student> itr = ListaStudentow.iterator();
            while (itr.hasNext()) {
                i++;
                tmp += "\n" + i + ") " + (itr.next());
            }
            if (ListaStudentow.size() == 0) throw new Exception("Nie ma co usuwac. Brak studentow!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            blad = true;
        }
        if (blad == false) {
            tmp += "\n\nKt�rego studenta usunac? ";
            int n = CzytajDane.getIntJFC(tmp);
            if (n < 1 || n > ListaStudentow.size()) {
                JOptionPane.showMessageDialog(null, "Brak studenta o numerze " + n);
                return;

            }

            for (int j = 0; j < ListaKursow.size(); j++) {
                if (ListaKursow.get(j).getListaStudentowKursu().contains(ListaStudentow.get(n - 1)))
                    ListaKursow.get(j).UsunStudenta(ListaStudentow.get(n - 1));
            }
            ListaStudentow.remove(n - 1);


        } else return;
    }

    public void Usun_Studenta_SWING(int indeks) throws Exception {
        for (int j = 0; j < ListaKursow.size(); j++) {
            if (ListaKursow.get(j).getListaStudentowKursu().contains(ListaStudentow.get(indeks)))
                ListaKursow.get(j).UsunStudenta(ListaStudentow.get(indeks));
        }
        ListaStudentow.remove(indeks);
    }

    public void Usun_Kurs_SWING(int indeks) throws Exception {
        for (int j = 0; j < ListaStudentow.size(); j++) {
            if (ListaStudentow.get(j).getListaKursowStudenta().contains(ListaKursow.get(indeks))) {
                ListaStudentow.get(j).UsunKurs(ListaKursow.get(indeks));
                ListaStudentow.get(j).setTotalECTS(-ListaKursow.get(indeks).getECTS());
            }
        }
        ListaKursow.remove(indeks);
    }

    public void WyswietlStudentow() {
        String tmp = "";
        if (ListaStudentow.size() != 0) {
            tmp = "Studenci: ";
            int i = 0;
            for (Student a : ListaStudentow) {
                i++;
                tmp += "\n" + i + ") " + a.toString();
            }
        } else tmp = "Brak studentow!";
        JOptionPane.showMessageDialog(null, tmp);
        Sortuj_Alfabetycznie_Studentow();
    }

    public void Sortuj_Alfabetycznie_Studentow() {
        Collections.sort(ListaStudentow);
    }

    public void Sortuj_Studentow_Po_Stazu() {
        Collections.sort(ListaStudentow, new KomparatorStaz());
    }

    private class KomparatorStaz implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            int Staz = o1.getStaz() - o2.getStaz();
            if (Staz == 0) {
                return o1.compareTo(o2);
            }
            return Staz;
        }
    }

    public boolean Znajdz_Kurs_Po_Nazwie(String nazwa) {
        boolean opcja = false;
        for (Course a : ListaKursow)
            if (a.getNazwa().equals(nazwa)) opcja = true;

        return opcja;
    }

    public void Dodaj_Kurs() throws Exception {
        boolean blad;
        String nazwa;
        do {
            blad = false;
            try {
                nazwa = CzytajDane.getNameJFC("Podaj nazwe kursu: ");
                if (Znajdz_Kurs_Po_Nazwie(nazwa) == false) {
                    Course nowykurs = new Course(nazwa);
                    ListaKursow.add(nowykurs);
                    Sortuj_Alfabetycznie_Kursy();
                } else throw new Exception("Course o podanej nazwie juz istnieje!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                blad = true;
            }

        } while (blad);
    }

    public void Usun_Kurs() throws Exception {
        String tmp = "";
        boolean blad = false;
        int i = 0;
        try {
            Iterator<Course> itr = ListaKursow.iterator();
            while (itr.hasNext()) {
                i++;
                tmp += "\n" + i + ") " + (itr.next());
            }
            if (ListaKursow.size() == 0) throw new Exception("Nie ma co usuwac. Brak kursow!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            blad = true;
        }
        if (blad == false) {
            tmp += "\n\nKtórego kurs usunac? ";
            int n = CzytajDane.getIntJFC(tmp);
            if (n < 1 || n > ListaKursow.size()) {
                JOptionPane.showMessageDialog(null, "Brak kursu o numerze " + n);
                return;

            }

            for (int j = 0; j < ListaStudentow.size(); j++) {
                if (ListaStudentow.get(j).getListaKursowStudenta().contains(ListaKursow.get(n - 1)))
                    ListaStudentow.get(j).UsunKurs(ListaKursow.get(n - 1));
            }
            ListaKursow.remove(n - 1);

        } else return;
    }


    public void WyswietlKursy() {
        String tmp = "";
        if (ListaKursow.size() != 0) {
            tmp = "Kursy: ";
            int i = 0;
            for (Course a : ListaKursow) {
                i++;
                tmp += "\n" + i + ") " + a.toString();
            }
        } else tmp = "Brak kursow!";
        JOptionPane.showMessageDialog(null, tmp);
        Sortuj_Alfabetycznie_Kursy();
    }

    public void Sortuj_Alfabetycznie_Kursy() {
        Collections.sort(ListaKursow);
    }

    public void Sortuj_Kursy_Po_ECTS() {
        Collections.sort(ListaKursow, new KomparatorECTS());
    }

    private class KomparatorECTS implements Comparator<Course> {

        @Override
        public int compare(Course o1, Course o2) {
            int ECTS = o1.getECTS() - o2.getECTS();
            if (ECTS == 0) {
                return o1.compareTo(o2);
            }
            return ECTS;
        }
    }

    public void Dodaj_Przyporzadkowanie() {
        String tmp = "";
        boolean blad = false;
        int i = 0;
        try {
            Iterator<Student> itr = ListaStudentow.iterator();
            while (itr.hasNext()) {
                i++;
                tmp += "\n" + i + ") " + (itr.next());
            }
            if (ListaStudentow.size() == 0) throw new Exception("Nie ma kogo przyporzadkowywac. Brak studentow!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            blad = true;
        }
        if (blad == false) {
            tmp += "\n\nKtórego studenta przyporzadkowac? ";
            int n = CzytajDane.getIntJFC(tmp);
            if (n < 1 || n > ListaStudentow.size()) {
                JOptionPane.showMessageDialog(null, "Brak studenta o numerze " + n);
                return;

            }
            String tmp2 = "";
            blad = false;
            i = 0;
            try {
                Iterator<Course> itr = ListaKursow.iterator();
                while (itr.hasNext()) {
                    i++;
                    tmp2 += "\n" + i + ") " + (itr.next());
                }
                if (ListaKursow.size() == 0)
                    throw new Exception("Niestety w chwili obecnej nie mamy dla Ciebie zadnego kursu");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                blad = true;
            }
            if (blad == false) {
                tmp2 += "\n\nKtory kurs wybierasz? ";
                int n2 = CzytajDane.getIntJFC(tmp2);
                if (n2 < 1 || n2 > ListaKursow.size()) {
                    JOptionPane.showMessageDialog(null, "Brak kursu o numerze " + n2);
                    return;

                }

                if (ListaStudentow.get(n - 1).getListaKursowStudenta().contains(ListaKursow.get(n2 - 1))) {
                    JOptionPane.showMessageDialog(null, "Zapis nieudany. Student nie moze byc zapisany na dwa " +
                            "indentyczne kursy!");
                    return;
                } else {
                    if ((ListaStudentow.get(n - 1)).getTotalECTS() + ListaKursow.get(n2 - 1).getECTS() > 30) {
                        JOptionPane.showMessageDialog(null, "Zapis nieudany. Student moze miec maksymalnie 30 pkt " +
                                "ECTS.");
                    } else {
                        ListaStudentow.get(n - 1).DodajKurs(ListaKursow.get(n2 - 1));
                        ListaKursow.get(n2 - 1).DodajStudenta(ListaStudentow.get(n - 1));
                        JOptionPane.showMessageDialog(null, "Zapisano pomyslnie.");
                        ListaStudentow.get(n - 1).setTotalECTS(ListaKursow.get(n2 - 1).getECTS());
                    }
                }

            } else return;
        }
    }

    public void Dodaj_Przyporzadkowanie_SWING(int selectedRowStudent, int selectedRowCourse) {

        if (ListaStudentow.get(selectedRowStudent).getListaKursowStudenta().contains(ListaKursow.get
                (selectedRowCourse))) {
            JOptionPane.showMessageDialog(null, "Zapis nieudany. Student nie moze byc zapisany na dwa indentyczne " +
                    "kursy!");
            return;
        } else {
            if ((ListaStudentow.get(selectedRowStudent).getTotalECTS() + ListaKursow.get(selectedRowCourse).getECTS()
            ) > 30) {
                JOptionPane.showMessageDialog(null, "Zapis nieudany. Student moze miec maksymalnie 30 pkt ECTS.");
            } else {
                ListaStudentow.get(selectedRowStudent).DodajKurs(ListaKursow.get(selectedRowCourse));
                ListaKursow.get(selectedRowCourse).DodajStudenta(ListaStudentow.get(selectedRowStudent));
                JOptionPane.showMessageDialog(null, "Zapisano pomyslnie.");
                ListaStudentow.get(selectedRowStudent).setTotalECTS(ListaKursow.get(selectedRowCourse).getECTS());
            }
        }
    }

    public void Wyswietl_Kursy_Ze_Studentami() {
        String tmp = "";
        if (ListaKursow.size() != 0) {
            for (Course a : ListaKursow) {
                tmp += a.toString() + ":\n";
                tmp += a.WyswietlListeStudentowKursu();
            }
        } else tmp = "Brak kursow!";
        JOptionPane.showMessageDialog(null, tmp);
    }

    public String Wyswietl_Kursy_Ze_Studentami_SWING() {
        String tmp = "";
        if (ListaKursow.size() != 0) {
            for (Course a : ListaKursow) {
                tmp += a.toString() + ":\n";
                tmp += a.WyswietlListeStudentowKursu();
            }
        } else tmp = "Brak kursow!";
        return tmp;
    }

    public void Wyswietl_Studentow_Z_Kursami() {
        String tmp = "";
        if (ListaStudentow.size() != 0) {
            for (Student a : ListaStudentow) {
                tmp += a.toString() + ":\n";
                tmp += a.WyswietlListeKursowStudenta();
            }
        } else tmp = "Brak studentow!";
        JOptionPane.showMessageDialog(null, tmp);
    }

    public String Wyswietl_Studentow_Z_Kursami_SWING() {
        String tmp = "";
        if (ListaStudentow.size() != 0) {
            for (Student a : ListaStudentow) {
                tmp += a.toString() + ":\n";
                tmp += a.WyswietlListeKursowStudenta();
            }
        } else tmp = "Brak studentow!";
        return tmp;
    }

    public void ZapiszMiniEdukacje() {

        try {
            ObjectOutputStream osStudents = new ObjectOutputStream(new FileOutputStream(fileNameStudents));
            osStudents.writeObject(ListaStudentow);
            osStudents.close();
            ObjectOutputStream osCourses = new ObjectOutputStream(new FileOutputStream(fileNameCourses));
            osCourses.writeObject(ListaKursow);
            osCourses.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void WczytajMiniEdukacje() throws ClassNotFoundException {
        try {
            ObjectInputStream isStudents = new ObjectInputStream(new FileInputStream(fileNameStudents));
            ListaStudentow = (List<Student>) isStudents.readObject();
            isStudents.close();
            ObjectInputStream isCourses = new ObjectInputStream(new FileInputStream(fileNameCourses));
            ListaKursow = (List<Course>) isCourses.readObject();
            isCourses.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Nie znaleziono pliku.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }


    public static void main(String[] args) throws Exception {

        Records nowyzapis = new Records();

        /**
         * Możliwość wyboru rodzaju otwieranej aplikacji.
         * Użytkownik może korzystać z poprzedniej wersji lub z nowej z GUI.
         */
        int selectedOption = JOptionPane.showConfirmDialog(null,
                "Wybierz wersję aplikacji: \nVERSION 2.0 - GUI SWING - Yes.\nVERSION 1.0 - JFC - No.",
                "Wybierz",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (selectedOption == JOptionPane.NO_OPTION) {
            while (true) {
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                System.out.println(nowyzapis);
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
                switch (CzytajDane.getIntJFC(menu)) {
                    case 1:
                        nowyzapis.WczytajMiniEdukacje();
                        break;
                    case 2:
                        nowyzapis.ZapiszMiniEdukacje();
                        break;
                    case 3:
                        nowyzapis.Dodaj_Studenta();
                        break;
                    case 4:
                        nowyzapis.Usun_Studenta();
                        break;
                    case 5:
                        nowyzapis.WyswietlStudentow();
                        break;
                    case 6:
                        nowyzapis.Sortuj_Studentow_Po_Stazu();
                        break;
                    case 7:
                        nowyzapis.Dodaj_Kurs();
                        break;
                    case 8:
                        nowyzapis.Usun_Kurs();
                        break;
                    case 9:
                        nowyzapis.WyswietlKursy();
                        break;
                    case 10:
                        nowyzapis.Sortuj_Kursy_Po_ECTS();
                        break;
                    case 11:
                        nowyzapis.Dodaj_Przyporzadkowanie();
                        break;
                    case 12:
                        nowyzapis.Wyswietl_Kursy_Ze_Studentami();
                        break;
                    case 13:
                        nowyzapis.Wyswietl_Studentow_Z_Kursami();
                        break;
                    case 0:
                        System.exit(0);
                }
            }
        } else if (selectedOption == JOptionPane.YES_OPTION) {
            MainFrame mainFrame = new MainFrame(nowyzapis);
            mainFrame.setVisible(true);
        } else if (selectedOption == JOptionPane.CANCEL_OPTION) {
            System.exit(0);
        }
    }
}
