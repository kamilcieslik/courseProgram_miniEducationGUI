import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JComboBox;

public class AddCourseFrame extends JFrame {

	private static final long serialVersionUID = 981827316073992168L;
	private JPanel JPanelAddCourseFrame;

	private JTextField JTextFieldName;
	JButton JButtonClose;
	String[] messageStrings = {"10","9","8","7","6","5","4","3","2","1"};
	JComboBox<String> JComboBoxAmountOfECTS = new JComboBox<String>(messageStrings);
	
	public AddCourseFrame(JFrame previousFrame, JList<Course> JListCourses, Records nowyzapis) {
		
		setTitle("CreatorOfCourses");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 421, 335);
		setLocationRelativeTo(null);
		JPanelAddCourseFrame = new JPanel();
		JPanelAddCourseFrame.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(JPanelAddCourseFrame);
		JPanelAddCourseFrame.setLayout(null);
		
		/**
		 * Przycisk, który po kliknięciu wywołuje metodę odpowiadającą za dodanie do ListyKursów nowy kurs.
		 * W metodzie tej (nowyzapis.Dodaj_Kurs_SWING()) sprawdzana jest poprawność wprowadzanych do JTextFieldów
		 * danych.
		 * Po dodaniu następuje odświeżenie JListCourses w MainFrame, zamknięcie obecnego AddCourseFrame, oraz
		 * odmrożenie MainFrame.
		 */
		JButton JButtonAddCourse = new JButton("Dodaj kurs");
		JButtonAddCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int amountOfCoursesBefore = nowyzapis.getListaKursow().size();
				nowyzapis.Dodaj_Kurs_SWING(JTextFieldName.getText(), JComboBoxAmountOfECTS.getSelectedItem().toString());
				int amountOfCoursesAfter = nowyzapis.getListaKursow().size();
				if (amountOfCoursesAfter>amountOfCoursesBefore) 
				{
					AddCourseFrame.this.dispose();
					previousFrame.setEnabled(true);
					DefaultListModel<Course> defaultListModelCourses = new DefaultListModel<Course>();
					for (int i=0; i< nowyzapis.getListaKursow().size();i++)
					defaultListModelCourses.addElement(nowyzapis.getListaKursow().get(i));
					JListCourses.setModel(defaultListModelCourses);
				}
			}
		});
		JButtonAddCourse.setBounds(30, 236, 152, 23);
		JPanelAddCourseFrame.add(JButtonAddCourse);

		JTextFieldName = new JTextField("");
		JTextFieldName.setColumns(10);
		JTextFieldName.setBounds(203, 121, 171, 20);
		JPanelAddCourseFrame.add(JTextFieldName);
		
		JLabel JLabelData = new JLabel("Podaj dane:");
		JLabelData.setFont(new Font("Yu Gothic", Font.PLAIN, 36));
		JLabelData.setBounds(30, 28, 287, 50);
		JPanelAddCourseFrame.add(JLabelData);
		

		
		JLabel JLabelName = new JLabel("Nazwa");
		JLabelName.setFont(new Font("Yu Gothic", Font.PLAIN, 14));
		JLabelName.setBounds(30, 123, 52, 14);
		JPanelAddCourseFrame.add(JLabelName);
		
		JLabel JLabelECTS = new JLabel("ECTS");
		JLabelECTS.setFont(new Font("Yu Gothic", Font.PLAIN, 14));
		JLabelECTS.setBounds(30, 152, 86, 14);
		JPanelAddCourseFrame.add(JLabelECTS);

		/**
		 * Przycisk, który po kliknięciu zamyka AddCourseFrame oraz odmraża MainFrame.
		 */
		JButton JButtonClose = new JButton("Anuluj");
		JButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddCourseFrame.this.dispose();
				previousFrame.setEnabled(true);
			}
		});
		JButtonClose.setBounds(222, 236, 152, 23);
		JPanelAddCourseFrame.add(JButtonClose);
		
		JComboBoxAmountOfECTS.setBounds(203, 152, 171, 20);
		JPanelAddCourseFrame.add(JComboBoxAmountOfECTS);
		
		/**
		 * Przeciążenie słuchacza wydarzenia zamknięcia okna.
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
				// TODO Auto-generated method stub
			}
		});
	}
}