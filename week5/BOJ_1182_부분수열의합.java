package week5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_1182_부분수열의합 {
	static int N, S;
	static int result =0;
	static int[] num;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		num = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int n=0; n<N;n++) {
			num[n] = Integer.parseInt(st.nextToken());
		}
		powerSet(0,new boolean[N]);
		if(S==0) result--;//원래 sum을 0으로 초기화해주기때문에 카운트 1을 줄여줌.
		// 모두 false라서 돌지 않고 sum=0을 바로 비교하게 되기 때문에
		bw.write(String.valueOf(result));
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void powerSet(int cnt, boolean[] isSelected) {
		if (cnt == N) {
			int sum =0;
			for (int i = 0; i < N; i++) {
				if (isSelected[i]) {
					sum+=num[i];
				}
			}
			if(sum==S) result++;
			return;
		}

		//선택
		isSelected[cnt]=true;
		powerSet(cnt+1, isSelected);
		//비선택
		isSelected[cnt]=false;
		powerSet(cnt+1, isSelected);
	}
}
