package Elements.Types;

import Elements.Element;

public class Powder extends Element {


    public Powder(byte id, String name, String tooltip, byte type, int color, double density) {
        super(id, name, tooltip, type, color, density);
    }

    public byte[] interaction(Element[] elements, byte[] grid, int i, int width) {
        grid = super.interaction(elements, grid, i, width);

        int left = i - 1;
        int right = i + 1;
        int up = i - width;
        int down = i + width;
        int offset = (int) (Math.random() * 3) - 1;


        if (elements[grid[i]].density > elements[grid[down + offset]].density) {
            grid = Element.swap(grid, i, down + offset);
        }

        return grid;
    }
}