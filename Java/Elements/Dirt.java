package Elements;

public class Dirt extends Solid
{
	public Dirt()
	{
		//Id, Name, Tooltip, Type, Color, Density
		super((byte)2,"Dirt","Makes good mountains",(byte)3,0x8B4513,1);


	}

	@Override
	public byte[] interaction(Element[] elements, byte[] grid, int i, int width)
	{
		return grid;
	}
	
}