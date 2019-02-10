package com.example.examplemod;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Sprute_Amulet extends Item {
    public Sprute_Amulet() {
        setTextureName("examplemod:amylet_spruta");
        setUnlocalizedName("Амулет спрута");
        setCreativeTab(CreativeTabs.tabMaterials);
    }

    @SubscribeEvent
    public void sprut(TickEvent.PlayerTickEvent event) {
        if (!event.player.isInWater()) return;

        // TODO: Мне все равно не нравится поиск в инвентаре при каждом тике. Вот бы проверять наличие амулета без перешаривания.
        ItemStack[] staki = event.player.inventory.mainInventory;
        int i = 0;
        while (i < staki.length) { // TODO: У данила есть функция поиска. Заменить этот цикл на нее в дальнейшем
            ItemStack stak = staki[i];
            if (stak != null) {
                if (stak.getItem() instanceof Sprute_Amulet) {
                    event.player.motionX *= 1.075;
                    event.player.motionZ *= 1.075;
                    event.player.motionY *= 1.15;
                }
            }
            i++;
        }
    }
}
