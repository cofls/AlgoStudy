package week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Dot{
	int x, y;
	public Dot(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class BOJ_17836_공주님을구해라 {
	static int[] dx = {-1,0,0,1};
	static int[] dy = {0,1,-1,0};
	static boolean[][] check;
	static int[][] map ;
	static Dot sword;
	static int N,M,T;
	static int time1=Integer.MAX_VALUE, time2=Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		check = new boolean[N][M];
		for(int n=0;n<N;n++) {
			st = new StringTokenizer(br.readLine());
			for(int m=0;m<M;m++) {
				map[n][m] = Integer.parseInt(st.nextToken());
				if(map[n][m]==1) check[n][m] = true;
				if(map[n][m]==2) {
					sword = new Dot(n,m);
					map[n][m]=0;
				}
			}
		}
		if(find()) {
			int result = Math.min(time1, time2);
			if(result<=T)
				System.out.println(result);
			else
				System.out.println("Fail");
			return;
		}else {
			System.out.println("Fail");
		}
	}

	private static boolean find() {
		Queue<Dot> q = new LinkedList<>();
		boolean getSword = false;
		//boolean finish = false;
		q.add(new Dot(0,0)); //시작점
		check[0][0] = true;
		
		while(!q.isEmpty()) {
			Dot tmp = q.poll();
			
			for(int d=0;d<4;d++) {
				int nx = tmp.x + dx[d];
				int ny = tmp.y + dy[d];
				//int 배열을 이용해서 최소로 이동하는 카운트 세주기..!!
				//방문하지 않았으면서....
				if(isRange(nx,ny)&&!check[nx][ny]) {
					map[nx][ny]=map[tmp.x][tmp.y]+1;
					check[nx][ny]=true;
					q.add(new Dot(nx,ny));
				}else if(isRange(nx,ny)&&check[nx][ny]) {
					//방문했지만 더 작게 이동할 수 있으면 그걸로 업데이트
					if(map[nx][ny]>map[tmp.x][tmp.y]+1) {
						map[nx][ny]=map[tmp.x][tmp.y]+1;
						q.add(new Dot(nx,ny));
					}
				}
				//검 겟!!
				if(nx==sword.x&&ny==sword.y) {
					time1= map[nx][ny]+Math.abs(N-1-nx)+Math.abs(M-1-ny);//Math.abs 굳이 안해줘도 됨.
					getSword = true;
					continue;
				}
				if(nx == N-1&&ny ==M-1) {
					time2 = map[nx][ny];
					//finish = true;
					return true;
				}
			}
		}
		if(getSword) return true;
		return false;
	}

	private static boolean isRange(int nx, int ny) {
		return nx>=0&&nx<N&&ny>=0&&ny<M;
	}
}
