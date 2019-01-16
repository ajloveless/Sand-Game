package Elements;

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
		return grid;
	}
	
}