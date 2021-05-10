import com.revature.batch412.keithsantamaria.project1.app.App;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Main {
	public static void main(String[] args) {
		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.FATAL);
		BasicConfigurator.configure();
		System.out.println("Hello Project1");
		App myApp = new App();
		myApp.run();
	}
}
