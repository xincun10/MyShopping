package com.zcy.domain;
/**
 * ����һ��javabean�������ݿ��е�user���Ӧ
 * @author Administrator
 *
 */
public class Users {
	public Users(){}
	public Users(int id, String pwd) {
		this.id = id;
		this.pwd = pwd;
	}
	private int id;
	private String name;
	private String pwd;
	private String email;
	private String tel;
	private int grade;
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
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
}
