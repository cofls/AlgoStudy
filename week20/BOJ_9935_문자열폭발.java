package ���ڿ�;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Stack;

public class BOJ_9935_���ڿ����� {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		String bomb = br.readLine();
		
		Stack<Character> stack = new Stack<>();
		Stack<Character> tmpS = new Stack<>();//�˻縦 ���� ����
		
		ArrayList<Character> bombArr = new ArrayList<Character>();
		for (int i = 0; i < bomb.length(); i++) {
			bombArr.add(bomb.charAt(bomb.length() - i - 1));
		}//�ڿ������� �˻�
		//�Ųٷ� �־ ���� ���������� ������ �ϴ� ���� ����Ʈ.
		for(int i=0;i<input.length();i++) {
			char tmp = input.charAt(i);
			stack.push(tmp);
			
			if(tmp==bombArr.get(0)&& stack.size() >= bomb.length()) {
				tmpS.clear();
				for(char b :bombArr) {
					char c = stack.pop();
					tmpS.push(c);
					
					if(b!=c) {
						//���󺹱�
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
