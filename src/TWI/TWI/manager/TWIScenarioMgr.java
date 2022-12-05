package TWI.manager;

import TWI.TWI;
import x.XScenarioMgr;

public class TWIScenarioMgr extends XScenarioMgr {
    // constructor
    public TWIScenarioMgr(TWI twi) {
        super(twi);
    }

    // methods
    @Override
    protected void addScenarios() {
        // TODO: this.addScenario(XXX.createSingleton(this.mApp));
    }

    @Override
    protected void setInitCurScene() {
        // TODO: this.setCurScene(XXX.XXXScene.getSingleton());
    }

}