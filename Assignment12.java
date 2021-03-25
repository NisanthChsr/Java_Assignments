import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
class Student
{
    int field_id,age,year_of_enrollment;
    double perTillDate;
    String name,engDepartment,gender;
    Student(int field_id,String name,int age,String gender,String engDepartment,int year_of_enrollment,double perTillDate)
    {
        this.field_id = field_id;
        this.name=name;
        this.age=age;
        this.gender=gender;
        this.engDepartment=engDepartment;
        this.year_of_enrollment=year_of_enrollment;
        this.perTillDate=perTillDate;

    }
}
public class Assignment12 {
    public static void main(String[] args) {

        List<Student> list = new ArrayList<>();
        list.add(new Student(111, "Jiya Brein", 17, "Female", "Computer Science", 2018, 70.8));
        list.add(new Student(122, "Paul Niksui", 18, "Male", "Mechanical", 2016, 50.2));
        list.add(new Student(133, "Martin Theron", 17, "Male", "Electronic", 2017, 90.3));
        list.add(new Student(144, "Murali Gowda", 18, "Male", "Electrical", 2018, 80));
        list.add(new Student(155, "Nima Roy", 19, "Female", "Textile", 2016, 70));
        list.add(new Student(166, "Iqbal Hussain", 18, "Male", "Security", 2016, 70));
        list.add(new Student(177, "Manu Sharma", 16, "Male", "Chemical", 2018, 70));
        list.add(new Student(188, "Wang Liu", 20, "Male", "Computer Science", 2015, 80));
        list.add(new Student(199, "Amelia Zoe", 18, "Female", "Computer Science", 2016, 85));
        list.add(new Student(200, "Jaden Dough", 18, "Male", "Security", 2015, 82));
        list.add(new Student(211, "Jasna Kaur", 20, "Female", "Electronic", 2019, 83));
        list.add(new Student(222, "Nitin Joshi", 19, "Male", "Textile", 2016, 60.4));
        list.add(new Student(233, "Jyothi Reddy", 16, "Female", "Computer Science", 2015, 45.6));
        list.add(new Student(244, "Nicolus Den", 16, "Male", "Electronic", 2017, 95.8));
        list.add(new Student(255, "Ali Baig", 17, "Male", "Electronic", 2018, 88.4));
        list.add(new Student(266, "Sanvi Pandey", 17, "Female", "Electric", 2019, 72.4));
        list.add(new Student(277, "Anuj Chettiar", 18, "Male", "Computer Science", 2017, 57.5));
        // Print the name of all departments in the college
        List<String> departments=list.stream().map(d->d.engDepartment).collect(Collectors.toList());
        System.out.println(departments);
        //Get the names of all students who have enrolled after 2018
        List<String> names=list.stream().filter(y->y.year_of_enrollment > 2018).map(n->n.name).collect(Collectors.toList());
        System.out.println(names);
        //Get the details of all male student in the computer sci department
        List<Student> students=list.stream().filter(g->g.gender.equals("Male")).filter(d->d.engDepartment.equals("Computer Science")).collect(Collectors.toList());
        System.out.println(students.stream().map(n->n.name).collect(Collectors.toList()));
        // How many male and female student are there ?
        Map<String, Long> result = list.stream().map(g->g.gender).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(result);
        //Count the number of students in each department
        Map<String, Long> result1 = list.stream().map(d->d.engDepartment).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(result1);
        //the details of highest student having highest percentage
        Student highestStudent=list.stream().max((student1,student2)->student1.perTillDate>student2.perTillDate?1:-1).get();
        System.out.println(highestStudent.field_id+" "+highestStudent.name);
        //the average age of male and female students
        OptionalDouble average= list.stream().filter(g->g.gender.equals("Female")).mapToDouble(a-> a.age).average();
        OptionalDouble average1= list.stream().filter(g->g.gender.equals("Male")).mapToDouble(a-> a.age).average();
        System.out.println("Male average "+average1);
        System.out.println("Female Average "+average);
        //The details of youngest male student in the Electronic department
        Student youngest = list.stream().filter(d->d.engDepartment.equals("Electronic")).filter(d->d.gender.equals("Male")).collect(Collectors.minBy((student1, student2)->student1.age>student2.age?1:-1)).get();
        System.out.println(youngest.name);
        //.How many male and female students are there in the computer science departmen
        Map<String, Long> result2 = list.stream().filter(d->d.engDepartment.equals("Computer Science")).map(g->g.gender).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(result2);
        Map<Object, Double> averagePercentageEachDepartment = list
                .stream()
                .collect(Collectors.groupingBy(student -> student.engDepartment,
                        Collectors.averagingDouble(student->student.perTillDate)));

        averagePercentageEachDepartment.forEach(
                (department,average2) -> System.out.println(department + "\t" + average2)
        );
    }

}