package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_5430_AC_Deque {
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		loop:for(int t=0;t<T;t++) {
			String cm = br.readLine();
			char[] command = cm.toCharArray();
			int N = Integer.parseInt(br.readLine());
			/*String tmp = br.readLine();
			tmp = tmp.substring(1,tmp.length()-1);
			String[] str = tmp.split(",");*/
			StringTokenizer st = new StringTokenizer(br.readLine().replace("[", "").replace("]", ""),",");
			if(N==0) {
				if(cm.contains("D"))
					sb.append("error"+'\n');
				else
					sb.append("[]"+'\n');
				continue;
			}
			Deque<String> arr = new ArrayDeque<>();
			
			for(int i=0;i<N;i++)
				arr.add(st.nextToken());
			
			boolean reverse = false;//정방향
			for(int i=0;i<command.length;i++) {
				
				if(command[i]=='R') {
					reverse = !reverse;
				}else if(command[i]=='D') {
					if(arr.isEmpty()) {
						sb.append("error"+'\n');
						continue loop;
					}else {
						if(reverse)
							arr.pollLast();
						else
							arr.poll();
					}
				}
			}
			
			StringBuilder r = new StringBuilder();
			r.append("[");
			int num = arr.size();
			if(reverse) {
				for(int j=0;j<num;j++) {
					r.append(arr.pollLast());
					if(j!=num-1) r.append(",");
				}
			}else {
				for(int j=0;j<num;j++) {
					r.append(arr.poll());
					if(j!=num-1) r.append(",");
				}
			}	
			r.append("]");
			sb.append(r+"\n");

		}
		System.out.println(sb.toString());
	}

}
