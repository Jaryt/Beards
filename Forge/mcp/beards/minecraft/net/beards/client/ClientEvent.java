package net.beards.client;

import java.awt.Color;
import java.util.Random;

import net.beards.client.model.ModelBeard;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
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
		if (tag != null && tag.hasKey("BeardGrowth"))
		{
			GL11.glPushMatrix();
			GL11.glRotatef(180, 0, 0, 1);
			GL11.glRotatef(180, 0, 1, 0);
			GL11.glTranslatef(0.0f, 0.25f, 0.01f);
			if (event.entityPlayer.isSneaking())
				GL11.glTranslatef(0, 0.05f, 0);
			GL11.glTranslatef(0, -0.03f, 0);
			//		Color color = Color.getHSBColor(event.entityPlayer.ticksExisted % 20 / 20F, 1, new Random().nextFloat());
			//		GL11.glColor3f(color.getRed(), color.getGreen(), color.getBlue());
//			GL11.glColor3f(0.2F, 0.1f, 0.0f);
			GL11.glColor3f(0.0F, 1.0f, 0.0f);
			GL11.glRotatef(interpolateRotation(event.entityPlayer.prevRotationYawHead, event.entityPlayer.rotationYawHead, 1), 0, 1, 0);
			GL11.glRotatef(interpolateRotation(event.entityPlayer.prevRotationPitch, event.entityPlayer.rotationPitch, 1), 1, 0, 0);
			Minecraft.getMinecraft().renderEngine.bindTexture(BEARD_TEXTURE);
			beard.render(event.entityPlayer, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, tag.getInteger("BeardGrowth"));
			GL11.glPopMatrix();
		}
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
