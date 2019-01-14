package Elements;

public class Snow extends Element
{
	public Snow()
	{
		//Id, Name, Tooltip, Type, Color, Density
		super((byte)3,"Snow","Falls to the ground and freezes things",(byte)3,0xe7e7e7,1);


	}

	@Override
	public void interaction()
	{
	}
	
}