package com.shirish.routemapper.commands

import com.shirish.routemapper.utils.Cursor

/***
 * All the supported commands will extend this and will be able to implement
 * their own apply method for updating their cursor position.
 */
trait Command {

  /***
   * This method will be implemented by all the child command classes to update
   * cursor position
   * @param cursor: Cursor
   * @return Cursor
   */
  def apply(cursor: Cursor): Cursor

}
