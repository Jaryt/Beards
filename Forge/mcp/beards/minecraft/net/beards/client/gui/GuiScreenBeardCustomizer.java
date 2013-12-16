package net.beards.client.gui;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.beards.beard.Beard;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
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

	public static ResourceLocation shears;

	public GuiTextField beardNameField;

	public int textMinX, textMinY, openSize, openStyle;

	public float openR, openG, openB;

	public boolean isFinalized;

	public NBTTagCompound tag;

	private int renderWarning;

	protected static final RenderItem itemRenderer = new RenderItem();

	public GuiScreenBeardCustomizer()
	{
		mc = Minecraft.getMinecraft();
		tag = mc.thePlayer.getEntityData();
		if (tag != null)
		{
			openR = tag.getFloat("BeardRed");
			openG = tag.getFloat("BeardGreen");
			openB = tag.getFloat("BeardBlue");
			openSize = tag.getInteger("BeardGrowth");
			openStyle = tag.getInteger("BeardStyle");
			selectedSize = openSize;
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
		tag.setInteger("BeardStyle", openStyle);
		tag.setInteger("BeardGrowth", openSize);
	}

	@Override
	public void initGui()
	{
		super.initGui();
		animation = 0;
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
		this.buttonList.add(new GuiSmallButton(5, this.width / 2 + 79, this.height / 6 + 50, 10, 10, ">"));
		this.buttonList.add(new GuiSmallButton(6, this.width / 2 + 60, this.height / 6 + 50, 10, 10, "<"));
		this.buttonList.add(new GuiSmallButton(7, this.width / 2 + 79, this.height / 6 + 63, 10, 10, ">"));
		this.buttonList.add(new GuiSmallButton(8, this.width / 2 + 60, this.height / 6 + 63, 10, 10, "<"));
		this.buttonList.add(new GuiButton(9, this.width / 2 - 79, this.height / 6 + 110, 54, 20, "Shave"));
		((GuiSmallButton) buttonList.get(4)).drawButton = false;
		((GuiSmallButton) buttonList.get(5)).drawButton = false;
		((GuiSmallButton) buttonList.get(6)).drawButton = false;
		((GuiSmallButton) buttonList.get(7)).drawButton = false;
		((GuiSmallButton) buttonList.get(6)).enabled = false;
		((GuiButton) buttonList.get(8)).drawButton = false;
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
			if (tag != null)
			{
				tag.setString("BeardName", beardName);
			}
			float r = ((GuiRGBSlider) buttonList.get(1)).sliderValue;
			float g = ((GuiRGBSlider) buttonList.get(2)).sliderValue;
			float b = ((GuiRGBSlider) buttonList.get(3)).sliderValue;

			ByteArrayOutputStream outstream = new ByteArrayOutputStream();
			DataOutputStream output = new DataOutputStream(outstream);

			try
			{
				output.writeInt(0);
				output.writeUTF(beardName);
				output.writeFloat(r);
				output.writeFloat(g);
				output.writeFloat(b);
				PacketDispatcher.sendPacketToServer(new Packet250CustomPayload("beards", outstream.toByteArray()));
				isFinalized = true;
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}

			break;
		case 5:
			if (tag != null)
			{
				int beardStyle = tag.getInteger("BeardStyle");
				if (Beard.getBeardFromId(beardStyle + 1) != null)
				{
					tag.setInteger("BeardStyle", beardStyle + 1);
				}
				else
				{
					tag.setInteger("BeardStyle", 0);
				}
			}
			break;
		case 6:
			if (tag != null)
			{
				int beardStyle = tag.getInteger("BeardStyle");
				if (Beard.getBeardFromId(beardStyle - 1) != null)
				{
					tag.setInteger("BeardStyle", beardStyle - 1);
				}
				else
				{
					tag.setInteger("BeardStyle", Beard.beardMaps.size() - 1);
				}
			}
			break;
		case 7:
			if (selectedSize < openSize)
			{
				selectedSize++;
			}
			break;
		case 8:
			if (selectedSize > 0)
			{
				selectedSize--;
			}
			break;
		case 9:
			if (tag != null)
			{
				ByteArrayOutputStream bOut = new ByteArrayOutputStream();
				DataOutputStream dOut = new DataOutputStream(bOut);

				try
				{
					dOut.writeInt(2);
					dOut.writeInt(tag.getInteger("BeardStyle"));
					dOut.writeInt(tag.getInteger("BeardGrowth"));
					PacketDispatcher.sendPacketToServer(new Packet250CustomPayload("beards", bOut.toByteArray()));
				}
				catch (IOException io)
				{
					io.printStackTrace();
				}
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
		int i = (this.width - 172) / 2 + 166;
		int j = this.height / 6 + 30;
		if (par2 > j && par2 < (j + 24))
		{
			if (par1 > i + (int) animation + (animation > 0 ? 0 : 3) && par1 < (i + (int) animation + (animation > 0 ? 0 : 3) + 24) && !shouldAnimate)
			{
				if (canShave)
					shouldAnimate = true;
				else
					renderWarning = 60;
			}
			if (par1 > i + 69 && par1 < (i + 24) + 69 && shouldAnimate)
			{
				shouldAnimate = false;
			}
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

	public int animation;

	public boolean shouldAnimate = false;

	private boolean canShave;

	private int selectedSize;

	@Override
	public void drawScreen(int par1, int par2, float par3)
	{
		this.drawDefaultBackground();
		if (tag != null)
		{
			Beard beard = Beard.getBeardFromId(tag.getInteger("BeardStyle"));
			if (tag.getInteger("BeardGrowth") + 1 > openSize)
				((GuiSmallButton) buttonList.get(6)).enabled = false;
			else
				((GuiSmallButton) buttonList.get(6)).enabled = true;
			if (tag.getInteger("BeardGrowth") - 1 < 0)
				((GuiSmallButton) buttonList.get(7)).enabled = false;
			else
				((GuiSmallButton) buttonList.get(7)).enabled = true;
			if (tag.getInteger("BeardGrowth") != selectedSize)
				tag.setInteger("BeardGrowth", selectedSize);
			ItemStack stack = mc.thePlayer.getCurrentEquippedItem();
			if ((openSize > tag.getInteger("BeardGrowth") || (!(beard.equals(Beard.getBeardFromId(openStyle))) && tag.getInteger("BeardGrowth") >= beard.minShaveSize)) && stack != null && stack.getMaxDamage() - stack.getItemDamage() >= tag.getInteger("BeardGrowth"))
				((GuiButton) buttonList.get(8)).enabled = true;
			else
				((GuiButton) buttonList.get(8)).enabled = false;
		}
		mc.renderEngine.bindTexture(BACKGROUND);
		int k = (this.width - 172) / 2;
		this.drawTexturedModalRect(k, this.height / 6 + 30, 0, 0, 172, 108);
		((GuiSmallButton) buttonList.get(4)).yPosition = this.height / 6 + 50;
		((GuiSmallButton) buttonList.get(4)).xPosition = k + 150 + animation;
		((GuiSmallButton) buttonList.get(5)).xPosition = k + 137 + animation;
		((GuiSmallButton) buttonList.get(6)).xPosition = k + 150 + animation;
		((GuiSmallButton) buttonList.get(7)).xPosition = k + 137 + animation;
		if (shouldAnimate)
		{
			if (animation > 30)
			{
				((GuiSmallButton) buttonList.get(5)).drawButton = true;
				((GuiSmallButton) buttonList.get(7)).drawButton = true;
			}
			if (animation > 20)
			{
				((GuiSmallButton) buttonList.get(4)).drawButton = true;
				((GuiSmallButton) buttonList.get(6)).drawButton = true;
			}
			if (animation < 69)
				this.buttonList.set(8, new GuiButton(9, this.width / 2 + 90, this.height / 6 + 75, 54 / ((69 / 5 - (animation + 1) / 5) + 1), 20, ""));
			else
				((GuiButton) this.buttonList.get(8)).displayString = "Shave";
			if (animation < 69)
			{
				animation += 3;
			}
		}
		else
		{
			if (animation > 0)
			{
				animation -= 3;
				((GuiButton) buttonList.get(8)).drawButton = false;
				if (animation < 20)
				{
					((GuiSmallButton) buttonList.get(4)).drawButton = false;
					((GuiSmallButton) buttonList.get(6)).drawButton = false;
				}
				if (animation < 30)
				{
					((GuiSmallButton) buttonList.get(5)).drawButton = false;
					((GuiSmallButton) buttonList.get(7)).drawButton = false;
				}
			}
		}
		if (animation > 0)
		{
			this.drawTexturedModalRect(k + 100 + animation, this.height / 6 + 30, 0, 108, 69, 69);
		}
		int animationShift = (int) animation + (animation > 0 ? 0 : 3);
		this.drawTexturedModalRect(k + 166 + animationShift, this.height / 6 + 30, 172, 0, 24, 23);
		this.drawTexturedModalRect(k + 166 + animationShift, this.height / 6 + 30, 172, 0, 24, 23);
		if (animation > 0)
			this.drawTexturedModalRect(k + 166, this.height / 6 + 96, 69, 108, 10, 3);
		if (renderWarning > 0)
		{
			renderWarning--;
			drawCenteredString(mc.fontRenderer, "You must have Shears in hand!", k + 80, this.height / 6 + 30 - renderWarning, 14737632 - (renderWarning * 2 + 1));
		}
		if (animation >= 69)
		{
			drawString(mc.fontRenderer, "Style:", k + 176, this.height / 6 + 50, 14737632);
			drawString(mc.fontRenderer, "Size:", k + 180, this.height / 6 + 64, 14737632);
		}
		this.beardNameField.drawTextBox();
		beardNameField.updateCursorCounter();
		float r = ((GuiRGBSlider) buttonList.get(1)).sliderValue;
		float g = ((GuiRGBSlider) buttonList.get(2)).sliderValue;
		float b = ((GuiRGBSlider) buttonList.get(3)).sliderValue;
		if (tag != null)
		{
			tag.setFloat("BeardRed", r);
			tag.setFloat("BeardGreen", g);
			tag.setFloat("BeardBlue", b);
			tag.setBoolean("disableLighting", true);
		}
		func_110423_a(width / 2 - 52, this.height / 6 + 106, 35, 0, 0, mc.thePlayer);
		super.drawScreen(par1, par2, par3);
		if (mc.thePlayer.getCurrentEquippedItem() == null || (mc.thePlayer.getCurrentEquippedItem() != null && !(mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemShears)))
		{
			canShave = false;
			itemRenderer.renderWithColor = false;
			GL11.glColor3f(0.2f, 0.2f, 0.2f);
		}
		else
		{
			canShave = true;
		}
		itemRenderer.renderItemIntoGUI(mc.fontRenderer, mc.renderEngine, new ItemStack(Item.shears), k + 169 + animationShift, this.height / 6 + 33, false);
	}
}
