package com.example.examplemod;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.Vec3;

import java.util.List;

/**
 * Этот класс позволяет сущностям избегать игрока у которых есть определённый предмет
 */
public class EntityAIAvoidEntityPlayerWithItem extends EntityAIBase {
    public final IEntitySelector field_98218_a = new IEntitySelector()
    {
        private static final String __OBFID = "CL_00001575";
        /**
         * Return whether the specified entity is applicable to this filter.
         */
        public boolean isEntityApplicable(Entity p_82704_1_)
        {
            return p_82704_1_.isEntityAlive() && EntityAIAvoidEntityPlayerWithItem.this.theEntity.getEntitySenses().canSee(p_82704_1_);
        }
    };
    /** The entity we are attached to */
    private EntityCreature theEntity;
    private double farSpeed;
    private double nearSpeed;
    private Entity closestLivingEntity;
    private float distanceFromEntity;
    /** The PathEntity of our entity */
    private PathEntity entityPathEntity;
    /** The PathNavigate of our entity */
    private PathNavigate entityPathNavigate;
    /** The class of the entity we should avoid */
    private Class targetEntityClass;
    /** Если у игрока есть этот предмет, то {@link #theEntity} будет от него убегать */
    private ItemStack itemStack;
    private static final String __OBFID = "CL_00001574";

    public EntityAIAvoidEntityPlayerWithItem(ItemStack itemStack, Class p_i1616_2_, float p_i1616_3_, double p_i1616_4_, double p_i1616_6_, EntityCreature p_i1616_1_)
    {
        this.itemStack = itemStack;
        this.theEntity = p_i1616_1_;
        this.targetEntityClass = p_i1616_2_;
        this.distanceFromEntity = p_i1616_3_;
        this.farSpeed = p_i1616_4_;
        this.nearSpeed = p_i1616_6_;
        this.entityPathNavigate = p_i1616_1_.getNavigator();
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (this.targetEntityClass == EntityPlayer.class)
        {
            if (this.theEntity instanceof EntityTameable && ((EntityTameable)this.theEntity).isTamed())
            {
                return false;
            }

            this.closestLivingEntity = this.theEntity.worldObj.getClosestPlayerToEntity(this.theEntity, (double)this.distanceFromEntity);


            if (this.closestLivingEntity == null)
            {
                return false;
            } else {


                if (! hasItemStack((EntityPlayer) this.closestLivingEntity, this.itemStack) ) {
                    closestLivingEntity = null;
                    return false;
                }
            }
        }
        else
        {
            List list = this.theEntity.worldObj.selectEntitiesWithinAABB(this.targetEntityClass, this.theEntity.boundingBox.expand((double)this.distanceFromEntity, 3.0D, (double)this.distanceFromEntity), this.field_98218_a);

            if (list.isEmpty())
            {
                return false;
            }

            this.closestLivingEntity = (Entity)list.get(0);
        }

        Vec3 vec3 = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this.theEntity, 16, 7, Vec3.createVectorHelper(this.closestLivingEntity.posX, this.closestLivingEntity.posY, this.closestLivingEntity.posZ));

        if (vec3 == null)
        {
            return false;
        }
        else if (this.closestLivingEntity.getDistanceSq(vec3.xCoord, vec3.yCoord, vec3.zCoord) < this.closestLivingEntity.getDistanceSqToEntity(this.theEntity))
        {
            return false;
        }
        else
        {
            this.entityPathEntity = this.entityPathNavigate.getPathToXYZ(vec3.xCoord, vec3.yCoord, vec3.zCoord);
            return this.entityPathEntity == null ? false : this.entityPathEntity.isDestinationSame(vec3);
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return !this.entityPathNavigate.noPath();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.entityPathNavigate.setPath(this.entityPathEntity, this.farSpeed);
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        this.closestLivingEntity = null;
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        if (this.theEntity.getDistanceSqToEntity(this.closestLivingEntity) < 49.0D)
        {
            this.theEntity.getNavigator().setSpeed(this.nearSpeed);
        }
        else
        {
            this.theEntity.getNavigator().setSpeed(this.farSpeed);
        }
    }

    /**
     * Проверяет, есть ли в инвентаре игрока нужный стак. В отличии от
     * {@link EntityPlayer#inventory#hasItemStack(EntityPlayer)} проверяет еще и NBT на совпадение.
     * @param player Игрок, в инвентаре которого производитеся поиск
     * @param itemStack Запрос поиска
     * @return True, если такой же стак содержится в инвентаре игрока. Иначе - false.
     */
    public static boolean hasItemStack(EntityPlayer player, ItemStack itemStack) {
        int i = 0;
        while (i < player.inventory.getSizeInventory()) {
            ItemStack itemStack1 = player.inventory.getStackInSlot(i);

            if (ItemStack.areItemStacksEqual(itemStack1, itemStack)) {
                return true;
            }

            i++;
        }

        return false;
    }
}

