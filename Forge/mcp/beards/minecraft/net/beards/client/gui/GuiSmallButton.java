package net.beards.client.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;

public class GuiSmallButton extends GuiButton
{

	public GuiSmallButton(int par1, int par2, int par3, int par4, int par5, String par6Str)
	{
		super(par1, par2, par3, par4, par5, par6Str);
	}

	public void drawButton(Minecraft par1Minecraft, int par2, int par3)
	{
		if (this.drawButton)
		{
			FontRenderer fontrenderer = par1Minecraft.fontRenderer;
			par1Minecraft.getTextureManager().bindTexture(buttonTextures);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.field_82253_i = par2 >= this.xPosition && par3 >= this.yPosition && par2 < this.xPosition + this.width && par3 < this.yPosition + this.height;
			int k = this.getHoverState(this.field_82253_i);
			this.drawTexturedModalRect(this.xPosition, this.yPosition + this.height / 2 - 5, 0, 46 + k * 20, this.width / 2, this.height / 2);
			this.drawTexturedModalRect(this.xPosition, this.yPosition + this.height / 2, 0, 61 + k * 20, this.width / 2, this.height / 2);
			this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition + this.height / 2 - 5, 200 - this.width / 2, 46 + k * 20, this.width / 2, this.height / 2);
			this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition + this.height / 2, 200 - this.width / 2, 46 + height + 5 + k * 20, this.width / 2, this.height / 2);
			this.mouseDragged(par1Minecraft, par2, par3);
			int l = 14737632;

			if (!this.enabled)
			{
				l = -6250336;
			}
			else if (this.field_82253_i)
			{
				l = 16777120;
			}

			this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, l);
		}
	}
}
