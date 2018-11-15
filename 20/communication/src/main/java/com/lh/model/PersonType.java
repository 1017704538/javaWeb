package com.lh.model;

public class PersonType {
	private String typeId;//编号
	private String personType;//讯友的类别：如朋友、同学、同时、家人等
	
	public PersonType(){}
	public PersonType(String personType){
		this.personType=personType;
	}
	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}
}
