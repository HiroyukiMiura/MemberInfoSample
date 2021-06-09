package jp.co.aforce.beans;

import java.io.Serializable;

public class MemberBean implements Serializable {

	private String memberNo; // 会員番号
	private String name; // 名前
	private String age; // 年齢
	private String birthYear; // 生年
	private String birthMonth; // 生月
	private String birthDay; // 生日
	private String emsg; // エラーメッセージ
	private String commsg; // 完了メッセージ

	// 引数無しのデフォルトコンストラクタ
	public MemberBean() {

	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}

	public String getBirthMonth() {
		return birthMonth;
	}

	public void setBirthMonth(String birthMonth) {
		this.birthMonth = birthMonth;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getEmsg() {
		return emsg;
	}

	public void setEmsg(String emsg) {
		this.emsg = emsg;
	}

	public String getCommsg() {
		return commsg;
	}

	public void setCommsg(String commsg) {
		this.commsg = commsg;
	}

}
