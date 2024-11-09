package Activity14Interface_Esquivel;

public class Main {
    public static void main(String[] args) {
        Teacher student = new Student();

        student.listen();
        student.teach();
        student.learn();
        student.know();
        student.look();
        
        Teachable.read();

        System.out.println("Name: " + student.name);
        System.out.println("Age: " + student.age);
        System.out.println("Number of Pages: " + Readable.numberOfPages);
    }
}
