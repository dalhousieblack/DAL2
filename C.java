import java.util.*;

public class C {

	public static void  main (String[] args) {
		
		//Board
	
		//Input
		Scanner input = new Scanner(System.in);

		//Get number of test cases
		int n = input.nextInt();
		
		input.nextLine();

		//Loop
		for (int i = 0; i < n; i++) {

			String dim = input.nextLine();
			int height = Integer.parseInt(dim.split("x")[0]);
			int width = Integer.parseInt(dim.split("x")[1]);

			Character[][] board = new Character[height][width];
			
			for (int h = 0; h < height; h++) {
				String line = input.nextLine();
				for (int w = 0; w < width; w++) {
				    board[h][w] = line.charAt(w);
				}
			}

			/*for (int h = 0; h < height; h++) {
                                for (int w = 0; w < width; w++) {
					System.out.print(board[h][w]);
                                }
				System.out.println();
				}*/
			
			int CASES=input.nextInt();
			for (int k=0;k<CASES;k++ ){
			    int ax,ay,bx,by;
			    ay=input.nextInt()-1;
			    ax=input.nextInt()-1;
			    by=input.nextInt()-1;
			    bx=input.nextInt()-1;
			    //got the test cases
			    if ( board[ay][ax]=='E' || board[by][bx]!='E') {
				//no way 
				System.out.println(0);
			    }
			    else {
				int result=move(board,ax,ay,bx,by);
				
				    System.out.println(result);
				    //switch positions
				
			    }
			}


		}

	}
    static void dist_surround(Character[][] board, int sx, int sy, int ex, int ey,boolean okmap[][], ArrayList<Integer> distances,
			      ArrayList<Integer> posxlist, ArrayList<Integer>posylist,
			      ArrayList<Integer> movelist, int currentnummoves
) {
	int distanceN = Integer.MAX_VALUE;
	int distanceS = Integer.MAX_VALUE;
	int distanceE = Integer.MAX_VALUE;
	int distanceW = Integer.MAX_VALUE;

	int arrlen=0;
	//if (sx == ex && sy == ey)
	//  return 0;
	
	if (safe(board, sx, sy-1,okmap) ){//|| (sx==ex && sy-1==ey)) {
	    //NORTH
	    //System.out.println("NORTH");
	    distanceN = distance(sx, sy-1, ex, ey);
	    //	    distanceN = distance(sx, ex, sy-1, ey);
	    okmap[sy-1][sx]=false;
	    posxlist.add(sx);
	    posylist.add(sy-1);
	    movelist.add(currentnummoves+1);
	    arrlen++;
	}

	    
	if (safe(board, sx, sy+1,okmap)){//|| (sx==ex && sy+1==ey)) {
	    //SOUTH
	    //System.out.println("SOUTH");   
	    distanceS = distance(sx, sy+1, ex, ey);
	    posxlist.add(sx);
	    posylist.add(sy+1);
	    movelist.add(currentnummoves+1);
	    okmap[sy+1][sx]=false;
	    arrlen++;
	}
	if (safe(board, sx+1, sy,okmap)){//|| (sx+1==ex && sy==ey)) {
	    //EAST
	    //System.out.println("EAST");   
	    distanceE = distance(sx+1, sy, ex, ey);
	    posxlist.add(sx+1);
	    posylist.add(sy);
	    movelist.add(currentnummoves+1);
	    okmap[sy][sx+1]=false;
	    arrlen++;
	}
	if (safe(board, sx-1, sy,okmap)){//|| (sx-1==ex && sy==ey)) {
	    //System.out.println("WEST");   
	    distanceW = distance(sx-1, sy, ex, ey);
	    posxlist.add(sx-1);
	    posylist.add(sy);
	    movelist.add(currentnummoves+1);
	    okmap[sy][sx-1]=false;
	    arrlen++;
	}
	

	    if (distanceN!=Integer.MAX_VALUE) 
		distances.add(distanceN);
	    
	    if (distanceS!=Integer.MAX_VALUE) 
		distances.add(distanceS);
	    if (distanceE!=Integer.MAX_VALUE) 
		distances.add(distanceE);
	    if (distanceW!=Integer.MAX_VALUE) 
		distances.add(distanceW);


	
	//construct the array
    }
    
    private static int nextPosition(ArrayList<Integer> distancelist) {
	//returns position of next closest distance
	//-1 indicates no more entries, -2 or more  indicates we have found the expected
	int min=Integer.MAX_VALUE;
	int leastindex=-1;
	for (int i=0;i<distancelist.size();i++) {
	    if (distancelist.get(i)<0) continue;
	    if (distancelist.get(i)<min) {
		min=distancelist.get(i);
		leastindex=i;
	    }
	}
	//if min is 0, return the negative number corresponding to its position (so we can return # of moves)
	if (min==0 ) {
	    leastindex=-1*(leastindex+2);
	}
	return leastindex;
    }
	private static int move(Character[][] board, int sx, int sy, int ex, int ey) {
		
	    boolean okmap[][]=new boolean [board.length][board[0].length];
	    for (int i=0;i<board.length;i++) {
		for (int j=0;j<board[0].length;j++) {
		    okmap[i][j]=true;
		}
	    }
		
	    //System.out.println(okmap[0][0]);
	    okmap[sy][sx]=false;
	    ArrayList<Integer> movelist=new ArrayList<Integer>();
	    ArrayList<Integer> distancelist=new ArrayList<Integer>();
	    ArrayList<Integer> posxlist=new ArrayList<Integer>();
	    ArrayList<Integer> posylist=new ArrayList<Integer>();
	    
	    posxlist.add(sx);
	    posylist.add(sy);
	    movelist.add(0);
	    distancelist.add(distance(sx,sy,ex,ey));


	    int nexpos=nextPosition(distancelist);
	    while (nexpos>-1) {
		//set the distance to -, so we do not visit it again
		//System.out.println("DSAFA " +nexpos);
		//System.out.println(distancelist +" "+ movelist+posxlist+posylist);
	
		  
		distancelist.set(nexpos,-1*distancelist.get(nexpos));
		//add the new distances adjacent to this spot
		dist_surround(board,posxlist.get(nexpos),posylist.get(nexpos),ex,ey,okmap,distancelist,posxlist,posylist,movelist,movelist.get(nexpos));
		nexpos=nextPosition(distancelist);
	    }
	    if (nexpos==-1) {
		return 0;
	    }
	    else {
		//switch positions
		Character c= board[sy][sx];
		board[sy][sx]=board[ey][ex];
		board[ey][ex]=c;
		    
		return movelist.get(-1*(nexpos+2));
	    }
	}

	    

		/*
		int distanceN = Integer.MAX_VALUE;
		int distanceS = Integer.MAX_VALUE;
		int distanceE = Integer.MAX_VALUE;
		int distanceW = Integer.MAX_VALUE;

		if (sx == ex && sy == ey)
			return 0;

		if (safe(board, sx, sy-1)) {
		    //distanceN = distance(sx, ex, sy-1, ey);
		    distanceN = distance(sx, ex, sy-1, ey);
		}

		if (safe(board, sx, sy+1)) {
                        distanceS = distance(sx, ex, sy+1, ey);
                }

		if (safe(board, sx+1, sy)) {
                        distanceE = distance(sx+1, ex, sy, ey);
                }

                if (safe(board, sx-1, sy)) {
                        distanceW = distance(sx-1, ex, sy, ey);
                }
		
		int finalmin=Integer.MAX_VALUE;
		
		int min = Math.min(Math.min(Math.min(distanceN, distanceS),distanceE),distanceW);
		while (min!=Integer.MAX_VALUE) {
		    		    
		    if (min==distanceN) {
			finalmin=move(board,sx,ex,sy-1,by);
			//need to update an array containing where we have visited already
		    }
		}
			
		*/
		//return min;



	static int distance(int sx, int sy, int ex, int ey) {
		return (sx-ex)*(sx-ex) + (sy-ey)*(sy-ey);
	}

    static boolean safe(Character[][] board, int x, int y, boolean okmap[][]) {
	    //out pof bounds
	    //System.out.println(x+" "+y+" "+board[0].length+ " "+board.length);
	    if (x<0 || y<0 || x>=board[0].length || y>=board.length) return false;
	    return  (board[y][x].equals('E') && okmap[y][x]);
	}

}

