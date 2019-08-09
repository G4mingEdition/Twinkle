/*
	authors:
		• Genco Luca
*/

package be.twinkle.daoimpl;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import be.twinkle.dao.EmployeeDao;
import be.twinkle.domaine.Employee;
import be.twinkle.domaine.Function;
import be.twinkle.domaine.Skill;

public class EmployeeDaoMockImpl implements EmployeeDao{
	/* ATTRIBUTES */
	private Map<String, Employee> mapEmployees;
	private List<Skill> listSkills;
	private List<Function> listFunctions;
	
	
	/* CONSTRUCTOR */
	public EmployeeDaoMockImpl(){
		Comparator<String> comp = new ComparateurEmployee();
		this.mapEmployees = new TreeMap<String, Employee>(comp);
		this.listSkills = new ArrayList<>();
		this.listFunctions = new ArrayList<>();
		
		// Skills list
		this.listSkills.add(new Skill(1, "Techniques", 0));
		this.listSkills.add(new Skill(2, "Organisationnelles", 0));
		this.listSkills.add(new Skill(3, "Relationnelle & Sociales", 0));
		this.listSkills.add(new Skill(4, "Adaptation", 0));
		// Functions list 
		this.listFunctions.add(new Function(1, "Développeur", 1));
		this.listFunctions.add(new Function(2, "Analyste", 1));
		this.listFunctions.add(new Function(3, "Chef de projet", 2));
		this.listFunctions.add(new Function(3, "Architecte", 2));
	}

	
	/* METHODS */
	@Override
	public boolean addEmployee(Employee employee) {
		if(this.mapEmployees.containsKey(String.valueOf(employee.getPhone()))){
			return false;
		}
		
		this.mapEmployees.put(String.valueOf(employee.getPhone()), employee);
		return true;
	}


	@Override
	public Employee getEmployee(String phone) {
		Employee emp = null;
		for (Map.Entry<String, Employee> element : this.mapEmployees.entrySet()) {
			if(element.getKey().equals(phone)) {
				emp = element.getValue();
			}
		}
		return emp;
	}

	@Override
	public List<Employee> listEmployees() {
		List<Employee> employees = new ArrayList<Employee>();
		for (Map.Entry<String, Employee> element : this.mapEmployees.entrySet()) {
			employees.add(element.getValue());
		}
		return employees;
	}
	
	@Override
	public List<Employee> listEmployeesByFunction(int function_id) {
		List<Employee> employees = new ArrayList<Employee>();
		for (Map.Entry<String, Employee> element : this.mapEmployees.entrySet()) {
			if(element.getValue().getFunction().getId() == function_id) {
				employees.add(element.getValue());
			}
		}
		return employees;
	}
	
	@Override
	public List<Function> listFunctions() {
		return this.listFunctions;
	}

	@Override
	public List<Skill> listSkills() {
		return this.listSkills;
	}

	@Override
	public boolean rateSkill(Employee employee) {
		boolean success = false; 
		for (Map.Entry<String, Employee> element : this.mapEmployees.entrySet()) {
			System.out.println(element.getKey());
			if(element.getKey().equals(employee.getPhone())) {
			  element.setValue(employee);
			  success = true;
			}
		}
		return success;
		
	}
	
	private class ComparateurEmployee implements Comparator<String> {
		@Override
		public int compare(String nom1, String nom2) {
			return nom1.compareTo(nom2);
		}
	}
	
}
