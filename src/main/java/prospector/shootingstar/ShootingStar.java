package prospector.shootingstar;


import java.util.ArrayList;
import java.util.List;

import static prospector.shootingstar.ModelMethods.registerItemModel;
import static prospector.shootingstar.ModelMethods.setIgnoreStatesStateMapper;

public class ShootingStar {
    protected static List<ModelInfo> modelList = new ArrayList<>();

    /**
     * Adds a block or item model to the "to be registered" list
     *
     * @param modelInfo
     */
    public static void registerModel(ModelInfo modelInfo) {
        modelList.add(modelInfo);
    }

    /**
     * Registers models for a given modid
     *
     * @param modid Sets what modid to register models for
     */
    public static void registerModels(String modid) {
        for (ModelInfo model : modelList) {
            if (model.getModid().equals(modid)) {
                registerItemModel(model.getItem(), model.getMeta(), model.getBlockStatePath());
                if (model.isBlock() && model.getIgnoreProperties().length > 0) {
                    setIgnoreStatesStateMapper(model.getBlock(), model.getIgnoreProperties());
                }
            }
        }
    }
}
