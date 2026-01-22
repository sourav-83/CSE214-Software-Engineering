public abstract class Mediator {
    public abstract boolean enrollStudent(Student student, Course course);
    public abstract boolean addToWaitlist(Student student, Course course);
    public abstract boolean dropStudent(Student student, Course course);
}