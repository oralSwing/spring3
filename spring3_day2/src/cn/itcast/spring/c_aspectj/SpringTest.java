package cn.itcast.spring.c_aspectj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.spring.a_proxy.CustomerService;
import cn.itcast.spring.a_proxy.ProductService;
@RunWith(SpringJUnit4ClassRunner.class)//将spring和junuit进行继承，并且自动打开注解
@ContextConfiguration(locations="classpath:applicationContext-aspectj.xml")//配置容器核心配置文件，会自动创建容器
public class SpringTest {
	//注入service
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ProductService productService;
	
	@Test
	public void test(){
		//客户
		customerService.save();
		customerService.update();
		customerService.findCount();
		
		//产品
		productService.save();
		productService.update();
		
		
	}
}
