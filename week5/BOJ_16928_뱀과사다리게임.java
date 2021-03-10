package week5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16928_뱀과사다리게임 {
	static int N,M;
	static HashMap<Integer, Integer> bridge = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for(int i=0;i<N+M;i++) {
			 st = new StringTokenizer(br.readLine());
			 int a = Integer.parseInt(st.nextToken());
			 int b = Integer.parseInt(st.nextToken());
			 bridge.put(a, b);
		}
		
		bw.write(String.valueOf(bfs()));
		bw.flush();
		bw.close();
		br.close();
		
	}

	private static int bfs() {
		int cnt =0;
		boolean[] visited = new boolean[101];
		Queue<Integer> q = new LinkedList<>();
		
		visited[1]=true;
		q.add(1);
		
		while(!q.isEmpty()) {
			int n = q.size();
			for(int i=0;i<n;i++) {
				int cur = q.poll();
				
				if(cur==100) return cnt;
				
				for(int d=1;d<=6;d++) {
					int next = cur+d;
					if(next>100) break;
					if(visited[next]) continue;
					
					if(bridge.containsKey(next)) {
						next = bridge.get(next);
						if(visited[next]) continue;
					}
					
					q.add(next);
					visited[next]=true;
				}
			}
			cnt++;
		}
		return cnt;
	}
}
