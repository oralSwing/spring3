package cn.itcast.spring.a_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxyFactory implements InvocationHandler{
	//目标对象
	private Object target;
	
	//构造注入
	public JdkProxyFactory(Object target) {
		this.target=target;
	}
	
	//获取代理对象
	public Object getProxyObject(){
		
		//参数：1：目标对象的类加载器，2，目标对象的接口，回调的接口
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), 
				target.getClass().getInterfaces(), this );
	}

	//内部类
//	private class myu implements InvocationHandler{
//
//		@Override
//		public Object invoke(Object proxy, Method method, Object[] args)
//				throws Throwable {
//			return null;
//		}
//		
//	}
	
	@Override
	//写一些方法调用相关的代码，增强代码都在这里实现
	//参数：1，代理对象，2，method可以获取目标原来的方法，3.args目标对象的原来的方法中的参数
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		//在方法调用之前增强（添加额外的功能，添加额外的代码）
//		System.out.println("开始记录日志了。。。。");
		writeLog();
		//调用原来目标的方法
		//第一个参数：目标对象，第二个参数是目标对象的参数
		Object object = method.invoke(target, args);
		
		//在原来的方法之后增强
		System.out.println("日志记录结束了。。。");
		
		return object;//将原来的目标对象返回回去
	}
	
	private void writeLog(){
		System.out.println("开始记录日志了。。。。");
	}
	


}
