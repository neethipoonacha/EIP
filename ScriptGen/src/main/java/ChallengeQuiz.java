import java.util.ArrayList;
import java.util.List;

public class ChallengeQuiz {

	public static void main(String[] args) {
		List<Integer> arr = new ArrayList();
		arr.add(1);
		arr.add(2);
		arr.add(3);
		arr.add(4);
		arr.add(5);
		arr.add(6);
		arr.add(7);
		arr.add(8);
		arr.add(9);
		//arr.add(1);
		
		int h = oddDigitPrime(1, 9, 0);
		System.out.println(h);
		
	}

	public static int oddDigitPrime(int l, int r, int t) {
	    // Write your code here
		List<Integer> arr = new ArrayList();
		int count =0;
		for(int i = l;i<=r;i++) {
			int length = String.valueOf(l).length();
			if(length<0) {
				char[] charArry = ("" + i).toCharArray();
				for(int j = 0;j<=charArry.length;j++) {
					
				}
			}else {
				if(i==0||i==1) {
					continue;
				}
				if(i%2==0) {
					continue;
				}
				for(int k=2;k<=i;k++) {
					if(!(k%i==0))
						arr.add(k);
					break;
				}
			}
		}
		t=arr.size()-1;
		return t;

	    }
	
	private static boolean checkPrime(int p){

		if(p==0||p==1) {
			return false;
		}
		if(p%2==0) {
			return false;
		}
		for(int k=2;k<=p;k++) {
			if(!(k%p==0))
				return true;
			break;
		}
		return false;

	}
}
