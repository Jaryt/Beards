package net.beards.client.keys;

import java.util.EnumSet;

import net.beards.client.gui.GuiScreenBeardCustomizer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;

public class KeyEventBeard extends KeyHandler
{

	public KeyEventBeard(KeyBinding[] keyBindings, boolean[] repeatings)
	{
		super(keyBindings, repeatings);
	}

	@Override
	public String getLabel()
	{
		return "Beards";
	}

	boolean repeat = false;

	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat)
	{
		if (!repeat)
		{
			if (Minecraft.getMinecraft().currentScreen == null)
				Minecraft.getMinecraft().displayGuiScreen(new GuiScreenBeardCustomizer());
			repeat = true;
		}
	}

	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd)
	{
		repeat = false;
	}

	@Override
	public EnumSet<TickType> ticks()
	{
		return EnumSet.of(TickType.PLAYER);
	}

}
