package week5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_1783_병든나이트 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		bw.write(String.valueOf(calc(N,M)));
		bw.flush();
		bw.close();
		br.close();
	}

	private static int calc(int N, int M) {
		if(N==1) return 1;
		if(N==2) return Math.min(4,(M+1)/2);
		if(M<7) return Math.min(4, M);
		return M-2;
	}
}
