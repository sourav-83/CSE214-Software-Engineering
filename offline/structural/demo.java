public class demo {
    
    public static void main(String[] args) {
        checkout checkout = new checkout();
        
        System.out.println("          EDULEARN PLATFORM - DESIGN PATTERN DEMO           ");
        
        // SCENARIO 1: Simple Course Purchase
        System.out.println("SCENARIO 1: Student purchases a single Course");
        System.out.println("──────────────────────────────────────────────────────────────\n");
        
        course javaCourse = createJavaCourse();
        checkout.processCheckout(javaCourse);
        
        // SCENARIO 2: Module with Multiple Courses
        System.out.println("SCENARIO 2: Student purchases a Module (Full-Stack Development)");
        System.out.println("──────────────────────────────────────────────────────────────\n");
        
        module fullStackModule = createFullStackModule();
        checkout.processCheckout(fullStackModule);
        
        // SCENARIO 3: Module with Practice Set Add-on
        System.out.println("SCENARIO 3: Student purchases Module + Practice Set");
        System.out.println("──────────────────────────────────────────────────────────────\n");
        
        module dataModule = createDataScienceModule();
        component moduleWithPractice = new practiceSetDecorator(dataModule);
        checkout.processCheckout(moduleWithPractice);
        
        // SCENARIO 4: Module with Both Add-ons
        System.out.println("SCENARIO 4: Student purchases Module + Practice Set + Live Mentor");
        System.out.println("──────────────────────────────────────────────────────────────\n");
        
        module aiModule = createAIModule();
        component moduleWithBothAddons = new liveMentorDecorator(
            new practiceSetDecorator(aiModule)
        );
        checkout.processCheckout(moduleWithBothAddons);
        
        // SCENARIO 5: Duration-based Discount
        System.out.println("SCENARIO 5: Student purchases Module (5+ hours) - Gets Duration Discount");
        System.out.println("──────────────────────────────────────────────────────────────\n");
        
        module webModule = createWebDevModule();
        component moduleWithDurationDiscount = new durationDiscountDecorator(webModule);
        checkout.processCheckout(moduleWithDurationDiscount);
        
        // SCENARIO 6: Developing Country Student
        System.out.println("SCENARIO 6: Developing Country Student purchases Module + Practice Set");
        System.out.println("──────────────────────────────────────────────────────────────\n");
        
        module mobileModule = createMobileDevModule();
        component developingCountryPurchase = new developingCountryDiscountDecorator(
            new practiceSetDecorator(mobileModule)
        );
        checkout.processCheckout(developingCountryPurchase);
        
        // SCENARIO 7: Multiple Discounts Combined
        System.out.println("SCENARIO 7: Developing Country Student buys 2 Modules with Duration 5+ hrs");
        System.out.println("           Gets BOTH Developing Country + Duration + Multi-Module Discounts!");
        System.out.println("──────────────────────────────────────────────────────────────\n");
        
        // Create a bundle of 2 modules
        module bundleModule = new module("Complete Developer Bundle (2 Modules)");
        bundleModule.addCourse(createFullStackModule());
        bundleModule.addCourse(createDataScienceModule());
        
        // Apply all applicable discounts + add-ons
        component superDiscountedPurchase = new developingCountryDiscountDecorator(
            new multiModuleDiscountDecorator(
                new durationDiscountDecorator(
                    new practiceSetDecorator(bundleModule)
                ), 2  
            )
        );
        checkout.processCheckout(superDiscountedPurchase);
        
        // SCENARIO 8: Single Lesson Purchase
        System.out.println("SCENARIO 8: Student purchases a single Lesson");
        System.out.println("──────────────────────────────────────────────────────────────\n");
        
        lesson singleLesson = new lesson("Advanced SQL Queries", 1.5, 15.0);
        checkout.processCheckout(singleLesson);
        
    }
    
    
    
    private static course createJavaCourse() {
        course course = new course("Java Programming Fundamentals");
        course.addLesson(new lesson("Introduction to Java", 1.0, 10));
        course.addLesson(new lesson("Variables and Data Types", 1.5, 5));
        course.addLesson(new lesson("Control Flow", 2.0, 5));
        course.addLesson(new lesson("OOP Concepts", 2.5, 10));
        return course;
    }
    
    private static module createFullStackModule() {
        module Module = new module("Full-Stack Web Development");
        
        course frontendCourse = new course("Frontend Development");
        frontendCourse.addLesson(new lesson("HTML & CSS Basics", 2.0, 5));
        frontendCourse.addLesson(new lesson("JavaScript Fundamentals", 3.0, 20));
        frontendCourse.addLesson(new lesson("React Framework", 3.5, 10));
        
        course backendCourse = new course("Backend Development");
        backendCourse.addLesson(new lesson("Node.js Basics", 2.5, 20));
        backendCourse.addLesson(new lesson("RESTful APIs", 2.0, 20));
        backendCourse.addLesson(new lesson("Database Design", 2.5, 5));
        
        Module.addCourse(frontendCourse);
        Module.addCourse(backendCourse);
        
        return Module;
    }
    
    private static module createDataScienceModule() {
        module Module = new module("Data Science Fundamentals");
        
        course pythonCourse = new course("Python for Data Science");
        pythonCourse.addLesson(new lesson("Python Basics", 1.5, 15));
        pythonCourse.addLesson(new lesson("NumPy & Pandas", 2.0, 10));
        pythonCourse.addLesson(new lesson("Data Visualization", 1.5, 20));
        
        Module.addCourse(pythonCourse);
        
        return Module;
    }
    
    private static module createAIModule() {
        module Module = new module("Artificial Intelligence");
        
        course mlCourse = new course("Machine Learning Basics");
        mlCourse.addLesson(new lesson("Introduction to ML", 1.0, 5));
        mlCourse.addLesson(new lesson("Supervised Learning", 2.5, 10));
        mlCourse.addLesson(new lesson("Unsupervised Learning", 2.0, 20));
        
        Module.addCourse(mlCourse);
        
        return Module;
    }
    
    private static module createWebDevModule() {
        module Module = new module("Advanced Web Development");
        
        course advancedCourse = new course("Modern Web Technologies");
        advancedCourse.addLesson(new lesson("TypeScript", 2.0, 10));
        advancedCourse.addLesson(new lesson("Next.js Framework", 2.5, 10));
        advancedCourse.addLesson(new lesson("GraphQL", 1.5, 10));
        
        Module.addCourse(advancedCourse);
        
        return Module;
    }
    
    private static module createMobileDevModule() {
        module Module = new module("Mobile App Development");
        
        course mobileCourse = new course("React Native Development");
        mobileCourse.addLesson(new lesson("React Native Basics", 1.5, 20));
        mobileCourse.addLesson(new lesson("Navigation", 1.0, 15));
        mobileCourse.addLesson(new lesson("State Management", 1.5, 10));
        
        Module.addCourse(mobileCourse);
        
        return Module;
    }
}