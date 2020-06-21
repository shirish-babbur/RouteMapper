package com.shirish.routemapper

import com.shirish.routemapper.services.{CommandParser, Executor, SimpleExecutor}
import com.shirish.routemapper.utils.{Cursor, Utils}

object RouteMapperApplication extends App {
  private val parser: CommandParser = new CommandParser()
  private val executor: Executor = new SimpleExecutor()

  /***
   * Simple Run method which basically provides switch interface over commandline
   */
  def run(): Unit = {
    println("Welcome to Route Mapper Application!")
    var exit = false
    while(!exit) {
      printUsage()
      try {
        val choice = scala.io.StdIn.readInt()
        choice match {
          case 1 =>
            try {
              println("Enter comma separated commands to map route:")
              val input = scala.io.StdIn.readLine()
              val route = executor.execute(parser.parse(input), Utils.getDefaultCursor)
              println("Printing cursor positions at each step:")
              printRoute(route)
            } catch {
              case x: Throwable =>
                println(s" Exception occurred due to : ${x.getMessage}")
            }
          case 2 => exit = true
          case _ => println("Invalid Choice")
        }
      } catch {
        case x: Throwable => println("Invalid Input. Enter Number only.")
      }
    }
  }

  /***
   * Prints usage of the application
   */
  def printUsage(): Unit = {
    println(
      """
        |Use following Numbers to do operations
        |1 - Entering route commands and print route
        |2 - exit
        |""".stripMargin)
  }

  /***
   * Prints the cursor positions travelled after applying given user commands
   * @param route: Seq[Cursor]
   */
  def printRoute(route: Seq[Cursor]): Unit = {
    for(cursor <- route) {
      println(s"Cursor at (${cursor.point.x}, ${cursor.point.y}) and direction at ${cursor.angle % 360 } degrees")
    }
  }

  run()
}
