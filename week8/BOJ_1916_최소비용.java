package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1916_최소비용 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		int[][] adjMatrix = new int[N+1][N+1];// 인접행렬
		//미방문처리
		for(int i=1; i<N+1; i++) {
			Arrays.fill(adjMatrix[i], -1);
		}
		
		StringTokenizer st;
		for(int m=0;m<M;m++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			//두 정점 사이에 중복엣지 처리, 
			if(adjMatrix[from][to] == -1 || adjMatrix[from][to] > weight)
				adjMatrix[from][to] = weight; 
		}
		st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		int[] distance = new int[N+1];
		boolean[] visited = new boolean[N+1];
	
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[A] = 0; //시작정점에서 자신으로 오는 직접비용 > 기본값으로 두고 시작
		
		for (int i = 1; i <= N; i++) {
			int min = Integer.MAX_VALUE;
			int current = A; // min 최소비용에 해당하는 정점 번호 
			// step1. : 처리하지 않은정점중에 출발지에서 가장 가까운(최소비용) 정점 선택
			for (int j = 1; j <= N; j++) {
				if(!visited[j] &&  min>distance[j]) {
					min = distance[j];
					current = j;
				}
			}
			visited[current] = true;
			if(current == B) break; //최종 목적지이면 끝
			
			// step2. 선택된 current를 경유지로 하여 아직 처리하지 않은 다른 정점으로의 최소비용 따져본다.
			for (int j = 1; j <= N; j++) {
				if(!visited[j] && adjMatrix[current][j] != -1 && distance[j]> min + adjMatrix[current][j]) {
					distance[j] = min + adjMatrix[current][j];
				}
			}
		}
		System.out.println(distance[B]);
	}
}
