package poker.com;

import java.util.Arrays;
import java.util.HashMap;

public class Poke
{
	public static void main(String[] args)
	{
		String black = "Black: 9H 2S 2D 9C 9D";
		String white = "White: 2H 2C 8D 8C 8S";
		Poke.pokerJudge(black,white);
	}
	//�ж��������͵Ĺ���
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
		boolean sameCode = false;//�ǲ���ͬ��
		int result = 0;
		if(oneside[0].charAt(1) == oneside[1].charAt(1)
				&&oneside[0].charAt(1)==oneside[2].charAt(1)
				&&oneside[0].charAt(1)==oneside[3].charAt(1)
				&&oneside[0].charAt(1)==oneside[4].charAt(1))
					sameCode = true;
		//����������ȫ����һ����˳�ӡ�ͬ��˳
		if (two == 0 && three ==0 && four ==0)
		{
			boolean line = false;//�ǲ���˳��
			if(oneside[4].charAt(0) - oneside[0].charAt(0) == 4)
				line = true;
			if(line && sameCode)
					result = 9;
			else if(line)
				result = 5;
			else
				result = 1;
		}
		//һ�ԣ�����
		else if (two == 1&& three==0 && four==0)
			result = 2;
		//���ԣ�����
		else if (two == 2&& three==0 && four==0)
			result = 3;
		//����������
		else if (two == 0&& three==1 && four==0)
			result = 4;
		//���Ӽ�����
		else if (two == 1&& three==1 && four==0)
			result = 7;
		//���ţ���֧
		else if (two == 0&& three==0 && four==1)
			result = 8;
		if (sameCode && result <6)
			result = 6;
		return result;
	}
	//����ɢ��֮��Ĵ�С�Ƚ�
	public static String compareHighCard(String[] blacks,String[] whites)
	{
		String str = "tie(ƽ��)";
		//�ж�ɢ�ƵĴ�С���Ƚ����һ���ƵĴ�С����ͬ���Ƚϴδ�
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
	//�ж϶��ӵĴ�С��ֻ��һ�ԣ�
	public static String compareOnePair(String[] blacks,String[] whites)
	{
		String str = "tie(ƽ��)";
		//�ж϶��ӵĴ�С������ȣ��Ƚ�ɢ�ƵĴ�С
		char blackPair = 'a';
		char whitePair = 'a';
		for(int i=4;i>0;i--)
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
		else
		{
			int j = 4;
			int k = 4;
			while(j>0&&k>0)
			{
				if(blacks[j].charAt(0) == blacks[j-1].charAt(0))
					j = j -2;
				if(whites[k].charAt(0) == whites[k-1].charAt(0))
					k = k -2;
				if (j<0 || k <0)
					break;//�Ѿ��Ƚϵ���β
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
		}
		return str;	
	}
	//�ж����ԵĴ�С
	public static String compareTwoPairs(String[] blacks,String[] whites)
	{
		String str = "tie(ƽ��)";
		//�ж϶��ӵĴ�С������ȣ��Ƚ�ɢ�ƵĴ�С
		char blackPair = 'a';
		char whitePair = 'a';
		//�жϽϴ��һ��
		for(int i=4;i>0;i--)
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
		else
		{//�ϴ��һ�Ե�����ͬʱ���жϽ�С��һ��
			for(int i=0;i<4;i++)
			{
				if(blacks[i].charAt(0) == blacks[i+1].charAt(0))
					blackPair = blacks[i].charAt(0);
				if(whites[i].charAt(0) == whites[i+1].charAt(0))
					whitePair = whites[i].charAt(0);
			}
			if (blackPair > whitePair)
				str = "Black wins";
			else if (blackPair < whitePair)
				str = "White wins";
			else
			{//��С��һ�Ե�����ͬ���Ƚϵ����ƴ�С
				int j = 4;
				int k = 4;
				while(j>0&&k>0)
				{
					if(blacks[j].charAt(0) == blacks[j-1].charAt(0))
						j = j -2;
					if(whites[k].charAt(0) == whites[k-1].charAt(0))
						k = k -2;
					if (j<0 || k <0)
						break;//�Ѿ��Ƚϵ���β
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
			}
		}
		return str;	
	}
	//����:������ƽ��
	public static String compareThreeOfAKind(String[] blacks,String[] whites)
	{
		String str = "White wins";
		//�ж������Ĵ�С
		char blackPair = 'a';
		char whitePair = 'a';
		for(int i=4;i>0;i--)
		{
			if(blacks[i].charAt(0) == blacks[i-1].charAt(0))
				blackPair = blacks[i].charAt(0);
			if(whites[i].charAt(0) == whites[i-1].charAt(0))
				whitePair = whites[i].charAt(0);
		}
		if (blackPair > whitePair)
			str = "Black wins";
		return str;
	}
	//˳��
	public static String compareStraight(String[] blacks,String[] whites)
	{
		String str = "tie(ƽ��)";
		//�ж������Ĵ�С������ȣ��Ƚ�ɢ�ƵĴ�С
		if(blacks[4].charAt(0) > whites[4].charAt(0))
			str = "Black wins";
		else if(blacks[4].charAt(0) < whites[4].charAt(0))
			str = "White wins";
		return str;
	}
	//ͬ��:����ɢ�ƹ���Ƚ�
	//��«: Full House
	public static String compareFullHouse(String[] blacks,String[] whites)
	{
		String str = "White wins";
		//�ж������Ĵ�С������ȣ��Ƚ�ɢ�ƵĴ�С
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
	//��֧
	public static String compareFourOfAKind(String[] blacks,String[] whites)
	{
		String str = "White wins";
		//�ж������Ĵ�С
		char blackPair = 'a';
		char whitePair = 'a';
		for(int i=4;i>0;i--)
		{
			if(blacks[i].charAt(0) == blacks[i-1].charAt(0))
				blackPair = blacks[i].charAt(0);
			if(whites[i].charAt(0) == whites[i-1].charAt(0))
				whitePair = whites[i].charAt(0);
		}
		if (blackPair > whitePair)
			str = "Black wins";
		return str;
	}
	//ͬ��˳:����ɢ�Ƶķ���
	
	public static String pokerJudge(String blackside,String whiteside)
	{
		String str= "tie(ƽ��)";
		StringBuffer black = new StringBuffer(blackside);
		StringBuffer white = new StringBuffer(whiteside);
		HashMap<Character,Character> numberMap = new HashMap<Character, Character>()
			{
			{put('2','a');put('3','b');;put('4','c');put('5','d');put('6','e');put('7','f');
			put('8','g');put('9','h');put('T','i');put('J','j');put('Q','k');put('K','l');put('A','m');}
			};
//		����: Black: 2H 3D 5S 9C KD White: 2C 3H 4S 8C AH ���: White wins
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
		//��ͬ����֮��ıȽ�
		else
		{
			if(typeOfBlack == 1)
				str = compareHighCard(blacks,whites);
			else if (typeOfBlack == 2)
				str = compareOnePair(blacks,whites);
			else if (typeOfBlack == 3)
				str = compareTwoPairs(blacks,whites);
			else if (typeOfBlack == 4)
				str = compareThreeOfAKind(blacks,whites);
			else if (typeOfBlack == 5)
				str = compareStraight(blacks,whites);
			else if(typeOfBlack == 6)//ͬ������ɢ�ƵĹ���ȥ�Ƚ�
				str = compareHighCard(blacks,whites);
			else if(typeOfBlack == 7)//������һ��
				str = compareFullHouse(blacks,whites);
			else if(typeOfBlack == 8)//������һ��
				str = compareFourOfAKind(blacks,whites);
			else 
				str = compareHighCard(blacks,whites);
		}
		
		return str;
	}
}
