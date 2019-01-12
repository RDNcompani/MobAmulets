package com.example.examplemod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

public class Amulet extends Item {
    public Amulet() {
        setTextureName("examplemod:amulet");
        setUnlocalizedName("амулет");
        setCreativeTab(CreativeTabs.tabMaterials);
    }

    @Override
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
        p_77624_3_.add("Служит основой для амулетов");
    }

}
