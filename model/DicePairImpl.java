package model;

import model.interfaces.DicePair;

public class DicePairImpl implements DicePair {
	private int dice1;
	private int dice2;
	private int numFaces;

	public DicePairImpl(int dice1, int dice2, int numFaces) {
		this.dice1 = dice1;
		this.dice2 = dice2;
		this.numFaces = numFaces;
	}

	public int getDice1() {
		return dice1;
	}

	public int getDice2() {
		return dice2;
	}

	public int getNumFaces() {
		return numFaces;
	}

	@Override
	public String toString() {
		int total = dice1 + dice2;
		return String.format("Dice 1: %d , Dice 2: %d .. Total: %d", dice1, dice2, total);
	}
}
