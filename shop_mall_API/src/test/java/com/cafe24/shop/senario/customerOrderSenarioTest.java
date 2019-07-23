package com.cafe24.shop.senario;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.cafe24.shop.api.CustomerControllerTest;
import com.cafe24.shop.api.ProductControllerTest;

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
