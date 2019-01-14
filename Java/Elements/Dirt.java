package Elements;

public class Dirt extends Element
{
	public Dirt()
	{
		//Id, Name, Tooltip, Type, Color, Density
		super((byte)2,"Dirt","Makes good mountains",(byte)3,0x8B4513,1);


	}

	@Override
	public void interaction()
	{
	}
	
}