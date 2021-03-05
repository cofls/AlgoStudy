package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_17827_달팽이리스트 {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); //노드의 개수
		int M = Integer.parseInt(st.nextToken()); //질문의 횟수
		int V= Integer.parseInt(st.nextToken()); //N번 노드가 가리키는 번호
		StringBuilder sb = new StringBuilder();
		
		int[] snail = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N;i++) {
			snail[i]= Integer.parseInt(st.nextToken());
		}
		
		int index = 0;
		int q = 0;

		for(int i=0; i<M;i++) {
			q=Integer.parseInt(br.readLine());
			
			if(q<N) index = q; //사이클 생성 전까지
			else {//사이클
				if(N==V) index = N-1; //민달팽이
				else {
					index = q %(N-V+1);
					if(index<V-1) index+=(N-V+1);
				}
			}
			sb.append(snail[index]+"\n");
		}
		System.out.println(sb);
		
	}

}
