package net.beards.client;

import net.beards.CommonProxy;
import net.beards.client.model.ModelBeard;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.EventBus;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy
{

	@Override
	public void init()
	{
		super.init();
		MinecraftForge.EVENT_BUS.register(new ClientEvent());
	}

}
