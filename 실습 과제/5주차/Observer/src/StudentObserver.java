public class StudentObserver extends Observer {

    public StudentObserver(Teacher teacher, String name) {
        this.teacher = teacher;
        this.name = name;
        this.teacher.attach(this);
    }

    @Override
    public void update() {
        System.out.println(this.name + " 주목!");
    }
}
