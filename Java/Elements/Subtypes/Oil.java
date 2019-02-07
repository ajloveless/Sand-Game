package Elements.Subtypes;

import Elements.Element;
import Elements.Liquid;

public class Oil extends Liquid
{
	public Oil()
	{
		//Id, Name, Tooltip, Type, Color, Density
		super((byte)11,"Oil","Floats on top of water",(byte)2,0x2c2416,0.4);


	}

	@Override
	public byte[] interaction(Element[] elements, byte[] grid, int i, int width)
	{
		grid = super.interaction(elements, grid, i, width);
		return grid;
	}
	
}