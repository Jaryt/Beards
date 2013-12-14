package net.beards;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy
{

	public void init()
	{
		MinecraftForge.EVENT_BUS.register(new CommonEventHandler());
	}

}
