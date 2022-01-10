import java.util.*;
import java.util.stream.Stream;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
public class A_star {
	
	
	/**
	 * randomly generates the puzzle tiles
	 * @return
	 */
	static Integer[] randoGen()
	{
		Integer[] Epuzzle = {0,1,2,3,4,5,6,7,8};
		List<Integer> intList = Arrays.asList(Epuzzle);
		Collections.shuffle(intList);
		intList.toArray(Epuzzle);
		//System.out.println(Arrays.toString(Epuzzle));
		for(int i=0;i<Epuzzle.length;i++)
		{
			if(i%3 == 0)
			{
				System.out.println();
			}
			System.out.print(Epuzzle[i] + " ");
			
		}
		System.out.println();
		return Epuzzle;
	}
	/**
	 * allows user to manually input the tile values
	 * @return
	 */
	static Integer[] manualEntry()
	{
		Scanner mE = new Scanner(System.in);
		Integer[] EpuzzleM = new Integer[9];
		int manualE;
		System.out.println("Manually enter values in eight puzzle: (unique values of 0-8 in each entry) ");
		for(int i=0;i<EpuzzleM.length;i++)
		{
	
			EpuzzleM[i] = mE.nextInt();
		}
		
		for(int i=0;i<EpuzzleM.length;i++)
		{
			if(i%3 == 0)
			{
				System.out.println();
			}
			System.out.print(EpuzzleM[i] + " ");
			
		}
		System.out.println();
		return EpuzzleM;
	}
	/**
	 * Checks the initial state of the puzzle to ensure puzzle is solvable
	 * @param puzzle
	 * @return
	 */
	static boolean inversionCheck(Integer[] puzzle)
	{
		int counter = 0;
	
		for(int i=0;i<puzzle.length;i++)
		{
			if(puzzle[i] != 0)
			{
			
			for(int j=i;j<puzzle.length;j++)
			{
				if(puzzle[j] != 0)
				{
					if(puzzle[i] > puzzle[j])
					{
						counter++;
						
					}
					else if(puzzle[i] == puzzle[j])
					{
						continue;
					}
					else
					{
						continue;
					}
					
				}
				
				else
				{
					continue;
				}
				
			}
		
			}
			else
			{
				continue;
			}
		}
		System.out.println("There are " + counter + " inversions found.");
		if(counter % 2 == 0)
		{
			System.out.println("Inversion Check Passed!");
			return true;
		}
		else {
			System.out.println("Inversion Check Failed! Please try another puzzle orientation.");
		}
		
		return false;
	}
	
//	static String puzToString(Integer[][] previousState, Integer child)
//	{
//		String puzzle = "";
//		String childString = "";
//		for(int i=0;i<3;i++)
//		{
//			for(int j=0;j<3;j++)
//			{
//				puzzle += previousState[i][j].toString();
//			}
//		}
//		childString = child.toString();
//		
//		for(int i=0;i<puzzle.length();i++)
//		{
//			if(puzzle.charAt(i).equals(childString))
//		}
//		return puzzle;
//	}
	
	static int A_search(Integer[][] Epuzzle, int H_type)
	{
		
	
		
		Integer[][] currentState = new Integer[3][3];
		Integer[][] goalState = new Integer[3][3];
		
		PriorityQueue<Integer> frontier = new PriorityQueue<>();
		HashMap<Integer,Integer> explored = new HashMap<>();
		HashSet<Integer> explore = new HashSet<>();
		HashMap<Integer,Integer> open = new HashMap<>();
		HashSet<String> expanded = new HashSet<>();
		

		int incrementer = 0;
		for(int i=0;i<goalState.length;i++)
		{
			for(int j=0;j<goalState.length;j++)
			{
				goalState[i][j] = incrementer;
				incrementer++;
			}
			
		}
		manHatDist(Epuzzle,goalState);	
		String puzzle = "";
		int g_n = 0;
		int h_n = 0;
		int f_n = 0;
		Integer upChild = 0;
		Integer downChild = 0;
		Integer leftChild = 0;
		Integer rightChild = 0;
		Integer swap = 0;
		g_n++;
		if(H_type == 1)
		{
//			while(g_n < 3)
//			{
//				g_n++;
				for(int i=0;i<3;i++)
				{
					for(int j=0;j<3;j++)
					{
						if(Epuzzle[i][j]  == 0)  // finds the blank tile
						{
							
							try 
							{
								if(!explored.containsValue(h_n))
								{
									upChild = Epuzzle[i-1][j]; //obtains the upChild of blank if possible 
								}
								
							
								System.out.println("Up Child: " + upChild);
							}
							catch(ArrayIndexOutOfBoundsException exception)
							{
								
							}
							try 
							{
								if(!explored.containsValue(h_n))
								{
									downChild = Epuzzle[i+1][j]; //obtains the downChild of blank if possible
								}
							
					
								System.out.println("Down Child: " + downChild);
							}
							catch(ArrayIndexOutOfBoundsException exception)
							{
								
							}try 
							{
								if(!explored.containsValue(h_n))
								{
									leftChild = Epuzzle[i][j-1];   //obtains the leftChild of blank if possible
								}
								
			
								System.out.println("Left Child: " + leftChild);
							}
							catch(ArrayIndexOutOfBoundsException exception)
							{
								
							}
							try 
							{
								if(!explored.containsValue(h_n))
								{
									rightChild = Epuzzle[i][j+1];   //obtains the rightChild of blank if possible
								}
								
			
								System.out.println("Right Child: " + rightChild);
							}
							catch(ArrayIndexOutOfBoundsException exception)
							{
								
							}
							
							
						}
					}
				}
				
				if(!expanded.contains(puzzle))
				{
					expanded.add(puzzle); // adds puzzle to expanded set
				}
				int counter = 0;
//				for(int i=0;i<3;i++)
//				{
//					for(int j=0;j<3;j++)
//					{
//						if(initState[i][j] == 0 && counter < 1)
//						{
//							swap = frontier.peek();
//							try {
//								if(swap == (rightChild +g_n+h_n))
//								{
//							
//									initState[i][j] = rightChild;
//									initState[i][j+1] = 0;
//								
//									//explored.put(initState[i][j], frontier.poll());
//									counter++;
//									
//									break;
//								}
//								if(swap == (leftChild +g_n+h_n))
//								{
//								
//									initState[i][j] =leftChild;
//									initState[i][j-1] = 0;
//								
//									//explored.put(initState[i][j], frontier.poll());
//									counter++;
//									break;
//								}
//								if(swap == (upChild +g_n+h_n))
//								{
//								
//									initState[i][j] = upChild;
//									initState[i-1][j] = 0;
//									
//									//explored.put(initState[i][j], frontier.poll());
//									counter++;
//									break;
//								}
//								if(swap == (downChild +g_n+h_n))
//								{
//									
//									initState[i][j] = downChild;
//									initState[i+1][j] = 0;
//									
//									//explored.put(initState[i][j], frontier.poll());
//									counter++;
//									break;
//								
//							}
//							}
//							catch(ArrayIndexOutOfBoundsException exception)
//							{
//								
//							}
//							
//						}
//						
//					}
//					
//				}
//				
			
					//-------------------------
			/*
			 * swaps puzzle tiles with previously found children (right swap)		
			 */
			for(int i=0;i<3;i++)
			{
				for(int j=0;j<3;j++)
				{
					currentState[i][j] = Epuzzle[i][j];
				}
			}
				for(int i=0;i<3;i++)
				{
					for(int j=0;j<3;j++)
					{
						if(Epuzzle[i][j] == 0 )
						{
							
							try {
								if(Epuzzle[i][j+1] == rightChild )
								{
							
									Epuzzle[i][j] = rightChild;
									Epuzzle[i][j+1] = 0;
								
									//explored.put(initState[i][j], frontier.poll());
									counter++;
									continue;
									
								}
							}
								catch(ArrayIndexOutOfBoundsException exception)
								{
									
								}}}}
				h_n=misplacedTiles(Epuzzle,goalState);
				/*
				 * swaps puzzle tiles with previously found children (left swap)		
				 */
				for(int i=0;i<3;i++)
				{
					for(int j = 0;j<3;j++)
					{
						System.out.print(Epuzzle[i][j] + " ");
					}
					System.out.println();
				}
					for(int i=0;i<3;i++)
					{
						for(int j=0;j<3;j++)
						{
							if(Epuzzle[i][j] == 0 )
							{
							try {
								if(Epuzzle[i][j-1] == leftChild )
								{
								
									Epuzzle[i][j] =leftChild;
									Epuzzle[i][j-1] = 0;
								
									//explored.put(initState[i][j], frontier.poll());
									counter++;
									continue;
									
								}
								}
								catch(ArrayIndexOutOfBoundsException exception)
								{
									
								}}}}
					for(int i=0;i<3;i++)
					{
						for(int j = 0;j<3;j++)
						{
							System.out.print(Epuzzle[i][j] + " ");
						}
						System.out.println();
					}
					h_n=misplacedTiles(Epuzzle,goalState);
					
					/*
					 * swaps puzzle tiles with previously found children (up swap)		
					 */
					for(int i=0;i<3;i++)
					{
						for(int j=0;j<3;j++)
						{
							if(Epuzzle[i][j] == 0 )
							{
							try {
								if(Epuzzle[i-1][j] == upChild )
								{
								
									Epuzzle[i][j] = upChild;
									Epuzzle[i-1][j] = 0;
									
									//explored.put(initState[i][j], frontier.poll());
									counter++;
									continue;
								
								}
							}
							catch(ArrayIndexOutOfBoundsException exception)
							{
								
							}}}}
					for(int i=0;i<3;i++)
					{
						for(int j = 0;j<3;j++)
						{
							System.out.print(Epuzzle[i][j] + " ");
						}
						System.out.println();
					}
					h_n=misplacedTiles(Epuzzle,goalState);
					/*
					 * swaps puzzle tiles with previously found children (down swap)		
					 */
					for(int i=0;i<3;i++)
					{
						for(int j=0;j<3;j++)
						{
							if(Epuzzle[i][j] == 0 )
							{
							try {
								if(Epuzzle[i+1][j] == downChild )
								{
									
									Epuzzle[i][j] = downChild;
									Epuzzle[i+1][j] = 0;
									
									//explored.put(initState[i][j], frontier.poll());
									counter++;
									continue;
								
							}
							}
							catch(ArrayIndexOutOfBoundsException exception)
							{
								
							}}}}
					for(int i=0;i<3;i++)
					{
						for(int j = 0;j<3;j++)
						{
							System.out.print(Epuzzle[i][j] + " ");
						}
						System.out.println();
					}
					h_n=misplacedTiles(Epuzzle,goalState);
					
					
					
				
//			 for(int i=0;i<3;i++)
//			 {
//				 for(int j=0;j<3;j++)
//				 {
//					 System.out.print(initState[i][j] + " ");
//				 }
//				 System.out.println();
//			 }
			       
				 }
				
				
				
				
			
	
			
	
	
		if(H_type == 2)
		{
//			while(g_n < 3)
//			{
//				g_n++;
				for(int i=0;i<3;i++)
				{
					for(int j=0;j<3;j++)
					{
						if(Epuzzle[i][j]  == 0)  // finds the blank tile
						{
							
							try 
							{
								if(!explored.containsValue(h_n))
								{
									upChild = Epuzzle[i-1][j]; //obtains the upChild of blank if possible 
								}
								
							
								System.out.println("Up Child: " + upChild);
							}
							catch(ArrayIndexOutOfBoundsException exception)
							{
								
							}
							try 
							{
								if(!explored.containsValue(h_n))
								{
									downChild = Epuzzle[i+1][j]; //obtains the downChild of blank if possible
								}
							
					
								System.out.println("Down Child: " + downChild);
							}
							catch(ArrayIndexOutOfBoundsException exception)
							{
								
							}try 
							{
								if(!explored.containsValue(h_n))
								{
									leftChild = Epuzzle[i][j-1];   //obtains the leftChild of blank if possible
								}
								
			
								System.out.println("Left Child: " + leftChild);
							}
							catch(ArrayIndexOutOfBoundsException exception)
							{
								
							}
							try 
							{
								if(!explored.containsValue(h_n))
								{
									rightChild = Epuzzle[i][j+1];   //obtains the rightChild of blank if possible
								}
								
			
								System.out.println("Right Child: " + rightChild);
							}
							catch(ArrayIndexOutOfBoundsException exception)
							{
								
							}
							
							
						}
					}
				}
				
				if(!expanded.contains(puzzle))
				{
					expanded.add(puzzle); // adds puzzle to expanded set
				}
				int counter = 0;
//				for(int i=0;i<3;i++)
//				{
//					for(int j=0;j<3;j++)
//					{
//						if(initState[i][j] == 0 && counter < 1)
//						{
//							swap = frontier.peek();
//							try {
//								if(swap == (rightChild +g_n+h_n))
//								{
//							
//									initState[i][j] = rightChild;
//									initState[i][j+1] = 0;
//								
//									//explored.put(initState[i][j], frontier.poll());
//									counter++;
//									
//									break;
//								}
//								if(swap == (leftChild +g_n+h_n))
//								{
//								
//									initState[i][j] =leftChild;
//									initState[i][j-1] = 0;
//								
//									//explored.put(initState[i][j], frontier.poll());
//									counter++;
//									break;
//								}
//								if(swap == (upChild +g_n+h_n))
//								{
//								
//									initState[i][j] = upChild;
//									initState[i-1][j] = 0;
//									
//									//explored.put(initState[i][j], frontier.poll());
//									counter++;
//									break;
//								}
//								if(swap == (downChild +g_n+h_n))
//								{
//									
//									initState[i][j] = downChild;
//									initState[i+1][j] = 0;
//									
//									//explored.put(initState[i][j], frontier.poll());
//									counter++;
//									break;
//								
//							}
//							}
//							catch(ArrayIndexOutOfBoundsException exception)
//							{
//								
//							}
//							
//						}
//						
//					}
//					
//				}
//				
			
					//-------------------------
			/*
			 * swaps puzzle tiles with previously found children (right swap)		
			 */
			for(int i=0;i<3;i++)
			{
				for(int j=0;j<3;j++)
				{
					currentState[i][j] = Epuzzle[i][j];
				}
			}
				for(int i=0;i<3;i++)
				{
					for(int j=0;j<3;j++)
					{
						if(Epuzzle[i][j] == 0 )
						{
							
							try {
								if(Epuzzle[i][j+1] == rightChild )
								{
							
									Epuzzle[i][j] = rightChild;
									Epuzzle[i][j+1] = 0;
								
									//explored.put(initState[i][j], frontier.poll());
									counter++;
									continue;
									
								}
							}
								catch(ArrayIndexOutOfBoundsException exception)
								{
									
								}}}}
				h_n=manHatDist(Epuzzle,goalState);	
				/*
				 * swaps puzzle tiles with previously found children (left swap)		
				 */
				for(int i=0;i<3;i++)
				{
					for(int j = 0;j<3;j++)
					{
						System.out.print(Epuzzle[i][j] + " ");
					}
					System.out.println();
				}
					for(int i=0;i<3;i++)
					{
						for(int j=0;j<3;j++)
						{
							if(Epuzzle[i][j] == 0 )
							{
							try {
								if(Epuzzle[i][j-1] == leftChild )
								{
								
									Epuzzle[i][j] =leftChild;
									Epuzzle[i][j-1] = 0;
								
									//explored.put(initState[i][j], frontier.poll());
									counter++;
									continue;
									
								}
								}
								catch(ArrayIndexOutOfBoundsException exception)
								{
									
								}}}}
					for(int i=0;i<3;i++)
					{
						for(int j = 0;j<3;j++)
						{
							System.out.print(Epuzzle[i][j] + " ");
						}
						System.out.println();
					}
					h_n=manHatDist(Epuzzle,goalState);	
					
					/*
					 * swaps puzzle tiles with previously found children (up swap)		
					 */
					for(int i=0;i<3;i++)
					{
						for(int j=0;j<3;j++)
						{
							if(Epuzzle[i][j] == 0 )
							{
							try {
								if(Epuzzle[i-1][j] == upChild )
								{
								
									Epuzzle[i][j] = upChild;
									Epuzzle[i-1][j] = 0;
									
									//explored.put(initState[i][j], frontier.poll());
									counter++;
									continue;
								
								}
							}
							catch(ArrayIndexOutOfBoundsException exception)
							{
								
							}}}}
					for(int i=0;i<3;i++)
					{
						for(int j = 0;j<3;j++)
						{
							System.out.print(Epuzzle[i][j] + " ");
						}
						System.out.println();
					}
					h_n=manHatDist(Epuzzle,goalState);	
					/*
					 * swaps puzzle tiles with previously found children (down swap)		
					 */
					for(int i=0;i<3;i++)
					{
						for(int j=0;j<3;j++)
						{
							if(Epuzzle[i][j] == 0 )
							{
							try {
								if(Epuzzle[i+1][j] == downChild )
								{
									
									Epuzzle[i][j] = downChild;
									Epuzzle[i+1][j] = 0;
									
									//explored.put(initState[i][j], frontier.poll());
									counter++;
									continue;
								
							}
							}
							catch(ArrayIndexOutOfBoundsException exception)
							{
								
							}}}}
					for(int i=0;i<3;i++)
					{
						for(int j = 0;j<3;j++)
						{
							System.out.print(Epuzzle[i][j] + " ");
						}
						System.out.println();
					}
					h_n=manHatDist(Epuzzle,goalState);	
					
					
					
				
//			 for(int i=0;i<3;i++)
//			 {
//				 for(int j=0;j<3;j++)
//				 {
//					 System.out.print(initState[i][j] + " ");
//				 }
//				 System.out.println();
//			 }
			       
				 
			
		}
		
		return f_n;
		
	}
	/**
	 * The H1 heuristic that measures the overall misplaced tiles within the puzzle
	 * @param currentState
	 * @param goalState
	 * @return
	 */
	static int misplacedTiles(Integer[][] currentState, Integer[][] goalState)
	{
		int misP = 0;
		for(int i=0;i<goalState.length;i++)
		{
			for(int j=0;j<goalState.length;j++)
			{
				if(goalState[i][j] != currentState[i][j])
				{
					misP++;
				}
			}
		}
		System.out.println("There are: " + misP  + " misplaced tiles");
		
		
		return misP;
	}
	static int manHatDist(Integer[][] currentState, Integer[][] goalState)
	{
		int manHat =0;
		  
		    for (int i = 0; i < 3; i++) 
		        for (int j = 0; j < 3; j++) { 
		            int currentValue = currentState[i][j]; 
		            if (currentValue != 0) { // we don't compute MD for element 0
		                int expecX = (currentValue - 1) / 3; // expected row
		                int expecY = (currentValue - 1) % 3; // expected col
		                int dx = i - expecX; // x-distance to expected coordinate
		                int dy = j - expecY; // y-distance to expected coordinate
		                manHat += Math.abs(dx) + Math.abs(dy); 
		            } 
		        }
		  System.out.println("Manhattan distance: " + manHat);
		
		return manHat;
	}

	public static void main(String[] args) {
	
		int Hinput =0;
		Integer[][] initState = new Integer[3][3];
		Integer[] randomPuzzle;
		Integer[] manualPuzzle;
		final int[] goalState = {0,1,2,3,4,5,6,7,8};
		Scanner keyboard = new Scanner(System.in);
		int input;
		System.out.println("Select:");
		System.out.println("[1] Single Test Puzzle");
		System.out.println("[2] Exit");
		input = keyboard.nextInt();
		while(input == 1)
		{
			int input2;
			System.out.println("Select Input Method: ");
			System.out.println("[1] Random");
			System.out.println("[2] Manual");
			input2 = keyboard.nextInt();
			if(input2 == 1)
			{
				System.out.println("[1] Random Generation Selection");
				//randoGen();
				System.out.println("Inversion Check");
			
				randomPuzzle = randoGen();
				for(int i=0;i<initState.length;i++)
				{
					for(int j=0;j<initState.length;j++)
					{
						if(i == 0)
						{
							initState[i][j] = randomPuzzle[j];
						}
						else if(i == 1)
						{
							initState[i][j] = randomPuzzle[j+3];
						}
						else
						{
							initState[i][j] = randomPuzzle[j+6];
						}
						
					}
				}
				
				
				if(inversionCheck(randomPuzzle) == true)
				{
					System.out.println("[1] H1");
					System.out.println("[2] H2");
					Hinput = keyboard.nextInt();
					if(Hinput ==  1)
					{
						A_search(initState,Hinput);
						
					}
					if(Hinput == 2)
					{
						
					}
					
					
				}
		
				
			}
			else if(input2 == 2)
			{
				System.out.println("[2] Manual Input Selection");
				manualPuzzle = manualEntry();
				for(int i=0;i<initState.length;i++)
				{
					for(int j=0;j<initState.length;j++)
					{
						if(i == 0)
						{
							initState[i][j] = manualPuzzle[j];
						}
						else if(i == 1)
						{
							initState[i][j] = manualPuzzle[j+3];
						}
						else
						{
							initState[i][j] = manualPuzzle[j+6];
						}
						
					}
				}
				if(inversionCheck(manualPuzzle) == true)
				{
					System.out.println("[1] H1");
					System.out.println("[2] H2");
					Hinput = keyboard.nextInt();
					if(Hinput ==  1)
					{
						A_search(initState,Hinput);
						
					}
					if(Hinput == 2)
					{
						
					}
				}
				
			}
	
			
			//PriorityQueue <Integer> firstQ = new PriorityQueue<>();
			
			System.out.println("Select:");
			System.out.println("[1] Single Test Puzzle");
			System.out.println("[2] Exit");
			input = keyboard.nextInt();
		}	
		System.out.println("You have exited the A* program.");
	}

}

/**
 * Below is my second attempt in recreating the program with a different implementation of A_Star function
 * 
 */

/**
 * Below function converts 2d array value of puzzle to string to work with hashmaps
 */
//	static String arrayToString(Integer[][] puzzle)
//	{
//		
//		String puzzleString = "";
//		
//		for(int i=0;i<3;i++)
//		{
//			for(int j=0;j<3;j++)
//			{
//				puzzleString += puzzle[i][j].toString();
//			}
//		}
//		return puzzleString;
//		
//		
//	}

	/**
	 * Function below maintains the trace of the hashmap
	 */
//	static HashSet<String> reconstruct_path(HashMap<String,String> cameFrom, String current)
//	{
//		HashSet<String> total_path = new HashSet<>();
//		total_path.add(current);
//		
//		while(cameFrom.containsKey(current))
//		{
//			current = cameFrom.get(current);
//			total_path.add(current);	
//		}
//		return total_path;
//	}


/**
 * function is intended to input the string value of a given puzzle and return the swapped version
 */
//	static String swap(String puzzle)
//	{
//		Integer[] array = new Integer[9];
//		Integer[][] array2 = new Integer[3][3];
//		
//		for(int i=0;i<9;i++)
//		{
//			array[i] = Character.getNumericValue(puzzle.charAt(i));
//		}
//		
//		for(int i=0;i<3;i++)
//		{
//			for(int j=0;j<3;j++)
//			{
//				if(i == 0)
//				{
//					array2[i][j] = array[j];
//				}
//				else if(i == 1)
//				{
//					array2[i][j] = array[j+3];
//				}
//				else
//				{
//					array2[i][j] = array[j+6];
//				}
//				
//			}
//		}
//		int upChild =0;
//		int downChild = 0;
//		int leftChild = 0;
//		int rightChild = 0;
//		for(int i=0;i<3;i++)
//		{
//			for(int j=0;j<3;j++)
//			{
//				
//				if(array2[i][j] == 0)
//				{
//					
//					
//					try 
//					{
//						upChild = array2[i-1][j];
//						
//						
//						System.out.println("Up Child: " + upChild);
//					}
//					catch(ArrayIndexOutOfBoundsException exception)
//					{
//						
//					}
//					try 
//					{
//						downChild = array2[i+1][j];
//						
//						System.out.println("Down Child: " + downChild);
//					}
//					catch(ArrayIndexOutOfBoundsException exception)
//					{
//						
//					}try 
//					{
//						leftChild = array2[i][j-1];
//						
//						
//						System.out.println("Left Child: " + leftChild);
//					}
//					catch(ArrayIndexOutOfBoundsException exception)
//					{
//						
//					}
//					try 
//					{
//						rightChild = array2[i][j+1];
//				
//						
//						System.out.println("Right Child: " + rightChild);
//					}
//					catch(ArrayIndexOutOfBoundsException exception)
//					{
//						
//					}
//								
//					
//					
//					
//				}
//				 
//			}
//			
//			
//		}
//
//		
//		
//		return puzzle;
//		
//	}
//	
/**
 * Reconstructed A_star algorithm intended to take in starting state and goal state of puzzle
 */
//	static boolean A_search(Integer[][] start, Integer[][] goal, int H_type)
//	{
//		
//		PriorityQueue<String> openSet = new PriorityQueue<>();  // initiate open set for all nodes in the frontier
//		String startString = arrayToString(start); // convert start puzzle to a string value
//		String goalString = arrayToString(goal); //convert goal puzzle to a string value
//		openSet.add(startString);  //add to openset (frontier)
//		HashMap<String, String> cameFrom = new HashMap<>();  //intended to map the index of a given string node to another preceding string node
//		
//		HashMap<String,Integer> g_n = new HashMap<>(); //maps puzzle value to g_n value
//		g_n.put(startString, 0);
//		
//		HashMap<String,Integer> f_n = new HashMap<>();
//		f_n.put(startString, misplacedTiles(startString,goalString)); // maps string puzzle value to search cost
//		
//		
//		while(!openSet.isEmpty())
//		{
//			String current = openSet.peek(); intended to remove node with lowest f_n value
//			if(current.equals(goalString))
//			{
//				reconstruct_path(cameFrom,current); //returns traceback of goalState to path of initial state if current puzzle finally equals goal puzzle
//			}
//			openSet.remove(current); // remove current state from the frontier as its been explored/expanded
//			for(Character neighbor: current.toCharArray())  //check neighboring nodes of current node 
//			{
////				if(neighbor == '0')
////				{
//					Integer tempG = g_n.get(current) + 1; // increments g_n value when expanding into given node
//					if(tempG < g_n.get(neighbor))  //checks new g_n value with neighboring node g_n value to focus on the lower cost
//					{
//						String newCurrent = cameFrom.get(neighbor); //gets previous node of new current (neighbor) 
//						newCurrent = current; set newCurrent to equal current node
//						Integer newG = g_n.get(neighbor);
//						newG = tempG;   //gets g_n value of neighbor and sets as updated g_n value
//						Integer newF = f_n.get(neighbor); //obtains updated value of f_n
//						newF = tempG + misplacedTiles(neighbor.toString(),goalString);
//						if(!openSet.contains(neighbor))   //if neighbor is not in frontier, add it to the frontier
//						{
//							openSet.add(neighbor.toString());
//						}
//				//}
//			
//				}
//			}
//			
//		}
//		
//		return false;
//		
//		
//	}















