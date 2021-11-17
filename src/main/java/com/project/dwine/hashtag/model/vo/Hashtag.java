package com.project.dwine.hashtag.model.vo;

public class Hashtag {

	private int hashNo; 		// 해시태그 번호
	private int hashType;		// 해시태그 타입
	private String hashName;	// 해시태그명
	
	public Hashtag() {}

	public Hashtag(int hashNo, int hashType, String hashName) {
		super();
		this.hashNo = hashNo;
		this.hashType = hashType;
		this.hashName = hashName;
	}

	public int getHashNo() {
		return hashNo;
	}

	public int getHashType() {
		return hashType;
	}

	public String getHashName() {
		return hashName;
	}

	public void setHashNo(int hashNo) {
		this.hashNo = hashNo;
	}

	public void setHashType(int hashType) {
		this.hashType = hashType;
	}

	public void setHashName(String hashName) {
		this.hashName = hashName;
	}

	@Override
	public String toString() {
		return "Hashtag [hashNo=" + hashNo + ", hashType=" + hashType + ", hashName=" + hashName + "]";
	}
	
	
}
