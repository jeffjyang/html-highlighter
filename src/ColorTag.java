
public class ColorTag {
    
    private static final String[] colors = {"RED", "YELLOW", "GREEN", "TURQUOISE", "PINK", "DARKGREEN", "DARKGRAY"};
    private static int colorSelector = 0;
    private final String color;
    private final int index;	// index at which we want to apply the tag 
    
    /**
     * ColorTag constructor. The ColorTag instantiated will have a different
     * color than the last ColorTag instantiated.
     *  
     * @param index
     * 		represents the index of the location we want to insert the tag.
     * 
     * effects: Creates a new ColorTag object for the provided index with a 
     * 		different color than the previous ColorTag instantiated.
     */
    public ColorTag(int index) {
	this.color = colors[colorSelector % colors.length];
	this.index = index;
	colorSelector ++;
    }
    
    /**
     * ColorTag partial copy constructor.
     * 
     * @param tag
     * 		is a ColorTag that is our desired color for this ColorTag.
     * @param index
     * 		represents the index of the location we want to insert the tag.
     * 
     * effects: Creates a new ColorTag object for the provided index that is 
     * 		the same color as tag.
     */
    public ColorTag(ColorTag tag, int index) {
	this.color = tag.getColor();
	this.index = index;
    }
    
    
    /** 
     * Returns the color of this ColorTag
     * 
     * @return the color of this ColorTag
     */
    public String getColor() {
	return color;
    }
    
    /**
     * Returns the index at which we want to insert this ColorTag.
     * 
     * @return the index at which we want to insert the ColorTag.
     */
    public int getIndex() {
	return index;
    }
    
    /**
     * Returns a string representation of the ColorTag. Output is formatted as
     * follows: \color[MYCOLOR]
     * 
     * @return a string representation of the ColorTag.
     */
    @Override
    public String toString() {
	return "\\color[" + color + "]";
    }
    
    /**
     * Checks if two ColorTags are equal. Two ColorTags are considered equal if
     * they both have the same color and index.
     * 
     * @return true if the two ColorTags are equal, false otherwise.
     */
    @Override 
    public boolean equals(Object o) {
	if (o instanceof ColorTag) {
	    ColorTag other = (ColorTag) o;
	    return color.equals(other.color) && index == other.index;
	} else {
	    return false;
	}
	
    }
}
