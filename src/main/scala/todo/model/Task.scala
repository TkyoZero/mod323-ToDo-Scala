package todo.model

import java.time.LocalDate

/**
 * The category of a task
 */
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


/**
 * The status of a task
 */
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


/**
 * Represents a task with all required properties
 */
case class Task(
                 id: Int,
                 title: String,
                 description: String,
                 category: Category,
                 deadline: LocalDate,
                 status: Status
               )

