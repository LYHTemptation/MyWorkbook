package com.example.demo.vo;

public class MemberVO {
	
	private String id,pw,pwd,name,email,nickname,phone;
	
	

	public MemberVO(String id, String pw, String pwd, String name, String email, String nickname, String phone) {
		setId(id);
		setPhone(phone);
		setPw(pw);
		setPwd(pwd);
		setName(name);
		setNickname(nickname);
		setEmail(email);
	}

	public MemberVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		if(id!=null && !id.equals("")) {
			this.id = id;
		}else {
			System.out.println("id를 입력하세요");
		}
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		if(pw!=null && !pw.equals("")) {
			this.pw = pw;
		}else {
			System.out.println("pw를 입력하세요");
		}
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name!=null && !name.equals("")) {
			this.name = name;
		}else {
			System.out.println("name을 입력하세요");
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", pw=" + pw + ", pwd=" + pwd + ", name=" + name + ", email=" + email
				+ ", nickname=" + nickname + ", phone=" + phone + "]";
	}
	
	

}
