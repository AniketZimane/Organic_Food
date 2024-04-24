package com.example.Organic_Food.Controller;

import com.example.Organic_Food.Entity.*;
import com.example.Organic_Food.Helper.EmailService;
import com.example.Organic_Food.Helper.FileUploader;
import com.example.Organic_Food.Helper.ProductHelper;
import com.example.Organic_Food.Repo.*;
import com.razorpay.Order;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.json.JSONObject;
import com.razorpay.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    tCodeRepo tCodeRepo;
    @Autowired
    HttpSession session;
    @Autowired
    tCodeRepo tCodeRepo1;
    @Autowired
    EmailService emailService;
    @Autowired
    RegistrationRepo registrationRepo;
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
    @Autowired
    TempOrderRepo tempOrderRepo;
    @Autowired
    OrderPlacedRepo orderPlacedRepo;

    @GetMapping("/admin/product/report/")
    public String getProductReportPage(Model model) {
        List<Product> productList = productRepo.findAll();
        model.addAttribute("productList", productList);
        return "product_report";
    }
    @GetMapping("/review/report/")
    public String getReviewReportPage(Model model) {
        List<Review> reviewList = reviewRepo.findAll();
        model.addAttribute("reviewList", reviewList);
        return "review_report";
    }

    @GetMapping("/admin/product/delete/{id}/")
    public String deleteResultRecord(Model model, @PathVariable int id) {
        productRepo.deleteById(id);
        List<Product> productList = productRepo.findAll();
        model.addAttribute("productList", productList);
        return "product_report";
    }

    @GetMapping("/admin/update/product/{id}/")
    public String updateProductRecord(Model model, @PathVariable int id) {
        Product data = productRepo.getReferenceById(id);
        model.addAttribute("data", data);
        model.addAttribute("id", data.getId());
        return "add_product";
    }

    @GetMapping("/user/order/")
    public String orderPage(Model model) {
        List<Temp_order> tempOrders = tempOrderRepo.findAll();
        Integer[] productId = new Integer[1000];
        Integer[] productPrize = new Integer[1000];
        Integer[] productQty = new Integer[1000];
        Integer tempOrderLength = tempOrders.size();
        String[] productName = new String[tempOrderLength];
        String userId = null;
        String Page = "order";
        int iterator = 0;
        System.out.println("ordesr details:-"+tempOrders);
        for (Temp_order data : tempOrders) {
            productId[iterator] = data.getProductId();
            productQty[iterator] = data.getProductQty();
            productName[iterator] = productRepo.findProductNameById(data.getProductId());
            userId = data.getUserId();
            iterator++;

        }
        System.out.println("Product id:-"+productId[0]);
        try {
            System.out.println(productRepo.findProductPrizeById(productId[0]));
            productPrize[0] = productRepo.findProductPrizeById(productId[0]);
            Integer totalAmt = productPrize[0] * productQty[0];
            System.out.println("Total amount:-"+totalAmt);
            
            model.addAttribute("productNames", productName);
            model.addAttribute("totalAmt", totalAmt);
            model.addAttribute("userId", userId);

        } catch (Exception e) {
            model.addAttribute("emsg", "Some thing went wrong");
            Page = "index";
        }

        return Page;
    }


    @ResponseBody
    @PostMapping("/admin/passData/")
    public String handlePostRequest(Model model, @RequestBody ProductHelper productHelper) {
        Integer closingBal = productStockRepo.findClosingStockByProductId(productHelper.getProductId());
        if (closingBal != null) {
            // Your existing code inside the if block
            List<ProductStock> productStockList = productStockRepo.findProductStockByProductId(productHelper.getProductId());
            Integer productId = 0;
            Integer openingBal = 0;
            Integer returnQty = 0;
            Integer issueQty = 0;
            Integer closingQty = 0;
//            String userId="IT";
            String userId = (String)session.getAttribute("key");
            System.out.println("Session Id:"+userId);
            LocalDateTime systemDate = null;
            for (ProductStock i : productStockList) {
                productId = productHelper.getProductId();
                openingBal = i.getClosingQty();
                System.out.println(openingBal);
                returnQty = i.getReturnQty();
                issueQty = productHelper.getProductQty();
                closingQty = closingBal - productHelper.getProductQty();
                systemDate = LocalDateTime.now();
            }
            if (closingBal >= productHelper.getProductQty()) {
                ProductStock productStock = new ProductStock(productId, openingBal, returnQty, issueQty, closingQty, systemDate);
                Temp_order tempOrder = new Temp_order(productId, issueQty, userId);

                try {
                    List<Temp_order> tempOrders = tempOrderRepo.findByUserId(userId);
                    Integer userSelectedProductId = tempOrderRepo.findProductIdByUserId(userId);
                    Integer userSelectedProductQty = tempOrderRepo.findProductQtyByUserId(userId);
                    System.out.println("user selected product id" + userSelectedProductId + "prod id" + productId);
                    System.out.println("user selected product qty" + userSelectedProductQty + "prod qty" + issueQty);

                    if (userSelectedProductId == productId && tempOrders != null || userSelectedProductQty != issueQty) {

                        System.out.println("Data already int the field");
                        Integer tempOrderUpdatedId = 0;
                        for (Temp_order temp : tempOrders) {
                            tempOrderUpdatedId = temp.getId();
                        }
                        Temp_order tempOrderUpdated = new Temp_order(tempOrderUpdatedId, productId, issueQty, userId);
                        tempOrderRepo.save(tempOrderUpdated);
                    } else {
                        productStockRepo.save(productStock);
                        tempOrderRepo.save(tempOrder);
                    }

                } catch (Exception e) {
                    System.out.println(e);
                }


            } else {
                model.addAttribute("errorMsg", "Currently Stock Empty");
            }
        } else {
            System.out.println("Data fnot found");
        }
//        session.invalidate();
        return "Data get it";

    }
//        Integer closingBal=productStockRepo.findClosingStockByProductId(productHelper.getProductId());
//        System.out.println(closingBal);
//        List <ProductStock> productStockList=productStockRepo.findProductStockByProductId(productHelper.getProductId());
//        System.out.println(productStockList);
//        Integer productId;
//        Integer openingBal;
//        Integer returnQty;
//        Integer issueQty;
//        Integer closingQty;
//        String userId="IT";
//        LocalDateTime systemDate;
//        for (ProductStock i:productStockList) {
//            productId=productHelper.getProductId();
//            openingBal=i.getClosingQty();
//            System.out.println(openingBal);
//            returnQty=i.getReturnQty();
//            issueQty=productHelper.getProductQty();
//            closingQty=closingBal-productHelper.getProductQty();
//            systemDate=LocalDateTime.now();
//            if(closingBal>=productHelper.getProductQty())
//            {
//                ProductStock productStock=new ProductStock(productId,openingBal,returnQty,issueQty,closingQty,systemDate);
//                Temp_order tempOrder=new Temp_order(productId,issueQty,userId);
//                productStockRepo.save(productStock);
//                tempOrderRepo.save(tempOrder);
//            }
//            else{
//                model.addAttribute("errorMsg","Currently Stock Empty");
//            }
//        }
//
//        return "Data get it";
//    }

    @PostMapping("/user/add/order/details/")
    public String plasedOder(Model model, OrderPlased orderPlased) {
        OrderPlased plased = orderPlacedRepo.save(orderPlased);
        String invoiceNumber = orderPlacedRepo.findInvoiceIdByName(orderPlased.getCustName());
        String productName = orderPlased.getBuyProductName();
        String[] productNames = productName.split(",");
        List<String> productNameList = Arrays.asList(productNames);
        List<Integer> productIds = productRepo.findProductIdsByNames(productNameList);
        Product productList = productRepo.getReferenceById(productRepo.findProductIdByName(productNames[0]));
        String userId=(String)session.getAttribute("key");
        System.out.println(userId);
        model.addAttribute("productNames", productNames);

        System.out.println(tempOrderRepo.findProductQtyByUserId(userId, productIds.get(0)));
        System.out.println("Payment type: "+orderPlased.getPaymentType());
        System.out.println(orderPlased.getPaymentType());

        model.addAttribute("productQty", tempOrderRepo.findProductQtyByUserId(userId, productIds.get(0)));
        model.addAttribute("productPrize", productList.getPrize());
        Product product=productRepo.getReferenceById(productIds.get(0));
        if(product.getDiscount()>0)//product.getDiscount_YN().equals("1") || product.getDiscount_YN()==""
        {
            Integer totalDiscount=(100*product.getDiscount()/product.getPrize());
            System.out.println("Total discount:-"+totalDiscount);
            model.addAttribute("discount", totalDiscount);
        }

        model.addAttribute("buyerHomeAddress", orderPlased.getHomeAddress());
        model.addAttribute("buyerHomeAddress", orderPlased.getHomeAddress());
        model.addAttribute("systemDate", orderPlased.getSystemDate());
        model.addAttribute("status", orderPlased.getPaymentType());
        model.addAttribute("buyerOfficeAddress", orderPlased.getOfficeAddress());
        model.addAttribute("invoiceNumber", invoiceNumber);
        model.addAttribute("custName", plased.getCustName());
        return "invoice";
    }
    @Transactional
    @ResponseBody
    @PostMapping("/admin/payment_order/")
    public String handlePostRequestOfSeatCount(Model model, @RequestBody String amountEntered) throws RazorpayException {
        String arr[] = amountEntered.split("=");
        System.out.println(arr[0]);
        int amt = Integer.parseInt(arr[0]);
        String userId=(String)session.getAttribute("key");
        String productNames=orderPlacedRepo.findProductsNamesByUserName(userId);
        String[] productsNamesArray = productNames.split(",");
        String message="work in progress!";
        System.out.println("product names:-"+ productsNamesArray);
        for (String i:productsNamesArray) {
            System.out.println("Product id:-"+ productRepo.findProductIdsByName(i));
            Integer productId=productRepo.findProductIdsByName(i);
            Product product=productRepo.getReferenceById(productId);
            Integer toProcureQty=tempOrderRepo.findProductQtyByUserId(userId,productId);
            Integer actualStock=productRepo.findActualStockById(productId);
            System.out.println("actual stock:-"+ actualStock);
            System.out.println("to producre qty stock:-"+ toProcureQty);
            if(actualStock>=toProcureQty)
            {
                Integer remainStock=actualStock-toProcureQty;
                Product data=new Product(productId,product.getCategory(),product.getName(),product.getTitle(),product.getMnf_date(),product.getExp_date(),product.getUnit(),product.getPrize(),product.getDiscount_YN(),product.getDiscount(),remainStock,product.getExtension(),product.getBrief_info(),product.getProd_name(),product.getProd_add(),product.getProd_mob_number(),product.getSystemDateTime());
                productRepo.save(data);
                List<ProductStock> productStockList=productStockRepo.findProductStockByProductId(productId);
                for (ProductStock productStock : productStockList) {
                    Integer openingBal = productStock.getClosingQty();
                    Integer returnQty = productStock.getReturnQty();
                    Integer issueQty = productHelper.getProductQty();
                    Integer closingQty = actualStock-toProcureQty;
                    LocalDateTime systemDate = LocalDateTime.now();
                    ProductStock productStockUpdated = new ProductStock(productStock.getId(),productId, openingBal, returnQty, issueQty, closingQty, systemDate);
                    productStockRepo.save(productStockUpdated);
                    tempOrderRepo.deleteByUserIdAndProductId(userId,productId);
                }
            }
            else{
                message="Store is not available try after some time";
            }

        }
        RazorpayClient razorpay = null;
        try {
            razorpay = new RazorpayClient("rzp_test_61WqW2hrEvrGTv", "9EkJKA7v7OH4qWphUMtQTrEJ");
        } catch (RazorpayException e) {
            throw new RuntimeException(e);
        }
        JSONObject option = new JSONObject();
        option.put("amount", amt * 100); // amount in the smallest currency unit
        option.put("currency", "INR");
        option.put("receipt", "order_rcptid_11");
        Order order = razorpay.orders.create(option);
        System.out.println("Order:" + order);
        Integer amtval = option.getInt("amount");
        String trncId = order.get("id");
        String currency = option.getString("currency");

        System.out.println(amtval);
        session.invalidate();
        message=order.toString();
        return message;
    }

    @PostMapping("/new/user/registration/")
    public String addUser(Model model, Registration registration) {
        Registration registrationList = registrationRepo.getByEmail(registration.getEmail());

        System.out.println(registrationList);
        if (registrationList == null) {
            tCode code= tCodeRepo.getReferenceById(1);
            System.out.println(code);
            if(registration.getUserType().equals("1"))
            {
                if(registration.gettCode().equals(code.gettCodeValue()))
                {
                    Registration registration1 = registrationRepo.save(registration);
                    model.addAttribute("msg", "Admin Registered Successfully");
                }
                else{
                    model.addAttribute("emsg", "You are not an Admin");
                }
            }
            else{
                Registration registration1 = registrationRepo.save(registration);
                model.addAttribute("msg", "New User Registration done Successfully");
            }

        } else {
            model.addAttribute("emsg", "User Already Exist");
        }

        return "Admin_Login";
    }

    @GetMapping("/master/login/")
    public String getAdminLogin(Model model) {
        return "tcodeWiAdminLogin";
    }

    @PostMapping("/data/pass/controller/")
    public String handleLogin(@RequestParam("logCode") String logCode, Model model) {
        tCode code = tCodeRepo1.getReferenceById(1);
        System.out.println(code.gettCodeValue() + " " + logCode);
        String page = "tcodeWiAdminLogin";
        if (code.gettCodeValue().equals(logCode)) {
            model.addAttribute("msg", "Login Successfully");
            page = "Admin_Dashboard";
        } else {
            model.addAttribute("emsg", "Check Your T Code");
            page = "tcodeWiAdminLogin";
        }
        return page;
    }
    @GetMapping("/dashboard/")
    public String getDashboard()
    {
        return "Admin_Dashboard";
    }
//    @GetMapping("/get/admin/key/")
//    public String sendSpecialKey(Model model)
//    {
//        tCode code=tCodeRepo1.getReferenceById(1);
//        String from = "ad.developer@gmail.com";
//        String to = "aniketz2126@gmail.com";
//        MimeMessage message=mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//        String maiSubject="Get Special Key - [Innovative THings] Account";
//        String mailContent = "<h1>Get Special Key</h1><br>"
//                + "<p>Dear <strong>" + to + "</strong></p>"
//                + "<p>We have received a request to reset the password for your Innovative THings account.</p>"
//                + "<p>Your new temporary password is: <strong>" + code.gettCodeValue() + "</strong></p>"
//                + "<p>Please log in using this password and change it to a new one after signing in.</p>"
//                + "<p>If you didn't request a password reset, please ignore this email.</p>"
//                + "<p>If you have any questions or need further assistance, please don't hesitate to contact our support team at [SupportEmail].</p>"
//                + "<p>Thank you,</p>"
//                + "<p>The Innovative THings Team</p>";
//        helper.setFrom(from,"Innovative Things");
//        helper.setTo(to);
//        helper.setSubject(maiSubject);
//        helper.setText(mailContent,true);
//
//        try
//        {
//            mailSender.send(message);
//            System.out.println("Email send successfully");
//        }catch (Exception e)
//        {
//            System.out.println(e);
//        }
//
//
//        return "tcodeWiAdminLogin";
//
//    }
    @GetMapping("/oder/placed/")
    public String orderPaced(Model model)
    {
        String userId=(String)session.getAttribute("key");
        String productNames=orderPlacedRepo.findProductsNamesByUserName(userId);
        String[] productsNamesArray = productNames.split(",");
        for (String i:productsNamesArray)
        {
            Integer productId = productRepo.findProductIdsByName(i);
            Integer toProcureQty = tempOrderRepo.findProductQtyByUserId(userId, productId);
            Integer actualStock = productRepo.findActualStockById(productId);
            Integer remainStock = actualStock - toProcureQty;
            Product data = new Product(productId, remainStock);
            productRepo.save(data);
            List<ProductStock> productStockList = productStockRepo.findProductStockByProductId(productId);
            for (ProductStock productStock : productStockList) {
                Integer openingBal = productStock.getClosingQty();
                Integer returnQty = productStock.getReturnQty();
                Integer issueQty = productHelper.getProductQty();
                Integer closingQty = actualStock - toProcureQty;
                LocalDateTime systemDate = LocalDateTime.now();
                ProductStock productStockUpdated = new ProductStock(productStock.getId(), productId, openingBal, returnQty, issueQty, closingQty, systemDate);
                tempOrderRepo.deleteByUserIdAndProductId(userId, productId);
            }
        }
        session.invalidate();
        return "index";
    }

    @GetMapping("/admin/review/status/update/{id}/")
    public String updateAdmittedStatus(Model model, @PathVariable Integer id) {
        String status = "";
        Review reviewRecord = reviewRepo.getReferenceById(id);
        if (reviewRecord.getStatus() == null || reviewRecord.getStatus().equals("0")) {
            status = "1";
        } else {
            status = "0";
        }
        reviewRepo.save(new Review(reviewRecord.getId(),reviewRecord.getName(),reviewRecord.getProfession(),reviewRecord.getExtension(),reviewRecord.getBriefInfo(),status,reviewRecord.getSystemDate()));
        List<Review> reviewList = reviewRepo.findAll();
        model.addAttribute("reviewList", reviewList);
        return "review_report";
    }
    @GetMapping("/admin/review/update/{id}/")
    public String updateReviewRecord(@PathVariable Integer id, Model model)
    {
        Review data=reviewRepo.getReferenceById(id);
        model.addAttribute("data",data);
        return "add_review";
    }
    @GetMapping("/admin/review/delete/{id}/")
    public String deleteReviewRecord(@PathVariable Integer id, Model model)
    {
        reviewRepo.deleteById(id);
        List<Review> reviewList=reviewRepo.findByStatus("1");
        model.addAttribute("reviewList",reviewList);
        return "review_report";
    }


}
