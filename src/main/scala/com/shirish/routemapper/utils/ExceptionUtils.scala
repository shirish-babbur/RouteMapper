package com.shirish.routemapper.utils

object ExceptionUtils {

  /***
   * Simple Wrapper exception over Exception class for handling invalid command exceptions
   * @param msg: String
   */
  class InvalidCommandException(msg: String) extends Exception(msg)
}
