package poker.com;

import java.util.Arrays;
import java.util.HashMap;

public class Poke
{
	public static void main(String[] args)
	{
		String black = "Black: 3D 2D JD 9D KD";
		String white = "White: 2C 3C 4C 5C 6C";
		Poke.pokerJudge(black,white);
	}
	public static int helper(String[] oneside)
	{
		int two = 0;
		int three = 0;
		int four = 0;
		for (int k=0;k<4;k++)
		{
			if (k+3 <5 &&oneside[k].charAt(0) == oneside[k+3].charAt(0))
			{
				four++;
				break;
			}				
			else if (k+2<5 &&oneside[k].charAt(0) == oneside[k+2].charAt(0))
			{
				three++;
				k = k+3;
			}				
			else if (oneside[k].charAt(0) == oneside[k+1].charAt(0))
			{
				two++;
				k = k+2;
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
//		输入: Black: 2H 3D 5S 9C KD White: 2C 3H 4S 8C AH 输出: White wins
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
		//相同类型之间的比较
		else
		{
			
		}
		
		return str;
	}
}
