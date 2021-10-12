package floyd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1956_운동{
	static int V,E;
	static int[][] town;
	static final int INF = 10001;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V= stoi(st.nextToken());
		E= stoi(st.nextToken());
		
		town = new int[V+1][V+1];
		
		
		for (int i = 1; i <= V; i++) {
			Arrays.fill(town[i], INF);
		}
		
		/*for(int v=1;v<=V;v++) {
			for(int w=1;w<=V;w++) {
				if(v==w) continue;
				town[v][w]=INF;
			}
		}*/
		int a,b,c;
		for(int e=0;e<E;e++) {
			st = new StringTokenizer(br.readLine());
			a = stoi(st.nextToken());
			b = stoi(st.nextToken());
			c = stoi(st.nextToken());
			town[a][b]=c;
		}
		
		floyd();
		
		int sum=2*INF;
		for(int l=1;l<=V;l++) {
			for(int r=1;r<=V;r++) {
				if(l==r) continue;
				
				//���ο��� ���� ���� ������, ����Ŭ�� �����Ѵٴ� ��.
				if(town[l][r] !=INF&&town[r][l]!=INF)
					sum= Math.min(sum, town[l][r]+town[r][l]);
			}
		}
		sum = (sum==2*INF)?-1:sum;
		
		System.out.println(sum);
		//print();
		
	}
	/*private static void print() {
		for(int l=1;l<=V;l++) {
			for(int r=1;r<=V;r++) {
				System.out.print(town[l][r]+" ");
			}
			System.out.println();
		}
	}*/
	private static void floyd() {
		//������
		for(int m=1;m<=V;m++) {
			for(int l=1;l<=V;l++) {
				for(int r=1;r<=V;r++) {
					if(town[l][r]>town[l][m]+town[m][r])
						town[l][r]=town[l][m]+town[m][r];
				}
			}
		}
	}
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
