package floyd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

//INF�� ���� ���� ����ħ..... double�� ����
public class BOJ_13168_내일로{
	static int N,R;
	static final int INF = 10000001;
	static HashMap<String,Integer> city = new HashMap<>();
	static double[][] map;
	static double[][] mapUse;
	
	static ArrayList<String> travel = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N= stoi(st.nextToken());
		R= stoi(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int cnt =1;
		for(int n=0;n<N;n++) {
			String tmp = st.nextToken().toLowerCase();
			if(city.containsKey(tmp)) continue;
			city.put(tmp,cnt++);
		}
		
		N = city.size();
		map = new double[N+1][N+1];
		mapUse = new double[N+1][N+1];
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				if(i==j) continue;
				map[i][j] =INF;
				mapUse[i][j] =INF;
			}
		}
		
		int M = stoi(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int n=0;n<M;n++) {
			travel.add(st.nextToken().toLowerCase());
		}

		int K = stoi(br.readLine());
		for(int k=0;k<K;k++) {
			st = new StringTokenizer(br.readLine());
			String vehicle = st.nextToken();
			String from = st.nextToken().toLowerCase();
			String to = st.nextToken().toLowerCase();
			int money = stoi(st.nextToken());
			map[city.get(from)][city.get(to)] = Math.min(money,map[city.get(from)][city.get(to)] );
			map[city.get(to)][city.get(from)] = Math.min(money,map[city.get(to)][city.get(from)] );
			if(vehicle.equals("Mugunghwa")||vehicle.equals("ITX-Saemaeul")||vehicle.equals("ITX-Cheongchun")) {
				mapUse[city.get(from)][city.get(to)] = 0;
				mapUse[city.get(to)][city.get(from)] = 0;
			}else if(vehicle.equals("S-Train")||vehicle.equals("V-Train")) {
				mapUse[city.get(from)][city.get(to)] = Math.min(money/2.0,mapUse[city.get(from)][city.get(to)] );
				mapUse[city.get(to)][city.get(from)] = Math.min(money/2.0,mapUse[city.get(to)][city.get(from)] );
			}else {
				mapUse[city.get(from)][city.get(to)] = Math.min(money,mapUse[city.get(from)][city.get(to)] );
				mapUse[city.get(to)][city.get(from)] = Math.min(money,mapUse[city.get(to)][city.get(from)] );
			}
		}
		floyd(map);
		floyd(mapUse);
	
		double sum1=0;
		double sum2=0;
		for(int n=0;n<M-1;n++) {
			sum1+=map[city.get(travel.get(n))][city.get(travel.get(n+1))];
			sum2+=mapUse[city.get(travel.get(n))][city.get(travel.get(n+1))];
		}
		if(sum1>sum2+R) System.out.println("Yes");
		else System.out.println("No");
	
	}
	private static void floyd(double[][] arr) {
		//������
		for(int m=1;m<=N;m++) {
			for(int l=1;l<=N;l++) {
				for(int r=1;r<=N;r++) {
					if(arr[l][r]>arr[l][m]+arr[m][r])
						arr[l][r]=arr[l][m]+arr[m][r];
				}
			}
		}
	}
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}