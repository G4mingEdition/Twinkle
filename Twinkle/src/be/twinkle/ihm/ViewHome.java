/**
	@author
		• Denis Christopher
**/

package be.twinkle.ihm;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class ViewHome extends JPanel implements ChangeListener {

	/* ATTRIBUTES */
	private Model model;
	
	
	/* CONSTRUCTOR */
	public ViewHome(Model model) {
		this.setBackground(Color.DARK_GRAY);
		this.setLayout(null);
		this.model = model;
		
		JLabel jlSubtitle = new JLabel("The new HR technology");
		jlSubtitle.setForeground(Color.WHITE);
		jlSubtitle.setHorizontalAlignment(SwingConstants.CENTER);
		jlSubtitle.setFont(new Font("Tahoma", Font.BOLD, 36));
		jlSubtitle.setBounds(10, 261, 974, 58);
		this.add(jlSubtitle);
		
		JLabel label = new JLabel("TWINKLE");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, 59));
		label.setBounds(10, 201, 974, 58);
		this.add(label);
		
		/* Menu */
		Border emptyBorder = BorderFactory.createEmptyBorder();
		
		JButton jbAdd = new JButton(new ImageIcon("res/addEmployee.png"));
		jbAdd.setBackground(Color.DARK_GRAY);
		jbAdd.setBorder(emptyBorder);
		jbAdd.setBounds(226, 330, 89, 89);
		jbAdd.setFocusable(false);
		this.add(jbAdd);
		
		JButton jbList = new JButton(new ImageIcon("res/listEmployees.png"));
		jbList.setBackground(Color.DARK_GRAY);
		jbList.setBorder(emptyBorder);
		jbList.setBounds(376, 330, 89, 89);
		jbList.setFocusable(false);
		this.add(jbList);
		
		JButton jbSearch = new JButton(new ImageIcon("res/searchEmployees.png"));
		jbSearch.setBackground(Color.DARK_GRAY);
		jbSearch.setBorder(emptyBorder);
		jbSearch.setBounds(526, 330, 89, 89);
		jbSearch.setFocusable(false);
		this.add(jbSearch);
		
		JButton jbRate = new JButton(new ImageIcon("res/rate.png"));
		jbRate.setBackground(Color.DARK_GRAY);
		jbRate.setBorder(emptyBorder);
		jbRate.setBounds(676, 330, 89, 89);
		jbRate.setFocusable(false);
		this.add(jbRate);
		
		
		/* Listeners */
		jbAdd.addActionListener(e -> {
			model.changeCurrentView("ViewAddEmployee");
		});
		
		jbList.addActionListener(e -> {
			model.changeCurrentView("ViewListEmployees");
		});
		
		jbSearch.addActionListener(e -> {
			model.changeCurrentView("ViewSearch");
		});
		
		jbRate.addActionListener(e -> {
			model.changeCurrentView("ViewRates");
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
	
}
