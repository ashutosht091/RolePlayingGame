package model;

import java.io.Serializable;

public class Question implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3759103354805942020L;
	private int questionId;
	private Position position;
	private int mapId;
	private String questionDescription;
	private boolean isAnswered;
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isAnswered ? 1231 : 1237);
		result = prime * result + mapId;
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((questionDescription == null) ? 0 : questionDescription.hashCode());
		result = prime * result + questionId;
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
		Question other = (Question) obj;
		if (isAnswered != other.isAnswered)
			return false;
		if (mapId != other.mapId)
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (questionDescription == null) {
			if (other.questionDescription != null)
				return false;
		} else if (!questionDescription.equals(other.questionDescription))
			return false;
		if (questionId != other.questionId)
			return false;
		return true;
	}
	
	
	public boolean isAnswered() {
		return isAnswered;
	}
	public void setAnswered(boolean isAnswered) {
		this.isAnswered = isAnswered;
	}
	public int getMapId() {
		return mapId;
	}
	public void setMapId(int mapId) {
		this.mapId = mapId;
	}
	public String getQuestionDescription() {
		return questionDescription;
	}
	public void setQuestionDescription(String questionDescription) {
		this.questionDescription = questionDescription;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", position=" + position + ", mapId=" + mapId
				+ ", questionDescription=" + questionDescription + ", isAnswered=" + isAnswered + "]";
	}
	
	

}
