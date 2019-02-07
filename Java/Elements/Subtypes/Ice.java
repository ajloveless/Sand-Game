package Elements.Subtypes;

import Elements.Element;
import Elements.Types.Solid;

public class Ice extends Solid
{
	public Ice()
	{
		//Id, Name, Tooltip, Type, Color, Density
		super((byte)7,"Ice","Freezes water",(byte)1,0xadd8e6,2);

	}

	@Override
	public byte[] interaction(Element[] elements, byte[] grid, int i, int width)
	{
		grid = super.interaction(elements, grid, i, width);
		for(int xx=-1;xx<=1;xx++)
		{
			for (int yy = -1; yy <= 1; yy++)
			{
				if (elements[grid[i + (width * yy) + xx]].name == "Water" || elements[grid[i + (width * yy) + xx]].name == "Snow")
				{
					if (Math.random() > 0.99) //One percent of the time
					{
						grid[i + (width * yy) + xx] = grid[i];
					}
				}
			}
		}
		return grid;
	}

}