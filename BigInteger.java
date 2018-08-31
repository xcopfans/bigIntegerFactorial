public class BigInteger 
{
	/**
	 * 计算进位
	 * @param bit	 数组
	 * @param pos 用于判断是否是数组的最高位
	 */
	private void carry(int[] bit, int pos)
	{
		int i ,carray = 0;
		for(i = 0 ; i<= pos ;i++)//从0到pos逐位检查是否需要进位
		{
			bit[i] += carray;//累加进位
			if(bit[i] <= 9)	 //小于9不进位
			{
				carray = 0;
			}
			else if(bit[i] >9 && i<pos)//大于9，但不是最高位
			{
				carray = bit[i]/10;//保存进位值
				bit[i] = bit[i]%10;//得到该位的一位数
			}
			else if(bit[i] > 9 && i >= pos)//大于9，且是最高位
			{
				while(bit[i] > 9)//循环向前进位
				{
					carray = bit[i]/10;//计算进位值
					bit[i] = bit[i] % 10;//当前的第一位数
					i ++ ;
					bit[i] = carray;//在下一位保存进位值
				}
			}
		}
	}
	
	/**
	 * 大整数阶乘
	 * @param bigInteger 所计算的大整数
	 */
	private  void bigFactorial(int bigInteger)
	{
		int pos =0;//
		int digit;//数据长度
		int a , b ;
		int m = 0 ;//统计输出位数
		int n  = 0 ;//统计输出行数
		double sum = 0;//阶乘位数
		for(a = 1 ; a <= bigInteger ; a ++)//计算阶乘位数
		{
		    sum += Math.log10(a);
		}
		digit = (int)sum + 1;//数据长度
		
		int[] fact = new int[digit];//初始化一个数组 
		fact[0]  = 1;//设个位为 1
		
		for(a = 2 ; a <= bigInteger ; a++ )//将2^bigInteger逐个与原来的积相乘
		{
			for(b = digit-1 ; b >= 0 ; b--)//查找最高位{} 
			{	
				if( fact[b]  !=  0 )
				{
					pos = b ;//记录最高位
					break;
				}
			} 
			
			for(b = 0; b <= pos ; b++)
			{
				fact[b] *= a ;//每一位与i乘
			}
			carry(fact,pos);
		}
		
		for(b = digit-1 ; b >= 0 ; b --)
		{
			if(fact[b] != 0)
			{
				pos = b ;//记录最高位 
				break;
			}
		}
		System.out.println(bigInteger +"阶乘结果为：");
		for(a = pos ; a >= 0 ; a --)//输出计算结果
		{
			System.out.print(fact[a]);
			m++;
			if(m % 5 == 0)
			{
				System.out.print(" ");
			}
			if(40 == m )
			{
				System.out.println("");
				m = 0 ; 
				n ++;
				if(10 == n )
				{
					System.out.print("\n");
					n = 0;
				}
			}
		}
		System.out.println("\n"+"阶乘共有: "+(pos+1)+" 位");
		
	}
	
	public void doBigFactorial(int bigInteger)
	{
		int timeBegin=(int) System.currentTimeMillis();
		this.bigFactorial(bigInteger);
		int timeFinishi=(int) System.currentTimeMillis();
		int time = timeFinishi-timeBegin;
		System.out.println("计算耗时: " + time +"毫秒" );
	}
	
	public static void main(String[] args)
	{
		BigInteger bi = new BigInteger();
		bi.doBigFactorial(10000);
	}
}
