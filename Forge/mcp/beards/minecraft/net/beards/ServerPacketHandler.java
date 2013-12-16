package net.beards;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.beards.beard.Beard;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
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
					break;
				case 1:
					String playerName = stream.readUTF();
					EntityPlayer updatedPlayer = MinecraftServer.getServer().getEntityWorld().getPlayerEntityByName(playerName);
					if (updatedPlayer != null)
						tag = updatedPlayer.getEntityData();
					if (tag != null)
					{
						outStream.writeInt(3);
						outStream.writeUTF(playerName);
						outStream.writeInt(tag.getInteger("BeardGrowth"));
						outStream.writeInt(tag.getInteger("BeardStage"));
						outStream.writeUTF(tag.getString("BeardName"));
						outStream.writeFloat(tag.getFloat("BeardRed"));
						outStream.writeFloat(tag.getFloat("BeardGreen"));
						outStream.writeFloat(tag.getFloat("BeardBlue"));
						PacketDispatcher.sendPacketToPlayer(new Packet250CustomPayload("beards", outByte.toByteArray()), player);
					}
					break;
				case 2:
					tag = playerMP.getEntityData();
					if (tag != null)
					{
						int curBeardSize = tag.getInteger("BeardSize");
						int curBeardStyle = tag.getInteger("BeardStyle");
						int newBeardSize = stream.readInt();
						int newBeardStyle = stream.readInt();

						Beard curBeard = Beard.getBeardFromId(curBeardStyle);
						Beard newBeard = Beard.getBeardFromId(newBeardStyle);

						ItemStack item = playerMP.getCurrentEquippedItem();

						if (item != null && item.getItem() instanceof ItemShears)
						{
							if (!newBeard.equals(curBeard) && curBeardSize >= newBeard.minShaveSize)
							{

							}
							else
							{
								if (newBeardSize < curBeardSize)
								{

								}
							}
						}
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
