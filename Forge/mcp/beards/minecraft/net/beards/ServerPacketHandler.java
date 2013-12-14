package net.beards;

import java.io.ByteArrayOutputStream;

import com.google.common.io.ByteArrayDataOutput;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class ServerPacketHandler implements IPacketHandler
{

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
	{
	}

}
