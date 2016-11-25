
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JFormattedTextField;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.JSeparator;

public class cal implements ActionListener {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cal window = new cal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public cal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public static final String[] tmois = { "Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août",
			"Septembre", "Octobre", "Novembre", "Décembre" };

	calendarPane calpane;
	JComboBox mois, annee, jour;
	JButton today;
	int anneeC = 1900;
	int anneeT = 2100;

	private void initialize() {
		System.out.println("init");
		Calendar calendar = new GregorianCalendar();

		int moisr = calendar.get(Calendar.MONTH);
		int jourr = calendar.get(Calendar.DAY_OF_MONTH);
		int anneer = calendar.get(Calendar.YEAR);

		calendar.set(Calendar.DAY_OF_MONTH, 1);

		frame = new JFrame();
		frame.setBounds(100, 100, 806, 525);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		annee = new JComboBox();
		annee.setBackground(Color.WHITE);
		for (int i = anneeC; i <= anneeT; i++) {
			annee.addItem(i);
		}

		annee.setSelectedItem(anneer);
		annee.addActionListener(this);
		annee.setBounds(322, 24, 104, 20);
		frame.getContentPane().add(annee);

		mois = new JComboBox(tmois);
		mois.setBackground(Color.WHITE);
		mois.setSelectedItem(tmois[moisr]);
		mois.addActionListener(this);
		mois.setBounds(182, 24, 104, 20);
		frame.getContentPane().add(mois);

		jour = new JComboBox();
		jour.setForeground(Color.BLUE);
		jour.setBackground(Color.WHITE);
		jour.setSelectedItem(jourr);
		jour.setBounds(46, 24, 104, 20);

		metjour(anneer, moisr);
		jour.setSelectedIndex(jourr - 1);
		jour.addActionListener(this);
		frame.getContentPane().add(jour);
		calpane = new calendarPane(jourr, moisr, anneer);

		calpane.setBackground(Color.WHITE);
		calpane.setBounds(10, 93, 452, 382);
		frame.getContentPane().add(calpane);

		today = new JButton("Aujourd'hui");
		today.setBackground(Color.WHITE);
		today.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().repaint();
				frame.remove(calpane);
				calpane = new calendarPane(jourr, moisr, anneer);
				calpane.setBounds(10, 93, 452, 382);
				jour.setSelectedItem(jourr);
				mois.setSelectedItem(tmois[moisr]);
				annee.setSelectedItem(anneer);
				frame.getContentPane().add(calpane);
			}
		});
		today.setBounds(175, 55, 127, 30);
		frame.getContentPane().add(today);
		
		JPanel panel = new JPanel();
		panel.setBounds(472, 11, 308, 464);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(10, 14, 46, 14);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(93, 11, 205, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(93, 56, 149, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(252, 56, 46, 20);
		panel.add(formattedTextField);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(10, 59, 46, 14);
		panel.add(lblNewLabel_1);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(93, 87, 195, 72);
		panel.add(editorPane);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(10, 115, 46, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(136, 217, 46, 14);
		panel.add(lblNewLabel_3);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 204, 308, 2);
		panel.add(separator);
		
		JList list = new JList();
		list.setBounds(25, 242, 273, 161);
		panel.add(list);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(35, 170, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(199, 170, 89, 23);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setBounds(209, 430, 89, 23);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("New button");
		btnNewButton_3.setBounds(10, 430, 89, 23);
		panel.add(btnNewButton_3);

	}

	public final static int nbrJour[] = { 31, 28, 31, 30, /* jan feb mar apr */
			31, 30, 31, 31, /* may jun jul aug */
			30, 31, 30, 31 /* sep oct nov dec */
	};
	private JTextField textField;
	private JTextField textField_1;

	public void actionPerformed(ActionEvent evt) {
		frame.getContentPane().repaint();
		frame.remove(calpane);
		int m = mois.getSelectedIndex();
		int a = annee.getSelectedIndex() + anneeC;
		int j = jour.getSelectedIndex() + 1;

		calpane = new calendarPane(j, m, a);
		frame.getContentPane().add(calpane);
		calpane.setBounds(10, 93, 452, 382);
		if (evt.getSource() == mois || evt.getSource() == annee) {
			jour.removeAllItems();
			metjour(a, m);
			jour.setSelectedItem(j);
		}
	}

	public void metjour(int a, int m) {
		if (a % 4 == 0)
			nbrJour[1] = 29;
		else
			nbrJour[1] = 28;
		for (int i = 1; i <= nbrJour[m]; i++) {
			jour.addItem(i);

		}
	}
}
