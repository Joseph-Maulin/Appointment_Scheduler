
package Panes;

import java.time.LocalDate;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class Main_Pane {
    
    // Pane
    private BorderPane Pane;
    
    // Button
    private Button customerButton;
    private Button appointmentButton;
    private Button reportButton;
    private Button signOutButton;
    
    // VBox
    private VBox menuOptions;
    
    // Label
    private Label appointmentWarning;
    
    
    public Main_Pane(){
        
        // Pane
        this.Pane = this.setPane();
        
        // Button
        this.customerButton = new Button("Customers");
        this.appointmentButton = new Button("Appointments");
        this.reportButton = new Button("Reports");
        this.signOutButton = new Button("Sign out");
        
        // Label
        this.appointmentWarning = new Label();
        this.appointmentWarning.setTextFill(Color.web("#ff0000", 0.8));
        
        // VBox
        this.menuOptions = new VBox();
        this.menuOptions.setAlignment(Pos.CENTER);
        this.menuOptions.setSpacing(40);
        this.menuOptions.getChildren().addAll(this.customerButton, this.appointmentButton, this.reportButton, this.appointmentWarning);
        
        
        // Set Pane
        this.Pane.setCenter(this.menuOptions);
        this.Pane.setBottom(this.signOutButton);
    }
        
    
    // Set Pane
    public BorderPane setPane(){
        BorderPane Pane = new BorderPane();
        Pane.setPadding(new Insets(25,25,25,25));
        
        return Pane;
    }
    
    // Get Pane
    public BorderPane getPane(){
        return this.Pane;
    }
    
    
    // Getters
    public Button getCustomerButton(){
        return this.customerButton;
    }
    
    public Button getAppointmentButton(){
        return this.appointmentButton;
    }
    
    public Button getReportButton(){
        return this.reportButton;
    }
    
    public Button getSignOutButton(){
        return this.signOutButton;
    }
    
    // Setters
    public void setAppointmentWarning(String newText){
        this.appointmentWarning.setText(newText);
    }
    
    public void setToSpanish(){
        this.customerButton.setText("Clientes");
        this.appointmentButton.setText("Citas");
        this.reportButton.setText("Informes");
        this.signOutButton.setText("Desconectar");
        if(!(this.appointmentWarning.getText().equals(""))){
            this.appointmentWarning.setText("Cita en 15 minutos");
        }
    }
    
    public void setToEnglish(){
        this.customerButton.setText("Customers");
        this.appointmentButton.setText("Appointments");
        this.reportButton.setText("Reports");
        this.signOutButton.setText("Sign out");
        if(!(this.appointmentWarning.getText().equals(""))){
            this.appointmentWarning.setText("Appointment within 15 mins");
        }
    }
}
