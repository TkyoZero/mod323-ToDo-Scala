package todo.model

import java.time.LocalDate

// The category of a task
sealed trait Category
object Category {
  case object Work extends Category
  case object School extends Category
  case object Private extends Category

  def fromString(str: String): Option[Category] = str.toLowerCase match {
    case "work" => Some(Work)
    case "school" => Some(School)
    case "private" => Some(Private)
    case _ => None
  }

  def toString(category: Category): String = category match {
    case Work => "work"
    case School => "school"
    case Private => "private"
  }
}


// The status of a task
sealed trait Status
object Status {
  case object Open extends Status
  case object InWork extends Status
  case object Finished extends Status

  def fromString(str: String): Option[Status] = str.toLowerCase match {
    case "open" => Some(Open)
    case "in-work" | "inwork" => Some(InWork)
    case "finished" => Some(Finished)
    case _ => None
  }

  def toString(status: Status): String = status match {
    case Open => "open"
    case InWork => "in-work"
    case Finished => "finished"
  }
}


// A task with all required properties
case class Task(
                 id: Int,
                 title: String,
                 description: String,
                 category: Category,
                 deadline: LocalDate,
                 status: Status
               )

// Helper functions for Task
object Task {
  // Creates a new task with default status as Open
  def create(
              id: Int,
              title: String,
              description: String,
              category: Category,
              deadline: LocalDate
            ): Task = Task(id, title, description, category, deadline, Status.Open)

  // Updates the status of a task
  def updateStatus(task: Task, newStatus: Status): Task =
    task.copy(status = newStatus)

  // Updates the properties of a task
  def update(
              task: Task,
              title: Option[String] = None,
              description: Option[String] = None,
              category: Option[Category] = None,
              deadline: Option[LocalDate] = None,
              status: Option[Status] = None
            ): Task = task.copy(
    title = title.getOrElse(task.title),
    description = description.getOrElse(task.description),
    category = category.getOrElse(task.category),
    deadline = deadline.getOrElse(task.deadline),
    status = status.getOrElse(task.status)
  )
}