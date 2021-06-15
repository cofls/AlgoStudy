package week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_12886_돌그룹{
    static int sum;
    static boolean[][] ch = new boolean[1501][1501];
    static int answer = 0;

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        sum = a + b + c;

        ch[a][b] = true;
        dfs(a,b);
        System.out.println(answer);

    }

    public static void dfs(int a,int b){
        int c = sum - a - b;
        if(a == b && b == c ){
            answer = 1;
            return;
        }
        go(a,b);
        go(b,c);
        go(a,c);

    }

    private static void go(int a, int b) {
        int small = Math.min(a,b);
        int big = Math.max(a,b);
        if(!ch[small *2][big - small] || !ch[big - small][small *2]){
            ch[small *2][big - small] = ch[big - small][small *2] = true;
            dfs(small*2, big-small);
        }
    }


}