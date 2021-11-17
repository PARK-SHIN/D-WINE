package com.project.dwine.product.model.vo;

public class Type {

	private int typeNo;			// 와인종류 번호
	private String typeName;	// 와인종류명

	public Type() {}

	public Type(int typeNo, String typeName) {
		super();
		this.typeNo = typeNo;
		this.typeName = typeName;
	}

	public int getTypeNo() {
		return typeNo;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeNo(int typeNo) {
		this.typeNo = typeNo;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Override
	public String toString() {
		return "Type [typeNo=" + typeNo + ", typeName=" + typeName + "]";
	}
	
	
}
