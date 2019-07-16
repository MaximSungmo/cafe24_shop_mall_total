package com.cafe24.mysite.senario;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.cafe24.mysite.controller.CustomerControllerTest;
import com.cafe24.mysite.controller.ProductControllerTest;

import junit.framework.Test;
import junit.framework.TestSuite;

@RunWith(Suite.class)
@SuiteClasses({
	CustomerControllerTest.class,
	ProductControllerTest.class
})
public class customerOrderSenarioTest {

	public static Test suite() {
		return new TestSuite("회원 주문 시나리오 테스트");
	}
}
