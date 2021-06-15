package week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_12904_A와B {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder S = new StringBuilder(br.readLine());
		StringBuilder T = new StringBuilder(br.readLine());
		
		while(S.length()<T.length()) {
			if(T.charAt(T.length()-1)=='A') {
				T.deleteCharAt(T.length()-1);
			}else if(T.charAt(T.length()-1)=='B') {
				T.deleteCharAt(T.length()-1);
				T.reverse();
			}
		}
		if(T.toString().equals(S.toString())) {
			System.out.println(1);
		}else
			System.out.println(0);
		
	}
}
