/**
	@author
		• Denis Christopher
**/

package be.twinkle.ihm;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jdesktop.swingx.JXDatePicker;

import be.twinkle.controleur.UseCasesManagement;
import be.twinkle.domaine.Bundle;
import be.twinkle.domaine.Employee;
import be.twinkle.domaine.Function;
import be.twinkle.domaine.Skill;

@SuppressWarnings("serial")
public class ViewAddEmployee extends JPanel implements ChangeListener {

	/* ATTRIBUTES */
	private Bundle bundle;
	private UseCasesManagement useCasesManagement;
	private Model model;
	private JTextField jtfFirstname;
	private JTextField jtfLastname;
	private JTextArea jtaAddress;
	private JTextArea jtaDegrees;
	private JXDatePicker jxBirthday;
	private JXDatePicker jxHiredDate;
	private JTextField jtfPhone;
	private JComboBox<String> jcbFunction;
	private String dateFormat = "dd/MM/yyyy";
	private ArrayList<Function> functionsList;
	
	
	/* CONSTRUCTOR */
	@SuppressWarnings("unchecked")
	public ViewAddEmployee(Model model) {
		this.bundle = new Bundle();
		this.useCasesManagement = UseCasesManagement.getInstance();
		this.setBackground(Color.DARK_GRAY);
		this.setLayout(null);
		this.model = model;
		
		JLabel jlTitle = new JLabel("Add Employee");
		jlTitle.setForeground(Color.WHITE);
		jlTitle.setHorizontalAlignment(SwingConstants.CENTER);
		jlTitle.setFont(new Font("Tahoma", Font.PLAIN, 26));
		jlTitle.setBounds(10, 22, 974, 52);
		this.add(jlTitle);
		
		/* Form */
		JLabel jlFirstname = new JLabel("firstname*");
		jlFirstname.setForeground(Color.WHITE);
		jlFirstname.setFont(new Font("Tahoma", Font.PLAIN, 20));
		jlFirstname.setBounds(114, 97, 100, 32);
		this.add(jlFirstname);
		
		jtfFirstname = new JTextField("");
		jtfFirstname.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jtfFirstname.setBounds(220, 99, 250, 30);
		this.add(jtfFirstname);
		jtfFirstname.setColumns(10);
		
		JLabel jlLastname = new JLabel("lastname*");
		jlLastname.setForeground(Color.WHITE);
		jlLastname.setFont(new Font("Tahoma", Font.PLAIN, 20));
		jlLastname.setBounds(535, 97, 100, 32);
		this.add(jlLastname);
		
		jtfLastname = new JTextField();
		jtfLastname.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jtfLastname.setToolTipText("");
		jtfLastname.setColumns(10);
		jtfLastname.setBounds(645, 99, 240, 30);
		this.add(jtfLastname);
		
		JLabel jlAddress = new JLabel("address*");
		jlAddress.setForeground(Color.WHITE);
		jlAddress.setFont(new Font("Tahoma", Font.PLAIN, 20));
		jlAddress.setBounds(114, 158, 89, 32);
		this.add(jlAddress);
		
		jtaAddress = new JTextArea();
		jtaAddress.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jtaAddress.setBounds(114, 196, 770, 75);
		this.add(jtaAddress);
		
		JLabel jlDegrees = new JLabel("degrees*");
		jlDegrees.setForeground(Color.WHITE);
		jlDegrees.setFont(new Font("Tahoma", Font.PLAIN, 20));
		jlDegrees.setBounds(114, 291, 89, 32);
		this.add(jlDegrees);
		
		jtaDegrees = new JTextArea();
		jtaDegrees.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jtaDegrees.setBounds(114, 329, 770, 75);
		this.add(jtaDegrees);
		
		JLabel jlBirthday = new JLabel("birthday*");
		jlBirthday.setForeground(Color.WHITE);
		jlBirthday.setFont(new Font("Tahoma", Font.PLAIN, 20));
		jlBirthday.setBounds(114, 435, 89, 32);
		this.add(jlBirthday);
		
		jxBirthday = new JXDatePicker();
		jxBirthday.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jxBirthday.setBounds(213, 437, 250, 30);
		jxBirthday.setFormats(this.dateFormat);
		this.add(jxBirthday);
		
		JLabel jlHiredDate = new JLabel("hired date*");
		jlHiredDate.setForeground(Color.WHITE);
		jlHiredDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		jlHiredDate.setBounds(535, 435, 110, 32);
		this.add(jlHiredDate);

		jxHiredDate = new JXDatePicker();
		jxHiredDate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jxHiredDate.setBounds(650, 437, 234, 30);
		jxHiredDate.setFormats(this.dateFormat);
		this.add(jxHiredDate);
		
		JLabel jlPhone = new JLabel("phone*");
		jlPhone.setForeground(Color.WHITE);
		jlPhone.setFont(new Font("Tahoma", Font.PLAIN, 20));
		jlPhone.setBounds(114, 494, 89, 32);
		this.add(jlPhone);
		
		jtfPhone = new JTextField();
		jtfPhone.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jtfPhone.setColumns(10);
		jtfPhone.setBounds(213, 496, 250, 30);
		this.add(jtfPhone);
		
		JLabel jlInfoPhone = new JLabel("Format (X)XXX/XX.XX.XX");
		jlInfoPhone.setForeground(Color.WHITE);
		jlInfoPhone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jlInfoPhone.setBounds(213, 520, 200, 32);
		this.add(jlInfoPhone);
		
		
		JLabel jlFunction = new JLabel("function*");
		jlFunction.setForeground(Color.WHITE);
		jlFunction.setFont(new Font("Tahoma", Font.PLAIN, 20));
		jlFunction.setBounds(535, 494, 89, 32);
		this.add(jlFunction);
		
		this.useCasesManagement.listFunctions(bundle);
		functionsList = (ArrayList<Function>) this.bundle.get(Bundle.LISTFUNCTIONS);
		jcbFunction = new JComboBox<String>();
		jcbFunction.addItem("Select a function");
		for (Function function: functionsList) {
			jcbFunction.addItem(function.getDescription());
		}
		jcbFunction.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jcbFunction.setBounds(650, 494, 234, 32);
		this.add(jcbFunction);
		
		JButton jbReset = new JButton("Reset");
		jbReset.setFont(new Font("Tahoma", Font.PLAIN, 20));
		jbReset.setBounds(618, 564, 112, 32);
		this.add(jbReset);
		
		JButton jbCancel = new JButton("Cancel");
		jbCancel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		jbCancel.setBounds(745, 564, 112, 32);
		this.add(jbCancel);
		
		JButton jbConfirm = new JButton("Confirm");
		jbConfirm.setFont(new Font("Tahoma", Font.PLAIN, 20));
		jbConfirm.setBounds(872, 564, 112, 32);
		this.add(jbConfirm);
		
		
		/* Listeners */
		jbReset.addActionListener(e -> {
			if(JOptionPane.showConfirmDialog(this, "Are you sure you want to reset all fields?") == 0) {
				resetAllFields();
			}
		});
		
		jbCancel.addActionListener(e -> {
			model.changeCurrentView("ViewHome");
		});
		
		jbConfirm.addActionListener(e -> {
			if(addEmployee()) {
				JOptionPane.showMessageDialog(this, (String) this.bundle.get(Bundle.MESSAGE), "Add Employee", JOptionPane.INFORMATION_MESSAGE);
				resetAllFields();
				model.changeCurrentView("ViewHome");
			}
			else {
				JOptionPane.showMessageDialog(this, (String) this.bundle.get(Bundle.MESSAGE), "Add Employee", JOptionPane.WARNING_MESSAGE);
			}
		});
	}
	
	
	/* METHODS */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// not used
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		// not used
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
	
	public void resetAllFields() {
		jtfFirstname.setText("");
		jtfLastname.setText("");
		jtaAddress.setText("");
		jtaDegrees.setText("");
		jxBirthday.setDate(null);
		jxHiredDate.setDate(null);
		jtfPhone.setText("");
		jcbFunction.setSelectedIndex(0);
	}
	
	@SuppressWarnings("unchecked")
	private boolean addEmployee() {	
		/* Get all fields */
		DateFormat dateFormat = new SimpleDateFormat(this.dateFormat);
		String firstname = this.jtfFirstname.getText();
		String lastname = this.jtfLastname.getText();
		String address = this.jtaAddress.getText();
		String degrees = this.jtaDegrees.getText();
		String birthday = null;
		if(this.jxBirthday.getDate() != null) {
			birthday = dateFormat.format(this.jxBirthday.getDate());
		}
		String hiredDate = null;
		if(this.jxHiredDate.getDate() != null) {
			hiredDate = dateFormat.format(this.jxHiredDate.getDate());
		}
		String phone = this.jtfPhone.getText();
		Function function = null;
		boolean found = false;
		int i = 0;
		while(i < this.functionsList.size() && !found) {
			if(this.functionsList.get(i).getDescription() == this.jcbFunction.getSelectedItem()) {
				function = this.functionsList.get(i);
			}
			i++;
		}
		
		/* Objects creation */
		this.useCasesManagement.listSkills(bundle);
		ArrayList<Skill> skillsList = (ArrayList<Skill>) this.bundle.get(Bundle.LISTSKILLS);
		Employee employee = new Employee(0, firstname, lastname, address, phone, birthday, hiredDate, degrees, skillsList, function);
		
		/* Process */
		this.bundle.put(Bundle.EMPLOYEE, employee);
		this.useCasesManagement.addEmployee(bundle);
		this.model.setBundle(bundle);
		
		return (boolean) bundle.get(Bundle.SUCCESS);
	}
	
}
