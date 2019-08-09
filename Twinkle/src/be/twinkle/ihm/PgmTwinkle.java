/**
	@author
		• Denis Christopher
		• Prevot Sean
		• Genco Luca
**/

package be.twinkle.ihm;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class PgmTwinkle {
	
	public static void main(String[] args) {		
		
		/* Thread */
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					/* Initialization */
					Model model = new Model();
					MainWindow mwWindow = new MainWindow("Twinkle", model);
					ViewHome vhHome = new ViewHome(model);
					ViewAddEmployee vaeAddEmployee = new ViewAddEmployee(model);
					ViewListEmployees vleListeEmployees = new ViewListEmployees(model);
					ViewSearch vsViewSearch = new ViewSearch(model);
					ViewRates vrRates = new ViewRates(model);
					
					/* Add all views */
					model.addView(mwWindow);
					model.addView(vhHome);
					model.addView(vaeAddEmployee);
					model.addView(vleListeEmployees);
					model.addView(vsViewSearch);
					model.addView(vrRates);
					
					/* Default view */
					mwWindow.getModel().setCurrentView(vhHome);
					
					/* Others */
					mwWindow.setVisible(true);
				} catch(Exception e) {
					JOptionPane.showMessageDialog(null, "An error occurred, please contact an administrator.", "Twinkle", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
	}
	
}
