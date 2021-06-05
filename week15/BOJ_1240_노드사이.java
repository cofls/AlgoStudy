package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class weight{
	int to, w;
	public weight(int to, int w) {
		this.to = to;
		this.w = w;
	}
}
public class BOJ_1240_노드사이 {
	static ArrayList<weight>[] arr;
	static int sum;
	static int n1,n2;
	static boolean[] check;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		arr = new ArrayList[M+1];
		for(int i=0;i<=M;i++) {
			arr[i] = new ArrayList<>();
		}
		
		for(int i=1;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			//양방향
			arr[from].add(new weight(to,w));
			arr[to].add(new weight(from,w));
		}
		
		for(int i=0;i<N;i++) {
			check = new boolean[M+1];
			st = new StringTokenizer(br.readLine());
			n1 = Integer.parseInt(st.nextToken());
			n2 = Integer.parseInt(st.nextToken());
			sum = Integer.MAX_VALUE;
			check[n1] = true;
			calc(n1,0,check);
			sb.append(sum+"\n");
		}
		System.out.println(sb.toString());
	
	}
	private static void calc(int f,int tmp, boolean[] check) {
		if(f==n2) {
			sum = Math.min(sum,tmp);
			return;
		}
		
		for(int i=0;i<arr[f].size();i++) {
			if(!check[arr[f].get(i).to]) {
				check[arr[f].get(i).to] = true;
				calc(arr[f].get(i).to,tmp+arr[f].get(i).w,check);
				check[arr[f].get(i).to] = false;
			}
		}
		
	}
}
