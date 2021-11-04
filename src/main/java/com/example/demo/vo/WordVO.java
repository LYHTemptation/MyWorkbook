package com.example.demo.vo;

public class WordVO {

	private int code;
	private String searchWord, resultWord;
	
	private int w_code;
	private String w_word,w_mean;
	
	
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
				+ w_code + ", w_word=" + w_word + ", w_mean=" + w_mean + "]";
	}
	public WordVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}
