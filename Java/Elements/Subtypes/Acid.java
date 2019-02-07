package Elements.Subtypes;

import Elements.Element;
import Elements.Types.Liquid;

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
		grid = super.interaction(elements, grid, i, width);
		 for(int xx=-1;xx<=1;xx++)
          for(int yy=-1;yy<=1;yy++)
          {
          	if (Math.random() > 0.90)
            if (elements[grid[ i + (width*yy) + xx]].name != "Air" && elements[grid[ i + (width*yy) + xx]].name != "Acid")
          {
              grid[ i + (width*yy) + xx] = 0;
              grid[i] = 0;
          }
         }
		return grid;
	}
	
}