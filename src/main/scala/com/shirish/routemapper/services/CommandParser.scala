package com.shirish.routemapper.services

import com.shirish.routemapper.commands._
import com.shirish.routemapper.utils.Constants._
import com.shirish.routemapper.utils.ExceptionUtils.InvalidCommandException
import com.shirish.routemapper.utils.Utils
import com.shirish.routemapper.validator.Validator

/***
 * This is simple Command Parser class with exposed method parse
 * and other helper functions
 */
class CommandParser {

  /***
   * Function which parses given input string with comma separated commands
   * e.g. 'FD 100, BK 100' as input returns sequence of command objects
   * @param commandString: String
   * @return Seq[Command]
   */
  def parse(commandString: String): Seq[Command] = {
    var commands: Seq[Command] = Seq()
    var cmdString = commandString.trim
    while (cmdString.length > 0) {
      cmdString = cmdString.trim
      if (!cmdString.startsWith(REPEAT_COMMAND)) {
        val(currentCommand, nextCommand) = splitCommands(cmdString)
        val cmd = getCommand(currentCommand)
        if (cmd != null) commands = commands :+ cmd
        cmdString = nextCommand
      } else {
        val subCommands = getSubCommands(cmdString)
        val repeats = getRepeats(cmdString)
        for (i <- 1 to repeats) {
          commands = commands ++ parse(subCommands)
        }
        val commandList = cmdString.split(ESCAPED_CLOSING_BRACE, 2)
        val splitCmds = splitCommands(commandList(1))
        cmdString = splitCmds._2
      }
    }
    commands
  }

  /**
   * Splits the command string into first command and rest of the commands
   * @param cmdString: String
   * @return (String, String)
   */
  private def splitCommands(cmdString: String): (String, String) = {
    var currentCommand = ""
    var nextCommands = ""
    val commandList = cmdString.split(COMMA, 2)
    if (commandList.length == 1){
      currentCommand = commandList(0).trim
      nextCommands = ""
    } else {
      currentCommand = commandList(0).trim
      nextCommands = commandList(1).trim
    }
    (currentCommand, nextCommands)
  }

  /***
   * Gets the subset of commands that needs to be expanded for specified no of times
   * @param cmdString: String
   * @return String
   */
  private def getSubCommands(cmdString: String): String = {
    val openingBrace = cmdString.indexOf(OPENING_BRACE)
    val closingBrace = cmdString.indexOf(CLOSING_BRACE)
    if (openingBrace == -1 || closingBrace == -1) throw new InvalidCommandException("Command must be enclosed in braces")
    cmdString.substring(openingBrace + 1, closingBrace).trim
  }

  /***
   * Parses the REPEAT command string and gets the no of times we need to repeat the said commands
   * @param cmdString: String
   * @return Int
   */
  private def getRepeats(cmdString: String): Int = {
    val openingBrace = cmdString.indexOf(OPENING_BRACE)
    if(openingBrace == -1) throw new InvalidCommandException("Command must be enclosed in braces")
    val repeatCommand = cmdString.substring(0, openingBrace).trim
    val cmds = repeatCommand.split(SPACE_LITERAL)
    if (cmds.length > 1){
      Utils.getDigit(cmds(1))
    } else {
      throw new InvalidCommandException("Command must have repeat number")
    }
  }

  /***
   * Gives out Command object based on the command type string passed
   * @param command: String
   * @return Command
   */
  private def getCommand(command: String): Command = {
    var cmd: Command = null
    Validator.ValidateCommand(command)
    val words = command.trim.split(SPACE_LITERAL)
    cmd = if (words.length > 1) {
      if (words(0) == FD_COMMAND) {
        Forward(words(1).toInt)
      } else if (words(0) == BK_COMMAND) {
        Backward(words(1).toInt)
      } else if (words(0) == RT_COMMAND) {
        RightTurn(words(1).toInt)
      } else if (words(0) == LT_COMMAND) {
        LeftTurn(words(1).toInt)
      } else if (words(0) == JUMP_COMMAND && words(1) == FD_COMMAND) {
        JumpForWard(words(2).toInt)
      } else if (words(0) == JUMP_COMMAND && words(1) == BK_COMMAND){
        JumpBackWard(words(2).toInt)
      } else {
        throw new InvalidCommandException("Invalid command.")
      }
    } else {
      null
    }
    cmd
  }
}
