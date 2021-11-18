package com.example.demo.service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

@Service
public class OpenApiService {
	
    public String word(String word) {
        String clientId = "";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "";//애플리케이션 클라이언트 시크릿값";
        try {
        	String searchWord = word;
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
            JSONObject jo=new JSONObject();
            jo.put("resultWord", resultWord);
            return jo.toString();
        } catch (Exception e) {
            System.out.println(e);
        }
		return "else data";
    }
	
	
	
	//Map API
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
	
	//TTS API
	 public String tts(String text) {
	     String clientId = "";//애플리케이션 클라이언트 아이디값";
	     String clientSecret = "";//애플리케이션 클라이언트 시크릿값";
         if(text.contains("심심")) {
        	 text="나랑 같이 노래 부르자";
         }
	     try {
	         text = URLEncoder.encode(text, "UTF-8"); // 13자
	         System.out.println("utf후 : "+text);
	         String apiURL = "https://naveropenapi.apigw.ntruss.com/tts-premium/v1/tts";
	         URL url = new URL(apiURL);
	         HttpURLConnection con = (HttpURLConnection)url.openConnection();
	         con.setRequestMethod("POST");
	         con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
	         con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
	         // post request
	         String postParams="";
	         if(Pattern.matches("^[a-zA-Z]*$", text)) {
	        	 postParams = "speaker=clara&volume=0&speed=0&pitch=0&format=mp3&text=" + text; 
	         }else {
	        	 postParams = "speaker=jinho&volume=0&speed=0&pitch=0&format=mp3&text=" + text;
	         }
	         con.setDoOutput(true);
	         DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	         wr.writeBytes(postParams);
	         wr.flush();
	         wr.close();
	         int responseCode = con.getResponseCode();
	         BufferedReader br;
	         if(responseCode==200) { // 정상 호출
	             InputStream is = con.getInputStream();
	             int read = 0;
	             byte[] bytes = new byte[1024];
	             // 랜덤한 이름으로 mp3 파일 생성
	             String tempname = Long.valueOf(new Date().getTime()).toString();
	             // 파일의 경로 + 파일명
	             String fileName = "";
	             String filePath = "src\\main\\webapp\\media\\";
	             File rw = new File(filePath);
	             File deleteFile = new File(filePath);
	             File []fileList = rw.listFiles();
	             for(File file : fileList) {

	                 if(file.isFile()) {

	                    fileName = file.getName();
	                    deleteFile = new File(filePath+fileName);
	                    deleteFile.delete();
	                    System.out.println("파일을 삭제하였습니다.");
	                 }else {
	                	 System.out.println("파일이 존재하지 않습니다.");
	                 }

	           }
	             File f = new File("src\\main\\webapp\\media\\"+tempname+".mp3");
	             f.createNewFile();
	             OutputStream outputStream = new FileOutputStream(f);
	             while ((read =is.read(bytes)) != -1) {
	                 outputStream.write(bytes, 0, read);
	             }
	             is.close();
	             return tempname;
	         } else {  // 오류 발생
	             br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	             String inputLine;
	             StringBuffer response = new StringBuffer();
	             while ((inputLine = br.readLine()) != null) {
	                 response.append(inputLine);
	             }
	             br.close();
	             System.out.println(response.toString());
	             return "error";
	         }
	     } catch (Exception e) {
	         System.out.println(e);
	     }
	     return "";
	 }
	 
	 //Face API
	    public static void main(String[] args) {

	        StringBuffer reqStr = new StringBuffer();
	        String clientId = "";//애플리케이션 클라이언트 아이디값";
	        String clientSecret = "";//애플리케이션 클라이언트 시크릿값";

	        try {
	            String paramName = "image"; // 파라미터명은 image로 지정
	            String imgFile = "c:\\Temp\\3.jpg";
	            File uploadFile = new File(imgFile);
	            String apiURL = "https://naveropenapi.apigw.ntruss.com/vision/v1/celebrity"; // 유명인 얼굴 인식
	            URL url = new URL(apiURL);
	            HttpURLConnection con = (HttpURLConnection)url.openConnection();
	            con.setUseCaches(false);
	            con.setDoOutput(true);
	            con.setDoInput(true);
	            // multipart request
	            String boundary = "---" + System.currentTimeMillis() + "---";
	            con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
	            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
	            con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
	            OutputStream outputStream = con.getOutputStream();
	            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true);
	            String LINE_FEED = "\r\n";
	            // file 추가
	            String fileName = uploadFile.getName();
	            writer.append("--" + boundary).append(LINE_FEED);
	            writer.append("Content-Disposition: form-data; name=\"" + paramName + "\"; filename=\"" + fileName + "\"").append(LINE_FEED);
	            writer.append("Content-Type: "  + URLConnection.guessContentTypeFromName(fileName)).append(LINE_FEED);
	            writer.append(LINE_FEED);
	            writer.flush();
	            FileInputStream inputStream = new FileInputStream(uploadFile);
	            byte[] buffer = new byte[4096];
	            int bytesRead = -1;
	            while ((bytesRead = inputStream.read(buffer)) != -1) {
	                outputStream.write(buffer, 0, bytesRead);
	            }
	            outputStream.flush();
	            inputStream.close();
	            writer.append(LINE_FEED).flush();
	            writer.append("--" + boundary + "--").append(LINE_FEED);
	            writer.close();
	            BufferedReader br = null;
	            int responseCode = con.getResponseCode();
	            if(responseCode==200) { // 정상 호출
	                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	            } else {  // 오류 발생
	                System.out.println("error!!!!!!! responseCode= " + responseCode);
	                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	            }
	            String inputLine;
	            if(br != null) {
	                StringBuffer response = new StringBuffer();
	                while ((inputLine = br.readLine()) != null) {
	                    response.append(inputLine);
	                }
	                br.close();
	                System.out.println(response.toString());
	            } else {
	                System.out.println("error !!!");
	            }
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	    }
	 
	 
}
