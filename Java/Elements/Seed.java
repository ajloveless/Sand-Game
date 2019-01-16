package Elements;

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
		return grid;
	}
	
}