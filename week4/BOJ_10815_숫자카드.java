package week4;

import java.io.*;
import java.util.*;

public class BOJ_10815_숫자카드 {
	static ArrayList<Integer> card;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		card = new ArrayList<>();
		for(int n=0; n<N;n++) {
			card.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(card);
		
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int[] num = new int[M];
		for(int m=0; m<M;m++) {
			num[m] = Integer.parseInt(st.nextToken());
			num[m]=check(num[m]);
		}
		
		for(int r : num)
			System.out.print(r+" ");
		
	}
	private static int check(int i) {
		int low = 0;
		int high = card.size()-1;
		int mid;
		
		while(low<=high) {
			mid = (low+high)/2;
			if(card.get(mid)==i) {
				return 1;
			}else if(i<card.get(mid))
				high = mid-1;
			else
				low = mid+1;
		}
		return 0;
	}
}
