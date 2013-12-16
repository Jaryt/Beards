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
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class ClientPacketHandler implements IPacketHandler
{

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
	{
		EntityClientPlayerMP playerCMP = (EntityClientPlayerMP) player;
		if (playerCMP.getEntityData() != null)
		{
			NBTTagCompound tag = playerCMP.getEntityData();
			ByteArrayInputStream byteStream = new ByteArrayInputStream(packet.data);
			DataInputStream stream = new DataInputStream(byteStream);
			EntityPlayer updatedPlayer = null;
			try
			{
				int packetId = stream.readInt();
				switch (packetId)
				{
				case 0:
					int beardGrowth = stream.readInt();
					int beardStage = stream.readInt();
					if (playerCMP != null)
						updatedPlayer = playerCMP.worldObj.getPlayerEntityByName(stream.readUTF());
					if (updatedPlayer != null)
						tag = updatedPlayer.getEntityData();
					tag.setInteger("BeardGrowth", beardGrowth);
					tag.setInteger("BeardStage", beardStage);
					break;
				case 1:
					String beardName = stream.readUTF();
					float r = stream.readFloat();
					float g = stream.readFloat();
					float b = stream.readFloat();
					beardGrowth = stream.readInt();
					beardStage = stream.readInt();
					String playerName = stream.readUTF();
					if (playerCMP != null)
						updatedPlayer = playerCMP.worldObj.getPlayerEntityByName(playerName);
					if (updatedPlayer != null)
						tag = updatedPlayer.getEntityData();
					if (tag != null)
					{
						tag.setString("BeardName", beardName);
						tag.setFloat("BeardRed", r);
						tag.setFloat("BeardGreen", g);
						tag.setFloat("BeardBlue", b);
						tag.setInteger("BeardGrowth", beardGrowth);
						tag.setInteger("BeardStage", beardGrowth);
					}
					break;
				case 2:
					playerName = stream.readUTF();
					beardName = stream.readUTF();
					r = stream.readFloat();
					g = stream.readFloat();
					b = stream.readFloat();
					if (playerCMP != null)
						updatedPlayer = playerCMP.worldObj.getPlayerEntityByName(playerName);
					if (updatedPlayer != null)
						tag = updatedPlayer.getEntityData();
					if (tag != null)
					{
						tag.setFloat("BeardRed", r);
						tag.setFloat("BeardGreen", g);
						tag.setFloat("BeardBlue", b);
						tag.setString("BeardName", beardName);
					}
					break;
				case 3:
					playerName = stream.readUTF();
					beardStage = stream.readInt();
					beardGrowth = stream.readInt();
					beardName = stream.readUTF();
					r = stream.readFloat();
					g = stream.readFloat();
					b = stream.readFloat();
					if (playerCMP != null)
						updatedPlayer = playerCMP.worldObj.getPlayerEntityByName(playerName);
					if (updatedPlayer != null)
						tag = updatedPlayer.getEntityData();
					if (tag != null)
					{
						tag.setString("BeardName", beardName);
						tag.setFloat("BeardRed", r);
						tag.setFloat("BeardGreen", g);
						tag.setFloat("BeardBlue", b);
						tag.setInteger("BeardGrowth", beardGrowth);
						tag.setInteger("BeardStage", beardGrowth);
						tag.setBoolean("BeardSeen", true);
					}
					break;
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
