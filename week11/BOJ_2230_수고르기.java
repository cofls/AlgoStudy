package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2230_수고르기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		for(int n=0;n<N;n++) {
			arr[n] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);
		
		int l =0, r=0, result = Integer.MAX_VALUE;
		
		while(r<N) {
			if(arr[r]-arr[l]<M) {
				r++;
				continue;
			}
			if(arr[r]-arr[l]==M) {
				result = M;
				break;
			}
			result =Math.min(result, arr[r]-arr[l]);
			l++;
		}
		if(result == Integer.MAX_VALUE) {
			System.out.println(0);
		}else {
			System.out.println(result);
		}
	}
}
