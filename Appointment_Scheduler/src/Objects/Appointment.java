/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import java.sql.Date;
import java.time.LocalDateTime;
/**
 *
 * @author mauli
 */
public class Appointment {
    
    //parameters
    private int appointmentID;
    private int customerID;
    private int userID;
    private String customerName;
    private String title;
    private String description;
    private String location;
    private String contact;
    private String type;
    private String url;
    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdateBy;
    
    
    //constructors
    public Appointment(int appointmentID, int customerID, String customerName, String title, String description,
                       String location, String contact, String type, String url, LocalDateTime start,
                       LocalDateTime end, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdateBy){
        this.appointmentID = appointmentID;
        this.customerID = customerID; 
        this.customerName = customerName;
        this.title = title; 
        this.description = description;
        this.location = location;
        this.contact = contact;
        this.type = type;
        this.url = url;
        this.start = start;
        this.end = end;
        this.createDate = createDate; 
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate; 
        this.lastUpdateBy = lastUpdateBy;
    }
    
    public Appointment(String customerName, String title, String description, String location, String contact, String type, 
                       String url, LocalDateTime start, LocalDateTime end, LocalDateTime createDate, String createdBy, 
                       LocalDateTime lastUpdate, String lastUpdateBy){
        this.customerName = customerName;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contact = contact;
        this.type = type;
        this.url = url;
        this.start = start;
        this.end = end;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdateBy = lastUpdateBy;
    }
    
    
    //getters
    public int getAppointmentID(){
        return this.appointmentID;
    }
    
    public int getCustomerID(){
        return this.customerID;
    }
    
    public String getCustomerName(){
        return this.customerName;
    }
    
    public int getUserID(){
        return this.userID;
    }
    
    public String getTitle(){
        return this.title;
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public String getLocation(){
        return this.location;
    }
    
    public String getContact(){
        return this.contact;
    }
    
    public String getType(){
        return this.type;
    }
    
    public String getUrl(){
        return this.url;
    }
    
    public LocalDateTime getStart(){
        return this.start;
    }
    
    public LocalDateTime getEnd(){
        return this.end;
    }
    
    public LocalDateTime getCreateDate(){
        return this.createDate;
    }
    
    
     //setters
    public void setAppointmentID(int ID){
        this.appointmentID = ID;
    }
    
    public void setCustomerID(int ID){
        this.customerID = ID;
    }
    
    public void setCustomerName(String name){
        this.customerName = name;
    }
    
    public void setUserID(int ID){
        this.userID = ID;
    }
    
    public void setTitle(String title){
        this.title = title;
    }
    
    public void setDescription(String description){
        this.description = description;
    }
    
    public void setLocation(String location){
        this.location = location;
    }
    
    public void setContact(String contact){
        this.contact = contact;
    }
    
    public void setType(String type){
        this.type = type;
    }
    
    public void setUrl(String url){
        this.url = url;
    }
    
    public void setStart(LocalDateTime start){
        this.start = start;
    }
    
    public void setEnd(LocalDateTime end){
        this.end = end;
    }
    
    public void setCreateDate(LocalDateTime date){
        this.createDate = date;
    }
    
    
    
}
