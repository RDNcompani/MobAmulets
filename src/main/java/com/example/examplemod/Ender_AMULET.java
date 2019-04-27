package com.example.examplemod;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Ender_AMULET extends Item {
    public Ender_AMULET() {
        setTextureName("examplemod:Ender Amulet");
        setUnlocalizedName("Амулет Эндермена");
        setCreativeTab(CreativeTabs.tabMaterials);
    }

    @SubscribeEvent
    public void hurt(LivingHurtEvent event) {
        if (event.entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.entityLiving;
            if (player.inventory.hasItem(ExampleMod.ENDER_AMULET)) { // Проверяем, если ли у игрока амулет
                vibros(player);

            }
        }
    }

public void vibros(EntityPlayer player){
    HashMap<Integer, ItemStack> map= new HashMap<Integer, ItemStack>();
    for(int b=0 ; b<player.inventory.getSizeInventory();b++){
        ItemStack itemStack123 =player.inventory.getStackInSlot(b);
        if(itemStack123 != null){
            map.put(b, itemStack123);
        }
    }

    Random random = new Random();
    if (random.nextInt(100) < 25) {
        // Обертка для получения рандомных ключей из hasmap'а
        ArrayList<Integer> arrayList = new ArrayList<Integer>(map.keySet());
        int slotId = arrayList.get(random.nextInt(arrayList.size()));

        ItemStack itemStack1 = map.get(slotId);
        player.inventory.setInventorySlotContents(slotId, null);
        player.dropPlayerItemWithRandomChoice(itemStack1,true);

    }
}
}
