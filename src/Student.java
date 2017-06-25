import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Student implements Comparable<Student>, Serializable {

    private static final long serialVersionUID = -8809444346662435457L;
    private String Imie;
    private String Nazwisko;
    private int Nr_Indeksu;
    private int Rok_Rozpoczecia_Studiow;
    private int Staz;
    private int totalECTS = 0;


    private List<Course> ListaKursowStudenta = new ArrayList<Course>();

    Student() {
        _setImie(ReadData.getNameJFC("Podaj imie: "));
        _setNazwisko(ReadData.getNameJFC("Podaj nazwisko: "));
        _setNr_Indeksu(ReadData.getNumer_IndeksuJFC("Podaj numer indeksu: "));
        Rok_Rozpoczecia_Studiow = ReadData.getRok_RozpoczeciaJFC("Podaj rok w ktorym zaczales studiowac. Jezeli " +
                "nasza uczelnia jest Twoja pierwsza, podaj biezacy rok.");
        ObliczStaz();
    }

    Student(int indeks) {
        Nr_Indeksu = indeks;
        _setImie(ReadData.getNameJFC("Podaj imie: "));
        _setNazwisko(ReadData.getNameJFC("Podaj nazwisko: "));
        Rok_Rozpoczecia_Studiow = ReadData.getRok_RozpoczeciaJFC("Podaj rok w ktorym zaczales studiowac. Jezeli " +
				"nasza uczelnia jest Twoja pierwsza, podaj biezacy rok.");
        ObliczStaz();
    }

    Student(int index, String name, String surname, int yearOfTheBeginning) {
        Nr_Indeksu = index;
        Imie = name;
        Nazwisko = surname;
        Rok_Rozpoczecia_Studiow = yearOfTheBeginning;
        ObliczStaz();
    }

    public void setTotalECTS(int totalECTS) {
        this.totalECTS += totalECTS;
    }

    public int getTotalECTS() {
        return totalECTS;
    }

    public String getImie() {
        return Imie;
    }

    private void _setImie(String imie) {
        Imie = imie;
    }

    public String getNazwisko() {
        return Nazwisko;
    }

    private void _setNazwisko(String nazwisko) {
        Nazwisko = nazwisko;
    }

    public int getNr_Indeksu() {
        return Nr_Indeksu;
    }

    private void _setNr_Indeksu(int nr_Indeksu) {
        Nr_Indeksu = nr_Indeksu;
    }

    public int getStaz() {
        return Staz;
    }

    public void ObliczStaz() {
        Calendar calendar = Calendar.getInstance();
        Staz = (calendar.get(Calendar.YEAR) - Rok_Rozpoczecia_Studiow);
    }

    public void wprowadzDane() {
        _setImie(ReadData.getNameJFC("Podaj imie: "));
        _setNazwisko(ReadData.getNameJFC("Podaj nazwisko: "));
        _setNr_Indeksu(ReadData.getNumer_IndeksuJFC("Podaj numer indeksu: "));
        Rok_Rozpoczecia_Studiow = ReadData.getRok_RozpoczeciaJFC("Podaj rok w ktorym zaczales studiowac. Jezeli " +
				"nasza uczelnia jest Twoja pierwsza, podaj biezacy rok.");
        ObliczStaz();
    }

    public String toString() {
        return (Nazwisko + " " + Imie + ", INDEKS: " + Nr_Indeksu + ", STAZ: " + Staz + ", ECTS: " + totalECTS);
    }

    @Override
    public int compareTo(Student o) {
        int porownaneNazwiska = Nazwisko.compareTo(o.getNazwisko());

        if (porownaneNazwiska == 0) {
            return Imie.compareTo(o.getImie());
        } else {
            return porownaneNazwiska;
        }
    }


    public List<Course> getListaKursowStudenta() {
        return ListaKursowStudenta;
    }


    public void setListaKursowStudenta(List<Course> listaKursowStudenta) {
        ListaKursowStudenta = listaKursowStudenta;
    }

    public void DodajKurs(Course kurs) {
        ListaKursowStudenta.add(kurs);
    }

    public void UsunKurs(Course kurs) {
        ListaKursowStudenta.remove(kurs);
    }

    public String WyswietlListeKursowStudenta() {
        String tmp = "";
        if (ListaKursowStudenta.size() != 0) {
            for (Course a : ListaKursowStudenta) {
                tmp += "- " + a.toString() + "\n";
            }
        } else tmp = "- student nie zostać zapisany na żaden kurs.\n";
        return tmp;
    }

}
