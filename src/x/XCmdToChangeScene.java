package x;

public class XCmdToChangeScene extends XLoggableCmd {
    // fields
    private XScene mFromScene = null;
    private XScene mToScene = null;
    private XScene mReturnScene = null;

    // private constructor
    private XCmdToChangeScene(XApp app, XScene toScene, XScene returnScene) {
        super(app);
        this.mFromScene = this.mApp.getScenarioMgr().getCurScene();
        this.mToScene = toScene;
        this.mReturnScene = returnScene;
    }

    // static method to construct and execute this command
    public static boolean execute(
        XApp app, XScene toScene, XScene returnScene
    ) {
        XCmdToChangeScene cmd =
            new XCmdToChangeScene(app, toScene, returnScene);
        return cmd.execute();
    }

    // methods
    @Override
    protected boolean defineCmd() {
        this.mToScene.setReturnScene(this.mReturnScene);
        this.mApp.getScenarioMgr().setCurScene(this.mToScene);
        return true;
    }

    @Override
    protected String createLog() {
        // add to log "XCmdToChangeScene fromScene toScene (returnScene)"
        StringBuffer sb = new StringBuffer();

        sb.append(this.getClass().getSimpleName());
        sb.append("\t");

        sb.append(this.mFromScene.getClass().getSimpleName());
        sb.append("\t");

        XScene toScene = this.mApp.getScenarioMgr().getCurScene();
        sb.append(toScene.getClass().getSimpleName());
        sb.append("\t");

        if (this.mReturnScene == null) {
            sb.append("null");
        } else {
            sb.append(this.mReturnScene.getClass().getSimpleName());
        }

        return sb.toString();
    }
}
