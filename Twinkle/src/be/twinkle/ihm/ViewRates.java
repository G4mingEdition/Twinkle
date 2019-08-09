/**
	@author
		• Genco Luca
**/

package be.twinkle.ihm;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import be.twinkle.controleur.UseCasesManagement;
import be.twinkle.domaine.Bundle;
import be.twinkle.domaine.Employee;
import be.twinkle.domaine.Skill;
import be.twinkle.utils.FilterComboBox;

@SuppressWarnings("serial")
public class ViewRates extends JPanel implements ChangeListener {

	/* ATTRIBUTES */
	private Bundle bundle;
	private UseCasesManagement useCasesManagement;
	private Model model;
	private FilterComboBox jcbListEmployee;
	private String itemSelected;
	private ArrayList<Employee> employeeList;
	private List<JSlider> listSlider = new ArrayList<JSlider>();
	private List<JSpinner> listSpinner = new ArrayList<JSpinner>();
	private List<JLabel> listLabel = new ArrayList<JLabel>();
	private List<JButton> listButton = new ArrayList<JButton>();
	private List<JLabel> listNoRates = new ArrayList<JLabel>();
	private Employee employee;
	
	
	/* CONSTRUCTOR */
	public ViewRates(Model model) {
		this.setBackground(Color.DARK_GRAY);
		this.setLayout(null);
		this.bundle = new Bundle();
		this.useCasesManagement = UseCasesManagement.getInstance();
		this.model = model;
		SpinnerNumberModel spinnerNumberModel1 = new SpinnerNumberModel(0, 0, 10, 1);
		SpinnerNumberModel spinnerNumberModel2 = new SpinnerNumberModel(0, 0, 10, 1);
		SpinnerNumberModel spinnerNumberModel3 = new SpinnerNumberModel(0, 0, 10, 1);
		SpinnerNumberModel spinnerNumberModel4 = new SpinnerNumberModel(0, 0, 10, 1);
		
		JLabel jlTitle = new JLabel("Rate Employees");
		jlTitle.setForeground(Color.WHITE);
		jlTitle.setHorizontalAlignment(SwingConstants.CENTER);
		jlTitle.setFont(new Font("Tahoma", Font.PLAIN, 26));
		jlTitle.setBounds(10, 22, 974, 52);
		this.add(jlTitle);
	
		jcbListEmployee = new FilterComboBox(new ArrayList<String>());
		jcbListEmployee.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jcbListEmployee.setBounds(139, 159, 200, 30);
		this.add(jcbListEmployee);
			
		JLabel jlListEmployee = new JLabel("Employees");
		jlListEmployee.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jlListEmployee.setForeground(Color.WHITE);
		jlListEmployee.setBounds(139, 124, 200, 30);
		this.add(jlListEmployee);
		
		JLabel jlSkill = new JLabel("Skill 1");
		jlSkill.setForeground(Color.WHITE);
		jlSkill.setFont(new Font("Tahoma", Font.BOLD, 15));
		jlSkill.setBounds(139, 255, 150, 50);
		this.add(jlSkill);
		
		JLabel jlNoRates1 = new JLabel("Skill not evalued");
		jlNoRates1.setForeground(Color.RED);
		jlNoRates1.setFont(new Font("Tahoma", Font.BOLD, 12));
		jlNoRates1.setBounds(139, 280, 150, 50);
		this.add(jlNoRates1);
		
		JSpinner jspSkill1 = new JSpinner(spinnerNumberModel1);
		jspSkill1.setBounds(310, 273, 29, 20);
		this.add(jspSkill1);
		
		JButton jbSkill1 = new JButton("Apply");
		jbSkill1.setBounds(139, 346, 90, 20);
		this.add(jbSkill1);
		
		JSlider jsSkill1 = new JSlider(0, 10);
		jsSkill1.setEnabled(false);
		jsSkill1.setBackground(Color.DARK_GRAY);
		jsSkill1.setBounds(139, 306, 200, 26);
		this.add(jsSkill1);
		
		JLabel jtSkill2 = new JLabel("Skill 2");
		jtSkill2.setForeground(Color.WHITE);
		jtSkill2.setFont(new Font("Tahoma", Font.BOLD, 15));
		jtSkill2.setBounds(139, 410, 150, 50);
		this.add(jtSkill2);
		
		JLabel jlNoRates2 = new JLabel("Skill not evalued");
		jlNoRates2.setForeground(Color.RED);
		jlNoRates2.setFont(new Font("Tahoma", Font.BOLD, 12));
		jlNoRates2.setBounds(139, 430, 150, 50);
		this.add(jlNoRates2);
		
		JButton jbSkill2 = new JButton("Apply");
		jbSkill2.setBounds(139, 501, 90, 20);
		this.add(jbSkill2);
		
		JSlider jsSkill2 = new JSlider(0, 10);
		jsSkill2.setEnabled(false);
		jsSkill2.setBackground(Color.DARK_GRAY);
		jsSkill2.setBounds(139, 461, 200, 26);
		this.add(jsSkill2);
		
		JSpinner jspSkill2 = new JSpinner(spinnerNumberModel2);
		jspSkill2.setBounds(310, 428, 29, 20);
		this.add(jspSkill2);
		
		JLabel jlSkill3 = new JLabel("Skill 3");
		jlSkill3.setForeground(Color.WHITE);
		jlSkill3.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlSkill3.setBounds(608, 255, 200, 50);
		this.add(jlSkill3);
		
		JLabel jlNoRates3 = new JLabel("Skill not evalued");
		jlNoRates3.setForeground(Color.RED);
		jlNoRates3.setFont(new Font("Tahoma", Font.BOLD, 12));
		jlNoRates3.setBounds(608, 280, 150, 50);
		this.add(jlNoRates3);
		
		JSpinner jspSkill3 = new JSpinner(spinnerNumberModel3);
		jspSkill3.setBounds(779, 273, 29, 20);
		this.add(jspSkill3);
		
		JButton jbSkill3 = new JButton("Apply");
		jbSkill3.setBounds(608, 346, 90, 20);
		this.add(jbSkill3);
		
		JSlider jsSkill3 = new JSlider(0, 10);
		jsSkill3.setEnabled(false);
		jsSkill3.setBackground(Color.DARK_GRAY);
		jsSkill3.setBounds(608, 306, 200, 26);
		this.add(jsSkill3);
		
		JLabel jlSkill4 = new JLabel("Skill 4");
		jlSkill4.setForeground(Color.WHITE);
		jlSkill4.setFont(new Font("Tahoma", Font.BOLD, 15));
		jlSkill4.setBounds(608, 410, 180, 50);
		this.add(jlSkill4);
		
		JLabel jlNoRates4 = new JLabel("Skill not evalued");
		jlNoRates4.setForeground(Color.RED);
		jlNoRates4.setFont(new Font("Tahoma", Font.BOLD, 12));
		jlNoRates4.setBounds(608, 430, 150, 50);
		this.add(jlNoRates4);
		
		JButton jbSkill4 = new JButton("Apply");
		jbSkill4.setBounds(608, 501, 90, 20);
		this.add(jbSkill4);
		
		JSlider jsSkill4 = new JSlider(0, 10);
		jsSkill4.setEnabled(false);
		jsSkill4.setBackground(Color.DARK_GRAY);
		jsSkill4.setBounds(608, 461, 200, 26);
		this.add(jsSkill4);
		
		JSpinner jspSkill4 = new JSpinner(spinnerNumberModel4);
		jspSkill4.setBounds(779, 428, 29, 20);
		this.add(jspSkill4);
		
		listLabel.add(jlSkill);
		listLabel.add(jtSkill2);
		listLabel.add(jlSkill3);
		listLabel.add(jlSkill4);
		
		listSpinner.add(jspSkill1);
		listSpinner.add(jspSkill2);
		listSpinner.add(jspSkill3);
		listSpinner.add(jspSkill4);
		
		listSlider.add(jsSkill1);
		listSlider.add(jsSkill2);
		listSlider.add(jsSkill3);
		listSlider.add(jsSkill4);
		
		listButton.add(jbSkill1);
		listButton.add(jbSkill2);
		listButton.add(jbSkill3);
		listButton.add(jbSkill4);
		
		listNoRates.add(jlNoRates1);
		listNoRates.add(jlNoRates2);
		listNoRates.add(jlNoRates3);
		listNoRates.add(jlNoRates4);

		hideAll(listSlider, listLabel, listSpinner, listButton, listNoRates);

		
		/*LISENERS*/
		jcbListEmployee.addActionListener((e)->{
			if(jcbListEmployee.getSelectedItem() != null) {
				if(jcbListEmployee.getSelectedItem().toString().contains(" ")) {
					if(jcbListEmployee.getSelectedItem().toString().contains("~")) {
						itemSelected = jcbListEmployee.getSelectedItem().toString();
						int id = Integer.parseInt(itemSelected.split(" ")[0]);
						
						int i = 0;
						boolean found = false;
						while(i < this.employeeList.size() && !found) {
							if(this.employeeList.get(i).getId() == id) {
								found = true;
								employee = this.employeeList.get(i);
								
								// DISPLAY
								List<Skill> listSkill = employee.getSkills();
								jlSkill.setText(listSkill.get(0).getName());
								jtSkill2.setText(listSkill.get(1).getName());
								jlSkill3.setText(listSkill.get(2).getName());
								jlSkill4.setText(listSkill.get(3).getName());
								
								int j = 0;
								for (Skill skill : listSkill) {
									listLabel.get(j).setVisible(true);
									listSpinner.get(j).setVisible(true);
									listButton.get(j).setVisible(true);
									if(skill.getRate() >= 0) {
										listSpinner.get(j).setValue(skill.getRate());
										showNoRates(j, listNoRates, false);
										showSlider(j, listSlider, true);
									}else{
										listSpinner.get(j).setValue(0);
										showNoRates(j, listNoRates, true);
										showSlider(j, listSlider, false);
									}
									j++;
									
								}
							}
							i++;
						}
					}
					else {
						hideAll(listSlider, listLabel, listSpinner, listButton, listNoRates);
					}
				}
			}
		});
		
		jspSkill1.addChangeListener((e)->{
			jsSkill1.setValue((Integer)jspSkill1.getValue());
		});
		
		jspSkill2.addChangeListener((e)->{
			jsSkill2.setValue((Integer)jspSkill2.getValue());
		});
		
		jspSkill3.addChangeListener((e)->{
			jsSkill3.setValue((Integer)jspSkill3.getValue());
		});
		
		jspSkill4.addChangeListener((e)->{
			jsSkill4.setValue((Integer)jspSkill4.getValue());
		});
		
		jbSkill1.addActionListener((e)->{
			showSlider(0, listSlider, true);
			showNoRates(0, listNoRates, false);
			jsSkill1.setValue((Integer)jspSkill1.getValue());
			employee.getSkills().get(0).setRate((Integer)jspSkill1.getValue());
			/* Process */
			this.bundle.put(Bundle.EMPLOYEE, employee);
			this.useCasesManagement.rateEmployee(bundle);
			this.model.setBundle(bundle);
			//RETURN 
			if((boolean)bundle.get(Bundle.SUCCESS)) {
				JOptionPane.showMessageDialog(this, (String) this.bundle.get(Bundle.MESSAGE), "Rate Employee Skill", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(this, (String) this.bundle.get(Bundle.MESSAGE), "Rate Employee Skill", JOptionPane.WARNING_MESSAGE);
			}
		});
		
		jbSkill2.addActionListener((e)->{
			showSlider(1, listSlider, true);
			showNoRates(1, listNoRates, false);
			jsSkill2.setValue((Integer)jspSkill2.getValue());
			employee.getSkills().get(1).setRate((Integer)jspSkill2.getValue());
			/* Process */
			this.bundle.put(Bundle.EMPLOYEE, employee);
			this.useCasesManagement.rateEmployee(bundle);
			this.model.setBundle(bundle);
			//RETURN 
			if((boolean)bundle.get(Bundle.SUCCESS)) {
				JOptionPane.showMessageDialog(this, (String) this.bundle.get(Bundle.MESSAGE), "Rate Employee Skill", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(this, (String) this.bundle.get(Bundle.MESSAGE), "Rate Employee Skill", JOptionPane.WARNING_MESSAGE);
			}
		});
		
		jbSkill3.addActionListener((e)->{
			showSlider(2, listSlider, true);
			showNoRates(2, listNoRates, false);
			jsSkill3.setValue((Integer)jspSkill3.getValue());
			employee.getSkills().get(2).setRate((Integer)jspSkill3.getValue());
			/* Process */
			this.bundle.put(Bundle.EMPLOYEE, employee);
			this.useCasesManagement.rateEmployee(bundle);
			this.model.setBundle(bundle);
			//RETURN 
			if((boolean)bundle.get(Bundle.SUCCESS)) {
				JOptionPane.showMessageDialog(this, (String) this.bundle.get(Bundle.MESSAGE), "Rate Employee Skill", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(this, (String) this.bundle.get(Bundle.MESSAGE), "Rate Employee Skill", JOptionPane.WARNING_MESSAGE);
			}
		});
		
		jbSkill4.addActionListener((e)->{
			showSlider(3, listSlider, true);
			showNoRates(3, listNoRates, false);
			jsSkill4.setValue((Integer)jspSkill4.getValue());
			employee.getSkills().get(3).setRate((Integer)jspSkill4.getValue());
			/* Process */
			this.bundle.put(Bundle.EMPLOYEE, employee);
			this.useCasesManagement.rateEmployee(bundle);
			this.model.setBundle(bundle);
			//RETURN 
			if((boolean)bundle.get(Bundle.SUCCESS)) {
				JOptionPane.showMessageDialog(this, (String) this.bundle.get(Bundle.MESSAGE), "Rate Employee Skill", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(this, (String) this.bundle.get(Bundle.MESSAGE), "Rate Employee Skill", JOptionPane.WARNING_MESSAGE);
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
		if(this.model.getCurrentView() instanceof ViewRates) {
			majList();
		}
	}
	
	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
	
	public void hideAll(List<JSlider> listSlider, List<JLabel> listLabel, List<JSpinner> listSpinner, List<JButton> listButton, List<JLabel> listNoRates) {
		for(JSlider slider : listSlider) {
			slider.setVisible(false);
		}
		for(JLabel label : listLabel) {
			label.setVisible(false);
		}
		for(JSpinner spinner : listSpinner) {
			spinner.setVisible(false);
		}
		for(JButton button : listButton) {
			button.setVisible(false);
		}
		for(JLabel label : listNoRates) {
			label.setVisible(false);
		}
	}
	
	public void showNoRates(int index, List<JLabel> listNoRates, boolean show) {
		listNoRates.get(index).setVisible(show);
	}
	
	public void showSpinner(int index, List<JSpinner> listSpinner, boolean show) {
		listSpinner.get(index).setVisible(show);
	}
	
	public void showSlider(int index, List<JSlider> listSlider, boolean show) {
		listSlider.get(index).setVisible(show);
	}
	
	public void showButton(int index, List<JButton> listButton, boolean show) {
		listButton.get(index).setVisible(show);
	}
	
	@SuppressWarnings("unchecked")
	private void majList() {
		jcbListEmployee.removeAllItems();
		jcbListEmployee.getArray().clear();
		this.useCasesManagement.listEmployees(bundle);
		employeeList = (ArrayList<Employee>) this.bundle.get(Bundle.LISTEMPLOYEES);
		jcbListEmployee.getArray().add("Select an employee");
		jcbListEmployee.addItem("Select an employee");
		for (Employee employee : employeeList) {
			jcbListEmployee.getArray().add(employee.getId() + " ~ " + employee.getLastname() + " " + employee.getFirstname());
			jcbListEmployee.addItem(employee.getId() + " ~ " + employee.getLastname() + " " + employee.getFirstname());
		}
	}
}
