package model;

import java.io.Serializable;
import java.util.Arrays;

public class Game implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3665596482964956966L;
	private int gameId;
	private Player player;
	private GameCharacter gameCharacter;
	private GameMap map;
	private Position[][] positionGrid ;
	
	public Game(int gameId, Player player, GameCharacter gameCharacter, GameMap map) {
		super();
		this.gameId = gameId;
		this.player = player;
		this.gameCharacter = gameCharacter;
		this.map = map;
		this.positionGrid = new Position[map.getBordersX()][map.getBordersY()];
		initializePositions(map.getBordersX(),map.getBordersY(),this.positionGrid);
		
		
	}
	
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gameCharacter == null) ? 0 : gameCharacter.hashCode());
		result = prime * result + gameId;
		result = prime * result + ((map == null) ? 0 : map.hashCode());
		result = prime * result + ((player == null) ? 0 : player.hashCode());
		result = prime * result + Arrays.deepHashCode(positionGrid);
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
		Game other = (Game) obj;
		if (gameCharacter == null) {
			if (other.gameCharacter != null)
				return false;
		} else if (!gameCharacter.equals(other.gameCharacter))
			return false;
		if (gameId != other.gameId)
			return false;
		if (map == null) {
			if (other.map != null)
				return false;
		} else if (!map.equals(other.map))
			return false;
		if (player == null) {
			if (other.player != null)
				return false;
		} else if (!player.equals(other.player))
			return false;
		if (!Arrays.deepEquals(positionGrid, other.positionGrid))
			return false;
		return true;
	}




	public Position[][] getPositionGrid() {
		return positionGrid;
	}

	public void setPositionGrid(Position[][] positionGrid) {
		this.positionGrid = positionGrid;
	}

	private void initializePositions(int x , int y,Position[][] positionGrid)
	{
		for(int i =0 ; i<x;i++)
		{
			for(int j =0;j<y;j++)
			{
				Position position = new Position(x,y,false);
				positionGrid[i][j] = position;
			}
		}
	}
	
	public GameMap getMap() {
		return map;
	}
	public void setMap(GameMap map) {
		this.map = map;
	}
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public GameCharacter getGameCharacter() {
		return gameCharacter;
	}
	public void setGameCharacter(GameCharacter gameCharacter) {
		this.gameCharacter = gameCharacter;
	}
	
	

	@Override
	public String toString() {
		return "Game [gameId=" + gameId + ", player=" + player + ", gameCharacter=" + gameCharacter + ", map=" + map
				+ ", positionGrid=" + Arrays.toString(positionGrid) + "]";
	}
	
	
	
	
}
