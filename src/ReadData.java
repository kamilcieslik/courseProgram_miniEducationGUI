/*
  Created by mrfarinq on 25.06.17.
 */

import java.util.Calendar;
import javax.swing.JOptionPane;

class ReadData {
    private static String GetStringJFC(String question) {
        return JOptionPane.showInputDialog(question);
    }

    static String GetNameJFC(String question) {
        boolean error;
        String c = null;
        do {
            error = false;
            try {
                c = GetStringJFC(question);
                if (!(c.matches("^[A-Z]{1}[a-z]+")))
                    throw new Exception("Podaj poprawne dane rozpoczynajac od duzej litery!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                error = true;
            }

        } while (error);
        return c;
    }

    static int GetStudentIndexJFC(String question) {
        boolean error;
        int i = 0;
        String c;
        do {
            error = false;
            try {
                c = GetStringJFC(question);
                char[] index;
                index = c.toCharArray();
                int amountOfElements = index.length;
                if (amountOfElements != 6) throw new Exception("Numer indeksu musi miec 6 cyfr!");
                i = Integer.parseInt(c);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                error = true;
            }
        } while (error);
        return i;
    }

    static int GetIntEctsJFC(String question) {
        boolean error;
        int i = 0;
        do {
            error = false;
            try {
                i = Integer.parseInt(GetStringJFC(question));
                if ((i < 1) || (i > 10)) throw new Exception("Jeden kurs moze miec 1-10 punktow ECTS!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                error = true;
            }
        } while (error);
        return i;
    }

    static int GetYearOfCommencement(String question) {

        boolean error;
        int i = 0;
        do {
            error = false;
            try {
                i = Integer.parseInt(GetStringJFC(question));
                Calendar calendar = Calendar.getInstance();
                int currentYear = calendar.get(Calendar.YEAR);
                if (i > currentYear)
                    throw new Exception("Poki co nie wynaleziono jeszcze wehikulu czasu. Podaj poprawna date " +
                            "rozpoczecia studiow!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                error = true;
            }
        } while (error);
        return i;
    }

    static int GetIntJFC(String question) {
        boolean error;
        int i = 0;
        do {
            error = false;
            try {
                i = Integer.parseInt(GetStringJFC(question));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Wprowadzono niepoprawne dane!");
                error = true;
            }
        } while (error);
        return i;
    }
}
