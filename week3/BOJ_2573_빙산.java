package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class bing{
	int x, y, h;
	public bing(int x, int y, int h) {
		this.x=x;
		this.y=y;
		this.h=h;
	}
}
public class BOJ_2573_빙산 {
	static int N,M;
	static int[][] ice;
	static int[][] newice;
	static boolean[][] check;
	static int groupNum=0;
	static int total=0;
	static int[] dx = {-1,0,0,1};
	static int[] dy = {0,-1,1,0};
	static Queue<bing> icy = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		ice = new int[N][M];
		check = new boolean[N][M];
		for(int n=0;n<N;n++) {
			 st = new StringTokenizer(br.readLine());
			for(int m=0;m<M;m++) {
				ice[n][m] = Integer.parseInt(st.nextToken());
				if(ice[n][m]!=0) {
					icy.add(new bing(n,m,ice[n][m]));
					check[n][m]=true;
					total++;
				}
			}
		}
		
		//빙산 없으면
		if(total==0) {
			System.out.println(0);
			return;
		}
		//이미 그룹이 2개 이상이면 0년 출력
		groupNum=0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(check[i][j]==true) {
					group(i,j);
					groupNum++;
				}
			}
		}
		if(groupNum>1) {
			System.out.println(0);
			return;
		}
		
		int year=0;
		while(!icy.isEmpty()) {
			int num = icy.size();
			total = num;
			newice = new int[N][M];
			for(int t=0;t<num;t++) {
				bing tmp = icy.poll();
				
				int cnt=0;
				//사방향검사
				for(int i=0;i<4;i++) {
					int nx = tmp.x + dx[i];
					int ny = tmp.y + dy[i];
					//범위 벗어남
					if(nx<0&&nx>=N&&ny<0&&ny>=M) continue;
					
					if(ice[nx][ny]==0) cnt++;
				}
				//높이 있을 때만 넣기
				if(tmp.h-cnt>0) {
					newice[tmp.x][tmp.y]=tmp.h-cnt;
					icy.add(new bing(tmp.x, tmp.y,tmp.h-cnt));
				}else {
					total--;
				}
				
			}
			year++;
			//빙산 녹을때까지 분리되지 않으면 0출력하기
			if(total==0) {
				System.out.println(0);
				return;
			}
			check = new boolean[N][M];
			copy();//newice>ice
			groupNum=0;
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(check[i][j]==true) {
						group(i,j);
						groupNum++;
					}
				}
			}
			//분리
			if(groupNum>1) {
				System.out.println(year);
				return;
			}
		}
	}
	private static void group(int i, int j) {
		check[i][j] = false;
		
		for(int d=0;d<4;d++) {
			int nx = i+dx[d];
			int ny = j+dy[d];
			
			if(nx<0&&nx>=N&&ny<0&&ny>=M) continue;
			
			if(check[nx][ny]) group(nx,ny);
		}
		return;
	}
	private static void copy() {
		for(int n=0;n<N;n++) {
			for(int m=0;m<M;m++) {
				ice[n][m] = newice[n][m];
				if(ice[n][m]!=0) check[n][m]=true;
			}
		}
	}
}
