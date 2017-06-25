import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

class AddStudentFrame extends JFrame {
    private static final long serialVersionUID = 981827316073992168L;
    private JTextField JTextFieldIndex;
    private JTextField JTextFieldName;
    private JTextField JTextFieldSurname;

    private String[] messageStrings = {"2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007",
            "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995"};
    private JComboBox<String> JComboBoxYearOfTheBeginning = new JComboBox<>(messageStrings);


    AddStudentFrame(JFrame previousFrame, JList<Student> JListStudents, Records newRecord) {

        setTitle("CreatorOfStudents");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 421, 400);
        setLocationRelativeTo(null);
        JPanel JPanelAddStudentFrame = new JPanel();
        JPanelAddStudentFrame.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(JPanelAddStudentFrame);
        JPanelAddStudentFrame.setLayout(null);

        /*
          Przycisk, który po kliknięciu wywołuje metodę odpowiadającą za dodanie do ListyStudentów nowego studenta.
          W metodzie tej (newRecord.AddStudentSWING()) sprawdzana jest poprawność wprowadzanych do JTextFieldów
          danych.
          Po dodaniu następuje odświeżenie JListStudents w MainFrame, zamknięcie obecnego AddStudentFrame, oraz
          odmrożenie MainFrame.
         */
        JButton JButtonAddStudent = new JButton("Dodaj studenta");
        JButtonAddStudent.addActionListener(e -> {
            int amountOfStudentsBefore = newRecord.GetStudentsList().size();
            newRecord.AddStudentSWING(JTextFieldIndex.getText(), JTextFieldName.getText(), JTextFieldSurname
                    .getText(), JComboBoxYearOfTheBeginning.getSelectedItem().toString());
            int amountOfStudentsAfter = newRecord.GetStudentsList().size();
            if (amountOfStudentsAfter > amountOfStudentsBefore) {
                AddStudentFrame.this.dispose();
                previousFrame.setEnabled(true);
                DefaultListModel<Student> defaultListModelStudents = new DefaultListModel<>();
                for (int i = 0; i < newRecord.GetStudentsList().size(); i++)
                    defaultListModelStudents.addElement(newRecord.GetStudentsList().get(i));
                JListStudents.setModel(defaultListModelStudents);
            }
        });
        JButtonAddStudent.setBounds(30, 306, 152, 23);
        JPanelAddStudentFrame.add(JButtonAddStudent);

        JTextFieldIndex = new JTextField("");
        JTextFieldIndex.setBounds(203, 110, 171, 20);
        JPanelAddStudentFrame.add(JTextFieldIndex);
        JTextFieldIndex.setColumns(10);

        JTextFieldName = new JTextField("");
        JTextFieldName.setColumns(10);
        JTextFieldName.setBounds(203, 141, 171, 20);
        JPanelAddStudentFrame.add(JTextFieldName);

        JTextFieldSurname = new JTextField("");
        JTextFieldSurname.setColumns(10);
        JTextFieldSurname.setBounds(203, 172, 171, 20);
        JPanelAddStudentFrame.add(JTextFieldSurname);

        JLabel JLabelData = new JLabel("Podaj dane:");
        JLabelData.setFont(new Font("Yu Gothic", Font.PLAIN, 36));
        JLabelData.setBounds(30, 28, 287, 50);
        JPanelAddStudentFrame.add(JLabelData);

        JLabel JLabelIndex = new JLabel("Indeks");
        JLabelIndex.setFont(new Font("Yu Gothic", Font.PLAIN, 14));
        JLabelIndex.setBounds(30, 112, 52, 14);
        JPanelAddStudentFrame.add(JLabelIndex);

        JLabel JLabelName = new JLabel("Imie");
        JLabelName.setFont(new Font("Yu Gothic", Font.PLAIN, 14));
        JLabelName.setBounds(30, 143, 52, 14);
        JPanelAddStudentFrame.add(JLabelName);

        JLabel JLabelSurname = new JLabel("Nazwisko");
        JLabelSurname.setFont(new Font("Yu Gothic", Font.PLAIN, 14));
        JLabelSurname.setBounds(30, 172, 86, 14);
        JPanelAddStudentFrame.add(JLabelSurname);

        JLabel JLabelYearOfTheBeginning = new JLabel("Rok rozpoczecia studiow");
        JLabelYearOfTheBeginning.setFont(new Font("Yu Gothic", Font.PLAIN, 14));
        JLabelYearOfTheBeginning.setBounds(30, 205, 163, 14);
        JPanelAddStudentFrame.add(JLabelYearOfTheBeginning);

        /*
          Przycisk, który po kliknięciu zamyka AddStudentFrame oraz odmraża MainFrame.
         */
        JButton JButtonClose = new JButton("Anuluj");
        JButtonClose.addActionListener(e -> {
            AddStudentFrame.this.dispose();
            previousFrame.setEnabled(true);
        });
        JButtonClose.setBounds(222, 306, 152, 23);
        JPanelAddStudentFrame.add(JButtonClose);

        JComboBoxYearOfTheBeginning.setBounds(203, 203, 171, 20);
        JPanelAddStudentFrame.add(JComboBoxYearOfTheBeginning);

        /*
          Przeciążenie słuchacza wydarzenia zamknięcia okna.
          Po zamknęciu okna następuje odmrożenie MainFrame.
         */
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                previousFrame.setEnabled(true);
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowOpened(WindowEvent e) {
            }
        });
    }
}
