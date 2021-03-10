package week5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Dot{
	int x;
	int y;
	public Dot(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class BOJ_2468_안전영역 {
	static int[][] water;
	static boolean[][] check;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	static Queue<Dot> q = new LinkedList<Dot>();
	static int[] dx= {-1,0,1,0};//위, 오, 아,왼
	static int[] dy= {0,1,0,-1};
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		water =  new int[N][N];
		check =  new boolean[N][N];
		
		StringTokenizer st;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				water[i][j]=Integer.parseInt(st.nextToken());
				max = Math.max(max, water[i][j]);
				min = Math.min(min, water[i][j]);
			}
		}
		
		int result =0;
		for(int m=min; m<=max;m++) {
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(water[i][j]<=m)
						check[i][j]=true;
					else
						q.add(new Dot(i,j));
				}
			}
			int tmp =0;
			while(!q.isEmpty()) {
				Dot land = q.poll();
				if(!check[land.x][land.y]) {
					tmp++;
					dfs(land.x,land.y);
				}
			}
			if(max==min) tmp=1;
			result =Math.max(result, tmp);
			check =  new boolean[N][N];
		}
		bw.write(String.valueOf(result));
		
		bw.flush();
		bw.close();
		br.close();
		
	}
	private static void dfs(int x, int y) {
		check[x][y]=true;
		
		for(int d=0;d<4;d++) {
			int nextX = x +dx[d];
			int nextY = y +dy[d];
			if(isRange(nextX,nextY)&&!check[nextX][nextY]) {
				dfs(nextX,nextY);
			}
		}
	}
	private static boolean isRange(int nextX, int nextY) {
		return nextX>=0&&nextX<N&&nextY>=0&&nextY<N;
	}
}