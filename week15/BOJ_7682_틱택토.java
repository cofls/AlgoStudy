package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_7682_틱택토 {
	static char[][] map = new char[3][3];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String tmp = br.readLine();
		while(!tmp.equals("end")) {
			boolean right = true;
			int numO = 0;
			int numX = 0;
			int remain =0;
			for(int i=0,j=0;i<9;i++) {
				char ch = tmp.charAt(i);
				if(ch=='O') numO++;
				else if(ch=='X') numX++;
				else remain++;
				
				map[j][i%3]= ch;
				if((i+1)%3==0) j++;
			}
			
			//0,X,.개수 확인
			if (remain == 0) {//OX로 다 놓은 경우
				//X가 하나 더 많은 경우가 아니면 false
				if (numX - 1 != numO)	right = false;
				//다 찼을 때 O로 이기는 경우가 있으면 false
				int result = check('O');
				if (result > 0) right = false;			
			}//X를 놓음으로써 이기는 경우
			else if (remain % 2 == 0 && remain!=0) {
				//X가 하나더 많아야 함, 그렇지 않으면 false
				if (numX - 1 != numO)	right = false;
				//O로 이기면 안되는 거니까 false
				int result = check('O');
				if (result > 0) right = false;
				//X로 이겨야 하는데 맞는 경우가 없으면 false
				result = check('X');
				if (result == 0) right = false;
			}//O를 둠으로써 이기는 경우, X와 O의 갯수 같아야 함 
			else {
				//갯수 안 같으면 false
				if (numX != numO)	right = false;
				//X가 이기면 false
				int result = check('X');
				if (result > 0) right = false;
				//O가 이기는 경우 없으면 false
				result = check('O');
				if (result == 0) right = false;
			}
			
			if (right) sb.append("valid\n");
			else sb.append("invalid\n");
			
			tmp = br.readLine();
		}
		System.out.println(sb.toString());
	}
	private static int check(char c) {
		int cnt = 0;
		for (int i = 0; i < 3; i++) {
			//가로
			if (map[i][0] == map[i][1] && map[i][1] == map[i][2]) {	
				if (map[i][0] == c) 	cnt++;	
			}
			//세로
			if (map[0][i] == map[1][i] && map[1][i] == map[2][i]) {
				if (map[0][i] == c) 	cnt++;
			}
		}
		// /, \
		if (map[0][0] == map[1][1] && map[1][1] == map[2][2]) {
			if (map[1][1] == c)		cnt++;
		}
		if (map[0][2] == map[1][1] && map[1][1] == map[2][0]) {
			if (map[1][1] == c) 	cnt++;
		}
		return cnt;
	}

}
