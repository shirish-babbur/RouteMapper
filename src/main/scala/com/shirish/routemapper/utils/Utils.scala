package com.shirish.routemapper.utils

/***
 * Case class to represent co-ordinate positions for a 2D plane
 * @param x: Int
 * @param y: Int
 */
case class Point(x: Int, y: Int)

/***
 * Cursor represents Point with current direction (angle in degrees) to move on 2D plane
 * @param point: Point
 * @param angle: Int
 */
case class Cursor(point: Point, angle: Int)

/***
 * Bounding Box which will bound the 2D plane with 4 Points
 * @param topLeft: Point
 * @param topRight: Point
 * @param bottomLeft: Point
 * @param bottomRight: Point
 */
case class BoundingBox(topLeft: Point, topRight: Point, bottomLeft: Point, bottomRight: Point)

object Utils {

  lazy val boundingBox: BoundingBox = createBoundingBox()

  /***
   * Creates the bounding box with
   * points (-400,400), (-400,-400), (400,400), (400,-400)
   * @return
   */
  def createBoundingBox(): BoundingBox = {
    val topLeft = Point(-400, 400)
    val bottomLeft = Point(-400, -400)
    val topRight = Point(400, 400)
    val bottomRight = Point(400, -400)
    BoundingBox(topLeft, topRight, bottomLeft, bottomRight)
  }

  /***
   * helper function to convert string literal to Integer equivalent
   * @param literal: String
   * @return Int
   */
  def getDigit(literal: String): Int = {
    if(literal.length > 0) {
      literal.trim.toInt
    } else {
      throw new NumberFormatException
    }
  }

  /***
   * helper function to check whether given co-ordinates present inside bounding box
   * @param x: Int
   * @param y: Int
   * @return Boolean
   */
  def isWithinBoundingBox(x: Int, y: Int): Boolean = {
    if (boundingBox.topLeft.x <= x && x <= boundingBox.topRight.x && boundingBox.topLeft.y >= y && y >= boundingBox.bottomRight.y) {
      true
    } else {
      false
    }
  }

  /***
   * helper function to create a Cursor instance with default co-ordinates
   * as (0, 0) and angle of 0 degrees. used to start at.
   * @return Cursor
   */
  def getDefaultCursor: Cursor = {
    Cursor(Point(0, 0), 0)
  }
}
