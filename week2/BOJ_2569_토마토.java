package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class tmt{
	int x, y, h;
	public tmt(int x, int y, int h) {
		this.x = x;
		this.y = y;
		this.h = h;
	}
}
public class BOJ_2569_토마토 {
	static int M,N,H;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		int[] dx = {-1,0,1,0,0};
		int[] dy = {0,-1,0,1,0};
		int[] dh = {-1,1};		
		Queue<tmt> q = new LinkedList<>();
		int[][][] tomato = new int[N][M][H];
		boolean[][][] check = new boolean[N][M][H];
		int total=0;
		for(int h=0;h<H;h++) {
			for(int n=0;n<N;n++) {
				 st = new StringTokenizer(br.readLine());
				for(int m=0;m<M;m++) {
					tomato[n][m][h] = Integer.parseInt(st.nextToken());
					if(tomato[n][m][h]==0) total++;
					if(tomato[n][m][h]==1) q.add(new tmt(n,m,h));		
				}
			}
		}
		int day = 0;
		if(total==0) {
			System.out.println(0);
			return;
		}
		while(!q.isEmpty()) {
			int cnt = q.size();
			for(int c =0;c<cnt;c++) {
				tmt one = q.poll();
				check[one.x][one.y][one.h]=true;
				
				for(int d=0;d<5;d++) {
					int nx = one.x+dx[d];
					int ny = one.y+dy[d];
					int nh = one.h;
					if(d==4) {
						nx = one.x;
						ny = one.y;
						for(int h=0;h<2;h++) {
							nh = one.h+dh[h];
							if(isRange(nx,ny,nh)&&!check[nx][ny][nh]&&tomato[nx][ny][nh]==0) {
								check[nx][ny][nh]=true;
								q.add(new tmt(nx,ny,nh));
								total--;
							}
						}
						break;
					}
					if(isRange(nx,ny,nh)&&!check[nx][ny][nh]&&tomato[nx][ny][nh]==0) {
						check[nx][ny][nh]=true;
						q.add(new tmt(nx,ny,nh));
						total--;
					}
				}
			}
			day++;
			if(total==0) break;
		}
		if(total==0) System.out.println(day);
		else System.out.println(-1);
	}

	private static boolean isRange(int nx, int ny, int nh) {
		return nx>=0&&nx<N&&ny>=0&&ny<M&&nh>=0&&nh<H;
	}
}
