package com.cafe24.shop.util;

import com.cafe24.shop.utils.ApiUtils;
import com.cafe24.shop.utils.ApiUtils.ApiResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;

public class ApiUtilsTest {

  private final ObjectMapper objectMapper = new ObjectMapper();

  private static final Map<String, Object> responseStringMessage = new HashMap<String, Object>() {{
    put("success", (boolean) true);
    put("response", "TEST");
    put("error", null);
  }};
  private static final Map<String, Object> responseIntegerMessage = new HashMap<String, Object>() {{
    put("success", (boolean) true);
    put("response", 123);
    put("error", null);
  }};

  private static final Map<String, Object> responseCollectionMessage = new HashMap<String, Object>() {{
    put("success", (boolean) true);
    put("response", new ArrayList<Integer>(Arrays.asList(1, 2, 3)));
    put("error", null);
  }};

  private static final Map<String, Object> responseDoubleMessage = new HashMap<String, Object>() {{
    put("success", (boolean) true);
    put("response", 123.0);
    put("error", null);
  }};

  private static final Map<String, Object> responseBooleanMessage = new HashMap<String, Object>() {{
    put("success", (boolean) true);
    put("response", true);
    put("error", null);
  }};

  private static final Map<String, Object> responseObjectMessage = new HashMap<String, Object>() {{
    put("success", (boolean) true);
    put("response", new TestObject("type_test", "name_test"));
    put("error", null);
  }};

  private static final Map<String, Object> responseFailMessage = new HashMap<String, Object>() {{
    put("success", false);
    put("response", null);
    put("error", new HashMap<String, Object>() {{
      put("message", "fail");
      put("status", 200);
    }});
  }};

  @Test
  public void scalaBooleanSuccess() throws JsonProcessingException {
    ApiResult<Boolean> apiResult = ApiUtils.success(true);
    String result = objectMapper.writeValueAsString(apiResult);
    String expected = objectMapper.writeValueAsString(responseBooleanMessage);
    Assert.assertEquals(result, expected);
  }

  @Test
  public void scalaDoubleSuccess() throws JsonProcessingException {
    ApiResult<Double> apiResult = ApiUtils.success(123.0);
    String result = objectMapper.writeValueAsString(apiResult);
    String expected = objectMapper.writeValueAsString(responseDoubleMessage);
    Assert.assertEquals(result, expected);
  }

  @Test
  public void scalaIntegerSuccess() throws JsonProcessingException {
    ApiResult<Integer> apiResult = ApiUtils.success(123);
    String result = objectMapper.writeValueAsString(apiResult);
    String expected = objectMapper.writeValueAsString(responseIntegerMessage);
    Assert.assertEquals(result, expected);
  }

  @Test
  public void scalaStringSuccess() throws JsonProcessingException {
    ApiResult<String> apiResult = ApiUtils.success("TEST");
    String result = objectMapper.writeValueAsString(apiResult);
    String expected = objectMapper.writeValueAsString(responseStringMessage);
    Assert.assertEquals(result, expected);
  }

  @Test
  public void collectionSuccess() throws JsonProcessingException {
    List<Integer> list = new ArrayList<Integer>() {{
      add(1);
      add(2);
      add(3);
    }};
    ApiResult<List<Integer>> apiResult = ApiUtils.success(list);
    String result = objectMapper.writeValueAsString(apiResult);
    String expected = objectMapper.writeValueAsString(responseCollectionMessage);
    Assert.assertEquals(result, expected);
  }

  @Test
  public void objectSuccess() throws JsonProcessingException {
    TestObject testObject = new TestObject("type_test", "name_test");
    ApiResult<TestObject> apiResult = ApiUtils.success(testObject);
    String result = objectMapper.writeValueAsString(apiResult);
    String expected = objectMapper.writeValueAsString(responseObjectMessage);
    Assert.assertEquals(result, expected);
  }


  @Test
  public void testError() throws JsonProcessingException {
    ApiResult<?> apiResult = ApiUtils.error("fail", HttpStatus.OK);
    String result = objectMapper.writeValueAsString(apiResult);
    String expected = objectMapper.writeValueAsString(responseFailMessage);
    Assert.assertEquals(result, expected);
  }

  private static class TestObject {

    private String type;
    private String name;

    public TestObject(String type, String name) {
      this.type = type;
      this.name = name;
    }

    public String getType() {
      return type;
    }

    public String getName() {
      return name;
    }
  }
}

