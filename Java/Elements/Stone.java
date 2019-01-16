package Elements;

public class Stone extends Solid
{
	public Stone()
	{
		//Id, Name, Tooltip, Type, Color, Density
		super((byte)5,"Stone","Good at blocking things",(byte)1,0x808080,2);


	}

	@Override
	public byte[] interaction(Element[] elements, byte[] grid, int i, int width)
	{
		grid = super.interaction(elements, grid, i, width);
		return grid;
	}
	
}