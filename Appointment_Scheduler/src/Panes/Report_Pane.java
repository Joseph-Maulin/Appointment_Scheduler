
package Panes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class Report_Pane {
    // Pane
    private BorderPane Pane;
    
    // Button
    private Button cancelButton;
    private Button appointmentmentTypeMonthReportButton;
    private Button consultantScheduleReportButton;
    private Button customerReportButton;
    
    // VBox
    private VBox reportOptions;
    
    // Label 
    private Label reportStatus;
    
    
    
    public Report_Pane(){
        // Pane
        this.Pane = this.setPane();
        
        // Button
        this.cancelButton = new Button("Cancel");
        this.appointmentmentTypeMonthReportButton = new Button("Appointmentment Type Monthly Report");
        this.consultantScheduleReportButton = new Button("Consultant Schedule Report");
        this.customerReportButton = new Button("Customer Report");
        
        
        // Label
        this.reportStatus = new Label();
        this.reportStatus.setTextFill(Color.web("#ff0000", 0.8));
        
        
        // VBox
        this.reportOptions = new VBox();
        this.reportOptions.setAlignment(Pos.CENTER);
        this.reportOptions.setSpacing(40);
        this.reportOptions.getChildren().addAll(this.appointmentmentTypeMonthReportButton,
                                                this.consultantScheduleReportButton,
                                                this.customerReportButton,
                                                this.reportStatus);
        
        
        // Set Pane
        this.Pane.setCenter(this.reportOptions);
        this.Pane.setBottom(this.cancelButton);
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
    public Button getCancelButton(){
        return this.cancelButton;
    }
    
    public Button getAppointmentmentTypeMonthReportButton(){
        return this.appointmentmentTypeMonthReportButton;
    }
    
    public Button getConsultantScheduleReportButton(){
        return this.consultantScheduleReportButton;
    }
    
    public Button getCustomerReportButton(){
        return this.customerReportButton;
    }
    
    // Setters
    public void setReportStatus(String newText){
        this.reportStatus.setText(newText);
    }
    
    public void setToEnglish(){
        this.cancelButton.setText("Cancel");
        this.appointmentmentTypeMonthReportButton.setText("Appointmentment Type Monthly Report");
        this.consultantScheduleReportButton.setText("Consultant Schedule Report");
        this.customerReportButton.setText("Customer Report");
    }
    
    public void setToSpanish(){
        this.cancelButton.setText("Cancela");
        this.appointmentmentTypeMonthReportButton.setText("Tipo de Cita Informe Mensual");
        this.consultantScheduleReportButton.setText("Informe de Programación del Consultor");
        this.customerReportButton.setText("Informe de Cliente");
    }
}
