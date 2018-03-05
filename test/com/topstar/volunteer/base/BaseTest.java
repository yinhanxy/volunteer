package com.topstar.volunteer.base;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
/**@Transactional(transactionManager="transactionManager",propagation=Propagation.REQUIRED)   **/
@ContextConfiguration(locations = {"classpath:spring-bean.xml","classpath:spring-mybatis.xml","classpath:spring-cache.xml","classpath:spring-validate.xml","classpath:spring-scheduler.xml"})
public class BaseTest {

	
}