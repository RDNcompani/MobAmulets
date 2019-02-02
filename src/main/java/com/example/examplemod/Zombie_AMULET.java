package com.example.examplemod;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;

import java.util.List;

public class Zombie_AMULET extends Item {
    @Override
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
        p_77624_3_.add("Позваляет есть гнилую плоть ");
        p_77624_3_.add("И не получать голод");
    }

    public Zombie_AMULET() {
        setTextureName("examplemod:zombee_amulet");
        setUnlocalizedName("зомби амулет");
        setCreativeTab(CreativeTabs.tabMaterials);

    }

    @SubscribeEvent
    public void zombee(PlayerUseItemEvent.Finish e) {

        // Запомни!
        // Потом заюзаешь в других амулетах
        ItemStack[] staki = e.entityPlayer.inventory.mainInventory; // Получаем стаки из инвентаря игрока
        int i = 0;
        while (i < staki.length) {
            ItemStack stak = staki[i];

            if (stak != null) {// СТАК НЕ ДОЛЖЕН БЫТЬ ПУСТЫМ! Это проверка гарантирует это
                if (stak.getItem() instanceof Zombie_AMULET) {
                    // Все! Нашли! stak содержит зомби амулет!

                    e.entityPlayer.removePotionEffect(Potion.hunger.id);

                }

            }
            i++;
        }

//        if (e.entityPlayer.inventory.hasItem(ExampleMod.CHUCEN_AMULET)) {
//
//        }


    }

}
