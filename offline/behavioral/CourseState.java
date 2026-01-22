interface CourseState {
    boolean tryEnroll(Course course, Student student);
    boolean addToWaitlist(Course course, Student student);
    boolean dropStudent(Course course, Student student);
    boolean isVisibleToStudents();
    void setCapacity(Course course, int newCapacity);
    void setStatusAdmin(Course course, CourseStatus newStatus);
    void setStatusAdminInteractive(Course course, CourseStatus newStatus, java.util.Scanner scanner);
}