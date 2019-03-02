package cn.itcast.spring.a_proxy;
//产品：没有接口
public class ProductService {
	//保存
	public void save(){
		System.out.println("ProductService:产品保存");
	}
	
	//修改
	public void update(){
		System.out.println("ProductService:产品修改");
	}

}
