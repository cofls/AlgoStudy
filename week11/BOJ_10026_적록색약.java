package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10026_적록색약 {
	static char[][] color;
	static char[][] color2;
	static boolean[][] check;
	static boolean[][] check2;
	static int[] dx = {-1,0,0,1};
	static int[] dy = {0,-1,1,0};
	static int N, c1=0,c2=0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		color = new char[N][N];
		color2 = new char[N][N];
		check = new boolean[N][N];
		check2 = new boolean[N][N];
		for(int n=0;n<N;n++) {
			String tmp = br.readLine();
			for(int m=0;m<N;m++) {
				color[n][m] = tmp.charAt(m);
				
				if(color[n][m]=='G') {
					color2[n][m] = 'R';
				}else {
					color2[n][m] = tmp.charAt(m);
				}
			}
		}
		
		for(int n=0;n<N;n++) {
			for(int m=0;m<N;m++) {
				if(!check[n][m]) {
					check[n][m]=true;
					dfs(color,check,n,m);
					c1++;
				}
				if(!check2[n][m]) {
					check2[n][m]=true;
					dfs(color2,check2,n,m);
					c2++;
				}
			}
		}
		
		System.out.println(c1+" "+c2);
		
		
	}
	private static void dfs(char[][] c, boolean[][] ch, int x, int y) {
		for(int i=0;i<4;i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if( isRange(nx,ny)&&c[x][y]==c[nx][ny]&& !ch[nx][ny]) {
				ch[nx][ny]=true;
				dfs(c,ch,nx,ny);
			}
		}
		
	}
	private static boolean isRange(int nx, int ny) {
		return nx>=0 && nx<N&&ny>=0 &&ny<N;
	}
}
