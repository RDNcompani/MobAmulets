package com.example.examplemod;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.event.entity.living.LivingEvent;

import java.lang.reflect.Field;
import java.util.List;

public class СatAmulet extends Item {

    public СatAmulet() {
        setTextureName("examplemod:cat_amulet");
        setUnlocalizedName("кошачий омулет");
        setCreativeTab(CreativeTabs.tabMaterials);



    }

    @SubscribeEvent
    public void update(LivingEvent.LivingUpdateEvent event) {
        // Пытаемся выяснить, является ли ентити из евента - крипером
        EntityCreeper cr;
        if (event.entityLiving instanceof EntityCreeper) {
            cr = (EntityCreeper) event.entityLiving;
        } else {
            return; // Если entity - не крипер, то выходим.
        }


        // Создаем AI-таск убегания от игрока
        EntityAIAvoidEntity task = new EntityAIAvoidEntity(cr, EntityPlayer.class, 6.0F, 1.0D, 1.2D);

//        int i = 0;
//        while (i < cr.tasks.taskEntries.size()){
//            EntityAITasks.EntityAITaskEntry ai = (EntityAITasks.EntityAITaskEntry) cr.tasks.taskEntries.get(i);
//
//            if (ai.action instanceof EntityAINearestAttackableTarget) {
//                //cr.targetTasks
//            }
//
//            i++;
//        }


        // Проверяем, следует ли добавить задачу убегания от игрока
        if (dobavlyatTask(cr)) {
            // Добавляем к криперу задачу убегания от игрока
            cr.tasks.addTask(3, task);
            // Находим и удаляем атаковать игрока
            List taskEntries = cr.tasks.taskEntries; // TODO: Работает плохо
            for (int i1 = 0; i1 < taskEntries.size(); ) {
                Object taskEntry = taskEntries.get(i1);
                if (taskEntry instanceof EntityAIAttackOnCollide) { // Нашли!
                    cr.tasks.taskEntries.remove(i1); // Удаляем атакование врага
                } else {
                    i1++;
                }
            }
        }


    }

    //public void updateAmuletTasks

    public boolean dobavlyatTask(EntityMob mob) {
        for (int i = mob.tasks.taskEntries.size() - 1; i >= 0; i--) {
            EntityAITasks.EntityAITaskEntry ai = (EntityAITasks.EntityAITaskEntry) mob.tasks.taskEntries.get(i);

            if (ai.action instanceof EntityAIAvoidEntity) {
                try {
                    EntityAIAvoidEntity asd = (EntityAIAvoidEntity) ai.action;
                    Field targetEntityClassField = asd.getClass().getDeclaredField("targetEntityClass");
                    targetEntityClassField.setAccessible(true);
                    Field theEntityField = asd.getClass().getDeclaredField("theEntity");
                    theEntityField.setAccessible(true);

                    Class targetEntityClass = (Class) targetEntityClassField.get(asd);
                    EntityCreature theEntity = (EntityCreature) theEntityField.get(asd);

                    if (targetEntityClass == EntityPlayer.class && theEntity == mob) {
                        return false;
                    }
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return true;
    }


}
