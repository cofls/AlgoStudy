package week18_dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_4485_녹색옷 {
	static class dot implements Comparable<dot>{
		int x, y, m;
		public dot(int x, int y, int m) {
			this.x=x;
			this.y=y;
			this.m=m;
		}
		@Override
		public int compareTo(dot o) {
			return m-o.m;
		}
	}
	static int N;
	static int[][] map;
	static boolean[][] check;
	static int[] dx = {-1,0,0,1};
	static int[] dy = {0,1,-1,0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int t=1;
		while(true) {
			N = stoi(br.readLine());
			//종료
			if(N==0) break;
			
			map = new int[N][N];
			check = new boolean[N][N];
			for(int n=0;n<N;n++) {
				st = new StringTokenizer(br.readLine());
				for(int m=0;m<N;m++) {
					map[n][m] = stoi(st.nextToken());
				}
			}
			sb.append("Problem "+ t++ +": "+calc()+"\n");
		}
		System.out.println(sb.toString());
		
	}

	private static int calc() {
		PriorityQueue<dot> pq = new PriorityQueue<>();
		pq.add(new dot(0,0,map[0][0]));
		check[0][0] = true;
		
		while(!pq.isEmpty()) {
			dot tmp = pq.poll();
			
			for(int d=0;d<4;d++) {
				int nx = tmp.x+dx[d];
				int ny = tmp.y+dy[d];
				//범위 벗어나면
				if(!isRange(nx,ny)) continue;
				if(nx==N-1&&ny==N-1) {
					return tmp.m+map[nx][ny];
				}
				
				if(!check[nx][ny]) {
					check[nx][ny]=true;
					pq.add(new dot(nx,ny,tmp.m+map[nx][ny]));
				}
					
			}	
		}
		return 0;
	}

	private static boolean isRange(int nx, int ny) {
		return nx>=0&&nx<N&&ny>=0&&ny<N;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
