package it.polito.tdp.borders.model;

public class Country {
	
	private String abb; 
	private int code; 
	private String name;
	
	public Country(String abb, int code, String name) {
		super();
		this.abb = abb;
		this.code = code;
		this.name = name;
	}

	public String getAbb() {
		return abb;
	}

	public void setAbb(String abb) {
		this.abb = abb;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	} 
	
	
	

}
