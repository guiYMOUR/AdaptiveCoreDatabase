package ACD;

import ACD.main.ADCMain;
import arc.Core;
import mindustry.mod.*;

import static mindustry.Vars.mods;

public class ACD extends Mod{

    public ACD() {

    }

    @Override
    public void init() {
        ADCMain.init();
    }
}
