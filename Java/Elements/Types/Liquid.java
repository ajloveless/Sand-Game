package Elements.Types;

import Elements.Element;

public class Liquid extends Element {

    public Liquid(byte id, String name, String tooltip, byte type, int color, double density) {
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
        } else if ((elements[grid[i]].density < elements[grid[down]].density)) {
            int rightSpace = 0;
            int leftSpace = 0;
            for (int j = 0; j < 3; j++) {
                if (elements[grid[i]].density > elements[grid[left - j]].density) leftSpace++;
                else break;
            }

            for (int j = 0; j < 3; j++) {
                if (elements[grid[i]].density > elements[grid[right + j]].density) rightSpace++;
                else break;
            }

            if (rightSpace > leftSpace) {
                grid = Element.swap(grid, i, right + rightSpace);
            } else if (leftSpace > rightSpace){
                grid = Element.swap(grid, i, left - leftSpace);
            }
            else grid = Element.swap(grid, i, i + offset);

        } else if ((elements[grid[i]].density == elements[grid[down + offset]].density)) {
            grid = Element.swap(grid, i, i + offset);
        }

        return grid;
    }
}
