package com.kp.sprinboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kp.sprinboot.ProductEntity.ProductEntity;
import com.kp.sprinboot.productmodel.ProductModel;
import com.kp.sprinboot.productrepository.ProductRepository;

@Service
public class ProductService {
@Autowired
ProductRepository productRepository;
public void SaveProductDetails(ProductModel productModel) {
	double stockvalue;
	stockvalue =productModel.getPrice()*productModel.getQuantity();
	double disprice;
	disprice =productModel.getPrice()*productModel.getDiscountrate()/100;
	double offerprice=productModel.getPrice()-disprice;
	double taxrate =18.00;
	double finalprice =offerprice+(offerprice*taxrate)/100;
	ProductEntity productEntity =new ProductEntity();
	productEntity.setName(productModel.getName());
	productEntity.setBrand(productModel.getBrand());
	productEntity.setDiscountrate(productModel.getDiscountrate());
	productEntity.setDisprice(disprice);
	productEntity.setFinalprice(finalprice);
	productEntity.setTaxrate(taxrate);
	productEntity.setMadeIn(productModel.getMadeIn());
	productEntity.setOfferpirce(offerprice);
	productEntity.setPrice(productModel.getPrice());
	productEntity.setStackvalue(stockvalue);
	productEntity.setQuantity(productModel.getQuantity());
	productRepository.save(productEntity);
	
}
public List<ProductEntity> getAllProduct(){
	List<ProductEntity> products =productRepository.findAll();
	return products;
}
public ProductEntity serachbyid(long id) {
	Optional<ProductEntity>optionalData =productRepository.findById(id);
	if(optionalData.isPresent()) {
	ProductEntity product =optionalData.get();
	return product;
	}
	else {
		return null;
	}
}
public void DeleteByProductId(long id) {
	// TODO Auto-generated method stub
	productRepository.deleteById(id);
}


public ProductEntity editbyid(long id) {
    // Fetch the existing product by ID
	Optional<ProductEntity>product =productRepository.findById(id);
	if(product.isPresent()) {
		return product.get();
	}
	else {
		return null;
	}
           
}

public void updateProduct(long id, ProductModel  products) {
    // Fetch the existing product by ID
    ProductEntity existingProduct = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

    // Update fields using the ProductModel values
    existingProduct.setName(products.getName());
    existingProduct.setBrand(products.getBrand());
    existingProduct.setMadeIn(products.getMadeIn());
    existingProduct.setPrice(products.getPrice());
    existingProduct.setQuantity(products.getQuantity());
    existingProduct.setDiscountrate(products.getDiscountrate());

    // Recalculate dependent fields
    double discount = (existingProduct.getPrice() * existingProduct.getDiscountrate()) / 100;
    existingProduct.setDisprice(discount);

    double tax = (existingProduct.getPrice() - discount) * 0.10; // Assuming 10% tax rate
    existingProduct.setTaxrate(tax);

    double finalPrice = existingProduct.getPrice() - discount + tax;
    existingProduct.setFinalprice(finalPrice);

    existingProduct.setStackvalue(finalPrice * existingProduct.getQuantity());

    // Save the updated product to the repository
    productRepository.save(existingProduct);
}




}

