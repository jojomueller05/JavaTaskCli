import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskList {

    public Task[] Tasks;

    public TaskList(){
        try {
            File jsonFile = new File("data.json");

            if (!jsonFile.exists()) {
                jsonFile.createNewFile();
            }

            String fileContent = Files.readString(Paths.get("data.json"), StandardCharsets.UTF_8);

            if(!fileContent.isEmpty()) {


                Gson gson = new GsonBuilder()
                        .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                        .create();

                Type taskListType = new TypeToken<List<Task>>() {
                }.getType();
                List<Task> list = gson.fromJson(fileContent, taskListType);

                // Initialisiere das Array Tasks
                Tasks = list.toArray(new Task[0]);
            }
        } catch (IOException e){
            System.out.println("Oops! Something went wrong");
            e.printStackTrace();
        }
    }

    public void printAllTasks() {
        if (Tasks != null) {
            for (Task task : Tasks) {
                System.out.println(task);
            }
        } else {
            System.out.println("Keine Tasks vorhanden.");
        }
    }

    public static Task findMaxIdObject(Task[] tasks){
        if (tasks == null || tasks.length == 0){
            return null;
        }
        Task maxIdObject = tasks[0];
        for (Task task : tasks){
            if (task.id > maxIdObject.id){
                maxIdObject = task;
            }
        }
        return maxIdObject;
    }

    public static int findMaxId(Task[] tasks){
        if(tasks == null || tasks.length == 0){
            return 0;
        }
        Task maxIdObject = tasks[0];
        for (Task task : tasks){
            if(task.id > maxIdObject.id){
                maxIdObject = task;
            }
        }
        return maxIdObject.id;
    }

    public void writeToJsonFile(){

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();

        try (FileWriter writer = new FileWriter("data.json")){
             gson.toJson(this.Tasks, writer);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void addTask(String description){
        // get object with biggest id
        int maxIdObj = findMaxId(this.Tasks);

        // add +1 to the obj with biggest id
        int newId = maxIdObj + 1;

        if (this.Tasks == null){
            this.Tasks = new Task[1];

            // create a new Task
            this.Tasks[0] = new Task(newId, description);

        } else {
            // create a new Task
            Task newTask = new Task(newId, description);

            // add new Task to this.Tasks
            Task[] newTaskList = Arrays.copyOf(this.Tasks, this.Tasks.length + 1);
            newTaskList[newTaskList.length - 1] = newTask;

            this.Tasks = newTaskList;
        }
        this.writeToJsonFile();

    }

    public boolean isExistingTaskId(int id){
        for(Task task : this.Tasks){
            if (task.id == id){
                return true;
            }
        }
        return  false;
    }

    public void deleteTask(int id){
        if(this.isExistingTaskId(id)){
            Task[] newTaskArray = Arrays.stream(this.Tasks)
                    .filter(obj -> obj.id != id)
                    .toArray(Task[]::new);

            this.Tasks = newTaskArray;
            this.writeToJsonFile();
        } else {
            System.out.println("There is no task with id " + id);
        }
    }

    public void updateTask(int id, String description){

        if(this.isExistingTaskId(id)){
            for (Task task : this.Tasks){
                if(task.id == id){
                    task.setDescription(description);
                }
            }
        } else {
            System.out.println("There is not Task with id " + id);
        }
    }

    public void updateTaskStatus(int id, Task.Status status){
        if(this.isExistingTaskId(id)){
            for (Task task : this.Tasks){
                if (task.id == id){
                    task.status = status;
                }
            }

            this.writeToJsonFile();
        }
    }

    public void printTasksByStatus(Task.Status status){
        if (this.Tasks == null){
            System.out.println("No Tasks registered!");
        } else {

            for (Task task : this.Tasks){
                if (task.status == status){
                    System.out.println(task);
                }
            }
        }
    }
}
