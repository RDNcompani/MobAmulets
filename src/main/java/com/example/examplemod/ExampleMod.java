package com.example.examplemod;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = ExampleMod.MODID, version = ExampleMod.VERSION)
public class ExampleMod {
    public static final String MODID = "examplemod";
    public static final String VERSION = "1.0";
    public static final Spider1 SPIDER_AMULET = new Spider1();
    public static final Amulet AMULET = new Amulet();
    public static final СatAmulet CAT_AMULET = new СatAmulet();
    public static final Zombie_AMULET ZOMBIE_AMULET = new Zombie_AMULET();
    public static final Chicen_Amulet CHUCEN_AMULET = new Chicen_Amulet();
    public static final Cave_spider CAVE_SPIDER = new Cave_spider();
    public static final Sprute_Amulet SPRUTE_AMULET = new Sprute_Amulet();

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(AMULET);
        MinecraftForge.EVENT_BUS.register(CAT_AMULET);
        MinecraftForge.EVENT_BUS.register(ZOMBIE_AMULET);
        MinecraftForge.EVENT_BUS.register(CHUCEN_AMULET);
        FMLCommonHandler.instance().bus().register(CAVE_SPIDER);
        FMLCommonHandler.instance().bus().register(SPIDER_AMULET);
        FMLCommonHandler.instance().bus().register(SPRUTE_AMULET);
        Object[] object1 = {"HKH","GAG","HKH", 'H', Items.string,'K',Items.leather,'G',Items.iron_ingot,'A',Items.diamond };
        ItemStack item1 = new ItemStack (AMULET);
        Object[] object2 = {"CCC","CAC","CCC", 'C', Items.fish,'A', AMULET };
        ItemStack item2 = new ItemStack(CAT_AMULET);
        Object[] object3 = {"GZG","ZAZ","GZG",'G',Items.iron_ingot,'Z',Items.rotten_flesh,'A',AMULET};
        ItemStack item3 = new ItemStack(ZOMBIE_AMULET);
        Object[] object4 = {"NPN","PAP","NPN",'N',Items.string,'P',Items.spider_eye,'A',Items.fire_charge};
        ItemStack item4 = new ItemStack(SPIDER_AMULET);
        Object[] object5 = {"OIO","IAI","OIO", 'O', Items.feather,'I',Items.gold_ingot,'A',AMULET};
        ItemStack item5 = new ItemStack (CHUCEN_AMULET);
        Object[] object6 = {"SSS","SAS","SSS",'S', new ItemStack(Items.dye, 1, 0),'A', new ItemStack(AMULET)};
        ItemStack item6 = new ItemStack (SPRUTE_AMULET);
        GameRegistry.addRecipe(item1,object1);
        GameRegistry.addRecipe(item2,object2);
        GameRegistry.addRecipe(item3,object3);
        GameRegistry.addRecipe(item4,object4);
        GameRegistry.addRecipe(item5,object5);
        GameRegistry.addRecipe(item6,object6);

        GameRegistry.registerItem(AMULET , "Амулет");
        GameRegistry.registerItem(CAT_AMULET , "Амулет Кошки");
        GameRegistry.registerItem(ZOMBIE_AMULET , "Амулет Зомби");
        GameRegistry.registerItem(CHUCEN_AMULET, "Амулет Курицы");
        GameRegistry.registerItem(SPIDER_AMULET, "Амулет Паука");
        GameRegistry.registerItem(CAVE_SPIDER , "Амулет Пещерного паука");
        GameRegistry.registerItem(SPRUTE_AMULET , "Амулет спрута");
    }



}