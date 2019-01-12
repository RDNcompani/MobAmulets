package com.example.examplemod;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

public class Spider1 extends Item {
    public Spider1() {
        setTextureName("examplemod:spider_amulet");
        setUnlocalizedName("Паучий амулет");
        setCreativeTab(CreativeTabs.tabMaterials);
    }
    @Override
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
        p_77624_3_.add("Позволяет карабкаться 5 секунд");
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
