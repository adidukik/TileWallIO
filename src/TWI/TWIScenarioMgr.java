package TWI;

import TWI.scenario.TWIColorScenario;
import TWI.scenario.TWIDefaultScenario;
import TWI.scenario.TWIDrawScenario;
import TWI.scenario.TWIEditScenario;
import TWI.scenario.TWIPreviewScenario;
import TWI.scenario.TWISelectScenario;
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
        this.addScenario(TWISelectScenario.createSingleton(this.mApp));
        this.addScenario(TWIEditScenario.createSingleton(this.mApp));
        this.addScenario(TWIColorScenario.createSingleton(this.mApp));
    }

    @Override
    protected void setInitCurScene() {
        this.setCurScene(TWIDefaultScenario.ReadyScene.getSingleton());
    }

}