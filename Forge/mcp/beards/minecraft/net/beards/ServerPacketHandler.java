package net.beards;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class ServerPacketHandler implements IPacketHandler
{

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
	{
		EntityPlayerMP playerMP = (EntityPlayerMP) player;
		ByteArrayInputStream input = new ByteArrayInputStream(packet.data);
		DataInputStream stream = new DataInputStream(input);
		NBTTagCompound tag = playerMP.getEntityData();
		ByteArrayOutputStream outByte = new ByteArrayOutputStream();
		DataOutputStream outStream = new DataOutputStream(outByte);
		try
		{
			if (playerMP != null)
			{
				switch (stream.readInt())
				{
				case 0:
					try
					{
						String beardName = stream.readUTF();
						float r = stream.readFloat();
						float g = stream.readFloat();
						float b = stream.readFloat();

						if (tag != null)
						{
							tag.setFloat("BeardRed", r);
							tag.setFloat("BeardGreen", g);
							tag.setFloat("BeardBlue", b);
							tag.setString("BeardName", beardName);
						}

						outStream.writeInt(2);
						outStream.writeUTF(playerMP.username);
						outStream.writeUTF(beardName);
						outStream.writeFloat(r);
						outStream.writeFloat(g);
						outStream.writeFloat(b);

						PacketDispatcher.sendPacketToAllPlayers(new Packet250CustomPayload("beards", outByte.toByteArray()));
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
					break;
				case 1:
					try
					{
						String playerName = stream.readUTF();
						EntityPlayer updatedPlayer = MinecraftServer.getServer().getEntityWorld().getPlayerEntityByName(playerName);
						if (updatedPlayer != null)
							tag = updatedPlayer.getEntityData();
						if (tag != null)
						{
							outStream.writeInt(3);
							outStream.writeUTF(playerName);
							outStream.writeInt(tag.getInteger("BeardGrowth"));
							outStream.writeInt(tag.getInteger("BeardSize"));
							outStream.writeUTF(tag.getString("BeardName"));
							outStream.writeFloat(tag.getFloat("BeardRed"));
							outStream.writeFloat(tag.getFloat("BeardGreen"));
							outStream.writeFloat(tag.getFloat("BeardBlue"));
							PacketDispatcher.sendPacketToPlayer(new Packet250CustomPayload("beards", outByte.toByteArray()), player);
						}
					}
					catch (IOException ex)
					{
						ex.printStackTrace();
					}
					break;
				}
			}
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}

}
