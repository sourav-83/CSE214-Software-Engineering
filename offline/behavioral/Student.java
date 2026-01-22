import java.util.ArrayList;
import java.util.List;

public class Student {
    public final String id;
    public final String name;
    private final List<Course> enrolledCourses = new ArrayList<>();
    private final List<Course> waitlistedCourses = new ArrayList<>();
    private RegistrationMediator mediator;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public void setMediator(RegistrationMediator mediator) {
        this.mediator = mediator;
    }

    public void enrollIn(Course course) {
        if (mediator != null) {
            mediator.enrollStudent(this, course);
        }
    }

    public void waitlistFor(Course course) {
        if (mediator != null) {
            mediator.addToWaitlist(this, course);
        }
    }

    public void dropCourse(Course course) {
        if (mediator != null) {
            mediator.dropStudent(this, course);
        }
    }

    public boolean isEnrolledIn(Course course) {
        return enrolledCourses.contains(course);
    }

    public boolean isWaitlistedFor(Course course) {
        return waitlistedCourses.contains(course);
    }

    void addEnrolledCourse(Course course) {
        if (!enrolledCourses.contains(course)) {
            enrolledCourses.add(course);
        }
        waitlistedCourses.remove(course);
    }

    void addWaitlistCourse(Course course) {
        if (!waitlistedCourses.contains(course)) {
            waitlistedCourses.add(course);
        }
    }

    void removeCourse(Course course) {
        enrolledCourses.remove(course);
        waitlistedCourses.remove(course);
    }

    public void printSchedule() {
        System.out.println("Schedule for " + name + " (" + id + "):");
        System.out.println("  Enrolled:");
        if (enrolledCourses.isEmpty()) {
            System.out.println("    [none]");
        } else {
            for (Course c : enrolledCourses) {
                System.out.println("    " + c.code + " - " + c.title + " (" + c.status + ")");
            }
        }
        System.out.println("  Waitlisted:");
        if (waitlistedCourses.isEmpty()) {
            System.out.println("    [none]");
        } else {
            for (Course c : waitlistedCourses) {
                System.out.println("    " + c.code + " - " + c.title + " (" + c.status + ")");
            }
        }
    }
}