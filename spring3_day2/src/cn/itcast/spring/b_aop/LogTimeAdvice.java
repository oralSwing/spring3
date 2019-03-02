package cn.itcast.spring.b_aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

//传统aop的环绕增强,必须实现接口
public class LogTimeAdvice  implements MethodInterceptor{
	//日志记录器
	private static final Logger LOG =Logger.getLogger(LogTimeAdvice.class);

	@Override
	//编写增强的代码，回调方法(目标的方法)
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		//需求：记录方法运行时间
		//开始时间
		long beginTime = System.currentTimeMillis();
		//要执行原来的目标的方法
		Object object = methodInvocation.proceed();
		//结束时间
		long endTime = System.currentTimeMillis();
		//计算执行的事件
		long  runTime =endTime-beginTime;
		
		//将时间写入日志log4j，便于以后查看
		LOG.info(methodInvocation.getMethod().getName()+"方法的运行时间是："+runTime+"毫秒");
		
		return object;//返回目标对象
	}

}
