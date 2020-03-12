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

	//不同类型牌比较的测试用例：
	//①需要涉及到所有类型，测试程序能否正确判断牌的类型
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

}
