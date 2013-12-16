package net.beards;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

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
							if (entityTag.getInteger("BeardGrowth") < 15)
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
					PacketDispatcher.sendPacketToAllPlayers(new Packet250CustomPayload("beards", data.toByteArray()));
					shouldSendUpdate = false;
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

}
