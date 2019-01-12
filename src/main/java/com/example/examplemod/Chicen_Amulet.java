package com.example.examplemod;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class Chicen_Amulet extends Item {
    public Chicen_Amulet(){
        setTextureName("examplemod:Chicen_Amulet");
        setUnlocalizedName("куринный амулет");
        setCreativeTab(CreativeTabs.tabMaterials);
    }

    @SubscribeEvent
    public void ypal (LivingHurtEvent event){
        EntityPlayer entityPlayer;
        if (event.entityLiving instanceof EntityPlayer) {
            entityPlayer = (EntityPlayer) event.entityLiving;
        } else {
            return;
        }


        if (entityPlayer.inventory.hasItem(ExampleMod.CHUCEN_AMULET)) {
            if (event.source == DamageSource.fall) {

                if (event.isCancelable()) {
                    event.setCanceled(true);
                }

            }
      }


    }


}
