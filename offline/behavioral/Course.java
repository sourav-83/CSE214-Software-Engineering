import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Course {
    public final String code;
    public final String title;
    private int capacity;
    public CourseStatus status;
    private final List<Student> enrolled = new ArrayList<>();
    private final LinkedList<Student> waitlist = new LinkedList<>();
    private CourseState state;
    private RegistrationMediator mediator;

    public Course(String code, String title, int capacity, CourseStatus status) {
        this.code = code;
        this.title = title;
        this.capacity = Math.max(0, capacity);
        this.status = status;
        
        switch (status) {
            case DRAFT:
                this.state = new DraftState();
                break;
            case OPEN:
                this.state = new OpenState();
                break;
            case FULL:
                this.state = new FullState();
                break;
            case CLOSED:
                this.state = new ClosedState();
                break;
            case CANCELLED:
                this.state = new CancelledState();
                break;
            default:
                this.state = new DraftState();
        }
    }
    
    public void setMediator(RegistrationMediator mediator) {
        this.mediator = mediator;
    }
    

    public String getCode() { return code; }
    public String getTitle() { return title; }
    public int getCapacity() { return capacity; }
    public int getEnrolledCount() { return enrolled.size(); }
    public int getWaitlistCount() { return waitlist.size(); }
    public List<Student> getEnrolled() { return enrolled; }
    public LinkedList<Student> getWaitlist() { return waitlist; }
    

    boolean isAlreadyEnrolled(Student s) {
        return enrolled.contains(s);
    }
    
    boolean isOnWaitlist(Student s) {
        return waitlist.contains(s);
    }
    

    void addToEnrolled(Student s) {
        enrolled.add(s);
    }
    
    void addToWaitlistInternal(Student s) {
        waitlist.add(s);
    }
    
    void removeFromEnrolled(Student s) {
        enrolled.remove(s);
    }
    
    void removeFromWaitlist(Student s) {
        waitlist.remove(s);
    }
    
    Student pollFromWaitlist() {
        return waitlist.poll();
    }
    
    void setState(CourseState newState) {
        this.state = newState;
    }
    
    void setCapacityInternal(int newCapacity) {
        this.capacity = newCapacity;
    }
    
    void cancelCourse() {
        status = CourseStatus.CANCELLED;
        state = new CancelledState();
        
        if (mediator != null) {
            for (Student s : new ArrayList<>(enrolled)) {
                mediator.removeStudentDirect(s, this);
            }
            for (Student s : new ArrayList<>(waitlist)) {
                mediator.removeStudentDirect(s, this);
            }
        }
        
        enrolled.clear();
        waitlist.clear();
        System.out.println(code + " has been CANCELLED. All students dropped and waitlist cleared.");
    }
    
    void closeWithRandomWaitlistSelection(int targetCapacity) {
        status = CourseStatus.CLOSED;
        state = new ClosedState();
        System.out.println(code + " transitioned FULL -> CLOSED");
        
        if (!waitlist.isEmpty() && mediator != null) {
            int availableSlots = targetCapacity - enrolled.size();
            if (availableSlots > 0) {
                Random random = new Random();
                List<Student> waitlistCopy = new ArrayList<>(waitlist);
                int promotionCount = Math.min(availableSlots, waitlistCopy.size());
                
                System.out.println("Randomly selecting " + promotionCount + " student(s) from waitlist:");
                for (int i = 0; i < promotionCount; i++) {
                    int randomIndex = random.nextInt(waitlistCopy.size());
                    Student promoted = waitlistCopy.remove(randomIndex);
                    waitlist.remove(promoted);
                    mediator.enrollStudentDirect(promoted, this);
                    System.out.println("  Randomly selected: " + promoted.name + " for " + code);
                }
            }
        }
    }
    
    public boolean isVisibleToStudents() {
        return state.isVisibleToStudents();
    }

    public boolean tryEnroll(Student s) {
        if (s == null) return false;
        return state.tryEnroll(this, s);
    }

    public boolean addToWaitlist(Student s) {
        if (s == null) return false;
        return state.addToWaitlist(this, s);
    }

    public boolean dropStudent(Student s) {
        if (s == null) return false;
        return state.dropStudent(this, s);
    }

    public void setCapacity(int newCapacity) {
        state.setCapacity(this, newCapacity);
    }

    public void setStatusAdmin(CourseStatus newStatus) {
        state.setStatusAdmin(this, newStatus);
    }

    public void setStatusAdminInteractive(CourseStatus newStatus, Scanner scanner) {
        state.setStatusAdminInteractive(this, newStatus, scanner);
    }

    public void printRoster() {
        System.out.println("Roster for " + code + " - " + title + " (" + status + ", cap=" + capacity + "):");
        if (enrolled.isEmpty()) {
            System.out.println("  [no enrolled]");
        } else {
            for (Student s : enrolled) {
                System.out.println("  " + s.id + " - " + s.name);
            }
        }
    }

    public void printWaitlist() {
        System.out.println("Waitlist for " + code + ":");
        if (waitlist.isEmpty()) {
            System.out.println("  [no waitlisted]");
        } else {
            for (Student s : waitlist) {
                System.out.println("  " + s.id + " - " + s.name);
            }
        }
    }
}