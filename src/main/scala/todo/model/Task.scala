package todo.model

/**
 * Represents the category of a task
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