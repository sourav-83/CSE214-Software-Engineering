import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

public class RegistrarSystem {
    private final Map<String, Student> students = new HashMap<>();
    private final Map<String, Course> courses = new HashMap<>();
    private final RegistrationMediator mediator = new RegistrationMediator();

    public void addStudent(Student s) {
        if (s != null) {
            s.setMediator(mediator);
            students.put(s.id, s);
        }
    }

    public void addCourse(Course c) {
        if (c != null) {
            c.setMediator(mediator);
            courses.put(c.code, c);
        }
    }

    public Student getStudent(String id) {
        return students.get(id);
    }

    public Course getCourse(String code) {
        return courses.get(code);
    }

    public Collection<Student> getAllStudents() {
        return students.values();
    }

    public Collection<Course> getAllCourses() {
        return courses.values();
    }
    
    public RegistrationMediator getMediator() {
        return mediator;
    }
}