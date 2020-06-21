package com.shirish.routemapper.commands

import com.shirish.routemapper.utils.{Cursor, Utils}

/***
 * Simple JumpBackWard command which updates the x and y co ordinates based on how much
 * units distance to jump back to at current angle plane.
 * @param units: Int
 */
case class JumpBackWard(units: Int) extends Command {

  /***
   * Basic apply function to apply jump backward motion by units distance
   * and returns updated Cursor position
   * @param cursor: Cursor
   * @return Cursor
   */
  override def apply(cursor: Cursor): Cursor = {
    val absAngle = if(cursor.angle >= 0 ) cursor.angle else Math.abs(360 - Math.abs(cursor.angle))
    // find angle and decide how to much long to go on
    var newX = cursor.point.x - Math.round(Math.sin(Math.toRadians(absAngle)) * this.units).toInt
    var newY = cursor.point.y - Math.round(Math.cos(Math.toRadians(absAngle)) * this.units).toInt
    if (!Utils.isWithinBoundingBox(newX,  newY)) {
      newX = cursor.point.x
      newY = cursor.point.y
    }
    cursor.copy(point = cursor.point.copy(x = newX, y= newY), angle = cursor.angle)
  }
}
