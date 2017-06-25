/**
 * Created by mrfarinq on 25.06.17.
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Course implements Comparable<Course>, Serializable{

    private static final long serialVersionUID = -7963279805278185119L;
    private String Nazwa;
    private int ECTS;

    private List<Student> ListaStudentowKursu = new ArrayList<Student>();


    Course(){
        _setNazwa(CzytajDane.getNameJFC("Podaj nazwe kursu: "));
        _setECTS(CzytajDane.getIntEctsJFC("Jaka wage punktow ECTS ma miec ten kurs? (1-10)"));
    }

    Course(String nazwa){
        Nazwa=nazwa;
        _setECTS(CzytajDane.getIntEctsJFC("Jaka wage punktow ECTS ma miec ten kurs? (1-10)"));
    }
    Course(String name, int _ECTS){
        Nazwa=name;
        ECTS=_ECTS;
    }

    public String getNazwa() {
        return Nazwa;
    }

    private void _setNazwa(String nazwa) {
        Nazwa=nazwa;
    }

    public int getECTS() {
        return ECTS;
    }

    private void _setECTS(int eCTS) {
        ECTS = eCTS;
    }

    public void wprowadzDane(){
        _setNazwa(CzytajDane.getNameJFC("Podaj nazwe kursu: "));
        _setECTS(CzytajDane.getIntEctsJFC("Jaka wage punktow ECTS ma miec ten kurs? (1-10)"));
    }

    public String toString(){
        return (Nazwa+", ECTS: "+ECTS);
    }

    @Override
    public int compareTo(Course o) {
        int porownaneNazwy = Nazwa.compareTo(o.getNazwa());
        return porownaneNazwy;
    }

    public List<Student> getListaStudentowKursu() {
        return ListaStudentowKursu;
    }

    public void setListaStudentowKursu(List<Student> listaStudentowKursu) {
        ListaStudentowKursu = listaStudentowKursu;
    }

    public void DodajStudenta(Student student){
        ListaStudentowKursu.add(student);
    }

    public void UsunStudenta(Student student){
        ListaStudentowKursu.remove(student);
    }

    public String WyswietlListeStudentowKursu(){
        String tmp="";
        if (ListaStudentowKursu.size()!=0){
            for(Student a : ListaStudentowKursu) {
                tmp += "- "+a.toString()+"\n";
            }
        }
        else tmp="- na kurs nie zostal wpisany jeszcze zaden student.\n";
        return tmp;
    }

}