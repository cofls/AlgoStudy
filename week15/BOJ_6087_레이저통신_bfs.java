package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//최단 거리가 아닌 꺾임이 적은 것 찾는거임.....
//그래서 최단으로 꺾이면서 어떤 곳에 도착할 수 있다면 값 갱신하면서 거기서 다시 bfs 돌아줘야 함.
class lazer{
	int x, y,dir,cnt;
	public lazer(int x, int y,int dir,int cnt) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.cnt = cnt;
	}
}
public class BOJ_6087_레이저통신_bfs {
	static char[][] map;
	static int[][] visited ;
	static lazer lz2;
	static int result=Integer.MAX_VALUE;
	static int W,H;
	static int[] dx = {-1,0,0,1};
	static int[] dy = {0,-1,1,0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new char[H][W];
		visited = new int[H][W];
		
		lazer[] lz= new lazer[2];
		//map생성
		int n=0;
		for(int h =0;h<H;h++) {
			String tmp = br.readLine();
			for(int w =0;w<W;w++) {
				map[h][w] = tmp.charAt(w);
				if(map[h][w]=='C') {
					lz[n++]=new lazer(h,w,-1,0);	
				}else if(map[h][w]=='*') {
					visited[h][w] = -1;
				}
			}
		}
		lz2 = new lazer(lz[1].x,lz[1].y,-1,0);
		visited[lz[0].x][lz[0].y] = -1;
		point(lz[0].x,lz[0].y);
		
		System.out.println(result-1);
	
	}
	private static void point(int x, int y) {
		Queue<lazer> tmp = new LinkedList<>();
		tmp.add(new lazer(x,y,-1,0));
		
		while(!tmp.isEmpty()) {
			lazer lz = tmp.poll();
			int count = lz.cnt;
			int direction = lz.dir;
			
			if(lz.x==lz2.x&&lz.y==lz2.y) {
				result = Math.min(lz.cnt,result);
				continue;
			}
			//System.out.println(lz.x+" "+lz.y+" "+lz.cnt+" "+lz.dir);
			
			for(int i=0;i<4;i++) {
				int nx = lz.x+dx[i];
				int ny = lz.y+dy[i];
				int nm = count;
				//범위 벗어나면
				if(!isRange(nx,ny)) continue;
				
				//방향 바뀌면
				if(direction!=i) nm+=1;
				//방문하지 않은 경우
				if(visited[nx][ny]==0) {
					visited[nx][ny] = nm;
					tmp.add(new lazer(nx,ny,i,nm));
				}else if(visited[nx][ny]>=nm) {
					//방문했는데 더 작게 방문할 수 있으면 갱신
					visited[nx][ny] =nm;
					tmp.add(new lazer(nx,ny,i,nm));
				}
			}
		}
		
	}
	private static boolean isRange(int nx, int ny) {
		return nx>=0&&nx<H&&ny>=0&&ny<W;
	}
}
