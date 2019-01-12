package com.example.examplemod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class Amulet extends Item {
    public Amulet() {
        setTextureName("examplemod:amulet");
        setUnlocalizedName("амулет");
        setCreativeTab(CreativeTabs.tabMaterials);
    }

    //PlayerUseItemEvent.Finish
}
