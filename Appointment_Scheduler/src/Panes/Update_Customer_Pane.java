
package Panes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Update_Customer_Pane {
     private GridPane Pane;
     
     // Text
    private Text updateCustomerTitle; 
    
    // TextField
    private TextField updateCustomerNameField;
    private TextField updateCustomerPhoneField;
    private TextField updateCustomerAddress1Field;
    private TextField updateCustomerAddress2Field;
    private TextField updateCustomerCityField;
    private TextField updateCustomerPostalField;
    private TextField updateCustomerCountryField;
    
    // Label
    private Label updateCustomerName;
    private Label updateCustomerPhone;
    private Label updateCustomerAddress1;  
    private Label updateCustomerAddress2;
    private Label updateCustomerCity;
    private Label updateCustomerPostal;
    private Label updateCustomerCountry;
    private Label updateCustomerErrorResponse;
    
    // Button
    private Button updateCustomerCancelButton;
    private Button updateCustomerButton;
    
    
    public Update_Customer_Pane(){
        this.Pane = this.setPane();
        
        // Text
        this.updateCustomerTitle = new Text("Update Customer");
        this.updateCustomerTitle.setX(50);
        this.updateCustomerTitle.setY(50);
        this.updateCustomerTitle.setFont(Font.font("verdana", FontWeight.BOLD, 20));
         
        // TextField
        this.updateCustomerNameField = new TextField();
        this.updateCustomerPhoneField = new TextField();
        this.updateCustomerAddress1Field = new TextField();
        this.updateCustomerAddress2Field = new TextField();
        this.updateCustomerCityField = new TextField();
        this.updateCustomerPostalField = new TextField();
        this.updateCustomerCountryField = new TextField();
        
        // Label
        this.updateCustomerName = new Label("Name: ");
        this.updateCustomerPhone = new Label("Phone: ");
        this.updateCustomerAddress1 = new Label("Address Line1: ");
        this.updateCustomerAddress2 = new Label("Address Line(Opt): ");
        this.updateCustomerCity = new Label("City: ");
        this.updateCustomerPostal = new Label("Postal Code: ");
        this.updateCustomerCountry = new Label("Country: ");
        this.updateCustomerErrorResponse = new Label("");
        this.updateCustomerErrorResponse.setTextFill(Color.web("#ff0000", 0.8));
        
        // Button
        this.updateCustomerCancelButton = new Button("Cancel");
        this.updateCustomerButton = new Button("Update");
        
        
        // Set Pane
        this.Pane.add(updateCustomerTitle, 0,0,2,1);
        this.Pane.add(updateCustomerName, 0,1);
        this.Pane.add(updateCustomerNameField,1,1);
        this.Pane.add(updateCustomerPhone,0,2);
        this.Pane.add(updateCustomerPhoneField,1,2);
        this.Pane.add(updateCustomerCountry,0,3);
        this.Pane.add(updateCustomerCountryField,1,3);
        this.Pane.add(updateCustomerAddress1,0,4);
        this.Pane.add(updateCustomerAddress1Field,1,4,3,1);
        this.Pane.add(updateCustomerAddress2,0,5);
        this.Pane.add(updateCustomerAddress2Field,1,5,3,1);
        this.Pane.add(updateCustomerCity,0,6);
        this.Pane.add(updateCustomerCityField,1,6);
        this.Pane.add(updateCustomerPostal,2,6);
        this.Pane.add(updateCustomerPostalField,3,6);
        this.Pane.add(updateCustomerErrorResponse,0,7);
        this.Pane.add(updateCustomerButton,0,14);
        this.Pane.add(updateCustomerCancelButton,1,14);
    }
        
    public GridPane setPane(){
        GridPane Pane = new GridPane();
        Pane.setAlignment(Pos.TOP_CENTER);
        Pane.setHgap(10);
        Pane.setVgap(10);
        Pane.setPadding(new Insets(25,25,25,25));
        
        return Pane;
    }
    
    public GridPane getPane(){
        return this.Pane;
    }
    
    // Getters
    public Button getUpdateCustomerCancelButton(){
        return this.updateCustomerCancelButton;
    }
    
    public Button getUpdateCustomerButton(){
        return this.updateCustomerButton;
    }
    
    public String getUpdateCustomerNameField(){
        return this.updateCustomerNameField.getText();
    }
    
    public String getUpdateCustomerPhoneField(){
        return this.updateCustomerPhoneField.getText();
    }
    
    public String getUpdateCustomerAddress1Field(){
        return this.updateCustomerAddress1Field.getText();
    }
    
    public String getUpdateCustomerAddress2Field(){
        return this.updateCustomerAddress2Field.getText();
    }
    
    public String getUpdateCustomerCityField(){
        return this.updateCustomerCityField.getText();
    }
    
    public String getUpdateCustomerPostalField(){
        return this.updateCustomerPostalField.getText();
    }
    
    public String getUpdateCustomerCountryField(){
        return this.updateCustomerCountryField.getText();
    }
    
    
    // Setters
    public void setUpdateCustomerNameField(String newText){
        this.updateCustomerNameField.setText(newText);
    }
    
    public void setUpdateCustomerPhoneField(String newText){
        this.updateCustomerPhoneField.setText(newText);
    }
    
    public void setUpdateCustomerAddress1Field(String newText){
        this.updateCustomerAddress1Field.setText(newText);
    }
    
    public void setUpdateCustomerAddress2Field(String newText){
        this.updateCustomerAddress2Field.setText(newText);
    }
    
    public void setUpdateCustomerCityField(String newText){
        this.updateCustomerCityField.setText(newText);
    }
    
    public void setUpdateCustomerPostalField(String newText){
        this.updateCustomerPostalField.setText(newText);
    }
    
    public void setUpdateCustomerCountryField(String newText){
        this.updateCustomerCountryField.setText(newText);
    }
    
    public void setUpdateCustomerErrorResponse(String newText){
        this.updateCustomerErrorResponse.setText(newText);
    }
    
    public void setToEnglish(){
        this.updateCustomerTitle.setText("Update Customer");
        this.updateCustomerName.setText("Name: ");
        this.updateCustomerPhone.setText("Phone: ");
        this.updateCustomerAddress1.setText("Address Line1: ");
        this.updateCustomerAddress2.setText("Address Line(Opt): ");
        this.updateCustomerCity.setText("City: ");
        this.updateCustomerPostal.setText("Postal Code: ");
        this.updateCustomerCountry.setText("Country: ");
        this.updateCustomerCancelButton.setText("Cancel");
        this.updateCustomerButton.setText("Update");
    }
    
    public void setToSpanish(){
        this.updateCustomerTitle.setText("Actualiza cliente");
        this.updateCustomerName.setText("Nombre: ");
        this.updateCustomerPhone.setText("Teléfono: ");
        this.updateCustomerAddress1.setText("Dirección Línea 1: ");
        this.updateCustomerAddress2.setText("Dirección Línea 2: ");
        this.updateCustomerCity.setText("Ciudad: ");
        this.updateCustomerPostal.setText("C" + Character.toString('\u00F3') + "digo postal: ");
        this.updateCustomerCountry.setText("Pa" + Character.toString('\u00ED') + "s: ");
        this.updateCustomerCancelButton.setText("Cancela");
        this.updateCustomerButton.setText("Agrega");
    }
}
