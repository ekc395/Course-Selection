import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.*;

public class CourseComparison {
   public static void main(String args[]) throws IOException {
    AuditDegree acms = new AuditDegree("https://www.washington.edu/students/gencat/program/S/Nursing-552.html"); // paste url within quotes
    AuditDegree stat = new AuditDegree("https://www.washington.edu/students/gencat/program/S/Biology-112.html");
    acms.printClasses();
    System.out.println();
    stat.printClasses();
    System.out.println();
    System.out.println(acms.commonClasses(stat));
   }   
}