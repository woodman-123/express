package express.crm.service;

import java.util.List;

import javax.jws.WebService;

import com.heima.express.crm.entity.TCustomer;

@WebService
public interface CustomerService {

	
	//查询还没关联承包区的客户
	List<TCustomer> findCustomersNoAss();
	
	//根据承包区id查询该承包区已经关联的客户
	List<TCustomer> findCustomersByDzoneid(String dzid);
	
	//把选中的客户添加到承包区,(往多个客户添加承包区id就可以了)
	void addAssCustomers(String dzid,int[] cids);
	
	
	//注册
	void addCustomerForRegister(TCustomer customer);
	
	//根据电话号码查询客户，用于页面回显
	TCustomer findCustomerByTel(String tel);
	
}
