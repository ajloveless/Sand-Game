import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;


//Reminder that each method can handle mouse clicks differently
//(the left and right mouse buttons do different things)



//Implement all mouse methods to test for
public class MouseHandler implements MouseListener, MouseMotionListener, MouseWheelListener
{
 //Declare engine object
 private Engine engine;
 public MouseHandler(Engine engine)
 { 
  this.engine = engine; 
 }

 //When the mouse is pressed and released
 public void mouseClicked(MouseEvent event) 
 {  

    }  
    //When the mouse is pressed and dragged
    public void mouseDragged(MouseEvent event) 
    {  
    int b1 = MouseEvent.BUTTON1_DOWN_MASK;
    int b2 = MouseEvent.BUTTON2_DOWN_MASK;
    int b3 = MouseEvent.BUTTON3_DOWN_MASK;


        if ((event.getModifiersEx() & (b1 | b2 | b3)) == b1) 
        {
            engine.leftClick(event.getX(), event.getY());
        } else if ((event.getModifiersEx() & (b1 | b2 | b3)) == b3) 
        {
            engine.rightClick(event.getX(), event.getY());
        }
        else if ((event.getModifiersEx() & (b1 | b2 | b3)) == b2) 
        {
            engine.middleClick(event.getX(), event.getY());
        }
    }
    //When the mouse enters the window
    public void mouseEntered(MouseEvent event) 
    {  

    }  
    //When the mouse exits the window
    public void mouseExited(MouseEvent event) 
    {  

    }  
    //When the mouse moves
    public void mouseMoved(MouseEvent event) 
    {  
    }  
    //When the mouse is pressed down
    public void mousePressed(MouseEvent event) 
    {  
      //Run left click method in engine class
       if(event.getButton() == MouseEvent.BUTTON1)
   engine.leftClick(event.getX(), event.getY());
 }
 //When the mouse is released
    public void mouseReleased(MouseEvent event) 
    {  

    }  
    
    public void mouseWheelMoved(MouseWheelEvent event)
    {
        String message;
        int notches = event.getWheelRotation();
        engine.scroll -= notches;
        //Keep in bounds
        if (engine.scroll < 1) engine.scroll = 1;
        if (engine.scroll > 404) engine.scroll = 404;

    }
}