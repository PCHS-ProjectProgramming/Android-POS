package net.a6te.lazycoder.andropos.test;

import android.content.Context;
import android.util.Log;

import net.a6te.lazycoder.andropos.database.Customer;
import net.a6te.lazycoder.andropos.database.Product;
import net.a6te.lazycoder.andropos.database.SellsInfo;
import net.a6te.lazycoder.andropos.database.Stock;
import net.a6te.lazycoder.andropos.database.User;
import net.a6te.lazycoder.andropos.modelClass.CustomerModel;
import net.a6te.lazycoder.andropos.modelClass.ProductDatabaseModel;
import net.a6te.lazycoder.andropos.modelClass.SellsDatabaseModel;
import net.a6te.lazycoder.andropos.modelClass.StockDatabaseModel;
import net.a6te.lazycoder.andropos.modelClass.UserDatabaseModel;

import static net.a6te.lazycoder.andropos.MainActivity.TAG;

/**
 * Created by Programmer on 5/21/2017.
 */

public class SampleDataInsert {


    private Stock stock;
    private Product product;
    private Customer customer;
    private User user;
    private SellsInfo sells;


    public SampleDataInsert(Context context){
        stock = new Stock(context);
        product = new Product(context);
        customer = new Customer(context);
        user = new User(context);
        sells = new SellsInfo(context);

    }

    public void storeSomeSampleData() {


        //insert data to stock page
        if (!stock.haveAnyStock()){

            //only first time send some temporary data
//        private String ProductId;
//        private String ProductType;
//        private String ProductQuantity;
//        private String ProductFor;


//            stock table

            // Working Entries
            stock.storeStock(new StockDatabaseModel("001","Drink","60","1"));
            stock.storeStock(new StockDatabaseModel("002","Snack","30","1"));
            stock.storeStock(new StockDatabaseModel("003","Drink","24","1"));
            stock.storeStock(new StockDatabaseModel("004","Drink","50","1"));
            stock.storeStock(new StockDatabaseModel("005","Drink","45","1"));
            stock.storeStock(new StockDatabaseModel("006","Drink","37","1"));
            stock.storeStock(new StockDatabaseModel("007","Drink","29","1"));

            // Old DB Entries
            //stock.storeStock(new StockDatabaseModel("992","2","40","1"));
            //stock.storeStock(new StockDatabaseModel("993","2","60","1"));
            //stock.storeStock(new StockDatabaseModel("994","2","98","1"));
            //stock.storeStock(new StockDatabaseModel("995","2","500","1"));
            //stock.storeStock(new StockDatabaseModel("996","2","60","1"));

            // More Working Entries
            stock.storeStock(new StockDatabaseModel("001","0","20","1"));
            stock.storeStock(new StockDatabaseModel("002","2","50","1"));
            stock.storeStock(new StockDatabaseModel("003","2","100","1"));
            stock.storeStock(new StockDatabaseModel("004","0","15","1"));

            //product table
//            private String productName;
//            private String productCode;
//            private String productPrice;
//            private String productSellPrice;
//            private String productUnit;
//            private String productBrand;
//            private String productSize;

            // TODO: Fix Refresh Button Logic, Currently Useless (Cache Clear required for DB map to update)
            product.storeProductInfo(new ProductDatabaseModel("Drink","01","50000","55000","Pcs","Pepsi","16.9 FL OZ"));
            product.storeProductInfo(new ProductDatabaseModel("Candy","02","10000","15000","Pcs","Various","Various"));
            product.storeProductInfo(new ProductDatabaseModel("Snack","03","70000","80000","Pcs","Various","Various"));
            product.storeProductInfo(new ProductDatabaseModel("Merch","04","50000","90000","Pcs","Gildan","S-XXL"));



            // "Working" DB Entries (These just store product info, UI uses 'Store Stock' DB map
            product.storeProductInfo(new ProductDatabaseModel("Water","001","1.00","1.00","Bottles","Dell","45 inch"));
            product.storeProductInfo(new ProductDatabaseModel("Nacho Cheese Doritos","002","1.25","1.25","Bags","Snack","24 inch"));
            product.storeProductInfo(new ProductDatabaseModel("Mtn. Dew","003","1.25","1.25","Bottles","Drink","28 inch"));
            product.storeProductInfo(new ProductDatabaseModel("Diet Mtn. Dew","004","1.25","1.25","Bottles","Drink","28 inch"));
            product.storeProductInfo(new ProductDatabaseModel("Code Red Mtn. Dew","005","1.25","1.25","Bottles","Drink","30 inch"));
            product.storeProductInfo(new ProductDatabaseModel("Dr. Pepper","006","1.25","1.25","Bottles","Drink","32.5 inch"));
            product.storeProductInfo(new ProductDatabaseModel("Diet Dr. Pepper","007","1.25","1.25","Bottles","Drink","38.5 inch"));




            product.storeProductInfo(new ProductDatabaseModel("Pepsi","107","1.25","1.25","Drink","Dell","50 inch"));
            product.storeProductInfo(new ProductDatabaseModel("Diet Pepsi","108","1.25","1.25","Drink","DANSAT","28 inch"));
            product.storeProductInfo(new ProductDatabaseModel("Cherry Pepsi","109","1.25","1.25","Drink","DANSAT","28 inch"));
            product.storeProductInfo(new ProductDatabaseModel("3 Musketeer","110","1.25","1.25","Candy","DANSAT","28 inch"));
            product.storeProductInfo(new ProductDatabaseModel("Zero Bar","111","1.25","1.25","Candy","DANSAT","28 inch"));
            product.storeProductInfo(new ProductDatabaseModel("Purple Gatorade","112","1.25","1.25","Drink","DANSAT","28 inch"));
            product.storeProductInfo(new ProductDatabaseModel("Red Gatorade","113","1.25","1.25","Drink","DANSAT","28 inch"));
            product.storeProductInfo(new ProductDatabaseModel("Yellow Gatorade","114","1.25","1.25","Drink","DANSAT","28 inch"));
            product.storeProductInfo(new ProductDatabaseModel("Blue Gatorade","115","1.25","1.25","Drink","DANSAT","28 inch"));
            product.storeProductInfo(new ProductDatabaseModel("Sweet Tea","116","1.25","1.25","Drink","DANSAT","28 inch"));
            product.storeProductInfo(new ProductDatabaseModel("Hersheys","117","1.25","1.25","Candy","DANSAT","28 inch"));
            product.storeProductInfo(new ProductDatabaseModel("Sour Patch Kids","118","1.25","1.25","Candy","DANSAT","28 inch"));
            product.storeProductInfo(new ProductDatabaseModel("Sour Ropes","119","1.25","1.25","Candy","DANSAT","28 inch"));



            //customer table


//            private String customerName;
//            private String customerCode;
//            private String customerType;
//            private String customerGender;
//            private String customerPhone;
//            private String customerEmail;
//            private String customerAddress;
//            private String customerDueAmount;
            customer.createNewCustomer(new CustomerModel("Walkthrough","CUS1","regular","male","88555320","WalkThrough@gmail.com","mirpur dohs","600"));
            customer.createNewCustomer(new CustomerModel("Mikel brane","CUS2","regular","male","54115415","Mikel@gmail.com","mirpur dohs","20"));
            customer.createNewCustomer(new CustomerModel("Anca","CUS3","regular","male","7881544","Anca@gmail.com","mirpur dohs","0"));
            customer.createNewCustomer(new CustomerModel("James","CUS4","regular","male","8718245","James@gmail.com","mirpur dohs","100"));
            customer.createNewCustomer(new CustomerModel("Jonson","CUS5","regular","male","3562565","Jonson@gmail.com","mirpur dohs","500"));
            customer.createNewCustomer(new CustomerModel("Miler","CUS6","regular","male","356255","Miler@gmail.com","mirpur dohs","600"));
            customer.createNewCustomer(new CustomerModel("Amla","CUS7","regular","male","985414","Amla@gmail.com","mirpur dohs","600"));
            customer.createNewCustomer(new CustomerModel("Maxual","CUS8","regular","male","987761","Maxual@gmail.com","mirpur dohs","0"));

            //user table

//            private String userName;
//            private String userEmail;
//            private String userPassword;
//            private String userPhone;
//            private String userEmployeeId;
//
            user.createNewUser(new UserDatabaseModel("admin","ashrafswe789@gmail.com","123456","01777777777","1"));

            //sell table
//            private String sellsCode;
//            private String customerId;
//            private String totalAmount;
//            private String discount;
//            private String payAmount;
//            private String paymentType;
//            private String sellDate;
//            private String paymentStatus;
//            private String sellBy;

            sells.storeSellDetails(new SellsDatabaseModel("in-1","CUS1","10000","10","8000","0","06-06-17","1","1"));
            sells.storeSellDetails(new SellsDatabaseModel("in-2","CUS2","5000","300","5000","0","06-06-17","0","1"));

        }

    }
}
