import java.util.Scanner;

class FullState implements CourseState {
    
    @Override
    public boolean tryEnroll(Course course, Student student) {
        System.out.println("Cannot enroll; course is FULL. You may waitlist: " + course.getCode());
        return false;
    }
    
    @Override
    public boolean addToWaitlist(Course course, Student s) {
        if (course.isAlreadyEnrolled(s)) {
            System.out.println("Already enrolled; no need to waitlist: " + s.name + " for " + course.getCode());
            return false;
        }
        if (course.isOnWaitlist(s)) {
            System.out.println("Already waitlisted: " + s.name + " for " + course.getCode());
            return false;
        }
        return true;
    }
    
    @Override
    public boolean dropStudent(Course course, Student s) {
    if (s == null) return false;
    
    if (course.isAlreadyEnrolled(s)) {
        return true; 
    } else if (course.isOnWaitlist(s)) {
        return true; 
    } else {
        System.out.println(s.name + " is neither enrolled nor waitlisted for " + course.getCode());
        return false;
    }
    }
    
    @Override
    public boolean isVisibleToStudents() {
        return true;
    }
    
    @Override
    public void setCapacity(Course course, int newCapacity) {
        if (newCapacity < 0) newCapacity = 0;
        System.out.println("Setting capacity of " + course.getCode() + " to " + newCapacity);
        course.setCapacityInternal(newCapacity);
        
        
        if (course.getEnrolledCount() < course.getCapacity()) {
            course.status = CourseStatus.OPEN;
            course.setState(new OpenState());
            System.out.println(course.getCode() + " status changed to OPEN (capacity allows enrollment).");
        } else if (course.getEnrolledCount() == course.getCapacity()) {
            course.status = CourseStatus.FULL;
            System.out.println(course.getCode() + " status changed to FULL (at capacity).");
        } else {
            course.status = CourseStatus.FULL;
            System.out.println(course.getCode() + " over capacity; remains FULL.");
        }
    }
    
    @Override
    public void setStatusAdmin(Course course, CourseStatus newStatus) {
        if (newStatus == null) return;
        if (newStatus == course.status) {
            System.out.println("No change: " + course.getCode() + " already " + course.status);
            return;
        }
        
        if (newStatus == CourseStatus.CLOSED) {
            course.closeWithRandomWaitlistSelection(course.getCapacity());
        } else if (newStatus == CourseStatus.CANCELLED) {
            course.setState(new CancelledState());
            course.cancelCourse();
        } else {
            System.out.println("Invalid transition from FULL to " + newStatus + " (FULL->OPEN is automatic on drop)");
        }
    }
    
    @Override
    public void setStatusAdminInteractive(Course course, CourseStatus newStatus, Scanner scanner) {
        if (newStatus == null) return;
        if (newStatus == course.status) {
            System.out.println("No change: " + course.getCode() + " already " + course.status);
            return;
        }
        
        if (newStatus == CourseStatus.CLOSED) {
            if (course.getWaitlistCount() > 0) {
                System.out.println(course.getCode() + " has " + course.getWaitlistCount() + " student(s) on waitlist.");
                System.out.print("Do you want to increase capacity before closing? (Enter new capacity, or 0 to not increase): ");
                try {
                    int newCapacity = Integer.parseInt(scanner.nextLine().trim());
                    if (newCapacity > 0) {
                        if (newCapacity > course.getCapacity()) {
                            course.setCapacityInternal(newCapacity);
                            System.out.println("Capacity increased to " + newCapacity);
                            course.closeWithRandomWaitlistSelection(newCapacity);
                        } else {
                            System.out.println("New capacity must be greater than current capacity (" + course.getCapacity() + "). No change.");
                            course.closeWithRandomWaitlistSelection(course.getCapacity());
                        }
                    } else {
                        System.out.println("No capacity increase.");
                        course.closeWithRandomWaitlistSelection(course.getCapacity());
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Closing without capacity increase.");
                    course.closeWithRandomWaitlistSelection(course.getCapacity());
                }
            } else {
                course.closeWithRandomWaitlistSelection(course.getCapacity());
            }
        } else {
            setStatusAdmin(course, newStatus);
        }
    }
}