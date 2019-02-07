package Elements.Subtypes;

import Elements.Element;
import Elements.Types.Solid;

public class Spout extends Solid
{
	public Spout()
	{
		//Id, Name, Tooltip, Type, Color, Density
		super((byte)9,"Spout","Creates a stream of water",(byte)1,0xffd700,2);


	}

	@Override
	public byte[] interaction(Element[] elements, byte[] grid, int i, int width)
	{
		grid = super.interaction(elements, grid, i, width);
      
        if (elements[grid[i + width]].name == "Air")
        if (Math.random() > 0.90)
        grid[i + width] = 10; //water
      
		return grid;
	}
}