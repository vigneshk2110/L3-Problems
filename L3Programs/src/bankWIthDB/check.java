package bankWIthDB;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class check {

	public static void main(String[] args) {
		System.out.println(validatePassword("MNnm89"));		

	}
	
	protected static boolean validatePassword(String pass) {

		String pattern1="[a-z]";
		String pattern2="[A-Z]";
		String pattern3="[0-9]";
		String pattern4="\u0020";
		int total=0;
		for(int i=1;i<=4;i++) {
			String pattern = null;
			switch(i) {
			case 1:
				pattern=pattern1;
				break;
			case 2:
				pattern=pattern2;
				break;
			case 3:
				pattern=pattern3;
				break;
				
			case 4:
				pattern=pattern4;
				break;
			}
			Pattern p =Pattern.compile(pattern);
			Matcher m=p.matcher(pass);
			int count=0;
			while(m.find()) {
				if(m.group().equals(" ")) {
					return false;
				}
				System.out.println(m.group());
				count+=1;
				if(count==2) {
					total+=count;
					break;
				}	
			}
		}
		if(total==6) {
			return true;
			}
		else {
			return false;
		}
	}

}
