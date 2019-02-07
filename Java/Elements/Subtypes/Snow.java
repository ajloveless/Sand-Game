package Elements.Subtypes;

import Elements.Element;
import Elements.Types.Powder;

public class Snow extends Powder
{
	public Snow()
	{
		//Id, Name, Tooltip, Type, Color, Density
		super((byte)3,"Snow","Falls to the ground and freezes things",(byte)3,0xe7e7e7,1);

	}

	@Override
	public byte[] interaction(Element[] elements, byte[] grid, int i, int width)
	{
		grid = super.interaction(elements, grid, i, width);
        for(int xx=-1;xx<=1;xx++)
          for(int yy=-1;yy<=1;yy++)
            if (elements[grid[ i + (width*yy) + xx]].name == "Water")
                grid[i] = 7; //ice
		return grid;
	}
	
}