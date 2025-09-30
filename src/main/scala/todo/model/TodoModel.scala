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
}