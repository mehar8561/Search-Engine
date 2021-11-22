package algorithms;

import java.util.Random;

public class Sequences {

	public static int editDistance(String word1, String word2) {
		int len1 = word1.length();
		int len2 = word2.length();

		int[][] dp = new int[len1 + 1][len2 + 1];
	 
		for (int i = 0; i <= len1; i++) {
			dp[i][0] = i;
		}
	 
		for (int j = 0; j <= len2; j++) {
			dp[0][j] = j;
		}
	 
	
		for (int i = 0; i < len1; i++) {
			char c1 = word1.charAt(i);
			for (int j = 0; j < len2; j++) {
				char c2 = word2.charAt(j);

				if (c1 == c2) {

					dp[i + 1][j + 1] = dp[i][j];
				} else {
					int replace = dp[i][j] + 1;
					int insert = dp[i][j + 1] + 1;
					int delete = dp[i + 1][j] + 1;
	 
					int min = replace > insert ? insert : replace;
					min = delete > min ? min : delete;
					dp[i + 1][j + 1] = min;
				}
			}
		}
	 
		return dp[len1][len2];
	}
	
	public static void main(String[] args) {
		
		String s1 = "inteanet torrain";
		String s2 = "internet domain";
		int d = editDistance(s1, s2);
		long totaltime=0;
		System.out.println("Dist between '" + s1 + "' and '" + s2 + "' = " + d);
		Random r = new Random();
		int len = 9;//
		String str = new String();
		
		String[] lst= new String[1000]; 
		for (int i=0; i <1000;i++)
		{
			
		for( int j = 0; j < len; j++ )
           str  += (char) ( 'a' + r.nextInt( 26 ) );
		
		lst[i] = str;
		}
	
		String s1r = "";
		String s2r = "";
 

		for ( int c =0; c<1000;c++){
        s1r="";
        s2r="";
      
        for( int j = 0; j < len; j++ ){
            s1r += (char) ( 'a' + r.nextInt(26));
            s2r += (char) ( 'a' + r.nextInt(26));
        }
long t1= System.currentTimeMillis();
		int dr = editDistance(s1r, s2r);
long t2 = System.currentTimeMillis();
totaltime += t2-t1; 

	}
		System.out.println("avg dist for 1000 random pairs of strings " + totaltime/1000);

}
}