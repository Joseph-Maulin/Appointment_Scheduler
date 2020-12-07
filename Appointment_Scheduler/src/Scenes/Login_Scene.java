/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scenes;

import javafx.scene.Scene;
import Panes.Login_Pane;

public class Login_Scene {
    private Scene scene;
    private Login_Pane pane;
    
    public Login_Scene(Login_Pane loginPane){
        this.pane = loginPane;
        this.scene = new Scene(this.pane.getPane(), 385, 275);
    }
    
    public Scene getScene(){
        return this.scene;
    }
    
    public Login_Pane getPane(){
        return this.pane;
    }
}
