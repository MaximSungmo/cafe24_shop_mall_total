package com.cafe24.mysite.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.mysite.service.TestApiService;
import com.cafe24.mysite.vo.TestVo;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import okhttp3.Response;


@Controller
public class TestApiController {
	
	@Autowired
	TestApiService testApiService;
	
  
	Response response;
	@RequestMapping(value =  {"/api/scripttag"}, method = RequestMethod.GET)
	public String main(Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "API 테스트");
		model.addAttribute("map", map);
		
		response = testApiService.getScriptTags();
		String str = response.body().string();
        model.addAttribute("script_no",str.split(":")[3].split(",")[0]);
		model.addAttribute("vo",str);		

		return "test/script";
	}
	
	
	@RequestMapping(value =  {"/api/scripttag"}, method = RequestMethod.POST)
	public String insert_api(Model model, @ModelAttribute TestVo testvo) throws IOException {
		// 변수 받아서 입력하기 
//		String display_locations= testvo.getDisplay_locations();
//		String src = testvo.getSrc();
		String display_locations= "";
		String src = "";				
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "API 테스트");
		model.addAttribute("map", map);
		Response response = testApiService.addScriptTag(src, display_locations);	     
        model.addAttribute("response",response.toString()+"post작업시 나오는 값");
		return "test/script";
	}
	
	@RequestMapping(value =  {"/api/scripttag/{script_no}"}, method = RequestMethod.GET)
	public String insert_api(Model model, @PathVariable Long script_no ) throws IOException {		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "API 테스트");
		model.addAttribute("map", map);
		
		Response response = testApiService.deleteScriptTag(script_no);	     
        model.addAttribute("response",response.toString()+"delete작업시 나오는 값");
		return "test/script";
	}
		 
	
	/**
	 * HttpURLConnection 
	 */
//	 public void get_connection(String method, String url_,TestVo parameter) throws IOException {
//		 	method = method.toUpperCase();
//		 	String src ="";
//		 	String display_location ="";
//		 	
//		 	
//		 	String json = new ObjectMapper().writeValueAsString(parameter);
//		 	
//		 	if(parameter!=null) {
//			 	src = ((TestVo)parameter).getSrc();
//			 	display_location = ((TestVo)parameter).getDisplay_location();
//		 	}
//			URL url = new URL("https://ksm53182.cafe24api.com/api/v2/admin/scripttags");		    
//		    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//		    conn.setRequestMethod(method); 
//		    conn.setRequestProperty("authorization", "Bearer "+accessToken);
//		    conn.setRequestProperty("content-type", "application/json"); 
//		    
//		    if(method.equals("POST")) {
//		    	// 연결된 connection 에서 출력도 하도록 설정 
//		        conn.setDoOutput(true);
//		        String body = "src="+src +"&"+"display_location="+display_location;
//		        // 요청 파라미터 출력   
//		        // - 파라미터는 쿼리 문자열의 형식으로 지정 (ex) 이름=값&이름=값 형식&...
//		        // - 파라미터의 값으로 한국어 등을 송신하는 경우는 URL 인코딩을 해야 함.
//		        try (OutputStream out = conn.getOutputStream()) {
//		            out.write(json.getBytes("UTF-8"));
//		            out.flush();
//		            out.close();
//		        }
//		    }
//		    else if(method.equals("DELETE")) {
//		    	System.out.println("delete");
//		    }
//	
//		    for (Map.Entry<String, List<String>> header : conn.getHeaderFields().entrySet()) {
//		        for (String value : header.getValue()) {
//		            System.out.println(header.getKey() + " : " + value);
//		        }
//		    }
//		    
//		    try (InputStream in = conn.getInputStream();
//		            ByteArrayOutputStream out = new ByteArrayOutputStream()) {
//		        
//		        byte[] buf = new byte[1024 * 8];
//		        int length = 0;
//		        while ((length = in.read(buf)) != -1) {
//		            out.write(buf, 0, length);
//		        }
//		        System.out.println(new String(out.toByteArray(), "UTF-8"));            
//		    }
//		   
//		    // 접속 해제
//		    conn.disconnect();
//		}
	 
	 
	 
	 
}

