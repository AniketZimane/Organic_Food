package com.example.Organic_Food.Controller;

import com.example.Organic_Food.Entity.*;
import com.example.Organic_Food.Helper.FileUploader;
import com.example.Organic_Food.Repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class matercontroller {
    ArrayList<Integer> productId=new ArrayList<Integer>();
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
    ProductStockRepo productStockRepo;

    @GetMapping("/")
    public String getMainPage(Model model)
    {
        List<Product> productList=productRepo.findAll();
        List<Blog> blogList=blogRepo.findAll();
        List<Review> reviewList=reviewRepo.findAll();
        List<Feature> featureList=featureRepo.findAll();
        model.addAttribute("featureList",featureList);
        model.addAttribute("productList",productList);
        model.addAttribute("blogList",blogList);
        model.addAttribute("reviewList",reviewList);
        return "index";
    }

    @GetMapping("/blog/")
    public String getAddBlogPage(){
        return "add_blog";
    }

    @GetMapping("/feature/")
    public String getAddFeature(){
        return "add_features";
    }

    @GetMapping("/review/")
    public String getAddReview(){
        return "add_review";
    }

    @GetMapping("/scheme/")
    public String getAddScheme(){
        return "add_scheme";
    }

    @GetMapping("/product/")
    public String getAddProduct(){
        return "add_product";
    }

    @GetMapping("/login/")
    public String getAdminLogin(){
        return "Admin_Login";
    }

    @PostMapping("/add/product/")
    public String addNewProduct(Model model, Product addProd, MultipartFile file)
    {
        System.out.println(addProd.getId());
        if(addProd.getId()==null)
        {
            String fileNameOld = file.getOriginalFilename();
            String extension = fileNameOld.substring(fileNameOld.indexOf(".") + 1);
            addProd.setExtension(extension);

            Product product = productRepo.save(addProd);
            String fileNameNew = product.getId() + "." + extension;

            uploader.uploadFile(file, fileNameNew);
            addProd.setExtension(extension);
            Integer issueQty=0;
            Integer returnQty=0;
            ProductStock productStock=new ProductStock(product.getId(),product.getStock(),issueQty,returnQty,product.getStock(),product.getSystemDateTime());
            productStockRepo.save(productStock);
            model.addAttribute("msg", "Product Uploaded Successfully");
        }
        else{
            try{
                LocalDateTime date=LocalDateTime.now();
                productRepo.save(new Product(addProd.getId(),addProd.getCategory(),addProd.getName(),addProd.getTitle(),addProd.getMnf_date(),addProd.getExp_date(),addProd.getUnit(),addProd.getPrize(),addProd.getDiscount_YN(),addProd.getDiscount(),addProd.getStock(),addProd.getExtension(),addProd.getBrief_info(),addProd.getProd_name(),addProd.getProd_add(),addProd.getProd_mob_number(),date));
                model.addAttribute("msg","Product Updated successfully!");
            }catch (Exception e)
            {
                model.addAttribute("emsg","Product not Updated yet...! Try after some time");
            }
        }

        System.out.println("Data saved..");
        List<Product> productList=productRepo.findAll();
        List<Blog> blogList=blogRepo.findAll();
        List<Review> reviewList=reviewRepo.findAll();
        List<Feature> featureList=featureRepo.findAll();
        model.addAttribute("featureList",featureList);
        model.addAttribute("productList",productList);
        model.addAttribute("blogList",blogList);
        model.addAttribute("reviewList",reviewList);
        return "index";
    }

    @PostMapping("/add/blog/")
    public String addNewBlog(Model model, Blog blog, MultipartFile file)
    {
        String fileNameOld = file.getOriginalFilename();
        String extension = fileNameOld.substring(fileNameOld.indexOf(".") + 1);
        blog.setExtension(extension);

        Blog blogAdd = blogRepo.save(blog);
        String fileNameNew = blogAdd.getId() + "." + extension;

        uploader.uploadFile(file, fileNameNew);
        blog.setExtension(extension);
        System.out.println("Data saved..");
        List<Product> productList=productRepo.findAll();
        List<Blog> blogList=blogRepo.findAll();
        List<Review> reviewList=reviewRepo.findAll();
        List<Feature> featureList=featureRepo.findAll();
        model.addAttribute("featureList",featureList);
        model.addAttribute("productList",productList);
        model.addAttribute("blogList",blogList);
        model.addAttribute("reviewList",reviewList);
        model.addAttribute("msg", "Blog Uploaded Successfully");
        return "index";
    }
    @PostMapping("/add/review/")
    public String addNewReview(Model model, Review review, MultipartFile file)
    {
        String fileNameOld = file.getOriginalFilename();
        String extension = fileNameOld.substring(fileNameOld.indexOf(".") + 1);
        review.setExtension(extension);

        Review reviewAdd = reviewRepo.save(review);
        String fileNameNew = reviewAdd.getId() + "." + extension;

        uploader.uploadFile(file, fileNameNew);
        review.setExtension(extension);
        System.out.println("Data saved..");
        List<Product> productList=productRepo.findAll();
        List<Blog> blogList=blogRepo.findAll();
        List<Review> reviewList=reviewRepo.findAll();
        model.addAttribute("productList",productList);
        model.addAttribute("blogList",blogList);
        model.addAttribute("reviewList",reviewList);
        model.addAttribute("msg", "Review Uploaded Successfully");
        return "index";
    }
    @PostMapping("/add/feature/")
    public String addNewFeature(Model model, Feature feature, MultipartFile file)
    {
        String fileNameOld = file.getOriginalFilename();
        String extension = fileNameOld.substring(fileNameOld.indexOf(".") + 1);
        feature.setExtension(extension);

        Feature featureAdd = featureRepo.save(feature);
        String fileNameNew = featureAdd.getId() + "." + extension;

        uploader.uploadFile(file, fileNameNew);
        feature.setExtension(extension);
        System.out.println("Data saved..");
        List<Product> productList=productRepo.findAll();
        List<Blog> blogList=blogRepo.findAll();
        List<Review> reviewList=reviewRepo.findAll();
        List<Feature> featureList=featureRepo.findAll();
        model.addAttribute("featureList",featureList);
        model.addAttribute("productList",productList);
        model.addAttribute("blogList",blogList);
        model.addAttribute("reviewList",reviewList);
        model.addAttribute("msg", "New Feature Uploaded Successfully");
        return "index";
    }
    @GetMapping("/add/to/card/{id}/")
    public String addToCardPage(@PathVariable int id, Model model)
    {
        List<Product> productList=productRepo.findAll();
        List<Blog> blogList=blogRepo.findAll();
        List<Review> reviewList=reviewRepo.findAll();
        model.addAttribute("productList",productList);
        model.addAttribute("blogList",blogList);
        model.addAttribute("reviewList",reviewList);
        if(productId.contains(id))
        {
            model.addAttribute("emsg","This product is already added!");
        }
        else{
            productId.add(id);
            System.out.println(productId);
        }
        return "index";
    }
    @GetMapping("/addtocart/")
    public String addToCardPage(Model model){
        List<Product> productList=productRepo.findAllById(productId);
        model.addAttribute("productList",productList);
        model.addAttribute("productId",productId);
        return "addToCart";
    }
    @GetMapping("/remove/to/card/{id}/")
    public String removeProductFromCart(@PathVariable int id, Model model)
    {
        List<Product> productList=productRepo.findAll();
        List<Blog> blogList=blogRepo.findAll();
        List<Review> reviewList=reviewRepo.findAll();
        model.addAttribute("productList",productList);
        model.addAttribute("blogList",blogList);
        model.addAttribute("reviewList",reviewList);
        String page="addToCart";
        if(productId.contains(id))
        {
            productId.remove(Integer.valueOf(id));
            int len=productId.size();
            if(len==0)
            {
                page="index";
                model.addAttribute("msg","You should have to add new product in your cart!");
            }
            else{
                model.addAttribute("msg","Product Remove Successfully!");
                page="addToCart";
            }

        }
        else{
            model.addAttribute("emsg","Product already Remove Successfully!");
            page="addToCart";
            System.out.println(productId);
        }
        return page;
    }

}
