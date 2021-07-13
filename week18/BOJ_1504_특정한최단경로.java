package week18_dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1504_특정한최단경로 {
	static class dot implements Comparable<dot>{
		int con, w;
		public dot(int con, int w) {
			this.con = con;
			this.w = w;
		}
		 @Override
		    public int compareTo(dot o) {
		        return w - o.w;
		    }
		 
	}
	static int N,E;
	static int v1,v2;
	static ArrayList<dot>[] arr;
	static int[] distance;
	static boolean[] visited;
	static final int INF = 200000000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = stoi(st.nextToken());
		E = stoi(st.nextToken());
		
		arr = new ArrayList[N+1];
		for(int n=0;n<=N;n++)
			arr[n] = new ArrayList<>();
		
		int a,b,c;
		for(int e =0;e<E;e++) {
			st = new StringTokenizer(br.readLine());
			a=stoi(st.nextToken());
			b=stoi(st.nextToken());
			c=stoi(st.nextToken());
			arr[a].add(new dot(b,c));
			arr[b].add(new dot(a,c));
		}
		
		st = new StringTokenizer(br.readLine());
		v1 = stoi(st.nextToken());
		v2 = stoi(st.nextToken());
		
		distance = new int[N+1];
		visited = new boolean[N+1];
		
		 int res1 = 0;
	        res1 += dijkstra(1, v1);
	        res1 += dijkstra(v1, v2);
	        res1 += dijkstra(v2, N);
		 int res2 = 0;
	        res2 += dijkstra(1, v2);
	        res2 += dijkstra(v2, v1);
	        res2 += dijkstra(v1, N);
	 
	     int ans = (res1 >= INF && res2 >= INF) ? -1 : Math.min(res1, res2);
		
		System.out.println(ans);
	}
	public static int dijkstra(int start, int end) {
        Arrays.fill(distance, INF);
        Arrays.fill(visited, false);
 
        PriorityQueue<dot> pq = new PriorityQueue<>();
        //시작
        pq.offer(new dot(start, 0));
        distance[start] = 0;
 
        while (!pq.isEmpty()) {
            dot curNode = pq.poll();
            int cur = curNode.con;
 
            if (!visited[cur]) {
                visited[cur] = true;
                //연결된거 검사 
                for (dot dot : arr[cur]) {
                    if (!visited[dot.con] && distance[dot.con] > distance[cur] + dot.w) {
                        distance[dot.con] = distance[cur] + dot.w;
                        pq.add(new dot(dot.con, distance[dot.con]));
                    }
                }
                
//                for(int i=0;i<arr[cur].size();i++) {
//                	if(!visited[arr[cur].get(i).con]&& distance[arr[cur].get(i).con]>distance[arr[cur].get(i).con]+arr[cur].get(i).w) {
//                		 distance[arr[cur].get(i).con] =  distance[cur]+arr[cur].get(i).w;
//                		 pq.add(new dot(arr[cur].get(i).con,distance[arr[cur].get(i).con]));
//                	}
//                }
            }
        }
 
        return distance[end];
    }
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
