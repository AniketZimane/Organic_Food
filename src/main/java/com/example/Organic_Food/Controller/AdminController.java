package com.example.Organic_Food.Controller;

import com.example.Organic_Food.Entity.Product;
import com.example.Organic_Food.Entity.ProductStock;
import com.example.Organic_Food.Helper.FileUploader;
import com.example.Organic_Food.Helper.ProductHelper;
import com.example.Organic_Food.Repo.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    ProductRepo productRepo;
    @Autowired
    ReviewRepo reviewRepo;
    @Autowired
    BlogRepo blogRepo;
    @Autowired
    FeatureRepo featureRepo;
    @Autowired
    FileUploader uploader;
    @Autowired
    ProductHelper productHelper;
    @Autowired
    ProductStockRepo productStockRepo;

    @GetMapping("/admin/product/report/")
    public String getAddBlogPage(Model model)
    {
        List<Product> productList=productRepo.findAll();
        model.addAttribute("productList",productList);
        return "product_report";
    }

    @GetMapping("/admin/product/delete/{id}/")
    public String deleteResultRecord(Model model, @PathVariable int id)
    {
        productRepo.deleteById(id);
        List<Product> productList = productRepo.findAll();
        model.addAttribute("productList", productList);
        return "product_report";
    }

    @GetMapping("/admin/update/product/{id}/")
    public String updateProductRecord(Model model, @PathVariable int id)
    {
        Product data = productRepo.getReferenceById(id);
        model.addAttribute("data", data);
        return "add_product";
    }

    @GetMapping("/admin/order/")
    public String orderPage()
    {
        return "order";
    }

    @ResponseBody
    @PostMapping("/admin/passData/")
    public String handlePostRequest(Model model, @RequestBody ProductHelper productHelper ) {
        Integer closingBal=productStockRepo.findClosingStockByProductId(productHelper.getProductId());
        System.out.println(closingBal);
        List <ProductStock> productStockList=productStockRepo.findProductStockByProductId(productHelper.getProductId());
        System.out.println(productStockList);
        Integer productId;
        Integer openingBal;
        Integer returnQty;
        Integer issueQty;
        Integer closingQty;
        LocalDateTime systemDate;
        for (ProductStock i:productStockList) {
            productId=productHelper.getProductId();
            openingBal=i.getClosingQty();
            System.out.println(openingBal);
            returnQty=i.getReturnQty();
            issueQty=productHelper.getProductQty();
            closingQty=closingBal-productHelper.getProductQty();
            systemDate=LocalDateTime.now();
            if(closingBal>=productHelper.getProductQty())
            {
                ProductStock productStock=new ProductStock(productId,openingBal,returnQty,issueQty,closingQty,systemDate);
                productStockRepo.save(productStock);
            }
            else{
                model.addAttribute("errorMsg","Currently Stock Empty");
            }
        }

        return "Data get it";
    }

}
