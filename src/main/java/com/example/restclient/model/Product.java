package com.example.restclient.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
public class Product {
    private Long id;
    private String productName;
    private String expiredDate;
    private int stock;
}
