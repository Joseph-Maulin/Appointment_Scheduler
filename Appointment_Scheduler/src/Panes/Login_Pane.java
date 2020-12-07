
package Panes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Login_Pane {
    // Pane
    private GridPane Pane;
    
    // Text
    private Text scenetitle;
    
    // Label
    private Label userName;
    private Label pw;
    private Label statusLogin;
    
    // TextField
    private TextField userTextField;
    
    // PasswordField
    private PasswordField pwBox;
    
    // Button
    private Button signInButton;
//    private Button changeLanguageButton;
    
    // HBox
    private HBox signInBox;
    
    
    public Login_Pane(){
        // Pane
        this.Pane = this.setPane();
        
        // Text
        this.scenetitle = new Text("Please Login:");
        this.scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        
        // Label
        this.userName = new Label("User Name:");
        this.pw = new Label("Password: ");
        this.statusLogin = new Label();
        this.statusLogin.setTextFill(Color.web("#ff0000", 0.8));
        
        // Textfield
        this.userTextField = new TextField();
        
        // PasswordField
        this.pwBox = new PasswordField();
        
        // Button
        this.signInButton = new Button("Sign in");
        this.signInButton.setDefaultButton(true);
//        this.changeLanguageButton = new Button("Espa" + Character.toString('\u00F1') + "ol");
//        this.changeLanguageButton.setAlignment(Pos.TOP_RIGHT);
        
        // HBox
        this.signInBox = new HBox(10);
        this.signInBox.setAlignment(Pos.BOTTOM_RIGHT);
        this.signInBox.getChildren().add(this.signInButton);
        
        // Set Pane
//        this.Pane.add(this.changeLanguageButton, 2, 0);
        this.Pane.add(scenetitle,0,2,2,1);
        this.Pane.add(userName,0,3);
        this.Pane.add(userTextField,1,3);
        this.Pane.add(pw,0,4);
        this.Pane.add(pwBox,1,4);
        this.Pane.add(statusLogin,0,5,3,1);
        this.Pane.add(this.signInButton,1,6);
    }
        
    // Setters
    public GridPane setPane(){
        GridPane Pane = new GridPane();
        Pane.setHgap(10);
        Pane.setVgap(10);
        Pane.setPadding(new Insets(25,25,25,25));
        
        return Pane;
    }
    
    public void setSceneTitle(String newText){
        this.scenetitle.setText(newText);
    }
    
//    public void setChangeLanguageButtonText(String newText){
//        this.changeLanguageButton.setText(newText);
//    }
    
    public void setUserNameLabelText(String newText){
        this.userName.setText(newText);
    }
    
    public void setPasswordLabelText(String newText){
        this.pw.setText(newText);
    }
    
    public void setSignInButtonText(String newText){
        this.signInButton.setText(newText);
    }
    
    public void setStatusLogin(String newText){
        this.statusLogin.setText(newText);
    }
    
    public void setUserTextField(String newText){
        this.userTextField.setText(newText);
    }
    
    public void setPW(String newText){
        this.pwBox.setText("");
    }
    
    public void setToSpanish(){
        this.setSceneTitle("Por favor, registrese:");
        this.setUserNameLabelText("Nombre de usuario:");
        this.setPasswordLabelText("Contrase" + Character.toString('\u00F1') + "a:");
        this.setSignInButtonText("Registrarse");
        this.setStatusLogin("");
    }
    
    public void setToEnglish(){
        this.setSceneTitle("Please Login:");
        this.setUserNameLabelText("User Name:");
        this.setPasswordLabelText("Password:");
        this.setSignInButtonText("Sign in");
        this.setStatusLogin("");
    }
    
    // Getters
    public GridPane getPane(){
        return this.Pane;
    }
    
    public Button getSignInButton(){
        return this.signInButton;
    }
    
    public String getUserTextField(){
        return this.userTextField.getText();
    }
    
    public String getPW(){
        return this.pwBox.getText();
    }
    
//    public Button getChangeLanguageButton(){
//        return this.changeLanguageButton;
//    }
}

       
        
        
       