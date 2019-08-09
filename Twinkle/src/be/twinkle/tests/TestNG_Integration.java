/**
	@author
		• Denis Christopher
		• Prevot Sean
		• Genco Luca
**/

package be.twinkle.tests;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import be.twinkle.domaine.Bundle;
import be.twinkle.domaine.Employee;
import be.twinkle.domaine.Function;
import be.twinkle.domaine.Skill;
import be.twinkle.usecaseimpl.EmployeesManagementImpl;

public class TestNG_Integration {
	/* PROPERTIES */
	private EmployeesManagementImpl employeesManagementImpl;
	private Bundle bundle;
	private Employee employee;
	private Function function;
	private ArrayList<Skill> skillsList;
	
	
	/* INITIALIZATIONS */
	@BeforeClass
	public void initialiser_EmployeesManangementImpl() {
		employeesManagementImpl = new EmployeesManagementImpl();
	}
	
	@BeforeClass
	public void initialiser_Bundle() {
		bundle = new Bundle();
	}
	
	@SuppressWarnings("unchecked")
	@BeforeClass(dependsOnMethods = {"initialiser_Bundle"})
	public void initialiser_SkillsList() {
		this.employeesManagementImpl.listSkills(this.bundle);
		skillsList = (ArrayList<Skill>) this.bundle.get(Bundle.LISTSKILLS);
	}
	
	@SuppressWarnings("unchecked")
	@BeforeClass(dependsOnMethods = {"initialiser_SkillsList"})
	public void initialiser_Function() {
		employeesManagementImpl.listFunctions(bundle);
		ArrayList<Function> list = (ArrayList<Function>) bundle.get(Bundle.LISTFUNCTIONS);
		function = list.get(1);
		bundle.put(Bundle.FUNCTION, function);
	}
	
	@BeforeClass(dependsOnMethods = {"initialiser_Function"})
	public void initialiser_Employee() {
		employee = new Employee(10, "Michael", "Frost", "2716 Ray Court Fayetteville, NC 28301", "0471/12.25.48", "09/05/1991", "14/09/2020", "Analyst Programmer", skillsList, function);
		bundle.put(Bundle.EMPLOYEE, employee);
	}
	
	
	/* TESTS */	
	@Test
	public void test_addEmployee() {
		employeesManagementImpl.addEmployee(bundle);
		Assert.assertFalse((boolean) bundle.get(Bundle.SUCCESS));
	}
	
	@Test(dependsOnMethods = { "test_addEmployee" })
	public void test_listEmployees() {
		employeesManagementImpl.listEmployees(bundle);
		Assert.assertTrue((boolean) bundle.get(Bundle.SUCCESS));
	}
	
	@Test(dependsOnMethods = { "test_listEmployees" })
	public void test_listEmployeesByFunction() {
		employeesManagementImpl.listEmployeesByFunction(bundle);
		Assert.assertTrue((boolean) bundle.get(Bundle.SUCCESS));
	}
	
	@Test(dependsOnMethods = { "test_listEmployeesByFunction" })
	public void test_rateSkill() {
		employee.getSkills().get(0).setRate(5);
		bundle.put(Bundle.EMPLOYEE, employee);
		employeesManagementImpl.rateEmployee(bundle);
		Assert.assertTrue((boolean) bundle.get(Bundle.SUCCESS));
	}
}
