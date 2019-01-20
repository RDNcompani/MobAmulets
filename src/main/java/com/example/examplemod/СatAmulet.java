package com.example.examplemod;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

import java.util.List;

public class СatAmulet extends Item {

    public СatAmulet() {
        setTextureName("examplemod:cat_amulet");
        setUnlocalizedName("кошачий омулет");
        setCreativeTab(CreativeTabs.tabMaterials);
    }

    @Override
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
        p_77624_3_.add("Отпугивает от вас криперов");
    }


    @SubscribeEvent
    public void joinWorld(EntityJoinWorldEvent event) {
        if (event.entity instanceof EntitySlime && event.isCancelable()) event.setCanceled(true);


        EntityCreeper cr;
        if (event.entity instanceof EntityCreeper) {
            cr = (EntityCreeper) event.entity;
        } else {
            return; // Если entity - не крипер, то выходим.
        }

        // Создаем AI-таск убегания от игрока
        EntityAIAvoidEntityPlayerWithItem task = new EntityAIAvoidEntityPlayerWithItem(new ItemStack(ExampleMod.CAT_AMULET), EntityPlayer.class, 13.0F, 1.0D, 1.2D, cr);

        cr.tasks.addTask(2, task);
    }
}
