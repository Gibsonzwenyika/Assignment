package  finalProject;
import java.util.TreeMap;

public class GpaCalculator{
 public static void main(String [] args){
  //creating two student objects
  Student student1 = new Student("John kanyelele","2023653412");
   String[] courses1 = {"csc1010","csc1100","csc1970","csc1400","csc2001","csc2011","csc2072","csc2041"};
   String[] grades1 = {"A+","c","C+","B","c","D","c","B"};
   student1.setResults(courses1,grades1);

  Student student2 = new Student("Dan kamuzi","2021473667");
   String[] courses2 = {"csc1010","csc1100","csc1970","csc1400","csc2001","csc2011","csc2072","csc2041"};
   String[] grades2 = {"C+","B","A","A+","B+","B","C","A"};
   student2.setResults(courses2,grades2);

 //printing out
 System.out.printf("%20s %30s \n","Name", "GPA");
 System.out.printf("%20s %30.2f \n",student1.getName(), student1.getGPA());
 System.out.printf("%20s %30.2f",student2.getName(), student2.getGPA());

 }
}


//this class is for the student object
 class Student{
   //characteristics of the student obj
   private String name;
   private String compNumber;
   private  TreeMap<String,String> results = new TreeMap<>();
   private double GPA ;
  
      //constucter with only name and compNumber parameters
   public Student(String name,String compNumber){
      this.name = name;
      this.compNumber = compNumber;
      this.GPA = 0;
   }

   //setter and getter methods for name
   public void setName(String name){
     this.name = name;
   }
 
   public String getName(){
     return this.name;
   }
  

   // setter and getter methods for compNumber
   public void setCompNumber(String compNumber){
      this.compNumber = compNumber;
   }
   
   public String getCompNumber(){
      return this.compNumber;
   }

   //setter method for results
   public void setResults(String[] courses,String[] courseGrades){
    for (int i = 0;i < courses.length;i++)
       this.results.putIfAbsent(courses[i],courseGrades[i].toUpperCase()); //add course if not present
   
    //update the value for GPA after adding courses
    calculateGPA();
   }

   //method for calculating the GPA and setting the value
   public void calculateGPA(){
     double totalCourseGPA = calculateTotalCourseGPA(); //stores the accumulative total fo cc * GPV
     double totalCredits = calculateTotalCourseCredits(); //stores  the maximum number of credits for a student
      this.GPA = totalCourseGPA/totalCredits;
   }

   //getter method for GPA
   public double getGPA(){
      return this.GPA;
   }
  
   //method for calculating the GPA  summation for all courses
   public double calculateTotalCourseGPA(){
     double totalGPA = 0; //for storing the accumulative course GPAs temporarily

      //loop for adding the GPA for the courses
      for (String course : results.keySet()) {
          String grade = results.get(course);
          totalGPA += calculateCourseGPA(course,grade);
      }
      return totalGPA;
   }

   //method for calculating the couse gpa
   public double calculateCourseGPA(String course,String grade){
      double  gradeValue = getGradeValue( grade);
      double courseCredit = getCourseCredit( course);
      return gradeValue * courseCredit ;
   }
    
    // method for determining the grade value for each course
    public double getGradeValue(String grade){
      if(grade.equals("A+"))
         return 5.00;
      else if (grade.equals("A"))
         return 4;
     else if (grade.equals("B+"))
         return 3.5;
     else if (grade.equals("B"))
         return 3;
     else if (grade.equals("C+"))
         return 2.37;
     else if (grade.equals("C"))
         return 1;
     else
         return 0;
    }
  
   //method for getting the course credit
   public double getCourseCredit(String course){
      if(course.charAt(course.length() - 1) == '0')
        return 30;
      else
        return 15;
   }
  
   //method for calculating the total course credits
   public double calculateTotalCourseCredits(){
      double totalCredits = 0; //temporal storage for the sum

      //loop for summing up the course credits
      for (String course : results.keySet()) {
         totalCredits +=  getCourseCredit(course);
      }
      return totalCredits;
   }
 }