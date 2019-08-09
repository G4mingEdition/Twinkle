/**
	@author
		• Genco Luca
**/

package be.twinkle.dao;

import java.util.List;
import be.twinkle.domaine.Employee;
import be.twinkle.domaine.Function;
import be.twinkle.domaine.Skill;

public interface EmployeeDao extends Dao{
	/* METHODS */
	public boolean addEmployee(Employee employee);
	public Employee getEmployee(String phone);
	public List<Employee> listEmployees();
	public List<Employee> listEmployeesByFunction(int function_id);
	public List<Function> listFunctions();
	public List<Skill> listSkills();
	public boolean rateSkill(Employee employee);
}
