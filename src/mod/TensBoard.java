package mod;

import java.util.List;
import java.util.ArrayList;
import mod.Board;

/**
 * The ElevensBoard class represents the board in a game of Elevens.
 */
public class TensBoard extends Board {

	/**
	 * The size (number of cards) on the board.
	 */
	private static final int BOARD_SIZE = 13;

	/**
	 * The ranks of the cards for this game to be sent to the deck.
	 */
	private static final String[] RANKS =
		{"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};

	/**
	 * The suits of the cards for this game to be sent to the deck.
	 */
	private static final String[] SUITS =
		{"spades", "hearts", "diamonds", "clubs"};

	/**
	 * The values of the cards for this game to be sent to the deck.
	 */
	private static final int[] POINT_VALUES =
		{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 0, 0};

	/**
	 * Flag used to control debugging print statements.
	 */
	private static final boolean I_AM_DEBUGGING = false;


	/**
	 * Creates a new <code>ElevensBoard</code> instance.
	 */
	 public TensBoard() {
	 	super(BOARD_SIZE, RANKS, SUITS, POINT_VALUES);
	 }

	/**
	 * Determines if the selected cards form a valid group for removal.
	 * In Tens, the legal groups are (1) a pair of non-face cards
	 * whose values add to 10, and (2) a group of three cards consisting of
	 * a jack, a queen, and a king in some order.
	 * @param selectedCards the list of the indices of the selected cards.
	 * @return true if the selected cards form a valid group for removal;
	 *         false otherwise.
	 */
	@Override
	public boolean isLegal(List<Integer> selectedCards) {
		if (selectedCards.size() == 2) {
			return containsPairSum10(selectedCards);
		} else if (selectedCards.size() == 4) {
			return containsFourJ(selectedCards);
		} else if (selectedCards.size() == 4) {
			return containsFourQ(selectedCards);
		} else if (selectedCards.size() == 4) {
			return containsFourK(selectedCards);
		} else if (selectedCards.size() == 4) {
			return containsFour10(selectedCards);
		} else {
			return false;
		}
	}

	/**
	 * Determine if there are any legal plays left on the board.
	 * In Tens, there is a legal play if the board contains
	 * (1) a pair of non-face cards whose values add to 10, or (2) a group
	 * of four cards consisting of the same suit jacks, queens, kings, or 10s in some order.
	 * @return true if there is a legal play left on the board;
	 *         false otherwise.
	 */
	@Override
	public boolean anotherPlayIsPossible() {
		List<Integer> cIndexes = cardIndexes();
		return containsPairSum10(cIndexes) || containsFourJ(cIndexes) || 
				containsFourQ(cIndexes) || containsFourK(cIndexes) || containsFour10(cIndexes);
	}

	/**
	 * Check for an 10-pair in the selected cards.
	 * @param selectedCards selects a subset of this board.  It is list
	 *                      of indexes into this board that are searched
	 *                      to find an 10-pair.
	 * @return true if the board entries in selectedCards
	 *              contain an 10-pair; false otherwise.
	 */
	private boolean containsPairSum10(List<Integer> selectedCards) {
		for (int sk1 = 0; sk1 < selectedCards.size(); sk1++) {
			int k1 = selectedCards.get(sk1).intValue();
			for (int sk2 = sk1 + 1; sk2 < selectedCards.size(); sk2++) {
				int k2 = selectedCards.get(sk2).intValue();
				if (cardAt(k1).pointValue() + cardAt(k2).pointValue() == 10) {
					return true;
				}
			}
		}
		return false;
	}

	
	/**
	 * Check for a four of a kind J in the selected cards.
	 * @param selectedCards selects a subset of this board.  It is list
	 *                      of indexes into this board that are searched
	 *                      to find a four of a kind J group.
	 * @return true if the board entries in selectedCards
	 *              include four jacks; false otherwise.
	 */
	private boolean containsFourJ(List<Integer> selectedCards) {
		boolean found1 = false;
		boolean found2 = false;
		boolean found3 = false;
		boolean found4 = false;
		for (Integer kObj : selectedCards) {
			int k = kObj.intValue();
			if (cardAt(k).rank().equals("jack")) {
				found1 = true;
			}
		}
		for (Integer k2Obj : selectedCards) {
			int k2 = k2Obj.intValue();
			if (cardAt(k2).rank().equals("jack")) {
				found2 = true;
			}
		}
		for (Integer k3Obj : selectedCards) {
			int k3 = k3Obj.intValue();
			if (cardAt(k3).rank().equals("jack")) {
				found3 = true;
			}
		}
		for (Integer k4Obj : selectedCards) {
			int k4 = k4Obj.intValue();
			if (cardAt(k4).rank().equals("jack")) {
				found4 = true;
			}
		}
		return found1 && found2 && found3 && found4;
	}
	
	/**
	 * Check for a four of a kind Q in the selected cards.
	 * @param selectedCards selects a subset of this board.  It is list
	 *                      of indexes into this board that are searched
	 *                      to find a four of a kind Q group.
	 * @return true if the board entries in selectedCards
	 *              include four queens; false otherwise.
	 */
	private boolean containsFourQ(List<Integer> selectedCards) {
		boolean found1 = false;
		boolean found2 = false;
		boolean found3 = false;
		boolean found4 = false;
		for (Integer kObj : selectedCards) {
			int k = kObj.intValue();
			if (cardAt(k).rank().equals("queen")) {
				found1 = true;
			}
		}
		for (Integer k2Obj : selectedCards) {
			int k2 = k2Obj.intValue();
			if (cardAt(k2).rank().equals("queen")) {
				found2 = true;
			}
		}
		for (Integer k3Obj : selectedCards) {
			int k3 = k3Obj.intValue();
			if (cardAt(k3).rank().equals("queen")) {
				found3 = true;
			}
		}
		for (Integer k4Obj : selectedCards) {
			int k4 = k4Obj.intValue();
			if (cardAt(k4).rank().equals("queen")) {
				found4 = true;
			}
		}
		return found1 && found2 && found3 && found4;
	}
	
	/**
	 * Check for a four of a kind K in the selected cards.
	 * @param selectedCards selects a subset of this board.  It is list
	 *                      of indexes into this board that are searched
	 *                      to find a four of a kind K group.
	 * @return true if the board entries in selectedCards
	 *              include four kings; false otherwise.
	 */
	private boolean containsFourK(List<Integer> selectedCards) {
		boolean found1 = false;
		boolean found2 = false;
		boolean found3 = false;
		boolean found4 = false;
		for (Integer kObj : selectedCards) {
			int k = kObj.intValue();
			if (cardAt(k).rank().equals("king")) {
				found1 = true;
			}
		}
		for (Integer k2Obj : selectedCards) {
			int k2 = k2Obj.intValue();
			if (cardAt(k2).rank().equals("king")) {
				found2 = true;
			}
		}
		for (Integer k3Obj : selectedCards) {
			int k3 = k3Obj.intValue();
			if (cardAt(k3).rank().equals("king")) {
				found3 = true;
			}
		}
		for (Integer k4Obj : selectedCards) {
			int k4 = k4Obj.intValue();
			if (cardAt(k4).rank().equals("king")) {
				found4 = true;
			}
		}
		return found1 && found2 && found3 && found4;
	}
	
	/**
	 * Check for a four of a kind 10 in the selected cards.
	 * @param selectedCards selects a subset of this board.  It is list
	 *                      of indexes into this board that are searched
	 *                      to find a four of a kind 10 group.
	 * @return true if the board entries in selectedCards
	 *              include four 10; false otherwise.
	 */
	private boolean containsFour10(List<Integer> selectedCards) {
		boolean found1 = false;
		boolean found2 = false;
		boolean found3 = false;
		boolean found4 = false;
		for (Integer kObj : selectedCards) {
			int k = kObj.intValue();
			if (cardAt(k).rank().equals("10")) {
				found1 = true;
			}
		}
		for (Integer k2Obj : selectedCards) {
			int k2 = k2Obj.intValue();
			if (cardAt(k2).rank().equals("10")) {
				found2 = true;
			}
		}
		for (Integer k3Obj : selectedCards) {
			int k3 = k3Obj.intValue();
			if (cardAt(k3).rank().equals("10")) {
				found3 = true;
			}
		}
		for (Integer k4Obj : selectedCards) {
			int k4 = k4Obj.intValue();
			if (cardAt(k4).rank().equals("10")) {
				found4 = true;
			}
		}
		return found1 && found2 && found3 && found4;
	}	
}
