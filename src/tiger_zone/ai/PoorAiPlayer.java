package tiger_zone.ai;

import tiger_zone.Game;
import tiger_zone.Player;
import tiger_zone.PossibleMovesRule;
import tiger_zone.Tiger;
import tiger_zone.Tile;

/**
 * A pretty bad AI player which simply makes the first possible move found. This exists simply to demonstrate how an AI
 * player should function.
 */
public class PoorAiPlayer extends AiPlayer {
	private int move_num = 1;
	
	/**
	 * Create a new instance of <code>PoorAiPlayer</code>.
	 *
	 * @param game The game instance.
	 */
	public PoorAiPlayer(Game game) {
		super(game);
	}

	/**
	 * Get the Player instance that corresponds to this AiPlayer.
	 * 
	 * @return player
	 */
	private final Player getPlayer() {
		int i = 0;
		for (AiPlayer player : this.game.getAiPlayers()) {
			if (player.equals(this)) {
				return this.game.getPlayers().get(i);
			}
			i++;
		}
		return null;
	}
	
	/**
	 * Have this AI player place a tile on the board. To maximize points, AI places its tigers on the first available
	 * den or unique animal tile.
	 */
	public final void makeMove() {
		long millis = System.currentTimeMillis();
		Tile current = this.game.getCurrentTile();
		PossibleMovesRule pmr = new PossibleMovesRule(this.game.getBoard(), 0, 0, current, false);

		// no possible move; simply pass our turn (not what's actually supposed to happen)
		if (!pmr.evaluate()) {
			return;
		}

		int[] move = pmr.getFirstPossibleMove();
		while (current.getRotation() != move[2]) {
			current.rotate();
		}

		game.getBoard().addTile(move[0], move[1], current);
		
		Player currentPlayer = this.getPlayer();
		
		int i = -1;
		
		// If this tile has a den, place a tiger on it if we have one available.
		if (current.hasDen() && currentPlayer.getTigers().size() > 0) {
			Tiger tiger = currentPlayer.getTigers().pop();
			current.addTiger(5, tiger);
			i = 5;
		}
		else if(current.hasAnimal()) {
			for (i = 1; i < 10; i++) {
				if (current.getZone(i) == 'l') {
					boolean isValid = this.game.getBoard().validTigerPlacement(move[0], move[1], i, false);
					if (isValid && currentPlayer.getTigers().size() > 0) {
						Tiger tiger = currentPlayer.getTigers().pop();
						current.addTiger(i, tiger);
						break;
					}
				}
			}
			i = (i == 10) ? -1 : i;
		}
		
		Player me = new Player(1,"s1");
		int score = me.updateScore(move[0], move[1], this.game.getBoard());
		System.err.println("Score: " + score);
		
		millis = System.currentTimeMillis() - millis;
		if( i != -1)
		System.err.println(currentPlayer + "'s Move: " + move_num++ + " \tCoor: (" + move[0] +"," + move[1] +") \ttile: "
				+ current.getSide(0)+current.getSide(1)+current.getSide(2)+current.getSide(3)
				+ "\tTiger Locations: "  + ((i != -1)? i:"N/A")
				+ "\tElapsed Time: " + millis);
		else
			System.out.println(currentPlayer + "'s Move: " + move_num++ + " \tCoor: (" + move[0] +"," + move[1] +") \ttile: "
					+ current.getSide(0)+current.getSide(1)+current.getSide(2)+current.getSide(3)
					+ "\tTiger Locations: "  + ((i != -1)? i:"N/A")
					+ "\tElapsed Time: " + millis);
	}
}
