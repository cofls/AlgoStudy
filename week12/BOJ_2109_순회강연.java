package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2109_순회강연 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		if(N==0) {
			System.out.println(0);
			return;
		}
		int[][] cl = new int[N][2];
		StringTokenizer st;
		int max = Integer.MIN_VALUE;
		for(int n=0;n<N;n++) {
			st = new StringTokenizer(br.readLine());
			cl[n][0] = Integer.parseInt(st.nextToken());
			cl[n][1] = Integer.parseInt(st.nextToken());
			max = Math.max(max, cl[n][1]);
		}
		
		//비용 기준으로 내림차순 하는 것이 포인트...
		// 그 날 까지만 강연을 하면 된다는 것이 중요함.
		
		//비용기준으로 내림차순, 같으면 날짜 기준으로 내림차순
		Arrays.sort(cl,(o1,o2)->{
			if(o1[0]==o2[0])
				return o2[1]-o1[1];
			return o2[0]-o1[0];
		});
				
		int sum = 0;
		boolean check[] = new boolean[max+1];
		
		loop : for(int n=0;n<N;n++) {
			int tmp = cl[n][1];
			for(int i=tmp;i>=1;i--) {
				if(!check[i]) {
					check[i] = true;
					sum+=cl[n][0];
					continue loop;
				}
			}
		}
		System.out.println(sum);
	}
}
