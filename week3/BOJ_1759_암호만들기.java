package week3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1759_암호만들기 {
	static char[] ch;
	static int L; 
	static int C; 
	static  BufferedWriter bw;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        ch = new char[C];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<C;i++) {
        	ch[i] = st.nextToken().charAt(0);
        }
        
        Arrays.sort(ch);
        
        makeCombination(0,new char[L], 0);
        bw.flush();
		bw.close();
		br.close();
	}
	private static void makeCombination(int toSelect , char[] selected, int startIdx) throws IOException {
		if(toSelect == L) {
			int cnt1 =0;
			int cnt2 =0;
			StringBuilder sb = new StringBuilder();
			for(char i : selected) {
				if(i=='a'||i=='e'||i=='i'||i=='o'||i=='u')
					cnt1++;
				else cnt2++;
				sb.append(i);
			}
			if(cnt1>=1&&cnt2>=2)
				bw.write(sb+"\n");

			return;
		}
		for(int i=startIdx;i<C;i++) {
			selected[toSelect]=ch[i];
			makeCombination(toSelect+1, selected, i+1);
		}
	}
}
