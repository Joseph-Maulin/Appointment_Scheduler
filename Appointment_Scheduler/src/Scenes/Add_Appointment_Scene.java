

package Scenes;

import Panes.Add_Appointment_Pane;
import javafx.scene.Scene;


public class Add_Appointment_Scene {
    private Scene scene;
    private Add_Appointment_Pane pane;
    

    public Add_Appointment_Scene(Add_Appointment_Pane addAppointmentPane){
        this.scene = new Scene(addAppointmentPane.getPane(), 1200, 700);
        this.pane = addAppointmentPane;
    }
    
    
    
    public Scene getScene(){
        return this.scene;
    }
    
    public Add_Appointment_Pane getPane(){
        return this.pane;
    }
}
