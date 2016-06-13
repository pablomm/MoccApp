package pads.antiquary.model.item;

/**
 * Class with the structure and methods to store the dimension of an item
 * 
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 * 
 */
public class Dimension {

	/** Weight of the item */
	private Integer weight;

	/** Height of the item */
	private Integer height;

	/** Width of the item */
	private Integer width;

	/** Length of the item */
	private Integer length;

	public Dimension(Integer weight, Integer height, Integer width, Integer length) {
		super();
		this.weight = weight;
		this.height = height;
		this.width = width;
		this.length = length;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	@Override
	public String toString() {
		return this.weight + ";" + this.height + ";" + this.width + ";" + this.length;
	}

}
