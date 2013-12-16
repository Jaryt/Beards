package net.beards.client;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Random;

import net.beards.Beards;
import net.beards.beard.Beard;
import net.beards.client.model.ModelBeardBase;
import net.beards.client.model.ModelLumberjackBeard;
import net.beards.client.model.ModelDwarfBeard;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.ForgeSubscribe;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.network.PacketDispatcher;

public class ClientEvent
{
	public ModelBeardBase beard = new ModelBeardBase();

	public ResourceLocation beardTexture = new ResourceLocation("beards", "beard2.png");

	@ForgeSubscribe
	public void onPlayerRender(RenderPlayerEvent.Post event)
	{
		NBTTagCompound tag = event.entityPlayer.getEntityData();
		if (tag != null)
		{
			Beard beardObj = Beard.getBeardFromId(tag.getInteger("BeardStyle"));
			if (beardObj != null)
			{
				beardTexture = new ResourceLocation("beards", beardObj.beardName.toLowerCase() + ".png");
				beard = beardObj.beardModel;
			}
			GL11.glPushMatrix();
			GL11.glRotatef(180, 0, 0, 1);
			GL11.glRotatef(180, 0, 1, 0);
			double par2 = Beards.proxy.clientTickHandler.renderTick;
			double d0 = event.entityPlayer.lastTickPosX + (event.entityPlayer.posX - event.entityPlayer.lastTickPosX) * (double) par2;
			double d1 = event.entityPlayer.lastTickPosY + (event.entityPlayer.posY - event.entityPlayer.lastTickPosY) * (double) par2;
			double d2 = event.entityPlayer.lastTickPosZ + (event.entityPlayer.posZ - event.entityPlayer.lastTickPosZ) * (double) par2;
			if (event.entityPlayer.equals(Minecraft.getMinecraft().thePlayer))
				GL11.glTranslated(1 * (d0 - RenderManager.renderPosX), 1 * (d1 - RenderManager.renderPosY), (1 * d2 - RenderManager.renderPosZ));
			else
			{
				GL11.glTranslated(1 * (d0 - RenderManager.renderPosX), -1 * (d1 - RenderManager.renderPosY) - 1.65f, -1 * (d2 - RenderManager.renderPosZ));
				if (event.entityPlayer.isSneaking())
					GL11.glTranslatef(0, 0.15f, 0);
			}
			GL11.glTranslatef(0.0f, 0.25f, 0.01f);
			if (event.entityPlayer.isSneaking())
				GL11.glTranslatef(0, 0.05f, 0);
			GL11.glTranslatef(0, -0.03f, 0);
			Color color = Color.getHSBColor(event.entityPlayer.ticksExisted % 20 / 20F, 1, new Random().nextFloat());
			if (tag.getString("BeardName").equals("jeb_"))
				GL11.glColor3f(color.getRed(), color.getGreen(), color.getBlue());
			else
				GL11.glColor3f(tag.getFloat("BeardRed"), tag.getFloat("BeardGreen"), tag.getFloat("BeardBlue"));
			if (event.entityPlayer.lastTickPosX != event.entityPlayer.posX || event.entityPlayer.lastTickPosZ != event.entityPlayer.posZ)
			{
				tag.setFloat("BeardShake", tag.getFloat("BeardShake") + (float) (tag.getInteger("BeardGrowth") / 3) / 15);
			}
			else if (tag.getFloat("BeardShake") > 0)
			{
				tag.setFloat("BeardShake", tag.getFloat("BeardShake") - 0.015f);
			}
			GL11.glRotatef(interpolateRotation(event.entityPlayer.prevRotationYawHead, event.entityPlayer.rotationYawHead, 1), 0, 1, 0);
			GL11.glRotatef(interpolateRotation(event.entityPlayer.prevRotationPitch, event.entityPlayer.rotationPitch, 1), 1, 0, 0);
			if (tag.getBoolean("disableLighting"))
				GL11.glDisable(GL11.GL_LIGHTING);
			Minecraft.getMinecraft().renderEngine.bindTexture(beardTexture);
			beard.render(event.entityPlayer, tag.getFloat("BeardShake"), 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, tag.getInteger("BeardGrowth"), tag.getInteger("BeardStage"));
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glPopMatrix();
			if (tag.getBoolean("BeardSeen") == false)
			{
				ByteArrayOutputStream outStream = new ByteArrayOutputStream();
				DataOutputStream dataOut = new DataOutputStream(outStream);

				try
				{
					dataOut.writeInt(1);
					dataOut.writeUTF(event.entityPlayer.username);
					PacketDispatcher.sendPacketToServer(new Packet250CustomPayload("beards", outStream.toByteArray()));
				}
				catch (IOException ex)
				{
					ex.printStackTrace();
				}
			}
		}
	}

	@ForgeSubscribe
	public void onFOVUpdate(FOVUpdateEvent event)
	{
	}

	private float interpolateRotation(float par1, float par2, float par3)
	{
		float f3;

		for (f3 = par2 - par1; f3 < -180.0F; f3 += 360.0F)
		{
			;
		}

		while (f3 >= 180.0F)
		{
			f3 -= 360.0F;
		}

		return par1 + par3 * f3;
	}

}
