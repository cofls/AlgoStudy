package week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_3079_입국심사 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		for(int n=0;n<N;n++) {
			arr[n] = Integer.parseInt(br.readLine());
		}
		System.out.println(solution(M,arr));		
	}
	private static long solution(int n, int[] times) {
	       // Arrays.sort(times);
	        long left = 1;
	        long right = (long) n*times[times.length-1];
	        long mid =0;
	        
	        while(left<=right){
	            long done =0;
	            mid = (left+right)/2;
	            
	            for(int time :times){
	            	done += mid/time;
	            }
	            //n명이하 끝났다고 하면 늘리기 위해
	            if(done<n){
	                left = mid +1;
	            }
	            else{
	            	//n명이상으로 돼서 줄여야 하면 
	                right = mid-1;
	            }
	        }
	        return left;
	    }
}