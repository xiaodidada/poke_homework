package poker.com;

import java.util.Arrays;
import java.util.HashMap;

public class Poke
{
	//判断牌面类型的工具
	public static int helper(String[] oneside)
	{
		int two = 0;
		int three = 0;
		int four = 0;
		for (int k=0;k<4;k++)
		{
			if (k+3 <5 &&(oneside[k].charAt(0) == oneside[k+3].charAt(0)))
			{
				four++;
				break;
			}				
			else if (k+2<5 &&(oneside[k].charAt(0) == oneside[k+2].charAt(0)))
			{
				three++;
				k = k+2;
			}				
			else if (oneside[k].charAt(0) == oneside[k+1].charAt(0))
			{
				two++;
				k = k+1;
			}	
		}
		boolean sameCode = false;//是不是同花
		int result = 0;
		if(oneside[0].charAt(1) == oneside[1].charAt(1)
				&&oneside[0].charAt(1)==oneside[2].charAt(1)
				&&oneside[0].charAt(1)==oneside[3].charAt(1)
				&&oneside[0].charAt(1)==oneside[4].charAt(1))
					sameCode = true;
		//五张牌数字全都不一样：顺子、同花顺
		if (two == 0 && three ==0 && four ==0)
		{
			boolean line = false;//是不是顺子
			if(oneside[4].charAt(0) - oneside[0].charAt(0) == 4)
				line = true;
			if(line && sameCode)
					result = 9;
			else if(line)
				result = 5;
			else
				result = 1;
		}
		//一对：对子
		else if (two == 1&& three==0 && four==0)
			result = 2;
		//两对：两对
		else if (two == 2&& three==0 && four==0)
			result = 3;
		//三条：三条
		else if (two == 0&& three==1 && four==0)
			result = 4;
		//对子加三条
		else if (two == 1&& three==1 && four==0)
			result = 7;
		//四张：铁支
		else if (two == 0&& three==0 && four==1)
			result = 8;
		if (sameCode && result <6)
			result = 6;
		return result;
	}
	//两对散牌之间的大小比较
	public static String compareHighCard(String[] blacks,String[] whites)
	{
		String str = "tie(平局)";
		//判断散牌的大小：比较最大一张牌的大小，若同，比较次大
		for(int i=4;i>=0;i--)
		{
			char blackPoint = blacks[i].charAt(0);
			char whitePoint = whites[i].charAt(0);
			if(blackPoint < whitePoint )
			{
				str = "White wins";
				break;
			}
			else if (blackPoint < whitePoint)
			{
				str = "Black wins";
				break;
			}
		}
		return str;
	}
	//将对子的大小比较抽取出来
	public static String comparePairs(String[] blacks,String[] whites,int start,int end)
	{
		String str = "tie(平局)";
		char blackPair = 'a';
		char whitePair = 'a';
		for(int i=start;i>end;i--)
		{
			if(blacks[i].charAt(0) == blacks[i-1].charAt(0))
				blackPair = blacks[i].charAt(0);
			if(whites[i].charAt(0) == whites[i-1].charAt(0))
				whitePair = whites[i].charAt(0);
		}
		if (blackPair > whitePair)
			str = "Black wins";
		else if (blackPair < whitePair)
			str = "White wins";
		return str;
	}
	//将含有对子（一对、两对）的散牌的比较抽取出来
	public static String compareHighCardsWithPairs(String[] blacks,String[] whites)
	{
		String str = "tie(平局)";
		int j = 4;
		int k = 4;
		while(j>0&&k>0)
		{
			if(blacks[j].charAt(0) == blacks[j-1].charAt(0))
				j = j -2;
			if(whites[k].charAt(0) == whites[k-1].charAt(0))
				k = k -2;
			if (j<0 || k <0)
				break;//已经比较到牌尾
			if (blacks[j].charAt(0) >whites[k].charAt(0))
			{
				str = "Black wins";
				break;
			}
			else if (blacks[j].charAt(0) <whites[k].charAt(0))
			{
				str = "White wins";
				break;
			}
			else
			{
				j--;
				k--;
			}
		}
		return str;
	}
	//判断对子的大小（只有一对）
	public static String compareOnePair(String[] blacks,String[] whites)
	{
		String str = "tie(平局)";
		//判断对子的大小
		str = comparePairs(blacks,whites,4,0);
		//若相等，比较散牌的大小
		if (str == "tie(平局)")
			str = compareHighCardsWithPairs(blacks,whites);
		return str;	
	}
	//判断两对的大小
	public static String compareTwoPairs(String[] blacks,String[] whites)
	{
		String str = "tie(平局)";
		//判断较大的一对
		str = comparePairs(blacks,whites,4,0);
		//若相等，比较散牌的大小
		if (str == "tie(平局)")
		{//较大的一对点数相同时，判断较小的一对
			str = comparePairs(blacks,whites,0,4);
			//较小的一对点数相同，比较单张牌大小
			if (str == "tie(平局)")
				str = compareHighCardsWithPairs(blacks,whites);
		}
		return str;	
	}
	//三条、铁支、葫芦合并:不可能平局
	public static String compareThreeOrFourOfAKindAndFullHouse(String[] blacks,String[] whites)
	{
		String str = "White wins";
		char blackPair = 'a';
		char whitePair = 'a';
		for(int i=4;i>1;i--)
		{
			if(blacks[i].charAt(0) == blacks[i-2].charAt(0))
				blackPair = blacks[i].charAt(0);
			if(whites[i].charAt(0) == whites[i-2].charAt(0))
				whitePair = whites[i].charAt(0);
		}
		if (blackPair > whitePair)
			str = "Black wins";
		return str;
	}
	public static String pokerJudge(String blackside,String whiteside)
	{
		String str= "tie(平局)";
		StringBuffer black = new StringBuffer(blackside);
		StringBuffer white = new StringBuffer(whiteside);
		HashMap<Character,Character> numberMap = new HashMap<Character, Character>()
			{
			{put('2','a');put('3','b');;put('4','c');put('5','d');put('6','e');put('7','f');
			put('8','g');put('9','h');put('T','i');put('J','j');put('Q','k');put('K','l');put('A','m');}
			};
		for (int i=7;i<21;i=i+3)
		{
			black.setCharAt(i, numberMap.get(black.charAt(i)));
			white.setCharAt(i, numberMap.get(white.charAt(i)));
		}
		String[] blacks = black.substring(7).split(" ");
		String[] whites = white.substring(7).split(" ");
		Arrays.sort(blacks);
		Arrays.sort(whites);
		int typeOfBlack = helper(blacks);
		int typeOfWhite = helper(whites);
		if (typeOfBlack > typeOfWhite)
			str = "Black wins";
		else if (typeOfBlack < typeOfWhite)
			str = "White wins";
		else
		{//相同类型之间的比较
			if(typeOfBlack == 1 || typeOfBlack == 5 || typeOfBlack == 6 || typeOfBlack == 9)//散牌、顺子、同花，同花顺
				str = compareHighCard(blacks,whites);
			else if (typeOfBlack == 2)//一对
				str = compareOnePair(blacks,whites);
			else if (typeOfBlack == 3)//两对
				str = compareTwoPairs(blacks,whites);
			else if (typeOfBlack == 4 || typeOfBlack == 7 || typeOfBlack == 8)//三条、葫芦、铁支
				str = compareThreeOrFourOfAKindAndFullHouse(blacks,whites);
		}
		return str;
	}
}
