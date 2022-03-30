package it.unibo.radarSystem22.sprint2a.handlers;

import it.unibo.comm2022.ApplMsgHandler;
import it.unibo.comm2022.interfaces.IApplMsgHandler;
import it.unibo.comm2022.interfaces.Interaction2021;
import it.unibo.radarSystem22.domain.interfaces.ISonar;
import it.unibo.radarSystem22.domain.utils.ColorsOut;

public class SonarApplHandler extends ApplMsgHandler {

    private ISonar sonar;

    public SonarApplHandler(String name, ISonar sonar) {
        super(name);
        this.sonar = sonar;
    }

    public static IApplMsgHandler create(String name, ISonar sonar) {
        return new SonarApplHandler(name, sonar);
    }

    @Override
    public void elaborate(String message, Interaction2021 conn) {
        ColorsOut.out(name+ " | elaborate " + message + " conn=" + conn, ColorsOut.BLUE);

        if( message.equals("getDistance")  ) {
            String answer = ""+sonar.getDistance().getVal();
            sendMsgToClient( answer, conn );
        } else if( message.equals("isActive") ) {
            sendMsgToClient( ""+sonar.isActive(), conn );
        } else if( message.equals("activate") ) {
            sonar.activate();
        } else if( message.equals("dectivate") ) {
            sonar.deactivate();
        }
    }
}
