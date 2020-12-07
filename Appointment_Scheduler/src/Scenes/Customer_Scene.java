/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scenes;

import Panes.Customer_Pane;
import javafx.scene.Scene;

public class Customer_Scene {
    private Scene Customer_Scene;
    private Customer_Pane customerPane;
    
    public Customer_Scene(Customer_Pane customerPane){
        this.customerPane = customerPane;
        this.Customer_Scene = new Scene(this.customerPane.getPane(), 600, 550);
    }
    
    public Scene getScene(){
        return this.Customer_Scene;
    }
    
    public Customer_Pane getPane(){
        return this.customerPane;
    }
}
