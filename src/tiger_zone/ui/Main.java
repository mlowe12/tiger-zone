package tiger_zone.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import javax.swing.JFrame;

import tiger_zone.Board;

import tiger_zone.Client;
import tiger_zone.Game;
import tiger_zone.Player;
import tiger_zone.Tile;
import tiger_zone.UnionFind;
import tiger_zone.ai.PoorAiPlayer;
import tiger_zone.Protocol;

public class Main {
	public static void main(String[] args) {
	

		
		Stack<Tile> pile = Board.createDefaultStack();
		Collections.shuffle(pile);
		Game game = new Game(pile);



		List<Player> players = new ArrayList<Player>(2);
		players.add(new Player(0, "p1"));
		players.add(new Player(1, "p2"));
		game.setPlayers(players);

		PoorAiPlayer skynet1 = new PoorAiPlayer(game, "s1");
		PoorAiPlayer skynet2 = new PoorAiPlayer(game, "s2");
		
		while (game.getBoard().getPile().size() >= 1) {
			skynet1.makeMove();
			skynet2.makeMove();
			System.out.println(game.getBoard().getPile().size());
		}

		BoardFrame bf = new BoardFrame(game);
		bf.setSize(900, 900);
		bf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bf.setVisible(true);
	}
}
