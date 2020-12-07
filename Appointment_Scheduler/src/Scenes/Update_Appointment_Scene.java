

package Scenes;

import Panes.Update_Appointment_Pane;
import javafx.scene.Scene;


public class Update_Appointment_Scene {
    private Scene scene;
    private Update_Appointment_Pane pane;
     
    public Update_Appointment_Scene(Update_Appointment_Pane updateAppointmentPane){
        this.pane = updateAppointmentPane;
        this.scene = new Scene(this.pane.getPane(), 1200, 700);
    }
    
    public Scene getScene(){
        return this.scene;
    }
    
    public Update_Appointment_Pane getPane(){
        return this.pane;
    }
}
