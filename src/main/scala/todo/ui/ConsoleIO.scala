package todo.ui

import todo.model.TodoModel

import java.time.LocalDate
import scala.annotation.tailrec
import scala.io.StdIn
import scala.util.{Failure, Success, Try}

object ConsoleIO {

  private var model: TodoModel = TodoModel.empty

  def run(): Unit = {
    println("Welcome to your Todo List!")
    mainMenu()
  }

  // Recursive function for main menu
  @tailrec
  private def mainMenu(): Unit = {
    println("\n--- Main Menu ---")
    println("1. Add task")
    println("2. List all tasks")
    println("3. Update task status")
    println("4. Remove task")
    println("5. Search tasks")
    println("6. Show statistics")
    println("7. Show overdue tasks")
    println("0. Exit")

    print("Choose an option: ")

    StdIn.readLine() match {
      case "1" => addTaskMenu(); mainMenu()
      case "2" => listTasks(); mainMenu()
      case "3" => updateStatusMenu(); mainMenu()
      case "4" => removeTaskMenu(); mainMenu()
      case "5" => searchTasksMenu(); mainMenu()
      case "6" => showStats(); mainMenu()
      case "7" => showOverdueTasks(); mainMenu()
      case "0" => println("Goodbye!")
      case _ =>
        println("Invalid option, please try again.")
        mainMenu()
    }
  }

  private def addTaskMenu(): Unit = {

  }

  private def listTasks(): Unit = {

  }

  private def updateStatusMenu(): Unit = {

  }

  private def removeTaskMenu(): Unit = {

  }

  private def searchTasksMenu(): Unit = {

  }

  private def showStats(): Unit = {

  }

  private def showOverdueTasks(): Unit = {

  }

}