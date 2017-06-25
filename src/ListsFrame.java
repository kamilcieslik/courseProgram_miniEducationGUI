import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

class ListsFrame extends JFrame {
    private static final long serialVersionUID = 4002731480961680538L;

    ListsFrame(JFrame previousFrame, Records newRecord) {

        setTitle("Lists");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 700, 580);
        setLocationRelativeTo(null);
        JPanel JPanelListsFrame = new JPanel();
        JPanelListsFrame.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(JPanelListsFrame);
        JPanelListsFrame.setLayout(new BorderLayout(0, 0));

        JLabel JLabelLists = new JLabel("Listy przyporzadkowan", SwingConstants.CENTER);
        JLabelLists.setFont(new Font("Yu Gothic", Font.PLAIN, 20));
        JPanelListsFrame.add(JLabelLists, BorderLayout.NORTH);

        JTextPane JTextFieldRecords = new JTextPane();
        JScrollPane scrollPaneRecords = new JScrollPane(JTextFieldRecords, ScrollPaneConstants
                .VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneRecords.setViewportView(JTextFieldRecords);
        JPanelListsFrame.add(scrollPaneRecords, BorderLayout.CENTER);

        /*
          Przycisk opcji, który po kliknięciu wyświetla w JTextFieldRecords studentów z kursami.
         */
        JRadioButton JRadioButtonStudentsWithCourses = new JRadioButton("Studenci z kursami");
        JRadioButtonStudentsWithCourses.addActionListener(e -> JTextFieldRecords.setText(newRecord
                .ShowStudentsWithCoursesSWING()));
        JRadioButtonStudentsWithCourses.setSelected(true);
        JPanelListsFrame.add(JRadioButtonStudentsWithCourses, BorderLayout.EAST);

        /*
          Przycisk opcji, który po kliknięciu wyświetla w JTextFieldRecords kursy ze studentami.
         */
        JRadioButton JRadioButtonCoursesWithStudents = new JRadioButton("Kursy ze studentami");
        JRadioButtonCoursesWithStudents.addActionListener(e -> JTextFieldRecords.setText(newRecord
                .ShowCoursesWithStudentsSWING()));
        JPanelListsFrame.add(JRadioButtonCoursesWithStudents, BorderLayout.WEST);

        /*
          Zgrupowanie powyższych przycisków opcji.
         */
        ButtonGroup ButtonGroupOfListsTypes = new ButtonGroup();
        ButtonGroupOfListsTypes.add(JRadioButtonStudentsWithCourses);
        ButtonGroupOfListsTypes.add(JRadioButtonCoursesWithStudents);

        /*
          Przycisk, który po kliknięciu zamyka ListFrame oraz odmraża MainFrame.
         */
        JButton JButtonClose = new JButton("Zamknij");
        JPanelListsFrame.add(JButtonClose, BorderLayout.SOUTH);
        JButtonClose.addActionListener(e -> {
            dispose();
            previousFrame.setEnabled(true);
        });

        /*
          Przeciążenie słuchacza wydarzenia otwarcia okna i zamknięcia okna.
          Po otwarciu ListFrame w JTextPane wyświetleni zostaną studenci z kursami - domyślna lista przyporządkowań.
          Po zamknięciu okna następuje odmrożenie MainFrame.
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
                JTextFieldRecords.setText(newRecord.ShowStudentsWithCoursesSWING());
            }
        });
    }
}