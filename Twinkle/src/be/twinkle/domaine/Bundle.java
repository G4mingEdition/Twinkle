/**
	@author
		• Denis Christopher
		• Prevot Sean
		• Genco Luca
**/

package be.twinkle.domaine;
import java.util.HashMap;
import java.util.Map;

public class Bundle {
	/* ATTRIBUTES */
	public static final String MESSAGE = "message";
	public static final String EMPLOYEE = "employee";
	public static final String SUCCESS = "Success";
	public static final String LISTEMPLOYEES = "listEmployees";
	public static final String LISTSKILLS = "listSkills";
	public static final String LISTFUNCTIONS = "listFunctions";
	public static final String LASTNAME = "lname";
	public static final String FIRSTNAME = "fName";
	public static final String FUNCTION = "function";
	private Map<String, Object> map = new HashMap<String, Object>();
	

	/* CONSTRUCTOR */
	public Bundle() {
		this.map.put(SUCCESS, false);
		this.map.put(MESSAGE, "");
	}
	
	
	/* METHODS */
	public void put(String clef, Object valeur) {
		this.map.put(clef, valeur);
	}
	
	public Object get(String clef) {
		return this.map.get(clef);
	}
	
	public void vider() {
		this.map.clear();		
	}
	
}
