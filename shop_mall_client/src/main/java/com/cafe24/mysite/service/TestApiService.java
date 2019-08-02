package com.cafe24.mysite.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.TestApiDao;

import okhttp3.Response;

@Service
public class TestApiService {
	    
	    @Autowired
	    TestApiDao testApiDao;
	  
	   public Response addScriptTag(String src, String display_locations) throws IOException {
	      return testApiDao.addScriptTag(src, display_locations);
	   }

	   public Response getScriptTags() throws IOException {
	      return testApiDao.getScriptTag();
	   }

	public Response deleteScriptTag(Long script_no) throws IOException {
		return testApiDao.deleteScriptTag(script_no);
	}

//	   public Response removeScriptTag() throws IOException {	
//	      return response;
//	   }

}
