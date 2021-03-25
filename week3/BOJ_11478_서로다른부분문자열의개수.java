package week3;

import java.io.*;
import java.util.*;

public class BOJ_11478_서로다른부분문자열의개수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		HashSet<String> arr = new HashSet<>();
		int N = input.length();
		String tmp;

			for(int i=0; i<N;i++) {
				for(int j=i+1;j<=N;j++) {
					tmp = input.substring(i,j);
					arr.add(tmp);
				}
			}
		System.out.println(arr.size());
	}
}
