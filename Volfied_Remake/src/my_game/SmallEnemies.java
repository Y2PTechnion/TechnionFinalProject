package my_game;

public class SmallEnemies {

	SmallEnemy[] smallEnemies = new SmallEnemy[3];

	public void initSmallEnemies(Field field) {
		
		SmallEnemy s; 
		s = new SmallEnemy("smallEnemy1", field);
		s.setLocation(new BoardPoint(11,7));
		smallEnemies[0] = s;

		s = new SmallEnemy("smallEnemy2", field);
		s.setLocation(new BoardPoint(20,13));
		smallEnemies[1] = s;

		s = new SmallEnemy("smallEnemy3", field);
		s.setLocation(new BoardPoint(1,13));
		smallEnemies[2] = s;
	}
	
	public void move() {
		for (SmallEnemy s: smallEnemies) {
			s.move();
		}
	}
	
	public SmallEnemy[] getSmallEnemies() {
		return smallEnemies;
	}

	public SmallEnemy firstSmallEnemy() {
		return smallEnemies[0];
	}

	public SmallEnemy secondSmallEnemy() {
		return smallEnemies[1];
	}

	public SmallEnemy thirdSmallEnemy() {
		return smallEnemies[2];
	}
}
