package todo

import todo.ui.ConsoleIO

object Main {
  def main(args: Array[String]): Unit = {
    println("=== ConsoleIO Todo App ===")
    ConsoleIO.run()
  }
}