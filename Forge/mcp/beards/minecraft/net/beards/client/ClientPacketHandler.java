package net.beards.client;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class ClientPacketHandler implements IPacketHandler
{

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
	{
		EntityClientPlayerMP playerCMP = (EntityClientPlayerMP) player;
		if (playerCMP.getEntityData() != null && packet.data.length == 1)
		{
			NBTTagCompound tag = playerCMP.getEntityData();
			tag.setInteger("BeardGrowth", packet.data[0]);
		}
		else
		{
			ByteArrayInputStream byteStream = new ByteArrayInputStream(packet.data);
			DataInputStream stream = new DataInputStream(byteStream);
			try
			{
				int beardSize = stream.readInt();
				String playerName = stream.readUTF();
				for (Object playerObj : playerCMP.worldObj.playerEntities)
				{
					EntityPlayer entityPlayer = (EntityPlayer) playerObj;
					NBTTagCompound tag = entityPlayer.getEntityData();
					if (entityPlayer.username.equals(playerName))
					{
						if (tag != null)
						{
							tag.setInteger("BeardGrowth", beardSize);
						}
					}
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

}
