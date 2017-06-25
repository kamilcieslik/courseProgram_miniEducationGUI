import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

class MainFrame extends JFrame {
    private static final long serialVersionUID = -434071091709274293L;

    MainFrame(Records newRecord) {

        setTitle("MiniEdukacja");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 1790, 560);
        setLocationRelativeTo(null);
        JPanel JPanelMainFrame = new JPanel();
        JPanelMainFrame.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(JPanelMainFrame);
        JPanelMainFrame.setLayout(null);
        setResizable(false);

        /*
          Etykieta z nazwą projektu - aplikacji.
         */
        JLabel JLabelTitle = new JLabel("Mini Edukacja");
        JLabelTitle.setForeground(new Color(139, 69, 19));
        JLabelTitle.setFont(new Font("Yu Gothic", Font.PLAIN, 50));
        JLabelTitle.setBounds(706, 0, 367, 93);
        JLabelTitle.setHorizontalAlignment(SwingConstants.CENTER);
        JPanelMainFrame.add(JLabelTitle);

        /*
          Etykieta nad komponentem tekstowym JTextPaneStudentDetails wyświetlającym szczegółowe informacje o
          zaznaczonym w JListStudents studencie.
         */
        JLabel JLabelStudent = new JLabel("Nie wybrano");
        JLabelStudent.setHorizontalAlignment(SwingConstants.CENTER);
        JLabelStudent.setForeground(Color.BLACK);
        JLabelStudent.setFont(new Font("Yu Gothic", Font.PLAIN, 20));
        JLabelStudent.setBounds(113, 84, 173, 33);
        JPanelMainFrame.add(JLabelStudent);

        /*
          Etykieta nad komponentem tekstowym JTextPaneCourseDetails wyświetlającym szczegółowe informacje o
          zaznaczonym w JListCourses kursie.
         */
        JLabel JLabelCourse = new JLabel("Nie wybrano");
        JLabelCourse.setHorizontalAlignment(SwingConstants.CENTER);
        JLabelCourse.setForeground(Color.BLACK);
        JLabelCourse.setFont(new Font("Yu Gothic", Font.PLAIN, 20));
        JLabelCourse.setBounds(1492, 84, 173, 33);
        JPanelMainFrame.add(JLabelCourse);

        /*
          Komponent tekstowy wyświetlający szczegółowe informacje o zaznaczonym w JListStudents studencie.
          Dla zawartego w nim tekstu zostały ustawione atrybuty wyświetlające go na środku.
         */
        JTextPane JTextPaneStudentDetails = new JTextPane();
        JTextPaneStudentDetails.setBackground(UIManager.getColor("Button.background"));
        JTextPaneStudentDetails.setBounds(10, 117, 387, 276);
        JTextPaneStudentDetails.setText("Aby wyswietlic szczegolowe informacje o studencie\nw pierwszej kolejnosci " +
                "musisz jakiegos wybrac.");
        SimpleAttributeSet attributesForText = new SimpleAttributeSet();
        StyleConstants.setAlignment(attributesForText, StyleConstants.ALIGN_CENTER);
        StyledDocument doc = JTextPaneStudentDetails.getStyledDocument();
        doc.setParagraphAttributes(0, 104, attributesForText, false);
        JPanelMainFrame.add(JTextPaneStudentDetails);

        /*
          Komponent tekstowy wyświetlający szczegółowe informacje o zaznaczonym w JListCourses kursie.
          Dla zawartego w nim tekstu zostały ustawione atrybuty wyświetlające go na środku.
         */
        JTextPane JTextPaneCourseDetails = new JTextPane();
        JTextPaneCourseDetails.setBackground(UIManager.getColor("Button.background"));
        JTextPaneCourseDetails.setBounds(1376, 117, 387, 276);
        JTextPaneCourseDetails.setText("Aby wyswietlic szczegolowe informacje o kursie\nw pierwszej kolejnosci musisz" +
                " jakies wybrac.");
        doc = JTextPaneCourseDetails.getStyledDocument();
        doc.setParagraphAttributes(0, 104, attributesForText, false);
        JPanelMainFrame.add(JTextPaneCourseDetails);

        /*
          JList ze studentami.
          Po zaznaczeniu pozycji studenta, wyświetlają się o nim szczegółowe informacjie w JTextPaneStudentDetails.
         */
        JList<Student> JListStudents = new JList<>();
        JListStudents.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JListStudents.getSelectionModel().addListSelectionListener(event -> {
            try {
                JTextPaneStudentDetails.setText(newRecord.GetStudentsList().get(JListStudents.getSelectedIndex
                        ()).ShowListOfStudentCourses());
                JLabelStudent.setText(newRecord.GetStudentsList().get(JListStudents.getSelectedIndex()).getName
                        () + " " + newRecord.GetStudentsList().get(JListStudents.getSelectedIndex()).getSurname
                        ());
            } catch (Exception e) {
                JTextPaneStudentDetails.setText("Aby wyswietlic szczegolowe informacje o studencie\nw pierwszej " +
                        "kolejnosci musisz jakiegos wybrac.");
                JLabelStudent.setText("Nie wybrano");
            }
        });

        /*
          Przejęcie JListStudents przez panel przewijany z suwakami.
          W sytuacji przepełnienia komponentu ze studentami pojawi się możliwość przewijania.
         */
        JScrollPane scrollPaneStudents = new JScrollPane(JListStudents, ScrollPaneConstants
                .VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneStudents.setBounds(406, 117, 387, 276);
        scrollPaneStudents.setViewportView(JListStudents);
        JPanelMainFrame.add(scrollPaneStudents);

        /*
          JList z kursami.
          Po zaznaczeniu pozycji kursu, wyświetlają się o nim szczegółowe informacjie w JTextPaneCourseDetails.
         */
        JList<Course> JListCourses = new JList<>();
        JListCourses.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JListCourses.getSelectionModel().addListSelectionListener(event -> {
            try {
                JTextPaneCourseDetails.setText(newRecord.GetCoursesList().get(JListCourses.getSelectedIndex())
                        .ShowListOfCourseStudents());
                JLabelCourse.setText(newRecord.GetCoursesList().get(JListCourses.getSelectedIndex()).getName());
            } catch (Exception e) {
                JTextPaneCourseDetails.setText("Aby wyswietlic szczegolowe informacje o kursie\nw pierwszej " +
                        "kolejnosci musisz jakis wybrac.");
                JLabelCourse.setText("Nie wybrano");
            }
        });

        /*
          Przejęcie JListCourses przez panel przewijany z suwakami.
          W sytuacji przepełnienia komponentu z kursami pojawi się możliwość przewijania.
         */
        JScrollPane scrollPaneCourses = new JScrollPane(JListCourses, ScrollPaneConstants
                .VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneCourses.setBounds(980, 117, 387, 276);
        scrollPaneCourses.setViewportView(JListCourses);
        JPanelMainFrame.add(scrollPaneCourses);

        /*
          Przycisk, który po kliknięciu wyświetla nowy Frame z kreatorem dodania nowego studenta.
          MainFrame przekazany aby móc go odmrozić po zamknięciu addStudentFrame.
          JListStudents przekazana aby ją odświeżyć po zamknięciu addStudentFrame.
         */
        JButton JButtonAddStudent = new JButton("Dodaj Studenta");
        JButtonAddStudent.addActionListener(e -> {
            MainFrame.this.setEnabled(false);
            AddStudentFrame addStudentFrame = new AddStudentFrame(MainFrame.this, JListStudents, newRecord);
            addStudentFrame.setVisible(true);
        });
        JButtonAddStudent.setBounds(406, 422, 130, 33);
        JPanelMainFrame.add(JButtonAddStudent);

        /*
          Przycisk, który po kliknięciu wyświetla nowy Frame z kreatorem dodania nowego kursu.
          MainFrame przekazany aby móc go odmrozić po zamknięciu addCourseFrame.
          JListCourses przekazana aby ją odświeżyć po zamknięciu addStudentFrame.
         */
        JButton JButtonAddCourse = new JButton("Dodaj Course");
        JButtonAddCourse.addActionListener(e -> {
            MainFrame.this.setEnabled(false);
            AddCourseFrame addCourseFrame = new AddCourseFrame(MainFrame.this, JListCourses, newRecord);
            addCourseFrame.setVisible(true);
        });
        JButtonAddCourse.setBounds(980, 422, 130, 33);
        JPanelMainFrame.add(JButtonAddCourse);

        /*
          Przycisk, który po kliknięciu usuwa, zaznaczonego w JListStudents, studenta.
          Po wywołaniu metody usuwającej następuje odświeżenie JListStudent.
         */
        JButton JButtonDeleteStudent = new JButton("Usun zaznaczonego studenta");
        JButtonDeleteStudent.addActionListener(e -> {
            try {
                int firstSelectedRow = JListStudents.getSelectedIndex();
                newRecord.RemoveStudentSWING(firstSelectedRow);

                DefaultListModel<Student> defaultListModelStudents = new DefaultListModel<>();
                for (int i = 0; i < newRecord.GetStudentsList().size(); i++)
                    defaultListModelStudents.addElement(newRecord.GetStudentsList().get(i));
                JListStudents.setModel(defaultListModelStudents);
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "Nie wykryto zaznaczenia!");
            }

        });
        JButtonDeleteStudent.setBounds(569, 422, 224, 33);
        JPanelMainFrame.add(JButtonDeleteStudent);

        /*
          Przycisk, który po kliknięciu usuwa, zaznaczony w JListCourses, kurs.
          Po wywołaniu metody usuwającej następuje odświeżenie JListCourses oraz JListStudents w celu
          odświeżenia punktów ECTS studentów zapisanych wcześniej na ten kurs.
         */
        JButton JButtonDeleteCourse = new JButton("Usun zaznaczony kurs");
        JButtonDeleteCourse.addActionListener(e -> {
            try {
                int firstSelectedRow = JListCourses.getSelectedIndex();
                newRecord.RemoveCourseSWING(firstSelectedRow);

                DefaultListModel<Course> defaultListModelCourses = new DefaultListModel<>();
                for (int i = 0; i < newRecord.GetCoursesList().size(); i++)
                    defaultListModelCourses.addElement(newRecord.GetCoursesList().get(i));
                JListCourses.setModel(defaultListModelCourses);

                DefaultListModel<Student> defaultListModelStudents = new DefaultListModel<>();
                for (int i = 0; i < newRecord.GetStudentsList().size(); i++)
                    defaultListModelStudents.addElement(newRecord.GetStudentsList().get(i));
                JListStudents.setModel(defaultListModelStudents);
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "Nie wykryto zaznaczenia!");
            }

        });
        JButtonDeleteCourse.setBounds(1143, 422, 224, 33);
        JPanelMainFrame.add(JButtonDeleteCourse);

        /*
          Etykieta nad JListStudents ze studentami.
         */
        JLabel JLabelStudents = new JLabel("Lista Studentow:");
        JLabelStudents.setHorizontalAlignment(SwingConstants.CENTER);
        JLabelStudents.setForeground(Color.BLACK);
        JLabelStudents.setFont(new Font("Yu Gothic", Font.PLAIN, 20));
        JLabelStudents.setBounds(508, 84, 173, 33);
        JPanelMainFrame.add(JLabelStudents);

        /*
          Etykieta nad JListCourses z kursami.
         */
        JLabel JLabelCourses = new JLabel("Lista Kursow:");
        JLabelCourses.setHorizontalAlignment(SwingConstants.CENTER);
        JLabelCourses.setForeground(Color.BLACK);
        JLabelCourses.setFont(new Font("Yu Gothic", Font.PLAIN, 20));
        JLabelCourses.setBounds(1097, 84, 173, 33);
        JPanelMainFrame.add(JLabelCourses);

        /*
          Przycisk, który po kliknięciu wywołuje metodę wczytującą zserializowane wcześniej do pliku listy
          studentów oraz kursów.
          Po wywołaniu metody wczytującej następuje odświeżenie JListStudents oraz JListCourses.
         */
        JButton JButtonLoad = new JButton("Wczytaj");
        JButtonLoad.addActionListener(e -> {
            try {
                DefaultListModel<Student> defaultListModelStudents = new DefaultListModel<>();
                newRecord.ReadMiniEducation();
                for (int i = 0; i < newRecord.GetStudentsList().size(); i++)
                    defaultListModelStudents.addElement(newRecord.GetStudentsList().get(i));
                JListStudents.setModel(defaultListModelStudents);

                DefaultListModel<Course> defaultListModelCourses = new DefaultListModel<>();
                for (int i = 0; i < newRecord.GetCoursesList().size(); i++)
                    defaultListModelCourses.addElement(newRecord.GetCoursesList().get(i));
                JListCourses.setModel(defaultListModelCourses);
            } catch (ClassNotFoundException e1) {
                JOptionPane.showMessageDialog(null, "Nie znaleziono pliku.");
            }
        });
        JButtonLoad.setBounds(803, 117, 167, 40);
        JPanelMainFrame.add(JButtonLoad);

        /*
          Przycisk, który po kliknięciu wywołuje metodę zapisującą do pliku listy studentów oraz kursów.
         */
        JButton JButtonSave = new JButton("Zapisz");
        JButtonSave.addActionListener(e -> {
            int selectedOption = JOptionPane.showConfirmDialog(null,
                    "Czy na pewno chcesz zapisać widoczną MiniEdukację?\nSkutkuje to nadpisaniem poprzedniej" +
                            ".\nOperacja wymaga potwierdzenia.",
                    "Wybierz",
                    JOptionPane.YES_NO_OPTION);
            if (selectedOption == JOptionPane.YES_OPTION) {
                newRecord.SaveMiniEducation();
            }
        });
        JButtonSave.setBounds(803, 168, 167, 40);
        JPanelMainFrame.add(JButtonSave);

        /*
          Przycisk, który po kliknięciu wywołuje metodę przyporządkowania zaznaczonego studenta do zaznaczonego
          kursu.
          Po udanym przypisaniu następuje odświeżenie JListStudents w celu ukazania nowej wartości sumy punktów
          ECTS danego studenta.
         */
        JButton JButtonAssignment = new JButton("Zapisz na kurs");
        JButtonAssignment.addActionListener(e -> {
            try {
                int firstSelectedRowStudent = JListStudents.getSelectedIndex();
                int firstSelectedRowCourse = JListCourses.getSelectedIndex();
                int selectedOption = JOptionPane.showConfirmDialog(null,
                        "Czy na pewno chcesz przypisac studenta " + newRecord.GetStudentsList().get
                                (firstSelectedRowStudent).getName() + " " + newRecord.GetStudentsList().get
                                (firstSelectedRowStudent).getSurname() + " do kursu " + newRecord.GetCoursesList
                                ().get(firstSelectedRowCourse).getName() + "?",
                        "Potwierdz",
                        JOptionPane.YES_NO_OPTION);
                if (selectedOption == JOptionPane.YES_OPTION) {
                    newRecord.AddAssignmentSWING(firstSelectedRowStudent, firstSelectedRowCourse);
                    JTextPaneStudentDetails.setText(newRecord.GetStudentsList().get(firstSelectedRowStudent)
                            .ShowListOfStudentCourses());
                    JLabelStudent.setText(newRecord.GetStudentsList().get(firstSelectedRowStudent).getName() +
                            " " + newRecord.GetStudentsList().get(JListStudents.getSelectedIndex()).getSurname
                            ());
                    JTextPaneCourseDetails.setText(newRecord.GetCoursesList().get(firstSelectedRowCourse)
                            .ShowListOfCourseStudents());
                    JLabelCourse.setText(newRecord.GetCoursesList().get(firstSelectedRowCourse).getName());

                    DefaultListModel<Student> defaultListModelStudents = new DefaultListModel<>();
                    for (int i = 0; i < newRecord.GetStudentsList().size(); i++)
                        defaultListModelStudents.addElement(newRecord.GetStudentsList().get(i));
                    JListStudents.setModel(defaultListModelStudents);
                }
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "Aby przypisac studenta do kursu, w pierwszej kolejnosci " +
                        "zaznacz studenta oraz odpowiedni kurs!");
            }


        });
        JButtonAssignment.setToolTipText("");
        JButtonAssignment.setBounds(803, 353, 167, 40);
        JPanelMainFrame.add(JButtonAssignment);

        /*
          Przycisk opcji, który po kliknięciu wywołuje metody sortujące Listy Kursów i Studentów alfabetycznie.
          Po sortowaniu następuje odświeżenie JListStudents oraz JListCourses.
         */
        JRadioButton JRadioButtonNormalSorting = new JRadioButton("Sortowanie alfabetyczne");
        JRadioButtonNormalSorting.addActionListener(e -> {
            newRecord.SortStudentByName();
            newRecord.SortCoursesByName();

            DefaultListModel<Student> defaultListModelStudents = new DefaultListModel<>();
            for (int i = 0; i < newRecord.GetStudentsList().size(); i++)
                defaultListModelStudents.addElement(newRecord.GetStudentsList().get(i));
            JListStudents.setModel(defaultListModelStudents);

            DefaultListModel<Course> defaultListModelCourses = new DefaultListModel<>();
            for (int i = 0; i < newRecord.GetCoursesList().size(); i++)
                defaultListModelCourses.addElement(newRecord.GetCoursesList().get(i));
            JListCourses.setModel(defaultListModelCourses);

        });
        JRadioButtonNormalSorting.setSelected(true);
        JRadioButtonNormalSorting.setBounds(803, 249, 171, 23);
        JPanelMainFrame.add(JRadioButtonNormalSorting);

        /*
          Przycisk opcji, który po kliknięciu wywołuje metody sortujące Listy Kursów i Studentów wg pkt ECTS
          oraz wg STAŻU.
          Po sortowaniu następuje odświeżenie JListStudents oraz JListCourses.
         */
        JRadioButton JRadioButtonAlternativeSorting = new JRadioButton("Sortowanie alternatywne");
        JRadioButtonAlternativeSorting.addActionListener(e -> {
            newRecord.SortCoursesByECTS();
            newRecord.SortStudentsByInternship();

            DefaultListModel<Student> defaultListModelStudents = new DefaultListModel<>();
            for (int i = 0; i < newRecord.GetStudentsList().size(); i++)
                defaultListModelStudents.addElement(newRecord.GetStudentsList().get(i));
            JListStudents.setModel(defaultListModelStudents);

            DefaultListModel<Course> defaultListModelCourses = new DefaultListModel<>();
            for (int i = 0; i < newRecord.GetCoursesList().size(); i++)
                defaultListModelCourses.addElement(newRecord.GetCoursesList().get(i));
            JListCourses.setModel(defaultListModelCourses);

        });
        JRadioButtonAlternativeSorting.setBounds(803, 275, 171, 23);
        JPanelMainFrame.add(JRadioButtonAlternativeSorting);

        /*
          Zgrupowanie powyższych przycisków opcji.
         */
        ButtonGroup ButtonGroupOfSorts = new ButtonGroup();
        ButtonGroupOfSorts.add(JRadioButtonNormalSorting);
        ButtonGroupOfSorts.add(JRadioButtonAlternativeSorting);

        /*
          Przycisk, który po kliknięciu zamyka aplikację.
         */
        JButton JButtonClose = new JButton("Zamknij");
        JButtonClose.addActionListener(e -> {
            int selectedOption = JOptionPane.showConfirmDialog(null,
                    "Czy na pewno chcesz wyjsc z aplikacji?",
                    "Wybierz",
                    JOptionPane.YES_NO_OPTION);
            if (selectedOption == JOptionPane.YES_OPTION) {
                MainFrame.this.dispose();
            }
        });
        JButtonClose.setBounds(1458, 466, 250, 33);
        JPanelMainFrame.add(JButtonClose);

        /*
          Przycisk, który po kliknięciu wyświetla nowy Frame z listami przyporządkować studentów do kursów oraz
          kursów do studentów.
          MainFrame przekazany aby móc go odmrozić po zamknięciu addCourseFrame.
         */
        JButton JButtonShowRecords = new JButton("Wyswietl przyporzadkowania");
        JButtonShowRecords.addActionListener(e -> {
            setEnabled(false);
            ListsFrame listsFrame = new ListsFrame(MainFrame.this, newRecord);
            listsFrame.setVisible(true);
        });
        JButtonShowRecords.setBounds(76, 466, 250, 33);
        JPanelMainFrame.add(JButtonShowRecords);
    }
}