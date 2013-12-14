package net.beards.client.gui;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.beards.client.model.ModelBeard;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.common.network.PacketDispatcher;

public class GuiScreenBeardCustomizer extends GuiScreen
{
	public Minecraft mc;

	public static final ResourceLocation BACKGROUND = new ResourceLocation("beards", "/gui/beardCustomizer.png");

	public GuiTextField beardNameField;

	public int textMinX, textMinY;

	public float openR, openG, openB;

	public boolean isFinalized;

	public NBTTagCompound tag;

	public GuiScreenBeardCustomizer()
	{
		mc = Minecraft.getMinecraft();
		tag = mc.thePlayer.getEntityData();
		if (tag != null)
		{
			openR = tag.getFloat("BeardRed");
			openG = tag.getFloat("BeardGreen");
			openB = tag.getFloat("BeardBlue");
		}
	}

	@Override
	public void onGuiClosed()
	{
		super.onGuiClosed();
		if (!isFinalized)
		{
			if (tag != null)
			{
				tag.setFloat("BeardRed", openR);
				tag.setFloat("BeardGreen", openG);
				tag.setFloat("BeardBlue", openB);
			}
		}
	}

	@Override
	public void initGui()
	{
		super.initGui();
		float r = 1.0f;
		float g = 1.0f;
		float b = 1.0f;
		String beardName = "";
		if (tag != null)
		{
			r = tag.getFloat("BeardRed");
			g = tag.getFloat("BeardGreen");
			b = tag.getFloat("BeardBlue");
			beardName = tag.getString("BeardName");
		}
		buttonList.clear();
		this.buttonList.add(new GuiButton(1, this.width / 2 - 79, this.height / 6 + 110, 54, 20, "Finalize"));
		this.buttonList.add(new GuiRGBSlider(2, this.width / 2 - 20 + 0 % 2 * 160, this.height / 6 + 38 + 24 * (0 >> 1), "Red", r));
		this.buttonList.add(new GuiRGBSlider(3, this.width / 2 - 20 + 0 % 2 * 160, this.height / 6 + 38 + 22 + 24 * (0 >> 1), "Green", g));
		this.buttonList.add(new GuiRGBSlider(4, this.width / 2 - 20 + 0 % 2 * 160, this.height / 6 + 38 + 44 + 24 * (0 >> 1), "Blue", b));
		textMinX = this.width / 2 - 20;
		textMinY = this.height / 6 + 110;
		beardNameField = new GuiTextField(mc.fontRenderer, textMinX, textMinY, 100, 20);
		beardNameField.setText(beardName);
	}

	@Override
	protected void actionPerformed(GuiButton par1GuiButton)
	{
		super.actionPerformed(par1GuiButton);
		switch (par1GuiButton.id)
		{
		case 1:
			String beardName = beardNameField.getText();
			float r = ((GuiRGBSlider) buttonList.get(1)).sliderValue;
			float g = ((GuiRGBSlider) buttonList.get(2)).sliderValue;
			float b = ((GuiRGBSlider) buttonList.get(3)).sliderValue;

			ByteArrayOutputStream outstream = new ByteArrayOutputStream();
			DataOutputStream output = new DataOutputStream(outstream);

			try
			{
				output.writeUTF(beardName);
				output.writeFloat(r);
				output.writeFloat(g);
				output.writeFloat(b);
				PacketDispatcher.sendPacketToAllPlayers(new Packet250CustomPayload("beards", outstream.toByteArray()));
				PacketDispatcher.sendPacketToServer(new Packet250CustomPayload("beards", outstream.toByteArray()));
				isFinalized = true;
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}

			break;
		}
	}

	@Override
	protected void keyTyped(char par1, int par2)
	{
		super.keyTyped(par1, par2);
		if (beardNameField.isFocused())
		{
			beardNameField.textboxKeyTyped(par1, par2);
		}
	}

	@Override
	protected void mouseClicked(int par1, int par2, int par3)
	{
		super.mouseClicked(par1, par2, par3);
		if (par1 > textMinX && par1 < textMinX + beardNameField.getWidth() && par2 > textMinY && par2 < textMinY + 20)
		{
			beardNameField.setFocused(true);
		}
		else
		{
			beardNameField.setFocused(false);
		}
	}

	public static void func_110423_a(int par0, int par1, int par2, float par3, float par4, EntityLivingBase par5EntityLivingBase)
	{
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) par0, (float) par1, 50.0F);
		GL11.glScalef((float) (-par2), (float) par2, (float) par2);
		GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
		float f2 = par5EntityLivingBase.renderYawOffset;
		float f3 = par5EntityLivingBase.rotationYaw;
		float f4 = par5EntityLivingBase.rotationPitch;
		float f5 = par5EntityLivingBase.prevRotationYawHead;
		float f6 = par5EntityLivingBase.rotationYawHead;
		GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
		RenderHelper.enableStandardItemLighting();
		GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-((float) Math.atan((double) (par4 / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
		par5EntityLivingBase.renderYawOffset = (float) Math.atan((double) (par3 / 40.0F)) * 20.0F;
		par5EntityLivingBase.rotationYaw = (float) Math.atan((double) (par3 / 40.0F)) * 40.0F;
		par5EntityLivingBase.rotationPitch = -((float) Math.atan((double) (par4 / 40.0F))) * 20.0F;
		par5EntityLivingBase.rotationYawHead = par5EntityLivingBase.rotationYaw;
		par5EntityLivingBase.prevRotationYawHead = par5EntityLivingBase.rotationYaw;
		GL11.glTranslatef(0.0F, par5EntityLivingBase.yOffset, 0.0F);
		RenderManager.instance.playerViewY = 180.0F;
		RenderManager.instance.renderEntityWithPosYaw(par5EntityLivingBase, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
		par5EntityLivingBase.renderYawOffset = f2;
		par5EntityLivingBase.rotationYaw = f3;
		par5EntityLivingBase.rotationPitch = f4;
		par5EntityLivingBase.prevRotationYawHead = f5;
		par5EntityLivingBase.rotationYawHead = f6;
		GL11.glPopMatrix();
		RenderHelper.disableStandardItemLighting();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
	}

	@Override
	public void drawScreen(int par1, int par2, float par3)
	{
		this.drawDefaultBackground();
		ModelBeard beard;
		beard = new ModelBeard();
		mc.renderEngine.bindTexture(BACKGROUND);
		int k = (this.width - 172) / 2;
		this.drawTexturedModalRect(k, 0, 0, -330, 172, this.height);
		this.beardNameField.drawTextBox();
		beardNameField.updateCursorCounter();
		super.drawScreen(par1, par2, par3);
		float r = ((GuiRGBSlider) buttonList.get(1)).sliderValue;
		float g = ((GuiRGBSlider) buttonList.get(2)).sliderValue;
		float b = ((GuiRGBSlider) buttonList.get(3)).sliderValue;
		if (tag != null)
		{
			tag.setFloat("BeardRed", r);
			tag.setFloat("BeardGreen", g);
			tag.setFloat("BeardBlue", b);
		}
		func_110423_a(width / 2 - 52, 150, 35, 0, 0, mc.thePlayer);
	}
}
