public class Rectangle 
{
	//Declare variables
	public int x,y,w,h;
	private int[] pixels;

	//if arguments are given, make the rectangle the specified size
	Rectangle(int x, int y, int w, int h) 
	{
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	//For no arguments, make the rectangle size 0
	Rectangle() 
	{
		this(0,0,0,0);
	}

	//Render an object to the screen with a color argument
	public void generateGraphics(int color) 
	{
		pixels = new int[w*h];
		for(int y = 0; y < h; y++)
			for(int x = 0; x < w; x++)
				pixels[x + y * w] = color;
	}

	//Render object with color optional border
	public void generateGraphics(int borderWidth, int color) {
		pixels = new int[w*h];
		
		for(int i = 0; i < pixels.length; i++)
			pixels[i] = Engine.alpha;

		for(int y = 0; y < borderWidth; y++)
			for(int x = 0; x < w; x++)
				pixels[x + y * w] = color;

		for(int y = 0; y < h; y++)
			for(int x = 0; x < borderWidth; x++)
				pixels[x + y * w] = color;

		for(int y = 0; y < h; y++)
			for(int x = w - borderWidth; x < w; x++)
				pixels[x + y * w] = color;

		for(int y = h - borderWidth; y < h; y++)
			for(int x = 0; x < w; x++)
				pixels[x + y * w] = color;
		
	}

	//Get pixel data from the screen
	public int[] getPixels() 
	{
		if(pixels != null)
			return pixels;
		else
			System.out.println("Attempted to retrive pixels from a Rectangle without generated graphics.");

		return null;
	}
}