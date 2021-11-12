package com.example.demo.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class OpenApiService {

	public JSONObject getXY(String address) throws Exception {
		String text = URLEncoder.encode(address,"UTF-8");
		String getParms ="query="+text;
		
		URL url = new URL("https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?"+getParms);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");  //헤더 메소드 방식
		con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", ""); //헤더 프로퍼티
		con.setRequestProperty("X-NCP-APIGW-API-KEY", "");
		
		int responseCode = con.getResponseCode();
		BufferedReader br;
		if(responseCode==200) {
			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		}else {
			br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		}
		String oneLine="";
		String totalLine="";
		while((oneLine=br.readLine())!=null) {
			totalLine += oneLine;			
		}
		br.close();
		
		JSONObject jo = new JSONObject(totalLine);
		JSONArray ja = (JSONArray)jo.get("addresses");
		JSONObject jo2 = (JSONObject) ja.get(0);
		String x = (String)jo2.get("x");
		String y = (String)jo2.get("y");
		System.out.println(x+" "+y);
		
		JSONObject jo3 = new JSONObject();
		jo3.put("x", x);
		jo3.put("y", y);
		return jo3;
	}

}
