package Elements;

public class Acid extends Liquid
{
	public Acid()
	{
		//Id, Name, Tooltip, Type, Color, Density
		super((byte)12,"Acid","Destroys all elements",(byte)2,0xD0FB3C,0.6);


	}

	@Override
	public byte[] interaction(Element[] elements, byte[] grid, int i, int width)
	{
		return grid;
	}
	
}