# ToDo with Scala
## Members
- Jussif Abdel-Rahman
- Tenzin Tobatsang

## Structure
````
mod323-ToDo-Scala/
├── src/
│   └── main/
│       └── scala/
│           └── todo/
│               ├── Main.scala              # Entry point
│               ├── ui/
│               │   └── ConsoleIO.scala     # All UI logic
│               └── model/                  # Pure data models only
│                   ├── TodoModel.scala
│                   └── Task.scala
├── build.sbt
└── README.md
````

----

## Features
- Add, update, and remove tasks
- Search tasks by title or descriptio
- Filter by status (Open, In Work, Finished
- Track tasks by category (Work, School, Private
- View statistics and overdue task
- Set deadlines for tasks
---
## Quick Start

Clone and compile:
```bash
sbt compile
```

Run the application:
```bash
sbt run
```

Follow the menu to manage your tasks!
