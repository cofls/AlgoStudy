package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_15903_카드합체놀이 {
	public static void main(String[] args) throws IOException {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());	
		PriorityQueue<Long> pq = new PriorityQueue<>();
		for(int i=0; i<n;i++) {
			pq.add(Long.parseLong(st.nextToken()));
		}
		for(int j=0; j<m;j++) {
			long a = pq.poll();
			long b = pq.poll();
			pq.add(a+b);
			pq.add(a+b);
		}
		long sum = 0;
		while(!pq.isEmpty()) {
			sum+=pq.poll();
		}
		System.out.println(sum);
	}
}
