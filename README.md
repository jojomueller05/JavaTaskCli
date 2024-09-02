# Java Task CLI

*Inspired by [roadmap.sh](https://roadmap.sh/projects/task-tracker)*

`Java Task CLI` is a command line task tracker written in Java.
I've used the community edition of IntelliJ IDEA for writing this
program. IntelliJ IDEA Community Edition is free and
probably the easiest way to get started with Java.
You can download it [here](https://www.jetbrains.com/idea/download/?section=windows).


# Usage

1. Clone this repository
2. Make sure java is installed
3. Create an alias called `task-cli` for `java -jar <pathTo./out/artifacts/JavaCli_jar/JavaCli.jar`

**Command Line Arguments**

```shell
# Adding a new task
task-cli add "Buy groceries"
# Output: Task added successfully (ID: 1)

# Updating and deleting tasks
task-cli update 1 "Buy groceries and cook dinner"
task-cli delete 1

# Marking a task as in progress or done
task-cli mark-in-progress 1
task-cli mark-done 1

# Listing all tasks
task-cli list

# Listing tasks by status
task-cli list done
task-cli list todo
task-cli list in-progress
```

# Disclaimer

This code is definitely NOT perfect. There is probably
a better way to handle arguments from the cli. 