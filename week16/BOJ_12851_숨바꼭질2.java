package week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_12851_숨바꼭질2 {
	static int[] arr = new int[100001];
	static int K, min, cnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		if(N==K) {
			System.out.println("0\n1");
			return;
		}else {
			move(N);
			System.out.println(min+"\n"+cnt);
		}
	}
	private static void move(int n) {
		Queue<Integer> q = new LinkedList<>();
		q.add(n);
		arr[n] = 0;
		
		min = Integer.MAX_VALUE;
		cnt =0;
		
		while(!q.isEmpty()) {
			int tmp = q.poll();
			
			//방문시간이 최소 시간보다 크면 리턴
			if(min<arr[tmp]) return;
			
			for(int i=0;i<3;i++) {
				int next;
				if(i==0) next = tmp+1;
				else if(i==1) next = tmp-1;
				else next = tmp*2;
			
				//첫 방문이거나
				//이미 방문한 곳이어도 같은 시간에 방문한다면
				//경우의 수 추가될 수 있기 때문에 큐에 한 번 더 넣어줌.
				if(next>=0 && next<100001 && (arr[next]==0||arr[next]==arr[tmp]+1)) {
					q.add(next);
					arr[next]=arr[tmp]+1;//1초 움직임
					if(next==K) {
						min = arr[K];
						cnt++;
					}
				}
			}
		}
	}
}

