public class ObserverDemo {
    public static void main(String[] args) {
        Teacher teacher = new Teacher();

        Observer gyeongEun = new StudentObserver(teacher, "GyeongEun");
        Observer hanSol = new StudentObserver(teacher, "HanSol");
        Observer jaeSang = new StudentObserver(teacher, "JaeSang");

        System.out.println("----------3 students----------");
        teacher.notifyObservers();

        System.out.println();
        System.out.println("----------detached 1 student----------");
        teacher.detach(gyeongEun);
        teacher.notifyObservers();
    }
}
