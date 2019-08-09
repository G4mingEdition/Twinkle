/**
	@author
		• Prevot Sean
**/

package be.twinkle.ihm;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import be.twinkle.controleur.UseCasesManagement;
import be.twinkle.domaine.Bundle;
import be.twinkle.domaine.Employee;

@SuppressWarnings("serial")
public class ViewListEmployees extends JPanel implements ChangeListener{
	
	private static final String[] columnNames = { "Firstname", "Lastname", "Phone", "Function", "Adress", "Degree(s)", "Birthday", "Hired Date"};
	private Model model;
	private Bundle bundle;
	private UseCasesManagement usesCasesManagement;
	private JTable jtEmployee;
	
	
	public ViewListEmployees(Model model) {
		super();
		this.model = model;
		bundle = model.getBundle();
		this.usesCasesManagement = UseCasesManagement.getInstance();
		this.setBackground(Color.DARK_GRAY);
		this.setLayout(null);
		
		JLabel jlTitle = new JLabel("List Employees");
		jlTitle.setForeground(Color.WHITE);
		jlTitle.setHorizontalAlignment(SwingConstants.CENTER);
		jlTitle.setFont(new Font("Tahoma", Font.PLAIN, 26));
		jlTitle.setBounds(10, 22, 974, 52);
		this.add(jlTitle);
		
		jtEmployee = new JTable();
		jtEmployee.setCellSelectionEnabled(false);
		jtEmployee.setDefaultEditor(Object.class, null);
		JScrollPane jspTable = new JScrollPane(jtEmployee);
		jspTable.setBounds(65, 115, 855, 440);
		this.add(jspTable);
		jtEmployee.setAutoCreateRowSorter(true);
		
		jtEmployee.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent evt) {
		        int row = jtEmployee.rowAtPoint(evt.getPoint());
		        int column = jtEmployee.columnAtPoint(evt.getPoint());
		        if (row >= 0 && column >= 0) {
		        	JOptionPane.showMessageDialog(getParent(), jtEmployee.getValueAt(row, column), "Employee information", JOptionPane.INFORMATION_MESSAGE);
		        }
		    }
		});
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		if(this.model.getCurrentView() instanceof ViewListEmployees) {
			majList();
		}
	}
	
	private void majList() {
		usesCasesManagement.listEmployees(bundle);
		if((boolean) bundle.get(Bundle.SUCCESS)) {
			@SuppressWarnings("unchecked")
			List<Employee> listEmployee = (List<Employee>) bundle.get(Bundle.LISTEMPLOYEES);
			String[][] data = new String[listEmployee.size()][columnNames.length];
			
			for(int i = 0; i < listEmployee.size(); i++) {
				Employee e = listEmployee.get(i);
				data[i][0] = e.getFirstname();
				data[i][1] = e.getLastname();
				data[i][2] = e.getPhone();
				data[i][3] = e.getFunction().getDescription();
				data[i][4] = e.getAddress();
				data[i][5] = e.getDegrees();
				data[i][6] = e.getBirthday();
				data[i][7] = e.getHiredDate();
			}
		this.jtEmployee.setModel(new DefaultTableModel(data, columnNames));
		}
	}
}
