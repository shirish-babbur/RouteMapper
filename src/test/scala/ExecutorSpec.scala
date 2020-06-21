import com.shirish.routemapper.services.{CommandParser, Executor, SimpleExecutor}
import com.shirish.routemapper.utils.Cursor

class ExecutorSpec  extends UnitSpec {
  import TestUtils._
  val parser = new CommandParser()
  val executor: Executor = new SimpleExecutor()

  "A Executor" should "be instantiatable" in {
    assert(executor.isInstanceOf[SimpleExecutor])
  }

  it should "map route for FD correctly" in {
    val input = "FD 100"
    val expected = Seq(getCursor(0,0,0), getCursor(0, 100, 0))
    val actual = executor.execute(parser.parse(input), TestUtils.getCursor(0, 0, 0))
    assertResult(expected.size)(actual.size)
    assertCursors(actual, expected)
  }

  it should "map route for square to right correctly" in {
    val input = "FD 100, RT 90, FD 100, RT 90, FD 100, RT 90, FD 100, RT 90"
    val expected = Seq(getCursor(0,0,0), getCursor(0, 100, 0), getCursor(0,100,90),
      getCursor(100,100,90), getCursor(100,100,180), getCursor(100,0,180),
      getCursor(100,0,270), getCursor(0, 0,270), getCursor(0, 0, 360))
    val actual = executor.execute(parser.parse(input), TestUtils.getCursor(0, 0, 0))
    assertResult(expected.size)(actual.size)
    assertCursors(actual, expected)
  }

  it should "map route for square to left correctly" in {
    val input = "FD 100, LT 90, FD 100, LT 90, FD 100, LT 90, FD 100, LT 90"
    val expected = Seq(getCursor(0,0,0), getCursor(0, 100, 0), getCursor(0,100,-90),
      getCursor(-100,100,-90), getCursor(-100,100,-180), getCursor(-100,0,-180),
      getCursor(-100,0,-270), getCursor(0, 0,-270), getCursor(0, 0, -360))
    val actual = executor.execute(parser.parse(input), TestUtils.getCursor(0, 0, 0))
    assertResult(expected.size)(actual.size)
    assertCursors(actual, expected)
  }

  it should "map route for Pentagon to right correctly" in {
    val input = "FD 100, RT 72, FD 100, RT 72, FD 100, RT 72, FD 100, RT 72, FD 100, RT 72"
    val expected = Seq(getCursor(0,0,0), getCursor(0, 100, 0), getCursor(0,100,72),
      getCursor(95,131,72), getCursor(95,131,144), getCursor(154,50,144),
      getCursor(154,50,216), getCursor(95, -31,216), getCursor(95, -31, 288),
      getCursor(0, 0, 288), getCursor(0, 0, 360))
    val actual = executor.execute(parser.parse(input), TestUtils.getCursor(0, 0, 0))
    assertResult(expected.size)(actual.size)
    assertCursors(actual, expected)
  }

  it should "map route for square to right correctly two times" in {
    val input = "REPEAT 4 (FD 100, RT 90), REPEAT 4 (FD 100, RT 90)"
    var expected = Seq(getCursor(0, 0, 0), getCursor(0, 100, 0), getCursor(0, 100, 90),
      getCursor(100, 100, 90), getCursor(100, 100, 180), getCursor(100, 0, 180),
      getCursor(100, 0, 270), getCursor(0, 0, 270), getCursor(0, 0, 360),
      getCursor(0, 100, 360), getCursor(0, 100, 450),
      getCursor(100, 100, 450), getCursor(100, 100, 540), getCursor(100, 0, 540),
      getCursor(100, 0, 630), getCursor(0, 0, 630), getCursor(0, 0, 720))
    val actual = executor.execute(parser.parse(input), TestUtils.getCursor(0, 0, 0))
    assertResult(expected.size)(actual.size)
    assertCursors(actual, expected)
  }

  it should "map route for Jump Forward correctly" in {
    val input = "FD 100, RT 90, JUMP FD 100, RT 90, FD 100, RT 90, FD 100, RT 90"
    val expected = Seq(getCursor(0,0,0), getCursor(0, 100, 0), getCursor(0,100,90),
      getCursor(100,100,180), getCursor(100,0,180), getCursor(100,0,270),
      getCursor(0,0,270), getCursor(0, 0,360))
    val actual = executor.execute(parser.parse(input), TestUtils.getCursor(0, 0, 0))
    assertResult(expected.size)(actual.size)
    assertCursors(actual, expected)
  }

  it should "map route for Jump and backward correctly" in {
    val input = "FD 100, LT 90, FD 100,  BK 100, RT 90, BK 100"
    val expected = Seq(getCursor(0,0,0), getCursor(0, 100, 0), getCursor(0,100,-90),
      getCursor(-100,100,-90), getCursor(0,100,-90), getCursor(0,100, 0),
      getCursor(0,0,0))
    val actual = executor.execute(parser.parse(input), TestUtils.getCursor(0, 0, 0))
    assertResult(expected.size)(actual.size)
    assertCursors(actual, expected)
  }

  it should "map route for out of bounding box should be no op" in {
    val input = "FD 200, FD 400, RT 90, FD 200, FD 400"
    val expected = Seq(getCursor(0,0,0), getCursor(0, 200, 0), getCursor(0,200,0),
      getCursor(0,200,90), getCursor(200,200,90), getCursor(200,200, 90))
    val actual = executor.execute(parser.parse(input), TestUtils.getCursor(0, 0, 0))
    assertResult(expected.size)(actual.size)
    assertCursors(actual, expected)
  }


  def assertCursors(actual: Seq[Cursor], expected: Seq[Cursor]): Unit = {
    for(entry <- actual.zip(expected)) {
      assert(entry._1.angle == entry._2.angle)
      assert(entry._1.point.x == entry._2.point.x)
      assert(entry._1.point.y == entry._2.point.y)
    }
  }

}
