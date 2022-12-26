import java.util.List;
import java.util.ArrayList;

public class Teacher {
    private List<Observer> observers = new ArrayList<>();

    public void attach(Observer observer){
        this.observers.add(observer);
    }

    public void detach(Observer observer){
        this.observers.remove(observer);
    }

    public void notifyObservers(){
        for (Observer observer : observers){
            observer.update();
        }
    }
}
