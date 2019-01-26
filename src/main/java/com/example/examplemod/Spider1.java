package com.example.examplemod;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

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

        if (event.player.inventory.hasItem(ExampleMod.SPIDER_AMULET) || event.player.inventory.hasItem(ExampleMod.CAVE_SPIDER)) {
            ItemStack itemStack;
            int i = 0;
            while ( i < event.player.inventory.getSizeInventory()) {
                    itemStack = event.player.inventory.getStackInSlot(i);
                    if (itemStack != null && (itemStack.getItem() == ExampleMod.SPIDER_AMULET || itemStack.getItem() == ExampleMod.CAVE_SPIDER)){
                        //нашли амулет


                        long metka;

                        if (itemStack.hasTagCompound()) {
                            metka = itemStack.getTagCompound().getLong("metka");
                        } else {
                            if (event.player.isCollidedVertically) {
                                metka = System.currentTimeMillis();
                                writeMetka(itemStack);
                            } else return;
                        }

                        long metkaEnd = metka + 5000;
                        long metkaCurrent = System.currentTimeMillis();

                        if (metkaCurrent > metkaEnd) {
                            // Анулируем метку
                            // TODO: !!!
                            itemStack.setTagCompound(null);

                        } else {
                            event.player.motionY = 0.5;
                        }


                    }

                   i++;
            }
        }
    }


    void writeMetka(ItemStack itemStack) {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setLong("metka",System.currentTimeMillis() );
        itemStack.setTagCompound(nbt);
    }
}
