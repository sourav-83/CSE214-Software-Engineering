public class RegistrationMediator extends Mediator {
    

    public boolean enrollStudent(Student student, Course course) {
        if (student == null || course == null) {
            return false;
        }
        
        if (!course.isVisibleToStudents()) {
            System.out.println("Course " + course.code + " is not available for student enrollment.");
            return false;
        }
        
        if (student.isEnrolledIn(course)) {
            System.out.println(student.name + " is already enrolled in " + course.code);
            return false;
        }
        

        if (student.isWaitlistedFor(course)) {
            System.out.println(student.name + " is already waitlisted for " + course.code);
            return false;
        }
        

        boolean success = course.tryEnroll(student);
        
        if (success) {
            course.addToEnrolled(student);
            student.addEnrolledCourse(course);
            System.out.println("Enrolled: " + student.name + " in " + course.code);
            CourseStatus status = course.status;
            if (status == CourseStatus.FULL)
            {
                System.out.println(course.getCode() + " is now FULL.");
            }
        }
        
        return success;
    }
    

    public boolean addToWaitlist(Student student, Course course) {
        if (student == null || course == null) {
            return false;
        }
        

        if (!course.isVisibleToStudents()) {
            System.out.println("Course " + course.code + " is not available.");
            return false;
        }
        

        if (student.isWaitlistedFor(course)) {
            System.out.println(student.name + " is already waitlisted for " + course.code);
            return false;
        }
        

        if (student.isEnrolledIn(course)) {
            System.out.println(student.name + " is already enrolled in " + course.code + "; cannot waitlist.");
            return false;
        }
        

        boolean success = course.addToWaitlist(student);
        
        if (success) {
            course.addToWaitlistInternal(student);
            student.addWaitlistCourse(course);
            System.out.println("Waitlisted: " + student.name + " for " + course.code);
        }
        
        return success;
    }
    

    public boolean dropStudent(Student student, Course course) {
        if (student == null || course == null) {
            return false;
        }
        
        boolean wasEnrolled = student.isEnrolledIn(course);
        boolean wasWaitlisted = student.isWaitlistedFor(course);
        
        if (!wasEnrolled && !wasWaitlisted) {
            System.out.println(student.name + " is not enrolled or waitlisted for " + course.code);
            return false;
        }
        

        boolean success = course.dropStudent(student);
        
        if (success) {
            student.removeCourse(course);
            
            if (wasEnrolled) {
                course.removeFromEnrolled(student);
                System.out.println("Dropped from enrolled: " + student.name + " from " + course.code);
                

                if (course.status == CourseStatus.OPEN || course.status == CourseStatus.FULL) {
                    promoteFromWaitlist(course);
                }
            } else {
                course.removeFromWaitlist(student);
                System.out.println("Removed from waitlist: " + student.name + " for " + course.code);
            }
        }
        
        return success;
    }
    

    public void promoteFromWaitlist(Course course) {
        if (course == null) return;
        
        if (course.getEnrolledCount() < course.getCapacity() && course.getWaitlistCount() > 0) {
            Student promoted = course.pollFromWaitlist();
            if (promoted != null) {
                course.addToEnrolled(promoted);
                promoted.addEnrolledCourse(course); 
                System.out.println("Promoted from waitlist: " + promoted.name + " into " + course.code);
            }
        }
    }
    

    public void removeStudentDirect(Student student, Course course) {
        if (student == null || course == null) return;
        
        student.removeCourse(course);
        course.removeFromEnrolled(student);
        course.removeFromWaitlist(student);
    }
    

    public void enrollStudentDirect(Student student, Course course) {
        if (student == null || course == null) return;
        
        course.addToEnrolled(student);
        student.addEnrolledCourse(course);
    }
}