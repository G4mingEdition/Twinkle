/**
	@author
		• Prevot Sean
**/

package be.twinkle.domaine;

public class Skill {
	/* ATTRIBUTES */
	private int id;
	private String name;
	private int rate;
	
	
	/* CONSTRUCTOR */
	public Skill(int id, String name, int rate) {
		super();
		this.id = id;
		this.name = name;
		this.rate = rate;
	}
	
	public Skill() {
		super();
		this.id = 0;
		this.name = "";
		this.rate = 0;
	}

	
	/* METHODS */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}
	
	
	
}
