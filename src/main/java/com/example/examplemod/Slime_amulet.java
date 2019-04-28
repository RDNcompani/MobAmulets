package com.example.examplemod;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;

public class Slime_amulet extends Item {
    public Slime_amulet() {
        setTextureName("examplemod:Slime_amulet");
        setUnlocalizedName("Aмулет Слайма");
        setCreativeTab(CreativeTabs.tabMaterials);
    }

    @SubscribeEvent
    public void jumpbust(LivingEvent.LivingJumpEvent event) {
        if (event.entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.entityLiving;

            if (player.inventory.hasItem(ExampleMod.SLIME_AMULET)) { // Проверяем, если ли у игрока амулет
                event.entityLiving.motionY += 0.5; // Увеличиваем прыжок
            }
        }
    }

    @SubscribeEvent
    public void fall(LivingFallEvent event) {
        if (event.entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.entityLiving;

            if (player.inventory.hasItem(ExampleMod.SLIME_AMULET)) {
                event.distance -= 2; // Раз увеличиваем прыжок, то и увеличиваем максимальную дистанцию падения, которая не приводит к повреждению
            }


        }

    }
}