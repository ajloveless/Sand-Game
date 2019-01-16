package Elements;

public class Sand extends Powder
{
	public Sand()
	{
		//Id, Name, Tooltip, Type, Color, Density
		super((byte)1,"Sand","Piles up in nice dunes",(byte)3,0xC2B280,1);


	}

	@Override
	public byte[] interaction(Element[] elements, byte[] grid, int i, int width)
	{
		return grid;
	}
	
}