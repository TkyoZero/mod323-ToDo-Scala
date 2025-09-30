package todo.model

import java.time.LocalDate

case class TodoModel(tasks: List[Task], nextId: Int) {
  def addTask(
               title: String,
               description: String,
               category: Category,
               deadline: LocalDate
             ): TodoModel = {
    val newTask = Task.create(nextId, title, description, category, deadline)
    TodoModel(newTask :: tasks, nextId + 1)
  }

  def updateTask(
                  id: Int,
                  title: Option[String] = None,
                  description: Option[String] = None,
                  category: Option[Category] = None,
                  deadline: Option[LocalDate] = None,
                  status: Option[Status] = None
                ): TodoModel = {
    val updatedTasks = tasks.map { task =>
      if (task.id == id) {
        Task.update(task, title, description, category, deadline, status)
      } else {
        task
      }
    }
    TodoModel(updatedTasks, nextId)
  }

  def updateTaskStatus(id: Int, status: Status): TodoModel = {
    updateTask(id, status = Some(status))
  }

  def removeTask(id: Int): TodoModel = {
    TodoModel(tasks.filterNot(_.id == id), nextId)
  }

  def getTask(id: Int): Option[Task] = {
    tasks.find(_.id == id)
  }

  def getAllTasks: List[Task] = {
    tasks.sortBy(_.id)
  }

  def getTasksByCategory(category: Category): List[Task] = {
    tasks.filter(_.category == category).sortBy(_.id)
  }

  def getTasksByStatus(status: Status): List[Task] = {
    tasks.filter(_.status == status).sortBy(_.id)
  }

  def getOverdueTasks: List[Task] = {
    val today = LocalDate.now()
    tasks.filter(task => task.deadline.isBefore(today) && task.status != Status.Finished).sortWith((a: Task, b: Task) => a.deadline.isBefore(b.deadline))
  }

  def getTasksDueWithin(days: Int): List[Task] = {
    val futureDate = LocalDate.now().plusDays(days)
    tasks.filter(task =>
      !task.deadline.isAfter(futureDate) &&
        task.status != Status.Finished
    ).sortWith((a: Task, b: Task) => a.deadline.isBefore(b.deadline))
  }

  def searchTasks(searchTerm: String): List[Task] = {
    val lowerSearchTerm = searchTerm.toLowerCase
    tasks.filter { task =>
      task.title.toLowerCase.contains(lowerSearchTerm) ||
        task.description.toLowerCase.contains(lowerSearchTerm)
    }.sortBy(_.id)
  }
  
  def getStats: TaskStats = {
    val total = tasks.length
    val open = tasks.count(_.status == Status.Open)
    val inWork = tasks.count(_.status == Status.InWork)
    val finished = tasks.count(_.status == Status.Finished)
    val overdue = getOverdueTasks.length

    TaskStats(total, open, inWork, finished, overdue)
  }
}

object TodoModel {
  /**
   * Creates an empty TodoModel
   */
  def empty: TodoModel = TodoModel(List.empty, 1)

  /**
   * Creates a TodoModel with initial tasks
   */
  def withTasks(tasks: List[Task]): TodoModel = {
    val maxId = if (tasks.isEmpty) 0 else tasks.map(_.id).max
    TodoModel(tasks, maxId + 1)
  }
}

/**
 * Case class to hold task statistics
 */
case class TaskStats(
                      total: Int,
                      open: Int,
                      inWork: Int,
                      finished: Int,
                      overdue: Int
                    )