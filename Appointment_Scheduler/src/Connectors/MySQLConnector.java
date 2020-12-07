
package Connectors;

import Objects.Appointment;
import Objects.Customer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;


public class MySQLConnector {
     private Connection myCon;
    
    
    public MySQLConnector(){
        this.setConnection();
    }
    
    // set MySQL connection
    public void setConnection(){  
        try{  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con = DriverManager.getConnection(  
            "jdbc:mysql://3.227.166.251:3306/U06DeT",
            "U06DeT",
            "53688730864");
            
            this.myCon = con;
            this.myCon.setAutoCommit(false);
        }
        
        catch(Exception e){ 
            System.out.println(e);
        }  
    }
    
    // check user password
    public boolean checkPassword(String user, String password){
        try{
            Statement stmt = this.myCon.createStatement();
            
            String sqlStatement = "SELECT * FROM user "
                                + "WHERE user.userName = '" + user + "'";
            
            ResultSet rs = stmt.executeQuery(sqlStatement);
            String pass = "";
            int active = 0;
            
            while(rs.next()){
                pass = rs.getString("password");
                active = rs.getInt("active");
            }
           
            
            if ((pass.equals(password)) && (active == 1)){
                    return true;   
            }
            
            return false;
        }
        
        catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
    // get userId from userName
    public int getUserId(String user){
        try{
            Statement stmt = this.myCon.createStatement();
            
            String sqlStatement = "SELECT user.userId FROM user WHERE user.userName = '" + user + "';";
            
            ResultSet rs = stmt.executeQuery(sqlStatement);
            int userId = -1;
            
            while(rs.next()){
                userId = rs.getInt("userId");
            }

            return userId;
        }
        
        catch(Exception e){
            System.out.println(e);
            return -1;
        }
    }
    
    // get customer table data
    public ArrayList<Customer> getCustomersTableData(){
        try{
            Statement stmt = this.myCon.createStatement();
            
            String sqlStatement = "SELECT customer.customerId, customer.customerName, address.phone, address.address, address.address2, city.city, address.postalCode, country.country\n" +
                                    "FROM customer\n" +
                                    "JOIN address\n" +
                                    "ON customer.addressId = address.addressId\n" +
                                    "JOIN city\n" +
                                    "ON city.cityId = address.cityId\n" +
                                    "JOIN country\n" +
                                    "ON country.countryId = city.countryId\n" +
                                    "WHERE active = '1'";
            
            ResultSet rs = stmt.executeQuery(sqlStatement);
            ArrayList<Customer> myCustomers = new ArrayList<Customer>();
            
            while(rs.next()){
                int customerId = rs.getInt("customerId");
                String customerName = rs.getString("customerName");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                String address2 = rs.getString("address2");
                String city = rs.getString("city");
                String postalCode = rs.getString("postalCode");
                String country = rs.getString("country");
                
                Customer myCustomer = new Customer(customerId, customerName, phone, address, address2, city, postalCode, country);
                myCustomers.add(myCustomer);
            }
            
            
            return myCustomers;
        }
        
        catch(Exception e){
            System.out.println(e);
            ArrayList<Customer> myCustomers = new ArrayList<Customer>();
            return myCustomers;
        }
    }
    
    
    // get customerId 
    public int getCustomerID(String customerName, String address, String address2){   
        try{
            Statement stmt = this.myCon.createStatement();
        
            String customerSearchSQL = "SELECT customer.customerID, customer.customerName, address.address, address.address2\n" +
                                        "FROM customer\n" +
                                        "JOIN address\n" +
                                        "ON customer.addressId = address.addressId\n" +
                                        "WHERE customerName = " + "'" + customerName + "'" +
                                        " AND address = " + "'" + address + "'" +
                                        " AND address2 = " + "'" + address2 + "';";
            
            
            ResultSet rsCustomer = stmt.executeQuery(customerSearchSQL);
            int customerID = -1;
            
            while (rsCustomer.next()){
                customerID = rsCustomer.getInt("customerId");
            }

            return customerID;
            
        }
        
        catch(Exception e){
            System.out.println(e);
            return -1;
        }
    }
    
    
    // get addressId for customerId
    public int getAddressID(int customerID){
        try{
            Statement stmt = this.myCon.createStatement();
            
            String addressSearchSQL = "SELECT addressId FROM customer "
                                    + "WHERE customerId = '" + customerID + "';";
            
            ResultSet rsAddress = stmt.executeQuery(addressSearchSQL);
            int addressID = -1;
            
            while (rsAddress.next()){
                addressID = rsAddress.getInt("addressId");
            }
            
            return addressID;
        }
        
        catch(Exception e){
            System.out.println(e);
            return -1;
        }
    }
    
    // get cityId for addressId
    public int getCityID(int addressID){
        try{
            Statement stmt = this.myCon.createStatement();
            
            String citySearchSQL = "SELECT cityId FROM address "
                                    + "WHERE addressId = '" + addressID + "';";
            
            ResultSet rsCity = stmt.executeQuery(citySearchSQL);
            int cityID = -1;
            
            while (rsCity.next()){
                cityID = rsCity.getInt("cityId");
            }
            
            return cityID;
        }
        
        catch(Exception e){
            System.out.println(e);
            return -1;
        }
    }
    
    
    // get countryId for cityId
    public int getCountryID(int cityID){
        try{
            Statement stmt = this.myCon.createStatement();
            
            String countrySearchSQL = "SELECT countryId FROM city "
                                    + "WHERE cityId = '" + cityID + "';";
            
            ResultSet rsCountry = stmt.executeQuery(countrySearchSQL);
            int countryID = -1;
            
            while (rsCountry.next()){
                countryID = rsCountry.getInt("countryId");
            }
            
            return countryID;
        }
        
        catch(Exception e){
            System.out.println(e);
            return -1;
        }
    }
    
    
    // add customer
    public String addCustomer(String customerName, String phone, String country, String address1, String address2, String city, 
                            String postalCode, String user, int languageSetting){
        try{
            // search for country
            Statement stmt = this.myCon.createStatement();
            
            String countrySearchSQL = "SELECT countryId FROM country "
                                 + "WHERE country = " + "'" + country + "'";
            
            ResultSet rsCountry = stmt.executeQuery(countrySearchSQL);
            int countryID = 0;
            ZoneId zoneId = ZoneId.of("UTC");
            LocalDateTime createDate = ZonedDateTime.now(zoneId).toLocalDateTime();
            
            
            while (rsCountry.next()){
                countryID = rsCountry.getInt("countryId");
            }
            
            // if not country add
            if (countryID == 0){ 
                String addCountrySQL = "INSERT INTO country (country, createDate, createdBy, lastUpdate, lastUpdateBy) "
                                  + "VALUES ('" + country + "', '" + createDate + "', '" + user + "', '" + createDate + "', '" + user + "')";
                
                PreparedStatement countryAdd = this.myCon.prepareStatement(addCountrySQL);
                
                countryAdd.executeUpdate();
              
                rsCountry = stmt.executeQuery(countrySearchSQL);
            
                while (rsCountry.next()){
                    countryID = rsCountry.getInt("countryId");
                }
            }
            
            // search for city    
            
            String citySearchSQL = "SELECT city.cityId FROM city "
                                  +"WHERE city.city = " + "'" + city + "'";
            
            ResultSet rsCity = stmt.executeQuery(citySearchSQL);
            int cityID = 0;
            
            while (rsCity.next()){
                cityID = rsCity.getInt("cityId");
            }
            
            // if city not found add
            if(cityID == 0){
                
                String addCitySQL = "INSERT INTO city (city, countryId, createDate, createdBy, lastUpdate, lastUpdateBy) "
                                   +"VALUES ('" + city + "', '" + countryID + "', '" + createDate + "', '" + user + "', '" 
                                   + createDate + "', '" + user + "')";
                
                PreparedStatement cityAdd = this.myCon.prepareStatement(addCitySQL);
                
                cityAdd.executeUpdate();
                
                rsCity = stmt.executeQuery(citySearchSQL);

                while (rsCity.next()){
                    cityID = rsCity.getInt("cityId");
                }
            }
            
            // search for address   
            String addressSearchSQL = "SELECT address.addressId FROM address "
                                     +"WHERE address.address = " + "'" + address1 + "'"
                                     +" AND cityId = " + "'" + cityID + "'"
                                     +" AND postalCode = " + "'" + postalCode + "'";
            
            ResultSet rsAddress = stmt.executeQuery(addressSearchSQL);
            int addressID = 0;
            
            while (rsAddress.next()){
                addressID = rsAddress.getInt("addressId");
            }
            
            // if address not found add   
            if (addressID == 0){
                
                String addAddressSQL = "INSERT INTO address (address, address2, cityId, postalCode, phone, createDate,"
                                     + " createdBy, lastUpdate, lastUpdateBy) "
                                     + "VALUES ('" + address1 + "', '" + address2 + "', '" + cityID + "', '" + postalCode + "', '"
                                     + phone + "', '" + createDate + "', '" + user + "', '" + createDate + "', '" + user + "')";
                
                PreparedStatement addressAdd = this.myCon.prepareStatement(addAddressSQL);
                
                addressAdd.executeUpdate();
                
                rsAddress = stmt.executeQuery(addressSearchSQL);
            
                while (rsAddress.next()){
                    addressID = rsAddress.getInt("addressId");
                }
                
            }
            
          
            // customer
            
            // check if customer exists
            String customerSearchSQL =  "SELECT customer.customerId, customer.active FROM customer "
                                      + "WHERE customerName = " + "'" + customerName + "';";
            
            ResultSet rsCustomer = stmt.executeQuery(customerSearchSQL);
            int customerID = 0;
            int active = 0;
            
            while (rsCustomer.next()){
                customerID = rsCustomer.getInt("customerId");
                active = rsCustomer.getInt("active");
            }

            // reactivate customer if found but not active
            if (customerID > 0 & active == 0) {
                // reactivate
                String sqlUpdateStatement =  "UPDATE customer\n"
                                           + "SET customerName = " + "'" + customerName + "', "
                                           + "addressId = " + "'" + addressID + "', "
                                           + "active = '1', "
                                           + "lastUpdate = " + "'" + createDate + "', "
                                           + "lastUpdateBy = " + "'" + user + "'\n"
                                           + "WHERE customerId = " + "'" + customerID + "';";
                
                PreparedStatement customerUpdate = this.myCon.prepareStatement(sqlUpdateStatement);
                int rowAffected = customerUpdate.executeUpdate();
                
                if (rowAffected > 0) {
                    this.myCon.commit();
                    if(languageSetting == 0){
                        return "Customer Added";
                    }
                    else{
                        return "Cliente Agregado";
                    }
                }
                
                else {
                    this.myCon.rollback();
                    if(languageSetting == 0){
                        return "Customer Add Failed";
                    }
                    else{
                        return "La Adición del Cliente Falló";
                    }
                }
            }
            
            // customer is found and active
            else if (customerID > 0 & active == 1) {
                if(languageSetting == 0){
                    return "Customer Already Exists";
                }
                else{
                    return "El cliente ya existe";
                }
            }
            
            // customer not found
            else {
                String sqlStatement = "INSERT INTO customer (customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy) "
                                    + "VALUES ('" + customerName + "', '" + addressID + "', 1, '" + createDate + "', '" + user + "', '" + createDate
                                    + "', '" + user + "')";

                PreparedStatement customerAdd = this.myCon.prepareStatement(sqlStatement);
                int rowAffected = customerAdd.executeUpdate();
                
                if (rowAffected > 0) {
                    this.myCon.commit();
                    if(languageSetting == 0){
                        return "Customer Added";
                    }
                    else{
                        return "Cliente Agregado";
                    }
                }
                
                else {
                    this.myCon.rollback();
                    if(languageSetting == 0){
                        return "Customer Add Failed";
                    }
                    else{
                        return "La Adición del Cliente Falló";
                    }
                }
            }
        }
        
        catch(SQLException e){
            System.out.println(e);
            try{
                this.myCon.rollback();
            }
            
            catch (SQLException se2){
                System.out.println("Rollback error");
            }
            
            if(languageSetting == 0){
                        return "Customer Add Failed";
            }
            else{
                return "La Adición del Cliente Falló";
            }
        }
    }
    
    
    // update customer
    public String updateCustomer(String customerName, String phone, String country, String address1, String address2, String city, 
                                 String postalCode, String user, int customerID, int addressID, int cityID, int countryID, int languageSetting) {
        try {
            
            //search for country
            Statement stmt = this.myCon.createStatement();
            
            String countrySearchSQL = "SELECT country FROM country "
                                 + "WHERE countryId = " + "'" + countryID + "'";
            
            ResultSet rsCountry = stmt.executeQuery(countrySearchSQL);
            ZoneId zoneId = ZoneId.of("UTC");
            LocalDateTime newDate = ZonedDateTime.now(zoneId).toLocalDateTime();
            String currentCountry = "not found";
            
            while (rsCountry.next()){
                currentCountry = rsCountry.getString("country");
            }
            
            // if country has changed
            if (!(currentCountry.equals(country))){
                
                // check if country exists in MySQL
                
                countryID = 0;
                
                String newCountrySearchSQL = "SELECT countryId FROM country "
                                           + "WHERE country = " + "'" + country + "'";
                
                ResultSet rsNewCountry = stmt.executeQuery(newCountrySearchSQL);
                                            
                while (rsNewCountry.next()){
                    countryID = rsNewCountry.getInt("countryId");
                }
                
                // if country not found in database --> insert
                if (countryID == 0){
                    
                    String addNewCountry = "INSERT INTO country (country, createDate, createdBy, lastUpdate, lastUpdateBy) "
                                         + "VALUES ('" + country + "', '" + newDate + "', '" + user + "', '" + newDate + "', '" + user + "');";
                    
                    PreparedStatement newCountryAdd = this.myCon.prepareStatement(addNewCountry);
                    newCountryAdd.executeUpdate();
                    
                    String newCountryIdFind = "SELECT countryId FROM country "
                                           + "WHERE country = " + "'" + country + "'";
                
                    ResultSet rsNewCountryId = stmt.executeQuery(newCountryIdFind);
                    countryID = 0;
                                            
                    while (rsNewCountryId.next()){
                        countryID = rsNewCountryId.getInt("countryId");
                    }
                }
            }
            
          
            
            // search for city
            String citySearchSQL = "SELECT city.city FROM city "
                                  +"WHERE cityId = " + "'" + cityID + "'";
            
            ResultSet rsCity = stmt.executeQuery(citySearchSQL);
            String currentCity = "not found";
            
            while (rsCity.next()){
                currentCity = rsCity.getString("city");
            }
            
            // current city mismatch -> add new city
            if(!(currentCity.equals(city))){
                String addCitySQL = "INSERT INTO city (city, countryId, createDate, createdBy, lastUpdate, lastUpdateBy) "
                                   +"VALUES ('" + city + "', '" + countryID + "', '" + newDate + "', '" + user + "', '" 
                                   + newDate + "', '" + user + "')";
                
                PreparedStatement cityAdd = this.myCon.prepareStatement(addCitySQL);
                cityAdd.executeUpdate();
                
                String newCitySQL = "SELECT cityId FROM city "
                                  + "WHERE city = " + "'" + city + "'";

                rsCity = stmt.executeQuery(newCitySQL);

                while (rsCity.next()){
                    cityID = rsCity.getInt("cityId");
                }              
            }  
            
            // update city
            else{
                String updateCitySQL = "UPDATE city "
                                     + "SET countryId = '" + countryID + "', "
                                     + "lastUpdate = '" + newDate + "', "
                                     + "lastUpdateBy = '" + user + "' "
                                     + "WHERE city.cityId = '" + cityID + "';";

                PreparedStatement cityUpdate = this.myCon.prepareStatement(updateCitySQL);
                cityUpdate.executeUpdate();
            }
            
            
            
            //search for address if not found add
            String addressSearchSQL = "SELECT address.address FROM address "
                                     +"WHERE address.addressId = " + "'" + addressID + "'";
            
            ResultSet rsAddress = stmt.executeQuery(addressSearchSQL);
            String currentAddress1 = "";
            
            while (rsAddress.next()){
                currentAddress1 = rsAddress.getString("address");
            }

            if (!(currentAddress1.equals(address1))){
                String addAddressSQL = "INSERT INTO address (address, address2, cityId, postalCode, phone, createDate,"
                                     + " createdBy, lastUpdate, lastUpdateBy) "
                                     + "VALUES ('" + address1 + "', '" + address2 + "', '" + cityID + "', '" + postalCode + "', '"
                                     + phone + "', '" + newDate + "', '" + user + "', '" + newDate + "', '" + user + "');";
                
                PreparedStatement addressAdd = this.myCon.prepareStatement(addAddressSQL);
                addressAdd.executeUpdate();
                
                String newAddressSQL = "SELECT addressId FROM address "
                                     +"WHERE address.address = " + "'" + address1 + "' "
                                     +"AND address.address2 = " + "'" + address2 + "' "
                                     +"AND cityId = " + "'" + cityID + "';";
                
                ResultSet rsGetAddress = stmt.executeQuery(newAddressSQL);
            
                while (rsGetAddress.next()){
                    addressID = rsGetAddress.getInt("AddressId");
                }
                
            }
        
            else{
                String updateAddressSQL = "UPDATE address "
                                         + "SET address2 = '" + address2 + "', "
                                         + "cityId = '" + cityID + "', "
                                         + "postalCode = '" + postalCode + "', "
                                         + "phone = '" + phone + "', "
                                         + "lastUpdate = '" + newDate + "', "
                                         + "lastUpdateBy = '" + user + "' "
                                         + "WHERE addressId = '" + addressID + "';";
                    
                    PreparedStatement addressUpdate = this.myCon.prepareStatement(updateAddressSQL);
                    addressUpdate.executeUpdate();
            }
            
            
            //customer
            
            //check if customer exists
            String customerSearchSQL = "SELECT customer.customerName FROM customer "
                                      +"WHERE customerId = '" + customerID + "';";
            
            
            ResultSet rsCustomer = stmt.executeQuery(customerSearchSQL);
            String currentCustomerName = "";
            
            while (rsCustomer.next()){
                currentCustomerName = rsCustomer.getString("customerName");
            }

            if(!(currentCustomerName.equals(customerName))){
                String customerUpdateStatement = "UPDATE customer "
                                               + "SET active = '0' "
                                               + "WHERE customerId = '" + customerID + "';";

                PreparedStatement customerUpdate = this.myCon.prepareStatement(customerUpdateStatement);
                customerUpdate.executeUpdate();
                
                String customerAddSQL = "INSERT INTO customer (customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy) "
                                    + "VALUES ('" + customerName + "', '" + addressID + "', '1', '" + newDate + "', '" + user + "', '" + newDate
                                    + "', '" + user + "');";
                
                
                PreparedStatement customerAdd = this.myCon.prepareStatement(customerAddSQL);              
                customerAdd.executeUpdate();
                
                this.myCon.commit();
                
                if(languageSetting == 0){
                    return "Customer Added";
                }
                else{
                    return "Cliente Agregado";
                }
            }
            
            else{
                String sqlStatement =  "UPDATE customer "
                                     + "SET customerName = '" + customerName + "', "
                                     + "addressId = '" + addressID + "', "
                                     + "active = '1', "
                                     + "lastUpdate = '" + newDate + "', "
                                     + "lastUpdateBy = '" + user + "' "
                                     + "WHERE customerId = '" + customerID + "';";
                        

                PreparedStatement customerAdd = this.myCon.prepareStatement(sqlStatement);
                int rowAffected = customerAdd.executeUpdate();
                
                if (rowAffected > 0) {
                    this.myCon.commit();
                    if(languageSetting == 0){
                        return "Customer Updated";
                    }
                    else{
                        return "Cliente actualizado";
                    }
                }
                
                else {
                    this.myCon.rollback();
                    if(languageSetting == 0){
                        return "Customer Update Failed";
                    }
                    else{
                        return "Error en la actualización del cliente";
                    }
                }
            }
        }
        
        catch(SQLException e){
            System.out.println(e);
            try {
                this.myCon.rollback();
            }
            
            catch (SQLException se2) {
                System.out.println("Rollback error");
            }
            
            if(languageSetting == 0){
                return "Customer Update Failed";
            }
            else {
                return "Error en la actualización del cliente";
            }
        }
    }
    
    // deactivate customerId
    public String deactivateCustomer(int customerID, String user, int languageSetting){
        try {
            ZoneId zoneId = ZoneId.of("UTC");
            LocalDateTime lastUpdate = ZonedDateTime.now(zoneId).toLocalDateTime();
            
            String sqlStatement = "UPDATE customer\n" + 
                                  "SET active = '0', lastUpdate = '" + lastUpdate + "', lastUpdateBy = '" + user + "'\n" +
                                  "WHERE customerId = " + "'" + customerID + "';";
            
            PreparedStatement deleteCustomer = this.myCon.prepareStatement(sqlStatement);
            int rowAffected = deleteCustomer.executeUpdate();
                
            if (rowAffected > 0){
                this.myCon.commit();
                if(languageSetting == 0){
                    return "Customer Deleted";
                }
                else{
                    return "Cliente Eliminado";
                }
            }
            else{
                this.myCon.rollback();
                if(languageSetting == 0){
                    return "Customer Delete Failed";
                }
                else {
                    return "Error en la Eliminación del Cliente";
                }
            }
        }
        
        catch(SQLException e) {
            System.out.println(e);
            if(languageSetting == 0){
                return "Delete Failed";
            }
            else{
                return "Eliminar Fallido";
            }
        }
    }
    
    
    // add appointment
    public String addAppointment(int customerId, String title, String description, String location, String contact, String type, 
                                 String url, String date, String start, String end, int userID, String user, int languageSetting){
        try {
            ZoneId zoneId = ZoneId.of("UTC");
            LocalDateTime createDate = ZonedDateTime.now(zoneId).toLocalDateTime();
            
            // parse date
            String dateArray[] = date.split("/");
            String timeStartArray[] = start.split(":");
            String timeEndArray[] = end.split(":");
            
            int month = Integer.parseInt(dateArray[0]);
            int day = Integer.parseInt(dateArray[1]);
            int year = Integer.parseInt(dateArray[2]);
            int hourStart = Integer.parseInt(timeStartArray[0]);
            int minStart = Integer.parseInt(timeStartArray[1]);
            int hourEnd = Integer.parseInt(timeEndArray[0]);
            int minEnd = Integer.parseInt(timeEndArray[1]);
            
            LocalDateTime startDateTime = ZonedDateTime.of(year, month, day, hourStart, minStart, 0, 0, ZoneId.systemDefault()).withZoneSameInstant(zoneId).toLocalDateTime();
            LocalDateTime endDateTime = ZonedDateTime.of(year, month, day, hourEnd, minEnd, 0, 0, ZoneId.systemDefault()).withZoneSameInstant(zoneId).toLocalDateTime();
            
            String addAppointmentSQL = "INSERT INTO appointment (customerId, userId, title, description, location, contact, type, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy) "
                                  + "VALUES ('" + customerId + "', '" + userID + "', '" + title + "', '" + description + "', '" + location + "', '" + contact + "', '" + type + "', '" + url + "', '" + startDateTime + "', '" + endDateTime + "', '" + createDate + "', '" + user + "', '" + createDate + "', '" + user + "')";
                
            PreparedStatement appointmentAdd = this.myCon.prepareStatement(addAppointmentSQL);
                
            int rowAffected = appointmentAdd.executeUpdate();
                
                if (rowAffected > 0){
                    this.myCon.commit();
                    if (languageSetting == 0) {
                        return "Appointment Added";
                    }
                    else{
                        return "Cita Agregada";
                    }
                }
                
                else{
                    this.myCon.rollback();
                    if (languageSetting == 0) {
                        return "Appointment Add Failed";
                    }
                    else{
                        return "No se Pudo Agregar la Cita";
                    }
                }    
        }
            
        catch(SQLException e){
            System.out.println(e);
            try{
                this.myCon.rollback();
            }
            
            catch (SQLException se2){
                System.out.println("Rollback error");
            }
            if (languageSetting == 0) {
                return "Appointment Add Failed";
            }
            else{
                return "No se Pudo Agregar la Cita";
            }
        }   
    }
    
    
    // update appointment
    public String updateAppointment(String appointmentID, String customerID, String title, String description, String location, 
                                    String contact, String type, String url, String date, String start, String end, int userID, 
                                    String user, int languageSetting){
        try{ 
            ZoneId zoneId = ZoneId.of("UTC");
            LocalDateTime updateDate = ZonedDateTime.now(zoneId).toLocalDateTime();
            
            // parse date
            String dateArray[] = date.split("/");
            String timeStartArray[] = start.split(":");
            String timeEndArray[] = end.split(":");
            
            int month = Integer.parseInt(dateArray[0]);
            int day = Integer.parseInt(dateArray[1]);
            int year = Integer.parseInt(dateArray[2]);
            int hourStart = Integer.parseInt(timeStartArray[0]);
            int minStart = Integer.parseInt(timeStartArray[1]);
            int hourEnd = Integer.parseInt(timeEndArray[0]);
            int minEnd = Integer.parseInt(timeEndArray[1]);
            
            LocalDateTime startDateTime = ZonedDateTime.of(year, month, day, hourStart, minStart, 0, 0, zoneId).toLocalDateTime();
            LocalDateTime endDateTime = ZonedDateTime.of(year, month, day, hourEnd, minEnd, 0, 0, zoneId).toLocalDateTime();
            
            String updateAppointmentSQL = "UPDATE appointment "
                                        + "SET "
                                        + String.format("appointment.customerId='%s', ",customerID)
                                        + String.format("appointment.userId='%s', ",userID)
                                        + String.format("appointment.title='%s', ",title)
                                        + String.format("appointment.description='%s', ",description)
                                        + String.format("appointment.location='%s', ",location)
                                        + String.format("appointment.contact='%s', ",contact)
                                        + String.format("appointment.type='%s', ",type)
                                        + String.format("appointment.url='%s', ",url)
                                        + String.format("appointment.start='%s', ",startDateTime)
                                        + String.format("appointment.end='%s', ",endDateTime)
                                        + String.format("appointment.lastUpdate='%s', ",updateDate)
                                        + String.format("appointment.lastUpdateBy='%s' ",user)
                                        + String.format("WHERE appointment.appointmentId='%s';", appointmentID);
            
                
            PreparedStatement appointmentUpdate = this.myCon.prepareStatement(updateAppointmentSQL);
            
            int rowAffected = appointmentUpdate.executeUpdate();
                
                if (rowAffected > 0){
                    this.myCon.commit();
                    if (languageSetting == 0) {
                        return "Appointment Updated";
                    }
                    else{
                        return "Cita Actualizada";
                    }
                }
                else{
                    this.myCon.rollback();
                    if (languageSetting == 0) {
                        return "Appointment Update Failed";
                    }
                    else{
                        return "La Actualización de la Cita Falló";
                    }
                }    
        }
            
        catch(SQLException e){
            System.out.println(e);
            try{
                this.myCon.rollback();
            }
            catch (SQLException se2){
                System.out.println("Rollback error");
            }
            if (languageSetting == 0) {
                return "Appointment Update Failed";
            }
            else{
                return "La Actualización de la Cita Falló";
            }
        }
    }
    
    
    public ArrayList<Appointment> getAppointmentsTableData(int userId, LocalDate day){
        try{
            Statement stmt = this.myCon.createStatement();
            
            String year = String.valueOf(day.getYear());
            String month;
            if(day.getMonthValue() < 10){
                month = "0" + String.valueOf(day.getMonthValue());
            }
            else{
                month = String.valueOf(day.getMonthValue());
            }

            String sqlStatement =  "SELECT appointment.appointmentId, appointment.customerId, user.userName, customer.customerName, appointment.title, appointment.description, appointment.location, appointment.contact, appointment.type, appointment.url, appointment.start, appointment.end, appointment.createDate, appointment.createdBy, appointment.lastUpdate, appointment.lastUpdateBy FROM appointment "
                                 + "JOIN customer "
                                 + "ON appointment.customerId = customer.customerId "
                                 + "JOIN user "
                                 + "ON appointment.userId = user.userId "
                                 + "WHERE appointment.start >= '"
                                 + year
                                 + "-"
                                 + month
                                 + "-"
                                 + "01' "
                                 + "AND appointment.end <= '"
                                 + year
                                 + "-"
                                 + month
                                 + "-"
                                 + "31"
                                 + "' "
                                 + "AND appointment.userId = "
                                 + Integer.toString(userId)
                                 + ";";
            
   
            ResultSet rs = stmt.executeQuery(sqlStatement);
            ArrayList<Appointment> myAppointments = new ArrayList<Appointment>();
            
            ZoneId zone= ZoneId.systemDefault();
            
            while(rs.next()){
//                public Appointment(int appointmentID, int customerID, int userID, String title, String description,
//                       String location, String contact, String type, String url, Timestamp start,
//                       Timestamp end, Timestamp createDate, String createdBy, Timestamp lastUpdate, String lastUpdateBy)
                int appointmentId = rs.getInt("appointmentId");
                int customerId = rs.getInt("customerId");
                String customerName = rs.getString("customerName");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String location = rs.getString("location");
                String contact = rs.getString("contact");
                String type = rs.getString("type");
                String url = rs.getString("url");
                LocalDateTime start = rs.getTimestamp("start").toLocalDateTime().atZone(zone).toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("end").toLocalDateTime().atZone(zone).toLocalDateTime();
                LocalDateTime createDate = rs.getTimestamp("createDate").toLocalDateTime().atZone(zone).toLocalDateTime();
                String createdBy = rs.getString("createdBy");
                LocalDateTime lastUpdate = rs.getTimestamp("lastUpdate").toLocalDateTime().atZone(zone).toLocalDateTime();
                String lastUpdateBy = rs.getString("lastUpdateBy");
                
                Appointment myAppointment = new Appointment(appointmentId, customerId, customerName, title, description, location, contact, type, 
                                                            url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy);
                myAppointments.add(myAppointment);
            }
            
            
            return myAppointments;
        }
        
        catch(Exception e){
            System.out.println(e);
            ArrayList<Appointment> myAppointments = new ArrayList<Appointment>();
            return myAppointments;
        }
    }
    
    public ArrayList<Appointment> getAppointmentsOnDate(int userId, LocalDate day){
        try{
            Statement stmt = this.myCon.createStatement();
            
            String year = String.valueOf(day.getYear());
            String month;
            if(day.getMonthValue() < 10){
                month = "0" + String.valueOf(day.getMonthValue());
            }
            else{
                month = String.valueOf(day.getMonthValue());
            }
            
            String dayOfMonth;
            if(day.getDayOfMonth()<10){
                dayOfMonth = "0" + String.valueOf(day.getDayOfMonth());
            }
            else{
                dayOfMonth = String.valueOf(day.getDayOfMonth());
            }

            String sqlStatement =  "SELECT appointment.appointmentId, appointment.customerId, user.userName, customer.customerName, appointment.title, appointment.description, appointment.location, appointment.contact, appointment.type, appointment.url, appointment.start, appointment.end, appointment.createDate, appointment.createdBy, appointment.lastUpdate, appointment.lastUpdateBy FROM appointment "
                                 + "JOIN customer "
                                 + "ON appointment.customerId = customer.customerId "
                                 + "JOIN user "
                                 + "ON appointment.userId = user.userId "
                                 + "WHERE appointment.start >= '"
                                 + year
                                 + "-"
                                 + month
                                 + "-"
                                 + dayOfMonth
                                 + "' "
                                 + "AND appointment.end < '"
                                 + year
                                 + "-"
                                 + month
                                 + "-"
                                 + String.valueOf(Integer.parseInt(dayOfMonth)+1)
                                 + "' "
                                 + "AND appointment.userId = "
                                 + Integer.toString(userId)
                                 + ";";
            
   
            ResultSet rs = stmt.executeQuery(sqlStatement);
            ArrayList<Appointment> myAppointments = new ArrayList<Appointment>();
            
            ZoneId zone = ZoneId.systemDefault();
            ZoneId zoneUTC = ZoneId.of("UTC");
            
            while(rs.next()){
                int appointmentId = rs.getInt("appointmentId");
                int customerId = rs.getInt("customerId");
                String customerName = rs.getString("customerName");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String location = rs.getString("location");
                String contact = rs.getString("contact");
                String type = rs.getString("type");
                String url = rs.getString("url");
                LocalDateTime start = rs.getTimestamp("start").toLocalDateTime().atZone(zoneUTC).withZoneSameInstant(zone).toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("end").toLocalDateTime().atZone(zoneUTC).withZoneSameInstant(zone).toLocalDateTime();
                LocalDateTime createDate = rs.getTimestamp("createDate").toLocalDateTime().atZone(zoneUTC).withZoneSameInstant(zone).toLocalDateTime();
                String createdBy = rs.getString("createdBy");
                LocalDateTime lastUpdate = rs.getTimestamp("lastUpdate").toLocalDateTime().atZone(zoneUTC).withZoneSameInstant(zone).toLocalDateTime();
                String lastUpdateBy = rs.getString("lastUpdateBy");
                
                Appointment myAppointment = new Appointment(appointmentId, customerId, customerName, title, description, location, contact, 
                                                            type, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy);
                myAppointments.add(myAppointment);
            }
            
            
            return myAppointments;
        }
        
        catch(Exception e){
            System.out.println(e);
            ArrayList<Appointment> myAppointments = new ArrayList<Appointment>();
            return myAppointments;
        }
    }
    
    public ArrayList<ArrayList> appointmentmentTypeMonthReport(LocalDate day){
        try{
            Statement stmt = this.myCon.createStatement();
            
            String year = String.valueOf(day.getYear());
            String month;
            if(day.getMonthValue() < 10){
                month = "0" + String.valueOf(day.getMonthValue());
            }
            else{
                month = String.valueOf(day.getMonthValue());
            }

            String sqlStatement = "SELECT type, COUNT(appointmentId) as number "
                                + "FROM appointment "
                                + "WHERE appointment.start >= '"
                                + year
                                + "-"
                                + month
                                + "-"
                                + "01' "
                                + "AND appointment.end <= '"
                                + year
                                + "-"
                                + month
                                + "-"
                                + "31"
                                + "' "
                                + "GROUP BY type;";
            
            ResultSet rs = stmt.executeQuery(sqlStatement);
            ArrayList<ArrayList> appointmentTypeCount = new ArrayList<ArrayList>();
            while(rs.next()){
                 ArrayList<String> appointmentData = new ArrayList<String>();
                 appointmentData.add(rs.getString("type"));
                 appointmentData.add(rs.getString("number"));
                 appointmentTypeCount.add(appointmentData);
            }
            
            return appointmentTypeCount;
        }
        
        catch(Exception e){
            System.out.println(e);
            ArrayList<ArrayList> appointmentTypeCount = null;
            return appointmentTypeCount;
        }
    }
    
    public HashMap<String, ArrayList<ArrayList<String>>> consultantScheduleReport(){
        try{
            Statement stmt = this.myCon.createStatement();

            String sqlStatement = "SELECT user.userName, customer.customerName, appointment.title, appointment.description, appointment.location, appointment.contact, appointment.url, appointment.start, appointment.end\n" +
                                  "FROM appointment\n" +
                                  "JOIN customer ON appointment.customerId=customer.customerId\n" +
                                  "JOIN user ON appointment.userId=user.userId;";
            
            ResultSet rs = stmt.executeQuery(sqlStatement);
            HashMap<String, ArrayList<ArrayList<String>>> schedule = new HashMap<String, ArrayList<ArrayList<String>>>();
            ZoneId zone = ZoneId.systemDefault();
            ZoneId zoneUTC = ZoneId.of("UTC");
            
            while(rs.next()){
                ArrayList<String> appointmentData = new ArrayList<String>();
                
                String userName = rs.getString("userName");
                String customerName = rs.getString("customerName");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String location = rs.getString("location");
                String contact = rs.getString("contact");
                String url = rs.getString("url");
                String start = rs.getTimestamp("start").toLocalDateTime().atZone(zoneUTC).withZoneSameInstant(zone).toString();
                String end = rs.getTimestamp("end").toLocalDateTime().atZone(zoneUTC).withZoneSameInstant(zone).toString();
                
                appointmentData.add(customerName);
                appointmentData.add(title);
                appointmentData.add(description);
                appointmentData.add(location);
                appointmentData.add(contact);
                appointmentData.add(url);
                appointmentData.add(start);
                appointmentData.add(end);
                
                if(schedule.containsKey(userName)){
                    schedule.get(userName).add(appointmentData);
                }
                
                else{
                    schedule.put(userName, new ArrayList<ArrayList<String>>());
                    schedule.get(userName).add(appointmentData);
                }
            }
            
            return schedule;
        }
        
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    
    public ArrayList<ArrayList<String>> customerReport(LocalDate day){
         try{
            Statement stmt = this.myCon.createStatement();
            
            String year = String.valueOf(day.getYear());
            String month;
            if(day.getMonthValue() < 10){
                month = "0" + String.valueOf(day.getMonthValue());
            }
            else{
                month = String.valueOf(day.getMonthValue());
            }
            
            String sqlStatement = "SELECT customer.customerName, COUNT(appointmentId) as appointmentCount\n" +
                                  "FROM appointment\n"
                                + "JOIN customer ON customer.customerId = appointment.customerId\n"
                                + "WHERE appointment.start >= '"
                                + year
                                + "-"
                                + month
                                + "-"
                                + "01' "
                                + "AND appointment.end <= '"
                                + year
                                + "-"
                                + month
                                + "-"
                                + "31"
                                + "' "
                                + "GROUP BY customerName;";
            
            ResultSet rs = stmt.executeQuery(sqlStatement);
            ArrayList<ArrayList<String>> appointmentTypeCount = new ArrayList<ArrayList<String>>();
            while(rs.next()) {
                 ArrayList<String> appointmentData = new ArrayList<String>();
                 appointmentData.add(rs.getString("customerName"));
                 appointmentData.add(rs.getString("appointmentCount"));
                 appointmentTypeCount.add(appointmentData);
            }
            
            return appointmentTypeCount;
        }
        
        catch(Exception e){
            System.out.println(e);
            ArrayList<ArrayList<String>> appointmentTypeCount = null;
            return appointmentTypeCount;
        }
    }
    
    public void closeConnection(){
        try{
            this.myCon.close();
        }
        
        catch(Exception e){
            System.out.println(e);
        }
    }
    
}
