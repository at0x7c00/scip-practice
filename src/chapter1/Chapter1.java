package chapter1;

public class Chapter1 {

	public static void main(String[] args) {
		//System.out.println(factorial(6));
		//System.out.println(factorial2(6));
		System.out.println(A(1,10));
		System.out.println(A(2,1));
		System.out.println(A(3,3));
		
		for(int i = 0;i<10;i++){
			System.out.print(fib(i) + "\t");
		}
		//System.out.println(fib(30));
		System.out.println();
		for(int i = 0;i<10;i++){
			System.out.print(fib2(i)+"\t");
		}
		System.out.println();
		//System.out.println(fib2(100));
		for(int i = 0;i<10;i++){
			System.out.print(fib3(i)+"\t");
		}
		System.out.println();
		System.out.println(countChange(15));
		//System.out.println(countChange2(10));
		
	}
	
	public static int factorial(int num){
		if(num==1) return 1;
		return num * factorial(num - 1);
	}
	
	public static int factorial2(int num){
		return factInter(1,1,num);
	}

	private static int factInter(int product, int counter, int max) {
		if(counter > max){
			return product;
		}
		return factInter(product * counter,counter + 1,max);
	}
	//练习1.10
	public static long A(long x,long y){
		if(y == 0 ) return 0;
		else if(x == 0) return 2 * y;
		else if(y == 1) return 2;
		else return A(x - 1,A(x,y - 1));
	}
	
	//树形递归
	public static int fib(int n){
		if(n == 1) return 1;
		if(n == 0)  return 0;
		return fib(n - 1) + fib(n - 2);
	}
	//线性递归
	public static int fib2(int n){
		return fib2Inter(1,0,n);
	}
	public static int fib2Inter(int num,int b,int counter){
		if(counter == 0){
			return b;
		}
		return fib2Inter(num + b,num,counter - 1);
	}
	//线性调用其实可以转化成简单for循环
	public static int fib3(int n){
		int res = 0;
		int prefix = 1;
		for(int i = 0;i<n;i++){
			int tmp = res;
			res = prefix + res;
			prefix = tmp;
		}
		return res;
	}
	
	
	//换零钱
	public static int countChange(int mount){
		return cc(mount,5,"");
	}

	/**
	 * @param mount 整钱数量
	 * @param coinKinds 零钱类型数量
	 */
	private static int cc(int mount, int coinKinds,String str) {
		if(mount == 0 ) {
			format2(str);
			return 1;
		}
		if(mount<=0 || coinKinds == 0) return 0;
		
		return cc(mount,coinKinds - 1,str) + cc(mount - denomination(coinKinds),coinKinds,str += "," + coinKinds);
	}
	
	private static void format2(String str) {
		String[] ds = str.split(",");
		int[] dCount = new int[6];
		for(String dStr :ds){
			if(dStr==null || dStr.equals("")) continue;
			dCount[Integer.parseInt(dStr)]++;
		}
		String res = "";
		for(int i = 1;i<dCount.length;i++){
			if(dCount[i]==0) continue;
			res += " (" + denomination(i) +"x"+dCount[i]  + ") +" ;
		}
		if(res.length()>0) res = res.substring(0,res.length() - 1);
		System.out.println(res);
	}

	private static int denomination(int coinKind){
		switch(coinKind){
		case 1:return 1;
		case 2:return 5;
		case 3: return 10;
		case 4: return 20;
		default: return 50;
		}
	}
	
	private static int countChange2(int mount){
		int count = 0;
		int d1 = denomination(1);
		int d2 = denomination(2);
		int d3 = denomination(3);
		int d4 = denomination(4);
		int d5 = denomination(5);
		for(int i=0;i*d1<=mount;i++){
			for(int j=0;j*d2<=mount;j++){
				for(int k=0;k*d3<=mount;k++){
					for(int l=0;l*d4<=mount;l++){
						for(int m=0;m*d5<=mount;m++){
							int test = i * d1 
									 + j * d2
									 + k * d3
									 + l * d4
									 + m * d5;
							if(test==mount){
								String str = format(d1,i);
								str += format(d2,j);
								str += format(d3,k);
								str += format(d4,l);
								str += format(d5,m);
								str = str.substring(0,str.length() - 1);
								System.out.println(str);
								count++;
							}
						}
					}
				}	
			}
		}
		return count;
	}
	private static String format(int d,int count){
		if(count==0){
			return "";
		}
		return " ("+d + "x" + count + ") +";
	}
}
