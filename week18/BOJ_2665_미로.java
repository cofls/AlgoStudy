package week18_dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ_2665_미로 {
	static class dot implements Comparable<dot>{
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
	static int[] dx = {-1,0,0,1};
	static int[] dy = {0,-1,1,0};
	static int N;
	static boolean[][] visited;
	static int result=Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = stoi(br.readLine());
		int[][] arr = new int[N][N];// 인접행렬
		visited = new boolean[N][N];
		
		for(int n=0;n<N;n++) {
			String tmp = br.readLine();
			for(int m=0;m<N;m++) {
				arr[n][m] = tmp.charAt(m)-'0';
			}
		}
		if(N==1) {
			System.out.println(0);
			return;
		}
		
		PriorityQueue<dot> q = new PriorityQueue<>();
		q.add(new dot(0,0,0));
		visited[0][0] = true;
		
		while(!q.isEmpty()) {
			dot tmp = q.poll();
			
			for(int d=0;d<4;d++) {
				int nx = tmp.x+dx[d];
				int ny = tmp.y+dy[d];
				if(nx==N-1&&ny==N-1) {
					System.out.println(tmp.cnt);
					return;
				}
				if(!isRange(nx,ny)) continue;
				if(!visited[nx][ny]) {
					if(arr[nx][ny]==1) {
						visited[nx][ny]=true;
						q.add(new dot(nx,ny,tmp.cnt));
					}else if(arr[nx][ny]==0) {
						visited[nx][ny]=true;
						q.add(new dot(nx,ny,tmp.cnt+1));
					}
				}
			}
		}
		
	}
	private static boolean isRange(int nx, int ny) {
		return nx>=0&&nx<N&&ny>=0&&ny<N;
	}
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
