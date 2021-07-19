package 문자열;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Stack;

public class BOJ_9935_문자열폭발 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		String bomb = br.readLine();
		
		Stack<Character> stack = new Stack<>();
		Stack<Character> tmpS = new Stack<>();//검사를 위한 스택
		
		ArrayList<Character> bombArr = new ArrayList<Character>();
		for (int i = 0; i < bomb.length(); i++) {
			bombArr.add(bomb.charAt(bomb.length() - i - 1));
		}//뒤에서부터 검사
		//거꾸로 넣어서 뺄때 정방향으로 나오게 하는 것이 포인트.
		for(int i=0;i<input.length();i++) {
			char tmp = input.charAt(i);
			stack.push(tmp);
			
			if(tmp==bombArr.get(0)&& stack.size() >= bomb.length()) {
				tmpS.clear();
				for(char b :bombArr) {
					char c = stack.pop();
					tmpS.push(c);
					
					if(b!=c) {
						//원상복구
						while(!tmpS.isEmpty())
							stack.push(tmpS.pop());
						break;
					}
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		while(!stack.isEmpty())
			sb.append(stack.pop());
		
		String result = sb.reverse().toString();
		System.out.println((result.length()==0)?"FRULA":result);
	}
}
