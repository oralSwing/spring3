package cn.itcast.spring.a_proxy;

import org.junit.Test;

public class SpringTest {

	@Test
	//代理增强之前
	public void testBefore(){
		CustomerService customerService = new CustomerServiceImpl();
		customerService.save();
	}
	
	//使用jdk动态代理来增强
	@Test
	public void testJdkProxy(){
		//要new一个对象，然后对这个对象生成代理对象，然后调用代理对象的方法
		//目标对象target(要增强的目标)
		CustomerService target = new CustomerServiceImpl();
		//生成代理类（根据接口来生成的代理子对象）
		JdkProxyFactory jdkProxyFactory = new JdkProxyFactory(target);
		//因为代理对象是接口的子对象，所以，可以用接口进行强转
		CustomerService customerService=(CustomerService)jdkProxyFactory.getProxyObject();
		//调用接口的方法
		customerService.save();//该方法已经被增强了
		
	}
	
	@Test
	//cglib动态代理，对方法进行增强
	public void testCglibProxy(){
		//确定目标，然后对目标生成代理对象，调用代理对象的方法
		//目标
		ProductService target = new ProductService();
		//生成代理子对象
		CglibProxyFactory cglibProxyFactory = new CglibProxyFactory(target);
		//得到代理子对象，强转（用父类）
		ProductService productService=(ProductService) cglibProxyFactory.getProxyObject();
		//调用代理对象的方法
		productService.save();
		productService.update();
		
	}
	
	
}
