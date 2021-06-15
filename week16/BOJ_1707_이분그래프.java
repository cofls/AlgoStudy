package week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1707_이분그래프 {
	static int V,E;
	static ArrayList<Integer>[] map;
	static int[] check;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		for(int t=0;t<T;t++) {
			st = new StringTokenizer(br.readLine());
			V= Integer.parseInt(st.nextToken());
			E= Integer.parseInt(st.nextToken());
			map = new ArrayList[V+1];
			check = new int[V+1];
			
			for(int n=0;n<V+1;n++) {
				map[n] = new ArrayList<>();
			}
			for(int e=0;e<E;e++) {
				st = new StringTokenizer(br.readLine());
				int n1 = Integer.parseInt(st.nextToken()); 
				int n2 = Integer.parseInt(st.nextToken()); 
				map[n1].add(n2);
				map[n2].add(n1);
			}
			binary();
		}
	}
	private static void binary() {
		Queue<Integer> q = new LinkedList<Integer>();

		for(int i=1;i<=V;i++) {

			//일단 1번 노드부터
			if(check[i]==0) {
				q.add(i);
				check[i]=1;
			}
			while(!q.isEmpty()) {
				int tmp = q.poll();
				//해당 노드와 연결된 모든 노드 탐색
				for(int t=0;t<map[tmp].size(); t++) {
					//System.out.println(Arrays.toString(check));
					//아직 방문하지 않은 곳이면 큐에 넣기
					if(check[map[tmp].get(t)]==0) {
						q.add(map[tmp].get(t));
					}
					//자기와 연결된 애들이 값이 같으면 이분 그래프 불가능, NO 출력
					if(check[map[tmp].get(t)]==check[tmp]) {						
						System.out.println("NO");
						return;
					}
					//현재 값이 1인 노드와 연결된 애들 탐색, 방문하지 않은 노드들 값을 2로
					if(check[map[tmp].get(t)]==0&&check[tmp]==1) {
						check[map[tmp].get(t)]=2;
					}else if(check[map[tmp].get(t)]==0&&check[tmp]==2) {
						//현재 값이 2인 노드와 연결된 애들 탐색, 방문하지 않은 노드들 값을 1로
						check[map[tmp].get(t)]=1;
					}
				}
			}
		}
		System.out.println("YES");
	}
}
