package com.example.demo.vo;

public class WordVO {

	private int code;
	private String searchWord, resultWord;
	
	private int w_code;
	private String w_word,w_mean;
	
	private int set_number;
	private String id;
	private String set_date;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSet_date() {
		return set_date;
	}
	public void setSet_date(String set_date) {
		this.set_date = set_date;
	}
	public int getSet_number() {
		return set_number;
	}
	public void setSet_number(int set_number) {
		this.set_number = set_number;
	}
	public int getW_code() {
		return w_code;
	}
	public void setW_code(int w_code) {
		this.w_code = w_code;
	}
	public String getW_word() {
		return w_word;
	}
	public void setW_word(String w_word) {
		this.w_word = w_word;
	}
	public String getW_mean() {
		return w_mean;
	}
	public void setW_mean(String w_mean) {
		this.w_mean = w_mean;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public String getResultWord() {
		return resultWord;
	}
	public void setResultWord(String resultWord) {
		this.resultWord = resultWord;
	}

	

	@Override
	public String toString() {
		return "WordVO [code=" + code + ", searchWord=" + searchWord + ", resultWord=" + resultWord + ", w_code="
				+ w_code + ", w_word=" + w_word + ", w_mean=" + w_mean + ", set_number=" + set_number + ", id=" + id
				+ ", set_date=" + set_date + "]";
	}
	public WordVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}
