package org.example.controller;

import org.example.dao.ProductDao;
import org.example.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class MainController {

	@Autowired
	private ProductDao productDao;

	@RequestMapping("/")
public String home(Model model) {
		List<Product>product=productDao.getProducts();
	model.addAttribute( "product",product);
	return "index";
}
@RequestMapping("/add-product")
public String addProduct(Model model)
{
model.addAttribute("title","Add product");
	return "add_product_form";
}
//handle add product form
	@RequestMapping(value ="/handle-product",method=RequestMethod.POST)
	public RedirectView handleProduct(@ModelAttribute Product product, HttpServletRequest request){
		System.out.println("product");
		System.out.println(product);
		productDao.createProduct(product);
		RedirectView redirectView=new RedirectView();
		redirectView.setUrl(request.getContextPath()+"/");
		return redirectView;
	}
	//delete
	@RequestMapping("/delete/{productId}")
	public RedirectView deleteProduct(@PathVariable ("productId") int productId ,HttpServletRequest request) {
		this.productDao.deleteproduct(productId);
		RedirectView redirectView=new RedirectView();
		redirectView.setUrl(request.getContextPath()+"/");
		return redirectView;
	}
	@RequestMapping("/update/{productId}")
	public String updateForm(@PathVariable("productId") int pid,Model model)
	{
		Product product=this.productDao.getProduct(pid);
		model.addAttribute("product",product);
				return "update_form";
	}
}
