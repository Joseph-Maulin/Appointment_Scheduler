
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


public class Update_Appointment_Pane {
        
        // Pane
        private BorderPane Pane;
        private GridPane FieldPane;
        
        // Customer Table
        private TableView customerTable;
        private TableColumn<String, Customer> updateCustomerNameAppointment;
        private TableColumn<String, Customer> updateAddressAppointment;
        private TableColumn<String, Customer> updateCustomerPhoneAppointment;       
        
        // Text
        private Text updateAppointmentTitleHeader;
        
        // Label
        private String updateAppointmentID;
        private String updateAppointmentCustomerID;
        private Label updateAppointmentCustomerName;
        private Label updateAppointmentTitle;
        private Label updateAppointmentDescription;
        private Label updateAppointmentLocation;
        private Label updateAppointmentContact;
        private Label updateAppointmentType;
        private Label updateAppointmentUrl;
        private Label updateAppointmentStart;
        private Label updateAppointmentEnd;
        private Label updateAppointmentErrorResponse;
        private Label updateAppointmentDate;
        
        // TextField
        private TextField updateAppointmentCustomerNameField;
        private TextField updateAppointmentTitleField;
        private TextField updateAppointmentDescriptionField;
        private TextField updateAppointmentLocationField;
        private TextField updateAppointmentContactField;
        private TextField updateAppointmentTypeField;
        private TextField updateAppointmentUrlField;
        
        // Button
        private Button updateAppointmentCancelButton;
        private Button updateAppointmentAddButton;
        private Button updateCustomerToAppointmentButton;
        
        // ComboBox
        private ComboBox<String> updateAppointmentStartDrop;
        private ComboBox<String> updateAppointmentEndDrop;
        
        // VBox
        private VBox updateAppointmentCustomerBox;
    
    public Update_Appointment_Pane(TableView updateAppointmentCustomerTable, ComboBox startDrop, ComboBox endDrop){
        // Pane
        this.Pane = this.setPane();
        this.FieldPane = this.setFieldPane();
        
        // CustomerTable
        this.customerTable = updateAppointmentCustomerTable;
        
        this.updateCustomerNameAppointment = new TableColumn<String, Customer>("Customer Name");
        this.updateCustomerNameAppointment.setCellValueFactory(new PropertyValueFactory<String, Customer>("customerName"));
        this.updateAddressAppointment = new TableColumn<String, Customer>("Address");
        this.updateAddressAppointment.setCellValueFactory(new PropertyValueFactory<String, Customer>("fullAddress"));
        this.updateCustomerPhoneAppointment = new TableColumn<String, Customer>("Phone#");
        this.updateCustomerPhoneAppointment.setCellValueFactory(new PropertyValueFactory<String, Customer>("phone"));
        
        this.customerTable.getColumns().addAll(updateCustomerNameAppointment, updateAddressAppointment, updateCustomerPhoneAppointment);
        this.customerTable.setPlaceholder(new Label("No rows to display"));
        
        // Text
        this.updateAppointmentTitleHeader = new Text("Update Appointment");
        this.updateAppointmentTitleHeader.setX(50);
        this.updateAppointmentTitleHeader.setY(50);
        this.updateAppointmentTitleHeader.setFont(Font.font("verdana", FontWeight.BOLD, 20));
        
        // Trackers
        this.updateAppointmentID = "";
        this.updateAppointmentCustomerID = "";
        
        // Label
        this.updateAppointmentCustomerName = new Label("Name: ");
        this.updateAppointmentTitle = new Label("Title: ");
        this.updateAppointmentDescription = new Label("Description: ");
        this.updateAppointmentLocation = new Label("Location: ");
        this.updateAppointmentContact = new Label("Contact: ");
        this.updateAppointmentType = new Label("Type: ");
        this.updateAppointmentUrl = new Label("Url: ");
        this.updateAppointmentStart = new Label("Start: ");
        this.updateAppointmentEnd = new Label("End: ");
        this.updateAppointmentErrorResponse = new Label("");
        this.updateAppointmentErrorResponse.setTextFill(Color.web("#ff0000", 0.8));
        this.updateAppointmentDate = new Label("");
        this.updateAppointmentDate.setFont(Font.font("verdana", FontWeight.BOLD, 20));
        
        // TextField
        this.updateAppointmentCustomerNameField = new TextField();
        this.updateAppointmentCustomerNameField.setEditable(false);
        this.updateAppointmentTitleField = new TextField();
        this.updateAppointmentDescriptionField = new TextField();
        this.updateAppointmentLocationField = new TextField();
        this.updateAppointmentContactField = new TextField();
        this.updateAppointmentTypeField = new TextField();
        this.updateAppointmentUrlField = new TextField();
        
        // Button
        this.updateAppointmentCancelButton = new Button("Cancel");
        this.updateAppointmentAddButton = new Button("Update Appointment");
        this.updateCustomerToAppointmentButton = new Button("Update Customer");
        
        // ComboBox
        this.updateAppointmentStartDrop = startDrop;
        this.updateAppointmentStartDrop.showingProperty().addListener((obs, wasShowing, isNowShowing)-> {
            if(isNowShowing){
                System.out.println(obs);
                System.out.println(wasShowing);
                System.out.println(isNowShowing);
                System.out.println();
            }
        });
        
        this.updateAppointmentEndDrop = endDrop;
        this.updateAppointmentEndDrop.showingProperty().addListener((obs, wasShowing, isNowShowing)-> {
            if(isNowShowing){
                System.out.println(obs);
                System.out.println(wasShowing);
                System.out.println(isNowShowing);
                System.out.println();
            }
        });
        
        // Set FieldPane
        this.FieldPane.add(updateAppointmentTitleHeader, 0,0,2,1);
        this.FieldPane.add(updateAppointmentDate,4,0);
        this.FieldPane.add(updateAppointmentCustomerName, 0,1);
        this.FieldPane.add(updateAppointmentCustomerNameField,1,1);
        this.FieldPane.add(updateAppointmentTitle,0,2);
        this.FieldPane.add(updateAppointmentTitleField,1,2);
        this.FieldPane.add(updateAppointmentDescription,0,3);
        this.FieldPane.add(updateAppointmentDescriptionField,1,3);
        this.FieldPane.add(updateAppointmentLocation,0,4);
        this.FieldPane.add(updateAppointmentLocationField,1,4,3,1);
        this.FieldPane.add(updateAppointmentContact,0,5);
        this.FieldPane.add(updateAppointmentContactField,1,5,3,1);
        this.FieldPane.add(updateAppointmentType,0,6);
        this.FieldPane.add(updateAppointmentTypeField,1,6);
        this.FieldPane.add(updateAppointmentUrl,0,7);
        this.FieldPane.add(updateAppointmentUrlField,1,7);
        this.FieldPane.add(updateAppointmentStart,0,8);
        this.FieldPane.add(updateAppointmentStartDrop,1,8);
        this.FieldPane.add(updateAppointmentEnd,2,8);
        this.FieldPane.add(updateAppointmentEndDrop,3,8);
        this.FieldPane.add(updateAppointmentErrorResponse,0,9);
        this.FieldPane.add(updateAppointmentAddButton,0,14);
        this.FieldPane.add(updateAppointmentCancelButton,1,14);
        
        // VBox
        this.updateAppointmentCustomerBox = new VBox(updateCustomerToAppointmentButton, this.customerTable);
        
        // Set Pane
        this.Pane.setLeft(this.FieldPane);
        this.Pane.setRight(this.updateAppointmentCustomerBox);
    }
        
    
    // Set Panes
    private BorderPane setPane(){
        BorderPane Pane = new BorderPane();
        Pane.setPadding(new Insets(25,25,25,25));
        
        return Pane;
    }
    
    private GridPane setFieldPane(){
        GridPane Pane = new GridPane();
        Pane.setAlignment(Pos.TOP_CENTER);
        Pane.setHgap(10);
        Pane.setVgap(10);
        Pane.setPadding(new Insets(25,25,25,25));
        
        return Pane;
    }
    
    // Get Panes
    public BorderPane getPane(){
        return this.Pane;
    } 
    
    public GridPane getFieldPane(){
        return this.FieldPane;
    }
    
    
    // Getters
    public String getUpdateAppointmentDate(){
        return this.updateAppointmentDate.getText();
    }
    
    public Button getUpdateAppointmentCancelButton(){
        return this.updateAppointmentCancelButton;
    }
    
    public Button getUpdateAppointmentAddButton(){
        return this.updateAppointmentAddButton;
    }
    
    public Button getUpdateCustomerToAppointmentButton(){
        return this.updateCustomerToAppointmentButton;
    }
    
    public String getUpdateAppointmentID(){
        return this.updateAppointmentID;
    }
    
    public String getUpdateAppointmentCustomerID(){
        return this.updateAppointmentCustomerID;
    }
    
    public String getUpdateAppointmentCustomerNameField(){
        return this.updateAppointmentCustomerNameField.getText();
    }
    
    public String getUpdateAppointmentTitleField(){
        return this.updateAppointmentTitleField.getText();
    }
    
    public String getUpdateAppointmentDescriptionField(){
        return this.updateAppointmentDescriptionField.getText();
    }
    
    public String getUpdateAppointmentLocationField(){
        return this.updateAppointmentLocationField.getText();
    }

    public String getUpdateAppointmentContactField(){
        return this.updateAppointmentContactField.getText();
    }
          
    public String getUpdateAppointmentTypeField(){
        return this.updateAppointmentTypeField.getText();
    }
    
    public String getUpdateAppointmentUrlField(){
        return this.updateAppointmentUrlField.getText();
    }
    
    public ComboBox getUpdateAppointmentStartDropBox(){
        return this.updateAppointmentStartDrop;
    }
    
    public ComboBox getUpdateAppointmentEndDropBox(){
        return this.updateAppointmentEndDrop;
    }
    
    public String getUpdateAppointmentStartDropValue(){
        return this.updateAppointmentStartDrop.getValue();
    }
    
    public String getUpdateAppointmentEndDropValue(){
        return this.updateAppointmentEndDrop.getValue();
    }

    public TableView getTable(){
        return this.customerTable;
    }
    
    // Setters
    public void setUpdateAppointmentID(String newID){
        this.updateAppointmentID =newID;
    }
    
    public void setUpdateAppointmentCustomerID(String newID){
        this.updateAppointmentCustomerID = newID;
    }
    
    public void setUpdateAppointmentCustomerNameField(String newText){
        this.updateAppointmentCustomerNameField.setText(newText);
    }
    
    public void setUpdateAppointmentTitleField(String newText){
        this.updateAppointmentTitleField.setText(newText);
    }
    
    public void setUpdateAppointmentDescriptionField(String newText){
        this.updateAppointmentDescriptionField.setText(newText);
    }
    
    public void setUpdateAppointmentLocationField(String newText){
        this.updateAppointmentLocationField.setText(newText);
    }
    
    public void setUpdateAppointmentContactField(String newText){
        this.updateAppointmentContactField.setText(newText);
    }
    
    public void setUpdateAppointmentTypeField(String newText){
        this.updateAppointmentTypeField.setText(newText);
    }
    
    public void setUpdateAppointmentUrlField(String newText){
        this.updateAppointmentUrlField.setText(newText);
    }
    
    public void setUpdateAppointmentErrorResponse(String newText){
        this.updateAppointmentErrorResponse.setText(newText);
    }
    
    public void setUpdateAppointmentDate(String newText){
        this.updateAppointmentDate.setText(newText);
    }
    
    public void setUpdateAppointmentStartDropValue(String value){
        this.updateAppointmentStartDrop.setValue(value);
    }
    
    public void setUpdateAppointmentEndDropValue(String value){
        this.updateAppointmentEndDrop.setValue(value);
    }
    
    public void setUpdateAppointmentStartDrop(ArrayList<LocalDateTime> appointmentSlots){
        for (LocalDateTime time : appointmentSlots){
            this.updateAppointmentStartDrop.getItems().add(String.format("%02d:%02d", time.getHour(), time.getMinute()));
        }
    }
    
    public void setUpdateAppointmentEndDrop(ArrayList<LocalDateTime> appointmentSlots){
        for (LocalDateTime time : appointmentSlots){
            this.updateAppointmentEndDrop.getItems().add(String.format("%02d:%02d", time.getHour(), time.getMinute()));
        }
    }
    
    public void clearUpdateAppointmentStartDrop(){
        this.updateAppointmentStartDrop.getItems().clear();
    }
    
    public void clearUpdateAppointmentEndDrop(){
        this.updateAppointmentEndDrop.getItems().clear();
    }
            
    public void clearUpdateAppointmentTable(){
        this.customerTable.getItems().clear();
    }
    
    public void setToEnglish(){
        this.updateCustomerNameAppointment.setText("Customer Name");
        this.updateAddressAppointment.setText("Address");
        this.updateCustomerPhoneAppointment.setText("Phone#");
        this.customerTable.setPlaceholder(new Label("No rows to display"));
        this.updateAppointmentTitleHeader.setText("Update Appointment");
        this.updateAppointmentCustomerName.setText("Name: ");
        this.updateAppointmentTitle.setText("Title: ");
        this.updateAppointmentDescription.setText("Description: ");
        this.updateAppointmentLocation.setText("Location: ");
        this.updateAppointmentContact.setText("Contact: ");
        this.updateAppointmentType.setText("Type: ");
        this.updateAppointmentUrl.setText("Url: ");
        this.updateAppointmentStart.setText("Start: ");
        this.updateAppointmentEnd.setText("End: ");
        this.updateAppointmentCancelButton.setText("Cancel");
        this.updateAppointmentAddButton.setText("Update Appointment");
        this.updateCustomerToAppointmentButton.setText("Update Customer");
    }
    
    public void setToSpanish(){
        this.updateCustomerNameAppointment.setText("Cliente");
        this.updateAddressAppointment.setText("Direcci" + Character.toString('\u00F3') + "n");
        this.updateCustomerPhoneAppointment.setText("Tel" + Character.toString('\u00E9') + "fono#");
        this.customerTable.setPlaceholder(new Label("No hay filas para mostrar"));
        this.updateAppointmentTitleHeader.setText("Agregar cita");
        this.updateAppointmentCustomerName.setText("Nombre: ");
        this.updateAppointmentTitle.setText("T" + Character.toString('\u00ED') + "tulo: ");
        this.updateAppointmentDescription.setText("Descripci" + Character.toString('\u00F3') + "n: ");
        this.updateAppointmentLocation.setText("Ubicaci" + Character.toString('\u00F3') + "n: ");
        this.updateAppointmentContact.setText("Contacto: ");
        this.updateAppointmentType.setText("Tipo: ");
        this.updateAppointmentUrl.setText("Url: ");
        this.updateAppointmentStart.setText("Hora de inicio: ");
        this.updateAppointmentEnd.setText("Hora de finalizaci" + Character.toString('\u00F3') + "n: ");
        this.updateAppointmentCancelButton.setText("Cancela");
        this.updateAppointmentAddButton.setText("Agregar cita");
        this.updateCustomerToAppointmentButton.setText("Agregar cliente");
    }
}

