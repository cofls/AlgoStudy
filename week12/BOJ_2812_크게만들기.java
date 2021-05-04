package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_2812_크게만들기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	       
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		String num = br.readLine();
		int[] arr = new int[N];
		Stack<Integer> stack = new Stack<>();
		
		int cnt =0;
		int n =0;
		for(n=0;n<N;n++) {
			int tmp = num.charAt(n)-'0';
			
			while(cnt<K && !stack.isEmpty()&& stack.peek()<tmp) {
				//top에 있는 것 지우기
				stack.pop();
				cnt++;
			}
			stack.push(tmp);
			if(cnt==K) break;
		}
		StringBuilder sb = new StringBuilder();
		
		if(n==N) {
			while(!stack.isEmpty()) {
				sb.append(stack.pop());
			}
			sb.reverse().setLength(N-K);
		}else {
			while(!stack.isEmpty()) {
				sb.append(stack.pop());
			}
			sb.reverse().append(num.substring(n+1));
		}
		
		System.out.println(sb.toString());
	}
}
