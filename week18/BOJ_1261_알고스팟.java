package week18_dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class dot implements Comparable<dot>{
	int x, y, cnt;
	public dot(int x, int y, int cnt) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}
	@Override
	public int compareTo(dot o) {
		return this.cnt-o.cnt;
	}
}
public class BOJ_1261_알고스팟 {
	static int[] dx = {-1,0,0,1};
	static int[] dy = {0,-1,1,0};
	static int N,M;
	static boolean[][] visited;
	static int result=Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		
		int[][] arr = new int[M][N];// 인접행렬
		visited = new boolean[M][N];
		for (int i = 0; i < M; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < N; j++) {
				arr[i][j] = tmp.charAt(j)-'0';
			}
		}
		if(N==M&&M==1) {
			System.out.println(0);
			return;
		}
		
		PriorityQueue<dot> q = new PriorityQueue<>();
		q.add(new dot(0,0,0));
		visited[0][0] = true;
		//ArrayList<Integer> result = new ArrayList<>();
		while(!q.isEmpty()) {
			dot tmp = q.poll();
			for(int i=0;i<4;i++) {
				int nx = tmp.x + dx[i];
				int ny = tmp.y + dy[i];
				//도착
				if(nx==M-1&&ny==N-1) {
					System.out.println(tmp.cnt);
					return;
				}
				//범위 벗어나면
				if(!isRange(nx,ny)) continue;
				
				//방문안했으면서
				if(!visited[nx][ny]) {
					//벽이면 깨기
					if(arr[nx][ny]==1) {
						visited[nx][ny] = true;
						q.add(new dot(nx,ny,tmp.cnt+1));
					}else {
						visited[nx][ny] = true;
						q.add(new dot(nx,ny,tmp.cnt));
					}
				}
				
			}
		}
		
	}

	private static boolean isRange(int nx, int ny) {
		return nx>=0&&nx<M&&ny>=0&&ny<N;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
