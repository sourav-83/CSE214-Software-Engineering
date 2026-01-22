class OpenState implements CourseState {
    
    @Override
    public boolean tryEnroll(Course course, Student s) {
        if (course.isAlreadyEnrolled(s)) {
            System.out.println("Already enrolled: " + s.name + " in " + course.getCode());
            return true;
        }
        if (course.getEnrolledCount() < course.getCapacity()) {
            if (course.getEnrolledCount() + 1 >= course.getCapacity()) {
                course.status = CourseStatus.FULL;
                course.setState(new FullState());
                System.out.println(course.getCode() + " will be FULL after this enrollment.");
            }
            return true;
        } else {
            course.status = CourseStatus.FULL;
            course.setState(new FullState());
            System.out.println(course.getCode() + " reached capacity; status set to FULL. Try waitlisting.");
            return false;
        }
    }
    
    @Override
    public boolean addToWaitlist(Course course, Student student) {
        System.out.println("Course is OPEN; try enrolling instead: " + course.getCode());
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
        return true;
    }
    
    @Override
    public void setCapacity(Course course, int newCapacity) {
        if (newCapacity < 0) newCapacity = 0;
        System.out.println("Setting capacity of " + course.getCode() + " to " + newCapacity);
        course.setCapacityInternal(newCapacity);
        
        // Adjust status based on capacity
        if (course.getEnrolledCount() < course.getCapacity()) {
            // Stay OPEN
            System.out.println(course.getCode() + " remains OPEN (capacity allows enrollment).");
        } else if (course.getEnrolledCount() >= course.getCapacity()) {
            course.status = CourseStatus.FULL;
            course.setState(new FullState());
            System.out.println(course.getCode() + " status changed to FULL (at capacity).");
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
            course.status = CourseStatus.CLOSED;
            course.setState(new ClosedState());
            System.out.println(course.getCode() + " transitioned OPEN -> CLOSED");
        } else if (newStatus == CourseStatus.DRAFT) {
            course.status = CourseStatus.DRAFT;
            course.setState(new DraftState());
            System.out.println(course.getCode() + " transitioned OPEN -> DRAFT");
        } else if (newStatus == CourseStatus.CANCELLED) {
            course.setState(new CancelledState());
            course.cancelCourse();
        } else {
            System.out.println("Invalid transition from OPEN to " + newStatus);
        }
    }
    
    @Override
    public void setStatusAdminInteractive(Course course, CourseStatus newStatus, java.util.Scanner scanner) {
        setStatusAdmin(course, newStatus);
    }
}