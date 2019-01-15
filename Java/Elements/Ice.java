package Elements;

public class Ice extends Element
{
	public Ice()
	{
		//Id, Name, Tooltip, Type, Color, Density
		super((byte)7,"Ice","Freezes water",(byte)1,0xadd8e6,2);


	}

	@Override
	public void interaction()
	{
		for(int xx=-1;xx<=1;xx++)
		{
			for (int yy = -1; yy <= 1; yy++)
			{
				if (grid[i + (width * yy) + xx].name == "Water" || grid[i + (width * yy) + xx].name == "Snow")
				{
					if (Math.random() > 0.99) //One percent of the time
					{
						grid[i + (width * yy) + xx] = grid[i];
					}
				}
			}
		}
	}
	
}