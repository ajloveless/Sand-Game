package Elements;

public class Plant extends Element
{
	public Plant()
	{
		//Id, Name, Tooltip, Type, Color, Density
		super((byte)8,"Plant","Grows when in contact with water",(byte)1,0x43921b,2);


	}

	@Override
	public void interaction()
	{
	}
	
}