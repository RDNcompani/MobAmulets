package com.example.examplemod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class Amulet_ghosta extends Item {
    public Amulet_ghosta() {
        setTextureName("examplemod:amulet_ghosta");
        setUnlocalizedName("амулет гаста");
        setCreativeTab(CreativeTabs.tabMaterials);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer player) {
        if (!p_77659_2_.isRemote) {
            EntityFireball fireball = new EntityLargeFireball(player.worldObj);

            //Задаём скорость и направление движения фаербола взамисимости от направления куда смотрит игрок
            fireball.setLocationAndAngles(player.posX, player.posY + (double) player.getEyeHeight(), player.posZ, player.rotationYaw, player.rotationPitch);
            fireball.setPosition(fireball.posX, fireball.posY, fireball.posZ);
            fireball.yOffset = 0.0F;
            float f = 4F; //скорость фаербола
            fireball.motionX = (double) (-MathHelper.sin(fireball.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(fireball.rotationPitch / 180.0F * (float) Math.PI) * f);
            fireball.motionZ = (double) (MathHelper.cos(fireball.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(fireball.rotationPitch / 180.0F * (float) Math.PI) * f);
            fireball.motionY = (double) (-MathHelper.sin(fireball.rotationPitch / 180.0F * (float) Math.PI) * f);

            fireball.posY += 2;//задаём точку спавна фаербола чуть выше игрока чтобы фаербол не взорвался в игроке
            fireball.worldObj.spawnEntityInWorld(fireball);
        }
        return p_77659_1_;
    }
}
