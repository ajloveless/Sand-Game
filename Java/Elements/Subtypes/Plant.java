package Elements.Subtypes;

import Elements.Element;
import Elements.Types.Solid;

public class Plant extends Solid
{
	public Plant()
	{
		//Id, Name, Tooltip, Type, Color, Density
		super((byte)8,"Plant","Grows when in contact with water",(byte)1,0x43921b,2);


	}

	@Override
	public byte[] interaction(Element[] elements, byte[] grid, int i, int width)
	{
		grid = super.interaction(elements, grid, i, width);

        int plantCount = -1; //number of plant neighbors it has, Offset by -1 because the cell itself is counted as a neighbor

        //Look at neighbors for water, absorb water to grow plant
        for(int xx=-1;xx<=1;xx++)
          for(int yy=-1;yy<=1;yy++)
          {
            if (elements[grid[ i + (width*yy) + xx]].name == "Water")
            {
                grid[ i + (width*yy) + xx] = grid[i];
            }
            if (elements[grid[ i + (width*yy) + xx]].name == "Plant")
            {
              plantCount++;
            }
          } 
          //If the cell has too many or too few neighbors it dies
          if (plantCount > 4 || plantCount < 1)
            grid[i] = 0;
		return grid;
	}
	
}