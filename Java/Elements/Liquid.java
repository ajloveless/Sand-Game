package Elements;

public class Liquid extends Element
{

    public Liquid(byte id, String name, String tooltip, byte type, int color, double density)
    {
        super(id, name, tooltip, type, color, density);
    }

    public byte[] interaction(Element[] elements, byte[] grid, int i, int width)
    {
        grid = super.interaction(elements, grid, i, width);
        return grid;
    }
}