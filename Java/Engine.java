import java.awt.Canvas; //Canvas to engine on
import java.awt.Color; //Color definitions
import java.awt.image.BufferStrategy; //Buffering object
import java.awt.Graphics; //Engineing library
import java.lang.Runnable; //Create a runnable
import java.lang.Thread; // import threads
import javax.swing.JFrame; //Display a JFrame
import java.awt.event.MouseEvent;
import java.util.Arrays; 
import Elements.*;

//The Engine class needs to have JFrame methods and the Runnable run() method
public class Engine extends JFrame implements Runnable
{

int realtime;
int paused;
int fps;
public static int width = 800; //Width of the window 
public static int height = 800; //Height of the window
public static int alpha = 0xFFFF00DC; //Defined alpha color
public static Color textColor = new Color(0xffffff); //Color of debug text
//public  Element[] grid = new Element[width * height]; //Array for each screen pixel
private boolean[] changedCells = new boolean[width*height]; //Array for each pixel for whether or not it was changed on the current frame

public int frames = 0; //How many frames have been rendered
private int offset = 0; //Cell offset for left or right, see update()
int speed = 2; //The speed of liquids (2 cells are checked in each direction instead of just 1)

int runTime; //Defined in engine constructor 
boolean debug;
int cursorSize;
//Create a canvas to engine on
private Canvas canvas = new Canvas(); 

//Air is it's own special category, at least until gasses are added
// public Element air = new Element("Air",3,0x000000,0.0);

// //Powders
// public Element sand = new Element("Sand",1,0xc2b280 ,1.0);
// public Element dirt = new Element("Dirt",1,0x8B4513,1.0);
// public Element snow = new Element("Snow",1,0xe7e7e7,1.0);
// public Element seed = new Element("Seed",1,0x368505,1.0);

// //Solids
// public Element stone = new Element("Stone",0,0x808080,2.0);
// public Element wood = new Element("Wood",0,0x855e42,2.0);
// public Element ice = new Element("Ice",0,0xadd8e6,2.0);
// public Element plant = new Element("Plant",0,0x43921b ,1.0);
// public Element spout = new Element("Spout",0,0xffd700 ,1.0);

// //Liquids
// public Element water = new Element("Water",2,0x0000ff,0.5);
// public Element oil = new Element("Oil",2,0x2c2416,0.4);
// public Element acid = new Element("Acid",2,0xD0FB3C,0.6);


public Elements.Sand sand = new Elements.Sand();
public Elements.Dirt dirt = new Elements.Dirt();
public Elements.Snow snow = new Elements.Snow();
public Elements.Seed seed = new Elements.Seed();
public Elements.Stone stone = new Elements.Stone();
public Elements.Wood wood= new Elements.Wood();
public Elements.Ice ice = new Elements.Ice();
public Elements.Plant plant = new Elements.Plant();
public Elements.Spout spout = new Elements.Spout();
public Elements.Water water = new Elements.Water();
public Elements.Oil oil = new Elements.Oil();
public Elements.Acid acid = new Elements.Acid();


//public Element[] elements = {air, sand, dirt, snow, seed, stone, wood, ice, plant, spout, water, oil, acid};
public int scroll = 1; //Which element will be selected, starts at 1 so that air is never selected

//Define handler objects
private RenderHandler renderer;
private MouseHandler mouse = new MouseHandler(this);
private KeyboardHandler keyboard = new KeyboardHandler(this);
private GuiHandler gui = new GuiHandler(this);


//Default engine constructor
public Engine()
{
 System.out.println("Water's tooltip: " + water.tooltip); //Proof of concept
 runTime = 0; //How long in seconds the game has been running
 debug = true; //If debug mode is on
 cursorSize = 10; //Size of the cursor in pixels
 realtime = 60; //What the realtime tick speed should be
 paused = 0; //What the tick speed should be when the game is paused
 fps = realtime; //Start the game unpaused
 //Make the application terminate when it's closed
 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 //Set temporary position and size of the frame
 setBounds(0,0,width,height);
 //Move the frame to the center of the screen
 setLocationRelativeTo(null);
 //Add the canvas to the frame
 add(canvas);
 //Make the frame visible
 setUndecorated(true);
 setVisible(true);
 setResizable(false);
 //Create buffer strategy - cache and display frames without stutters or flashing
 canvas.createBufferStrategy(2);
 //Initialize the render handler at the size of the window
 renderer = new RenderHandler(getWidth(),getHeight());
 //Add listeners to recieve input
 canvas.addKeyListener(keyboard); //Keyboard input
 canvas.addFocusListener(keyboard); //Check if window is in focus
 canvas.addMouseListener(mouse); //Listen for mouse button input
 canvas.addMouseMotionListener(mouse); //Mouse movement
 canvas.addMouseWheelListener(mouse); //Mouse wheel

 //Arrays.fill(grid, air); //Start the game empty
}

 //Update any game logic logic
public void update()
 {   
   
  //If the esc key is pressed, exit the game
  if (keyboard.escape())  System.exit(0); 


  //Add 1 to runTime every second
  if (this.fps == this.realtime)
  {
   frames++;
  if (frames % this.fps == 0)
  {
   this.runTime += 1;
  }

  
  }
}
    



 //Render visuals
public void render()
{
  //Declare and initialize buffer strategy
  BufferStrategy bufferStrategy = canvas.getBufferStrategy();
  Graphics graphics = bufferStrategy.getDrawGraphics();
  //Paint it to the canvas
  super.paint(graphics);
  //Render the graphics
  renderer.render(graphics);
  if (this.debug) 
  {
   graphics.setColor(textColor);
   graphics.drawString(gui.updateGUI(),0,10);
  }
  // for (int i=0; i < grid.length; i++)
  // renderer.setPixel(grid[i].color, i % width, (i -(i % width))/width);
  //Show the buffers
  bufferStrategy.show();
}

//When left click is pressed
public void leftClick(int x, int y)
 {
  //Spawn currently selected element at mouse
  //particle(elements[scroll],cursorSize,x,y);
  
 }

//When right click is pressed
public void rightClick(int x, int y)
 {
  //Destroy element at mouse
  //particle(air,cursorSize,x,y);
  
 }

 public void middleClick(int x, int y)
 {
  //If a middle click is ever needed, this is here  
 }

 //Implement the run function for Runnable, used with threads
 public void run() 
 {
  //Get and store the buffer strategy
  BufferStrategy bufferStrategy = canvas.getBufferStrategy();
  //Variable delcarations
  int i = 0;
  int x = 0;

  //Store the current time as a variable
  long lastTime = System.nanoTime();
  //One second converted to nanoseconds divided across desired this.fps
  double nanoSecondConversion = 1000000000.0 / this.fps;
  //Clear deltaTime, which adjusts discrepency between engine logic speed and this.fps 
  double deltaTime = 0;
  //Infinite loop the following for accurate-ish engine logic speed
  while(true)
  {
   //Get a new current time
   long now = System.nanoTime();

   //Compare both time variables to see if this.fps is at a suitable rate in relation to the engine logic speed
   deltaTime += (now - lastTime) / nanoSecondConversion;

   //Adjust engine logic speed according to this.fps
   while(deltaTime >= 1)
   {
    update();
    deltaTime = 0;
   }

   //Render the next frame and push the current time variable to the last time variable (for continuous looping)
   render();
   lastTime = now;
  }
 }
 
 //Spawn particles in a square around the xx and yy position
 public void particle(Element e, int size, int xx, int yy)
 {
  // int o = size/2;
  // for(int w=xx;w<xx +size;w++)
  //  for (int h=yy;h<yy+size;h++)
  //  { 
  //   if (e == air || grid[((w-o) + (h-o) * width)] == air)
  //   grid[((w-o) + (h-o) * width)] = e;
  //  }
 }

 //Executed once per key press, doesn't do anything for when keys are held down
 public void keyTyped(int keyCode)
 {
  switch(keyCode)
  {
    //Tilde key
    case 192:
      if (this.debug) this.debug = false;
      else this.debug = true;
      break;

    //Spacebar
    case 32:
      if (this.fps == this.realtime) this.fps = this.paused;
      else this.fps = this.realtime; 
    break;
      
    //1-9 keys
    case 49: //1 key
    case 50: //2 key
    case 51: //3 key
    case 52: //...
    case 53:
    case 54:
    case 55:
    case 56:
    case 57: //9 key
      //Set cursor size based on what key was pressed
      cursorSize = (keyCode-48) * 10;
      break;
  }
 }



  public static void main(String[] args)
   {
    //Declare and init the engine class
     Engine engine = new Engine();
     //Assign the class to a new thread
     Thread engineThread = new Thread(engine);
     //Start the thread
     engineThread.start();
   }

   //Get mouse input
   public MouseHandler getMouseHandler()
   {
    return mouse;
   }
}
