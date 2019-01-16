package Elements;

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
		return grid;
	}
	
}