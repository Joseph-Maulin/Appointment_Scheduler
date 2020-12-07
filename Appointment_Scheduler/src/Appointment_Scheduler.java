
import javafx.application.Application;
import javafx.stage.Stage;
import scheduler.Scheduler;

public class Appointment_Scheduler extends Application{
    
    
    @Override
    public void start(Stage primaryStage) {
        Scheduler scheduler = new Scheduler(primaryStage);
    }
    
    
    public static void main(String[] args) {
//        ZoneId zone = ZoneId.of("US/Eastern");
//        ZonedDateTime at = ZonedDateTime.of(2020, 4, 19, 2, 18, 30, 0, zone);
//        ExceptionControl control = new ExceptionControl();
//        System.out.println(control.insideBusinessHours(at));

    launch(args);
    }
}
