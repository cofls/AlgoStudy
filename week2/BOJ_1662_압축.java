package week2;

import java.util.*;
import java.io.*;

public class BOJ_1662_압축 {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		
		Stack<String> stack = new Stack<>();
		String out;
		int num =0;
		for(int i=0; i<input.length();i++) {
			String ch = String.valueOf(input.charAt(i));
			int len;
			if(ch.equals(")")) {
				len = 0;
				out = stack.pop();
				while(!out.equals("(")) {//"("만날 때까지 
					if(out.charAt(0)=='n') {
						num = Integer.parseInt(out.substring(1, out.length()));
						len+=num;
					}else
						len++;
					out = stack.pop();
				}
				int K = Integer.parseInt(stack.pop());//"("앞의 숫자 K 		
				len *=K;
				stack.push("n"+len);
			}
			else {
				stack.push(ch);
			}
		}
		int result=0;
		while(!stack.isEmpty()) {
			out = stack.pop();
			num=0;
			if(out.charAt(0)=='n')
				num = Integer.parseInt(out.substring(1, out.length()));
			else num++;
			result +=num;
		}
		System.out.println(result);

	}
}
