package org.smart4j.framework.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.annotation.Service;
import org.smart4j.framework.annotation.Transaction;

@Service
public class ServiceTest {
	private static final Logger LOGGER=LoggerFactory.getLogger(ServiceTest.class);
	@Transaction
	public void print(){
		LOGGER.info("日志信息正常打印出来");
		System.out.println("Hello world!");
	}

}
