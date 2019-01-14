package Elements;

public abstract class Element
{
	// String name = "Null";
	// String tooltip = "You shouldn\'t be able to see this message";
	// byte id = -1;
	// byte type = -1;
	// int color = 0x000000;
	// double density = 0.0;

	public final byte id;
	public final String name;
	public final String tooltip;
	public final byte type;
	public final int color;
	public final double density;
	/*
	Types
	-----
	0 - Air
	1 - Solid
	2 - Liquid
	3 -
	*/
	public Element(byte id, String name, String tooltip, byte type, int color, double density)
	{
		this.id = id;
		this.name = name;
		this.tooltip = tooltip;
		this.type = type;
		this.color = color;
		this.density = density;
	}

	public abstract void interaction();

	public void printStats()
	{
		System.out.println(id);
		System.out.println(name);
		System.out.println(tooltip);
		System.out.println(type);
		System.out.println(color);
		System.out.println(density);
	}

	
}