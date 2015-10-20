import java.util.*;
import static java.lang.System.out;

public class A{

    public static void main(String args[]) {

	Scanner sc=new Scanner(System.in );
	
	int tes=sc.nextInt();
	int MAX=9999;
	for (int i=0;i<tes;i++){
	    int M=sc.nextInt();
	    HashMap<String,Integer> hs=new HashMap<String,Integer>();
	    
	    int connects[][]=new int[M][M];
	    for (int j=0;j<M;j++) {
		for (int k=0;k<M;k++) {
		    connects[j][k]=MAX;
		}
		connects[j][j]=0;
	    }
	    int cont=sc.nextInt();
	    ArrayList<String> names=new ArrayList<String>();
	    //could be linked list but whatever
	    int entry=0;
	    for (int j=0;j<cont;j++) {
		String a=sc.next();
		String b=sc.next();
		if (a.equals(b)) continue;
		if (!hs.containsKey(a)) {
		    names.add(a);
		    hs.put(a,entry);
		    entry++;
		}
		if (!hs.containsKey(b)) {
		    names.add(b);
		    hs.put(b,entry);
		    entry++;
		}


		int an=hs.get(a);
		int bn=hs.get(b);
		connects[an][bn]=1;
		connects[bn][an]=1;

		/*		for (int d=0;d<M;d++) {
		    
		    out.printf(" %d ",connects[an][d]);
		}
		out.print("\n"+b);
		for (int d=0;d<M;d++) {
		    
		    out.printf(" %d ",connects[bn][d]);
		    }*/

		for (int k=0;k<M;k++) {
		    if (connects[an][k]==MAX && connects[bn][k]==MAX) continue;

		    if (connects[an][k]>connects[bn][k]+1) {
			connects[an][k]=connects[bn][k]+1;
			connects[k][an]=connects[bn][k]+1;
			
		    }

		    if (connects[bn][k]>connects[an][k]+1) {
			connects[bn][k]=connects[an][k]+1;
			connects[k][bn]=connects[an][k]+1;
		    }
		}
		/*
		out.print("\n"+a);
		for (int d=0;d<M;d++) {
		    
		    out.printf(" %d ",connects[an][d]);
		}
		out.print("\n"+b);
		for (int d=0;d<M;d++) {
		    
		    out.printf(" %d ",connects[bn][d]);
		    }
		
		*/
		
	    }
	    //print stuff
	    Collections.sort(names, (r1,r2) -> r1.compareTo(r2));
	    for (int ui=0;ui<names.size();ui++) {
		int first=0,second=0,third=0,fourth=0,fifth=0,other=0;
		int u=hs.get(names.get(ui));
		for (int d=0;d<M;d++) {
		    if (connects[u][d]==1)first++;
		    else if (connects[u][d]==2)second++;
		    else if (connects[u][d]==3)third++;
		    else if (connects[u][d]==4)fourth++;
		    else if (connects[u][d]==5)fifth++;
		    else if (connects[u][d]>0 && connects[u][d]<MAX) other++;

		}
		out.printf("%s %d %d %d %d %d %d\n",names.get(ui),first,second,third,fourth,fifth,other);
	    }
	
	}
    }
}