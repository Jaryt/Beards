package net.beards.beard;

import java.util.HashMap;

import net.beards.client.model.ModelBeardBase;
import net.beards.client.model.ModelDwarfBeard;
import net.beards.client.model.ModelLumberjackBeard;

public class Beard
{

	public ModelBeardBase beardModel;

	public static HashMap<Integer, Beard> beardMaps = new HashMap<Integer, Beard>();

	public String beardName;

	public static Beard lumberjack, dwarf;

	public int maxSize, id, minShaveSize;
	
	public Beard(int id, String beardName, int maxSize, int minShaveSize)
	{
		this.id = id;
		this.beardName = beardName;
		this.maxSize = maxSize;
		this.minShaveSize = minShaveSize;
	}

	public Beard setModel(ModelBeardBase beardModel)
	{
		this.beardModel = beardModel;
		return this;
	}
	
	public Beard finalizeBeard(){
		Beard.beardMaps.put(id, this);
		return this;
	}
	
	public static Beard getBeardFromId(int id)
	{
		return beardMaps.get(id);
	}
	
	public static void createBeards()
	{
		lumberjack = new Beard(0, "Lumberjack", 14, 14).setModel(new ModelLumberjackBeard()).finalizeBeard();
		dwarf = new Beard(1, "Dwarf", 15, 14).setModel(new ModelDwarfBeard()).finalizeBeard();
	}

}
