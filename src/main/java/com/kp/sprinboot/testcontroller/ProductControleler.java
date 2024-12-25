package com.kp.sprinboot.testcontroller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kp.sprinboot.ProductEntity.ProductEntity;
import com.kp.sprinboot.productmodel.ProductModel;
import com.kp.sprinboot.service.ProductService;

import jakarta.validation.Valid;
@Controller 
public class ProductControleler{
	
@Autowired
ProductService productService;
	
//@GetMapping("/form")
//public String greet() {
//	return "Add_Product";
//}

//
//@PostMapping("/saveProduct")
//public String ScaveProduct( @ModelAttribute ProductModel productModel) {
//	System.out.println(productModel);
//	productService.SaveProductDetails(productModel);
//	return "Success";
//}
@GetMapping("/contact")
	public String contact() {
		return "contact";
	}


@GetMapping("/about")
public String about() {
	return "about";
}

@GetMapping("/getAllProduct")
public String getAllProduct(Model model) {
	List<ProductEntity>products =productService.getAllProduct();
	model.addAttribute("products" ,products);
	return "Product_list";
	
}
@GetMapping("/search")
public String search() {
	return "searchbyid";
}
@PostMapping("/searchbyid")
public String search(@RequestParam long id,Model model) {
	ProductEntity product =productService.serachbyid(id);
	model.addAttribute("product",product);
	return "searchbyid";
}

// For delete the product
@GetMapping("/delete/{id}")
public String DeleteByPruductId(@PathVariable("id") long id) {
	productService.DeleteByProductId(id);
	return "redirect:/getAllProduct";
}
//Add The product by using button
@GetMapping("/addProduct")
public String showAddProductForm() {
    return "Add_Product"; // Ensure the Add_Product.html template exists
}

@GetMapping("/showAllProducts")
public String All(Model model) {
	List<ProductEntity>products =productService.getAllProduct();
	model.addAttribute("products" ,products);
	return "Product_list";

}
@GetMapping("/editProduct/{id}")
public String edit(@PathVariable("id") long id, Model model) {
	ProductEntity pEntity = productService.editbyid(id);
	model.addAttribute("id",id);
	model.addAttribute("product", pEntity);
    return "edit";
}
@PostMapping("/editproductsave/{id}")
public String updateProduct(@PathVariable("id") long id, @ModelAttribute ProductModel products) {
    productService.updateProduct(id,products); // Save updated product details
    return "redirect:/getAllProduct"; // Redirect to the product list page
}


// gettting the data by using some values
@GetMapping("/form")
public String getproductform(Model model) {
	ProductModel productModel =new ProductModel();
	productModel.setMadeIn("India");
	productModel.setQuantity(2);
	productModel.setDiscountrate(10.5);
	model.addAttribute("productModel",productModel);
	return "Add_Product";
}
// getting form using the validation
@PostMapping("/saveProduct")
public String savaproduct(@Valid ProductModel productModel,BindingResult bindingResult ,Model model) {
	// for adding the errors in the hash map
	 HashMap<String, String> validationerror=new HashMap<>();
	 if(bindingResult.hasErrors()) {
		 // use for each loof for read the error field by field and add into the hashmap
		 for (FieldError fielderror : bindingResult.getFieldErrors()) {
			validationerror.put(fielderror.getField(),fielderror.getDefaultMessage());
		}
		 model.addAttribute("validationerror",validationerror);
		 return "Add_Product";
	 }
	 productService.SaveProductDetails(productModel);
	 return "redirect:/getAllProduct";
	
	 
}
}


 
