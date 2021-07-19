package 문자열;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;

public class BOJ_1501_영어읽기 {
	static HashMap<String, Integer> arr = new HashMap<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = stoi(br.readLine());
		String str;
		for(int n=0;n<N;n++) {
			String tmp = br.readLine();
			if(tmp.length()>2) {
				str = toS(tmp);
			}else {
				str=tmp;
			}
			arr.put(str, arr.getOrDefault(str, 0)+1);
		}
		//단어가 아니라 문장으로 들어옴!!!!
		//없다면 0처리해줌.
		int M = stoi(br.readLine());
		for(int m=0;m<M;m++) {
			String[] dic = br.readLine().split(" ");
			int result =1;
			for(int i=0;i<dic.length;i++) {
				String tmp = dic[i];
				if(tmp.length()>2) {
					str = toS(tmp);
				}else {
					str=tmp;
				}
				int num = (arr.get(str)==null?0:arr.get(str));
				result *=num;
				//if(result==0) break;//오히려 많이 나옴.
			}
			bw.write(result+"\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}

	private static String toS(String tmp) {
		char[] ch = new char[tmp.length()];
		ch[0]=tmp.charAt(0);
		ch[1]=tmp.charAt(tmp.length()-1);
		int i=2;
		char[] sub = tmp.substring(1, tmp.length()-1).toCharArray();
		Arrays.sort(sub);
		for(char a: sub) {
			ch[i++]=a;
		}
		return new String(ch);
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}
