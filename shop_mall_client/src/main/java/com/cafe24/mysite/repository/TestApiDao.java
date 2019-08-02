package com.cafe24.mysite.repository;

import java.io.IOException;

import org.springframework.stereotype.Repository;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Repository
public class TestApiDao {
	String mailid = "ksm53182";
	String accessToken = "5fktqft2p0bnuV93anCvBG";
	String version = "";
	Request request;

	public Response addScriptTag(String src, String display_locations) throws IOException {

		MediaType mediaType = MediaType.parse("application/json");
		String body_data = "{\n" + "    \"shop_no\": 1,\n" + "    \"request\": {\n"
				+ "        \"src\": \"http://saleapp.cafe24.com/commons.js\",\n" + "        \"display_location\": [\n"
				+ "            \"ORDER_BASKET\"\n" + "        ]\n" + "    }\n" + "}";
		
		RequestBody body = RequestBody.create(mediaType, body_data);

		request = api_post(body);

		OkHttpClient client = new OkHttpClient();
		Response response = client.newCall(request).execute();
		return response;
	}

	public Response getScriptTag() throws IOException {
		request = api_get();

		OkHttpClient client = new OkHttpClient();
		Response response = client.newCall(request).execute();
		return response;
	}

	public Response removeScriptTag() throws IOException {
		request = api_get();

		OkHttpClient client = new OkHttpClient();
		Response response = client.newCall(request).execute();
		return response;
	}
	
	public Response deleteScriptTag(Long script_no) throws IOException {
		request = api_delete(script_no);
		OkHttpClient client = new OkHttpClient();
		Response response = client.newCall(request).execute();
		return response;
	}
	

	
	public Request api_post(RequestBody body) {
		request = new Request.Builder().url("https://ksm53182.cafe24api.com/api/v2/admin/scripttags")
				.addHeader("authorization", "Bearer " + accessToken).addHeader("content-type", "application/json")
				.post(body).build();
		return request;
	}

	public Request api_get() {
		request = new Request.Builder().url("https://ksm53182.cafe24api.com/api/v2/admin/scripttags")
				.addHeader("authorization", "Bearer " + accessToken).addHeader("content-type", "application/json").get()
				.build();
		return request;
	}
	
	public Request api_delete(Long script_no) {
		request = new Request.Builder().url("https://ksm53182.cafe24api.com/api/v2/admin/scripttags/"+script_no)
				.addHeader("authorization", "Bearer " + accessToken).addHeader("content-type", "application/json").delete()
				.build();
		return request;
	}

	
}
