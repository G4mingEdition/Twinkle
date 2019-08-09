/**
	@author
		• Denis Christopher
		• Prevot Sean
		• Genco Luca
**/

package be.twinkle.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import be.twinkle.daoimpl.DaoFactory;
import be.twinkle.dao.EmployeeDao;
import be.twinkle.domaine.Employee;
import be.twinkle.domaine.Function;
import be.twinkle.domaine.Skill;

public class EmployeeDaoImpl implements EmployeeDao{
	public static final String INSERT = "INSERT INTO employee(firstname, lastname, address, phone, birthday, hiredDate, degrees, function_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String INSERT_SKILLS_EMPLOYEE = "INSERT INTO employees_skills(skill_id, employee_id, rate) VALUES(?, ?, ?)";
	public static final String UPDATE_SKILLS_EMPLOYEE = "UPDATE employees_skills SET rate = ? WHERE employee_id = ? AND skill_id = ?";
	public static final String GET_EMPLOYEE = "SELECT * FROM employee WHERE phone = ?";
	public static final String GET_ID = "SELECT id FROM employee WHERE phone = ?";
	public static final String GET_FUNCTION = "SELECT * FROM function WHERE id = ?";
	public static final String GET_SKILLS_EMPLOYEE = "SELECT * FROM employees_skills WHERE employee_id = ?";
	public static final String GET_SKILL = "SELECT * FROM skill WHERE id = ?";
	public static final String LIST_EMPLOYEES = "SELECT * FROM employee";
	public static final String LIST_EMPLOYEES_BY_FUNCTION = "SELECT * FROM employee WHERE function_id = ?";
	public static final String LIST_SKILLS = "SELECT * FROM skill";
	public static final String LIST_FUNCTIONS = "SELECT * FROM function";
	
	public EmployeeDaoImpl() {
	}
	
	private void cloturer(ResultSet rs, PreparedStatement ps, Connection con) {
		try {
			if (rs != null)
				rs.close();
		} catch (Exception ex) {
		}
		try {
			if (ps != null)
				ps.close();
		} catch (Exception ex) {
		}
		try {
			if (con != null)
				con.close();
		} catch (Exception ex) {
		}
	}
	
	@Override
	public boolean addEmployee(Employee employee) {
		boolean insertSuccess = false;
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement psPhone = null;
		PreparedStatement psSkill = null;
		ResultSet rsPhone = null;
		
		try {
			con = DaoFactory.getInstance().getConnexion();
			
			ps = con.prepareStatement(INSERT);
			ps.setString(1, employee.getFirstname());
			ps.setString(2, employee.getLastname());
			ps.setString(3, employee.getAddress());
			ps.setString(4, employee.getPhone());
			ps.setString(5, employee.getBirthday());
			ps.setString(6, employee.getHiredDate());
			ps.setString(7, employee.getDegrees());
			ps.setInt(8, employee.getFunction().getId());
			int resultat = ps.executeUpdate();
			
			/*Id*/
			int id = 0;
			psPhone = con.prepareStatement(GET_ID);
			psPhone.setString(1, employee.getPhone());
			rsPhone = psPhone.executeQuery();
			if(rsPhone.next()) {
				id = rsPhone.getInt("id");
			}
			/*SKILL*/
			for(Skill skill : employee.getSkills()) {
				psSkill = con.prepareStatement(INSERT_SKILLS_EMPLOYEE);
				psSkill.setInt(1, skill.getId());
				psSkill.setInt(2, id);
				psSkill.setInt(3,  -1);
				int resInsert = psSkill.executeUpdate();
				if(resInsert != 1) {
					return false;
				}
			}
			if (resultat == 1) {
				insertSuccess = true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			cloturer(null, ps, con);
			cloturer(null, psSkill, con);
			cloturer(rsPhone, psPhone, con);
		}
		return insertSuccess;
	}

	@Override
	public Employee getEmployee(String phone) {
		Employee emp = null;
		Connection con = null;
		PreparedStatement psFunc = null;
		PreparedStatement psSkillsEmp = null;
		PreparedStatement ps = null;
		PreparedStatement psSkill = null;
		ResultSet rs = null;
		ResultSet rsFunc = null;
		ResultSet rsSkillsEmp = null;
		ResultSet rsSkill = null;
		Function func = null;
		ArrayList<Skill> listSkill = new ArrayList<Skill>();
		try {			
			con = DaoFactory.getInstance().getConnexion();
			ps = con.prepareStatement(GET_EMPLOYEE);
			ps.setString(1, phone);
			rs = ps.executeQuery();
			if(rs.next()){
				/*Récupération de la fonction*/
				psFunc = con.prepareStatement(GET_FUNCTION);
				psFunc.setInt(1, Integer.parseInt(rs.getString("function_id")));
				rsFunc = psFunc.executeQuery();
				if(rsFunc.next()) {
					func = new Function(Integer.parseInt(rs.getString("function_id")), rsFunc.getString("description"), Integer.parseInt(rsFunc.getString("level")));
				}
				/*Récupération des compétences*/
				psSkillsEmp = con.prepareStatement(GET_SKILLS_EMPLOYEE);
				psSkillsEmp.setInt(1, Integer.parseInt(rs.getString("id")));
				rsSkillsEmp = psSkillsEmp.executeQuery();
				//Ajout de la liste des id des skills
				while(rsSkillsEmp.next()) {
					psSkill = con.prepareStatement(GET_SKILL);
					psSkill.setInt(1, Integer.parseInt(rsSkillsEmp.getString("skill_id")));
					rsSkill = psSkill.executeQuery();
					if(rsSkill.next()) {
						listSkill.add(new Skill(Integer.parseInt(rsSkillsEmp.getString("skill_id")), rsSkill.getString("name"), Integer.parseInt(rsSkillsEmp.getString("rate"))));
					}
				}
				emp = new Employee(Integer.parseInt(rs.getString("id")), rs.getString("firstname"), rs.getString("lastname"), rs.getString("address"), phone, rs.getString("birthday"), rs.getString("hiredDate"), rs.getString("degrees"), listSkill, func);
				return emp;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			cloturer(rs, ps, con);
			cloturer(rsFunc, psFunc, con);
			cloturer(rsSkill, psSkill, con);
			cloturer(rsSkillsEmp, psSkillsEmp, con);
		}
		return emp;
	}

	@Override
	public List<Employee> listEmployees() {
		List<Employee> employeesList = new ArrayList<Employee>();
		Connection con = null;
		PreparedStatement psEmployees = null;
		PreparedStatement psFunctionEmpl = null;
		PreparedStatement psSkillsEmpl = null;
		PreparedStatement psSkill = null;
		ResultSet rsEmployees = null;
		ResultSet rsFunctionEmpl = null;
		ResultSet rsSkillsEmpl = null;
		ResultSet rsSkill = null;
		try {			
			con = DaoFactory.getInstance().getConnexion();
			psEmployees = con.prepareStatement(LIST_EMPLOYEES);
			rsEmployees = psEmployees.executeQuery();
			
			/* Get all employees */
			while(rsEmployees.next()) {					
				/* Get function of the employee */
				Function function = null;
				psFunctionEmpl = con.prepareStatement(GET_FUNCTION);
				psFunctionEmpl.setInt(1, Integer.parseInt(rsEmployees.getString("function_id")));
				rsFunctionEmpl = psFunctionEmpl.executeQuery();
				if(rsFunctionEmpl.next()) {
					function = new Function(Integer.parseInt(rsEmployees.getString("function_id")), rsFunctionEmpl.getString("description"), Integer.parseInt(rsFunctionEmpl.getString("level")));
				}
				
				/* Get skills of the employee */
				ArrayList<Skill> skillsList = new ArrayList<Skill>();
				psSkillsEmpl = con.prepareStatement(GET_SKILLS_EMPLOYEE);
				psSkillsEmpl.setInt(1, Integer.parseInt(rsEmployees.getString("id")));
				rsSkillsEmpl = psSkillsEmpl.executeQuery();
				while(rsSkillsEmpl.next()) {
					psSkill = con.prepareStatement(GET_SKILL);
					psSkill.setInt(1, Integer.parseInt(rsSkillsEmpl.getString("skill_id")));
					rsSkill = psSkill.executeQuery();
					if(rsSkill.next()) {
						skillsList.add(new Skill(Integer.parseInt(rsSkillsEmpl.getString("skill_id")), rsSkill.getString("name"), Integer.parseInt(rsSkillsEmpl.getString("rate"))));
					}
				}		
				
				/* Add employee to the list */
				employeesList.add(new Employee(Integer.parseInt(rsEmployees.getString("id")), rsEmployees.getString("firstname"), rsEmployees.getString("lastname"), rsEmployees.getString("address"), rsEmployees.getString("phone"), rsEmployees.getString("birthday"), rsEmployees.getString("hiredDate"), rsEmployees.getString("degrees"), skillsList, function));
			}
			return employeesList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			cloturer(rsEmployees, psEmployees, con);
			cloturer(rsFunctionEmpl, psFunctionEmpl, con);
			cloturer(rsSkill, psSkill, con);
			cloturer(rsSkillsEmpl, psSkillsEmpl, con);
		}
		return employeesList;
	}
	
	@Override
	public List<Employee> listEmployeesByFunction(int function_id) {
		List<Employee> employeesList = new ArrayList<Employee>();
		Connection con = null;
		PreparedStatement psEmployees = null;
		PreparedStatement psFunctionEmpl = null;
		PreparedStatement psSkillsEmpl = null;
		PreparedStatement psSkill = null;
		ResultSet rsEmployees = null;
		ResultSet rsFunctionEmpl = null;
		ResultSet rsSkillsEmpl = null;
		ResultSet rsSkill = null;
		try {			
			con = DaoFactory.getInstance().getConnexion();
			psEmployees = con.prepareStatement(LIST_EMPLOYEES_BY_FUNCTION);
			psEmployees.setInt(1, function_id);
			rsEmployees = psEmployees.executeQuery();
			
			/* Get all employees */
			while(rsEmployees.next()) {					
				/* Get function of the employee */
				Function function = null;
				psFunctionEmpl = con.prepareStatement(GET_FUNCTION);
				psFunctionEmpl.setInt(1, Integer.parseInt(rsEmployees.getString("function_id")));
				rsFunctionEmpl = psFunctionEmpl.executeQuery();
				if(rsFunctionEmpl.next()) {
					function = new Function(Integer.parseInt(rsEmployees.getString("function_id")), rsFunctionEmpl.getString("description"), Integer.parseInt(rsFunctionEmpl.getString("level")));
				}
				
				/* Get skills of the employee */
				ArrayList<Skill> skillsList = new ArrayList<Skill>();
				psSkillsEmpl = con.prepareStatement(GET_SKILLS_EMPLOYEE);
				psSkillsEmpl.setInt(1, Integer.parseInt(rsEmployees.getString("id")));
				rsSkillsEmpl = psSkillsEmpl.executeQuery();
				while(rsSkillsEmpl.next()) {
					psSkill = con.prepareStatement(GET_SKILL);
					psSkill.setInt(1, Integer.parseInt(rsSkillsEmpl.getString("skill_id")));
					rsSkill = psSkill.executeQuery();
					if(rsSkill.next()) {
						skillsList.add(new Skill(Integer.parseInt(rsSkillsEmpl.getString("skill_id")), rsSkill.getString("name"), Integer.parseInt(rsSkillsEmpl.getString("rate"))));
					}
				}		
				
				/* Add employee to the list */
				employeesList.add(new Employee(Integer.parseInt(rsEmployees.getString("id")), rsEmployees.getString("firstname"), rsEmployees.getString("lastname"), rsEmployees.getString("address"), rsEmployees.getString("phone"), rsEmployees.getString("birthday"), rsEmployees.getString("hiredDate"), rsEmployees.getString("degrees"), skillsList, function));
			}
			return employeesList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			cloturer(rsEmployees, psEmployees, con);
			cloturer(rsFunctionEmpl, psFunctionEmpl, con);
			cloturer(rsSkill, psSkill, con);
			cloturer(rsSkillsEmpl, psSkillsEmpl, con);
		}
		return employeesList;
	}

	@Override
	public List<Function> listFunctions() {
		List<Function> listFunc = new ArrayList<Function>();
		Connection con = null;
		con = DaoFactory.getInstance().getConnexion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(LIST_FUNCTIONS);
			rs = ps.executeQuery();
			while(rs.next()) {
				listFunc.add(new Function(Integer.parseInt(rs.getString("id")), rs.getString("description"), Integer.parseInt(rs.getString("level"))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cloturer(rs, ps, con);
		}
		return listFunc;
	}

	@Override
	public List<Skill> listSkills() {
		List<Skill> listSkill = new ArrayList<Skill>();
		Connection con = null;
		con = DaoFactory.getInstance().getConnexion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(LIST_SKILLS);
			rs = ps.executeQuery();
			while(rs.next()) {
				listSkill.add(new Skill(Integer.parseInt(rs.getString("id")), rs.getString("name"), -1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cloturer(rs, ps, con);
		}
		return listSkill;
	}

	@Override
	public boolean rateSkill(Employee employee) {
		Connection con = null;
		PreparedStatement psUpd = null;
		int id = employee.getId(); 
		
		try {
			con = DaoFactory.getInstance().getConnexion();
			
			for(Skill skill : employee.getSkills()) {
				psUpd = con.prepareStatement(UPDATE_SKILLS_EMPLOYEE);
				psUpd.setInt(1, skill.getRate());
				psUpd.setInt(2, id);
				psUpd.setInt(3, skill.getId());
				int resUpd = psUpd.executeUpdate();
				if(resUpd != 1) {
					return false;
				}
			}
			
		}catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			cloturer(null, psUpd, con);
		}
		
		return true;		
	}
}
