

package Scenes;

import Calendar.Calendar;
import Panes.Appointment_Pane;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;


public class Appointments_Scene {
    private Scene scene;
    private Appointment_Pane pane;
    
    public Appointments_Scene(Appointment_Pane appointmentPane){
        this.pane = appointmentPane;
        this.scene = new Scene(this.pane.getPane(), 900, 1000);
    }
    
    
    // Getters
    
    public Scene getScene(){
        return this.scene;
    }
    
    public Appointment_Pane getPane(){
        return this.pane;
    }
}
