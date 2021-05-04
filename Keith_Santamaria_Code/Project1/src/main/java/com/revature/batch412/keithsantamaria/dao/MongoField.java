package com.revature.batch412.keithsantamaria.dao;

public class MongoField {
	private String fieldName;
	private String fieldContents;

	public MongoField(){
		this.fieldName = "";
		this.fieldContents = "";
	}

	public MongoField(String fieldName){
		this.fieldName = fieldName;
		this.fieldContents = "";
	}

	public MongoField(String fieldName,String fieldContents){
		this.fieldName =fieldName;
		this.fieldContents = fieldContents;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldContents() {
		return fieldContents;
	}

	public void setFieldContents(String fieldContents) {
		this.fieldContents = fieldContents;
	}
}
