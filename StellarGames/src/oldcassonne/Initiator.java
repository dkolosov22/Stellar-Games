package oldcassonne;

public class Initiator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VariableContainer.map[VariableContainer.mapSize / 2][VariableContainer.mapSize / 2] = 1111;
		VariableContainer.tileID = ((VariableContainer.rand.nextInt(VariableContainer.tileTypes) + 1) * 1000) + // North
				((VariableContainer.rand.nextInt(VariableContainer.tileTypes) + 1) * 100) + // East
				((VariableContainer.rand.nextInt(VariableContainer.tileTypes) + 1) * 10) + // South
				(VariableContainer.rand.nextInt(VariableContainer.tileTypes) + 1); // West
		new GUIHandler();
	}
}