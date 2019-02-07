package Elements.Subtypes;

import Elements.Element;
import Elements.Types.Powder;

public class Seed extends Powder
{
	public Seed()
	{
		//Id, Name, Tooltip, Type, Color, Density
		super((byte)4,"Seed","Takes in water and sprouts a plant",(byte)3,0x368505,1);


	}

	@Override
	public byte[] interaction(Element[] elements, byte[] grid, int i, int width)
	{
		grid = super.interaction(elements, grid, i, width);
		        for(int xx=-1;xx<=1;xx++)
          for(int yy=-1;yy<=1;yy++)
            if (elements[grid[ i + (width*yy) + xx]].name == "Water")
                grid[i] = 8; //plant
		return grid;
	}
	
}