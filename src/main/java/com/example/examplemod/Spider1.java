package com.example.examplemod;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class Spider1 extends Item {
    public Spider1() {
        setTextureName("examplemod:spider_amulet");
        setUnlocalizedName("Паучий амулет");
        setCreativeTab(CreativeTabs.tabMaterials);
    }

    @SubscribeEvent
    public void climbWall(TickEvent.PlayerTickEvent event) {


        if (!event.player.isCollidedHorizontally) {
            return;
        }

        if (event.player.inventory.hasItem(ExampleMod.SPIDER_AMULET))
            event.player.motionY = 0.5;
    }


}
