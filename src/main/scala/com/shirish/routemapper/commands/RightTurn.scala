package com.shirish.routemapper.commands

import com.shirish.routemapper.utils.Cursor

/***
 * Simple RightTurn command which updates the current angle based on how much
 * angles to turn from current angle plane towards right.
 * @param angle: Int
 */
case class RightTurn(angle: Int) extends Command {

  /***
   * Basic apply function to apply right turning angle by angle degrees
   * and returns updated Cursor position
   * @param cursor: Cursor
   * @return Cursor
   */
  override def apply(cursor: Cursor): Cursor = {
    cursor.copy(angle = cursor.angle + this.angle)
  }
}
