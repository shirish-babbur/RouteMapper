package com.shirish.routemapper.commands

import com.shirish.routemapper.utils.Cursor

/***
 * Simple LeftTurn command which updates the current angle based on how much
 * angles to turn from current angle plane towards left.
 * @param angle: Int
 */
case class LeftTurn(angle: Int) extends Command {

  /***
   * Basic apply function to apply left turning angle by angle degrees
   * and returns updated Cursor position
   * @param cursor: Cursor
   * @return Cursor
   */
  override def apply(cursor: Cursor): Cursor = {
    cursor.copy(angle = cursor.angle - this.angle)
  }
}
