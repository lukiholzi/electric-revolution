package com.teamtsla.electricrevolution.init;


import com.teamtsla.electricrevolution.ElectricRevolutionMod;
import com.teamtsla.electricrevolution.blocks.CopperBlock;
import com.teamtsla.electricrevolution.blocks.CopperOre;
import com.teamtsla.electricrevolution.items.CopperIngot;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber(modid = ElectricRevolutionMod.MODID)
public class ModInit {

    //Items
    public static Item COPPER_INGOT;
    //Blocks
    public static Block COPPER_ORE ;
    public static Block COPPER_BLOCK ;

    public static void init() {
        //Items
        COPPER_INGOT = new CopperIngot("copperingot");
        //Blocks
        COPPER_ORE = new CopperOre("copperore");
        COPPER_BLOCK = new CopperBlock("copperblock");
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(COPPER_INGOT);
        event.getRegistry().register(new ItemBlock(COPPER_ORE).setRegistryName(COPPER_ORE.getRegistryName()));
        event.getRegistry().register(new ItemBlock(COPPER_BLOCK).setRegistryName(COPPER_BLOCK.getRegistryName()));
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(COPPER_ORE);
        event.getRegistry().registerAll(COPPER_BLOCK);

    }

    @SubscribeEvent
    public static void registerRenderers(ModelRegistryEvent event) {

        loadItems();
        loadBlocks();
        loadCraftingRecepies();
        loadSmeltingRecepies();

    }

    private static void registerRenderer(Item item) {
        ModelLoader.setCustomModelResourceLocation(item,0,new ModelResourceLocation(item.getRegistryName(),"inventory"));
    }

    private static void loadBlocks() {
        registerRenderer(Item.getItemFromBlock(COPPER_ORE));
        registerRenderer(Item.getItemFromBlock(COPPER_BLOCK));
    }

    private static void loadItems() {
        registerRenderer(COPPER_INGOT);
    }

    private static void loadCraftingRecepies() {
        GameRegistry.addShapedRecipe(new ResourceLocation("CopperBlockCrafting"),
                null, new ItemStack(COPPER_BLOCK),
                "XXX",
                "XXX",
                "XXX",
                'X', ModInit.COPPER_INGOT);
    }

    private static void loadSmeltingRecepies() {
        GameRegistry.addSmelting(new ItemStack(ModInit.COPPER_ORE),new ItemStack(ModInit.COPPER_INGOT),1f);
    }

}