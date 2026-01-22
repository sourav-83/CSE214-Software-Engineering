class CancelledState implements CourseState {
    
    @Override
    public boolean tryEnroll(Course course, Student student) {
        System.out.println("Cannot enroll; course is CANCELLED: " + course.getCode());
        return false;
    }
    
    @Override
    public boolean addToWaitlist(Course course, Student student) {
        System.out.println("Cannot waitlist; course not accepting waitlist: " + course.getCode());
        return false;
    }
    
    @Override
    public boolean dropStudent(Course course, Student s) {
        System.out.println(s.name + " is neither enrolled nor waitlisted for " + course.getCode());
        return false;
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
        System.out.println("Course is CANCELLED; capacity change has no effect.");
    }
    
    @Override
    public void setStatusAdmin(Course course, CourseStatus newStatus) {
        if (newStatus == null) return;
        if (newStatus == course.status) {
            System.out.println("No change: " + course.getCode() + " already " + course.status);
            return;
        }
        
        if (newStatus == CourseStatus.DRAFT) {
            course.status = CourseStatus.DRAFT;
            course.setState(new DraftState());
            System.out.println(course.getCode() + " transitioned CANCELLED -> DRAFT (reinstating course)");
        } else {
            System.out.println("Invalid: CANCELLED can only transition to DRAFT for " + course.getCode());
        }
    }
    
    @Override
    public void setStatusAdminInteractive(Course course, CourseStatus newStatus, java.util.Scanner scanner) {
        setStatusAdmin(course, newStatus);
    }
}