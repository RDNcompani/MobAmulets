package com.example.examplemod;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
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
            ItemStack itemStack = findItemStack(ExampleMod.SPIDER_AMULET, event.player.inventory);
            if (itemStack == null) itemStack = findItemStack(ExampleMod.CAVE_SPIDER, event.player.inventory);


            if (itemStack != null) {
                //нашли амулет
               updateMetka(itemStack,event);
            }
        }
    }

    /**
     * Находим амулеты.
     * @param f Нужный предмет.
     * @param iv Находим амулеты в инвентаре.
     * @return Возращает амулет.
     */
    static ItemStack findItemStack(Item f, InventoryPlayer iv) {//находим амулет
        int i = 0;
        while (i < iv.getSizeInventory()) {
            ItemStack itemStack;
            itemStack = iv.player.inventory.getStackInSlot(i);

            if (itemStack != null && itemStack.getItem() == f) {
                return itemStack;
            }
            i++;
        }

        return null;
    }
    /**
     *Записывает метку в амулеты.
     * @param itemStack скак с амулетом.
     */
    void writeMetka(ItemStack itemStack) {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setLong("metka", System.currentTimeMillis());
        itemStack.setTagCompound(nbt);
    }

    /**
     * Вычисляет, стоит ли придать игроку движение по Y. И если да, то дает игроку это движение.
     * @param itemStack стак с амулетом
     * @param event евент тика игрока
     */
    void updateMetka(ItemStack itemStack, TickEvent.PlayerTickEvent event) {
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
            itemStack.setTagCompound(null);

        } else {
            event.player.motionY = 0.5;
        }


    }
}