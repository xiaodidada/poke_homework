package poker.com;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PokeTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	//��ͬ�����ƱȽϵĲ���������
	//����Ҫ�漰���������ͣ����Գ����ܷ���ȷ�ж��Ƶ�����
	@Test
	public void test1with2() {
		String black = "Black: 3H 2D 5S 9C KD";
		String white = "White: 2C 2H 4S 8C AH";
		assertEquals("White wins",Poke.pokerJudge(black,white));
	}
	@Test
	public void test4with3() {
		String black = "Black: 3H 3D 3S 9C KD";
		String white = "White: 2C 2H 4S 8C KH";
		assertEquals("Black wins",Poke.pokerJudge(black,white));
	}
	@Test
	public void test5with6() {
		String black = "Black: 3H 4D 5S 5C 7D";
		String white = "White: 2C 3C 4C 8C AC";
		assertEquals("White wins",Poke.pokerJudge(black,white));
	}
	@Test
	public void test8with7() {
		String black = "Black: 9H 9D 9S 9C KD";
		String white = "White: 3C 3H 4S 4C 4H";
		assertEquals("Black wins",Poke.pokerJudge(black,white));
	}
	@Test
	public void test5with9() {
		String black = "Black: 3D 2D 5D 9D KD";
		String white = "White: 2C 3C 4C 5C 6C";
		assertEquals("White wins",Poke.pokerJudge(black,white));
	}
	//��ͬ��������֮��ıȽ�
	//��Ҫ���Ǣ�ƽ�֢�һ��ʤ
	////��ɢ�ƣ�high card
	@Test
	public void testHighCard1() {
		String black = "Black: 3D 2C 5D 9D KD";
		String white = "White: 2C 9C 3C 5D KC";
		assertEquals("tie(ƽ��)",Poke.pokerJudge(black,white));
	}
	@Test
	public void testHighCard2() {
		String black = "Black: 3H 2D 5S 9C KD";
		String white = "White: 2C 5H 4S 8C AH";
		assertEquals("White wins",Poke.pokerJudge(black,white));
	}
	////��һ�ԣ�one pair,ֱ�ӱȽ϶��ӣ�����Ҫ�Ƚ�ɢ�ƣ�ƽ��
	@Test
	public void testOnePair1() {
		String black = "Black: 3D 3H 5D 9D KD";
		String white = "White: 2C 9C 5C 5H KC";
		assertEquals("White wins",Poke.pokerJudge(black,white));
	}
	@Test
	public void testOnePair2() {
		String black = "Black: 2H 2D 5S 9C AD";
		String white = "White: 2C 4S 2S 8C KH";
		assertEquals("Black wins",Poke.pokerJudge(black,white));
	}
	@Test
	public void testOnePair3() {
		String black = "Black: 2H 2D 8S KC AD";
		String white = "White: 2C AS 2S 8C KH";
		assertEquals("tie(ƽ��)",Poke.pokerJudge(black,white));
	}
////�����ԣ�two pairs,�ȽϽϴ�Ķ��ӣ�����Ҫ�ȽϽ�С�Ķ��ӣ��Ƚ�ɢ�ƣ�ƽ��
	@Test
	public void testTwoPairs1() {
		String black = "Black: 3D 3H 9S 9D KD";
		String white = "White: 2C 2H 5C 5H KC";
		assertEquals("Black wins",Poke.pokerJudge(black,white));
	}
	@Test
	public void testTwoPairs2() {
		String black = "Black: 2D 2H 9S 9D KD";
		String white = "White: 3C 3H 9C 9H KC";
		assertEquals("White wins",Poke.pokerJudge(black,white));
	}
	@Test
	public void testTwoPairs3() {
		String black = "Black: 3D 3S 9S 9D AD";
		String white = "White: 3C 3H 9C 9H KC";
		assertEquals("Black wins",Poke.pokerJudge(black,white));
	}
	@Test
	public void testTwoPairs4() {
		String black = "Black: 3D 3S 9S 9D KD";
		String white = "White: 3C 3H 9C 9H KC";
		assertEquals("tie(ƽ��)",Poke.pokerJudge(black,white));
	}
////�ܣ�three of a kind:����
	@Test
	public void testThreeOfAKind() {
		String black = "Black: 3D 3H 3S 9D KD";
		String white = "White: 2C 2H 5C 2D KC";
		assertEquals("Black wins",Poke.pokerJudge(black,white));
	}
////�ݣ�˳�ӣ�ƽ�֣�һ��ʤ
	@Test
	public void testStraight1() {
		String black = "Black: 4D 3H 6S 5D 7D";
		String white = "White: 3C 4H 5C 6D 7C";
		assertEquals("tie(ƽ��)",Poke.pokerJudge(black,white));
	}
	@Test
	public void testStraight2() {
		String black = "Black: 2C 4H 5C 3D 6C";
		String white = "White: TD JH QS KD AD";
		assertEquals("White wins",Poke.pokerJudge(black,white));
	}
////��ͬ����Flush
	@Test
	public void testFlush1() {
		String black = "Black: 3D 2D 5D 9D KD";
		String white = "White: 2C 9C 3C 5C KC";
		assertEquals("tie(ƽ��)",Poke.pokerJudge(black,white));
	}
	@Test
	public void testFlush2() {
		String black = "Black: 3D 2D 5D 9D KD";
		String white = "White: 2C 9C 4C 5C KC";
		assertEquals("White wins",Poke.pokerJudge(black,white));
	}
////�ߺ�«��Full House
	@Test
	public void testFullHouse1() {
		String black = "Black: 9H 2S 2D 9C 9D";
		String white = "White: AH AS 8D 8C 8S";
		assertEquals("Black wins",Poke.pokerJudge(black,white));
	}
	@Test
	public void testFullHouse2() {
		String black = "Black: 9H 2S 2D 9C 9D";
		String white = "White: 2H 2C 8D 8C 8S";
		assertEquals("Black wins",Poke.pokerJudge(black,white));
	}
////����֧��four of a kind
	@Test
	public void testFourOfAkind1() {
		String black = "Black: 9H 2S 2D 2C 2H";
		String white = "White: 8H AS 8D 8C 8S";
		assertEquals("White wins",Poke.pokerJudge(black,white));
	}
////��ͬ��˳��Straight Flush
	@Test
	public void testStraightFlush1() {
		String black = "Black: 3D 2D 5D 4D 6D";
		String white = "White: 2C 4C 3C 5C 6C";
		assertEquals("tie(ƽ��)",Poke.pokerJudge(black,white));
	}
	@Test
	public void testStraightFlush2() {
		String black = "Black: 3D 2D 5D 4D 6D";
		String white = "White: TC JC QC KC AC";
		assertEquals("White wins",Poke.pokerJudge(black,white));
	}
}
