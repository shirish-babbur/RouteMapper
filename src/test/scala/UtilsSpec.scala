import com.shirish.routemapper.utils.Utils

class UtilsSpec extends UnitSpec {

  "A Utility Function" should "give out correct number" in {
    val input = "100"
    assertResult(100)(Utils.getDigit(input))
  }

  it should "throw exception if not number" in {
    val input = "1asd"
    assertThrows[NumberFormatException] {
      Utils.getDigit(input)
    }
  }

  it should "throw exception if empty input passed" in {
    val input = ""
    assertThrows[NumberFormatException] {
      Utils.getDigit(input)
    }
  }

  it should "give out default cursor with location at (0, 0) and angle of 0 degrees" in {
    val defaultCursor = Utils.getDefaultCursor
    assert(defaultCursor.point.x == 0)
    assert(defaultCursor.point.y == 0)
    assert(defaultCursor.angle == 0)
  }

  it should "return true if checked for point(10, 10) is within bounding box" in {
    val isWithinBox = Utils.isWithinBoundingBox(10, 10)
    assertResult(true)(isWithinBox)
  }

  it should "return false if checked for point(10, 1000) is within bounding box" in {
    val isWithinBox = Utils.isWithinBoundingBox(10, 1000)
    assertResult(false)(isWithinBox)
  }

  it should "return false if checked for point(500, 500) is within bounding box" in {
    val isWithinBox = Utils.isWithinBoundingBox(500, 500)
    assertResult(false)(isWithinBox)
  }

  it should "return true if checked for point(-400, -400) is within bounding box" in {
    val isWithinBox = Utils.isWithinBoundingBox(-400, -400)
    assertResult(true)(isWithinBox)
  }

}
