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

import be.twinkle.dao.EmployeeDao;
import be.twinkle.daoimpl.EmployeeDaoMockImpl;
import be.twinkle.domaine.Employee;
import be.twinkle.domaine.Function;
import be.twinkle.domaine.Skill;

public class TestNG_EmployeeDaoMockImpl {
	/* ATTRIBUTES */
	private EmployeeDao employeeDao;
	private Employee christopher, sean, luca;
	private ArrayList<Skill> skillsList;
	
	
	/* INITIALIZATIONS */
	@BeforeClass
	public void initialiser_EmployeeDao() {
		employeeDao = new EmployeeDaoMockImpl();
	}
	
	@BeforeClass(dependsOnMethods = { "initialiser_EmployeeDao" })
	public void initialiser_SkillsList() {
		skillsList = (ArrayList<Skill>) this.employeeDao.listSkills();
	}
	
	@BeforeClass(dependsOnMethods = { "initialiser_SkillsList" })
	public void initialiser_Employees() {
		christopher = new Employee(1, "Christopher", "Denis", "Adresse", "0498830433", "01/01/2001", "14/09/2020", "Analyst Programmer", skillsList, new Function());
		sean = new Employee(2, "Sean", "Prévot", "Adresse", "0498830431", "01/01/2001", "14/09/2020", "Analyst Programmer", skillsList, new Function());
		luca = new Employee(3, "Luca", "Genco", "Adresse", "0498830432", "01/01/2001", "14/09/2020", "Analyst Programmer", skillsList, new Function());
	}
	
	
	/* TESTS */	
	@Test
	public void test_addEmployee() {
		Assert.assertTrue(employeeDao.addEmployee(christopher));
		Assert.assertTrue(employeeDao.addEmployee(sean));
		Assert.assertTrue(employeeDao.addEmployee(luca));
		Assert.assertFalse(employeeDao.addEmployee(sean)); // renvoie false car id déjà dans la liste
	}
	
	@Test(dependsOnMethods = { "test_addEmployee" })
	public void test_listEmployees() {
		Assert.assertNotNull(employeeDao.listEmployees());
	}	
	
	@Test(dependsOnMethods = { "test_listEmployees" })
	public void test_listEmployeesByFunction() {
		Assert.assertNotNull(employeeDao.listEmployeesByFunction(1));
	}
	
	@Test(dependsOnMethods = { "test_listEmployeesByFunction" })
	public void test_rateSkill() {
		christopher.getSkills().get(0).setRate(5);
		Assert.assertTrue(employeeDao.rateSkill(christopher));
	}
		
}
