
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


public class Add_Customer_Pane {
    private GridPane Pane;
    
    private Text addCustomerTitle;
    private Label addCustomerName;
    private TextField addCustomerNameField;
    private Label addCustomerPhone;
    private TextField addCustomerPhoneField;
    private Label addCustomerAddress1;
    private TextField addCustomerAddress1Field;
    private Label addCustomerAddress2; 
    private TextField addCustomerAddress2Field;
    private Label addCustomerCity;
    private TextField addCustomerCityField;
    private Label addCustomerPostal;
    private TextField addCustomerPostalField;
    private Label addCustomerCountry;
    private TextField addCustomerCountryField;
    private Label addCustomerErrorResponse;
    private Button addCustomerCancelButton;
    private Button addCustomerAddButton;
    
    
    public Add_Customer_Pane(){
        this.Pane = this.setPane();
        
         // Text
        this.addCustomerTitle = new Text("Add Customer");
        this.addCustomerTitle.setX(50);
        this.addCustomerTitle.setY(50);
        this.addCustomerTitle.setFont(Font.font("verdana", FontWeight.BOLD, 20));
        
        
        // Labels
        this.addCustomerName = new Label("Name: ");
        this.addCustomerPhone = new Label("Phone: ");
        this.addCustomerAddress1 = new Label("Address Line1: ");
        this.addCustomerAddress2 = new Label("Address Line(Opt): ");
        this.addCustomerCity = new Label("City: ");
        this.addCustomerPostal = new Label("Postal Code: ");
        this.addCustomerCountry = new Label("Country: ");
        this.addCustomerErrorResponse = new Label("");
        this.addCustomerErrorResponse.setTextFill(Color.web("#ff0000", 0.8));
        
        
        // TextField
        this.addCustomerNameField = new TextField(); 
        this.addCustomerPhoneField = new TextField();
        this.addCustomerAddress1Field = new TextField();
        this.addCustomerAddress2Field = new TextField();
        this.addCustomerCityField = new TextField();       
        this.addCustomerPostalField = new TextField();
        this.addCustomerCountryField = new TextField();
        

        // Button
        this.addCustomerCancelButton = new Button("Cancel");
        this.addCustomerAddButton = new Button("Add");
        
        
        // Add to Pane
        this.Pane.add(this.addCustomerTitle, 0,0,2,1);
        this.Pane.add(this.addCustomerName, 0,1);        
        this.Pane.add(this.addCustomerNameField,1,1);
        this.Pane.add(this.addCustomerPhone,0,2);
        this.Pane.add(this.addCustomerPhoneField,1,2);
        this.Pane.add(this.addCustomerCountry,0,3);
        this.Pane.add(this.addCustomerCountryField,1,3);
        this.Pane.add(this.addCustomerAddress1,0,4);
        this.Pane.add(this.addCustomerAddress1Field,1,4,3,1);
        this.Pane.add(this.addCustomerAddress2,0,5);
        this.Pane.add(this.addCustomerAddress2Field,1,5,3,1);
        this.Pane.add(this.addCustomerCity,0,6);
        this.Pane.add(this.addCustomerCityField,1,6);
        this.Pane.add(this.addCustomerPostal,2,6);
        this.Pane.add(this.addCustomerPostalField,3,6);
        this.Pane.add(this.addCustomerErrorResponse,0,7);
        this.Pane.add(this.addCustomerAddButton,0,14);
        this.Pane.add(this.addCustomerCancelButton,1,14);   
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
    
    public Button getAddCustomerCancelButton(){
        return addCustomerCancelButton;
    }
    
    public Button getAddCustomerAddButton(){
        return this.addCustomerAddButton;
    }
    
    public String getAddCustomerNameField(){
        return this.addCustomerNameField.getText();
    }
    
    public String getAddCustomerPhoneField(){
        return this.addCustomerPhoneField.getText();
    }
    
    public String getAddCustomerCountryField(){
        return this.addCustomerCountryField.getText();
    }
    
    public String getAddCustomerAddress1Field(){
        return this.addCustomerAddress1Field.getText();
    }
    
    public String getAddCustomerAddress2Field(){
        return this.addCustomerAddress2Field.getText();
    }
    
    public String getAddCustomerCityField(){
        return this.addCustomerCityField.getText();
    }
    
    public String getAddCustomerPostalField(){
        return this.addCustomerPostalField.getText();
    }

    
    //Setters
    
    public void setAddCustomerNameField(String newText){
        this.addCustomerNameField.setText(newText);
    }
    
    public void setAddCustomerPhoneField(String newText){
        this.addCustomerPhoneField.setText(newText);
    }
    
    public void setAddCustomerCountryField(String newText){
        this.addCustomerCountryField.setText(newText);
    }
    
    public void setAddCustomerAddress1Field(String newText){
        this.addCustomerAddress1Field.setText(newText);
    }
    
    public void setAddCustomerAddress2Field(String newText){
        this.addCustomerAddress2Field.setText(newText);
    }
    
    public void setAddCustomerCityField(String newText){
        this.addCustomerCityField.setText(newText);
    }
    
    public void setAddCustomerPostalField(String newText){
        this.addCustomerPostalField.setText(newText);
    }
    
    public void setAddCustomerErrorResponse(String newText){
        this.addCustomerErrorResponse.setText(newText);
    }
    
    public void setToSpanish(){
        this.addCustomerTitle.setText("Agregar Customer");
        this.addCustomerName.setText("Nombre: ");
        this.addCustomerPhone.setText("Tel" + Character.toString('\u00E9') + "fono: ");
        this.addCustomerAddress1.setText("Dirección Línea 1: ");
        this.addCustomerAddress2.setText("Dirección Línea 2: ");
        this.addCustomerCity.setText("Ciudad: ");
        this.addCustomerPostal.setText("C" + Character.toString('\u00F3') + "digo postal: ");
        this.addCustomerCountry.setText("Pa" + Character.toString('\u00ED') + "s: ");
        
        this.addCustomerCancelButton.setText("Cancela");
        this.addCustomerAddButton.setText("Agrega");
    }
    
    public void setToEnglish(){
        this.addCustomerTitle.setText("Add Customer");
        this.addCustomerName.setText("Name: ");
        this.addCustomerPhone.setText("Phone: ");
        this.addCustomerAddress1.setText("Address Line1: ");
        this.addCustomerAddress2.setText("Address Line(Opt): ");
        this.addCustomerCity.setText("City: ");
        this.addCustomerPostal.setText("Postal Code: ");
        this.addCustomerCountry.setText("Country: ");
        
        this.addCustomerCancelButton.setText("Cancel");
        this.addCustomerAddButton.setText("Add");
    }
}
