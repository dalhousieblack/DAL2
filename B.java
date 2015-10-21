import static java.lang.System.out;
import java.util.*;
public class B {

    static int summation(int n) {
	return n*(n+1)/2;
    }
    static int firstindex(int ind,int len) {
	int i;
	int curind=ind;
	int nn=0;
	for (i=len;i>0;i--) {
	    if (curind>ind){
		curind-=i;
		nn++;
	    }
	    else{
		break;
	    }
		   
	}
	return curind+nn;
    }
    static int revsum(int n) {
	int ret=0;
	for (int i=12;i>12-n;i--) {
	    ret+=i;
	}
	return ret;
    }
    static char[] works(String a, String b, int[][]ops,String orig[], int indices[],int oplens[]) {
	//b is the longer one
	boolean fail=true;
	int len=b.length();
	int minlen=a.length();;
	char ret[]=new char[len];
	int verse[]=new int[len];
	//	int inverse[]=new int[len];
	for (int i=0;i<minlen;i++) {
	    verse[i]=(int)((a.charAt(i)-b.charAt(i)));
	    if (verse[i]<0) verse[i]+=26;
	    //  inverse[i]=(int)((a.charAt(i)-b.charAt(i)));
	    //if (inverse[i]<0) inverse[i]+=26;
	    //out.println(verse[i]);
	}

	//check to see if any entry in ops is = to verse or inverse
	//boolean invworks=true;
	boolean verworks=true;
	int i=0;
	int lenindex=-1;
	int bigindex=-1;
	if (minlen>=7 && minlen <=9) {
	    lenindex=minlen-7;
	}
	if (len>=7 && len <=9) {
	    bigindex=len-7;
	}
	if (minlen>9 ) {
	    lenindex=minlen-8;
	}
	if (len>9) {
	    bigindex=len-8;
	}
	int gf=revsum(indices[lenindex]);//summation(12)-summation(12-indices[lenindex]);
	int endf=revsum(indices[lenindex+1]);//summation(12)-summation(12-indices[lenindex+1]);
	int numbig=indices[bigindex+1]-indices[bigindex];


	int reset=0;
	    for (i=gf;i<endf;i++) {
		//compare tops and verse/inverse
		if (oplens[i]!=len) {
		    reset=0;
		    continue;
		}
		//		invworks=true;
		verworks=true;
		//out.println(i+" "+indices[lenindex]+" "+bigindex);
		for (int j=0;j<minlen && (verworks);j++) {//(invworks||verworks);j++) {
		    //		    out.print(ops[i][j]+" "+verse[j]+",");
		    if (verse[j]!=ops[i][j]) verworks=false;
		    //		    if (inverse[j]!=ops[i][j]) invworks=false;
		}
		
		if (verworks ) break;//|| invworks) break;
		reset++;
	    }


	    //time to reverse engineer the word
	    int df=indices[bigindex]+reset;//firstindex(i,ops.length);
	    if (verworks) {
		//out.println("ayyver "+df);	
		//		out.println(orig[df]+" "+reset+ " "+bigindex+" "+len);    
		for (int d=0;d<len;d++) {

		    int diff=-((int)orig[df].charAt(d)-(int)b.charAt(d));
		    //(int)orig[df].charAt(d)-(int)b.charAt(d);
		    //System.out.print(orig[df].charAt(d)+" "+b.charAt(d)+" "+(int)diff+ " \n");
		    if (diff<0) diff+=26;
		    
		    ret[d]=(char)(diff+(int)'a');
		    //System.out.print((int)ret[d]+ " ");
		    
		}
	    }
	    /*
	    else if (invworks) {
		// out.println("ayyinver");
		for (int d=0;d<len;d++) {
		    int diff=-((int)orig[df].charAt(d)-(int)b.charAt(d));
		    //(int)orig[df].charAt(d)-(int)a.charAt(d);
		    if (diff<0) diff+=26;
		    
		ret[d]=(char)(diff+(int)'a');
		//System.out.print((int)ret[d]+ " ");
		}*/
	    
    
    
	
    
	    else {ret[0]='\0';}
	    return ret;
    }
    /*;
      for (int j=0;j<len;j++) {
      if (inverse[j]==0) {
		offset=((int)(orig.charAt(j)-a.charAt(j))%26) ;
		break;
	    }
	}
        out.println(offset);
	if (invworks) {
	    for (int i=0;i<len;i++) {
		ret[i]=(char)(((int)(inverse[i]+offset)%26));
		ret[i]+='a';
	    }

	}
	else if (verworks) {
	    for (int i=0;i<len;i++) {
		ret[i]=(char)(((int)(verse[i]+offset)%26));
		ret[i]+='a';
	    }

	    }*/
	//reverse engineer here!

	
    
    public static void main(String args[]) {
	//Oh god im so sorry
	/*
	String seven[]={"wordone","wordtwo","wordsix"};
	int sevlen=3;
	String eight[]={"wordfour","wordfive"};
	int eightlen=2;
	String nine[]={"wordthree"};
	int ninelen=1;
	String eleven[]={"firstphrase","thirdphrase","fifthphrase","sixthphrase"};
	int elevenlen=4;
	String twelve[]={"secondphrase","fourthphrase"};
	int twelvlen=2;
	int sevops[][]=new int[summation(sevlen)][7];
	int c=0;*/
	String words[]={"wordone","wordtwo","wordsix","wordfour","wordfive","wordthree","firstphrase","thirdphrase","fifthphrase","sixthphrase","secondphrase","fourthphrase"};
	
	
	int lengthindices[]={0,3,5,6,10,12};
	int ops[][]=new int [summation(words.length)][12];
	int oplens[]=new int[summation(words.length)];
	int toplen=summation(words.length);
	int c=0;
	for (int i=0;i<words.length;i++) {
	    //	    ops[c]=new int[];
	    //	    c++;
	    for (int j=i;j<words.length;j++) {
		//out.print(words[i]+" "+words[j]+" "+c+" :");
		for (int k=0;k<words[i].length();k++) {      
                    ops[c][k]=(int)(words[i].charAt(k)-words[j].charAt(k));
		    if (ops[c][k]<0) ops[c][k]+=26;
		    //out.print (" "+ops[c][k]);
		    oplens[c]=words[j].length();
		}
		//out.println();
		    c++;
	    }

	}

	Scanner sc=new Scanner(System.in);
	int n=sc.nextInt();
	
	for (int i=0;i<n;i++) {
	    String a,b;
	    a=sc.next();
	    b=sc.next();
	    if ( b.length()>12 || b.length()<7) {
		out.println("Key not found");
		continue;
	    }
	    char ret[];
	    /*    switch (b.length()) {
	    case 7:
		ret=works(a,b,sevops,seven);
		break;
	    case 8:
		ret=works(a,b,eightops,eight);
		break;
	    case 9:
		ret=works(a,b,nineops,nine);
		break;
	    case 11:
		ret=works(a,b,elevenops,eleven);
		break;
	    default:
		ret=works(a,b,twelvops,twelve);
		break;
	    
		}*/
	    if (a.length()>b.length() ) {
		ret=works(b,a,ops,words,lengthindices,oplens);
	    }
	    else {
		ret=works(a,b,ops,words,lengthindices,oplens);
	    }
	    if (ret[0]=='\0') {
		out.println ("Key not found");
	    }
	    else {
		//
		out.println("Key = "+ new String(ret));
	    }
	
	   
	}
    }
}
