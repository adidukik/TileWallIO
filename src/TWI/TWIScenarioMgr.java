package TWI;

import TWI.scenario.TWIDefaultScenario;
import TWI.scenario.TWIDrawScenario;
import TWI.scenario.TWIPreviewScenario;
import x.XScenarioMgr;

public class TWIScenarioMgr extends XScenarioMgr {
    // constructor
    public TWIScenarioMgr(TWI twi) {
        super(twi);
    }

    // methods
    @Override
    protected void addScenarios() {
        this.addScenario(TWIDefaultScenario.createSingleton(this.mApp));
        this.addScenario(TWIDrawScenario.createSingleton(this.mApp));
        this.addScenario(TWIPreviewScenario.createSingleton(this.mApp));
    }

    @Override
    protected void setInitCurScene() {
        this.setCurScene(TWIDefaultScenario.ReadyScene.getSingleton());
    }

}