package com.example.restclient.controller;

import com.example.restclient.model.Product;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private RestTemplate restTemplate;

    private final String PRODUCT_URI = "http://localhost:8080/product/";

    @GetMapping("/product/list")
    public String getListProduct(Model model){//rest client hit rest-server
        ResponseEntity<List<Product>> responseEntity=restTemplate.exchange(PRODUCT_URI, HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>() {
        });
       List <Product> product=responseEntity.getBody();
        model.addAttribute("products", product);
        return "product-list";
    }

    @GetMapping("/form")
    public ModelMap showForm(Product product) {
        if (product == null) {
            product = new Product();
        }

            return new ModelMap().addAttribute("product", product);
    }

    @GetMapping("/form-update/{id}")
    public String showUpdateForm(@PathVariable final  Long id, Model model) {
        Product product=restTemplate.getForObject(PRODUCT_URI+id, Product.class);
        model.addAttribute("product", product);
        return "form-update";
    }

    @PostMapping("/product/save")
    public String saveProduct(@ModelAttribute("product") Product product) {
        Product productUpdate = null;
        if(product.getId()!=null){
            productUpdate=restTemplate.getForObject(PRODUCT_URI+ product.getId(), Product.class);
        }
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String jsonObject = new Gson().toJson(product);

        HttpEntity<String> productHttpEntity= new HttpEntity<>(jsonObject, headers);
        if(productUpdate!=null){
            ResponseEntity<Product> response = restTemplate
                    .exchange(PRODUCT_URI, HttpMethod.PUT, productHttpEntity, Product.class);
        }else {
            ResponseEntity<Product> response = restTemplate
                    .exchange(PRODUCT_URI, HttpMethod.POST, productHttpEntity, Product.class);
        }

        return "redirect:/product/list";
    }

    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable final Long id){
        restTemplate.delete(PRODUCT_URI+id);
        return "redirect:/product/list";
    }

}
