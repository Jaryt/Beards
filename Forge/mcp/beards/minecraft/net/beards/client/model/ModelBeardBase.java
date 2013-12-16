package net.beards.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;

public class ModelBeardBase extends ModelBase
{
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, int beardStage, int growStage)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
}
