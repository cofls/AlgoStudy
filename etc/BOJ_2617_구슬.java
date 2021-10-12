package floyd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

//���ſ� ������ ���� �Ǵ� ������ ������ ������ (n+1)/2�� �̻��� �� �� ������ ����� ������ �� �� ����.
public class BOJ_2617_구슬 {
	static int N,M;
	//static final int INF = 100;
	static int[][] marble;
	static ArrayList<Integer>[] arr;
	static boolean[] state;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N= stoi(st.nextToken());
		M= stoi(st.nextToken());
		
		int half = (N+1)/2;
		arr = new ArrayList[N+1];
		for(int i=1;i<=N;i++) {
			arr[i]= new ArrayList<>();
		}
		
		marble = new int[N+1][2];
//		for(int[] m:marble)
//			Arrays.fill(m, INF);
		int a, b;
		for(int m=0;m<M;m++) {
			st = new StringTokenizer(br.readLine());
			//���� a>b
			a = stoi(st.nextToken());
			b = stoi(st.nextToken());
			//marble[a][b]=a;
			arr[a].add(b);
		}
		//�� ��帶�� ���¸� �����ؼ�............
		for(int i=1;i<=N;i++) {
			state = new boolean[N+1];
			dfs(i,i);
		}
		
		int result = 0;
		for(int i=1;i<=N;i++) {
			if(marble[i][0]>=half||marble[i][1]>=half)
				result++;
		}
		System.out.println(result);
	}
	private static void dfs(int cur, int start) {
		state[cur] = true;
		for(int next:arr[cur]) {
			if(!state[next]) {
				marble[start][0]++;//0:나보다 가벼운거
				marble[next][1]++;//1:나보다 무거운거
				dfs(next,start);
			}
		}
		
	}
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}


//�÷��̵� ���߿� �����غ���.
/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, adj[][];
	static BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adj = new int[N][N];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken())-1;
			int e = Integer.parseInt(st.nextToken())-1;
			adj[s][e] = 1;
			adj[e][s] = -1;
		}
		
		floyd();
		
		System.out.println(check());
	}

	private static int check() {
		
		int cnt = 0;
		int middle = (N+1)/2;
				
		for (int i = 0; i < N; i++) {
			
			int big = 0;
			int small = 0;
			
			for (int j = 0; j < N; j++) {
				if( adj[i][j] == 1 ) ++big;
				if( adj[i][j] == -1 ) ++small;
			}
			
			if( big >= middle || small >=middle ) ++cnt;
		}
		
		return cnt;
	}

	private static void floyd() {
		
		for (int z = 0; z < N; z++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(adj[i][j] != 0) continue;
					if(adj[i][z] == 0) continue;
					if(adj[i][z] == adj[z][j])
						adj[i][j] = adj[i][z];
				}
			}
		}
	}
}
*/