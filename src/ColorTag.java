
public class ColorTag {
    
    private static final String[] colors = {"RED", "BLUE", "GREEN", "GRAY", "YELLOW", "ORANGE"};
    private static int colorSelector = 0;
    private final String color;
    private final int index;	// index at which we want to apply the tag 
    
    public ColorTag(int index) {
	this.color = colors[colorSelector % colors.length];
	this.index = index;
	colorSelector ++;
    }
    
    // copy constructor
    public ColorTag(ColorTag tag, int index) {
	this.color = tag.getColor();
	this.index = index;
    }
    
    public String getColor() {
	return color;
    }
    
    public int getIndex() {
	return index;
    }
    
    public String toString() {
	return "\\color[" + color + "]";
    }
}
