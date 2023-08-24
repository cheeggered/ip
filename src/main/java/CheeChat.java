import java.util.ArrayList;
import java.util.Scanner;
public class CheeChat {


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm CheeChat");
        System.out.println("What can I do for you?");
        Scanner input = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        String cmd = input.nextLine();

        while(!cmd.equals("bye")){
            int counter = tasks.size();
            if (cmd.equals("list")) {
                for (int x = 0; x < counter; x++) {
                    int index = x + 1;
                    System.out.println(index + "." + tasks.get(x).toString());
                }
            } else {
                if (cmd.startsWith("mark")) {
                    int index = cmd.length() - 1;
                    char c = cmd.charAt(index);
                    int number = c - 48 - 1;
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks.get(number).description(0));
                } else if (cmd.startsWith("unmark")) {
                    int index = cmd.length() - 1;
                    char c = cmd.charAt(index);
                    int number = c - 48 - 1;
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + tasks.get(number).description(1));
                } else {
                    if (cmd.startsWith("todo")) {
                        try {
                            if (cmd.substring(4).isEmpty()) {
                                throw new CheeException("OOPS!!! The description of a todo cannot be empty.");
                            }
                            System.out.println("Got it. I've added this task:");
                            String desc = cmd.substring(4);
                            Task instance = new Todo(desc);
                            tasks.add(instance);
                            System.out.println("  " + instance);
                            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        } catch (CheeException e){
                            System.out.println(e.getMessage());
                        }
                    } else if (cmd.startsWith("deadline")) {
                        System.out.println("Got it. I've added this task:");
                        int index = cmd.indexOf(47);
                        String description = cmd.substring(8, index - 1);
                        String time = cmd.substring(index + 4);
                        Task instance = new Deadline(description, time);
                        tasks.add(instance);
                        System.out.println("  " + instance);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    } else if (cmd.startsWith("event")){
                        System.out.println("Got it. I've added this task:");
                        int index1 = cmd.indexOf(47);
                        String description = cmd.substring(5, index1 - 1);
                        String duration = cmd.substring(index1 + 5);
                        int index2 = duration.indexOf(47);
                        String from = duration.substring(0, index2);
                        String to = duration.substring(index2 + 3);
                        Task instance = new Event(description, from, to);
                        tasks.add(instance);
                        System.out.println("  " + instance);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    } else {
                        try {
                            throw new CheeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                        } catch (CheeException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }
            cmd = input.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");

    }
}

