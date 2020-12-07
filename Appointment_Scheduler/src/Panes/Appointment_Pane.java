

package Panes;
import java.time.LocalDate;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Appointment_Pane {
    private StackPane Pane;
    private ImageView leftArrow;
    private ImageView rightArrow;
    private Button forwardButton;
    private Button backButton;
    private Button changeView;
    private Button returnButton;
    private String changeViewStatus;
    private LocalDate calendarDate;
    private Text appointmentDate;
    private Text calendarHeader;
    private GridPane calendarGrid;
    
    
    public Appointment_Pane(GridPane calendarGrid, Button left_arrow, Button right_arrow, Button changeView, Button returnButton){

        // Nav Arrows
        this.forwardButton = right_arrow;
        this.backButton = left_arrow;
        this.changeView = changeView;
        this.changeViewStatus = "weekly";
        
        // Calendar Header
        this.calendarDate = LocalDate.now();
        this.appointmentDate = this.setAppointmentDate();
        this.calendarHeader = this.setCalendarHeader(this.calendarDate);
        this.returnButton = returnButton;
        
        

        // Calendar
        this.calendarGrid = calendarGrid;
        
        // Pane
        this.Pane = this.setPane();  
    } 
        
    
    
    
    // Getters
    public StackPane getPane(){
        return this.Pane;
    }
    
    public Button getForwardButton(){
        return this.forwardButton;
    }
    
    public Button getBackButton(){
        return this.backButton;
    }
    
    public Button getChangeViewButton(){
        return this.changeView;
    }
    
    public String getChangeViewStatus(){
        return this.changeViewStatus;
    }
    
    public Button getReturnButton(){
        return this.returnButton;
    }
    
    public LocalDate getCalendarDate(){
        return this.calendarDate;
    }
    
    public Text getAppointmentDate(){
        return this.appointmentDate;
    }

    
    //Setters
    private StackPane setPane(){
        StackPane calendarView = new StackPane(this.calendarHeader, this.backButton, this.forwardButton, this.changeView, this.returnButton);
        calendarView.setAlignment(this.calendarHeader, Pos.TOP_LEFT);
        calendarView.setAlignment(this.forwardButton, Pos.TOP_RIGHT);
        calendarView.setAlignment(this.backButton, Pos.TOP_RIGHT);
        calendarView.setAlignment(this.changeView, Pos.TOP_RIGHT);
        calendarView.setAlignment(this.returnButton, Pos.TOP_RIGHT);
        calendarView.setMargin(this.forwardButton, new Insets(0, 100,10,0));
        calendarView.setMargin(this.backButton, new Insets(0, 160,10,0));
        calendarView.setMargin(this.changeView, new Insets(0, 220, 10, 0));
        calendarView.setMargin(this.returnButton, new Insets(0,10, 10, 0));
        calendarView.setMargin(this.calendarHeader, new Insets(10,10,10,10));
        
        return calendarView;
    }
    
    private Text setAppointmentDate(){
        Text appointmentDate = new Text();
        appointmentDate.setX(50);
        appointmentDate.setY(50);
        appointmentDate.setFont(Font.font("verdana", FontWeight.BOLD, 20));
        
        return appointmentDate;
    }
    
    public Text setCalendarHeader(LocalDate calendarDate){
        String month = calendarDate.getMonth().toString();
        String year = Integer.toString(calendarDate.getYear());
        Text calendarHeader = new Text(month + " " + year);
        calendarHeader.setFont(Font.font("Verdana", FontWeight.BOLD, 34));
        
        return calendarHeader;
    }
        
    
    public void setCalendarGrid(GridPane newCalendarGrid, int languageSetting){
        this.Pane.getChildren().remove(this.calendarGrid);
        this.calendarGrid = newCalendarGrid;
        this.Pane.getChildren().add(this.calendarGrid);
        
        this.Pane.setMargin(this.calendarGrid, new Insets(50, 10, 10, 10));
        
        String newMonth = this.calendarDate.getMonth().toString();
        String newYear = Integer.toString(this.calendarDate.getYear());
        if (languageSetting != 0) {
            if (newMonth.equals("JANUARY")){
                newMonth = "ENERO";
            }
            else if(newMonth.equals("FEBRUARY")){
                newMonth = "FEBRERO";
            }
            else if(newMonth.equals("MARCH")){
                newMonth = "MARZO";
            }
            else if(newMonth.equals("APRIL")){
                newMonth = "ABRIL";
            }
            else if(newMonth.equals("MAY")){
                newMonth = "MAYO";
            }
            else if(newMonth.equals("JUNE")){
                newMonth = "JUNIO";
            }
            else if(newMonth.equals("JULY")){
                newMonth = "JULIO";
            }
            else if(newMonth.equals("AUGUST")){
                newMonth = "AGOSTO";
            }
            else if(newMonth.equals("SEPTEMBER")){
                newMonth = "SEPTIEMBRE";
            }
            else if(newMonth.equals("OCTOBER")){
                newMonth = "OCTUBRE";
            }
            else if(newMonth.equals("NOVEMBER")){
                newMonth = "NOVIEMBRE";
            }
            else if(newMonth.equals("DECEMBER")){
                newMonth = "DICIEMBRE";
            }
        }
        
        this.calendarHeader.setText(newMonth + " " + newYear);
    }
    
    public void setCalendarDate(LocalDate date){
        this.calendarDate = date;
    }
    
    public void setCalendarDatePlusMonths(int numMonths){
        this.calendarDate = this.calendarDate.plusMonths(numMonths);
    }
    
    public void setCalendarDateMinusMonths(int numMonths){
        this.calendarDate = this.calendarDate.minusMonths(numMonths);
    }
    
    public void setCalendarDatePlusWeeks(int numWeeks){
        this.calendarDate = this.calendarDate.plusWeeks(numWeeks);
    }
    
    public void setCalendarDateMinusWeeks(int numWeeks){
        this.calendarDate = this.calendarDate.minusWeeks(numWeeks);
    }
    
    public void setChangeViewStatus(String newStatus){
        this.changeViewStatus = newStatus;
    }
}
