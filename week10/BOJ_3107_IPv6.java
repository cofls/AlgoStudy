package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//부분교체는 stringbuilder만 된다.
public class BOJ_3107_IPv6 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		int cnt =0;
		for(int i=0;i<str.length();i++) {
			if(str.charAt(i)==':') cnt++;
		}
		int N = cnt+1;
		String[] tmp = str.split(":");//3::은 배열 1만 만들어낸다....
		
		StringBuilder sb = new StringBuilder();
		
		if(N==8) {
			for(int i=0;i<N;i++) {
				StringBuilder zero = new StringBuilder("0000");
				if(tmp[i].length()!=4) {
					zero.replace(4-tmp[i].length(), 4, tmp[i]);
					sb.append(zero);
				}else {
					sb.append(tmp[i]);
				}
				if(i!=N-1) sb.append(":");
			}
		}else {
			if(str.equals("::")) {
				sb.append("0000:0000:0000:0000:0000:0000:0000:0000");
				System.out.println(sb.toString());
				return;
			}
			for(int i=0;i<N;i++) {
				StringBuilder zero = new StringBuilder("0000");
				if(tmp[0].length()==0&&tmp[1].length()==0) {//앞부분축약
					if(i==0) {
						for(int n=0;n<10-N;n++) {
							sb.append(zero+":");
						}
					}
					if(tmp[i].length()!=0) {
						zero.replace(4-tmp[i].length(), 4, tmp[i]);
						sb.append(zero);
					}
				}else if(N!=tmp.length) {//마지막부분축약
					zero.replace(4-tmp[i].length(), 4, tmp[i]);
					sb.append(zero+":");
					if(N-3==i) {//3:: 같은 배열은 길이가 1밖에 안돼서 내용있는 앞부분은 다 해주고, 나머지 뒷부분은 0으로 다 처리
						for(int t=0;t<7-i;t++) {
							sb.append("0000");
							if(t!=6-i) sb.append(":");
						}
						break;
					}
				}else if(i>=1&&tmp[i].length()==0) {//중간에 축약, 축약된 0 부분 처리
					for(int n=0;n<9-N;n++) {
						sb.append(zero+":");
					}
				}else{//중간에 축약되어 멀쩡한 부분 처리
					zero.replace(4-tmp[i].length(), 4, tmp[i]);
					sb.append(zero);
					if(i!=N-1) sb.append(":");
				}
			}
		}
		System.out.println(sb.toString());
	}
}
