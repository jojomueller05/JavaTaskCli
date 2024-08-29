//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        TaskList tasks = new TaskList();

        switch (args[0]){
            case "add":
                if(args.length == 2){

                    tasks.addTask(args[1]);
                } else {
                    System.out.println("Invalid amount of arguments!");
                }
                break;
            case "delete":
                if (args.length == 2) {
                    try {
                        int number = Integer.valueOf(args[1]);

                        tasks.deleteTask(number);
                    } catch (NumberFormatException e) {
                        System.out.println("Second argument must be a number!");
                    }
                } else {
                    System.out.println("Invalid amount of arguments!");
                }
                break;
            case "list":
                if(args.length == 1){

                    tasks.printAllTasks();
                } else if ("done".equals(args[1])) {
                    Task.Status done = Task.Status.DONE;
                    tasks.printTasksByStatus(done);
                } else if ("todo".equals(args[1])) {
                    Task.Status todo = Task.Status.TODO;
                    tasks.printTasksByStatus(todo);
                } else if ("in-progress".equals(args[1])) {
                    Task.Status progress = Task.Status.INPROGRESS;
                    tasks.printTasksByStatus(progress);
                } else {
                    System.out.println("Invalid arguments!");
                }

                break;
            case "update":
                if (args.length == 2){

                    try {

                        int number = Integer.valueOf(args[1]);
                        tasks.updateTask(number, args[2]);

                    } catch (NumberFormatException e){
                        System.out.println("Second argument must be a number!");
                    }

                } else {
                    System.out.println("Invalid amount of arguments!");
                }
                break;
            case "mark-in-progress":
                if(args.length == 2){
                    Task.Status status = Task.Status.INPROGRESS;
                    try {
                        int number = Integer.valueOf(args[1]);
                        tasks.updateTaskStatus(number, status);

                    } catch (NumberFormatException e){
                        System.out.println("Second argument must be a number!");
                    }
                } else {
                    System.out.println("Invalid amount of arguments!");
                }
                break;
            case "mark-done":
                if(args.length == 2){
                    Task.Status status = Task.Status.DONE;
                    try {
                        int number = Integer.valueOf(args[1]);
                        tasks.updateTaskStatus(number, status);
                    } catch (NumberFormatException e){
                        System.out.println("Second argument must be a number!");
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Invalid amount of arguments!");
                }
                break;
            case "mark-todo":
                if (args.length == 2){
                    Task.Status status = Task.Status.TODO;
                    try {
                        int number = Integer.valueOf(args[1]);
                        tasks.updateTaskStatus(number, status);
                    } catch (NumberFormatException e){
                        System.out.println("Secound argument must be a number!");
                    }
                } else {
                    System.out.println("Invalid amount of arguments");
                }
                break;
        }

    }
}