package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_5052_전화번호목록 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	     
	     int T = Integer.parseInt(br.readLine());
	     StringBuilder sb = new StringBuilder();
	     for(int t=0;t<T;t++) {
	    	 int N = Integer.parseInt(br.readLine());
	    	 String[] number = new String[N];
	    	 for(int n=0;n<N;n++) {
	    		 number[n]= br.readLine();
	    	 }
	    	 boolean flag = true;
	    	 Arrays.sort(number);
	    	 
	    	 for (int i = 0; i < N - 1; i++) {
	             int current = number[i].length();
	             int next = number[i + 1].length();
	             //길이 다를 때만 검사
	             if (current < next) {
	                 if (number[i + 1].startsWith(number[i])) {
	                     flag = false;
	                     break;
	                 }
	             }
	         }
	  
	         if (flag) {
	             sb.append("YES\n");
	         } else {
	             sb.append("NO\n");
	         }
	     }
	    System.out.println(sb.toString());
	}
}