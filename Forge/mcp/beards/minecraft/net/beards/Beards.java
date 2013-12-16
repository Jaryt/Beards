package net.beards;

import net.beards.beard.Beard;
import net.beards.client.ClientPacketHandler;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.registry.GameRegistry;

@NetworkMod(clientSideRequired = true, serverSideRequired = true, clientPacketHandlerSpec = @SidedPacketHandler(channels = {"beards"}, packetHandler = ClientPacketHandler.class), serverPacketHandlerSpec = @SidedPacketHandler(channels = {"beards"}, packetHandler = ServerPacketHandler.class))
@Mod(modid = "beards", name = "Beards", version = "0.1")
public class Beards
{

	@SidedProxy(modId = "beards", clientSide = "net.beards.client.ClientProxy", serverSide = "net.beards.CommonProxy")
	public static CommonProxy proxy;

	@Instance (value = "beards")
	public static Beards beards; 
	
	@EventHandler
	public void load(FMLInitializationEvent event)
	{
		Beard.createBeards();
		proxy.init();
		GameRegistry.registerPlayerTracker(new PlayerTracker());
	}

}
