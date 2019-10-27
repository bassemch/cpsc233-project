import java.awt.event.KeyEvent;
import java.util.ArrayList;

private class Map {
private int aXLen;
private int aYLen;
private int yPosDeAndH;
private ArrayList<Enemy> EnemyList = new ArrayList();
private Player aPlayer;
private ArrayList<Wall> BarList = new ArrayList();
private ArrayList<Shot> ShotList = new ArrayList();

private char EnemyMovingDirection = 'l'; // can be l,r,d
private int EnemyMoveToSide = 10; 
private char EnemyMoveBefore = 'l';
private int PlayerMove = 10;

Map(int aXLen, int aYLen, int yPosDeandHe, ArrayList<Enemy> aEnemyList,  Player aPlayer, ArrayList<Bar> bars, ArrayList<shot> shots){
this.aXLen = aXlen;
this.aYLen = aYlen;
this.yPosDeandHe = yPosDeandHe;
this.EnemyList = aEnemyList;
this.aPlayer = aPlayer;
this.BarList = bars;
this.ShotList = shots;


public void updatePos() {
	//Enemies:
	//check where Enemies need to move
	
	if (EnemyMovingDirection == 'r') {
		if (this.getMostRightEnemy().getX() < aXLen - EnemyMoveToSide)
			for (Enemy i : EnemyList){
				i.move(EnemyMoveToSide, 0);
			}else {
				EnemyMoveBefore = EnemyMovingDirection;
				EnemyMovingDirection = 'd';
			}
	}
	if (EnemyMovingDirection == 'l') {
		if (this.getMostLeftEnemy().getX() > EnemyMoveToSide)
			for (Enemy i : EnemyList){
				i.move(-EnemyMoveToSide, 0);
			}else {
				EnemyMoveBefore = EnemyMovingDirection;
				EnemyMovingDirection = 'd';
			}
	}
	if (EnemyMovingDirection == 'd') {
		if (EnemyMoveBefore == 'r') {
			//move to left
			for (Enemy i : EnemyList){
				i.move(-EnemyMoveToSide, 0);
			}
			EnemyMoveBefore = EnemyMovingDirection;
			EnemyMovingDirection = 'l';
		}else {
			for (Enemy i : EnemyList){
				i.move(EnemyMoveToSide, 0);
			}
			EnemyMoveBefore = EnemyMovingDirection;
			EnemyMovingDirection = 'r';
			}
	}	
//Shots:
	for (Shot i : ShotList) {
		i.move(0, 10);
	}
//Player:
	//if key was pressed move to right or left
}
public void updatePlayerPos(KeyEvent e) {
	int command = e.getKeyCode();
    if (command == KeyEvent.VK_LEFT) {
        aPlayer.move(-PlayerMove, 0);
    }
    if (command == KeyEvent.VK_RIGHT) {
    	aPlayer.move(PlayerMove, 0);
    }
}

public Enemy getMostRightEnemy() {
	Enemy e = EnemyList.get(0);
	for (Enemy i : EnemyList) {
		if (e.getX() < i.getX()) {
			e = i;
		}
	}
	return e;
}
public Enemy getMostLeftEnemy() {
	Enemy e = EnemyList.get(0);
	for (Enemy i : EnemyList) {
		if (e.getX() > i.getX()) {
			e = i;
		}
	}
	return e;
}

private int getMapxLen(){
return aXLen;
}


private int getMapYLen(){
return aYLen;
}

private int getPosDeathandH(){
return yPosDeAndH;
}


private ArrayList<Enemy> getEnemyList(){
return EnemyList;
}

private Player getPlayer(){
return aPlayer;
}


private ArrayList<Bar> getBarList(){
return BarList;
}


private ArrayList<Shot> getShotList(){
return ShotList;
}



private void CreateEnemyList(Enemy aEnemy, int numEn) {
int e = numEn;
for (int i = 0; i < e; i++ ) {
Enemy bigboi = new Enemy(100 + i * 100)
}

EnemyList.add(bigboi);

}
}
	

