package floyd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1719_택배 {
	static int n,m;
	static int[][] station;
	static int[][] result;
	static final int INF = 200001;
	static StringBuilder sb= new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n= stoi(st.nextToken());
		m= stoi(st.nextToken());
		station = new int[n+1][n+1];
		result = new int[n+1][n+1];
		for(int[] a: station)
			Arrays.fill(a, INF);
		
		int a, b, c;
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			a = stoi(st.nextToken());
			b = stoi(st.nextToken());
			c = stoi(st.nextToken());
			station[a][b] = c;
			station[b][a] = c;
			result[a][b] = b;
			result[b][a] = a;
		}
		floyd();
		print();
		System.out.println(sb.toString());
	}
	private static void print() {
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				if(i==j||result[i][j]==0) {
					sb.append("- ");
					continue;
				}else {
					//�Ž��� �ö󰡸鼭 ���� ���� ��ġ�� �������� ã�ƾ� �Ѵ�.
					//ans[1][6] = 5 -> ans[1][5] = 2 -> ans[1][2] = 2 �� �Ǿ�, ���� ans[1][6]�� 2�� �ȴ�.
					/*int t = j;
					while(result[i][t] !=t) {
						t=result[i][t];
					}
					sb.append(result[i][t]+" ");*/
					sb.append(result[i][j]+" ");
				}
			}
			sb.append("\n");
		}
	}
	private static void floyd() {
		for(int m=1;m<=n;m++) {
			for(int l=1;l<=n;l++) {
				for(int r=1;r<=n;r++) {
					if(station[l][r]>station[l][m]+station[m][r]) {
						station[l][r]=station[l][m]+station[m][r];
						//���⼭ ������Ʈ ���ָ� �ǳ�??
						//�ٵ� ���� ���� ��ġ�� �������� ã�ƾ� �Ѵ�.
						//1-->6�� ��� 2�� �������� ����X, 5�� ������ ������ ������ ����.
						//result[l][r]=m;
						result[l][r]=result[l][m];
					}
				}
			}
		}
	}
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
