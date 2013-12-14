package net.beards.client;

import net.beards.CommonProxy;
import net.beards.client.keys.KeyEventBeard;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy
{

	@Override
	public void init()
	{
		super.init();
		clientTickHandler = new TickHandlerClient();
		TickRegistry.registerTickHandler(clientTickHandler, Side.CLIENT);
		MinecraftForge.EVENT_BUS.register(new ClientEvent());
		KeyBindingRegistry.registerKeyBinding(new KeyEventBeard(new KeyBinding[] { new KeyBinding("Beard Customizer", Keyboard.KEY_B) }, new boolean[] { false }));
	}

}
