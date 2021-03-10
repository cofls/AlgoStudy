package week5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_1493_박스채우기 {
	static int count =0;
	static boolean solve = true;
	static int L, W, H, N;
	static int[] c = new int[20];
	static int max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		N = Integer.parseInt(br.readLine());
		for(int n=0;n<N;n++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int cNum = Integer.parseInt(st.nextToken());
			c[k]=cNum;
			max = Math.max(k, max);
		}

		calc(L,W,H);

		if(solve) bw.write(String.valueOf(count));
		else bw.write(String.valueOf(-1));
		
		bw.flush();
		bw.close();
		br.close();
		
	}
	private static void calc(int l, int w, int h) {
		if (l > 0 && w > 0 && h > 0) {
			//큰 것부터 채우기
			int i;
			for(i=max;i>=0;i--) {
				
				int cur = 1<<i; //한 변의 길이 2^i
				if((c[i]>0)&& (l>=cur)&&(w>=cur)&&(h>=cur)) {
					c[i]--;//큐브 사용
					count++;
					calc(l,w,h-cur);
					calc(l-cur,cur,cur);
					calc(l,w-cur,cur);
					break;
				}
			}
			//끝까지 돌면 F
			if(i==-1) solve = false;
		}
	}
}
