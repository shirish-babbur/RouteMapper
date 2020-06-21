package com.shirish.routemapper.services

import com.shirish.routemapper.commands.{Command, JumpBackWard, JumpForWard}
import com.shirish.routemapper.utils.Cursor

/***
 * Simple Executor concrete class which basically takes sequence of commands and executes them one by one
 */
class SimpleExecutor extends Executor {

  /***
   * Simply execute method which applies command one by one and builds the cursor objects
   * based on each of the step performed.
   * @param commands: Seq[Command]
   * @param cursor: Cursor
   * @return Seq[Cursor]
   */
  def execute(commands: Seq[Command], cursor: Cursor): Seq[Cursor] = {
    var cursorObj = cursor
    var route: Seq[Cursor] = Seq(cursor)
    for(command <- commands) {
      cursorObj = command.apply(cursorObj)
      if(!(command.isInstanceOf[JumpForWard] || command.isInstanceOf[JumpBackWard])) {
        route = route :+ cursorObj
      }
    }
    route
  }

}
