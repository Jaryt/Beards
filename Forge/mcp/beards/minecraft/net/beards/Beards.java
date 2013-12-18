package net.beards;

import net.beards.beard.Beard;
import net.beards.client.ClientPacketHandler;
import net.beards.commands.CommandSetBeard;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;

@NetworkMod(clientSideRequired = true, serverSideRequired = true, clientPacketHandlerSpec = @SidedPacketHandler(channels = { "beards" }, packetHandler = ClientPacketHandler.class), serverPacketHandlerSpec = @SidedPacketHandler(channels = { "beards" }, packetHandler = ServerPacketHandler.class))
@Mod(modid = "beards", name = "Beards", version = "1.0")
public class Beards
{

	@SidedProxy(modId = "beards", clientSide = "net.beards.client.ClientProxy", serverSide = "net.beards.CommonProxy")
	public static CommonProxy proxy;

	@Instance(value = "beards")
	public static Beards beards;

	public static Configuration config;

	public static int globalBeardGrowTicks = 20000;

	@EventHandler
	public void load(FMLInitializationEvent event)
	{
		config.load();

		Beard.createBeards();
		proxy.init();

		config.save();
	}

	@EventHandler
	public void preInt(FMLPreInitializationEvent event)
	{
		config = new Configuration(event.getSuggestedConfigurationFile());

		globalBeardGrowTicks = config.get(Configuration.CATEGORY_GENERAL, "Player's Beard Growth Ticks", 20000).getInt(20000);
	}

	@EventHandler
	public void onServerStart(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new CommandSetBeard());
	}

}
