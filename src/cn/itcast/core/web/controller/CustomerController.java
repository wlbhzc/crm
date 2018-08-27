package cn.itcast.core.web.controller;

import cn.itcast.common.utils.Page;
import cn.itcast.core.bean.BaseDict;
import cn.itcast.core.bean.Customer;
import cn.itcast.core.bean.User;
import cn.itcast.core.service.CustomerService;
import cn.itcast.core.service.SystemService;
import cn.itcast.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;


/**
 *
 * <p>Title: CustomerController</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p>
 * @version 1.0
 */
@Controller
@SessionAttributes("user")
public class CustomerController {


	@Autowired
	private CustomerService customerService;
	@Autowired
	private SystemService systemService;
	@Value("${customer.from.type}")
	private String FROM_TYPE;
	@Value("${customer.industry.type}")
	private String INDUSTRY_TYPE;
	@Value("${customer.level.type}")
	private String LEVEL_TYPE;
	@Autowired
	private UserService userService;


	@RequestMapping(value="/user/gologin")
	public String gologin(HttpSession session) {
		session.invalidate();
		return "login";
	}

	@RequestMapping(value="/user/register")
	public String RegisterableService(User user) {
		userService.register(user);


		return "login";
	}

	@RequestMapping(value="/user/login")
	public String LoginableService(User user,HttpSession session) {
		user = userService.login(user.getUsername(), user.getUserpassword());

		if(user != null) {
			session.setAttribute("user", user);
			return "redirect:/customer/list.action";
		}


		return "login";

	}




	// 主页
	@RequestMapping(value = "/customer/list.action")
	public String list(@RequestParam(defaultValue="1")Integer page, @RequestParam(defaultValue="10")Integer rows,
					   String custName, String custSource,	String custIndustry, String custLevel, Model model) {

		Page<Customer> customers = customerService.findCustomerList(page, rows, custName, custSource, custIndustry,
				custLevel);
		model.addAttribute("page", customers);

		List<BaseDict> fromType = systemService.findBaseDictListByType(FROM_TYPE);

		List<BaseDict> industryType = systemService.findBaseDictListByType(INDUSTRY_TYPE);

		List<BaseDict> levelType = systemService.findBaseDictListByType(LEVEL_TYPE);
		model.addAttribute("fromType", fromType);
		model.addAttribute("industryType", industryType);
		model.addAttribute("levelType", levelType);

		model.addAttribute("custName", custName);
		model.addAttribute("custSource", custSource);
		model.addAttribute("custIndustry", custIndustry);
		model.addAttribute("custLevel", custLevel);
		return "customer";
	}

	//去修改界面
	@RequestMapping("/customer/edit")
	@ResponseBody
	public Customer getCustomerById(Long id) {
		Customer customer = customerService.getCustomerById(id);
		return customer;
	}

	//更新修改界面数据
	@RequestMapping("/customer/update")
	@ResponseBody
	public String customerUpdate(Customer customer) {
		customerService.updateCustomer(customer);
		return "OK";
	}
	// 删除
	@RequestMapping("/customer/delete")
	@ResponseBody
	public String customerDelete(Long id) {
		customerService.deleteCustomer(id);
		return "OK";
	}

	// 去添加页面
	@RequestMapping("/customer/goinsert")
	public String goinsert(Model model, String custSource,String custIndustry,String custLevel) {
		List<BaseDict> fromType = systemService.findBaseDictListByType(FROM_TYPE);

		List<BaseDict> industryType = systemService.findBaseDictListByType(INDUSTRY_TYPE);

		List<BaseDict> levelType = systemService.findBaseDictListByType(LEVEL_TYPE);
		model.addAttribute("fromType", fromType);
		model.addAttribute("industryType", industryType);
		model.addAttribute("levelType", levelType);


		model.addAttribute("custSource", custSource);
		model.addAttribute("custIndustry", custIndustry);
		model.addAttribute("custLevel", custLevel);
		return "insert";
	}

	// 执行添加操作
	@RequestMapping("/customer/insert")

	public String insert(Customer customer) {


		customerService.insert(customer);
		return "redirect:/customer/list.action";
	}




}