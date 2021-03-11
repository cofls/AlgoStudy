package week2;

import java.io.*;
import java.util.*;

class balloon{
	int idx;
	int move;
	public balloon(int idx, int move) {
		this.idx = idx;
		this.move = move;
	}
}
public class BOJ_2346_풍선터뜨리기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		LinkedList<balloon> b = new LinkedList<>();
		
		for(int i=0; i<N;i++) {
			b.add(new balloon((i+1),Integer.parseInt(st.nextToken())));
		}

		int go = 0;
		int next = 0;
		for(int i=0; i<N-1;i++) {//길이-1만큼만 돈다.
			if(go>0) {//오른쪽으로
				for(int g=0;g<go-1;g++) {
					++next;
					if(next>=b.size()) next=0;
				}
			}else {//왼쪽으로
				go*=-1;
				for(int g=0;g<go;g++) {
					--next;
					if(next<0) next=b.size()-1;
				}
			}

			sb.append(b.get(next).idx + " ");
			go = b.get(next).move;
			b.remove(next);
            if(next==b.size()) next=0;
		}
		sb.append(b.get(0).idx);
		
		System.out.println(sb.toString());
	}
}
