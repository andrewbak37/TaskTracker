package Todo.Manager;

import Todo.Model.Task;

import java.util.*;
import java.util.function.Function;

public class InMemoryHistoryManager implements HistoryManager {
    private static final int RECENT_TASKS_COUNT = 10;
    private final LinkedUniqList<Task, Task> historyList;

    InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();

    public InMemoryHistoryManager() {

        this.historyList = new LinkedUniqList<>(new Function<Task, Task>() {
            @Override
            public Task apply(Task task) {
                return task;
            }
        });
    }

    private static final class GetTaskIdFunction implements Function<Task, Task> {
        @Override
        public Task apply(Task task) {
            return task;
        }
    }

    public InMemoryTaskManager getInMemoryTaskManager() {
        return inMemoryTaskManager;
    }

    public void setInMemoryTaskManager(InMemoryTaskManager inMemoryTaskManager) {
        this.inMemoryTaskManager = inMemoryTaskManager;
    }

    public List<Task> getRecentTasks() {
        return historyList.asUnmodifiableList();
    }

    @Override
    public void add(Task task) {
        historyList.addFirst(task);
        if (historyList.size() == RECENT_TASKS_COUNT + 1) {
            historyList.removeLast();
        }
    }

    @Override
    public void remove(Task task) {
        historyList.remove(task);
    }

    @Override
    public List<Task> getHistory() {
        return getRecentTasks();
    }

    private static class LinkedUniqList<E, TEKey> {
        private final LinkedList<E> list = new LinkedList<>();
        private final Map<TEKey, Node<E>> map = new HashMap<>();
        private final Function<E, TEKey> keyFunc;

        private LinkedUniqList(Function<E, TEKey> keyFunc) {
            this.keyFunc = keyFunc;
        }

        private void addFirst(E value) {
            TEKey key = keyFunc.apply(value);
            if (map.containsKey(key)) {
                list.remove(map.get(key));
            }
            map.put(key, list.addFirst(value));
        }

        private int size() {
            return list.size();
        }

        private boolean contains(E value) {
            return map.containsKey(keyFunc.apply(value));
        }

        private void remove(E value) {
            if (!contains(value)) {
                return;
            }
            TEKey key = keyFunc.apply(value);
            list.remove(map.get(key));
            map.remove(key);
        }

        private void removeLast() {
            E removedElement = list.removeLast();
            if (removedElement != null) {
                map.remove(keyFunc.apply(removedElement));
            }
        }

        private List<E> asUnmodifiableList() {
            List<E> list = new ArrayList<>(this.list.size());
            this.list.iterator().forEachRemaining(list::add);
            return Collections.unmodifiableList(list);

        }

    }

    private static class LinkedList<E> implements Iterable<E> {
        private Node<E> head = null;
        private Node<E> tail = null;
        private int size = 0;

        private LinkedList() {
        }

        private Node<E> addFirst(E value) {
            Node<E> node = new Node<>(value);
            if (head == null) {
                head = node;
                tail = node;
            } else {
                node.setNext(head);
                head.setPrev(node);
                head = node;
            }
            ++size;
            return node;
        }

        private E removeLast() {
            E value = tail == null ? null : tail.getValue();
            remove(tail);
            return value;
        }

        private void remove(Node<E> value) {
            if (value == head) {
                head.setNext(null);
                if (value != tail) {
                    head.getNext().setPrev(null);
                } else {
                    tail = null;
                }
                head = head.getNext();
            } else if (value == tail) {
                tail.setPrev(null);
                tail.getPrev().setNext(null);
                tail = tail.getPrev();
            } else {
                value.getPrev().setNext(value.getNext());
                value.getNext().setPrev(value.getPrev());
                value.setPrev(null);
                value.setNext(null);
            }

            --size;
        }

        private int size() {
            return size;
        }

        @Override
        public Iterator<E> iterator() {
            return new Iterator<>() {
                private Node<E> node = head;

                @Override
                public boolean hasNext() {
                    return node != null;
                }

                @Override
                public E next() {
                    E value = node.getValue();
                    node = node.getNext();
                    return value;
                }
            };
        }
    }

    private static class Node<E> {
        private final E value;
        private Node<E> prev;
        private Node<E> next;

        private Node(E value) {
            this.value = value;
        }

        public E getValue() {
            return value;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }
}
