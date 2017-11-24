package model;

import java.io.Serializable;
import java.util.Map;
/**
 * This class is model for a game Map . its attributes are dimensions , name and questions 
 * of map.
 * @author ashutosh
 *
 */
public class GameMap implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5414703979935244903L;
	private String mapName;
	private int mapId;
	private int bordersX;
	private int bordersY;
	Map<Position,Question> questions;
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bordersX;
		result = prime * result + bordersY;
		result = prime * result + mapId;
		result = prime * result + ((mapName == null) ? 0 : mapName.hashCode());
		result = prime * result + ((questions == null) ? 0 : questions.hashCode());
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
		GameMap other = (GameMap) obj;
		if (bordersX != other.bordersX)
			return false;
		if (bordersY != other.bordersY)
			return false;
		if (mapId != other.mapId)
			return false;
		if (mapName == null) {
			if (other.mapName != null)
				return false;
		} else if (!mapName.equals(other.mapName))
			return false;
		if (questions == null) {
			if (other.questions != null)
				return false;
		} else if (!questions.equals(other.questions))
			return false;
		return true;
	}
	public Map<Position, Question> getQuestions() {
		return questions;
	}
	public void setQuestions(Map<Position, Question> questions) {
		this.questions = questions;
	}
	public String getMapName() {
		return mapName;
	}
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}
	public int getMapId() {
		return mapId;
	}
	public void setMapId(int mapId) {
		this.mapId = mapId;
	}
	public int getBordersX() {
		return bordersX;
	}
	public void setBordersX(int bordersX) {
		this.bordersX = bordersX;
	}
	public int getBordersY() {
		return bordersY;
	}
	public void setBordersY(int bordersY) {
		this.bordersY = bordersY;
	}
	@Override
	public String toString() {
		return "GameMap [mapName=" + mapName + ", mapId=" + mapId + ", bordersX=" + bordersX + ", bordersY=" + bordersY
				+ ", questions=" + questions + "]";
	}
	
	

}
