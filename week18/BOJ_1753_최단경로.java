package week18_dijkstra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//우선순위큐로 변환 혹은, 인접리스트로 변환
public class BOJ_1753_최단경로 {
	static int V,E,K;
	static ArrayList<Edge>[] road;
	static int[] distance;
	static class Edge implements Comparable<Edge>{
		
		int end, weight;
		public Edge(int end, int weight) {
			this.end = end;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return this.weight- o.weight;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = stoi(st.nextToken());
		E = stoi(st.nextToken());
		
		//시작점
		K = stoi(br.readLine());
		
		road = new ArrayList[V+1];
		distance = new int[V+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		for(int i=0;i<=V;i++) {
			road[i]= new ArrayList<>();
		}
		
		for(int e=0;e<E;e++) {
			st = new StringTokenizer(br.readLine());
			int f =  stoi(st.nextToken());
			int t =  stoi(st.nextToken());
			int v =  stoi(st.nextToken());
			//if(road[f].)
			road[f].add(new Edge(t,v));
		}
		
		distance[K] = 0;
		dijkstra();
		
		
		for(int i=1;i<=V;i++) {
			bw.write(distance[i]==Integer.MAX_VALUE?"INF\n":distance[i]+"\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
	private static void dijkstra() {
		PriorityQueue<Edge> queue = new PriorityQueue<>();
      
		boolean[] visited = new boolean[V+1];
		queue.add(new Edge(K, 0));
		
        while (!queue.isEmpty()) {
            Edge curNode = queue.poll();
           
            int cur = curNode.end;
            
            if(visited[cur]==true) continue;
            visited[cur] = true;
            
            for(Edge e : road[cur]) {
            	int w = distance[cur]+e.weight;
            	if(distance[e.end]>w) {
            		distance[e.end]=w;
            		queue.add(new Edge(e.end, w));
            	}
            }
          
        }
	}

	
}
