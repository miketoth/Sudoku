package sudokuSolver;

import java.lang.Math;

public class SudokuPuzzle {
	
	
	private int size;
	private int[][] answerPuzzle;
        
        
	/**
	 * Default constructor.
	 * Assumes 9x9
	 */
	SudokuPuzzle(){
		size = 9; // sets size to 9x9 automatically
		int[][] puzzle = new int[size][size];
		fill(puzzle);
	}
	
	/**
	 * Constructor with integer size. 
	 * First round only implement 9x9
	 * @param size
	 */
	SudokuPuzzle(int size, int[] in){
		// check to see if it is a perfect square!
		this.size = size; // sets size to inputed size 
		int[][] puzzle = new int[size][size];
                
                if(in.length < puzzle.length){
                    System.err.print("input length does not match puzzle size");
                    System.exit(0);
                }

		fill(puzzle);
		fill(in, puzzle);
                
		
		if(solve(puzzle)){
                    //printPuzzle(puzzle
                    answerPuzzle = puzzle;
		}
		else{
			System.out.println("No Solution");
		}
	}
	
	void check(boolean b, String errorMessage){
		if(b){
			System.out.println("Error: " + errorMessage);
			System.exit(1);
		}
	}
	
	boolean checkLegal(int positionI, int positionJ, int input, int[][] puzzle){
		
		//check i
		for(int i=0;i<size;++i){
			if(input == puzzle[i][positionJ]){
				return false;
			}
		}
		
		//check j
		for(int i=0;i<size;++i){
			if(input == puzzle[positionI][i]){
				return false;
			}
		}
		                
                // assumes that square root is an integer (should check in constructor)
                int sqrtSize = (int) Math.sqrt((double)size); 
                
		int iBox =(positionI/sqrtSize)*sqrtSize;
		int jBox =(positionJ/sqrtSize)*sqrtSize;

        for (int i = 0; i < sqrtSize; ++i){
            for (int j = 0; j < sqrtSize; ++j){
            	if (input == puzzle[iBox+i][jBox+j]){
                    return false;
                }
            }
        }
		return true;
	}
	
	boolean solve(int i,int j, int puzzle[][]){
			int inputNumber = 1;
			// if i and j are 9: at the end of the puzzle so stop
			if(i==size){
				i=0;
				if(++j == size){
					return true;
				}
			}

			if(puzzle[i][j] != 0){
				return solve(i+1, j, puzzle);
			}
			
			while(inputNumber <= size){	
				if(checkLegal(i, j, inputNumber, puzzle)){
					puzzle[i][j] = inputNumber;
					if(solve(i+1, j, puzzle)){
						return true;
					}
				}
				inputNumber++;
			}
			puzzle[i][j] = 0;
			return false;
		}
	
	
	boolean solve(int[][] puzzle){
		if(solve(0,0,puzzle)){
			return true;
		}
		else{
			return false;
		}
		
	}
	
	
	
	/**
	 * Prints current state of the puzzle 
	 */
	void printPuzzle(int[][] puzzle){
		for(int i = 0;i<puzzle.length;i++){
			for(int j=0;j<puzzle.length;j++){
				System.out.print(" " + puzzle[i][j]);
			}
			System.out.println();
		}
	}
	
	
	/**
	 * fills an int[][] with 0s.
	 */
	void fill(int[][] puzzle){
		for(int i =0; i<puzzle.length;i++){
			for(int j=0;j<puzzle.length;j++){
				puzzle[i][j] = 0;
			}
		}
	}
        /**
         * fills an int[] with 0s
         * @param puzzle 
         */
        void fill(int[] puzzle){
            for(int i=0;i<puzzle.length;i++){
                puzzle[i] =0;
            }
        }
	
	/**
	 * Puts an array of ints into the puzzle
	 * @param number
	 */
	
	void fill(int[] number, int[][] puzzle){
            
            if(number.length < puzzle.length){
                number = new int[puzzle.length];
                fill(number);
            }
            
		int p=0;
		for(int i =0; i<puzzle.length;i++){
			for(int j=0;j<puzzle.length;j++){
				puzzle[i][j] = number[p];
				p++;
				
			}
		}
	}
        
        int[][] getAnswerPuzzle(){
            return answerPuzzle;
        }
        
}
