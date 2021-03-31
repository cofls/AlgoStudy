package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2156_포도주시식 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] grape = new int[N];
		int[] dp = new int[N];
		for(int n=0;n<N;n++) {
			grape[n]= Integer.parseInt(br.readLine());
		}
		//oxo
		//xoo
		//oox 경우의 수 3개가능
		// 첫잔일 경우
        if (N >= 1) {
            dp[0] = grape[0];
        }
        // 두잔일 경우
        if (N >= 2) {
            dp[1] = grape[0] + grape[1];
        }
        // 세잔일 경우
        if (N >= 3) {
            dp[2] = Math.max(dp[1], Math.max(grape[0] + grape[2], grape[1] + grape[2]));
        }
		for(int i=3;i<N;i++) {
			//???x, ooxo,oxoo
			dp[i] = Math.max(dp[i-1],Math.max(dp[i-2] + grape[i], dp[i-3]+grape[i-1]+ grape[i]));
		}
		System.out.println(dp[N-1]);
	}
}
