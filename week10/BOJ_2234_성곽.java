package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node{
	int r, c;
	boolean[] d = new boolean[4];
	public Node(int r, int c, boolean[] d) {
		this.r = r;
		this.c = c;
		this.d = d;
	}
}

public class BOJ_2234_성곽 {
	static int[] dx = {0,-1,0,1} ;//왼, 위 , 오, 아
	static int[] dy = {-1,0,1,0} ;//왼, 위 , 오, 아
	
	static int R,C;
	static boolean[][] check;
	static Node[][] map;
	static int max = Integer.MIN_VALUE;
	static int room=0;
	//static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new Node[R][C];
		check = new boolean[R][C];
		boolean[] tmp;
		for(int i=0;i<R;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<C;j++) {
				tmp = new boolean[4];
				int wall= Integer.parseInt(st.nextToken());
				
				for(int k = 1 ; k <= 8 ; k *= 2) {
					// 벽이 있는 경우에만 (비트연산자) 
					if((wall & k) == k) {
						switch(k) {
						case 1: tmp[0] = true; break;//서,왼
						case 2: tmp[1] = true; break;//북,위
						case 4: tmp[2] = true; break;//동,오
						case 8: tmp[3] = true; break;//남,아		
						}
					}
				}
				map[i][j] = new Node(i,j,tmp);
			}
		}
		int cnt =0;
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				if(!check[i][j]) {
					room =0;
					count(i,j);
					cnt++;
				}
				max = Math.max(room, max);
			}
		}
		sb.append(cnt+"\n"+max+"\n"); //이 성에 있는 방의 갯수, 최대 방의 넓이
		
		//벽 없애기
		max = Integer.MIN_VALUE;
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				for(int d=0;d<4;d++) {
					check = new boolean[R][C];//초기화
					if(!check[i][j]) {
						if(map[i][j].d[d]) {
							room =0;
							map[i][j].d[d]=false;
							count(i,j);
							map[i][j].d[d]=true;
						}
					}
					max = Math.max(room, max);
				}
			}
		}
		sb.append(max+"\n");
		System.out.println(sb.toString());
	}
	private static void count(int x, int y) {
		check[x][y]=true;
		++room;
		boolean[] dir = map[x][y].d;
		for(int i=0;i<4;i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			//if(nx < 0 || ny < 0 || nx >=R || ny >=C) continue;
			if(isRange(nx,ny)&&!dir[i]&&!check[nx][ny]) {
				count(nx,ny);//벽 없어서 움직임
			}
		}
	}
	private static boolean isRange(int x, int y) {
		return x>=0&&x<R&&y>=0&&y<C;
	}
}
