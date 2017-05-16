package prospector.shootingstar;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class ModelMethods {
    public static void registerItemModel(Item item) {
        setMRL(item, 0, item.getRegistryName(), "inventory");
    }

    public static void registerItemModel(Item item, int meta) {
        setMRL(item, meta, item.getRegistryName(), "inventory");
    }

    public static void registerItemModel(Item item, String path) {
        ResourceLocation loc = new ResourceLocation(item.getRegistryName().getResourceDomain(), path + "/" + item.getRegistryName().getResourcePath());
        setMRL(item, 0, loc, "inventory");
    }

    public static void registerItemModel(Item item, int meta, String path) {
        String slash = "";
        if (!path.isEmpty())
            slash = "/";
        ResourceLocation loc = new ResourceLocation(item.getRegistryName().getResourceDomain(), path + slash + item.getRegistryName().getResourcePath());
        setMRL(item, meta, loc, "inventory");
    }

    public static void registerBlockState(Item item, int meta, String path, String property, String variant) {
        registerBlockState(item, meta, path, property + "=" + variant);
    }

    public static void registerBlockState(Item item, int meta, String path, String variant) {
        ResourceLocation loc = new ResourceLocation(item.getRegistryName().getResourceDomain(), path + "/" + item.getRegistryName().getResourcePath());
        setMRL(item, meta, loc, variant);
    }

    public static void setMRL(Item item, int meta, ResourceLocation resourceLocation, String variant) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(resourceLocation, variant));
    }

    public static void setCustomStateMapper(Block block, IStateMapper mapper) {
        ModelLoader.setCustomStateMapper(block, mapper);
    }

    public static void setIgnoreStatesStateMapper(Block block, IProperty... properties) {
        ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(properties).build());
    }
}
