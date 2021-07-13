package week18_dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge>{
	
	int ver, cnt;
	public Edge(int ver, int cnt) {
		this.ver = ver;
		this.cnt = cnt;
	}
	@Override
	public int compareTo(Edge o) {
		return this.cnt- o.cnt;
	}
	
}
public class BOJ_10282_해킹 {
	static int n, d, c;
	static int a, b, s;
	static ArrayList<Edge>[] com;
	static int[] distance;
	static int cnt = 0;//총 감염되는 컴퓨터 수
	static int time = 0;//마지막 컴퓨터가 감염되기까지 걸리는 시간
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = stoi(br.readLine());
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		for(int t=0;t<T;t++) {
			st = new StringTokenizer(br.readLine());
			n = stoi(st.nextToken());
			d = stoi(st.nextToken());
			c = stoi(st.nextToken());
			
			com = new ArrayList[n+1];
			distance = new int[n+1];
			Arrays.fill(distance, Integer.MAX_VALUE);
			
			for(int i=0;i<=n;i++) {
				com[i]= new ArrayList<>();
			}
			
			for(int i=0;i<d;i++) {
				st = new StringTokenizer(br.readLine());
				a = stoi(st.nextToken());
				b = stoi(st.nextToken());	
				s = stoi(st.nextToken());
				com[b].add(new Edge(a, s));//컴퓨터 b가 감염되면 s초 후에 a가 감염된다.
			}
			distance[c]= 0; //시작
			dijkstra();
			
			cnt =0;
			time =0;
			
			//결과
			for (int i = 1; i <= n; i++) {
                if (distance[i] != Integer.MAX_VALUE) {
                    cnt++;
                    time = Math.max(time, distance[i]);
                }
            }
			sb.append(cnt + " " + time+"\n");
		}
		System.out.println(sb.toString());
		
	}

	private static void dijkstra() {
		PriorityQueue<Edge> queue = new PriorityQueue<>();
      
		queue.add(new Edge(c, 0));
        while (!queue.isEmpty()) {
            Edge edge = queue.poll();
           
            int vertex = edge.ver;
            int cost = edge.cnt;
          
            if (distance[vertex] < cost) {
                continue;
            }
            //경유해서
            for (int i = 0; i < com[vertex].size(); i++) {
                int vertex2 = com[vertex].get(i).ver;
                int cost2 = com[vertex].get(i).cnt + cost;
                //작은값이라면 갱신
                if (distance[vertex2] > cost2) {
                    distance[vertex2] = cost2;
                    queue.add(new Edge(vertex2, cost2));
                }
            }
        }
	}

	private static int stoi(String s){
		return Integer.parseInt(s);
	}
}
