import com.shirish.routemapper.commands.Forward
import com.shirish.routemapper.services.CommandParser
import com.shirish.routemapper.utils.ExceptionUtils.InvalidCommandException

class ParserSpec extends UnitSpec {

  var parser = new CommandParser()

  "A Parser" should "instantiate correctly"  in {
    val parser = new CommandParser()
    assert(parser.isInstanceOf[CommandParser])
  }

  it should "parse input correctly" in {
    val input = "FD 100"
    val parsed = parser.parse(input)
    val expectedOutput = Seq(Forward(100))
    assert(expectedOutput.size == parsed.size)
  }

  it should "throw Exception for incomplete commands" in {
    val input = "FD 100 BK"
    assertThrows[IllegalArgumentException] {
      parser.parse(input)
    }
  }

  it should "parse REPEAT keyword correctly" in {
    val input = "REPEAT 4 (FD 100, BK 100)"
    val actual = parser.parse(input)
    assert(actual.size == 8)
  }

  it should "should throw exception" in {
    val input = "REPEAT 4 (FD 100, BK )"
    assertThrows[IllegalArgumentException] {
      parser.parse(input)
    }
  }

  it should "should throw exception for no command but units specified" in {
    val input = "REPEAT 4 (FD 100, 100 )"
    assertThrows[IllegalArgumentException] {
      parser.parse(input)
    }
  }

  it should "should give empty collection for no command" in {
    val input = ""
    val actual = parser.parse(input)
    assert(actual.isEmpty)
  }

  it should "should throw exception for incomplete command but units specified" in {
    val input = "REPEAT 4 (FD 100, BK 100"
    assertThrows[InvalidCommandException] {
      parser.parse(input)
    }
  }

  it should "should throw exception for small case commands" in {
    val input = "fd 100"
    assertThrows[InvalidCommandException] {
      parser.parse(input)
    }
  }
}
