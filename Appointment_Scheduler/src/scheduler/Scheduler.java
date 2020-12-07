

package scheduler;

import Scenes.Login_Scene;
import Scenes.Customer_Scene;
import Scenes.Appointments_Scene;
import Scenes.Add_Customers_Scene;
import Scenes.Add_Appointment_Scene;
import Calendar.Calendar;
import Objects.Customer;
import Connectors.MySQLConnector;
import Objects.Appointment;
import Panes.Add_Appointment_Pane;
import Panes.Add_Customer_Pane;
import Panes.Appointment_Pane;
import Panes.Customer_Pane;
import Panes.Login_Pane;
import Panes.Main_Pane;
import Panes.Report_Pane;
import Panes.Update_Appointment_Pane;
import Panes.Update_Customer_Pane;
import Scenes.Main_Scene;
import Scenes.Report_Scene;
import Scenes.Update_Appointment_Scene;
import Scenes.Update_Customers_Scene;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class Scheduler{
    
    // Parameters
    private String currentUser;
    private int currentUserId;
    private int customerId;
    private int addressId;
    private int cityId;
    private int countryId;
    private int languageSetting;
    private ArrayList<LocalDateTime> appointmentStartTimesAvailable;
    private ArrayList<LocalDateTime> appointmentEndTimesAvailable;
    
    // Panes
    private final Login_Pane loginPane;
    private final Customer_Pane customerPane;
    private final Appointment_Pane appointmentPane;
    private final Main_Pane mainPane;
    private final Add_Customer_Pane addCustomerPane;
    private final Update_Customer_Pane updateCustomerPane;
    private final Add_Appointment_Pane addAppointmentPane;
    private final Update_Appointment_Pane updateAppointmentPane;
    private final Report_Pane reportPane;
    
    // Scenes
    private Add_Appointment_Scene addAppointmentScene;
    private Add_Customers_Scene addCustomersScene;
    private Appointments_Scene appointmentsScene;
    private Customer_Scene customerScene;
    private Login_Scene loginScene;
    private Main_Scene mainScene;
    private Update_Appointment_Scene updateAppointmentScene;
    private Update_Customers_Scene updateCustomersScene;
    private Report_Scene reportScene;
    
    // Connectors
    private MySQLConnector myConnector;
    
    // Calendar
    private Calendar calendar;
    
    // Stage
    private Stage primary;
    
    // Tableviews
    private TableView customerTable;
    private TableView.TableViewSelectionModel<Customer> CustomerSelectionModel;
    private TableView addAppointmentTable;
    private TableView.TableViewSelectionModel<Customer> addAppointmentCustomerSelectionModel;
    private TableView updateAppointmentTable;
    private TableView.TableViewSelectionModel<Customer> updateAppointmentCustomerSelectionModel;

    // ComboBox
    private ComboBox<String> addAppointmentStartDrop;
    private ComboBox<String> addAppointmentEndDrop;
    private ComboBox<String> updateAppointmentStartDrop;
    private ComboBox<String> updateAppointmentEndDrop;
    
    // Images
    private final Image left_arrow;
    private final Image right_arrow;
    private final ImageView left_arrow_img;
    private final ImageView right_arrow_img;
    
    // Button
    private final Button forwardButton;
    private final Button backButton;
    private final Button changeView;
    private final Button returnButton;
    
    public Scheduler(Stage primaryStage){
        
        
        // Parameters
        this.currentUser = "";
        this.currentUserId = -1;
        this.customerId = -1;
        this.addressId = -1;
        this.cityId = -1;
        this.countryId = -1;
        String l = Locale.getDefault().toString().substring(0, 2);
        if (l.equals("es")){
            this.languageSetting = 1;
        }
        else{
            this.languageSetting = 0;
        }
        
        // Stage
        this.primary = primaryStage;
        
        // Connector
        this.myConnector = new MySQLConnector();
        
        // Calendar
        this.calendar = new Calendar();
        
        // Images
        this.left_arrow = new Image("resources/left_arrow.png");
        this.left_arrow_img = new ImageView(this.left_arrow);
        this.left_arrow_img.setFitHeight(40);
        this.left_arrow_img.setFitWidth(40);
        
        this.right_arrow = new Image("resources/right_arrow.png");
        this.right_arrow_img = new ImageView(this.right_arrow);
        this.right_arrow_img.setFitHeight(40);
        this.right_arrow_img.setFitWidth(40);
        
        this.forwardButton = new Button("", this.right_arrow_img);
        this.backButton = new Button("", this.left_arrow_img);
        this.changeView = new Button("MONTHLY");
        
        this.returnButton = new Button("BACK");
        
        // Tableviews
        this.customerTable = new TableView();
        this.customerTable.prefHeightProperty().bind(this.primary.heightProperty());
        this.customerTable.minWidth(300.0);
        this.customerTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        this.CustomerSelectionModel = this.customerTable.getSelectionModel();
        this.customerTable.setEditable(false);
        
        this.addAppointmentTable = new TableView();
        this.addAppointmentTable.prefHeightProperty().bind(this.primary.heightProperty());
        this.addAppointmentTable.minWidth(300.0);
        this.addAppointmentTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        this.addAppointmentCustomerSelectionModel = this.addAppointmentTable.getSelectionModel();
        this.addAppointmentTable.setEditable(false);
        
        this.updateAppointmentTable = new TableView();
        this.updateAppointmentTable.prefHeightProperty().bind(this.primary.heightProperty());
        this.updateAppointmentTable.minWidth(300.0);
        this.updateAppointmentTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        this.updateAppointmentCustomerSelectionModel = this.updateAppointmentTable.getSelectionModel();
        this.updateAppointmentTable.setEditable(false);
        
        // ComboBox
        this.addAppointmentStartDrop = new ComboBox<>();
        this.addAppointmentEndDrop= new ComboBox<>();
        this.updateAppointmentStartDrop = new ComboBox<>();
        this.updateAppointmentEndDrop = new ComboBox<>();
        
        // Set Panes
        this.loginPane = new Login_Pane();
        this.customerPane = new Customer_Pane(this.customerTable);
        this.appointmentPane = new Appointment_Pane(this.calendar.getCalendarGrid(), this.backButton, this.forwardButton, this.changeView, this.returnButton);
        this.mainPane = new Main_Pane();
        this.addCustomerPane = new Add_Customer_Pane();
        this.updateCustomerPane = new Update_Customer_Pane();
        this.addAppointmentPane = new Add_Appointment_Pane(this.addAppointmentTable, this.addAppointmentStartDrop, this.addAppointmentEndDrop);
        this.updateAppointmentPane = new Update_Appointment_Pane(this.updateAppointmentTable, this.updateAppointmentStartDrop, this.updateAppointmentEndDrop);
        this.reportPane = new Report_Pane();
        
        // Set Scenes
        this.addAppointmentScene = new Add_Appointment_Scene(this.addAppointmentPane);
        this.addCustomersScene = new Add_Customers_Scene(this.addCustomerPane);
        this.appointmentsScene = new Appointments_Scene(this.appointmentPane);
        this.customerScene = new Customer_Scene(this.customerPane);
        this.loginScene = new Login_Scene(this.loginPane);
        this.mainScene = new Main_Scene(this.mainPane);
        this.updateAppointmentScene = new Update_Appointment_Scene(this.updateAppointmentPane);
        this.updateCustomersScene = new Update_Customers_Scene(this.updateCustomerPane);
        this.reportScene = new Report_Scene(this.reportPane);
        
        
        if(this.languageSetting == 1){
            this.loginScene.getPane().setToSpanish();
            this.mainScene.getPane().setToSpanish();
            this.customerScene.getPane().setToSpanish();
            this.addCustomersScene.getPane().setToSpanish();
            this.updateCustomersScene.getPane().setToSpanish();
            this.addAppointmentScene.getPane().setToSpanish();
            this.updateAppointmentScene.getPane().setToSpanish();
            this.reportScene.getPane().setToSpanish();

            this.primary.setTitle("Planificador Iniciar Sesión");
            this.changeView.setText("MENSUAL");
            this.returnButton.setText("RETORNO");
        }
        
        this.primary.setTitle("Scheduler Login");
        this.primary.setScene(loginScene.getScene());
        this.primary.show();
        
        // Calendar
        this.calendar.createGridMonthly(this.appointmentsScene.getPane().getCalendarDate(), 
                                     this.primary, 
                                     this.addAppointmentScene.getScene(), 
                                     this.appointmentsScene.getPane().getAppointmentDate(), 
                                     this.myConnector, 
                                     this.addAppointmentTable,
                                     this.currentUserId,
                                     this.addAppointmentStartDrop,
                                     this.addAppointmentEndDrop,
                                     this.languageSetting);
        this.appointmentsScene.getPane().setCalendarGrid(this.calendar.getCalendarGrid(), this.languageSetting);
        
        
        
        // Add Customers Buttons
        
        // Add Customers Cancel Button
        this.addCustomersScene.getPane().getAddCustomerCancelButton().setOnAction(event ->{
            // Clear Add Customers Table
            this.customerTable.getItems().clear();
            
            // Refresh and fill Customer Table
            ArrayList<Customer> customers = this.myConnector.getCustomersTableData();
            customerTable.getItems().addAll(customers);
            
            // Set scene to Customers
            this.primary.setScene(this.customerScene.getScene());
            
            if(this.languageSetting == 0){
                this.primary.setTitle("Customers");
            }
            
            else{
                this.primary.setTitle("Clientes");
            }
            
            // Clear Add Customer Fields
            this.addCustomersScene.getPane().setAddCustomerNameField("");
            this.addCustomersScene.getPane().setAddCustomerPhoneField("");
            this.addCustomersScene.getPane().setAddCustomerCountryField("");
            this.addCustomersScene.getPane().setAddCustomerAddress1Field("");
            this.addCustomersScene.getPane().setAddCustomerAddress2Field("");
            this.addCustomersScene.getPane().setAddCustomerCityField("");
            this.addCustomersScene.getPane().setAddCustomerPostalField("");
        });
        
        // Add Customer add button
        this.addCustomersScene.getPane().getAddCustomerAddButton().setOnAction(event ->{
            
            // Get Field Data
            String SQLaddCustomerName = this.addCustomersScene.getPane().getAddCustomerNameField();
            String SQLaddPhone = this.addCustomersScene.getPane().getAddCustomerPhoneField();
            String SQLaddCountry = this.addCustomersScene.getPane().getAddCustomerCountryField();
            String SQLaddAddress1 = this.addCustomersScene.getPane().getAddCustomerAddress1Field();
            String SQLaddAddress2 = this.addCustomersScene.getPane().getAddCustomerAddress2Field();
            String SQLaddCustomerCity = this.addCustomersScene.getPane().getAddCustomerCityField();
            String SQLaddCustomerPostal = this.addCustomersScene.getPane().getAddCustomerPostalField();
            
            
            // Validation Checks
            if(!(SQLaddCustomerName.matches("^[\\p{L} .'-]+$"))){
                if(this.languageSetting == 0){
                    this.addCustomersScene.getPane().setAddCustomerErrorResponse("Customer Name Invalid Format");
                }
                else{
                    this.addCustomersScene.getPane().setAddCustomerErrorResponse("Nombre de Cliente No Válido");
                }
                return;
            }
            
            if(!(SQLaddPhone.matches("\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}"))){
                if(this.languageSetting == 0){
                    this.addCustomersScene.getPane().setAddCustomerErrorResponse("Phone Number Invalid Format");
                }
                else{
                    this.addCustomersScene.getPane().setAddCustomerErrorResponse("Número de Teléfono No Válido");
                }
                return;
            }
            
            if(!(SQLaddCountry.matches("[a-zA-Z]+"))){
                if(this.languageSetting == 0){
                    this.addCustomersScene.getPane().setAddCustomerErrorResponse("Country Invalid Format");
                }
                else{
                    this.addCustomersScene.getPane().setAddCustomerErrorResponse("País No Válido");
                }
                return;
            }
           
            if(SQLaddAddress1.equals("")){
                if(this.languageSetting == 0){
                    this.addCustomersScene.getPane().setAddCustomerErrorResponse("Address Required");
                }
                else{
                    this.addCustomersScene.getPane().setAddCustomerErrorResponse("Dirección requerida");
                }
                return;
            }
            
            if(SQLaddAddress2.equals("")){
                if(this.languageSetting == 0){
                    this.addCustomersScene.getPane().setAddCustomerErrorResponse("Address Required");
                }
                else{
                    this.addCustomersScene.getPane().setAddCustomerErrorResponse("Dirección requerida");
                }
                return;
            }
            
            if(!(SQLaddCustomerCity.matches("^([a-zA-Z\\u0080-\\u024F]+(?:. |-| |'))*[a-zA-Z\\u0080-\\u024F]*$"))){
                if(this.languageSetting == 0){
                    this.addCustomersScene.getPane().setAddCustomerErrorResponse("City Invalid Format");
                }
                else{
                    this.addCustomersScene.getPane().setAddCustomerErrorResponse("Ciudad No Válido");
                }
                return;
            }
            
            if(!(SQLaddCustomerPostal.matches("\\d{5}(-\\d{4})?"))){
                if(this.languageSetting == 0){
                    this.addCustomersScene.getPane().setAddCustomerErrorResponse("Postal Code Invalid Format");
                }
                else{
                    this.addCustomersScene.getPane().setAddCustomerErrorResponse("Código Postal No Válido");
                }
                return;
            }
            
            
            // reset connection
            this.myConnector.setConnection();
            
            // add customer and other necessary table entries
            String addCustomerResponse;
            
            // add customer
            addCustomerResponse = myConnector.addCustomer(SQLaddCustomerName,
                                                            SQLaddPhone,
                                                            SQLaddCountry,
                                                            SQLaddAddress1,
                                                            SQLaddAddress2,
                                                            SQLaddCustomerCity,
                                                            SQLaddCustomerPostal,
                                                            this.currentUser,
                                                            this.languageSetting);
            
            // set customer response
            this.addCustomersScene.getPane().setAddCustomerErrorResponse(addCustomerResponse);
        });
        
        
        // Update Customers Buttons
        
        // Update Customer cancel button
        this.updateCustomersScene.getPane().getUpdateCustomerCancelButton().setOnAction(event ->{
            // clear customer table old data
            customerTable.getItems().clear();
            
            // refresh and fill customer table
            ArrayList<Customer> customers = this.myConnector.getCustomersTableData();
            customerTable.getItems().addAll(customers);
            
            // set scene to Customers
            this.primary.setScene(this.customerScene.getScene());
            
            if (this.languageSetting == 0){
                this.primary.setTitle("Customers");
            }
            else{
                this.primary.setTitle("Clientes");
            }
            
            // clear update customer fields
            this.updateCustomersScene.getPane().setUpdateCustomerNameField("");
            this.updateCustomersScene.getPane().setUpdateCustomerPhoneField("");
            this.updateCustomersScene.getPane().setUpdateCustomerCountryField("");
            this.updateCustomersScene.getPane().setUpdateCustomerAddress1Field("");
            this.updateCustomersScene.getPane().setUpdateCustomerAddress2Field("");
            this.updateCustomersScene.getPane().setUpdateCustomerCityField("");
            this.updateCustomersScene.getPane().setUpdateCustomerPostalField("");
            
            // clear customer data
            this.customerId = -1;
            this.addressId = -1;
            this.cityId = -1;
            this.countryId = -1;   
        });
        
        // Update Customer update button
        this.updateCustomersScene.getPane().getUpdateCustomerButton().setOnAction(event ->{
            
            // Get Field Data
            String SQLupdateCustomerName = this.updateCustomersScene.getPane().getUpdateCustomerNameField();
            String SQLupdatePhone = this.updateCustomersScene.getPane().getUpdateCustomerPhoneField();
            String SQLupdateCountry = this.updateCustomersScene.getPane().getUpdateCustomerCountryField();
            String SQLupdateAddress1 = this.updateCustomersScene.getPane().getUpdateCustomerAddress1Field();
            String SQLupdateAddress2 = this.updateCustomersScene.getPane().getUpdateCustomerAddress2Field();
            String SQLupdateCustomerCity = this.updateCustomersScene.getPane().getUpdateCustomerCityField();
            String SQLupdateCustomerPostal = this.updateCustomersScene.getPane().getUpdateCustomerPostalField();
            
            // Validation checks
            if(!(SQLupdateCustomerName.matches("^[\\p{L} .'-]+$"))){
                if (this.languageSetting == 0) {
                    this.updateCustomersScene.getPane().setUpdateCustomerErrorResponse("Customer Name Invalid Format");
                }
                else {
                    this.updateCustomersScene.getPane().setUpdateCustomerErrorResponse("Nombre del Cliente No Válido");
                }
                return;
            }
            
            if(!(SQLupdatePhone.matches("\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}"))) {
                if (this.languageSetting == 0) {
                    this.updateCustomersScene.getPane().setUpdateCustomerErrorResponse("Phone Number Invalid Format");
                }
                else {
                    this.updateCustomersScene.getPane().setUpdateCustomerErrorResponse("Teléfono No Válido");
                }
                return;
            }
            
            if(!(SQLupdateCountry.matches("[a-zA-Z]+"))){
                if (this.languageSetting == 0) {
                    this.updateCustomersScene.getPane().setUpdateCustomerErrorResponse("Country Invalid Format");
                }
                else {
                    this.updateCustomersScene.getPane().setUpdateCustomerErrorResponse("País No Válido");
                }
                return;
            }
           
            if(SQLupdateAddress1.equals("")){
                if (this.languageSetting == 0) {
                    this.updateCustomersScene.getPane().setUpdateCustomerErrorResponse("Address Required");
                }
                else {
                    this.updateCustomersScene.getPane().setUpdateCustomerErrorResponse("Dirección Requerida");
                }
                return;
            }
            
            if(SQLupdateAddress2.equals("")){
                if (this.languageSetting == 0) {
                    this.updateCustomersScene.getPane().setUpdateCustomerErrorResponse("Address Required");
                }
                else {
                    this.updateCustomersScene.getPane().setUpdateCustomerErrorResponse("Dirección Requerida");
                }
                return;
            }
            
            if(!(SQLupdateCustomerCity.matches("^([a-zA-Z\\u0080-\\u024F]+(?:. |-| |'))*[a-zA-Z\\u0080-\\u024F]*$")) || SQLupdateCustomerCity.equals("")) {
                if (this.languageSetting == 0) {
                    this.updateCustomersScene.getPane().setUpdateCustomerErrorResponse("City Invalid Format");
                }
                else {
                    this.updateCustomersScene.getPane().setUpdateCustomerErrorResponse("Ciudad No Válido");
                }
                return;
            }
            
            if(!(SQLupdateCustomerPostal.matches("\\d{5}(-\\d{4})?"))){
                if (this.languageSetting == 0) {
                    this.updateCustomersScene.getPane().setUpdateCustomerErrorResponse("Postal Code Invalid Format");
                }
                else {
                    this.updateCustomersScene.getPane().setUpdateCustomerErrorResponse("Código Postal No Válido");
                }
                return;
            }
            
            //reset connection
            this.myConnector.setConnection();
            
            
            //add customer and other necessary table entries
            String updateCustomerResponse;


            updateCustomerResponse = this.myConnector.updateCustomer(SQLupdateCustomerName,
                                                                    SQLupdatePhone,
                                                                    SQLupdateCountry,
                                                                    SQLupdateAddress1,
                                                                    SQLupdateAddress2,
                                                                    SQLupdateCustomerCity,
                                                                    SQLupdateCustomerPostal,
                                                                    this.currentUser,
                                                                    this.customerId,
                                                                    this.addressId,
                                                                    this.cityId,
                                                                    this.countryId,
                                                                    this.languageSetting);
            
            
            // set update response
            this.updateCustomersScene.getPane().setUpdateCustomerErrorResponse(updateCustomerResponse);
        });
        
        
        
        // Customer Buttons
        
        // Customer add button
        this.customerScene.getPane().getAddCustomerButton().setOnAction(event ->{
           // set scene Add Customer
           this.primary.setScene(this.addCustomersScene.getScene());
           
           if (this.languageSetting == 0){
               this.primary.setTitle("Add Customer");
           }
           else{
               this.primary.setTitle("Agregar Cliente");
           }
           
           this.customerScene.getPane().setCustomerErrorResponse("");
        });
        
        // Customer update button
        this.customerScene.getPane().getUpdateCustomerButton().setOnAction(event -> {
            // get selected customer from table
            ObservableList<Customer> customerSelected = this.CustomerSelectionModel.getSelectedItems();
            // check only one customer is selected
            if (customerSelected.size()==1){
                // get customer data
                for(Customer customer: customerSelected){
                    this.updateCustomersScene.getPane().setUpdateCustomerNameField(customer.getCustomerName());
                    this.updateCustomersScene.getPane().setUpdateCustomerPhoneField(customer.getPhone());
                    this.updateCustomersScene.getPane().setUpdateCustomerCountryField(customer.getCountry());
                    this.updateCustomersScene.getPane().setUpdateCustomerAddress1Field(customer.getAddress1());
                    this.updateCustomersScene.getPane().setUpdateCustomerAddress2Field(customer.getAddress2());
                    this.updateCustomersScene.getPane().setUpdateCustomerCityField(customer.getCity());
                    this.updateCustomersScene.getPane().setUpdateCustomerPostalField(customer.getPostalCode());
                    
                    this.customerId = this.myConnector.getCustomerID(customer.getCustomerName(), customer.getAddress1(), customer.getAddress2());
                    this.addressId = this.myConnector.getAddressID(this.customerId);
                    this.cityId = this.myConnector.getCityID(this.addressId);
                    this.countryId = this.myConnector.getCountryID(this.cityId);
                }
                // set scene update customer
                this.primary.setScene(updateCustomersScene.getScene());
                
                if (this.languageSetting == 0) {
                    this.primary.setTitle("Update Customer");
                }
                else {
                    this.primary.setTitle("Actualizar al Cliente");
                }
                
                this.customerScene.getPane().setCustomerErrorResponse("");
            }
            
            // no customer selected
            else{
                if (this.languageSetting == 0) {
                    this.customerScene.getPane().setCustomerErrorResponse("Please Select A Customer");
                }
                else{
                    this.customerScene.getPane().setCustomerErrorResponse("Por Favor Seleccione un Cliente");
                }
            }
        });
        
        // Delete customer button
        this.customerScene.getPane().getDeleteCustomerButton().setOnAction(event -> {
            // get selected customer
            ObservableList<Customer> customerSelected = this.CustomerSelectionModel.getSelectedItems();
            // delete (deactivate) customer
            for(Customer customer : customerSelected){
                this.customerTable.getItems().remove(customer);
                String response = this.myConnector.deactivateCustomer(this.myConnector.getCustomerID(customer.getCustomerName(), 
                                                                                                     customer.getAddress1(), 
                                                                                                     customer.getAddress2()),
                                                                      this.currentUser,
                                                                      this.languageSetting);
                this.customerScene.getPane().setCustomerErrorResponse(response);
            }  
        });
        
        // Customer back button
        this.customerScene.getPane().getCustomerBackButton().setOnAction(event -> {
            // clear customer table
            customerTable.getItems().clear();
            
            // set scene back to main
           this.primary.setScene(this.mainScene.getScene());
           if (this.languageSetting == 0) {
               this.primary.setTitle("Main Menu");
           }
           else{
               this.primary.setTitle("Menú Principal");
           }
           this.customerScene.getPane().setCustomerErrorResponse("");
        });
        
        
        
        // Login Buttons
        
        // sign in button
        this.loginScene.getPane().getSignInButton().setOnAction(event ->
        {
            // check if login is valid
            boolean accessGranted = this.myConnector.checkPassword(this.loginScene.getPane().getUserTextField(), this.loginScene.getPane().getPW());
            
            // log activity if credentials valid
            if(accessGranted){
                //log access
                try{
                    File file = new File("logs.txt");
                    FileWriter fr = new FileWriter(file, true);
                    PrintWriter pw = new PrintWriter(fr);
                    
                    pw.println(this.loginScene.getPane().getUserTextField() + " " + LocalDateTime.now());
                    
                    pw.close();
                    System.out.println("User entry logged..");
                }
                
                catch(IOException e){
                    System.out.println("Log error");
                    e.printStackTrace();
                }
                
                
                
                // reset login fields and set user data
                this.loginScene.getPane().setStatusLogin("");
                this.currentUser = this.loginScene.getPane().getUserTextField();
                this.currentUserId = this.myConnector.getUserId(currentUser);
                this.loginScene.getPane().setUserTextField("");
                this.loginScene.getPane().setPW("");
                
                //add alert if appointment within 15mins
                LocalDateTime now = LocalDateTime.now();
                ArrayList<Appointment> userAppointmentsToday = this.myConnector.getAppointmentsOnDate(this.currentUserId, now.toLocalDate());
                for(Appointment a : userAppointmentsToday){
                    if((a.getStart().minusMinutes(15).isBefore(now)) && (now.isBefore(a.getStart()))){
                        if(this.languageSetting == 0){
                            this.mainScene.getPane().setAppointmentWarning("Appointment within 15 mins");
                        }
                        
                        else{
                            this.mainScene.getPane().setAppointmentWarning("Cita en 15 minutos");
                        }
                    }
                }
                
                // set main scene and title
                this.primary.setScene(mainScene.getScene());
                if(this.languageSetting == 0){
                    this.primary.setTitle("Main Menu");
                }
                else{
                    this.primary.setTitle("Menú Principal");
                }
            }
            
            // invalid login credentials
            else{
                if(this.languageSetting == 0){
                    this.loginScene.getPane().setStatusLogin("The username and password did not match.");
                }
                
                else{
                    this.loginScene.getPane().setStatusLogin("El nombre de usuario y la contrase" + Character.toString('\u00F1') + "a no coinciden");
                }
                
                this.loginScene.getPane().setPW("");
            }
        });
        
        
//        this.loginScene.getPane().getChangeLanguageButton().setOnAction(event -> {
//            if(this.languageSetting == 0){
//                this.languageSetting = 1;
//                this.loginScene.getPane().setToSpanish();
//                this.mainScene.getPane().setToSpanish();
//                this.customerScene.getPane().setToSpanish();
//                this.addCustomersScene.getPane().setToSpanish();
//                this.updateCustomersScene.getPane().setToSpanish();
//                this.addAppointmentScene.getPane().setToSpanish();
//                this.updateAppointmentScene.getPane().setToSpanish();
//                this.reportScene.getPane().setToSpanish();
//                
//                this.primary.setTitle("Planificador Iniciar Sesión");
//                this.changeView.setText("MENSUAL");
//                this.returnButton.setText("RETORNO");
//            }
//            
//            else{
//                this.languageSetting = 0;
//                this.loginScene.getPane().setToEnglish();
//                this.mainScene.getPane().setToEnglish();
//                this.customerScene.getPane().setToEnglish();
//                this.addCustomersScene.getPane().setToEnglish();
//                this.updateCustomersScene.getPane().setToEnglish();
//                this.addAppointmentScene.getPane().setToEnglish();
//                this.updateAppointmentScene.getPane().setToEnglish();
//                this.reportScene.getPane().setToEnglish();
//                
//                this.primary.setTitle("Scheduler Login");
//                this.changeView.setText("MONTHLY");
//                this.returnButton.setText("BACK");
//            }
//            
//            this.loginScene.getPane().setStatusLogin("");
//        });
       
        
        
        // Appointments Buttons
        
        // appointments forward button
        this.appointmentsScene.getPane().getForwardButton().setOnAction(e -> {
            // if currently on monthly view
            if (this.appointmentsScene.getPane().getChangeViewStatus().equals("monthly")){
                // add 1 month to calendar date 
                this.appointmentsScene.getPane().setCalendarDatePlusMonths(1);
                this.appointmentsScene.getPane().setCalendarHeader(this.appointmentsScene.getPane().getCalendarDate());
                
                // rebuild calendar view for new month
                this.calendar.createGridMonthly(this.appointmentsScene.getPane().getCalendarDate(), 
                                                 this.primary, 
                                                 this.addAppointmentScene.getScene(), 
                                                 this.appointmentsScene.getPane().getAppointmentDate(), 
                                                 this.myConnector, 
                                                 this.addAppointmentTable,
                                                 this.currentUserId,
                                                 this.addAppointmentStartDrop,
                                                 this.addAppointmentEndDrop,
                                                 this.languageSetting);
                
                // fill monthly grid with appointments
                ArrayList<Appointment> appointments = this.myConnector.getAppointmentsTableData(this.currentUserId, this.appointmentsScene.getPane().getCalendarDate());
                for(Appointment a : appointments){
                    this.calendar.addSetAppointments(7, 
                                                     6, 
                                                     a, 
                                                     this.primary, 
                                                     this.appointmentsScene,
                                                     this.updateAppointmentScene, 
                                                     this.addAppointmentScene,
                                                     this.myConnector,
                                                     this.currentUserId,
                                                     this.updateAppointmentTable,
                                                     this.addAppointmentTable,
                                                     this.languageSetting);
                }
                
                // add calendar to the Pane
                this.appointmentsScene.getPane().setCalendarGrid(this.calendar.getCalendarGrid(), this.languageSetting);   
            }
            
            // if currently on weekly view 
            else{
                // add 1 week to calendar date
                this.appointmentsScene.getPane().setCalendarDatePlusWeeks(1);
                this.appointmentsScene.getPane().setCalendarHeader(this.appointmentsScene.getPane().getCalendarDate());
                
                // rebuild calendar view for new week
                this.calendar.createGridWeeklyDailySchedule(this.appointmentsScene.getPane().getCalendarDate(), 
                                                            this.myConnector, 
                                                            this.currentUserId,
                                                            this.primary, 
                                                            this.updateAppointmentScene,
                                                            this.addAppointmentScene,
                                                            this.updateAppointmentTable,
                                                            this.addAppointmentTable,
                                                            this.appointmentsScene.getPane().getAppointmentDate(),
                                                            this.languageSetting);
                
                // add calendar to Pane
                this.appointmentsScene.getPane().setCalendarGrid(this.calendar.getCalendarGrid(), this.languageSetting);
            }
            
        });
        
        
        // Appointment back button
        this.appointmentsScene.getPane().getBackButton().setOnAction(e -> {
            // if currently monthly view
            if (this.appointmentsScene.getPane().getChangeViewStatus().equals("monthly")) {
                // subtract 1 month from calendar date
                this.appointmentsScene.getPane().setCalendarDateMinusMonths(1);
                this.appointmentsScene.getPane().setCalendarHeader(this.appointmentsScene.getPane().getCalendarDate());
                
                // rebuild calendar view for new month
                this.calendar.createGridMonthly(this.appointmentsScene.getPane().getCalendarDate(), 
                                                this.primary, 
                                                this.addAppointmentScene.getScene(), 
                                                this.appointmentsScene.getPane().getAppointmentDate(), 
                                                this.myConnector, 
                                                this.addAppointmentTable,
                                                this.currentUserId,
                                                this.addAppointmentStartDrop,
                                                this.addAppointmentEndDrop,
                                                this.languageSetting);
                
                // fill monthly grid with appointments
                ArrayList<Appointment> appointments = this.myConnector.getAppointmentsTableData(this.currentUserId, this.appointmentsScene.getPane().getCalendarDate());
                for(Appointment a : appointments){
                    this.calendar.addSetAppointments(7, 
                                                     6, 
                                                     a, 
                                                     this.primary, 
                                                     this.appointmentsScene,
                                                     this.updateAppointmentScene, 
                                                     this.addAppointmentScene,
                                                     this.myConnector,
                                                     this.currentUserId,
                                                     this.updateAppointmentTable,
                                                     this.addAppointmentTable,
                                                     this.languageSetting);
                }
                
                // add calendar to Pane
                this.appointmentsScene.getPane().setCalendarGrid(this.calendar.getCalendarGrid(), this.languageSetting); 
            }
            
            // calendar view is currently weekly
            else{
                // subtract 1 week from calendar date
                this.appointmentsScene.getPane().setCalendarDateMinusWeeks(1);
                this.appointmentsScene.getPane().setCalendarHeader(this.appointmentsScene.getPane().getCalendarDate());
                
                // rebuild calendar view
                this.calendar.createGridWeeklyDailySchedule(this.appointmentsScene.getPane().getCalendarDate(), 
                                                            this.myConnector, 
                                                            this.currentUserId,
                                                            this.primary, 
                                                            this.updateAppointmentScene,
                                                            this.addAppointmentScene,
                                                            this.updateAppointmentTable,
                                                            this.addAppointmentTable,
                                                            this.appointmentsScene.getPane().getAppointmentDate(),
                                                            this.languageSetting);
                
                // add calendar to Pane
                this.appointmentsScene.getPane().setCalendarGrid(this.calendar.getCalendarGrid(), this.languageSetting);
            }
        });
        
        // Change calendar view between monthly and weekly
        this.appointmentsScene.getPane().getChangeViewButton().setOnAction(e ->{
            // if currently monthly
            if(this.appointmentsScene.getPane().getChangeViewStatus().equals("monthly")){
                // change status to weekly
                this.appointmentsScene.getPane().setChangeViewStatus("weekly");
                
                if (this.languageSetting == 0){
                    this.appointmentsScene.getPane().getChangeViewButton().setText("MONTH");
                }
                else{
                    this.appointmentsScene.getPane().getChangeViewButton().setText("MES");
                }
                
                this.calendar.setSwitches(7,3);
                
                //create grid weekly
                this.calendar.createGridWeeklyDailySchedule(this.appointmentsScene.getPane().getCalendarDate(), 
                                                            this.myConnector, 
                                                            this.currentUserId,
                                                            this.primary, 
                                                            this.updateAppointmentScene,
                                                            this.addAppointmentScene,
                                                            this.updateAppointmentTable,
                                                            this.addAppointmentTable,
                                                            this.appointmentsScene.getPane().getAppointmentDate(),
                                                            this.languageSetting);
                
                // add new calendar to Pane
                this.appointmentsScene.getPane().setCalendarGrid(this.calendar.getCalendarGrid(), this.languageSetting);
            }
            
            // if currently weekly
            else{
                // change status to monthly
                this.appointmentsScene.getPane().setChangeViewStatus("monthly");
                
                if (this.languageSetting == 0){
                    this.appointmentsScene.getPane().getChangeViewButton().setText("WEEK");
                }
                else{
                    this.appointmentsScene.getPane().getChangeViewButton().setText("SEMANA");
                }
                
                this.calendar.setSwitches(7,6);
                
                // create grid monthly
                this.calendar.createGridMonthly(this.appointmentsScene.getPane().getCalendarDate(), 
                                                this.primary, 
                                                this.addAppointmentScene.getScene(), 
                                                this.appointmentsScene.getPane().getAppointmentDate(), 
                                                this.myConnector, 
                                                this.addAppointmentTable,
                                                this.currentUserId,
                                                this.addAppointmentStartDrop,
                                                this.addAppointmentEndDrop,
                                                this.languageSetting);
                
                // add monthly appointments
                ArrayList<Appointment> appointments = this.myConnector.getAppointmentsTableData(this.currentUserId, this.appointmentsScene.getPane().getCalendarDate());
                for(Appointment a : appointments){
                    this.calendar.addSetAppointments(7, 
                                                     6, 
                                                     a, 
                                                     this.primary, 
                                                     this.appointmentsScene,
                                                     this.updateAppointmentScene, 
                                                     this.addAppointmentScene,
                                                     this.myConnector,
                                                     this.currentUserId,
                                                     this.updateAppointmentTable,
                                                     this.addAppointmentTable,
                                                     this.languageSetting);
                }
                
                // add new calendar to Pane
                this.appointmentsScene.getPane().setCalendarGrid(this.calendar.getCalendarGrid(), this.languageSetting);
            }
        });
        
        // Back/Return button
        this.appointmentsScene.getPane().getReturnButton().setOnAction(e ->{
            // set scene and title to Main
            this.primary.setScene(this.mainScene.getScene());
            
            if (this.languageSetting == 0) {
                this.primary.setTitle("Main Menu");   
            }
            else {
                this.primary.setTitle("Menú Principal");
            }
             
        });
        
        
        
        // Add Appointment Buttons
        
        // Add Appointment cancel button
        this.addAppointmentScene.getPane().getAddAppointmentCancelButton().setOnAction(event ->{
            // return to Appointments scene
            this.primary.setScene(appointmentsScene.getScene());
            if (this.languageSetting == 0) {
                this.primary.setTitle("Appointments");
            }
            else {
                this.primary.setTitle("Citas");
            }
            
            // clear add appointment table
            this.addAppointmentTable.getItems().clear();
            
            // if current view status is monthly
            if(this.appointmentsScene.getPane().getChangeViewStatus().equals("monthly")){
                
                // rebuild monthly calendar
                this.calendar.createGridMonthly(this.appointmentsScene.getPane().getCalendarDate(), 
                                                 this.primary, 
                                                 this.addAppointmentScene.getScene(), 
                                                 this.appointmentsScene.getPane().getAppointmentDate(), 
                                                 this.myConnector, 
                                                 this.addAppointmentTable,
                                                 this.currentUserId,
                                                 this.addAppointmentStartDrop,
                                                 this.addAppointmentEndDrop,
                                                 this.languageSetting);
                
                // add appointments to table
                ArrayList<Appointment> appointments = this.myConnector.getAppointmentsTableData(this.currentUserId, this.appointmentsScene.getPane().getCalendarDate());
                for(Appointment a : appointments){          
                    this.calendar.addSetAppointments(7, 
                                                     6, 
                                                     a, 
                                                     this.primary, 
                                                     this.appointmentsScene,
                                                     this.updateAppointmentScene, 
                                                     this.addAppointmentScene,
                                                     this.myConnector,
                                                     this.currentUserId,
                                                     this.updateAppointmentTable,
                                                     this.addAppointmentTable,
                                                     this.languageSetting);
                }
            }
            
            // if current view is weekly
            else{
                // rebuild weekly calendar
                this.calendar.createGridWeeklyDailySchedule(this.appointmentsScene.getPane().getCalendarDate(), 
                                                            this.myConnector, 
                                                            this.currentUserId,
                                                            this.primary, 
                                                            this.updateAppointmentScene,
                                                            this.addAppointmentScene,
                                                            this.updateAppointmentTable,
                                                            this.addAppointmentTable,
                                                            this.appointmentsScene.getPane().getAppointmentDate(),
                                                            this.languageSetting);
            }
            
            // add new calendar to Pane
            this.appointmentsScene.getPane().setCalendarGrid(this.calendar.getCalendarGrid(), this.languageSetting);
            
            // clear add appointment fields
            this.addAppointmentScene.getPane().setAddAppointmentCustomerNameField("");
            this.addAppointmentScene.getPane().setAddAppointmentTitleField("");
            this.addAppointmentScene.getPane().setAddAppointmentDescriptionField("");
            this.addAppointmentScene.getPane().setAddAppointmentLocationField("");
            this.addAppointmentScene.getPane().setAddAppointmentContactField("");
            this.addAppointmentScene.getPane().setAddAppointmentTypeField("");
            this.addAppointmentScene.getPane().setAddAppointmentUrlField("");
            this.addAppointmentScene.getPane().clearAddAppointmentStartDrop();
            this.addAppointmentScene.getPane().clearAddAppointmentEndDrop();
            this.addAppointmentScene.getPane().setAddAppointmentErrorResponse("");
            this.addAppointmentScene.getPane().setAddAppointmentDate("");
            this.addAppointmentScene.getPane().clearAddAppointmentTable();
        });
        
        // Add Appointment add button
        this.addAppointmentScene.getPane().getAddAppointmentAddButton().setOnAction(event ->{
            // get add appointment fields
            String SQLaddAppointmentCustomerName = this.addAppointmentScene.getPane().getAddAppointmentCustomerNameField();
            String SQLaddAppointmentTitle = this.addAppointmentScene.getPane().getAddAppointmentTitleField();
            String SQLaddAppointmentDescription = this.addAppointmentScene.getPane().getAddAppointmentDescriptionField();
            String SQLaddAppointmentLocation = this.addAppointmentScene.getPane().getAddAppointmentLocationField();
            String SQLaddAppointmentContact = this.addAppointmentScene.getPane().getAddAppointmentContactField();
            String SQLaddAppointmentType = this.addAppointmentScene.getPane().getAddAppointmentTypeField();
            String SQLaddAppointmentUrl = this.addAppointmentScene.getPane().getAddAppointmentUrlField();
            String SQLaddAppointmentStart = this.addAppointmentScene.getPane().getAddAppointmentStartDropValue();
            String SQLaddAppointmentEnd = this.addAppointmentScene.getPane().getAddAppointmentEndDropValue();

            // validation checks
            String appointmentDateString = this.addAppointmentScene.getPane().getAddAppointmentDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/d/yyyy");
            LocalDate appointmentDate = LocalDate.parse(appointmentDateString, formatter);
            
            if (appointmentDate.compareTo(LocalDate.now()) < 0){
                if (this.languageSetting == 0){
                    this.addAppointmentScene.getPane().setAddAppointmentErrorResponse("Appointment date past");
                }
                else{
                    this.addAppointmentScene.getPane().setAddAppointmentErrorResponse("Fecha de cita pasada");
                }
                return;
            }
            
            if(!(SQLaddAppointmentCustomerName.matches("^[\\p{L} .'-]+$"))){
                if (this.languageSetting == 0){
                    this.addAppointmentScene.getPane().setAddAppointmentErrorResponse("Customer Name Invalid Format");
                }
                else{
                    this.addAppointmentScene.getPane().setAddAppointmentErrorResponse("Nombre de Cliente No Válido");
                }
                return;
            }
            
            if(SQLaddAppointmentTitle.equals("")){
                if (this.languageSetting == 0){
                    this.addAppointmentScene.getPane().setAddAppointmentErrorResponse("Title Required");
                }
                else {
                    this.addAppointmentScene.getPane().setAddAppointmentErrorResponse("Título Requerido");
                }
                return;
            }
            
            if(SQLaddAppointmentDescription.equals("")){
                if (this.languageSetting == 0) {
                    this.addAppointmentScene.getPane().setAddAppointmentErrorResponse("Description Required");
                }
                else{
                    this.addAppointmentScene.getPane().setAddAppointmentErrorResponse("Descripción Requerida");
                }
                return;
            }
            
            if(SQLaddAppointmentLocation.equals("")){
                if (this.languageSetting == 0) {
                    this.addAppointmentScene.getPane().setAddAppointmentErrorResponse("Location Required");
                }
                else {
                    this.addAppointmentScene.getPane().setAddAppointmentErrorResponse("Ubicación Requerida");
                }
                return;
            }
            
            if(SQLaddAppointmentContact.equals("")){
                if (this.languageSetting == 0) {
                    this.addAppointmentScene.getPane().setAddAppointmentErrorResponse("Contact Required");
                }
                else{
                    this.addAppointmentScene.getPane().setAddAppointmentErrorResponse("Contacto Requerido");
                }
                return;
            }
            
            if(SQLaddAppointmentType.equals("")){
                if (this.languageSetting == 0) {
                    this.addAppointmentScene.getPane().setAddAppointmentErrorResponse("Type Required");
                }
                else{
                    this.addAppointmentScene.getPane().setAddAppointmentErrorResponse("Tipo Requerido");
                }
                return;
            }
            
            if(!(SQLaddAppointmentUrl.matches("((http?|https|ftp|file)://)?((W|w){3}.)?[a-zA-Z0-9]+\\.[a-zA-Z]+"))){
                if (this.languageSetting == 0) {
                    this.addAppointmentScene.getPane().setAddAppointmentErrorResponse("Url Invalid Format");
                }
                else {
                    this.addAppointmentScene.getPane().setAddAppointmentErrorResponse("Url No Válido");
                }
                return;
            }
            
            if(SQLaddAppointmentStart == null){
                if (this.languageSetting == 0) {
                    this.addAppointmentScene.getPane().setAddAppointmentErrorResponse("Start Time Required");
                }
                else {
                    this.addAppointmentScene.getPane().setAddAppointmentErrorResponse("Hora de Inicio Requerida");
                }
                return;
            }
            
            if(SQLaddAppointmentEnd == null){
                if (this.languageSetting == 0) {
                    this.addAppointmentScene.getPane().setAddAppointmentErrorResponse("End Time Required");
                }
                else{
                    this.addAppointmentScene.getPane().setAddAppointmentErrorResponse("Hora de Finalización Requerida");
                }
                return;
            }
            
            
            // check for time overlaps
            String startTime[] = addAppointmentScene.getPane().getAddAppointmentStartDropValue().split(":");
            String endTime[] = addAppointmentScene.getPane().getAddAppointmentEndDropValue().split(":");
            int hour = Integer.parseInt(startTime[0]);
            int min = Integer.parseInt(startTime[1]);
            int endHour = Integer.parseInt(endTime[0]);
            int endMin = Integer.parseInt(endTime[1]);
            ObservableList<String> times = addAppointmentScene.getPane().getAddAppointmentStartDropBox().getItems();
            
            
            time_check:while(!(hour == endHour && min == endMin)){
                for(String time : times){
                    String temp[] = time.split(":");
                    int tmpHour = Integer.parseInt(temp[0]);
                    int tmpMin = Integer.parseInt(temp[1]);
                    if(tmpHour == hour && tmpMin == min){
                        min += 15;
                        if(min ==60){
                            min = 0;
                            hour ++;
                        }
                        continue time_check;
                    }
                }
                
                if (this.languageSetting == 0) {
                    this.addAppointmentScene.getPane().setAddAppointmentErrorResponse("Time Overlaps Another Appointment");
                }
                else{
                    this.addAppointmentScene.getPane().setAddAppointmentErrorResponse("El Tiempo se Superpone a Otra Cita");
                }
                
                return;
            }   
            
            
            //reset connection
            this.myConnector.setConnection();
            
            String addAppointmentResponse;
            
            // add appointment
            addAppointmentResponse = this.myConnector.addAppointment(this.customerId,
                                                                     SQLaddAppointmentTitle,
                                                                     SQLaddAppointmentDescription,
                                                                     SQLaddAppointmentLocation,
                                                                     SQLaddAppointmentContact,
                                                                     SQLaddAppointmentType,
                                                                     SQLaddAppointmentUrl,
                                                                     this.appointmentsScene.getPane().getAppointmentDate().getText(),
                                                                     SQLaddAppointmentStart,
                                                                     SQLaddAppointmentEnd,
                                                                     this.currentUserId,
                                                                     this.currentUser,
                                                                     this.languageSetting);
            
            // show add appointment response
            this.addAppointmentScene.getPane().setAddAppointmentErrorResponse(addAppointmentResponse);
            
        });
        
        // add customer to appointment
        this.addAppointmentScene.getPane().getAddCustomerToAppointment().setOnAction(e->{
            // moves customer selction from table to appointment customer
            ObservableList<Customer> customerSelectedAppointment = this.addAppointmentCustomerSelectionModel.getSelectedItems();
            if (customerSelectedAppointment.size()==1){
                 for(Customer customer: customerSelectedAppointment){
                    this.addAppointmentScene.getPane().setAddAppointmentCustomerNameField(customer.getCustomerName());
                    this.customerId = customer.getCustomerId();
                }
            }
            
            else{
                if (this.languageSetting == 0) {
                    this.addAppointmentScene.getPane().setAddAppointmentErrorResponse("No Customer Selected");
                }
                else{
                    this.addAppointmentScene.getPane().setAddAppointmentErrorResponse("Ningún Cliente Seleccionado");
                }
            }
        });
        
        // Add Apointment Start Drop Box Listener
        this.addAppointmentScene.getPane().getAddAppointmentStartDropBox().valueProperty().addListener(new ChangeListener<String>(){
            // check if value of add start box changes
            @Override public void changed(ObservableValue ov, String t, String time) {
                // if start and end box has values
                // make sure times are valid
                if((addAppointmentScene.getPane().getAddAppointmentEndDropValue()!= null) && (addAppointmentScene.getPane().getAddAppointmentStartDropValue()!=null)){
                    // get combo box times
                    String timeStartArray[] = time.split(":");
                    String endDropValue = addAppointmentScene.getPane().getAddAppointmentEndDropValue();
                    String timeEndArray[];
                    
                    // get end drop value
                    timeEndArray = addAppointmentScene.getPane().getAddAppointmentEndDropValue().split(":");
                    
                    // set start and end values
                    int hourStart = Integer.parseInt(timeStartArray[0]);
                    int minStart = Integer.parseInt(timeStartArray[1]);
                    int hourEnd = Integer.parseInt(timeEndArray[0]);
                    int minEnd = Integer.parseInt(timeEndArray[1]);

                    // set end to next open time if end < start
                    if((hourStart > hourEnd) || ((hourStart == hourEnd) && (minStart >= minEnd))){
                        ObservableList<String> endTimes = addAppointmentScene.getPane().getAddAppointmentEndDropBox().getItems();
                        String endSetValue;
                        int setHourEnd=24;
                        int setMinEnd=24;
                        
                        // search end times
                        for(String endTime : endTimes){        

                            // check if endTime > startTime
                            String checkTime[] = endTime.split(":");
                            int checkHourEnd = Integer.parseInt(checkTime[0]);
                            int checkMinEnd = Integer.parseInt(checkTime[1]);
                            
                            // check if start time is valid
                            if((hourStart < checkHourEnd) || ((hourStart == checkHourEnd) && (minStart < checkMinEnd))){
                                if((checkHourEnd < setHourEnd) || ((checkHourEnd == setHourEnd) && (checkMinEnd < setMinEnd))){
                                    
                                    // check for time overlaps
                                    String startTimes[] = addAppointmentScene.getPane().getAddAppointmentStartDropValue().split(":");
                                    int hourIterate = hourStart;
                                    int minIterate = minStart;
                                    int endHour = checkHourEnd;
                                    int endMin = checkMinEnd;

                                    ObservableList<String> times = addAppointmentScene.getPane().getAddAppointmentStartDropBox().getItems();

                                    boolean valid_time = true;
                                    time_check:while(!(hourIterate == endHour && minIterate == endMin)){
                                        for(String itime : times){
                                            String temp[] = time.split(":");
                                            int tmpHour = Integer.parseInt(temp[0]);
                                            int tmpMin = Integer.parseInt(temp[1]);
                                            if(tmpHour == hourIterate && tmpMin == minIterate){
                                                minIterate += 15;
                                                if(minIterate ==60){
                                                    minIterate = 0;
                                                    hourIterate ++;
                                                }
                                                continue time_check;
                                            }
                                        }
                                        valid_time = false;
                                    }   
                                    
                                    if(valid_time){
                                        setHourEnd = checkHourEnd;
                                        setMinEnd = checkMinEnd; 
                                    }
                                }
                            }
                        }
                        
                        // set end box if need be
                        if(setHourEnd != 24){
                            addAppointmentScene.getPane().getAddAppointmentEndDropBox().setValue(String.format("%02d:%02d", setHourEnd, setMinEnd));
                        }
                        
                        else{
                            addAppointmentScene.getPane().getAddAppointmentEndDropBox().setValue(null);
                        }
                    }
                }
            }    
        });
        
        // Add Apointment End Drop Box Listener
        this.addAppointmentScene.getPane().getAddAppointmentEndDropBox().valueProperty().addListener(new ChangeListener<String>(){
            // check if value of add end box changes
            @Override public void changed(ObservableValue ov, String t, String time) {
                // if start box has a value
                if(addAppointmentScene.getPane().getAddAppointmentStartDropValue() !=  null){
                        // get combo box times
                        String timeEndArray[] = time.split(":");
                        String startDropValue = addAppointmentScene.getPane().getAddAppointmentStartDropValue();
                        String timeStartArray[];
                        
                        // get start drop value
                        timeStartArray = addAppointmentScene.getPane().getAddAppointmentStartDropValue().split(":");
                        
                        // set start and end values
                        int hourStart = Integer.parseInt(timeStartArray[0]);
                        int minStart = Integer.parseInt(timeStartArray[1]);
                        int hourEnd = Integer.parseInt(timeEndArray[0]);
                        int minEnd = Integer.parseInt(timeEndArray[1]);

                        // set next open time if start > end
                        if((hourStart > hourEnd) || ((hourStart == hourEnd) && (minStart >= minEnd))){
                            addAppointmentScene.getPane().getAddAppointmentStartDropBox().setValue(null);
                        }
                    }
                }    
            });
        
        
        // Update Appointment Buttons
        
        // Update Appointment cancel button
        this.updateAppointmentScene.getPane().getUpdateAppointmentCancelButton().setOnAction(event ->{
            // set scene back to appointments
            this.primary.setScene(this.appointmentsScene.getScene());
            if (this.languageSetting == 0) {
                this.primary.setTitle("Appointments");
            }
            else{
                this.primary.setTitle("Citas");
            }
            
            // clear update appointment table
            this.updateAppointmentTable.getItems().clear();
            
            //  if calendar view status currently is monthly
            if(this.appointmentsScene.getPane().getChangeViewStatus().equals("monthly")){
                // rebuild calendar
                this.calendar.createGridMonthly(this.appointmentsScene.getPane().getCalendarDate(), 
                                                 this.primary, 
                                                 this.addAppointmentScene.getScene(), 
                                                 this.appointmentsScene.getPane().getAppointmentDate(), 
                                                 this.myConnector, 
                                                 this.addAppointmentTable,
                                                 this.currentUserId,
                                                 this.addAppointmentStartDrop,
                                                 this.addAppointmentEndDrop,
                                                 this.languageSetting);
                
                // add calendar to Pane
                this.appointmentsScene.getPane().setCalendarGrid(this.calendar.getCalendarGrid(), this.languageSetting);
                
                // add appointments to calendar
                ArrayList<Appointment> appointments = this.myConnector.getAppointmentsTableData(this.currentUserId, LocalDate.now());
                for(Appointment a : appointments){          
                    this.calendar.addSetAppointments(7, 
                                                     6, 
                                                     a, 
                                                     this.primary, 
                                                     this.appointmentsScene,
                                                     this.updateAppointmentScene, 
                                                     this.addAppointmentScene,
                                                     this.myConnector,
                                                     this.currentUserId,
                                                     this.updateAppointmentTable,
                                                     this.addAppointmentTable,
                                                     this.languageSetting);
                }
            }
            
            // calendar view is currently weekly
            else{
                this.calendar.createGridWeeklyDailySchedule(this.appointmentsScene.getPane().getCalendarDate(), 
                                                            this.myConnector, 
                                                            this.currentUserId,
                                                            this.primary, 
                                                            this.updateAppointmentScene,
                                                            this.addAppointmentScene,
                                                            this.updateAppointmentTable,
                                                            this.addAppointmentTable,
                                                            this.appointmentsScene.getPane().getAppointmentDate(),
                                                            this.languageSetting);
                
                // add calendar to Pane
                this.appointmentsScene.getPane().setCalendarGrid(this.calendar.getCalendarGrid(), this.languageSetting);
            }
            
            // clear Update Appointment fields
            this.updateAppointmentScene.getPane().setUpdateAppointmentID("");
            this.updateAppointmentScene.getPane().setUpdateAppointmentCustomerID("");
            this.updateAppointmentScene.getPane().setUpdateAppointmentCustomerNameField("");
            this.updateAppointmentScene.getPane().setUpdateAppointmentTitleField("");
            this.updateAppointmentScene.getPane().setUpdateAppointmentDescriptionField("");
            this.updateAppointmentScene.getPane().setUpdateAppointmentLocationField("");
            this.updateAppointmentScene.getPane().setUpdateAppointmentContactField("");
            this.updateAppointmentScene.getPane().setUpdateAppointmentTypeField("");
            this.updateAppointmentScene.getPane().setUpdateAppointmentUrlField("");
            this.updateAppointmentScene.getPane().clearUpdateAppointmentStartDrop();
            this.updateAppointmentScene.getPane().clearUpdateAppointmentEndDrop();
            this.updateAppointmentScene.getPane().setUpdateAppointmentErrorResponse("");
            this.updateAppointmentScene.getPane().setUpdateAppointmentDate("");
            this.updateAppointmentScene.getPane().clearUpdateAppointmentTable();
        });
        
        // Update Appointment add button
        this.updateAppointmentScene.getPane().getUpdateAppointmentAddButton().setOnAction(event ->{
            
            // get field values
            String SQLupdateAppointmentID = this.updateAppointmentScene.getPane().getUpdateAppointmentID();
            String SQLupdateAppointmentCustomerName = this.updateAppointmentScene.getPane().getUpdateAppointmentCustomerNameField();
            String SQLupdateAppointmentTitle = this.updateAppointmentScene.getPane().getUpdateAppointmentTitleField();
            String SQLupdateAppointmentDescription = this.updateAppointmentScene.getPane().getUpdateAppointmentDescriptionField();
            String SQLupdateAppointmentLocation = this.updateAppointmentScene.getPane().getUpdateAppointmentLocationField();
            String SQLupdateAppointmentContact = this.updateAppointmentScene.getPane().getUpdateAppointmentContactField();
            String SQLupdateAppointmentType = this.updateAppointmentScene.getPane().getUpdateAppointmentTypeField();
            String SQLupdateAppointmentUrl = this.updateAppointmentScene.getPane().getUpdateAppointmentUrlField();
            String SQLupdateAppointmentStart = this.updateAppointmentScene.getPane().getUpdateAppointmentStartDropValue();
            String SQLupdateAppointmentEnd = this.updateAppointmentScene.getPane().getUpdateAppointmentEndDropValue();

            
            // validation checks
            String appointmentDateString = this.updateAppointmentScene.getPane().getUpdateAppointmentDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/d/yyyy");
            LocalDate appointmentDate = LocalDate.parse(appointmentDateString, formatter);
            
            if (appointmentDate.compareTo(LocalDate.now()) < 0){
                if (this.languageSetting == 0){
                    this.updateAppointmentScene.getPane().setUpdateAppointmentErrorResponse("Appointment date past");
                }
                else{
                    this.updateAppointmentScene.getPane().setUpdateAppointmentErrorResponse("Fecha de cita pasada");
                }
                return;
            }
            
            if(SQLupdateAppointmentID.equals("")){
                if (this.languageSetting == 0) {
                    this.updateAppointmentScene.getPane().setUpdateAppointmentErrorResponse("Bad Appointment ID");
                }
                else {
                    this.updateAppointmentScene.getPane().setUpdateAppointmentErrorResponse("Identificación de Cita Incorrecta");
                }
                return;
            }
            
            if(!(SQLupdateAppointmentCustomerName.matches("^[\\p{L} .'-]+$"))){
                if (this.languageSetting == 0) {
                    this.updateAppointmentScene.getPane().setUpdateAppointmentErrorResponse("Customer Name Invalid Format");
                }
                else {
                    this.updateAppointmentScene.getPane().setUpdateAppointmentErrorResponse("Nombre del Cliente Inválido");
                }
                return;
            }
            
            if(SQLupdateAppointmentTitle.equals("")){
                if (this.languageSetting == 0) {
                    this.updateAppointmentScene.getPane().setUpdateAppointmentErrorResponse("Title Required");
                }
                else {
                    this.updateAppointmentScene.getPane().setUpdateAppointmentErrorResponse("Título Requerido");
                }
                return;
            }
            
            if(SQLupdateAppointmentDescription.equals("")){
                if (this.languageSetting == 0) {
                    this.updateAppointmentScene.getPane().setUpdateAppointmentErrorResponse("Description Required");
                }
                else {
                    this.updateAppointmentScene.getPane().setUpdateAppointmentErrorResponse("Descripción Requerida");
                }
                return;
            }
            
            if(SQLupdateAppointmentLocation.equals("")){
                if (this.languageSetting == 0) {
                    this.updateAppointmentScene.getPane().setUpdateAppointmentErrorResponse("Location Required");
                }
                else {
                    this.updateAppointmentScene.getPane().setUpdateAppointmentErrorResponse("Ubicación Requerida");
                }
                return;
            }
            
            if(SQLupdateAppointmentContact.equals("")){
                if (this.languageSetting == 0) {
                    this.updateAppointmentScene.getPane().setUpdateAppointmentErrorResponse("Contact Required");
                }
                else {
                    this.updateAppointmentScene.getPane().setUpdateAppointmentErrorResponse("Contacto Requerido");
                }
                return;
            }
            
            if(SQLupdateAppointmentType.equals("")){
                if (this.languageSetting == 0) {
                    this.updateAppointmentScene.getPane().setUpdateAppointmentErrorResponse("Type Required");
                }
                else {
                    this.updateAppointmentScene.getPane().setUpdateAppointmentErrorResponse("Tipo Requerido");
                }
                return;
            }
            
            if(!(SQLupdateAppointmentUrl.matches("((http?|https|ftp|file)://)?((W|w){3}.)?[a-zA-Z0-9]+\\.[a-zA-Z]+"))){
                if (this.languageSetting == 0) {
                    this.updateAppointmentScene.getPane().setUpdateAppointmentErrorResponse("Url Invalid Format");
                }
                else{
                    this.updateAppointmentScene.getPane().setUpdateAppointmentErrorResponse("Url Requerido");
                }
                return;
            }
            
            if(SQLupdateAppointmentStart == null){
                if (this.languageSetting == 0) {
                    this.updateAppointmentScene.getPane().setUpdateAppointmentErrorResponse("Start Time Required");
                }
                else{
                    this.updateAppointmentScene.getPane().setUpdateAppointmentErrorResponse("Hora de Inicio Requerido");
                }
                return;
            }
            
            if(SQLupdateAppointmentEnd == null){
                if (this.languageSetting == 0) {
                    this.updateAppointmentScene.getPane().setUpdateAppointmentErrorResponse("End Time Required");
                }
                else {
                    this.updateAppointmentScene.getPane().setUpdateAppointmentErrorResponse("Hora de Finalización Requerido");
                }
                return;
            }
            
            
            // get drop box time data
            String startTime[] = updateAppointmentScene.getPane().getUpdateAppointmentStartDropValue().split(":");
            String endTime[] = updateAppointmentScene.getPane().getUpdateAppointmentEndDropValue().split(":");
            int hour = Integer.parseInt(startTime[0]);
            int min = Integer.parseInt(startTime[1]);
            int endHour = Integer.parseInt(endTime[0]);
            int endMin = Integer.parseInt(endTime[1]);
            ObservableList<String> times = updateAppointmentScene.getPane().getUpdateAppointmentStartDropBox().getItems();
            
            // check for time overlaps
            time_check:while(!(hour == endHour && min == endMin)){
                for(String time : times){
                    String temp[] = time.split(":");
                    int tmpHour = Integer.parseInt(temp[0]);
                    int tmpMin = Integer.parseInt(temp[1]);
                    if(tmpHour == hour && tmpMin == min){
                        min += 15;
                        if(min ==60){
                            min = 0;
                            hour ++;
                        }
                        continue time_check;
                    }
                }
                
                if (this.languageSetting == 0) {
                    this.updateAppointmentScene.getPane().setUpdateAppointmentErrorResponse("Time Overlaps Another Appointment");
                }
                else{
                    this.updateAppointmentScene.getPane().setUpdateAppointmentErrorResponse("El Tiempo se Superpone a Otra Cita");
                }
                 
                return;
            }   
            
            //set connection
            this.myConnector.setConnection();
            
            //add customer and other necessary table entries
            String updateAppointmentResponse;
            
            // update appointment
            updateAppointmentResponse = this.myConnector.updateAppointment(SQLupdateAppointmentID,
                                                                           this.updateAppointmentScene.getPane().getUpdateAppointmentCustomerID(),
                                                                           SQLupdateAppointmentTitle,
                                                                           SQLupdateAppointmentDescription,
                                                                           SQLupdateAppointmentLocation,
                                                                           SQLupdateAppointmentContact,
                                                                           SQLupdateAppointmentType,
                                                                           SQLupdateAppointmentUrl,
                                                                           this.appointmentsScene.getPane().getAppointmentDate().getText(),
                                                                           SQLupdateAppointmentStart,
                                                                           SQLupdateAppointmentEnd,
                                                                           this.currentUserId,
                                                                           this.currentUser,
                                                                           this.languageSetting);
            
            // response from update appointment
            this.updateAppointmentScene.getPane().setUpdateAppointmentErrorResponse(updateAppointmentResponse);  
        });
        
        // Update Appointment update customer
        this.updateAppointmentScene.getPane().getUpdateCustomerToAppointmentButton().setOnAction(e->{
            // get selected customer data
            ObservableList<Customer> customerSelectedAppointment = this.updateAppointmentCustomerSelectionModel.getSelectedItems();
            // if only one selected -> update customer
            if (customerSelectedAppointment.size()==1){
                 for(Customer customer: customerSelectedAppointment){
                    this.updateAppointmentScene.getPane().setUpdateAppointmentCustomerNameField(customer.getCustomerName());
                    this.updateAppointmentScene.getPane().setUpdateAppointmentCustomerID(String.valueOf(customer.getCustomerId()));
                }
            }
            
            // more than one or no customer selected
            else{
                if (this.languageSetting == 0) {
                    this.updateAppointmentScene.getPane().setUpdateAppointmentErrorResponse("No Customer Selected");
                }
                else{
                    this.updateAppointmentScene.getPane().setUpdateAppointmentErrorResponse("Ningún Cliente Seleccionado");
                }
            }
        });
        
        
        // Update Appointment start box listener
        this.updateAppointmentScene.getPane().getUpdateAppointmentStartDropBox().valueProperty().addListener(new ChangeListener<String>(){
            // if value changes
            @Override public void changed(ObservableValue ov, String t, String time) {
                // if start drop and end drop have values
                if ((updateAppointmentScene.getPane().getUpdateAppointmentEndDropValue() != null) && 
                    (updateAppointmentScene.getPane().getUpdateAppointmentStartDropValue() != null)) {
                    
                    // get time data
                    String timeStartArray[] = time.split(":");
                    String endDropValue = updateAppointmentScene.getPane().getUpdateAppointmentEndDropValue();
                    String timeEndArray[];
                    timeEndArray = endDropValue.split(":");
                    
                    int hourStart = Integer.parseInt(timeStartArray[0]);
                    int minStart = Integer.parseInt(timeStartArray[1]);
                    int hourEnd = Integer.parseInt(timeEndArray[0]);
                    int minEnd = Integer.parseInt(timeEndArray[1]);

                    // set next open time if end < start
                    if((hourStart > hourEnd) || ((hourStart == hourEnd) && (minStart >= minEnd))){
                        ObservableList<String> endTimes = updateAppointmentScene.getPane().getUpdateAppointmentEndDropBox().getItems();
                        int setHourEnd=24;
                        int setMinEnd=24;

                        for(String endTime : endTimes){        

                            // check if endTime > startTime
                            String checkTime[] = endTime.split(":");
                            int checkHourEnd = Integer.parseInt(checkTime[0]);
                            int checkMinEnd = Integer.parseInt(checkTime[1]);
                            
                            
                            if((hourStart < checkHourEnd) || ((hourStart == checkHourEnd) && (minStart < checkMinEnd))){
                                if((checkHourEnd<setHourEnd) || ((checkHourEnd==setHourEnd) && (checkMinEnd<setMinEnd))){
                                    
                                    // check for time overlaps
                                    int hourIterate = hourStart;
                                    int minIterate = minStart;
                                    int endHour = checkHourEnd;
                                    int endMin = checkMinEnd;

                                    ObservableList<String> times = updateAppointmentScene.getPane().getUpdateAppointmentStartDropBox().getItems();

                                    boolean valid_time = true;
                                    time_check:while(!(hourIterate == endHour && minIterate == endMin)){
                                        for(String itime : times){
                                            String temp[] = time.split(":");
                                            int tmpHour = Integer.parseInt(temp[0]);
                                            int tmpMin = Integer.parseInt(temp[1]);
                                            if(tmpHour == hourIterate && tmpMin == minIterate){
                                                minIterate += 15;
                                                if(minIterate == 60){
                                                    minIterate = 0;
                                                    hourIterate ++;
                                                }
                                                continue time_check;
                                            }
                                        }
                                        valid_time = false;
                                    }   
                                    
                                    if(valid_time) {
                                        setHourEnd = checkHourEnd;
                                        setMinEnd = checkMinEnd; 
                                    }
                                }
                            }
                        }
                        
                        if(setHourEnd != 24){
                            updateAppointmentScene.getPane().getUpdateAppointmentEndDropBox().setValue(String.format("%02d:%02d", setHourEnd, setMinEnd));
                        }
                        else{
                            updateAppointmentScene.getPane().getUpdateAppointmentEndDropBox().setValue(null);
                        }
                    }
                }
            }    
        });
        
        // Update Appointment end box listener
        this.updateAppointmentScene.getPane().getUpdateAppointmentEndDropBox().valueProperty().addListener(new ChangeListener<String>(){
            // if end box value changes
            @Override public void changed(ObservableValue ov, String t, String time) {
                // if start box has a value
                if(updateAppointmentScene.getPane().getUpdateAppointmentStartDropValue()!= null){
                        // get combo box times
                        String timeEndArray[] = time.split(":");
                        String startDropValue = updateAppointmentScene.getPane().getUpdateAppointmentStartDropValue();
                        String timeStartArray[];
                        timeStartArray = startDropValue.split(":");
  
                        int hourStart = Integer.parseInt(timeStartArray[0]);
                        int minStart = Integer.parseInt(timeStartArray[1]);
                        int hourEnd = Integer.parseInt(timeEndArray[0]);
                        int minEnd = Integer.parseInt(timeEndArray[1]);

                        // set next open time if start > end
                        if((hourStart > hourEnd) || ((hourStart == hourEnd) && (minStart >= minEnd))){
                            updateAppointmentScene.getPane().getUpdateAppointmentStartDropBox().setValue(null);
                        }
                    }
                }    
            });
        
        
        // Reports
        
        // Reports cancel button
        this.reportScene.getPane().getCancelButton().setOnAction(event -> {
            // return to main menu
            this.reportScene.getPane().setReportStatus("");
            this.primary.setScene(this.mainScene.getScene());
            if (this.languageSetting == 0){
                this.primary.setTitle("Main Menu");
            }
            else{
                this.primary.setTitle("Menú Principal");
            }
        });
        
        
        // Generate Appointment Type Month Report
        this.reportScene.getPane().getAppointmentmentTypeMonthReportButton().setOnAction(event -> {
            
            // reset connection
            this.myConnector.setConnection();
            
            // get appointment type report for last 6 months
            LocalDate appointmentMonth = LocalDate.now();
            try{
                // open file and initialize writer
                File file = new File("report.txt");
                FileWriter fr = new FileWriter(file);
                PrintWriter pw = new PrintWriter(fr);
                
                // print report header
                if (this.languageSetting == 0) {
                    pw.println("Appointment Type Month Report");
                }
                else{
                    pw.println("Informe Mensual del Tipo de Cita");
                }
                pw.println("_____________________________\n\n");

                for(int i=0; i<6; i++){
                    ArrayList<ArrayList> reportResponse = this.myConnector.appointmentmentTypeMonthReport(appointmentMonth);

                    // print report
                    String month = appointmentMonth.getMonth().toString();
                    int year = appointmentMonth.getYear();
                    
                    if (this.languageSetting == 0){
                        pw.println(month + " " + year);
                    }
                    else{
                        if (month.equals("JANUARY")){
                            month = "ENERO";
                        }
                        else if(month.equals("FEBRUARY")){
                            month = "FEBRERO";
                        }
                        else if(month.equals("MARCH")){
                            month = "MARZO";
                        }
                        else if(month.equals("APRIL")){
                            month = "ABRIL";
                        }
                        else if(month.equals("MAY")){
                            month = "MAYO";
                        }
                        else if(month.equals("JUNE")){
                            month = "JUNIO";
                        }
                        else if(month.equals("JULY")){
                            month = "JULIO";
                        }
                        else if(month.equals("AUGUST")){
                            month = "AGOSTO";
                        }
                        else if(month.equals("SEPTEMBER")){
                            month = "SEPTIEMBRE";
                        }
                        else if(month.equals("OCTOBER")){
                            month = "OCTUBRE";
                        }
                        else if(month.equals("NOVEMBER")){
                            month = "NOVIEMBRE";
                        }
                        else if(month.equals("DECEMBER")){
                            month = "DICIEMBRE";
                        }
                        
                        pw.println(month + " " + year);
                    }

                    pw.println("_____________________________\n");
                    
                    for(ArrayList appointmentData : reportResponse){
                        // print appointment data
                        String type = appointmentData.get(0).toString();
                        String num = appointmentData.get(1).toString();
                        int lineLength = type.length() + num.length();
                        String divider = new String(new char[25 - lineLength]).replace("\0", "-");
                        
                        pw.println(type + " " + divider + " " + num);
                    }
                    
                    // set space
                    pw.println("\n\n");
                    
                    // set a month back
                    appointmentMonth = appointmentMonth.minusMonths(1);
                }
                
                // close writer
                pw.close();
                
                if (this.languageSetting == 0) {
                    this.reportScene.getPane().setReportStatus("Report Generated");
                }
                else{
                    this.reportScene.getPane().setReportStatus("Informe Generado");
                }
            }
            
            catch(IOException e){
                    System.out.println("report print error");
                    e.printStackTrace();
            }
        });
        
        // Generate Consultant Schedule Report
        this.reportScene.getPane().getConsultantScheduleReportButton().setOnAction(event -> {
            
            // reset connection
            this.myConnector.setConnection();

            try{
                // open file and initialize writer
                File file = new File("report.txt");
                FileWriter fr = new FileWriter(file);
                PrintWriter pw = new PrintWriter(fr);
                
                // print report header
                if (this.languageSetting == 0) {
                    pw.println("Consultant Schedule Report");
                }
                else{
                    pw.println("Informe de Programación del Consultor");
                }
                pw.println("_____________________________\n\n");
                
                // get report data
                HashMap<String, ArrayList<ArrayList<String>>> reportResponse = this.myConnector.consultantScheduleReport();
                int columnLength = 25;
                
                // print report
                for(Map.Entry mapElement : reportResponse.entrySet()){
                    String user = (String)mapElement.getKey();
                    pw.println(user);
                    pw.println("_______________\n");
                    
                    
                    ArrayList<ArrayList<String>> appointments = (ArrayList)mapElement.getValue();
                    for(ArrayList<String> appointment : appointments){
                        pw.print(appointment.get(0) + " --- " + appointment.get(1));
                        pw.println("\n_______________\n");
                        
                        if (this.languageSetting == 0) {
                            pw.println("-------Desciption");
                        }
                        else {
                            pw.println("-------Descripción");
                        }
                        
                        pw.println("              " + appointment.get(2) + "\n");
                        
                        if (this.languageSetting == 0) {
                            pw.println("-------Location");
                        }
                        else {
                            pw.println("-------Ubicación");
                        }
                        pw.println("              " + appointment.get(3) + "\n");
                        
                        if (this.languageSetting == 0) {
                            pw.println("-------Contact");
                        }
                        else{
                            pw.println("-------Contacto");
                        }
                        
                        pw.println("              " + appointment.get(4) + "\n");
                        
                        pw.println("-------Url"
                                + "              " + appointment.get(5) + "\n");
                        
                        if (this.languageSetting == 0) {
                            pw.println("-------Start");
                        }
                        else{
                            pw.println("-------Comienzo");
                        }
                        
                        pw.println("              " + appointment.get(6) + "\n");
                        
                        if (this.languageSetting == 0) {
                            pw.println("-------End");
                        }
                        else{
                            pw.println("-------Fin");
                        }
                        
                        pw.println("              " + appointment.get(7) + "\n");
                        
                        pw.println("\n");
                    }
                }
                
                // close writer
                pw.close();
                
                if (this.languageSetting == 0) {
                    this.reportScene.getPane().setReportStatus("Report Generated");
                }
                else {
                    this.reportScene.getPane().setReportStatus("Informe Generado");
                }
            }
            
            catch(IOException e){
                    System.out.println("report print error");
                    e.printStackTrace();
            }
        });
        
        
        // Generate Customer Report
        this.reportScene.getPane().getCustomerReportButton().setOnAction(event -> {
                
            // reset connection
            this.myConnector.setConnection();

            try{
                // open file and initialize writer
                File file = new File("report.txt");
                FileWriter fr = new FileWriter(file);
                PrintWriter pw = new PrintWriter(fr);
                
                // print report header
                if (this.languageSetting == 0) {
                    pw.println("Customer Appointment Report");
                }
                else {
                    pw.println("Informe de Cita del Cliente");
                }
                pw.println("_____________________________\n\n");
                
                LocalDate appointmentMonth = LocalDate.now();

                for(int i=0; i<6; i++){
                    // get report data
                    ArrayList<ArrayList<String>> reportResponse = this.myConnector.customerReport(appointmentMonth);

                    // print report
                    String month = appointmentMonth.getMonth().toString();
                    int year = appointmentMonth.getYear();
                    
                    if (this.languageSetting == 0){
                        pw.println(month + " " + year);
                    }
                    else{
                        if (month.equals("JANUARY")){
                            month = "ENERO";
                        }
                        else if(month.equals("FEBRUARY")){
                            month = "FEBRERO";
                        }
                        else if(month.equals("MARCH")){
                            month = "MARZO";
                        }
                        else if(month.equals("APRIL")){
                            month = "ABRIL";
                        }
                        else if(month.equals("MAY")){
                            month = "MAYO";
                        }
                        else if(month.equals("JUNE")){
                            month = "JUNIO";
                        }
                        else if(month.equals("JULY")){
                            month = "JULIO";
                        }
                        else if(month.equals("AUGUST")){
                            month = "AGOSTO";
                        }
                        else if(month.equals("SEPTEMBER")){
                            month = "SEPTIEMBRE";
                        }
                        else if(month.equals("OCTOBER")){
                            month = "OCTUBRE";
                        }
                        else if(month.equals("NOVEMBER")){
                            month = "NOVIEMBRE";
                        }
                        else if(month.equals("DECEMBER")){
                            month = "DICIEMBRE";
                        }
                        
                        pw.println(month + " " + year);
                    }

                    pw.println("_____________________________\n");
                    
                    // print appointment data
                    for(ArrayList appointmentData : reportResponse){
                        // type, number
                        String customerName = appointmentData.get(0).toString();
                        String num = appointmentData.get(1).toString();
                        int lineLength = customerName.length() + num.length();
                        String divider = new String(new char[25 - lineLength]).replace("\0", "-");
                        
                        pw.println(customerName + " " + divider + " " + num);
                    }
                    
                    // space appointments
                    pw.println("\n\n");
                    
                    // iterate a month back
                    appointmentMonth = appointmentMonth.minusMonths(1);
                }
                
                // close writer
                pw.close();
                
                if (this.languageSetting == 0) {
                    this.reportScene.getPane().setReportStatus("Report Generated");
                }
                else {
                    this.reportScene.getPane().setReportStatus("Informe Generado");
                }
            }
            
            catch(IOException e){
                    System.out.println("report print error");
                    e.printStackTrace();
            }
        });
        
        
        // Main
        
        // Main customers button
        this.mainScene.getPane().getCustomerButton().setOnAction(event -> {
           // add customer data for customer table
           ArrayList<Customer> customers = this.myConnector.getCustomersTableData();
           this.customerTable.getItems().addAll(customers);
            
           // set to customer scene
           this.primary.setScene(this.customerScene.getScene());
           if (this.languageSetting == 0) {
               this.primary.setTitle("Customers");
           }
           else{
               this.primary.setTitle("Clientes");
           }
        });
        
        // Main appointment button
        this.mainScene.getPane().getAppointmentButton().setOnAction(event -> {
            // current calendar view status is monthly
            if(this.appointmentsScene.getPane().getChangeViewStatus().equals("monthly")){
                // build calendar grid
                this.calendar.createGridMonthly(this.appointmentsScene.getPane().getCalendarDate(), 
                                                 this.primary, 
                                                 this.addAppointmentScene.getScene(), 
                                                 this.appointmentsScene.getPane().getAppointmentDate(), 
                                                 this.myConnector, 
                                                 this.addAppointmentTable,
                                                 this.currentUserId,
                                                 this.addAppointmentStartDrop,
                                                 this.addAppointmentEndDrop,
                                                 this.languageSetting);
                // add calendar to Pane
                this.appointmentsScene.getPane().setCalendarGrid(this.calendar.getCalendarGrid(), this.languageSetting);
                
                // add appointment data to the calendar
                ArrayList<Appointment> appointments = this.myConnector.getAppointmentsTableData(this.currentUserId, LocalDate.now());
                for(Appointment a : appointments){          
                    this.calendar.addSetAppointments(7, 
                                                     6, 
                                                     a, 
                                                     this.primary, 
                                                     this.appointmentsScene,
                                                     this.updateAppointmentScene, 
                                                     this.addAppointmentScene,
                                                     this.myConnector,
                                                     this.currentUserId,
                                                     this.updateAppointmentTable,
                                                     this.addAppointmentTable,
                                                     this.languageSetting);
                }
            }
            
            // current calendar view status is weekly
            else{
                // build calendar
                this.calendar.createGridWeeklyDailySchedule(this.appointmentsScene.getPane().getCalendarDate(), 
                                                            this.myConnector, 
                                                            this.currentUserId,
                                                            this.primary, 
                                                            this.updateAppointmentScene,
                                                            this.addAppointmentScene,
                                                            this.updateAppointmentTable,
                                                            this.addAppointmentTable,
                                                            this.appointmentsScene.getPane().getAppointmentDate(),
                                                            this.languageSetting);
                
                // add calendar to Pane
                this.appointmentsScene.getPane().setCalendarGrid(this.calendar.getCalendarGrid(), this.languageSetting);
            }
            
            // set scene to Appointments
            this.primary.setScene(this.appointmentsScene.getScene());
            if (this.languageSetting == 0) {
                this.primary.setTitle("Appointments"); 
            }
            else{
                this.primary.setTitle("Citas");
            }
         
        });
        
        // Main report button
        this.mainScene.getPane().getReportButton().setOnAction(event -> {
            // set scene to reports
            this.primary.setScene(reportScene.getScene());
            if (this.languageSetting == 0) {
                this.primary.setTitle("Report Scene");
            }
            else {
                this.primary.setTitle("Reportar Escena");
            }
        });
        
        // Main sign out button
        this.mainScene.getPane().getSignOutButton().setOnAction(event -> {
            // sign out current user and return to the login scene
            this.currentUser = "";
            this.currentUserId = -1;
            this.primary.setScene(loginScene.getScene());
            if (this.languageSetting == 0) {
                this.primary.setTitle("Scheduler Login");
            }
            else {
                this.primary.setTitle("Inicio de Sesión del Planificador");
            }
        });
    }
    
}