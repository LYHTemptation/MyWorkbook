package com.example.demo.controller;

//네이버 Papago NMT API 예제
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.OpenApiService;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
@Controller
public class APIController {
	
	@Autowired
	OpenApiService openApiService;
	
	//파파고API
	@RequestMapping("searchWord")
	@ResponseBody
    public String word(HttpServletRequest request) {
        	String searchWord = request.getParameter("searchWord");
        	String data = openApiService.word(searchWord);
        	return data;
    }
	
	@RequestMapping("getXY")
	@ResponseBody
	public String getXY(String address) {
		System.out.println(address);
		try {
			org.json.JSONObject jo = openApiService.getXY(address);
			System.out.println(jo);
			return jo.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "fail";
		}
	}
	
	@RequestMapping("tts")
	@ResponseBody
	public String tts(String text) {
		String returnMp3 = openApiService.tts(text);
		return returnMp3;
	}

}
