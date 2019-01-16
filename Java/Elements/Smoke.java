package Elements;

public class Smoke extends Gas
{
 public Smoke()
 {
  //Id, Name, Tooltip, Type, Color, Density
  super((byte)13,"Smoke"," It's smokey",(byte)0,0xcccccc,-1);


 }

 @Override
 public byte[] interaction(Element[] elements, byte[] grid, int i, int width)
 {
  grid = super.interaction(elements, grid, i, width);
  return grid;
 }
 
}