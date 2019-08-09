/**
	@author
		• Denis Christopher
		• Prevot Sean
		• Genco Luca
**/

package be.twinkle.usecaseimpl;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;
import be.twinkle.dao.EmployeeDao;
import be.twinkle.daoimpl.DaoFactory;
import be.twinkle.domaine.Bundle;
import be.twinkle.domaine.Employee;
import be.twinkle.domaine.Function;
import be.twinkle.domaine.Skill;
import be.twinkle.usecase.EmployeesManagement;

public class EmployeesManagementImpl implements EmployeesManagement {
	/* ATTRIBUTES */
	private EmployeeDao employeeDao;
	
	
	/* CONSTRUCTOR */
	public EmployeesManagementImpl() {
		this.employeeDao = (EmployeeDao) DaoFactory.getInstance().getDaoImpl(EmployeeDao.class);
	}
	
	
	/* METHODS */
	@Override
	public void addEmployee(Bundle bundle) {
		boolean succeded = false;
		String message = "";
		Employee employee = (Employee) bundle.get(Bundle.EMPLOYEE);
		Pattern p = Pattern.compile("^[0-9]{3,4}[/][0-9]{2}[.][0-9]{2}[.][0-9]{2}$");
		Matcher m = null;
		
		if (employee.getFirstname() == null || employee.getFirstname().isEmpty()) {
			message = "Addition wasn't successful. Firstname is missing.";
		} else if (employee.getLastname() == null	|| employee.getLastname().isEmpty()) {
			message = "Addition wasn't successful. Lastname is missing.";
		} else if (employee.getAddress() == null || employee.getAddress().isEmpty()) {
			message = "Addition wasn't successful. Addres is missing.";
		} else if (employee.getPhone() == null || employee.getPhone().isEmpty()) {
			message = "Addition wasn't successful. Phone number is missing.";
		} else if (employee.getBirthday() == null || employee.getBirthday().isEmpty()) {
			message = "Addition wasn't successful. Birthday is missing.";
		} else if (employee.getHiredDate() == null || employee.getHiredDate().isEmpty()) {
			message = "Addition wasn't successful. Hired date is missing.";
		} else if (employee.getDegrees() == null || employee.getDegrees().isEmpty()) {
			message = "Addition wasn't successful. Degress are missing.";
		} else if (employee.getFunction() == null){
			message = "Addition wasn't successful. Function is missing.";
		} else if (employee.getSkills() == null || employee.getSkills().isEmpty()) {
			message = "Addition wasn't successful. Skills are missing.";
		} else {
			m = p.matcher(employee.getPhone());
			if (m.matches()){
				if (this.employeeDao.getEmployee(employee.getPhone()) == null){
					succeded = this.employeeDao.addEmployee(employee);
					if (succeded) {
						message = employee.getLastname() + " " + employee.getFirstname() + " has been added.";
					} else {
						message = "Impossible addition.";
					}
				}
				else {
					message = "This employee already exists!";
				}
			}
			else {
				message = "Invalid phone format.";
			}
		}
		
		bundle.put(Bundle.SUCCESS, succeded);
		bundle.put(Bundle.MESSAGE, message);
	}

	@Override
	public void listEmployees(Bundle bundle) {
		boolean succeded = true;
		String message = "";
		List<Employee> listEmployee = null;
		listEmployee = this.employeeDao.listEmployees();
		
		if(listEmployee == null){
			succeded = false;
			message = "Employees list doesn't exists.";
		}else if(listEmployee.isEmpty()) {
			message = "Employees list is empty.";
		}else if(listEmployee.size() == 1){
			message = "Employees list contains 1 employee.";
		}else {
			message = "Employees list contains " + listEmployee.size() + " employees.";
		}

		bundle.put(Bundle.SUCCESS, succeded);
		bundle.put(Bundle.MESSAGE, message);
		bundle.put(Bundle.LISTEMPLOYEES, listEmployee);
	}
	
	@Override
	public void listSkills(Bundle bundle) {
		boolean succeded = false;
		String message = "";
		
		ArrayList<Skill> skillsList = (ArrayList<Skill>) this.employeeDao.listSkills();
		if(skillsList != null) {
			if(!skillsList.isEmpty()) {
				succeded = true;
				message = "Skills list exists.";
				bundle.put(Bundle.LISTSKILLS, skillsList);
			}
			else {
				message = "Skills list is empty.";
			}
		}
		else {
			message = "Skills list doesn't exist.";
		}
		
		
		bundle.put(Bundle.SUCCESS, succeded);
		bundle.put(Bundle.MESSAGE, message);	
	}

	@Override
	public void listFunctions(Bundle bundle) {
		boolean succeded = false;
		String message = "";
		
		ArrayList<Function> functionsList = (ArrayList<Function>) this.employeeDao.listFunctions();
		if(functionsList != null) {
			if(!functionsList.isEmpty()) {
				succeded = true;
				message = "Functions list exists.";
				bundle.put(Bundle.LISTFUNCTIONS, functionsList);
			}
			else {
				message = "Functions list is empty.";
			}
		}
		else {
			message = "Functions list doesn't exist.";
		}
		
		
		bundle.put(Bundle.SUCCESS, succeded);
		bundle.put(Bundle.MESSAGE, message);
	}


	@Override
	public void rateEmployee(Bundle bundle) {
		boolean succeded = false;
		String message = "";
		Employee employee = (Employee) bundle.get(Bundle.EMPLOYEE);
		
		if(employee != null) {
			if(employee.getId() > 0) {
				if(employee.getPhone() != null && !employee.getPhone().isEmpty()) {
					if(employee.getSkills() != null) {
						succeded = this.employeeDao.rateSkill(employee);
						if (succeded) {
							message = "Skill has been evaluated.";
						}
						else {
							message = "Impossible evaluation.";
						}
					}
					else {
						message = "Evaluation couldn't be done. Employee doesn't have skills.";
					}
				}
				else {
					message = "Evaluation couldn't be done. Employee doesn't have a phone number.";
				}
			}
			else {
				message = "Evaluation couldn't be done. Employee ID is invalid.";
			}
		}
		else {
			message = "Evaluation couldn't be done. Employee doesn't exists.";
		}
		
		bundle.put(Bundle.SUCCESS, succeded);
		bundle.put(Bundle.MESSAGE, message);
	}


	@Override
	public void listEmployeesByFunction(Bundle bundle) {
		boolean succeded = false;
		String message = "";
		List<Employee> listEmployee = null;
		Function f = (Function)bundle.get(Bundle.FUNCTION);
		
		if(f != null) {
			if(f.getId() > 0) {
				listEmployee = this.employeeDao.listEmployeesByFunction(f.getId());
				if(listEmployee != null) {
					if(!listEmployee.isEmpty()) {
						succeded = true;
						message = "Employees list exists.";
						bundle.put(Bundle.LISTEMPLOYEES, listEmployee);
					}else{
						message = "Employees list is empty.";
					}
				}else{
					message = "Employees list doesn't exists.";
				}
			}else{
				message = "Function ID is invalid.";
			}
		}else{
			message = "No function was specified.";
		}
		
		bundle.put(Bundle.SUCCESS, succeded);
		bundle.put(Bundle.MESSAGE, message);
	}
	
}
