/**
	@author
		• Denis Christopher
		• Prevot Sean
		• Genco Luca
**/

package be.twinkle.usecase;

import be.twinkle.domaine.Bundle;

public interface EmployeesManagement {
	/* METHODS */	
	public void addEmployee(Bundle bundle);
	public void listEmployees(Bundle bundle);
	public void listEmployeesByFunction(Bundle bundle);
	public void listSkills(Bundle bundle);
	public void listFunctions(Bundle bundle);
	public void rateEmployee(Bundle bundle);
}
