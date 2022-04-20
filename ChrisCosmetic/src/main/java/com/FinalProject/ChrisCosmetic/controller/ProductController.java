package com.FinalProject.ChrisCosmetic.controller;

import com.FinalProject.ChrisCosmetic.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/{id}")
    public String viewProductPage(@PathVariable("id") Long id, Model model){
        model.addAttribute("products", productService.findProductBySubCategoryID(id));
        return "product";
    }

    @GetMapping("/category/{id}")
    public String viewProductCategoryID(@PathVariable("id") Long id, Model model){
        model.addAttribute("products", productService.findProductsByCategoryID(id));
        return "product";
    }
}
