package ACD.main;

import arc.files.Fi;
import arc.struct.Seq;
import arc.util.Log;
import arc.util.Nullable;
import arc.util.Strings;
import arc.util.serialization.Json;
import arc.util.serialization.Jval;
import mindustry.Vars;
import mindustry.content.Liquids;
import mindustry.content.Planets;
import mindustry.mod.Mods;
import mindustry.type.Item;
import mindustry.type.Liquid;
import mindustry.type.Planet;
import mindustry.type.UnitType;
import mindustry.world.Block;

import java.util.Arrays;
import java.util.Locale;

import static mindustry.Vars.maxModSubtitleLength;

public class ADCMain {
    private static final Json json = new Json();

    public static String ADCRoot = "adc.json";
    public static void init(){
        boolean load = false;
        for(var mod : Vars.mods.list()){
            if(mod != null && mod.meta != null){
                if(!mod.enabled()) continue;
                String name  = mod.meta.name;
                Fi metaFile = null;
                if(mod.root.child(ADCRoot).exists()){
                    metaFile = mod.root.child(ADCRoot);
                }
                if(metaFile == null) {
                    Log.info("mod " + name + " has no adc.json, skip...");
                    continue;
                }
                Log.info("mod " + name + " load adc.json...");
                adcMeta meta = json.fromJson(adcMeta.class, Jval.read(metaFile.readString()).toString(Jval.Jformat.plain));
                if(meta.root.length <= 0) continue;
                for(adcRoot root : meta.root){
                    if(root.planet == null){
                        Log.err("where is you planet?");
                        continue;
                    }
                    Planet planet = Vars.content.planet(root.planet);
                    if(planet == null) {
                        Log.err("Planet " + root.planet + " is null.");
                        continue;
                    }
                    if(root.items.length > 0) {
                        for (String s : root.items) {
                            Item item = Vars.content.item(s);
                            if (item == null) {
                                Log.err("Item " + s + " is null.");
                                continue;
                            }
                            item.shownPlanets.add(planet);
                            item.postInit();
                            load = true;
                        }
                    }
                    if(root.liquids.length > 0) {
                        for (String s : root.liquids) {
                            Liquid liquid = Vars.content.liquid(s);
                            if (liquid == null) {
                                Log.err("liquid " + s + " is null.");
                                continue;
                            }
                            liquid.shownPlanets.add(planet);
                            liquid.postInit();
                            load = true;
                        }
                    }
                    if(root.units.length > 0) {
                        for (String s : root.units) {
                            UnitType unit = Vars.content.unit(s);
                            if (unit == null) {
                                Log.err("Unit " + s + " is null.");
                                continue;
                            }
                            unit.shownPlanets.add(planet);
                            unit.postInit();
                            load = true;
                        }
                    }
                }
            }
        }
        if(!load) return;
        Log.info("last progress...");
        for(Block b : Vars.content.blocks()){
            if(b.requirements.length == 0) b.shownPlanets.addAll(Vars.content.planets().copy().removeAll(p -> p == Planets.sun));
            else b.shownPlanets.clear();
            b.postInit();
        }
    }

    private static class adcMeta {
        public adcRoot[] root;

        @Override
        public String toString() {
            return "adcMeta{" +
                    "root=" + Arrays.toString(root) +
                    '}';
        }
    }

    private static class adcRoot {
        public String planet;
        public String[] items;
        public String[] liquids;
        public String[] units;

        @Override
        public String toString() {
            return "adcRoot{" +
                    "planet='" + planet + '\'' +
                    ", items=" + Arrays.toString(items) +
                    '}';
        }
    }
}
