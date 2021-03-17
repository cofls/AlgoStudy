package week6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_17281_BaseBall {
	static int pNum = 10;            
	static int[] order= new int[pNum];
	static boolean[] visited = new boolean[pNum];
	static int[][] info;
	static int N;
	static int result=0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		info = new int[N][10];
		StringTokenizer st;
		for(int n=0; n<N;n++) {
			st = new StringTokenizer(br.readLine());
			for(int i=1;i<10;i++) {
				info[n][i]=Integer.parseInt(st.nextToken());
			}
		}
		play();
		bw.write(String.valueOf(result));
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	private static void play() {
		visited[4] = true;
		order[4]=1;
		makePermutation(2);
		
	}

	private static void makePermutation(int toSelect) {
		if(toSelect == pNum) {
			//count();
			result = Math.max(result, count());
			return;
		}
		for(int i=1; i<pNum; i++) {
			if(!visited[i]) {
				visited[i]=true;
				order[i]= toSelect;
				makePermutation(toSelect + 1);
				visited[i]=false;
			}
		}
	}

	private static int count() {
		int idx =1;
		int out =0;
		int score =0;
		boolean[] player = new boolean[4];
		int Num=0;
		
		for(int n=0;n<N;n++) {
			out = 0;
			Num = 0;
			player = new boolean[4];
			while(out!=3) {
				int tmp = info[n][order[idx++]];
				//순서 순환
				if(idx==10) idx=1;
				//아웃이면
				if(tmp==0) {
					out++;
					continue;
				}
				if(tmp==1) {
					if(Num==0) {
						Num++;//나가있는 사람 수
						player[tmp] = true;
					}else {
						if(player[3]) {
							player[3]=false;
							score++;
						}
						if(player[2]) {
							player[2]=false;
							player[3]=true;
						}
						if(player[1]) {
							player[1]=false;
							player[2]=true;
						}
						player[1]=true;//친사람 진루
						Num=1;
					}
				}
				if(tmp==2) {
					if(Num==0) {
						Num++;//나가있는 사람 수
						player[tmp] = true;
					}else {
						if(player[3]) {
							player[3]=false;
							score++;
						}
						if(player[2]) {
							player[2]=false;
							score++;
						}
						if(player[1]) {
							player[1]=false;
							player[3]=true;
						}
						player[2]=true;//친사람 진루
						Num=1;
					}
				}
				if(tmp==3) {
					if(Num==0) {
						Num++;//나가있는 사람 수
						player[tmp] = true;
					}else {
						for(int t=1;t<=3;t++) {
							if(player[t]) {
								player[t]=false;
								score++;
								//무조건 나갈 숫자 되어서 그냥 점수++
							}
						}
						player[3]=true;//친사람 진루
						Num=1;
					}
				}
				if(tmp==4) {
					score++;
					for(int t=1;t<=3;t++) {
						if(player[t]) {
							player[t]=false;
							score++;
							//무조건 나갈 숫자 되어서 그냥 점수++
						}
					}
					Num=0;//모두 들어왔으니까 리셋
				}	
			}
		}
		return score;
	}
}
