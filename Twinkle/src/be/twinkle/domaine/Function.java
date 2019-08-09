/**
	@author
		• Denis Christopher
		• Prevot Sean
**/

package be.twinkle.domaine;

public class Function {
	/* ATTRIBUTES */
	private int id;
	private String description;
	private int level;
	
	
	/* CONSTRUCTOR */
	public Function(int id, String description, int level) {
		super();
		this.id = id;
		this.description = description;
		this.level = level;
	}
	
	public Function() {
		super();
		this.id = 0;
		this.description = "";
		this.level = 0;
	}
	
	
	/* METHODS */
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
}
