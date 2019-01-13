import java.awt.event.FocusEvent; //Window focus event
import java.awt.event.FocusListener; //Import for checking window focus
import java.awt.event.KeyEvent; //Keyboard events
import java.awt.event.KeyListener; //Check for keyboard events

public class KeyboardHandler implements KeyListener, FocusListener 
{
 public boolean[] keys = new boolean[120]; //Keys we're going to listen to

 //Get the engine
 private Engine engine; 

 public KeyboardHandler(Engine engine)
 {
  this.engine = engine;
 }

 //When a key is pressed down
 public void keyPressed(KeyEvent event) 
 {

  int keyCode = event.getKeyCode(); //Get the keycode
  engine.keyTyped(event.getKeyCode()); //Debug specific keybinds
  if(keyCode < keys.length) //If we are listening for it,
   keys[keyCode] = true; //Put it in an array of pressed down keys
  
 }

 public void keyReleased(KeyEvent event)
 {
  int keyCode = event.getKeyCode();
  if(keyCode < keys.length)
   keys[keyCode] = false;
 }

 //Don't look for keypresses when the window isn't in focus
 public void focusLost(FocusEvent event)
 {
  for(int i = 0; i < keys.length; i++)
   keys[i] = false;
 }

 public void keyTyped(KeyEvent event) {}

 public void focusGained(FocusEvent event) {}


 //Keybinds
 public boolean up()
 {
  return keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP];
 }

 public boolean down()
 {
  return keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN];
 }

 public boolean left()
 {
  return keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT];
 }

 public boolean right()
 {
  return keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT];
 }

 public boolean escape()
 {
  return keys[KeyEvent.VK_ESCAPE];
 }
}