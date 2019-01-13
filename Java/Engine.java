import java.awt.Canvas; //Canvas to engine on
import java.awt.Color; //Color definitions
import java.awt.image.BufferStrategy; //Buffering object
import java.awt.Graphics; //Engineing library
import java.lang.Runnable; //Create a runnable
import java.lang.Thread; // import threads
import javax.swing.JFrame; //Display a JFrame
import java.awt.event.MouseEvent;
import java.util.Arrays; 

//The Engine class needs to have JFrame methods and the Runnable run() method
public class Engine extends JFrame implements Runnable
{

int realtime;
int paused;
int fps;
 public static int width = 800; //Width of the window 
 public static int height = 800; //Height of the window
 public static int alpha = 0xFFFF00DC; //Defined alpha color
 public static Color textColor = new Color(0xffffff);
 public  Element[] grid = new Element[width * height];
 private boolean[] changedCells = new boolean[width*height];

 public int frames = 0;
 private int offset = 0;
 int speed = 2;

 int runTime;
 boolean debug;
 int cursorSize;
 int[] movements;
 //Create a canvas to engine on
 private Canvas canvas = new Canvas();

public Element air = new Element("Air",3,0x000000,0.0);
 //Powders
public Element sand = new Element("Sand",1,0xc2b280 ,1.0);
public Element dirt = new Element("Dirt",1,0x8B4513,1.0);
public Element snow = new Element("Snow",1,0xe7e7e7,1.0);
public Element seed = new Element("Seed",1,0x368505,1.0);

//Solids
public Element stone = new Element("Stone",0,0x808080,2.0);
public Element wood = new Element("Wood",0,0x855e42,2.0);
public Element ice = new Element("Ice",0,0xadd8e6,2.0);
public Element plant = new Element("Plant",0,0x43921b ,1.0);
public Element spout = new Element("Spout",0,0xffd700 ,1.0);



//Liquids
public Element water = new Element("Water",2,0x0000ff,0.5);
public Element oil = new Element("Oil",2,0x2c2416,0.4);
public Element acid = new Element("Acid",2,0xD0FB3C,0.6);


 public Element[] elements = {air, sand, dirt, snow, seed, stone, wood, ice, plant, spout, water, oil, acid};
 public int scroll = 1;
 //Define handler objects
 private RenderHandler renderer;
 private MouseHandler mouse = new MouseHandler(this);
 private KeyboardHandler keyboard = new KeyboardHandler(this);
 private GuiHandler gui = new GuiHandler(this);


 //Default engine constructor
 public Engine()
 {
  runTime = 0;
  debug = true;
  cursorSize = 10;
  realtime = 60;
  paused = 0;
  fps = realtime;
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
  //setExtendedState(JFrame.MAXIMIZED_BOTH); 
  //
  //Create buffer strategy - cache and display frames without stutters or flashing
  canvas.createBufferStrategy(2);
  //Initialize the render handler at the size of the window
  renderer = new RenderHandler(getWidth(),getHeight());
  //Add listeners to recieve input
  canvas.addKeyListener(keyboard); //Keyboard input
  canvas.addFocusListener(keyboard); //Check if window is in focus
  canvas.addMouseListener(mouse);
  canvas.addMouseMotionListener(mouse);
  canvas.addMouseWheelListener(mouse);
  Arrays.fill(grid, air);
  

 }

 //Update any game logic logic
public void update()
 {   
   
  if (keyboard.escape())  System.exit(0);
  //Add 1 to runTime every second
  if (this.fps == this.realtime)
  {
   frames++;
  if (frames % this.fps == 0)
  {
   this.runTime += 1;
  }

  Arrays.fill(changedCells, false);
  for (int i = grid.length - 1; i>0; i--)
  {
    
    if (i + width < grid.length - 1){
      if (!changedCells[i]) {
    switch(grid[i].type)
    {
      case 0: //Solid
      //Solids don't move, no physics to simulate
      if (grid[i].name == "Ice")
      {
        for(int xx=-1;xx<=1;xx++)
          for(int yy=-1;yy<=1;yy++)
            if (grid[ i + (width*yy) + xx].name == "Water" || grid[ i + (width*yy) + xx].name == "Snow")
              if (Math.random() > 0.99)
                grid[ i + (width*yy) + xx] = grid[i];
      }

       if (grid[i].name == "Plant")
      {
        int plantCount = 0;
        for(int xx=-1;xx<=1;xx++)
          for(int yy=-1;yy<=1;yy++)
          {
            if (grid[ i + (width*yy) + xx].name == "Water")
            {
                grid[ i + (width*yy) + xx] = grid[i];
                changedCells[ i + (width*yy) + xx] = true;
            }
            if (grid[ i + (width*yy) + xx].name == "Plant")
            {
              plantCount++;
            }
          } 
          if (plantCount > 5 || plantCount < 2)
            grid[i] = air;
      }

      if (grid[i].name == "Spout")
      {
        if (grid[i + width] == air)
        if (Math.random() > 0.90)
        grid[i + width] = water;
      }
      break;

      case 1: //Powder
      if (Math.random() > 0.50)
      {
        if (Math.random() > 0.50) offset = -1;
            else offset = 1;
        }   else offset = 0;

        if (grid[i].density > grid[i + width + offset].density)
        {
          Element swap = grid[i + width + offset];
          grid[i + width + offset] = grid[i];
          grid[i] = swap;
          changedCells[i] = true;
          changedCells[i + width + offset] = true;
        } else 
        if (grid[i].density > grid[i + width - offset].density)
        {
          Element swap = grid[i + width - offset];
          grid[i + width - offset] = grid[i];
          grid[i] = swap;
          changedCells[i] = true;
          changedCells[i + width - offset] = true;
        } 

        if (grid[i].name == "Snow")
      {
        for(int xx=-1;xx<=1;xx++)
          for(int yy=-1;yy<=1;yy++)
            if (grid[ i + (width*yy) + xx].name == "Water")
                grid[i] = ice;
      }
        if (grid[i].name == "Seed")
      {
        for(int xx=-1;xx<=1;xx++)
          for(int yy=-1;yy<=1;yy++)
            if (grid[ i + (width*yy) + xx].name == "Water")
                grid[i] = plant;
      }
       
      break;

      case 2: //Liquid
        if (Math.random() > 0.50) offset = -1;
        else offset = 1;


        if (grid[i].density > grid[i + width + offset].density)
        {
          Element swap = grid[i + width + offset];
          grid[i + width + offset] = grid[i];
          grid[i] = swap;
          changedCells[i] = true;
          changedCells[i + width + offset] = true;
        } 
        if (grid[i].density > grid[i + width - offset].density)
        {
          Element swap = grid[i + width - offset];
          grid[i + width - offset] = grid[i];
          grid[i] = swap;
          changedCells[i] = true;
          changedCells[i + width - offset] = true;
        } 
        if (grid[i].density > grid[i + offset].density)
        {
          Element swap = grid[i + offset];
          grid[i + offset] = grid[i];
          grid[i] = swap;
          changedCells[i] = true;
          changedCells[i + offset] = true;
        } 
        if (grid[i].density > grid[i - offset].density)
        {
          Element swap = grid[i- offset];
          grid[i - offset] = grid[i];
          grid[i] = swap;
          changedCells[i] = true;
          changedCells[i- offset] = true;
        } 

        if (grid[i].name == "Acid")
      {
        for(int xx=-1;xx<=1;xx++)
          for(int yy=-1;yy<=1;yy++)
          {
            if (grid[ i + (width*yy) + xx].name != "Air")
              grid[ i + (width*yy) + xx] = air;
              grid[i] = air;
              changedCells[i] = true;
              changedCells[ i + (width*yy) + xx] = true;
          }
      }
      break;

      case 3: //Air
      break;
    }
  }
    }
    
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
   for (int i=0; i < grid.length; i++)
   renderer.setPixel(grid[i].color, i % width, (i -(i % width))/width);
   //Show the buffers
   bufferStrategy.show();
 }

public void leftClick(int x, int y)
 {
  particle(elements[scroll],cursorSize,x,y);
  
 }
public void rightClick(int x, int y)
 {
  particle(air,cursorSize,x,y);
  
 }
 public void middleClick(int x, int y)
 {
  particle(air,cursorSize,x,y);
  
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
 
 public void particle(Element e, int size, int xx, int yy)
 {
  int o = size/2;
  for(int w=xx;w<xx +size;w++)
   for (int h=yy;h<yy+size;h++)
   { 
    if (e == air || grid[((w-o) + (h-o) * width)] == air)
    grid[((w-o) + (h-o) * width)] = e;
   }
 }

 public void keyTyped(int keyCode)
 {
  switch(keyCode)
  {
    //Tilde key
    case 192:
      if (this.debug) this.debug = false;
      else this.debug = true;
      break;

    case 32:
      if (this.fps == this.realtime) this.fps = this.paused;
      else this.fps = this.realtime; 
    break;
      
    case 49: //1 key
    case 50: //2 key
    case 51: //3 key
    case 52:
    case 53:
    case 54:
    case 55:
    case 56:
    case 57:
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
