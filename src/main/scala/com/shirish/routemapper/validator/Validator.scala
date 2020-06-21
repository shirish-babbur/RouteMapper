package com.shirish.routemapper.validator

import com.shirish.routemapper.utils.Constants._

/***
 * Validator class to validate inputs and command
 */
object Validator {

  /***
   * This will validate the length of command in words.
   * if length is not within valid range throws exception.
   * @param command: String
   */
  def ValidateCommand(command: String): Unit = {
    validateNotEmpty(command)
    val cmdlist = command.split(SPACE_LITERAL)
    if(!command.startsWith(JUMP_COMMAND)) {
      require(cmdlist.length == 2, s"Invalid command at ${command}")
    } else {
      require(cmdlist.length == 3, s"Invalid command at ${command}")
    }
  }

  /***
   * Checks for if passed string is empty or null.
   * @param command: String
   */
  def validateNotEmpty(command: String) = {
    require(command != null && !command.isEmpty, "Command cannot be empty.")
  }

}
