import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class ListsFrame extends JFrame {

	private static final long serialVersionUID = 4002731480961680538L;
	private JPanel JPanelListsFrame;

	public ListsFrame(JFrame previousFrame, Records nowyzapis){
		
		setTitle("Lists");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 580);
		setLocationRelativeTo(null);
		JPanelListsFrame = new JPanel();
		JPanelListsFrame.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(JPanelListsFrame);
		JPanelListsFrame.setLayout(new BorderLayout(0, 0));
		
		JLabel JLabelLists = new JLabel("Listy przyporzadkowan", SwingConstants.CENTER);
		JLabelLists.setFont(new Font("Yu Gothic", Font.PLAIN, 20));
		JPanelListsFrame.add(JLabelLists, BorderLayout.NORTH);
		
		JTextPane JTextFieldRecords= new JTextPane();
		JScrollPane scrollPaneRecords = new JScrollPane(JTextFieldRecords, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPaneRecords.setViewportView(JTextFieldRecords);
		JPanelListsFrame.add(scrollPaneRecords,BorderLayout.CENTER);
		
		/**
		 * Przycisk opcji, który po kliknięciu wyświetla w JTextFieldRecords studentów z kursami.
		 */
		JRadioButton JRadioButtonStudentsWithCourses = new JRadioButton("Studenci z kursami");
		JRadioButtonStudentsWithCourses.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){	
				JTextFieldRecords.setText(nowyzapis.Wyswietl_Studentow_Z_Kursami_SWING());
			}
		});
		JRadioButtonStudentsWithCourses.setSelected(true);
		JPanelListsFrame.add(JRadioButtonStudentsWithCourses, BorderLayout.EAST);
		
		/**
		 * Przycisk opcji, który po kliknięciu wyświetla w JTextFieldRecords kursy ze studentami.
		 */
		JRadioButton JRadioButtonCoursesWithStudents = new JRadioButton("Kursy ze studentami");
		JRadioButtonCoursesWithStudents.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){	
				JTextFieldRecords.setText(nowyzapis.Wyswietl_Kursy_Ze_Studentami_SWING());
			}
		});
		JPanelListsFrame.add(JRadioButtonCoursesWithStudents, BorderLayout.WEST);
		
		/**
		 * Zgrupowanie powyższych przycisków opcji.
		 */
		ButtonGroup ButtonGroupOfListsTypes = new ButtonGroup();
		ButtonGroupOfListsTypes.add(JRadioButtonStudentsWithCourses);
		ButtonGroupOfListsTypes.add(JRadioButtonCoursesWithStudents);
		
		/**
		 * Przycisk, który po kliknięciu zamyka ListFrame oraz odmraża MainFrame.
		 */
		JButton JButtonClose = new JButton("Zamknij");
		JPanelListsFrame.add(JButtonClose, BorderLayout.SOUTH);
		JButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				previousFrame.setEnabled(true);
			}
		});
		
		/**
		 * Przeciążenie słuchacza wydarzenia otwarcia okna i zamknięcia okna.
		 * Po otwarciu ListFrame w JTextPane wyświetleni zostaną studenci z kursami - domyślna lista przyporządkowań.
		 * Po zamknięciu okna następuje odmrożenie MainFrame.
		 */
		this.addWindowListener(new WindowListener(){
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				previousFrame.setEnabled(true);
			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent e) {
				JTextFieldRecords.setText(nowyzapis.Wyswietl_Studentow_Z_Kursami_SWING());
			}
		});
	}

	


	

}