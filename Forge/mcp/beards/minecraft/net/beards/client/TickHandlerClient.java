package net.beards.client;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class TickHandlerClient implements ITickHandler
{

	public float renderTick;

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData)
	{
		if (Minecraft.getMinecraft().theWorld != null)
		{
			renderTick = (Float) tickData[0];
		}
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData)
	{

	}

	@Override
	public EnumSet<TickType> ticks()
	{
		return EnumSet.of(TickType.RENDER);
	}

	@Override
	public String getLabel()
	{
		return "Beards Client";
	}

}
