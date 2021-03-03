package week4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_9934 {
	static int[] arr;
	static StringBuilder sb = new StringBuilder();
	static ArrayList<Integer>[] tree;
	static int K;
	public static void main(String[] args) throws NumberFormatException, IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		K = Integer.parseInt(br.readLine());
		tree= new ArrayList[K+1];
		for(int k =0; k<K+1;k++) {
			tree[k]=new ArrayList<Integer>();
		}
		int n = (int)Math.pow(2, K)-1;
		arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<n;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
			
		makeTree(0,n,1);
		
		for(int d=1; d<=K;d++) {
			for(int i=0;i<tree[d].size();i++) {
				sb.append(tree[d].get(i)+" ");
			}
			sb.append("\n");
		}
		bw.write(sb.toString());
		
		bw.flush();
		bw.close();
		br.close();
	}
	private static void makeTree(int l,int r,int depth) {
		if(depth>K) return;
		
		tree[depth].add(arr[(l+r)/2]);
		makeTree(l,(l+r)/2-1,depth+1);
		makeTree((l+r)/2+1,r,depth+1);
		
	}
}
