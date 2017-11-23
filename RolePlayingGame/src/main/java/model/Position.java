package model;

import java.io.Serializable;

/**
 * The Position class is the model of x and y coordinates of a particular Character
 * 
 * 
 *
 */
public class Position implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8305473632072388850L;
	private int positionX;
	private int positionY;
	private boolean isVisited;

	public Position(int positionX, int positionY,boolean isVisited) {
		super();
		this.positionX = positionX;
		this.positionY = positionY;
		this.isVisited=isVisited;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isVisited ? 1231 : 1237);
		result = prime * result + positionX;
		result = prime * result + positionY;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (isVisited != other.isVisited)
			return false;
		if (positionX != other.positionX)
			return false;
		if (positionY != other.positionY)
			return false;
		return true;
	}



	public boolean isVisited() {
		return isVisited;
	}
	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}
	public int getPositionX() {
		return positionX;
	}
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}
	public int getPositionY() {
		return positionY;
	}
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
	
	

	
}
