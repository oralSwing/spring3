package cn.itcast.spring.a_proxy;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

//cglib的动态代理
public class CglibProxyFactory implements MethodInterceptor {
	//目标
	private Object target;
	//注入目标对象
	public CglibProxyFactory(Object target) {
		this.target=target;
	}
	
	//得到代理对象
	public Object getProxyObject(){
		//1.使用代理生成器
		Enhancer enhancer = new Enhancer();
		
		//2.在增强器上设置两个属性，两个方法
		//设置代理子对象的父类
		enhancer.setSuperclass(target.getClass());
		//设置回调接口(接口中可以写增强的代码)
		enhancer.setCallback(this);
//		Callback
		
		//3.创建代理子对象
		return enhancer.create();
	}

	@Override
	//增强的代码
	//参数：。1.代理子对象，2.目标的方法。3.目标的参数，4.代理对象的方法（已经生成代理对象的方法）
	public Object intercept(Object proxy, Method method, Object[] args,
			MethodProxy methodProxy) throws Throwable {
		//在目标的方法执行之前增强
//		System.out.println("方法之前记录日志了");
		//扩展：只对某方法增强
		if(method.getName().equals("save")){
			System.out.println("方法之前记录日志了:只对save增强了");
		}
		
		//执行原来目标的方法：
		Object object =method.invoke(target, args);
		
		return object;
	}
	//内部类
//	private class aa implements MethodInterceptor{
//		
//	}

}
