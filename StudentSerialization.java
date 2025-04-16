import java.io.*;

// Student class implements Serializable interface
class Student implements Serializable {
    private int id;
    private String name;
    private double GPA;

    // Constructor
    public Student(int id, String name, double GPA) {
        this.id = id;
        this.name = name;
        this.GPA = GPA;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getGPA() {
        return GPA;
    }

    // Method to display student details
    public void display() {
        System.out.println("Student ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("GPA: " + GPA);
    }
}

public class StudentSerialization {

    // Method to serialize a Student object
    public static void serializeStudent(Student student, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(student);
            System.out.println("Student object serialized successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        } catch (IOException e) {
            System.out.println("Error: IO exception occurred while serializing.");
        }
    }

    // Method to deserialize a Student object
    public static Student deserializeStudent(String filename) {
        Student student = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            student = (Student) ois.readObject();
            System.out.println("Student object deserialized successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        } catch (IOException e) {
            System.out.println("Error: IO exception occurred while deserializing.");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: Class not found.");
        }
        return student;
    }

    public static void main(String[] args) {
        // Create a Student object
        Student student = new Student(101, "Alice", 3.8);

        // Serialize the student object to a file
        String filename = "student.ser";
        serializeStudent(student, filename);

        // Deserialize the student object from the file
        Student deserializedStudent = deserializeStudent(filename);

        // Display deserialized student details
        if (deserializedStudent != null) {
            deserializedStudent.display();
        }
    }
}
