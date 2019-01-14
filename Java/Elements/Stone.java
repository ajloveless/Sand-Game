package Elements;

public class Stone extends Element
{
	public Stone()
	{
		//Id, Name, Tooltip, Type, Color, Density
		super((byte)5,"Stone","Good at blocking things",(byte)1,0x808080,2);


	}

	@Override
	public void interaction()
	{
	}
	
}