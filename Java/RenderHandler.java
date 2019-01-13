import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class RenderHandler
{

 //Declare objects 
 private BufferedImage view;
 private Rectangle camera;
 private int[] pixels;

 public RenderHandler(int width, int height)
 {
  //Create a buffered image that represents the view
  view = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
  camera = new Rectangle(0, 0, width, height);

  //Create an array for the pixels
  pixels = ((DataBufferInt) view.getRaster().getDataBuffer()).getData();

 }

 public void render(Graphics graphics)
 {
  //Render the handled view to grahpics
  graphics.drawImage(view,0,0,view.getWidth(),view.getHeight(),null);

 }


 public void renderArray(int[] renderPixels, int renderWidth, int renderHeight, int xPosition, int yPosition, int xZoom, int yZoom) 
 {
  //Render an array of pixels to the screen
  for(int y = 0; y < renderHeight; y++)
   for(int x = 0; x < renderWidth; x++)
    for(int yZoomPosition = 0; yZoomPosition < yZoom; yZoomPosition++)
     for(int xZoomPosition = 0; xZoomPosition < xZoom; xZoomPosition++)
      setPixel(renderPixels[x + y * renderWidth], (x * xZoom) + xPosition + xZoomPosition, ((y * yZoom) + yPosition + yZoomPosition));
 }


 public void setPixel(int pixel, int x, int y) 
 {
  //Set a pixel a color 
  if(x >= camera.x && y >= camera.y && x <= camera.x + camera.w && y <= camera.y + camera.h)
  {
   int pixelIndex = (x - camera.x) + (y - camera.y) * view.getWidth();
   if(pixels.length > pixelIndex && pixel != Engine.alpha)
    pixels[pixelIndex] = pixel;
  }
 }

 public void renderRectangle(Rectangle rectangle, int xZoom, int yZoom)
 {
  //Render a rectangle (this is easier to call than using Graphics2D)
  int[] rectanglePixels = rectangle.getPixels();
  if(rectanglePixels != null)
   renderArray(rectanglePixels, rectangle.w, rectangle.h, rectangle.x, rectangle.y, xZoom, yZoom); 
 }
}