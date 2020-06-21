import com.shirish.routemapper.utils.{Cursor, Point}

object TestUtils {

  def getCursor(x: Int, y: Int, angle: Int): Cursor = {
    Cursor(Point(x, y), angle)
  }
}
