package br.com.techthor.arraylists.version3;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<TodoItem> todoList = new ArrayList<TodoItem>();

        todoList.add(new TodoItem("Walk the dog", 3));
        todoList.add(new TodoItem("Learn Java", 5));

        for (TodoItem item : todoList) {
            System.out.println(item.getTitle() + " has a priority of " + item.getPriority());
        }
    }
}
