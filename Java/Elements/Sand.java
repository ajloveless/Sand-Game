package Elements;

public class Sand extends Element
{
	public Sand()
	{
		//Id, Name, Tooltip, Type, Color, Density
		super((byte)1,"Sand","Piles up in nice dunes",(byte)3,0xC2B280,1);


	}

	@Override
	public void interaction()
	{
	}
	
}