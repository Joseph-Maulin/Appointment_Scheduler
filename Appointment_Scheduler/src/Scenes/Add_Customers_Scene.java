
package Scenes;

import Panes.Add_Customer_Pane;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class Add_Customers_Scene {
    private Scene scene;
    private Add_Customer_Pane pane;

    
    public Add_Customers_Scene(Add_Customer_Pane addCustomerPane){
        
        
        // Scene
        this.pane = addCustomerPane;
        this.scene = new Scene(this.pane.getPane(), 600, 600);
    }
    
    
    public Scene getScene(){
        return this.scene;
    }
    
    public Add_Customer_Pane getPane(){
        return this.pane;
    }
}


