package sudokuSolver;


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
		for(int i=0;i<9;++i){
			if(input == puzzle[i][positionJ]){
				return false;
			}
		}
		
		//check j
		for(int i=0;i<9;++i){
			if(input == puzzle[positionI][i]){
				return false;
			}
		}
		
		// find which box i and j are in
		int iBox =(positionI/3)*3;
		int jBox =(positionJ/3)*3;

        for (int i = 0; i < 3; ++i){
            for (int j = 0; j < 3; ++j){
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
			if(i==9){
				i=0;
				if(++j == 9){
					return true;
				}
			}

			if(puzzle[i][j] != 0){
		//		System.out.println(i + " " + j);
				return solve(i+1, j, puzzle);
			}
			
			while(inputNumber <= 9){
				
			//	System.out.println("While is ok");
				
				if(checkLegal(i, j, inputNumber, puzzle)){
					puzzle[i][j] = inputNumber;
					
				//	System.out.println("Puzzle Value " + puzzle[i][j]);
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
	 * Puts some numbers in the puzzle
	 */
	void fill(int[][] puzzle){
		for(int i =0; i<puzzle.length;i++){
			for(int j=0;j<puzzle.length;j++){
				puzzle[i][j] = 0;
			}
		}
	}
	
	/**
	 * Puts an array of ints into the puzzle
	 * @param number
	 */
	
	void fill(int[] number, int[][] puzzle){
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
