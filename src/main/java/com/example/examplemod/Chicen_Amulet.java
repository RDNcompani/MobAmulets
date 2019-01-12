package com.example.examplemod;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.List;

public class Chicen_Amulet extends Item {
    public Chicen_Amulet(){
        setTextureName("examplemod:Chicen_Amulet");
        setUnlocalizedName("куринный амулет");
        setCreativeTab(CreativeTabs.tabMaterials);
    }

    @Override
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
        p_77624_3_.add("Вы не получаете урон от падения") ;
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
