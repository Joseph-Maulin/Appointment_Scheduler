/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import java.sql.Date;
import java.sql.Timestamp;
/**
 *
 * @author mauli
 */
public class Customer {
    
    //parameters
    private int customerId;
    private String customerName;
    private String address1;
    private String address2;
    private String city;
    private String postalCode;
    private String country;
 
    private String fullAddress;
    private String phone;
    
    
    //constructors
    
    public Customer(int customerId, String customerName, String phone, String address1, String address2, String city, String postalCode, String country){
        this.customerId = customerId;
        this.customerName = customerName;
        this.phone = phone;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
        
        String fullAddress = address1 + "\n";
        if(address2.length()>0){
            fullAddress += address2 + "\n";
        }
        fullAddress += city + " " + postalCode + " " + country;
                
        this.fullAddress = fullAddress;
    }
    
    //getters
    public int getCustomerId(){
        return this.customerId;
    }
    
    public String getCustomerName(){
        return this.customerName;
    }
    
    public String getAddress1(){
        return this.address1;
    }
    
    public String getAddress2(){
        return this.address2;
    }
    
    public String getFullAddress(){
        return this.fullAddress;
    }
    
    public String getCity(){
        return this.city;
    }
    
    public String getPhone(){
        return this.phone;
    }
    
    public String getCountry(){
        return this.country;
    }
    
    public String getPostalCode(){
        return this.postalCode;
    }
    
    
    //setters
    public void setCustomerName(String name){
        this.customerName = name;
    }
    
    public void setAddress1(String address){
        this.address1 = address;
        
        String fullAddress = this.address1 + "\n";
        if(this.address2.length()>0){
            fullAddress += this.address2 + "\n";
        }
        fullAddress += this.city + " " + this.postalCode + " " + this.country;
                
        this.fullAddress = fullAddress;
    }
    
    public void setAddress2(String address){
        this.address2 = address;
        
        String fullAddress = this.address1 + "\n";
        if(this.address2.length()>0){
            fullAddress += this.address2 + "\n";
        }
        fullAddress += this.city + " " + this.postalCode + " " + this.country;
                
        this.fullAddress = fullAddress;
    }
    
    public void setCity(String city){
        this.city = city;
        
        String fullAddress = this.address1 + "\n";
        if(this.address2.length()>0){
            fullAddress += this.address2 + "\n";
        }
        fullAddress += this.city + " " + this.postalCode + " " + this.country;
                
        this.fullAddress = fullAddress;
    }
    
    public void setPhone(String phone){
        this.phone = phone;
    }
    
    public void setCountry(String country){
        this.country = country;
        
        String fullAddress = this.address1 + "\n";
        if(this.address2.length()>0){
            fullAddress += this.address2 + "\n";
        }
        fullAddress += this.city + " " + this.postalCode + " " + this.country;
                
        this.fullAddress = fullAddress;
    }
    
    public void setPostalCode(String postalCode){
        this.postalCode = postalCode;
        
        String fullAddress = this.address1 + "\n";
        if(this.address2.length()>0){
            fullAddress += this.address2 + "\n";
        }
        fullAddress += this.city + " " + this.postalCode + " " + this.country;
                
        this.fullAddress = fullAddress;
    }
}
