package net.beards;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.beards.beard.Beard;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
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
		//		for the fo manchu xD
		//		if (event.entityLiving.swingProgress > 0)
		//		{
		//			event.entityLiving.swingProgress -= 0.4f;
		//		}
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
