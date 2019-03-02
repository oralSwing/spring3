package cn.itcast.spring.a_proxy;

//客户接口类
public interface CustomerService {
	//保存
	public void save();
	
	//更新
	public void update();
	
	//查询数量
	public int findCount();

}
