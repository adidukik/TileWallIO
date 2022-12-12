package TWI.scenario;

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
        this.addScenario(TWIStartScenario.createSingleton(this.mApp));
        this.addScenario(TWIDefaultScenario.createSingleton(this.mApp));
        this.addScenario(TWIDrawScenario.createSingleton(this.mApp));
        this.addScenario(TWIPreviewScenario.createSingleton(this.mApp));
    }

    @Override
    protected void setInitCurScene() {
        this.setCurScene(TWIDefaultScenario.ReadyScene.getSingleton());
        //later: this.setCurScene(TWIStartScenario.ReadyScene.getSingleton());
    }

}