package net.beards;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import net.beards.beard.Beard;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import cpw.mods.fml.common.network.PacketDispatcher;

public class CommonEventHandler
{

	public CommonEventHandler()
	{
	}

	@ForgeSubscribe
	public void onLivingUpdateEvent(LivingUpdateEvent event)
	{
		NBTTagCompound entityTag = event.entityLiving.getEntityData();
		boolean shouldSendUpdate = false;
		if (!event.entityLiving.worldObj.isRemote && event.entityLiving instanceof EntityPlayer)
		{
			if (entityTag != null && (event.entityLiving.ticksExisted % 20) == 1)
			{
				if (entityTag.hasKey("BeardStage") && !event.entityLiving.isDead)
				{
					if (entityTag.getInteger("BeardStage") == 3)
					{
						if (entityTag.hasKey("BeardGrowth"))
						{
							if (entityTag.getInteger("BeardGrowth") < Beard.getBeardFromId(entityTag.getInteger("BeardStyle")).maxSize)
							{
								entityTag.setInteger("BeardGrowth", entityTag.getInteger("BeardGrowth") + 1);
								shouldSendUpdate = true;
							}
						}
						else
						{
							entityTag.setInteger("BeardGrowth", entityTag.getInteger("BeardGrowth"));
							shouldSendUpdate = true;
						}
						entityTag.setInteger("BeardStage", 0);
					}
					else
					{
						entityTag.setInteger("BeardStage", entityTag.getInteger("BeardStage") + 1);
						shouldSendUpdate = true;
					}
				}
				else
				{
					entityTag.setInteger("BeardStage", entityTag.getInteger("BeardStage"));
				}
			}
			if (shouldSendUpdate)
			{
				try
				{
					ByteArrayOutputStream data = new ByteArrayOutputStream();
					DataOutputStream outputStream = new DataOutputStream(data);
					outputStream.writeInt(0);
					outputStream.writeInt(entityTag.getInteger("BeardGrowth"));
					outputStream.writeInt(entityTag.getInteger("BeardStage"));
					outputStream.writeUTF(event.entityLiving.getEntityName());
					PacketDispatcher.sendPacketToAllPlayers(new Packet250CustomPayload("beards", data.toByteArray()));
					shouldSendUpdate = false;
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		Beard beard = Beard.getBeardFromId(entityTag.getInteger("BeardStyle"));
		if (beard != null && beard.id == Beard.fumanchu.id)
		{
			if (event.entityLiving.swingProgress > 0)
			{
				event.entityLiving.swingProgress -= 0.4f;
				event.entityLiving.isSwingInProgress = false;
			}
		}
	}

	@ForgeSubscribe
	public void onAttackEntityEvent(AttackEntityEvent event)
	{
		NBTTagCompound tag = null;
		tag = event.entityPlayer.getEntityData();
		if (tag != null)
		{
			Beard beard = Beard.getBeardFromId(tag.getInteger("BeardStyle"));

			if (beard.id == Beard.fumanchu.id)
			{
				System.out.println("damage");
				event.target.attackEntityFrom(DamageSource.causePlayerDamage(event.entityPlayer), 2);
			}
			if (beard.id == Beard.dwarf.id)
			{
				event.target.attackEntityFrom(DamageSource.causePlayerDamage(event.entityPlayer), 1);
			}
		}
	}

	@ForgeSubscribe
	public void onEntityJoinWorldEvent(EntityJoinWorldEvent event)
	{
		if (event.entity instanceof EntityPotion)
		{
			EntityPotion pot = (EntityPotion) event.entity;
			NBTTagCompound tag = null;
			if (pot.getThrower() instanceof EntityPlayer)
				if (((EntityPlayer) pot.getThrower()).getCurrentEquippedItem() != null)
				{
					tag = ((EntityPlayer) pot.getThrower()).getEntityData();
					if (tag != null)
					{
						Beard beard = Beard.getBeardFromId(tag.getInteger("BeardStyle"));

						if (beard.id == beard.wizard.id)
						{
							List list = Item.potion.getEffects(pot.getPotionDamage());
							Iterator iterator = list.iterator();

							while (iterator.hasNext())
							{
								PotionEffect potEffect = (PotionEffect) iterator.next();
								potEffect.combine(new PotionEffect(potEffect.getPotionID(), potEffect.getDuration(), potEffect.getAmplifier() + (tag.getInteger("BeardGrowth") / 2)));
							}
						}
					}
				}
		}
	}

	@ForgeSubscribe
	public void onDigEvent(PlayerEvent.BreakSpeed event)
	{
		NBTTagCompound tag = event.entityPlayer.getEntityData();
		if (tag != null)
		{
			Beard beard = Beard.getBeardFromId(tag.getInteger("BeardStyle"));

			Material bm = event.block.blockMaterial;
			if (beard.id == Beard.lumberjack.id && (bm == Material.wood || bm == Material.leaves))
			{
				if (event.entityLiving.swingProgress > 0)
				{
					event.entityLiving.swingProgress -= (tag.getInteger("BeardGrowth")) / 100;
				}
				event.newSpeed += (float) (tag.getInteger("BeardGrowth")) / 5;
			}
			if (beard.id == Beard.dwarf.id)
			{
				if (bm == Material.ground || bm == Material.snow || bm == Material.grass || bm == Material.clay || bm == Material.craftedSnow || bm == Material.sand)
				{
					if (event.entityLiving.swingProgress > 0)
					{
						event.entityLiving.swingProgress -= (tag.getInteger("BeardGrowth")) / 100;
					}
					event.newSpeed += (float) (tag.getInteger("BeardGrowth")) / 10;
				}
				if (bm == Material.rock || bm == Material.iron || bm == Material.ice)
				{
					if (event.entityLiving.swingProgress > 0)
					{
						event.entityLiving.swingProgress -= 0.4f;
					}
					event.newSpeed += (float) (tag.getInteger("BeardGrowth")) / 100;
				}
			}
		}
	}

}
