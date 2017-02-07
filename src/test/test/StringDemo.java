package test;

public class StringDemo {

	public static void main(String[] args) {
		//commonMethod();
		//charAtString();
		internString();
	}
	
	//字符串类常用函数
	public static void commonMethod(){
		String str="hello";
		System.out.println("字符长度："+str.length());
		System.out.println(str.equals("hello"));
		System.out.println(str.toLowerCase());
		System.out.println(str.toUpperCase());
	}
	
	//字符串类常用函数
	public static void charAtString(){
		String text="One's left brain has nothing right.\n"+
		"One's right brain has nothing left.\n";
		System.out.println("字符串内容：");
		for(int i=0;i<text.length();i++){
			System.out.print(text.charAt(i));
		}
		System.out.println(text.indexOf("left"));
		System.out.println(text.lastIndexOf("left"));
		char[] c=text.toCharArray();
		for(int i=0;i<c.length;i++){
			System.out.print(c[i]);
		}
	}
	
	//字符串对象不可变
	public static void internString(){
		String str1="flyweight";
		String str2="flyweight";
		System.out.println(str1==str2);
		
		String str3=new String("caterpiller");
		String str4=new String("caterpiller");
		System.out.println(str3==str4);
		System.out.println(str3.equals(str4));
	}
}
