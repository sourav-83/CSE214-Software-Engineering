class DraftState implements CourseState {
    
    @Override
    public boolean tryEnroll(Course course, Student student) {
        System.out.println("Cannot enroll; course is DRAFT (not visible): " + course.getCode());
        return false;
    }
    
    @Override
    public boolean addToWaitlist(Course course, Student student) {
        System.out.println("Cannot waitlist; course not accepting waitlist: " + course.getCode());
        return false;
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
        return false;
    }
    
    @Override
    public void setCapacity(Course course, int newCapacity) {
        if (newCapacity < 0) newCapacity = 0;
        System.out.println("Setting capacity of " + course.getCode() + " to " + newCapacity);
        course.setCapacityInternal(newCapacity);
        System.out.println(course.getCode() + " is DRAFT; capacity set but status unchanged.");
    }
    
    @Override
    public void setStatusAdmin(Course course, CourseStatus newStatus) {
        if (newStatus == null) return;
        if (newStatus == course.status) {
            System.out.println("No change: " + course.getCode() + " already " + course.status);
            return;
        }
        
        if (newStatus == CourseStatus.OPEN) {
            course.status = CourseStatus.OPEN;
            course.setState(new OpenState());
            System.out.println(course.getCode() + " transitioned DRAFT -> OPEN");
        } else if (newStatus == CourseStatus.CLOSED) {
            course.status = CourseStatus.CLOSED;
            course.setState(new ClosedState());
            System.out.println(course.getCode() + " transitioned DRAFT -> CLOSED");
        } else if (newStatus == CourseStatus.CANCELLED) {
            course.setState(new CancelledState());
            course.cancelCourse();
        } else {
            System.out.println("Invalid transition from DRAFT to " + newStatus);
        }
    }
    
    @Override
    public void setStatusAdminInteractive(Course course, CourseStatus newStatus, java.util.Scanner scanner) {
        setStatusAdmin(course, newStatus);
    }
}