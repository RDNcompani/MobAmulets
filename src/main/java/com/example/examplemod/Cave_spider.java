package com.example.examplemod;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;

import java.util.List;

import static com.example.examplemod.ExampleMod.CAVE_SPIDER;

public class Cave_spider extends Spider1 {
    public Cave_spider(){
        setTextureName("examplemod:cave_spider");
        setUnlocalizedName("амулет пещерного паука");
        setCreativeTab(CreativeTabs.tabMaterials);

    }
    @Override
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
        super.addInformation(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
        p_77624_3_.add("А также даёт защиту от яда");

    }

    @SubscribeEvent
    public void damageFromPoisen(TickEvent.PlayerTickEvent event) {
        if (event.player.isPotionActive(Potion.poison) && event.player.inventory.hasItem(CAVE_SPIDER)){
            event.player.removePotionEffect(Potion.poison.id);
        }
    }
}
