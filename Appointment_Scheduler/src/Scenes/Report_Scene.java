/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scenes;

import Panes.Report_Pane;
import javafx.scene.Scene;

/**
 *
 * @author mauli
 */
public class Report_Scene {
    private Scene scene;
    private Report_Pane pane;
     
    public Report_Scene(Report_Pane reportPane){
        this.pane = reportPane;
        this.scene = new Scene(this.pane.getPane(), 600, 550);
    }
    
    public Scene getScene(){
        return this.scene;
    }
    
    public Report_Pane getPane(){
        return this.pane;
    }
}
