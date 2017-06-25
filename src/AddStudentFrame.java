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


public class AddStudentFrame extends JFrame {

	private static final long serialVersionUID = 981827316073992168L;
	private JPanel JPanelAddStudentFrame;
	private JTextField JTextFieldIndex;
	private JTextField JTextFieldName;
	private JTextField JTextFieldSurname;
	JButton JButtonClose;
	
	String[] messageStrings = {"2016","2015","2014","2013","2012","2011","2010","2009","2008","2007","2006","2005","2004","2003","2002","2001","2000","1999","1998","1997","1996","1995"};
	JComboBox<String> JComboBoxYearOfTheBeginning = new JComboBox<String>(messageStrings);

	
	public AddStudentFrame(JFrame previousFrame, JList<Student> JListStudents, Records nowyzapis) {
		
		setTitle("CreatorOfStudents");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 421, 400);
		setLocationRelativeTo(null);
		JPanelAddStudentFrame = new JPanel();
		JPanelAddStudentFrame.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(JPanelAddStudentFrame);
		JPanelAddStudentFrame.setLayout(null);
		
		/**
		 * Przycisk, który po kliknięciu wywołuje metodę odpowiadającą za dodanie do ListyStudentów nowego studenta.
		 * W metodzie tej (nowyzapis.Dodaj_Studenta_SWING()) sprawdzana jest poprawność wprowadzanych do JTextFieldów
		 * danych.
		 * Po dodaniu następuje odświeżenie JListStudents w MainFrame, zamknięcie obecnego AddStudentFrame, oraz
		 * odmrożenie MainFrame.
		 */
		JButton JButtonAddStudent = new JButton("Dodaj studenta");
		JButtonAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int amountOfStudentsBefore = nowyzapis.getListaStudentow().size();
				nowyzapis.Dodaj_Studenta_SWING(JTextFieldIndex.getText(), JTextFieldName.getText(), JTextFieldSurname.getText(), JComboBoxYearOfTheBeginning.getSelectedItem().toString());
				int amountOfStudentsAfter = nowyzapis.getListaStudentow().size();
				if (amountOfStudentsAfter>amountOfStudentsBefore) 
				{
					AddStudentFrame.this.dispose();
					previousFrame.setEnabled(true);
					DefaultListModel<Student> defaultListModelStudents = new DefaultListModel<Student>();
					for (int i=0; i< nowyzapis.getListaStudentow().size();i++)
					defaultListModelStudents.addElement(nowyzapis.getListaStudentow().get(i));
					JListStudents.setModel(defaultListModelStudents);
				}
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
		
		/**
		 * Przycisk, który po kliknięciu zamyka AddStudentFrame oraz odmraża MainFrame.
		 */
		JButton JButtonClose = new JButton("Anuluj");
		JButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddStudentFrame.this.dispose();
				previousFrame.setEnabled(true);
			}
		});
		JButtonClose.setBounds(222, 306, 152, 23);
		JPanelAddStudentFrame.add(JButtonClose);
		
		JComboBoxYearOfTheBeginning.setBounds(203, 203, 171, 20);
		JPanelAddStudentFrame.add(JComboBoxYearOfTheBeginning);
		
		/**
		 * Przeciążenie słuchacza wydarzenia zamknięcia okna.
		 * Po zamknęciu okna następuje odmrożenie MainFrame.
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
