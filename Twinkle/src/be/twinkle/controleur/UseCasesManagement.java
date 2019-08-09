/**
	@author
		• Denis Christopher
		• Prevot Sean
		• Genco Luca
**/

package be.twinkle.controleur;
import be.twinkle.domaine.Bundle;
import be.twinkle.usecase.EmployeesManagement;
import be.twinkle.usecaseimpl.EmployeesManagementImpl;

public class UseCasesManagement {
	/* ATTRIBUTES */
	private static final UseCasesManagement INSTANCE = new UseCasesManagement();
	private EmployeesManagement employeesManagement;
	
	
	/* CONSTRUCTOR */
	private UseCasesManagement() {
		this.employeesManagement = new EmployeesManagementImpl();
	}
	
	
	/* METHODS */
	public static UseCasesManagement getInstance() {
		return INSTANCE;
	}
	
	public void addEmployee(Bundle bundle) {
		this.employeesManagement.addEmployee(bundle);
	}
	
	public void searchEmployee(Bundle bundle) {
		this.employeesManagement.listEmployeesByFunction(bundle);
	}
	
	public void listEmployees(Bundle bundle) {
		this.employeesManagement.listEmployees(bundle);
	}
	
	public void listSkills(Bundle bundle) {
		this.employeesManagement.listSkills(bundle);
	}
	
	public void listFunctions(Bundle bundle) {
		this.employeesManagement.listFunctions(bundle);
	}

	public void rateEmployee(Bundle bundle) {
		this.employeesManagement.rateEmployee(bundle);
	}
	
}
