package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ_1715_카드정렬하기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int n=0;n<N;n++) {
			pq.add(Integer.parseInt(br.readLine()));
		}
		
		long num = 0;//int도 상관없음.
		while(pq.size()>1) {
			int tmp1 = pq.poll();
			int tmp2 = pq.poll();
			
			num+=(tmp1+tmp2);
			pq.add(tmp1+tmp2);
		}
		System.out.println(num);
		
	}
}
