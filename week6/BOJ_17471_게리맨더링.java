package week6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_17471_게리맨더링 {
	static int[] p;
	static int N;
	static boolean visited[];
	static ArrayList<Integer>[] garr ;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		garr = new ArrayList[N+1];
		
		p = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());	
		for(int n=1; n<N+1;n++) {
			p[n] = Integer.parseInt(st.nextToken());
			garr[n]= new ArrayList<>();
		}
		for(int n=1;n<=N;n++) {
			st = new StringTokenizer(br.readLine());	
			int T = Integer.parseInt(st.nextToken());
			for(int t =0; t<T;t++) {
				garr[n].add(Integer.parseInt(st.nextToken()));
			}
		}
		powerSet(1,new boolean[N+1]);
		if(min==Integer.MAX_VALUE)
			bw.write(String.valueOf(-1));
		else
			bw.write(String.valueOf(min));
		
		bw.flush();
		bw.close();
		br.close();
	
	}
	public static void powerSet(int cnt, boolean[] isSelected) {
		if (cnt == N+1) {
			int sum1 = 0;
			int sum2 = 0;
		
			for (int i = 1; i <= N; i++) {
				if (isSelected[i]) {
					sum1+=p[i];
				}else {
					sum2+=p[i];
				}
			}
			visited = new boolean[N+1];
			int num =0;
			
			//덩어리 갯수 체크
			for(int i=1; i<=N;i++) {
				if(!visited[i]) {
					check(i,isSelected[i], isSelected);
					num++;
				}
			}
			if(num==2)
				min = Math.min(Math.abs(sum2-sum1), min);
			return;
		}

		//선택
		isSelected[cnt]=true;
		powerSet(cnt+1, isSelected);
		//비선택
		isSelected[cnt]=false;
		powerSet(cnt+1, isSelected);
	}
	private static void check(int idx, boolean tf, boolean[] isSelected) {
		visited[idx]=true;
		for(int i=0; i<garr[idx].size();i++) {
			if(garr[idx].get(i)!=0&&!visited[garr[idx].get(i)]&&isSelected[garr[idx].get(i)]==tf) {
				check(garr[idx].get(i),tf, isSelected);
			}
		}
		
	}
}
