package Elements;

public class Water extends Liquid
{
	public Water()
	{
		//Id, Name, Tooltip, Type, Color, Density
		super((byte)10,"Water","Makes nice lakes",(byte)2,0x0000ff,0.5);


	}

	@Override
	public byte[] interaction(Element[] elements, byte[] grid, int i, int width)
	{
		return grid;
	}
	
}