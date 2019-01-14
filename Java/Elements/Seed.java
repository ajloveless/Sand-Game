package Elements;

public class Seed extends Element
{
	public Seed()
	{
		//Id, Name, Tooltip, Type, Color, Density
		super((byte)4,"Seed","Takes in water and sprouts a plant",(byte)3,0x368505,1);


	}

	@Override
	public void interaction()
	{
	}
	
}