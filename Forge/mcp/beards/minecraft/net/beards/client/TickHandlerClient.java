package net.beards.client;

import java.util.EnumSet;

import net.beards.client.model.ModelBeard;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class TickHandlerClient implements ITickHandler
{

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData)
	{
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData)
	{

	}

	@Override
	public EnumSet<TickType> ticks()
	{
		return EnumSet.of(TickType.CLIENT);
	}

	@Override
	public String getLabel()
	{
		return "beard client";
	}

}
