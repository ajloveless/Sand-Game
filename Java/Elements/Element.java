package Elements;

import java.util.Random;

public class Element
{
	public final byte id;
	public final String name;
	public final String tooltip;
	public final byte type;
	public final int color;
	public final double density;
	public Random random = new Random();

	/*
	Types
	-----
	0 - Air
	1 - Solid
	2 - Liquid
	3 - Powder
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

	public byte[] interaction(Element[] elements, byte[] grid, int i, int width)
	{
		return grid;
	}

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