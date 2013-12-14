package net.beards.client;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.DefaultResourcePack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class ClientPacketHandler implements IPacketHandler
{

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
	{
		EntityClientPlayerMP playerCMP = (EntityClientPlayerMP) player;
		if (playerCMP.getEntityData() != null)
		{
			if (packet.data.length == 2)
			{
				NBTTagCompound tag = playerCMP.getEntityData();
				tag.setInteger("BeardGrowth", packet.data[0]);
				tag.setInteger("BeardStage", packet.data[1]);
			}
			else if (packet.data.length == 3)
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
								tag.setInteger("BeardStage", beardSize);
							}
						}
					}
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			else if (packet.data.length == 14) // for when a player confirms a beard color change
			{
				ByteArrayInputStream input = new ByteArrayInputStream(packet.data);
				DataInputStream stream = new DataInputStream(input);
				try
				{
					String beardName = stream.readUTF();
					float r = stream.readFloat();
					float g = stream.readFloat();
					float b = stream.readFloat();

					NBTTagCompound tag = playerCMP.getEntityData();
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
