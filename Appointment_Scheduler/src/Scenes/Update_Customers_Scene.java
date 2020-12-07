
package Scenes;

import Panes.Update_Customer_Pane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Update_Customers_Scene {
   
    private Scene scene;
    private Update_Customer_Pane pane;
    
     
    public Update_Customers_Scene(Update_Customer_Pane updateCustomerPane){
        
        // Pane
        this.pane = updateCustomerPane;
        
        // Scene
        this.scene = new Scene(this.pane.getPane(), 600, 600);
    }
    
    
    public Update_Customer_Pane getPane(){
        return this.pane;
    }
    
    public Scene getScene(){
        return this.scene;
    }
}