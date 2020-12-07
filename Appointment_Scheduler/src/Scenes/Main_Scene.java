
package Scenes;

import Panes.Main_Pane;
import javafx.scene.Scene;

public class Main_Scene {
    
    private Scene scene;
    private Main_Pane pane;
    
    
    public Main_Scene(Main_Pane mainPane){
        this.pane = mainPane;
        this.scene = new Scene(this.pane.getPane(), 600, 550);
    }
    
    public Scene getScene(){
        return this.scene;
    }
    
    public Main_Pane getPane(){
        return this.pane;
    }
}
