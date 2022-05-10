package unibo.Robots.cleaner;

import unibo.actor22.Qak22Context;
import unibo.actor22.annotations.Actor22;
import unibo.actor22.annotations.Context22;
import unibo.actor22comm.utils.CommSystemConfig;
import unibo.actor22comm.utils.CommUtils;



@Context22(name = "pcCtx", host = "localhost", port = "8083")
@Actor22(name = MainRobotCleaner.myName, contextName = "pcCtx", implement = RobotCleaner.class)
public class MainRobotCleaner {
	
	public static final String myName = "cleaner";
	
	public void doJob() {
		CommSystemConfig.tracing = false;
 		Qak22Context.configureTheSystem(this);
		Qak22Context.showActorNames();
   		//Qak22Util.sendAMsg( SystemData.startSysCmd("main",myName) );
	};

	public void terminate() {
		CommUtils.aboutThreads("Before end - ");		
		CommUtils.delay(60000); //Give time to work ...
		CommUtils.aboutThreads("At exit - ");		
		System.exit(0);
	}
	
	public static void main( String[] args) throws Exception {
		CommUtils.aboutThreads("Before start - ");
		MainRobotCleaner appl = new MainRobotCleaner( );
		appl.doJob();
		appl.terminate();
	}

}
