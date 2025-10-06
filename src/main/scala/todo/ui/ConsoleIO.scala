package todo.ui

import todo.model._

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

  private def addTaskMenu(): Unit = {    println("\n--- Add New Task ---")

    print("Title: ")
    val title = StdIn.readLine()

    print("Description: ")
    val description = StdIn.readLine()

    print("Category (work/school/private): ")
    val category = Category.fromString(StdIn.readLine()) match {
      case Some(cat) => cat
      case None =>
        println("Invalid category, using 'private'")
        Category.Private
    }

    print("Deadline (YYYY-MM-DD): ")
    val deadline = Try(LocalDate.parse(StdIn.readLine())) match {
      case Success(date) => date
      case Failure(_) =>
        println("Invalid date, using today's date")
        LocalDate.now()
    }

    model = model.addTask(title, description, category, deadline)
    println(s"Task added successfully! (ID: ${model.nextId - 1})")
  }

  private def listTasks(): Unit = {
    println("\n--- All Tasks ---")
    val tasks = model.getAllTasks

    if (tasks.isEmpty) {
      println("No tasks found.")
    } else {
      tasks.map(formatTask).foreach(println)
    }
  }

  private def updateStatusMenu(): Unit = {
    println("\n--- Update Task Status ---")

    print("Task ID: ")
    val id = Try(StdIn.readLine().toInt).getOrElse(-1)

    model.getTask(id) match {
      case Some(task) =>
        println(s"Current task: ${formatTask(task)}")
        println("New status (open/in-work/finished): ")

        Status.fromString(StdIn.readLine()) match {
          case Some(newStatus) =>
            model = model.updateTaskStatus(id, newStatus)
            println("Task status updated!")
          case None =>
            println("Invalid status")
        }

      case None =>
        println("Task not found")
    }
  }

  private def removeTaskMenu(): Unit = {
    println("\n--- Remove Task ---")

    print("Task ID to remove: ")
    val id = Try(StdIn.readLine().toInt).getOrElse(-1)

    model.getTask(id) match {
      case Some(task) =>
        println(s"Removing: ${formatTask(task)}")
        model = model.removeTask(id)
        println("Task removed!")
      case None =>
        println("Task not found")
    }
  }

  private def searchTasksMenu(): Unit = {
    println("\n--- Search Tasks ---")

    print("Search term: ")
    val term = StdIn.readLine()

    val results = model.searchTasks(term)

    if (results.isEmpty) {
      println("No tasks found matching your search.")
    } else {
      println(s"Found ${results.length} task(s):")
      results.map(formatTask).foreach(println)
    }
  }

  private def showStats(): Unit = {
    println("\n--- Statistics ---")
    val stats = model.getStats

    println(s"Total tasks: ${stats.total}")
    println(s"Open: ${stats.open}")
    println(s"In work: ${stats.inWork}")
    println(s"Finished: ${stats.finished}")
    println(s"Overdue: ${stats.overdue}")
  }

  private def showOverdueTasks(): Unit = {
    println("\n--- Overdue Tasks ---")
    val overdue = model.getOverdueTasks

    if (overdue.isEmpty) {
      println("No overdue tasks! Great job!")
    } else {
      println(s"You have ${overdue.length} overdue task(s):")
      overdue.map(formatTask).foreach(println)
    }
  }

  private def formatTask(task: Task): String = {
    val statusStr = Status.toString(task.status)
    val categoryStr = Category.toString(task.category)
    s"[ID: ${task.id}] ${task.title} - ${task.description} ($categoryStr, $statusStr, due: ${task.deadline})"
  }

}