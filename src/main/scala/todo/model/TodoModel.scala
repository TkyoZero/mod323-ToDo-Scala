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
}