/**
 * Plays tic-tac-toe against computer with computer using only 1-ply search.
 *
 * @author  T.Sergeant
 * @version For AI In-Class Work
 *
*/

import java.util.Scanner;

public class TicTacToe
{
	private Scanner kb= new Scanner(System.in);
	private Board b;
	private final char [] TOKEN= {'X','O'};


	/**
	 * Creates new empty board.
	 */
	public TicTacToe()
	{
		b= new Board();
	}


	/**
	 * Invites "token" to make a move and then update the board appropriately.
	 */
	public void makeMove(char token)
	{
		int move;
		b.show();
		if (token=='X')
		{
			System.out.print("Enter move (0-8): ");
			move= kb.nextInt();
			while (!b.makeMove(move,token))
			{
				System.out.println("Move must be 0-8 and not on top of an existing piece ...");
				System.out.print("Enter  a different move (0-8): ");
				move= kb.nextInt();
			}
		}
		else
		{
			b.suggestMove(token, 6, b, -1000, 1000);
			b.makeMove(b.getBestMove(),token);
		}
	}

	/**
	 * Play a game of tic-tac-toe.
	 */
	public void play()
	{
		int turn= (int) (Math.random()*2.0);  // random start
		do {
			turn= (turn+1)%2;
			System.out.println("\nIt is "+TOKEN[turn]+"'s move ....\n");
			System.out.println("EVAL: "+b.evaluate(TOKEN[turn]));
			makeMove(TOKEN[turn]);
		} while (!b.gameOver());

		b.show();
		if (b.isWinner(TOKEN[turn]))
			System.out.println("CONGRATULATIONS "+TOKEN[turn]+" ... YOU WON!");
		else if (b.isWinner(TOKEN[(turn+1)%2]))
			System.out.println("CONGRATULATIONS "+TOKEN[(turn+1)%2]+" ... YOU WON!");
		else
			System.out.println("GAME OVER ... TIE!");
	}
}
