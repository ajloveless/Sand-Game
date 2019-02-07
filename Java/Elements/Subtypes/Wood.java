package Elements.Subtypes;

import Elements.Element;
import Elements.Types.Solid;

public class Wood extends Solid
{
	public Wood()
	{
		//Id, Name, Tooltip, Type, Color, Density
		super((byte)6,"Wood","Blocks objects",(byte)1,0x855e42,2);


	}

	@Override
	public byte[] interaction(Element[] elements, byte[] grid, int i, int width)
	{
		grid = super.interaction(elements, grid, i, width);
		return grid;
	}
	
}