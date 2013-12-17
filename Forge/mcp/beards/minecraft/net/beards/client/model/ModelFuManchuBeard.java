package net.beards.client.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFuManchuBeard extends ModelBeardBase
{
	//fields
	ModelRenderer stage2a;

	ModelRenderer stage1;

	ModelRenderer stage8a;

	ModelRenderer stage2b;

	ModelRenderer stage4b;

	ModelRenderer stage9a;

	ModelRenderer stage4a;

	ModelRenderer stage5a;

	ModelRenderer stage6a;

	ModelRenderer stage7a;

	ModelRenderer stage5b;

	ModelRenderer stage6b;

	ModelRenderer stage7b;

	ModelRenderer stage8b;

	ModelRenderer stage9b;

	public ModelFuManchuBeard()
	{
		textureWidth = 32;
		textureHeight = 32;

		stage2a = new ModelRenderer(this, 0, 2);
		stage2a.addBox(-1.35F, -3.1F, -4.2F, 2, 1, 1);
		stage2a.setRotationPoint(0F, 0F, 0F);
		stage2a.setTextureSize(32, 32);
		stage2a.mirror = true;
		setRotation(stage2a, 0F, 0F, -0.5235988F);
		stage1 = new ModelRenderer(this, 0, 0);
		stage1.addBox(-1F, -3F, -4.2F, 2, 1, 1);
		stage1.setRotationPoint(0F, 0F, 0F);
		stage1.setTextureSize(32, 32);
		stage1.mirror = true;
		setRotation(stage1, 0F, 0F, 0F);
		stage8a = new ModelRenderer(this, 6, 14);
		stage8a.addBox(1.7F, 2F, -4.2F, 1, 1, 1);
		stage8a.setRotationPoint(0F, 0F, 0F);
		stage8a.setTextureSize(32, 32);
		stage8a.mirror = true;
		setRotation(stage8a, 0F, 0F, 0F);
		stage2b = new ModelRenderer(this, 0, 2);
		stage2b.addBox(-0.6F, -3.1F, -4.2F, 2, 1, 1);
		stage2b.setRotationPoint(0F, 0F, 0F);
		stage2b.setTextureSize(32, 32);
		stage2b.mirror = true;
		setRotation(stage2b, 0F, 0F, 0.5235988F);
		stage4b = new ModelRenderer(this, 6, 4);
		stage4b.addBox(-2.8F, -2F, -4.2F, 1, 1, 1);
		stage4b.setRotationPoint(0F, 0F, 0F);
		stage4b.setTextureSize(32, 32);
		stage4b.mirror = true;
		setRotation(stage4b, 0F, 0F, 0F);
		stage9a = new ModelRenderer(this, 6, 12);
		stage9a.addBox(3.4F, -0.5F, -4.2F, 1, 1, 1);
		stage9a.setRotationPoint(0F, 0F, 0F);
		stage9a.setTextureSize(32, 32);
		stage9a.mirror = true;
		setRotation(stage9a, 0F, 0F, 0.9250245F);
		stage4a = new ModelRenderer(this, 10, 10);
		stage4a.addBox(1.7F, -2F, -4.2F, 1, 1, 1);
		stage4a.setRotationPoint(0F, 0F, 0F);
		stage4a.setTextureSize(32, 32);
		stage4a.mirror = true;
		setRotation(stage4a, 0F, 0F, 0F);
		stage5a = new ModelRenderer(this, 6, 6);
		stage5a.addBox(1.7F, -1F, -4.2F, 1, 1, 1);
		stage5a.setRotationPoint(0F, 0F, 0F);
		stage5a.setTextureSize(32, 32);
		stage5a.mirror = true;
		setRotation(stage5a, 0F, 0F, 0F);
		stage6a = new ModelRenderer(this, 6, 8);
		stage6a.addBox(1.7F, 0F, -4.2F, 1, 1, 1);
		stage6a.setRotationPoint(0F, 0F, 0F);
		stage6a.setTextureSize(32, 32);
		stage6a.mirror = true;
		setRotation(stage6a, 0F, 0F, 0F);
		stage7a = new ModelRenderer(this, 6, 10);
		stage7a.addBox(1.7F, 1F, -4.2F, 1, 1, 1);
		stage7a.setRotationPoint(0F, 0F, 0F);
		stage7a.setTextureSize(32, 32);
		stage7a.mirror = true;
		setRotation(stage7a, 0F, 0F, 0F);
		stage5b = new ModelRenderer(this, 2, 10);
		stage5b.addBox(-2.8F, -1F, -4.2F, 1, 1, 1);
		stage5b.setRotationPoint(0F, 0F, 0F);
		stage5b.setTextureSize(32, 32);
		stage5b.mirror = true;
		setRotation(stage5b, 0F, 0F, 0F);
		stage6b = new ModelRenderer(this, 2, 12);
		stage6b.addBox(-2.8F, 0F, -4.2F, 1, 1, 1);
		stage6b.setRotationPoint(0F, 0F, 0F);
		stage6b.setTextureSize(32, 32);
		stage6b.mirror = true;
		setRotation(stage6b, 0F, 0F, 0F);
		stage7b = new ModelRenderer(this, 10, 12);
		stage7b.addBox(-2.8F, 1F, -4.2F, 1, 1, 1);
		stage7b.setRotationPoint(0F, 0F, 0F);
		stage7b.setTextureSize(32, 32);
		stage7b.mirror = true;
		setRotation(stage7b, 0F, 0F, 0F);
		stage8b = new ModelRenderer(this, 10, 8);
		stage8b.addBox(-2.8F, 2F, -4.2F, 1, 1, 1);
		stage8b.setRotationPoint(0F, 0F, 0F);
		stage8b.setTextureSize(32, 32);
		stage8b.mirror = true;
		setRotation(stage8b, 0F, 0F, 0F);
		stage9b = new ModelRenderer(this, 2, 8);
		stage9b.addBox(0.3F, 3.3F, -4.2F, 1, 1, 1);
		stage9b.setRotationPoint(0F, 0F, 0F);
		stage9b.setTextureSize(32, 32);
		stage9b.mirror = true;
		setRotation(stage9b, 0F, 0F, 0.9250245F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, int beardStage, int growStage)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		if (beardStage >= 1)
			stage1.render(f5);
		if (beardStage >= 2)
		{
			stage2a.render(f5);
			stage2b.render(f5);
		}
		if (beardStage >= 3)
		{
			stage4a.render(f5);
			stage4b.render(f5);
		}
		if (beardStage >= 4)
		{
			stage5a.render(f5);
			stage5b.render(f5);
		}
		if (beardStage >= 5)
		{
			stage6a.render(f5);
			stage6b.render(f5);
		}
		if (beardStage >= 6)
		{
			stage7a.render(f5);
			stage7b.render(f5);
		}
		if (beardStage >= 7)
		{
			stage8b.render(f5);
			stage8a.render(f5);
		}
		if (beardStage >= 8)
		{
			stage9a.render(f5);
			stage9b.render(f5);
		}
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
