
package Panes;

import Objects.Customer;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class Customer_Pane {
    // Pane
    private BorderPane Pane;
    
    // Table
    private TableView customerTable;
    private TableColumn<String, Customer> customerName;
    private TableColumn<String, Customer> address;
    private TableColumn<String, Customer> customerPhone;
    
    // Label
    private Label customerErrorResponse;
    
    // Button
    private Button addCustomerButton;
    private Button updateCustomerButton;
    private Button deleteCustomerButton;
    private Button customerBackButton;
    
    // VBox
    private VBox buttons;
    
    
    public Customer_Pane(TableView customerTable){
        // Pane
        this.Pane = this.setPane();
        
        // Table
        this.customerTable = customerTable;
        this.customerName = new TableColumn<String, Customer>("Customer Name");
        this.customerName.setCellValueFactory(new PropertyValueFactory<String, Customer>("customerName"));
        this.address = new TableColumn<String, Customer>("Address");
        this.address.setCellValueFactory(new PropertyValueFactory<String, Customer>("fullAddress"));
        this.customerPhone = new TableColumn<String, Customer>("Phone#");
        this.customerPhone.setCellValueFactory(new PropertyValueFactory<String, Customer>("phone"));
        
        this.customerTable.getColumns().addAll(this.customerName, this.address, this.customerPhone);
        this.customerTable.setPlaceholder(new Label("No rows to display"));
        
        // Label
        this.customerErrorResponse = new Label("");
        this.customerErrorResponse.setTextFill(Color.web("#ff0000", 0.8));
        
        // Button
        this.addCustomerButton = new Button("Add customer");
        this.updateCustomerButton = new Button("Update customer");
        this.deleteCustomerButton = new Button("Delete customer");
        this.customerBackButton = new Button("Main Menu");
        
        // VBox
        this.buttons = new VBox();
        this.buttons.setSpacing(20);   
        this.buttons.getChildren().addAll(this.addCustomerButton, this.updateCustomerButton, this.deleteCustomerButton, this.customerErrorResponse);
        
        // Set Pane
        this.Pane.setLeft(this.buttons);
        this.Pane.setRight(this.customerTable);
        this.Pane.setBottom(this.customerBackButton);
    }
        
    public BorderPane setPane(){
        BorderPane Pane = new BorderPane();
        Pane.setPadding(new Insets(25,25,25,25));
        
        return Pane;
    }
    
    public BorderPane getPane(){
        return this.Pane;
    }
    
    
    // Getters
    
    public Button getAddCustomerButton(){
        return this.addCustomerButton;
    }
    
    public Button getUpdateCustomerButton(){
        return this.updateCustomerButton;
    }
    
    public Button getDeleteCustomerButton(){
        return this.deleteCustomerButton;
    }
    
    public Button getCustomerBackButton(){
        return this.customerBackButton;
    }
    
    public void setCustomerErrorResponse(String newText){
        this.customerErrorResponse.setText(newText);
    }
    
    
    public void setToSpanish(){
        this.customerName.setText("Cliente");
        this.address.setText("Dirección");
        this.customerPhone.setText("Tel" + Character.toString('\u00E9') + "fono#");
        this.customerTable.setPlaceholder(new Label("No hay filas para mostrar"));
        this.addCustomerButton.setText("Agregar Cliente");
        this.updateCustomerButton.setText("Actualizar al Cliente");
        this.deleteCustomerButton.setText("Eliminar Cliente");
        this.customerBackButton.setText("Men" + Character.toString('\u00FA') + " Principal");
    }
    
    public void setToEnglish(){
        this.customerName.setText("Customer Name");
        this.address.setText("Address");
        this.customerPhone.setText("Phone#");
        this.customerTable.setPlaceholder(new Label("No rows to display"));
        this.addCustomerButton.setText("Add customer");
        this.updateCustomerButton.setText("Update customer");
        this.deleteCustomerButton.setText("Delete customer");
        this.customerBackButton.setText("Main Menu");
    }
}
