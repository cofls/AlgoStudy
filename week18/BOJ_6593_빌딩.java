package week18_dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_6593_빌딩 {
	static int[] dx = {-1,0,0,1,0,0};
	static int[] dy = {0,-1,1,0,0,0};
	static int[] dz = {0,0,0,0,1,-1};
	static int L,R,C;
	static char[][][] building;
	static boolean[][][] check;
	static dot start;
	static dot end;
	static class dot implements Comparable<dot>{
		int l, r, c, min;
		public dot(int r, int c, int l, int min) {
			this.l = l;
			this.r = r;
			this.c = c;
			this.min = min;
		}
		@Override
		public int compareTo(dot o) {
			return min-o.min;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		loop: while(true) {
			st = new StringTokenizer(br.readLine());
			L = stoi(st.nextToken());
			R = stoi(st.nextToken());
			C = stoi(st.nextToken());
			
			if(L==R&&R==C&&C==0) break;
			building = new char[R][C][L];
			check = new boolean[R][C][L];
			String tmp;
			for(int l=0;l<L;l++) {
				for(int r=0;r<R;r++) {
					tmp = br.readLine();
					for(int c=0;c<C;c++) {
						building[r][c][l]=tmp.charAt(c);
						if(building[r][c][l]=='#') check[r][c][l]=true;
						else if(building[r][c][l]=='S') start = new dot(r,c,l,0);
						else if(building[r][c][l]=='E') end = new dot(r,c,l,10000);
					}
				}tmp = br.readLine();
			}
			PriorityQueue<dot> q = new PriorityQueue<>();
			q.add(start);
			check[start.r][start.c][start.l]=true;
			while(!q.isEmpty()) {
				dot cur = q.poll();
				for(int i=0;i<6;i++) {
					int nl = cur.l+dz[i];
					int nr = cur.r+dx[i];
					int nc = cur.c+dy[i];
					
					if(!isRange(nl,nr,nc)) continue;
					if(nl==end.l && nr==end.r && nc==end.c) {
						end.min = cur.min+1;
						sb.append("Escaped in "+(end.min)+" minute(s).\n");
						continue loop;
					}
					
					if(!check[nr][nc][nl]&&building[nr][nc][nl]=='.') {
						check[nr][nc][nl]=true;
						q.add(new dot(nr,nc,nl,cur.min+1));
					}
				}
			}
			if(end.min==10000)sb.append("Trapped!\n");
		}
		System.out.println(sb.toString());
		
	}

	private static boolean isRange(int nl, int nr, int nc) {
		return nl>=0&&nl<L&&nr>=0&&nr<R&&nc>=0&&nc<C;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
