package sudoku_solver;

import java.awt.Point;

public class Solver {

	private int puzzle[][]; // 2d representation of the sudoku puzzle
	private Point emptyPoint;
	
	public Solver(int[][] puzzle) {
		this.puzzle=puzzle;
	}
	
	
	public boolean solve() {
		System.out.println("Starting to solve ...");
		
		Boolean found = findEmpty();
		Point emptyPoint = getEmptyPoint();
		
		if(!found) {
			System.out.println("Solved!");
			return true;
		}
		
		for(int i = 1; i < 10; i++) {
			if(isCorrect(emptyPoint, i)) {
				puzzle[emptyPoint.x][emptyPoint.y] = i;
				System.out.println(puzzle[emptyPoint.x][emptyPoint.y] + " Assigned to " + emptyPoint.x +" "+ emptyPoint.y);
				if(solve()) {
					System.out.println("Solved!");
					return true;
				}
				
			}
			puzzle[emptyPoint.x][emptyPoint.y] = 0;

		}
		return false;
	}
	
	
	// Checks to see if value fits in a point
	
	public boolean isCorrect(Point p, int value) {
		
		int y = p.y;
		int x = p.x;
		
		// ROW
		
		// Check the row to see if 'value' does not fit in it
		
		for(int i = 0; i < puzzle[y].length; i++) {
			int row_aux = puzzle[i][y];
			if(row_aux == value && i != x) {
				return false;
			}
		}
		
		// COLUMN
		
		// Check the column to see if 'value' does not fit in it
		
		for(int a = 0; a < puzzle.length; a++) {
			int column_aux = puzzle[x][a];
			if(column_aux == value && a != y) {
				return false;
			}
		}
		
		// SQUARE
		
		// Check the square to see if 'value' does not fit in it
		
		/* 	Divide x and y by 3, due to integer rounding rules, the first 3[0,1,2] rows will equal 0, then 1 and then 2
		 * 
		 * 	The main goal of this operation is to always get a point inside a square normalized into a single point
		 * 	
		 * 	This means that every point inside a square will revert to the same point every time 	*/
		
		int aux_square_x = x/3;  
		int aux_square_y = y/3;	 
		
		// Multiplying now to get the upper left point of the square
		aux_square_x = aux_square_x*3; 
		aux_square_y = aux_square_y*3;
		
		for(int j = 0; j < aux_square_y + 3; j++) {
			for(int m = 0; m < aux_square_x + 3; m++) {
				int square_aux = puzzle[m][j];
				if(square_aux == value && m != x && j != y) {
					return false;
				}
			}
		}
		
		return true;	
	}
	
	// Finds the first empty point from left to right and top to bottom
	
	public boolean findEmpty() {
		
		for(int y = 0; y < puzzle.length; y++) { // top to bottom searching
			for(int x = 0; x < puzzle[y].length; x++) { // left to right searching
				int aux = puzzle[x][y];
				if(aux == 0) {
					setEmpyPoint(new Point(x,y));
					return true;
				}
			}
		}
		return false;
	}
	
	public void setEmpyPoint(Point p) {
		emptyPoint = p;
	}
	
	public Point getEmptyPoint() {
		return emptyPoint;
	}
	public int[][] getPuzzle(){
		return puzzle;
	}
}
