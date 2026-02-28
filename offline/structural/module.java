import java.util.ArrayList;
import java.util.List;


public class module implements component {
    private String name;
    private List<component> courses;

    public module(String name) {
        this.name = name;
        this.courses = new ArrayList<>();
    }

    public void addCourse(component course) {
        courses.add(course);
    }

    public void removeCourse(component course) {
        courses.remove(course);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        double total = 0;
        for (component course : courses) {
            total += course.getPrice();
        }
        return total;
    }

    @Override
    public double getDuration() {
        double totalDuration = 0;
        for (component course : courses) {
            totalDuration += course.getDuration();
        }
        return totalDuration;
    }

    @Override
    public void display(String indent) {
        System.out.printf("%sModule: %s | Duration: %.2f hrs | Price: $%.2f%n", indent, name, getDuration(), getPrice());
        for (component course : courses) {
            course.display(indent + "  ");
        }
    }
}