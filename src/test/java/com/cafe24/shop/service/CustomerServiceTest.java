package com.cafe24.shop.service;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cafe24.shop.BootApp;
import com.cafe24.shop.service.CustomerService;
import com.cafe24.shop.vo.CustomerVo;


// ApplicationContext 생성 
@RunWith(SpringRunner.class)
@SpringBootTest(classes= {BootApp.class})
public class CustomerServiceTest {
	
	//Test용 데이터 생성(DB)
	CustomerVo vo = new CustomerVo(100L, "ksm5318@naver.com", "123123");
	CustomerVo vo1 = new CustomerVo(101L, "성공테스트", "EMAIL@TEST.COM", "PASSWORD1!", "010-1234-1234", "M", 1L, "Y");
	
	@Autowired
	CustomerService customerService;
	
	@Before
	public void setup() {
		assertNotNull(customerService);
	}
	
	/**
	 * vo에 들어간 email이 db에 있다면 true 
	 */
	@Test
	public void exist_email_test() {
		assertThat(customerService.exist_email("ksm5318@naver.com"), is(true));
	}		

	
	/**
	 * vo에 들어간 email이 db에 없다면 false 
	 */
	@Test
	public void no_exist_email_test() {
		assertThat(customerService.exist_email("email@naver.com"), is(false));
	}
	
	/**
	 * insert 성공 시 true, 실패 시 false
	 */
	@Test
	public void join_test() {
		assertThat(customerService.join(vo1), is(true));
	}
	
	/**
	 * update 성공 시 true, 실패 시 false
	 */
	@Test
	public void update_test() {
		assertThat(customerService.update_user(vo1), is(true));
	}
	
//	/**
//	 * delete(update use_fl = 'N') 성공 시 true, 실패 시 false
//	 */
//	@Test
//	public void delete_test() {
//		assertThat(customerService.delete(vo1), is(true));
//	}
	
	
	
	
}
