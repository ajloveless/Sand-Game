package Elements.Types;

import Elements.Element;

public class Gas extends Element {

    public Gas(byte id, String name, String tooltip, byte type, int color, double density) {
        super(id, name, tooltip, type, color, density);
    }

    public byte[] interaction(Element[] elements, byte[] grid, int i, int width) {
        grid = super.interaction(elements, grid, i, width);
        
        int left = i - 1;
        int right = i + 1;
        int up = i - width;
        int down = i + width;
        int offsetx = (int) Math.random() * 8 - 2;
        int offsety = (int) Math.random() * 8 - 2;
        

        if (i + offsetx + offsety*width > 0 && i + offsetx + offsety*width < grid.length)
        if(elements[grid[i]].density < elements[grid[i + offsetx + offsety*width]].density)
        {
            byte swap = grid[i + offsetx + offsety*width];
            grid[i + offsetx + offsety*width] = grid[i];
            grid[i] = swap;
        }
        return grid;
    }
}