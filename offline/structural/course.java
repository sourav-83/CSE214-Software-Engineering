import java.util.ArrayList;
import java.util.List;

public class course implements component {
    private String name;
    private List<component> lessons;
    

    public course(String name) {
        this.name = name;
        
        this.lessons = new ArrayList<>();
    }

    public void addLesson(component lesson) {
        lessons.add(lesson);
    }

    public void removeLesson(component lesson) {
        lessons.remove(lesson);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        double total = 0;
        for (component lesson : lessons) {
            total += lesson.getPrice();
        }
        return total;
    }

    @Override
    public double getDuration() {
        double totalDuration = 0;
        for (component lesson : lessons) {
            totalDuration += lesson.getDuration();
        }
        return totalDuration;
    }

    @Override
    public void display(String indent) {
        System.out.printf("%sCourse: %s | Duration: %.2f hrs | Price: $%.2f%n", indent, name, getDuration(), getPrice());
        for (component lesson : lessons) {
            lesson.display(indent + "  ");
        }
    }
}