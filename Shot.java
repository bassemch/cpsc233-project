import java.awt.Rectangle;

public class Shot extends Rectangle {
	
	public Shot(int x, int y, int width, int heigth) {
		super(x, y, width, heigth);
	}
	
	public boolean overlapEnemy(Enemy e) {
		return this.intersects(e);
		
//		// shot over enemy
//		if (this.yPos - this.ySize > e.getYpos()) {
//			return false;
//		}
//		//shot below enemy
//		if (this.yPos  < e.getYpos() + e.getYsize()) {
//			return false;
//		}
//		//shot right to enemy
//		if (this.xPos > e.getXpos() + e.getXsize()) {
//			return false;
//		}	
//		//shot left to enemy
//		if (this.xPos + this.xSize  < e.getXpos()) {
//			return false;
//		}	
//		return true;
	}
	public boolean overlapBar(Wall w) {
		return this.intersects(w);
	}
}
