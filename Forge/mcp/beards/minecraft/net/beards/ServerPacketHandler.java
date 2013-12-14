package net.beards;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.google.common.io.ByteArrayDataOutput;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class ServerPacketHandler implements IPacketHandler
{

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
	{
		EntityPlayerMP playerMP = (EntityPlayerMP) player;
		if (playerMP != null)
		{
			if (packet.data.length == 14) // for when a player confirms a beard color change
			{
				ByteArrayInputStream input = new ByteArrayInputStream(packet.data);
				DataInputStream stream = new DataInputStream(input);

				try
				{
					String beardName = stream.readUTF();
					float r = stream.readFloat();
					float g = stream.readFloat();
					float b = stream.readFloat();

					NBTTagCompound tag = playerMP.getEntityData();
					if (tag != null)
					{
						tag.setFloat("BeardRed", r);
						tag.setFloat("BeardGreen", g);
						tag.setFloat("BeardBlue", b);
						tag.setString("BeardName", beardName);
					}
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

}
