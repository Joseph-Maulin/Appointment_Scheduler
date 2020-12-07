
package Panes;

import Objects.Customer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Add_Appointment_Pane {
    // Pane
    private BorderPane Pane;
    private GridPane FieldPane;
    
    // ComboBox
    private ComboBox<String> addAppointmentStartDrop;
    private ComboBox<String> addAppointmentEndDrop;
    
    // Table
    private TableView table;
    private TableColumn<String, Customer> addCustomerNameAppointment;
    private TableColumn<String, Customer> addAddressAppointment;
    private TableColumn<String, Customer> addCustomerPhoneAppointment;
    
    // Text
    private Text addAppointmentTitleHeader;
    
    // Label
    private Label addAppointmentCustomerName;
    private Label addAppointmentTitle;
    private Label addAppointmentDescription;
    private Label addAppointmentLocation;
    private Label addAppointmentContact;
    private Label addAppointmentType;
    private Label addAppointmentUrl;
    private Label addAppointmentStart;
    private Label addAppointmentEnd;
    private Label addAppointmentErrorResponse;
    private Label addAppointmentDate;
    
    // TextField
    private TextField addAppointmentCustomerNameField;
    private TextField addAppointmentTitleField;
    private TextField addAppointmentDescriptionField;
    private TextField addAppointmentLocationField;
    private TextField addAppointmentContactField;
    private TextField addAppointmentTypeField;
    private TextField addAppointmentUrlField;
    
    // Button
    private Button addAppointmentCancelButton;
    private Button addAppointmentAddButton;
    private Button addCustomerToAppointment;
    
    // VBox
    private VBox addAppointmentCustomerBox;
    
    
    public Add_Appointment_Pane(TableView addAppointmentTable, ComboBox<String> startBox, ComboBox<String> endBox){
        this.Pane = this.setPane();
        this.FieldPane = this.setFieldPane();
        
        // Combo Box
        this.addAppointmentStartDrop = startBox;
        this.addAppointmentEndDrop = endBox;
        
        // Table
        this.table = addAppointmentTable;
        
        // Table Columns
        this.addCustomerNameAppointment = new TableColumn<String, Customer>("Customer Name");
        this.addCustomerNameAppointment.setCellValueFactory(new PropertyValueFactory<String, Customer>("customerName"));
        this.addAddressAppointment = new TableColumn<String, Customer>("Address");
        this.addAddressAppointment.setCellValueFactory(new PropertyValueFactory<String, Customer>("fullAddress"));
        this.addCustomerPhoneAppointment = new TableColumn<String, Customer>("Phone#");
        this.addCustomerPhoneAppointment.setCellValueFactory(new PropertyValueFactory<String, Customer>("phone"));
        
        this.table.getColumns().addAll(this.addCustomerNameAppointment, this.addAddressAppointment, this.addCustomerPhoneAppointment);
        this.table.setPlaceholder(new Label("No rows to display"));
        
        // Text
        this.addAppointmentTitleHeader = new Text("Add Appointment");
        this.addAppointmentTitleHeader.setX(50);
        this.addAppointmentTitleHeader.setY(50);
        this.addAppointmentTitleHeader.setFont(Font.font("verdana", FontWeight.BOLD, 20));
        
        // Label
        this.addAppointmentCustomerName = new Label("Name: ");
        this.addAppointmentTitle = new Label("Title: ");
        this.addAppointmentDescription = new Label("Description: ");
        this.addAppointmentLocation = new Label("Location: ");
        this.addAppointmentContact = new Label("Contact: ");
        this.addAppointmentType = new Label("Type: ");
        this.addAppointmentUrl = new Label("Url: ");
        this.addAppointmentStart = new Label("Start: ");
        this.addAppointmentEnd = new Label("End: ");
        this.addAppointmentErrorResponse = new Label("");
        this.addAppointmentErrorResponse.setTextFill(Color.web("#ff0000", 0.8));
        this.addAppointmentDate = new Label("");
        this.addAppointmentDate.setFont(Font.font("verdana", FontWeight.BOLD, 20));
        
        // TextField
        this.addAppointmentCustomerNameField = new TextField();
        this.addAppointmentCustomerNameField.setEditable(false);
        this.addAppointmentTitleField = new TextField();
        this.addAppointmentDescriptionField = new TextField();
        this.addAppointmentLocationField = new TextField();
        this.addAppointmentContactField = new TextField();
        this.addAppointmentTypeField = new TextField();
        this.addAppointmentUrlField = new TextField();
        
        // Button
        this.addAppointmentCancelButton = new Button("Cancel");
        this.addAppointmentAddButton = new Button("Add Appointment");
        this.addCustomerToAppointment = new Button("Add Customer");
        
        // Set FieldPane
        this.FieldPane.add(addAppointmentTitleHeader, 0,0,2,1);
        this.FieldPane.add(addAppointmentDate,4,0);
        this.FieldPane.add(addAppointmentCustomerName, 0,1);
        this.FieldPane.add(addAppointmentCustomerNameField,1,1);
        this.FieldPane.add(addAppointmentTitle,0,2);
        this.FieldPane.add(addAppointmentTitleField,1,2);
        this.FieldPane.add(addAppointmentDescription,0,3);
        this.FieldPane.add(addAppointmentDescriptionField,1,3,3,1);
        this.FieldPane.add(addAppointmentLocation,0,4);
        this.FieldPane.add(addAppointmentLocationField,1,4,3,1);
        this.FieldPane.add(addAppointmentContact,0,5);
        this.FieldPane.add(addAppointmentContactField,1,5,3,1);
        this.FieldPane.add(addAppointmentType,0,6);
        this.FieldPane.add(addAppointmentTypeField,1,6);
        this.FieldPane.add(addAppointmentUrl,0,7);
        this.FieldPane.add(addAppointmentUrlField,1,7);
        this.FieldPane.add(addAppointmentStart,0,8);
        this.FieldPane.add(addAppointmentStartDrop,1,8);
        this.FieldPane.add(addAppointmentEnd,2,8);
        this.FieldPane.add(addAppointmentEndDrop,3,8);
        this.FieldPane.add(addAppointmentErrorResponse,0,9);
        this.FieldPane.add(addAppointmentAddButton,0,14);
        this.FieldPane.add(addAppointmentCancelButton,1,14);
        
        // VBox
        this.addAppointmentCustomerBox = new VBox(this.addCustomerToAppointment, this.table);
        
        // Set Pane
        this.Pane.setLeft(this.FieldPane);
        this.Pane.setRight(this.addAppointmentCustomerBox);
//        this.Pane.setRight(this.table);
    }
    
    
    // Getters
    public BorderPane getPane(){
        return this.Pane;
    } 
    
    public String getAddAppointmentDate(){
        return this.addAppointmentDate.getText();
    }
    
    public Button getAddAppointmentCancelButton(){
        return this.addAppointmentCancelButton;
    }
    
    public Button getAddAppointmentAddButton(){
        return this.addAppointmentAddButton;
    }

    public Button getAddCustomerToAppointment(){
        return this.addCustomerToAppointment;
    }
    
    public String getAddAppointmentCustomerNameField(){
        return this.addAppointmentCustomerNameField.getText();
    }
    
    public String getAddAppointmentTitleField(){
        return this.addAppointmentTitleField.getText();
    }
    
    public String getAddAppointmentDescriptionField(){
        return this.addAppointmentDescriptionField.getText();
    }
    
    public String getAddAppointmentLocationField(){
        return this.addAppointmentLocationField.getText();
    }
    
    public String getAddAppointmentContactField(){
        return this.addAppointmentContactField.getText();
    }
    
    public String getAddAppointmentTypeField(){
        return this.addAppointmentTypeField.getText();
    }
    
    public String getAddAppointmentUrlField(){
        return this.addAppointmentUrlField.getText();
    }
    
    public ComboBox getAddAppointmentStartDropBox(){
        return this.addAppointmentStartDrop;
    }
    
    public ComboBox getAddAppointmentEndDropBox(){
        return this.addAppointmentEndDrop;
    }

    public String getAddAppointmentStartDropValue(){
        return this.addAppointmentStartDrop.getValue();
    }
    
    public String getAddAppointmentEndDropValue(){
        return this.addAppointmentEndDrop.getValue();
    }
    
    // Settters
    private BorderPane setPane(){
        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(25,25,25,25));
        
        return pane;
    }
    
    private GridPane setFieldPane(){
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.TOP_CENTER);
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(25,25,25,25));
        
        return pane;
    }
    
    public void setAddAppointmentCustomerNameField(String newText){
        this.addAppointmentCustomerNameField.setText(newText);
    }
    
    public void setAddAppointmentTitleField(String newText){
        this.addAppointmentTitleField.setText(newText);
    }
    
    public void setAddAppointmentDescriptionField(String newText){
        this.addAppointmentDescriptionField.setText(newText);
    }
    
    public void setAddAppointmentLocationField(String newText){
        this.addAppointmentLocationField.setText(newText);
    }
            
    public void setAddAppointmentContactField(String newText){
        this.addAppointmentContactField.setText(newText);
    }
    
    public void setAddAppointmentTypeField(String newText){
        this.addAppointmentTypeField.setText(newText);
    }
            
    public void setAddAppointmentUrlField(String newText){
        this.addAppointmentUrlField.setText(newText);
    }
    
    public void setAddAppointmentErrorResponse(String newText){
        this.addAppointmentErrorResponse.setText(newText);
    }
    
    public void setAddAppointmentDate(String newText){
        this.addAppointmentDate.setText(newText);
    }
    
    public void clearAddAppointmentTable(){
        this.table.getItems().clear();
    }
    
    public void clearAddAppointmentStartDrop(){
       this.addAppointmentStartDrop.getItems().clear();
    }
    
    public void clearAddAppointmentEndDrop(){
        this.addAppointmentEndDrop.getItems().clear();
    }
    
    public void setAddAppointmentStartDrop(ArrayList<LocalDateTime> appointmentSlots){
        for (LocalDateTime time : appointmentSlots){
            this.addAppointmentStartDrop.getItems().add(String.format("%02d:%02d", time.getHour(), time.getMinute()));
        }
    }
    
    public void setAddAppointmentEndDrop(ArrayList<LocalDateTime> appointmentSlots){
        for (LocalDateTime time : appointmentSlots){
            this.addAppointmentEndDrop.getItems().add(String.format("%02d:%02d", time.getHour(), time.getMinute()));
        }
    }
    
    public void setAddAppointmentStartDropValue(String value){
        this.addAppointmentStartDrop.setValue(value);
    }
    
    public void setAddAppointmentEndDropValue(String value){
        this.addAppointmentEndDrop.setValue(value);
    }
    
    public void setToSpanish(){
        this.addCustomerNameAppointment.setText("Cliente");
        this.addAddressAppointment.setText("Direcci" + Character.toString('\u00F3') + "n");
        this.addCustomerPhoneAppointment.setText("Tel" + Character.toString('\u00E9') + "fono#");
        this.table.setPlaceholder(new Label("No hay filas para mostrar"));
        this.addAppointmentTitleHeader.setText("Agregar cita");
        
        this.addAppointmentCustomerName.setText("Nombre: ");
        this.addAppointmentTitle.setText("T" + Character.toString('\u00ED') + "tulo: ");
        this.addAppointmentDescription.setText("Descripci" + Character.toString('\u00F3') + "n: ");
        this.addAppointmentLocation.setText("Ubicaci" + Character.toString('\u00F3') + "n: ");
        this.addAppointmentContact.setText("Contacto: ");
        this.addAppointmentType.setText("Tipo: ");
        this.addAppointmentUrl.setText("Url: ");
        this.addAppointmentStart.setText("Hora de inicio: ");
        this.addAppointmentEnd.setText("Hora de finalizaci" + Character.toString('\u00F3') + "n: ");
        
        this.addAppointmentCancelButton.setText("Cancela");
        this.addAppointmentAddButton.setText("Agregar cita");
        this.addCustomerToAppointment.setText("Agregar cliente");
    }
    
    public void setToEnglish(){
        this.addCustomerNameAppointment.setText("Customer Name");
        this.addAddressAppointment.setText("Address");
        this.addCustomerPhoneAppointment.setText("Phone#");
        this.table.setPlaceholder(new Label("No rows to display"));
        this.addAppointmentTitleHeader.setText("Add Appointment");
        
        this.addAppointmentCustomerName.setText("Name: ");
        this.addAppointmentTitle.setText("Title: ");
        this.addAppointmentDescription.setText("Description: ");
        this.addAppointmentLocation.setText("Location: ");
        this.addAppointmentContact.setText("Contact: ");
        this.addAppointmentType.setText("Type: ");
        this.addAppointmentUrl.setText("Url: ");
        this.addAppointmentStart.setText("Start: ");
        this.addAppointmentEnd.setText("End: ");
        
        this.addAppointmentCancelButton.setText("Cancel");
        this.addAppointmentAddButton.setText("Add Appointment");
        this.addCustomerToAppointment.setText("Add Customer");
    }
}


        
        

       
       
        


        
        
        