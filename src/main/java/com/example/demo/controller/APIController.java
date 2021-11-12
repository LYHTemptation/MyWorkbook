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
	
	@RequestMapping("searchWord")
	@ResponseBody
    public String main(HttpServletRequest request,Model model) {
		System.out.println("API 함수 출력");
        String clientId = "";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "";//애플리케이션 클라이언트 시크릿값";
        try {
        	String searchWord = request.getParameter("searchWord");
        	System.out.println("searchWord: "+searchWord);
            String text = URLEncoder.encode(searchWord, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            // post request
            String postParams = "source=en&target=ko&text=" + text;
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            String resultWord = null;
            while ((inputLine = br.readLine()) != null) {
            	int start = inputLine.indexOf("translatedText");
            	String a = inputLine.substring(start);
				int next = a.indexOf(":");
				int end = a.indexOf(",");
				resultWord = a.substring(next+2,end-1);
                response.append(inputLine);
            }
            br.close();
            System.out.println(response.toString());
            System.out.println(resultWord);
            JSONObject jo=new JSONObject();
            jo.put("resultWord", resultWord);
            return jo.toJSONString();
        } catch (Exception e) {
            System.out.println(e);
        }
		return "else data";
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
}
