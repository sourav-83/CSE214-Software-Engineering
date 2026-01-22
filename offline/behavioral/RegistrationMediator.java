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
            System.out.println(student.name + " successfully enrolled in " + course.code);
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
            System.out.println(student.name + " added to waitlist for " + course.code);
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
                System.out.println(student.name + " dropped from " + course.code);
                

                if (course.status == CourseStatus.OPEN || course.status == CourseStatus.FULL) {
                    promoteFromWaitlist(course);
                }
            } else {
                course.removeFromWaitlist(student);
                System.out.println(student.name + " removed from waitlist for " + course.code);
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
                // promoted.removeCourse(course); 
                promoted.addEnrolledCourse(course); 
                System.out.println("  Promoted from waitlist: " + promoted.name + " for " + course.code);
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