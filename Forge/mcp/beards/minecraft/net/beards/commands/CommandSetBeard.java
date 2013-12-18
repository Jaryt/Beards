package net.beards.commands;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

import net.beards.beard.Beard;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatMessageComponent;
import cpw.mods.fml.common.network.PacketDispatcher;

public class CommandSetBeard extends CommandBase implements ICommand
{

	@Override
	public int compareTo(Object arg0)
	{
		return 0;
	}

	@Override
	public String getCommandName()
	{
		return "setbeardsize";
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender)
	{
		return "/setbeardsize size";
	}

	@Override
	public List getCommandAliases()
	{
		return null;
	}

	@Override
	public void processCommand(ICommandSender icommandsender, String[] astring)
	{
		int newBeardSize = Integer.parseInt(astring[1]);
		EntityPlayer player = icommandsender.getEntityWorld().getPlayerEntityByName(astring[0]);
		if (astring.length > 1 && newBeardSize > 0 && player != null)
		{
			NBTTagCompound tag = player.getEntityData();
			if (tag != null)
			{
				if (newBeardSize <= Beard.getBeardFromId(tag.getInteger("BeardType")).maxSize)
				{
					tag.setInteger("BeardGrowth", newBeardSize);
					ByteArrayOutputStream bOut = new ByteArrayOutputStream();
					DataOutputStream dOut = new DataOutputStream(bOut);
					try
					{
						dOut.writeInt(0);
						dOut.writeInt(newBeardSize);
						dOut.writeInt(tag.getInteger("BeardStage"));
						dOut.writeUTF(player.username);
						PacketDispatcher.sendPacketToAllPlayers(new Packet250CustomPayload("beards", bOut.toByteArray()));
					}
					catch (IOException ex)
					{
						ex.printStackTrace();
					}
				}
			}
			else
			{
			}
		}
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender icommandsender)
	{
		return icommandsender.getEntityWorld().getPlayerEntityByName(icommandsender.getCommandSenderName()).capabilities.isCreativeMode;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender icommandsender, String[] astring)
	{
		if (astring.length > 1)
		{
			EntityPlayer player = icommandsender.getEntityWorld().getPlayerEntityByName(astring[0]);
			NBTTagCompound tag = player.getEntityData();
			if (tag != null)
			{
				Beard beard = Beard.getBeardFromId(tag.getInteger("BeardStyle"));
				String[] bstring = new String[beard.maxSize];
				if (beard != null)
					for (int i = 0; i < beard.maxSize; i++)
					{
						bstring[i] = String.valueOf(i);
					}
				return (astring.length == 2 && bstring != null ? getListOfStringsMatchingLastWord(astring, bstring) : null);
			}
		}
		return (astring.length == 1 ? getListOfStringsMatchingLastWord(astring, MinecraftServer.getServer().getAllUsernames()) : null);
	}

	@Override
	public boolean isUsernameIndex(String[] astring, int i)
	{
		return i == 0;
	}

}
