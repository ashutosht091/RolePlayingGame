package model;

import java.io.Serializable;

/**
 * The Experience class is the model of the experince point of a user wrapping an int variable.
 * 
 *
 *
 */
public class Experience implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4394199014983288564L;
	private int point;

	public Experience(int point) {
		this.point = point;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + point;
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
		Experience other = (Experience) obj;
		if (point != other.point)
			return false;
		return true;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
}
