public class GuiHandler
{

 Engine engine;
 String output;
 String spacing = "     |     ";

 public GuiHandler(Engine engine)
 {
  this.engine = engine;
  this.output = output;

 }

 public String updateGUI()
 {
   
   output = ""+
   "FPS: " + (engine.frames + 1) / (engine.runTime + 1) + spacing +
   "CURSOR SIZE: " + (engine.cursorSize) + spacing +
   "ELEMENT: " + (engine.elements[engine.scroll].name);

   if (engine.fps == engine.paused) output += spacing + "PAUSED";
   return output;

 }
}