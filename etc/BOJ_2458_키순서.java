package floyd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//������ �����ؼ� ¥�Ⱑ ��ƴ�.
public class BOJ_2458_키순서 {
	static boolean height[][];
	static int N,M;
	static final int INF = 1000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N= stoi(st.nextToken());
		M= stoi(st.nextToken());
		height = new boolean[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(height[i], false);
		}
		
		int a,b;
		for(int m=0;m<M;m++) {
			st = new StringTokenizer(br.readLine());
			a = stoi(st.nextToken());
			b = stoi(st.nextToken());
			height[a][b] = true;
		}
		floyd(height);
		//print(height);
		int cnt=0;
		for(int l=1;l<=N;l++) {
			boolean p = true;
			for(int r=1;r<=N;r++) {
				if(l==r) continue;
				if(height[l][r]|height[r][l])continue;
				p=false;
			}
			if(p) cnt++;
		}
		
		System.out.println(cnt);
	}
	private static void print(boolean[][] arr) {
		for(int l=1;l<=N;l++) {
			for(int r=1;r<=N;r++) {
				System.out.print(arr[l][r]+" ");
			}
			System.out.println();
		}
	}
	
	private static void floyd(boolean[][] arr) {
		//������
		for(int m=1;m<=N;m++) {
			for(int l=1;l<=N;l++) {
				for(int r=1;r<=N;r++) {
					if(arr[l][m]&&arr[m][r])
						arr[l][r]=true;
				}
			}
		}
	}
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
//윤정이ㅣ꺼ㅓ