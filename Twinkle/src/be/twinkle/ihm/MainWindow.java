/**
	@author
		• Denis Christopher
**/

package be.twinkle.ihm;

import java.awt.BorderLayout;import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class MainWindow extends JFrame implements ChangeListener {

	/* ATTRIBUTES */
	private JMenu jmHome;
	private JMenuItem jmiAdd;
	private JMenuItem jmiList;
	private JMenuItem jmiSearch;
	private JMenu jmRate;
	private Model model;
	private BorderLayout layout;
	
	
	/* CONSTRUCTOR */
	public MainWindow(String title, Model model) {
		super(title);
		this.model = model;
		
		this.setIconImage(new ImageIcon("res/star.png").getImage());
		this.setResizable(false);
		this.setBounds(100, 100, 1000, 667);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout(0, 0));
		this.layout = (BorderLayout) this.getContentPane().getLayout();
		
		/* Menu */
		JMenuBar jmbMenu = new JMenuBar();
		this.getContentPane().add(jmbMenu, BorderLayout.NORTH);
		
		jmHome = new JMenu("Home");
		jmbMenu.add(jmHome);
		
		JMenu jmEmployees = new JMenu("Employees");
		jmbMenu.add(jmEmployees);
		
		jmiAdd = new JMenuItem("Add");
		jmEmployees.add(jmiAdd);
		jmEmployees.addSeparator();
		
		jmiList = new JMenuItem("List");
		jmEmployees.add(jmiList);
		jmEmployees.addSeparator();
		
		jmiSearch = new JMenuItem("Search");
		jmEmployees.add(jmiSearch);
		
		jmRate = new JMenu("Rate");
		jmbMenu.add(jmRate);
		
		/* Listeners */
		jmHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mouseReleased(e);
				model.changeCurrentView("ViewHome");
				jmHome.setSelected(false);
			}
		});
		
		jmiAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mouseReleased(e);
				model.changeCurrentView("ViewAddEmployee");
				jmiAdd.setSelected(false);
			}
		});
		
		jmiList.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mouseReleased(e);
				model.changeCurrentView("ViewListEmployees");
				jmiList.setSelected(false);
			}
		});
		
		jmiSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mouseReleased(e);
				model.changeCurrentView("ViewSearch");
				jmiList.setSelected(false);
			}
		});
		
		jmRate.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mouseReleased(e);
				model.changeCurrentView("ViewRates");
				jmRate.setSelected(false);
			}
		});
	}
	
	
	/* METHODS */
	@Override
	public void stateChanged(ChangeEvent e) {
		
		if(layout.getLayoutComponent(BorderLayout.CENTER) != null)
			this.remove(layout.getLayoutComponent(BorderLayout.CENTER));
		this.repaint();
		this.add((Component) this.model.getCurrentView(), BorderLayout.CENTER);
		this.revalidate();
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
	
}
