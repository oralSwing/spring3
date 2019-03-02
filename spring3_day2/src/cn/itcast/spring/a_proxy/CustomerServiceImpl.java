package cn.itcast.spring.a_proxy;

public class CustomerServiceImpl implements CustomerService{

	@Override
	public void save() {
		System.out.println("CustomerServiceImpl的save被调用");
	}

	@Override
	public void update() {
		System.out.println("CustomerServiceImpl的update被调用");
		//制造异常
		int d1=1/0;
	}

	@Override
	public int findCount() {
		System.out.println("CustomerServiceImpl的findCount被调用");
		return 100;
	}

}
