package week2;

import java.io.*;
import java.util.*;

public class BOJ_1406_에디터 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String tmp = br.readLine();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		LinkedList<Character> arr = new LinkedList<>();

		for(int i=0; i<tmp.length();i++) {
			arr.add(tmp.charAt(i));
		}
		ListIterator<Character> iter = arr.listIterator();

		while(iter.hasNext()){
			iter.next();
		}

		int N= Integer.parseInt(br.readLine());
		for(int n=0; n<N ; n++) {
			String cm = br.readLine();
			char c = cm.charAt(0);
            switch(c) {
                case 'L' : if(iter.hasPrevious()) iter.previous(); break;
                case 'D' : if(iter.hasNext()) iter.next(); break;
                case 'B' :if(iter.hasPrevious()) {
					    iter.previous();
					    iter.remove();
				    }
                    break;
                case 'P' :  char p = cm.charAt(2);
				        iter.add(p);
                    break;
            }
		}	
		for(char a: arr) {
			bw.write(a);
		}
		br.close();
		bw.flush();
		bw.close();
	}
}

