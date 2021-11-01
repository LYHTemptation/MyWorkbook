package com.example.demo.vo;

public class WordVO {
	private int no,pcode,quantity;
	private String id;
	public WordVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WordVO(int no, int pcode, int quantity, String id) {
		setId(id);
		setNo(no);
		setPcode(pcode);
		setQuantity(quantity);
	}
	@Override
	public String toString() {
		return "WordVO [no=" + no + ", pcode=" + pcode + ", quantity=" + quantity + ", id=" + id + "]";
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getPcode() {
		return pcode;
	}
	public void setPcode(int pcode) {
		this.pcode = pcode;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
