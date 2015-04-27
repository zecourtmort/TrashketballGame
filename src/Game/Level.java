package Game;

public class Level {
	private int solutionAngle;
	private int trashHeight;
	private int trashDistance;
	
	public Level(int trashHeight, int trashDistance) {
		super();
		//this.solutionAngle = solutionAngle;
		this.trashHeight = trashHeight;
		this.trashDistance = trashDistance;
	}
	
	public Level() {
		// TODO Auto-generated constructor stub
	}

	public void drawBoard(){
		
	}

	public int getSolutionAngle() {
		return solutionAngle;
	}

	public int getTrashHeight() {
		return trashHeight;
	}

	public int getTrashDistance() {
		return trashDistance;
	}

	public void setSolutionAngle(int solutionAngle) {
		this.solutionAngle = solutionAngle;
	}

	public void setTrashHeight(int trashHeight) {
		this.trashHeight = trashHeight;
	}

	public void setTrashDistance(int trashDistance) {
		this.trashDistance = trashDistance;
	}
	
}
