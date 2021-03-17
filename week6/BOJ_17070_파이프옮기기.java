package week6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_17070_파이프옮기기 {
	static int N;
	static int cnt = 0;
	static int[] dx = {0, 1, 1};
	static int[] dy = {1, 0, 1}; //가로, 세로, 대각선
	static int[][] house;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		house = new int[N+1][N+1];
		StringTokenizer st;
		for(int r=1; r<=N;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=1;c<=N;c++) {
				house[r][c]= Integer.parseInt(st.nextToken());
			}
		}
		//끝점을 기준으로 (1,2)
		DFS(1,2,0);
		
		bw.write(String.valueOf(cnt));
		bw.flush();
		bw.close();
		br.close();
	
	}
	private static void DFS(int x, int y, int dir) {
		if(x==N && y==N) {
			cnt++;
			return;
		}
		
		for(int d=0;d<3;d++) {
			//가로>세로, 세로>가로 불가능
			if((d==0&&dir==1)||(d==1&&dir==0)) continue;
					
			int nextX = x+dx[d];
			int nextY = y+dy[d];
			
			//범위 벗어나거나, 벽일 경우
			if(nextX>N||nextY>N||house[nextX][nextY]==1) continue;
			
			//대각선일 경우, 확인해야할 벽
			if(d==2 &&(house[x][y+1]==1||house[x+1][y]==1)) continue;
			
			DFS(nextX,nextY,d);
		}
		
	}
	
}
