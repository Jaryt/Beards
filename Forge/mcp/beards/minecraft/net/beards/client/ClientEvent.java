package net.beards.client;

import java.awt.Color;
import java.util.Random;

import net.beards.Beards;
import net.beards.client.model.ModelBeard;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.ForgeSubscribe;

import org.lwjgl.opengl.GL11;

public class ClientEvent
{
	public ModelBeard beard = new ModelBeard();

	public static final ResourceLocation BEARD_TEXTURE = new ResourceLocation("beards", "beard.png");

	@ForgeSubscribe
	public void onPlayerRender(RenderPlayerEvent.Post event)
	{
		NBTTagCompound tag = event.entityPlayer.getEntityData();
		if (tag != null)
		{
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
				GL11.glTranslated(1 * (d0 - RenderManager.renderPosX), -1 * (d1 - RenderManager.renderPosY) - 1.65f, -1 * (d2 - RenderManager.renderPosZ));
			GL11.glTranslatef(0.0f, 0.25f, 0.01f);
			if (event.entityPlayer.isSneaking())
				GL11.glTranslatef(0, 0.05f, 0);
			GL11.glTranslatef(0, -0.03f, 0);
			Color color = Color.getHSBColor(event.entityPlayer.ticksExisted % 20 / 20F, 1, new Random().nextFloat());
			if (tag.getString("BeardName").equals("jeb_"))
				GL11.glColor3f(color.getRed(), color.getGreen(), color.getBlue());
			else
				GL11.glColor3f(tag.getFloat("BeardRed"), tag.getFloat("BeardGreen"), tag.getFloat("BeardBlue"));
			GL11.glRotatef(interpolateRotation(event.entityPlayer.prevRotationYawHead, event.entityPlayer.rotationYawHead, 1), 0, 1, 0);
			GL11.glRotatef(interpolateRotation(event.entityPlayer.prevRotationPitch, event.entityPlayer.rotationPitch, 1), 1, 0, 0);
			GL11.glEnable(GL11.GL_LIGHTING);
			Minecraft.getMinecraft().renderEngine.bindTexture(BEARD_TEXTURE);
			beard.render(event.entityPlayer, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, tag.getInteger("BeardGrowth"), tag.getInteger("BeardStage"));
			GL11.glPopMatrix();
		}
	}

	@ForgeSubscribe
	public void onFOVUpdate(FOVUpdateEvent event)
	{
		if (!Minecraft.getMinecraft().gameSettings.keyBindDrop.pressed)
			event.newfov -= 0.7f;
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
