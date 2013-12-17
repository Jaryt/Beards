package net.beards.client.model;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;

public class ModelWizardBeard extends ModelBeardBase
{
	//fields
	ModelRenderer stage15;

	ModelRenderer stage3b;

	ModelRenderer stage1;

	ModelRenderer stage4b;

	ModelRenderer stage3a;

	ModelRenderer stage9b;

	ModelRenderer stage2a;

	ModelRenderer stage5b;

	ModelRenderer stage4a;

	ModelRenderer stage2c;

	ModelRenderer stage7b;

	ModelRenderer stage6b;

	ModelRenderer stage9a;

	ModelRenderer stage6a;

	ModelRenderer stage7d;

	ModelRenderer stage2b;

	ModelRenderer stage7c;

	ModelRenderer stage7a;

	ModelRenderer stage5a;

	ModelRenderer stage5c;

	ModelRenderer stage10;

	ModelRenderer stage11;

	ModelRenderer stage12;

	ModelRenderer stage13;

	ModelRenderer stage14;

	ModelRenderer stage16;

	public ModelWizardBeard()
	{
		textureWidth = 32;
		textureHeight = 32;

		stage15 = new ModelRenderer(this, 12, 28);
		stage15.addBox(-1F, 16F, 0F, 2, 1, 1);
		stage15.setRotationPoint(0F, 0F, 0F);
		stage15.setTextureSize(32, 32);
		stage15.mirror = true;
		setRotation(stage15, -0.6981317F, 0F, 0F);
		stage3b = new ModelRenderer(this, 0, 2);
		stage3b.addBox(-1.35F, -3.1F, -4.2F, 2, 1, 1);
		stage3b.setRotationPoint(0F, 0F, 0F);
		stage3b.setTextureSize(32, 32);
		stage3b.mirror = true;
		setRotation(stage3b, 0F, 0F, -0.5235988F);
		stage1 = new ModelRenderer(this, 0, 0);
		stage1.addBox(-1F, -3F, -4.2F, 2, 1, 1);
		stage1.setRotationPoint(0F, 0F, 0F);
		stage1.setTextureSize(32, 32);
		stage1.mirror = true;
		setRotation(stage1, 0F, 0F, 0F);
		stage4b = new ModelRenderer(this, 6, 10);
		stage4b.addBox(1.7F, -2F, -4.2F, 1, 2, 1);
		stage4b.setRotationPoint(0F, 0F, 0F);
		stage4b.setTextureSize(32, 32);
		stage4b.mirror = true;
		setRotation(stage4b, 0F, 0F, 0F);
		stage3a = new ModelRenderer(this, 0, 2);
		stage3a.addBox(-0.6F, -3.1F, -4.2F, 2, 1, 1);
		stage3a.setRotationPoint(0F, 0F, 0F);
		stage3a.setTextureSize(32, 32);
		stage3a.mirror = true;
		setRotation(stage3a, 0F, 0F, 0.5235988F);
		stage9b = new ModelRenderer(this, 18, 3);
		stage9b.addBox(2.4F, -2.99F, -4.15F, 1, 2, 1);
		stage9b.setRotationPoint(0F, 1F, 0F);
		stage9b.setTextureSize(32, 32);
		stage9b.mirror = true;
		setRotation(stage9b, 0F, 0F, 0F);
		stage2a = new ModelRenderer(this, 6, 2);
		stage2a.addBox(-1F, -1F, -4.2F, 2, 1, 1);
		stage2a.setRotationPoint(0F, 0.3F, 0F);
		stage2a.setTextureSize(32, 32);
		stage2a.mirror = true;
		setRotation(stage2a, 0F, 0F, 0F);
		stage5b = new ModelRenderer(this, 0, 10);
		stage5b.addBox(-2.8F, -1F, -4.2F, 2, 1, 1);
		stage5b.setRotationPoint(0F, 0.3F, 0F);
		stage5b.setTextureSize(32, 32);
		stage5b.mirror = true;
		setRotation(stage5b, 0F, 0F, 0F);
		stage4a = new ModelRenderer(this, 6, 10);
		stage4a.addBox(-2.8F, -2F, -4.2F, 1, 2, 1);
		stage4a.setRotationPoint(0F, 0F, 0F);
		stage4a.setTextureSize(32, 32);
		stage4a.mirror = true;
		setRotation(stage4a, 0F, 0F, 0F);
		stage2c = new ModelRenderer(this, 14, 4);
		stage2c.addBox(3.1F, -2.99F, -3.99F, 1, 1, 1);
		stage2c.setRotationPoint(0F, 0F, 0F);
		stage2c.setTextureSize(32, 32);
		stage2c.mirror = true;
		setRotation(stage2c, 0F, 0F, 0F);
		stage7b = new ModelRenderer(this, 12, 1);
		stage7b.addBox(-4.1F, -2F, -3.1F, 1, 1, 2);
		stage7b.setRotationPoint(0F, 0F, 0F);
		stage7b.setTextureSize(32, 32);
		stage7b.mirror = true;
		setRotation(stage7b, 0F, 0F, 0F);
		stage6b = new ModelRenderer(this, 14, 4);
		stage6b.addBox(3.1F, -2.99F, -4.1F, 1, 1, 1);
		stage6b.setRotationPoint(0F, 1F, 0F);
		stage6b.setTextureSize(32, 32);
		stage6b.mirror = true;
		setRotation(stage6b, 0F, 0F, 0F);
		stage9a = new ModelRenderer(this, 6, 10);
		stage9a.addBox(-3.3F, -2.99F, -4.15F, 1, 2, 1);
		stage9a.setRotationPoint(0F, 1F, 0F);
		stage9a.setTextureSize(32, 32);
		stage9a.mirror = true;
		setRotation(stage9a, 0F, 0F, 0F);
		stage6a = new ModelRenderer(this, 14, 4);
		stage6a.addBox(-4.1F, -2.99F, -4.1F, 1, 1, 1);
		stage6a.setRotationPoint(0F, 1F, 0F);
		stage6a.setTextureSize(32, 32);
		stage6a.mirror = true;
		setRotation(stage6a, 0F, 0F, 0F);
		stage7d = new ModelRenderer(this, 14, 4);
		stage7d.addBox(-4.1F, -1.99F, -4.1F, 1, 1, 1);
		stage7d.setRotationPoint(0F, 1F, 0F);
		stage7d.setTextureSize(32, 32);
		stage7d.mirror = true;
		setRotation(stage7d, 0F, 0F, 0F);
		stage2b = new ModelRenderer(this, 14, 4);
		stage2b.addBox(-4.1F, -2.99F, -3.99F, 1, 1, 1);
		stage2b.setRotationPoint(0F, 0F, 0F);
		stage2b.setTextureSize(32, 32);
		stage2b.mirror = true;
		setRotation(stage2b, 0F, 0F, 0F);
		stage7c = new ModelRenderer(this, 14, 4);
		stage7c.addBox(3.1F, -1.99F, -4.1F, 1, 1, 1);
		stage7c.setRotationPoint(0F, 1F, 0F);
		stage7c.setTextureSize(32, 32);
		stage7c.mirror = true;
		setRotation(stage7c, 0F, 0F, 0F);
		stage7a = new ModelRenderer(this, 12, 1);
		stage7a.addBox(3.1F, -1.99F, -3.1F, 1, 1, 2);
		stage7a.setRotationPoint(0F, 0F, 0F);
		stage7a.setTextureSize(32, 32);
		stage7a.mirror = true;
		setRotation(stage7a, 0F, 0F, 0F);
		stage5a = new ModelRenderer(this, 6, 0);
		stage5a.addBox(-2.8F, -1F, -4.2F, 2, 1, 1);
		stage5a.setRotationPoint(3.5F, 0.3F, 0F);
		stage5a.setTextureSize(32, 32);
		stage5a.mirror = true;
		setRotation(stage5a, 0F, 0F, 0F);
		stage5c = new ModelRenderer(this, 0, 4);
		stage5c.addBox(-3F, -0.9F, -3.2F, 6, 1, 1);
		stage5c.setRotationPoint(0F, 0F, 0F);
		stage5c.setTextureSize(32, 32);
		stage5c.mirror = true;
		setRotation(stage5c, 0F, 0F, 0F);
		stage10 = new ModelRenderer(this, 0, 6);
		stage10.addBox(-3.5F, 0.1F, -4.2F, 7, 1, 3);
		stage10.setRotationPoint(0F, 0F, 0F);
		stage10.setTextureSize(32, 32);
		stage10.mirror = true;
		setRotation(stage10, -0.0523599F, 0F, 0F);
		stage11 = new ModelRenderer(this, 0, 13);
		stage11.addBox(-3F, 1F, -4.1F, 6, 2, 3);
		stage11.setRotationPoint(0F, 0F, 0F);
		stage11.setTextureSize(32, 32);
		stage11.mirror = true;
		setRotation(stage11, -0.1745329F, 0F, 0F);
		stage12 = new ModelRenderer(this, 0, 13);
		stage12.addBox(-3F, 3F, -3.1F, 6, 4, 3);
		stage12.setRotationPoint(0F, 0F, 0F);
		stage12.setTextureSize(32, 32);
		stage12.mirror = true;
		setRotation(stage12, -0.5235988F, 0F, 0F);
		stage13 = new ModelRenderer(this, 0, 18);
		stage13.addBox(-2.5F, 7F, -3.1F, 5, 4, 3);
		stage13.setRotationPoint(0F, 0F, 0F);
		stage13.setTextureSize(32, 32);
		stage13.mirror = true;
		setRotation(stage13, -0.5235988F, 0F, 0F);
		stage14 = new ModelRenderer(this, 0, 23);
		stage14.addBox(-2F, 11F, -1.1F, 4, 3, 3);
		stage14.setRotationPoint(0F, 0F, 0F);
		stage14.setTextureSize(32, 32);
		stage14.mirror = true;
		setRotation(stage14, -0.6981317F, 0F, 0F);
		stage16 = new ModelRenderer(this, 12, 28);
		stage16.addBox(-1.5F, 14F, -0.6F, 3, 2, 2);
		stage16.setRotationPoint(0F, 0F, 0F);
		stage16.setTextureSize(32, 32);
		stage16.mirror = true;
		setRotation(stage16, -0.6981317F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, int beardStage, int beardGrowth)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		if (beardStage >= 1)
		{
			GL11.glPushMatrix();
			GL11.glScalef(1.0f, 1.0f, 1.0f);
			stage1.render(f5);
			GL11.glPopMatrix();
		}
		if (beardStage >= 2)
		{
			stage2a.render(f5);
			stage2b.render(f5);
			stage2c.render(f5);
		}
		if (beardStage >= 3)
		{
			stage3a.render(f5);
			stage3b.render(f5);
		}
		if (beardStage >= 4)
		{
			stage4a.render(f5);
			stage4b.render(f5);
		}
		if (beardStage >= 5)
		{
			stage5a.render(f5);
			stage5c.render(f5);
			stage5b.render(f5);
		}
		if (beardStage >= 6)
		{
			stage6a.render(f5);
			stage6b.render(f5);
		}
		if (beardStage >= 7)
		{
			stage7a.render(f5);
			stage7b.render(f5);
			stage7c.render(f5);
			stage7d.render(f5);
		}
		if (beardStage >= 8)
		{
			stage9a.render(f5);
			stage9b.render(f5);
		}
		NBTTagCompound tag = entity.getEntityData();
		Minecraft mc = Minecraft.getMinecraft();
		if (mc.currentScreen == null || (mc.currentScreen != null && !mc.currentScreen.doesGuiPauseGame()))
		{
			if (f1 > 0 || f1 < -0.8F)
				GL11.glRotatef((float) -Math.cos((float) f1) * 8 * (float) f1 * 3, 5, 0, 0);
			GL11.glTranslatef(0, -0.05f, 0);
			if (tag != null)
			{
				GL11.glRotatef((float) Math.cos(f) * 5, 0.5f, 1.0f, 0.0f);
				GL11.glRotatef((float) Math.sin(f) * 5, 0.0f, 1.0f, 0.5f);
			}
			if (entity != null && !(entity.rotationPitch < -10))
				GL11.glRotatef(-entity.rotationPitch, (entity.rotationPitch * 180 / (float) Math.PI), 0, 0);
		}
		if (beardStage >= 9)
			stage10.render(f5);
		if (beardStage >= 10)
			stage11.render(f5);
		if (beardStage >= 11)
			stage12.render(f5);
		if (beardStage >= 12)
			stage13.render(f5);
		if (beardStage >= 13)
			stage14.render(f5);
		if (beardStage >= 14)
			stage15.render(f5);
		if (beardStage >= 15)
			stage16.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
}