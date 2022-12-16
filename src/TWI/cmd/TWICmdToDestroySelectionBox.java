package TWI.cmd;

import TWI.scenario.TWISelectScenario;
import x.XApp;
import x.XLoggableCmd;

public class TWICmdToDestroySelectionBox extends XLoggableCmd {
    // private constructor
    private TWICmdToDestroySelectionBox(XApp app) {
        super(app);
    }

    public static boolean execute(XApp app) {
        TWICmdToDestroySelectionBox cmd = new TWICmdToDestroySelectionBox(app);
        return cmd.execute();
    }

    @Override
    protected boolean defineCmd() {
        TWISelectScenario.getSingleton().setSelectionBox(null);

        return true;
    }

    @Override
    protected String createLog() {
        StringBuffer sb = new StringBuffer();

        sb.append(this.getClass().getSimpleName());

        return sb.toString();
    }
}