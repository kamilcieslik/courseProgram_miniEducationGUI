/**
 * Created by mrfarinq on 25.06.17.
 */
import java.util.Calendar;
import javax.swing.JOptionPane;

public class ReadData {


    public static String getStringJFC(String pytanie) {
        return JOptionPane.showInputDialog(pytanie);
    }

    public static String getNameJFC(String pytanie){
        boolean blad;
        String c = null;
        do{
            blad = false;
            try{
                c = getStringJFC(pytanie);
                if ((c.matches("^[A-Z]{1}[a-z]+"))==false) throw new Exception("Podaj poprawne dane rozpoczynajac od duzej litery!");
            } catch(Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage());
                blad = true;
            }

        }while(blad);
        return c;
    }

    public static int getNumer_IndeksuJFC(String pytanie) {
        boolean blad;
        int i = 0;
        String c = null;
        do{
            blad = false;
            try{
                c = getStringJFC(pytanie);
                char[] tablica;
                tablica=c.toCharArray();
                int ilosc_elementow = tablica.length;
                if(ilosc_elementow!=6) throw new Exception("Numer indeksu musi miec 6 cyfr!");
                i = Integer.parseInt(c);
            } catch(Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage());
                blad = true;
            }
        }while(blad);
        return i;
    }

    public static int getIntEctsJFC(String pytanie) {
        boolean blad;
        int i = 0;
        do{
            blad = false;
            try{
                i = Integer.parseInt(getStringJFC(pytanie));
                if ((i<1)||(i>10)) throw new Exception("Jeden kurs moze miec 1-10 punktow ECTS!");
            } catch(Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage());
                blad = true;
            }
        }while(blad);
        return i;
    }

    public static int getRok_RozpoczeciaJFC(String pytanie) {

        boolean blad;
        int i = 0;
        do{
            blad = false;
            try{
                i = Integer.parseInt(getStringJFC(pytanie));
                Calendar calendar = Calendar.getInstance();
                int aktualny_rok=calendar.get(Calendar.YEAR);
                if (i>aktualny_rok) throw new Exception("Poki co nie wynaleziono jeszcze wehikulu czasu. Podaj poprawna date rozpoczecia studiow!");
            } catch(Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage());
                blad = true;
            }
        }while(blad);
        return i;
    }

    public static int getIntJFC(String pytanie) {
        boolean blad;
        int i = 0;
        do{
            blad = false;
            try{
                i = Integer.parseInt(getStringJFC(pytanie));
            } catch(Exception e){
                JOptionPane.showMessageDialog(null, "Wprowadzono niepoprawne dane!");
                blad = true;
            }
        }while(blad);
        return i;
    }
}
