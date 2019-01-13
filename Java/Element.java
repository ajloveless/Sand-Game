public class Element
{
	String name;
	int type;
	int color;
	double density;

	/*
	Types
	-----
	0 - Solid
	1 - Powder
	2 - Liquid
	3 - Air?
	*/

	public Element(String n, int t, int c, double d)
	{
		name = n;
		type = t;
		color = c;
		density = d;
	}
}