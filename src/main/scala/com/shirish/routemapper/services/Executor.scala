package com.shirish.routemapper.services

import com.shirish.routemapper.commands.Command
import com.shirish.routemapper.utils.Cursor

/***
 * Executor trait which simply takes seq of commands and executes them
 * one by one serially. This can be extended to have more complex
 * execution of commands and different orderings
 */
trait Executor {

  /***
   * Method which is implemented by SimpleExecutor class to apply
   * commands passed one by one.
   * @param commands: Seq[Command]
   * @param cursor: Cursor
   * @return Seq[Cursor]
   */
  def execute(commands: Seq[Command], cursor: Cursor): Seq[Cursor]

}
