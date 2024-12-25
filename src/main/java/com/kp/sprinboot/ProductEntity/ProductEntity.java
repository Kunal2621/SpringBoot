package com.kp.sprinboot.ProductEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Product")
public class ProductEntity {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
      private long id;
      private String name;
      private String brand;
      private String madeIn;
      private double price;
      private int quantity;
      private double discountrate;
      private double taxrate;
      private double disprice;
      private double offerpirce;
      private double finalprice;
      private double stackvalue;
}
