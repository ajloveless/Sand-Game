package Elements.Subtypes;

import Elements.Element;

public class Air extends Element {
    public Air() {
        //Id, Name, Tooltip, Type, Color, Density
        super((byte) 0, "Air", "Does nothing", (byte) 0, 0x000000, 0);

    }

    @Override
    public byte[] interaction(Element[] elements, byte[] grid, int i, int width) {
        grid = super.interaction(elements, grid, i, width);
        return grid;
    }

}