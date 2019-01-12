package com.example.examplemod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.List;

public class Cave_spider extends Spider1 {
    public Cave_spider(){
        setTextureName("examplemod:Spider_amulet1");
        setUnlocalizedName("амулет пещерного паука");

    }

    @Override
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
        super.addInformation(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
        p_77624_3_.add("А также даёт защиту от яда");

    }
}
