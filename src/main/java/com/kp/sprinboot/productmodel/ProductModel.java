package com.kp.sprinboot.productmodel;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductModel {
	 @NotBlank(message = "Product name can not be blanck")
   private String name;
	   @NotBlank(message = "brand name can not be blanck")
   private String brand;
    @NotBlank(message = "Made in field can not be blanck")
   private String madeIn;
   @Positive(message = "price must be grater than zero")
   private double price;
   @Min(value = 1,message = "Quantity must be atleast 1")
   private int quantity;
   @DecimalMax(value = "100.0",message = "Discount rate can not be grater than 100.00")
   private double discountrate;
}
