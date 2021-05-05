package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class pair{
	int num, cnt;
	public pair(int num, int cnt) {
		this.num=num;
		this.cnt=cnt;
	}
}
public class BOJ_2637_장난감조립 {
	static ArrayList<pair>[] arr;
	static int[] result;
	static int[][] count;
	static int[] degree;
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		arr = new ArrayList[N+1];
		result = new int[N+1];
		degree = new int[N+1];
		count = new int[N+1][N+1];
		for(int i=0;i<N+1;i++) {
			arr[i] = new ArrayList<pair>();
		}
		StringTokenizer st;
		for(int m=0;m<M;m++) {
			st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			int fromNum = Integer.parseInt(st.nextToken());
			
			arr[from].add(new pair(to,fromNum));
			degree[to]++;
		}
		
		topologySort();
	}
	private static void topologySort() throws IOException {
		Queue<Integer> q = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		//진입차수가 0인 노드를 큐에 삽입
		for(int i=1;i<=N;i++) {
			if(degree[i]==0) {
				q.add(i);
				count[i][i] = 1;
			}
			
		}
		
		while(!q.isEmpty()) {
			int x = q.poll();
			
			for(int j=0;j<arr[x].size();j++) {
				//만들고자 하는 상품
				int next = arr[x].get(j).num;

				for(int i=1;i<=N;i++) {
					//5는 상품 1 2개로 만들 수 있다.
					if(count[x][i]>0)
					count[next][i] +=count[x][i]* arr[x].get(j).cnt;
				}
				if(--degree[next]==0) {
					q.add(next);
				}
			}
		}
		for(int i=1;i<=N;i++) {
			if(count[N][i]>0)
				sb.append(i+" "+count[N][i]+"\n");
		}
		System.out.println(sb.toString());
	}
}
