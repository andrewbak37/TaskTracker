package Todo;

import Todo.Manager.InMemoryTaskManager;
import Todo.Model.Epic;
import Todo.Model.Subtask;
import Todo.Model.Task;
import Todo.Manager.TaskManager;
import Todo.Model.Status;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new InMemoryTaskManager();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                printMenu();
                int command = scanner.nextInt();
                switch (command) {
                    case 1:
                        System.out.println("Введите название задачи");
                        String name = scanner.next();
                        System.out.println("Введите описание");
                        String description = scanner.next();
                        System.out.println("Выберите вид задачи");
                        System.out.println("Task, Epic, Subtask");
                        String type = scanner.next();
                        switch (type) {
                            case "Task":
                                taskManager.addTask(new Task(name, description, Status.NEW));
                                break;
                            case "Epic":
                                taskManager.addEpic(new Epic(name, description, Status.NEW));
                                break;
                            case "Subtask":
                                System.out.println("В какой эпик вы хотите добавить подзадачу");
                                System.out.println("Доступные эпики");
                                System.out.println(taskManager.getAllEpicsKey());
                                long epicId = scanner.nextLong();
                                taskManager.addSubtask(new Subtask(name, description, Status.NEW, epicId));
                                break;
                        }
                        break;
                    case 2:
                        System.out.println(taskManager.getAllTasks());
                        System.out.println(taskManager.getAllEpics());
                        System.out.println(taskManager.getAllSubtasks());
                        break;
                    case 3:
                        System.out.println(taskManager.getAllEpics());
                        break;
                    case 4:
                        System.out.println("Введите номер определенного эпика");
                        long epicId2 = scanner.nextLong();
                        System.out.println(taskManager.getSubtasksByEpicId(epicId2));
                        break;
                    case 5:
                        System.out.println("Выберите вид задачи");
                        System.out.println("Task, Epic, Subtask");
                        String type5 = scanner.next();
                        switch (type5) {
                            case "Task":
                                System.out.println("Таски");
                                System.out.println(taskManager.getAllTasksKey());
                                System.out.println("Введите номер задачи");
                                long taskId = scanner.nextLong();
                                System.out.println(taskManager.getTaskById(taskId));
                                break;
                            case "Epic":
                                System.out.println("Эпики");
                                System.out.println(taskManager.getAllEpicsKey());
                                System.out.println("Введите номер задачи");
                                long epicId3 = scanner.nextLong();
                                System.out.println(taskManager.getEpicById(epicId3));
                                break;
                            case "Subtask":
                                System.out.println("Введите номер задачи");
                                long subtaskId = scanner.nextLong();
                                System.out.println("Сабтаски");
                                System.out.println(taskManager.getAllSubtasksKey());
                                System.out.println(taskManager.getSubtaskById(subtaskId));
                                break;
                            default:
                                System.out.println("Такого вида задачи нет");
                                break;
                        }
                        break;
                    case 6:
                        System.out.println("Введите название новой задачи");
                        String name2 = scanner.next();
                        System.out.println("Введите описание");
                        String description2 = scanner.next();
                        System.out.println("Выберите вид задачи");
                        System.out.println("Task, Epic, Subtask");
                        String type2 = scanner.next();
                        switch (type2) {
                            case "Task":
                                System.out.println("Введите номер задачи");
                                long taskId = scanner.nextLong();
                                System.out.println("Введите статус");
                                Status statusTask = Status.valueOf(scanner.next());
                                taskManager.updateTaskById(new Task(name2, description2, statusTask), taskId);
                                break;
                            case "Epic":
                                System.out.println("Введите номер задачи");
                                long epicIdUpdate = scanner.nextLong();
                                taskManager.updateEpicById(new Epic(name2, description2, Status.NEW), epicIdUpdate);
                                break;
                            case "Subtask":
                                System.out.println("Сабтаск какого эпика вы хотите изменить");
                                System.out.println("Доступные эпики");
                                System.out.println(taskManager.getAllEpicsKey());
                                System.out.println("Доступные сабтаски");
                                System.out.println(taskManager.getAllSubtasksKey());
                                System.out.println("Эпик");
                                long idEpicBySub = scanner.nextLong();
                                System.out.println("Сабтаска");
                                System.out.println("Введите статус");
                                Status status = Status.valueOf(scanner.next());
                                taskManager.updateSubtaskById(new Subtask(name2, description2, status, idEpicBySub), taskManager.getEpicById(idEpicBySub));
                                break;
                        }
                        break;
                    case 7:
                        System.out.println("Увалить все - 1, удалить по номеру - 2");
                        int numb2 = scanner.nextInt();
                        if (numb2 == 1) {
                            taskManager.deleteAll();
                        } else if (numb2 == 2) {
                            System.out.println("Введите вид задачи - Task, Epic или Subtask");
                            String type3 = scanner.next();
                            switch (type3) {
                                case "Task":
                                    System.out.println("Введите номер задачи которую хотите удалить");
                                    long numTaskId = scanner.nextLong();
                                    taskManager.deleteTaskId(numTaskId);
                                    break;
                                case "Epic":
                                    System.out.println("Введите номер задачи которую хотите удалить");
                                    long numEpicId = scanner.nextLong();
                                    taskManager.deleteEpicId(numEpicId);
                                    break;
                                case "Subtask":
                                    System.out.println("Сабтаск какого эпика вы хотите удалить?");
                                    System.out.println("Доступные эпики");
                                    System.out.println(taskManager.getAllEpicsKey());
                                    System.out.println("Доступные сабтаски");
                                    System.out.println(taskManager.getAllSubtasksKey());
                                    System.out.println("Введите эпик");
                                    long idEpicByDelete = scanner.nextLong();
                                    System.out.println("Введите номер задачи(Сабтаски) которую хотите удалить");
                                    long numSubtaskId = scanner.nextLong();
                                    taskManager.deleteSubtaskId(taskManager.getEpicById(idEpicByDelete), numSubtaskId);
                                    break;
                            }
                        }
                        break;
                    case 8:
                        System.out.println(taskManager.history());
                        break;

                    case 0:
                        System.out.println("До встречи");
                        break;
                    default:
                }
            } catch (Exception e) {
                System.out.println("Такой команды нет");
                break;
            }
        }
    }

    public static void printMenu() {
        System.out.println("Что вы хотите сделать? ");
        System.out.println("1 - Ввести новую задачу");
        System.out.println("2 - Получение списка всех задач");
        System.out.println("3 - Получение списка всех эпиков");
        System.out.println("4 - Получение списка всех подзадач определённого эпика");
        System.out.println("5 - Получение задачи любого типа по идентификатору");
        System.out.println("6 - Обновление задачи любого типа по идентификатору");
        System.out.println("7 - Удаление задач — всех и по идентификатору");
        System.out.println("8 - Показать историю полученных задач");
        System.out.println("0 - Выйти из приложения");
    }
}
