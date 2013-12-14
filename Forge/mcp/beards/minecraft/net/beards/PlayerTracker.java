package net.beards;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.IPlayerTracker;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class PlayerTracker implements IPlayerTracker
{

	@Override
	public void onPlayerLogin(EntityPlayer player)
	{
		if (!player.worldObj.isRemote)
		{
			for (Object playerObj : player.worldObj.playerEntities)
			{
				EntityPlayer entityPlayer = (EntityPlayer) playerObj;
				NBTTagCompound tag = entityPlayer.getEntityData();
				if (tag != null && tag.hasKey("BeardGrowth"))
				{
					ByteArrayOutputStream data = new ByteArrayOutputStream();
					DataOutputStream stream = new DataOutputStream(data);
					try
					{
						if (entityPlayer.username.equals("LexManos"))
						{
							entityPlayer.getEntityData().setInteger("BeardGrowth", 100);
							entityPlayer.getEntityData().setInteger("BeardStage", 3);
						}
						stream.writeInt(tag.getInteger("BeardGrowth"));
						stream.writeInt(tag.getInteger("BeardStage"));
						stream.writeUTF(entityPlayer.username);
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
					PacketDispatcher.sendPacketToPlayer(new Packet250CustomPayload("beards", data.toByteArray()), (Player) player);
				}
			}
		}
	}

	@Override
	public void onPlayerLogout(EntityPlayer player)
	{

	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player)
	{

	}

	@Override
	public void onPlayerRespawn(EntityPlayer player)
	{

	}

}
