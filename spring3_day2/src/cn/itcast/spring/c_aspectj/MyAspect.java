package cn.itcast.spring.c_aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

//aspectj的增强类，就是一个普通的pojo，不需要实现任何即可偶
public class MyAspect {

	//前置增强
	public void before1(){
		System.out.println("before1对方法进行前置增强了");
	}
	public void before2(){
		System.out.println("before2对方法进行前置增强了");
	}
	
	//前置通知:	权限控制 （权限不足，抛出异常）、 记录方法调用信息日志 
	//该方法是在目标对象的实际方法执行之前被调用
	//该方法，可以接收一个参数：joinPoint（它是连接点（方法）的信息，换句话说，就是被拦截到的方法的一些相关信息的包装）
	public void before(JoinPoint joinPoint){
		//
		System.out.println("方法："+joinPoint.getSignature().getName()+"被拦截了");
		System.out.println("增强的目标对象："+joinPoint.getTarget().getClass().getName()+"被拦截了");
		//在这里做一些判断，（判断当前操作用户是否有权限来继续执行该方法）
//		if(权限不足){
			throw new RuntimeException("权限不足！");
//		}
		//如果权限够，则放行
		
	}
	
	//后置通知：网上营业厅查询余额后，自动下发短信。
	//在目标对象方法执行之后增强(拦截的时机是目标对象已经执行后已经有返回了的时候)
	//参数：JoinPoint连接点，Object returnVal返回值（方法执行之后的返回值），名字随便，但必须和配置文件中的一致
	public void afterReturing(JoinPoint joinPoint,Object returnVal){
		//比如，该方法，模拟查询花费，返回话费的数量
		//打印模拟发短信动作
		System.out.println("发短信：查询的话费结果是："+returnVal);
		//短信下发完，可以记录一下相关信息
		System.out.println(joinPoint.getSignature().getName()+"被调用了，返回结果为："+returnVal);
		
	}
	
	//环绕通知：日志、缓存、权限、性能监控、事务管理
	//在目标对象方法执行的前后进行增强
	//参数：第一个参数：正在执行的连接点，
	//该方法，必须抛出Throwable异常
	//该方法的返回值，必须是Object
	public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		//原来的方法运行之前开启事务
		System.out.println("事务开启了。。。。session.beginTre...");
		
		Object object = proceedingJoinPoint.proceed();//执行了目标对象的方法
		
		//在方法之后提交事务
		System.out.println("事务提交。。。。session.gettransation.commit");
		return object;//返回目标对象
	}
	
	//抛出通知:记录异常日志、通知管理员（短信、邮件）
	//目标方法发生异常的情况下，可以进行拦截增强（不需要在代码中写try catch）
	//注意第二个参数，参数的类型，必须是Throwable，参数名字后面会被配置到spring中
	public void afterThrowing (JoinPoint joinPoint,Throwable ex){
		//当异常出现的时候，通知管理员，发信息，或发邮件，并记录日志
		System.out.println("发信息给管理员："+ex.getMessage());
	}
	
	//最终通知：释放资源 （关闭文件、 关闭数据库连接、 网络连接、 释放内存对象 ）
	//目标方法不管是否发生异常，都执行该增强
	public void after(JoinPoint joinPoint){
		//模拟释放资源了。。。
		System.out.println("释放连接了。。。。。");
	}
	
	
	
}
