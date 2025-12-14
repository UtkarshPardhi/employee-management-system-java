import java.util.Scanner;

public class EmployeeRunner {

    public static void main(String[] args) {

        EmployeeController controller = new EmployeeController();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Enter Your Choice :");
            System.out.println("Enter 1 for Adding Employee :");
            System.out.println("Enter 2 for Viewing Employees :");
            System.out.println("Enter 3 for Updating Records : ");
            System.out.println("Enter 4 for Deleting A Record :");
            System.out.println("Enter 5 to search Employee By Department :");
            System.out.println();
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    System.out.print("Department: ");
                    String dept = sc.nextLine();

                    System.out.print("Salary: ");
                    double sal = sc.nextDouble();

                    controller.addEmployee(
                            new Employee(id, name, dept, sal)
                    );
                    break;

                case 2:
                    controller.viewEmployees();
                    break;

                case 3:
                    System.out.print("ID: ");
                    int uid = sc.nextInt();
                    System.out.print("New Salary: ");
                    double newSal = sc.nextDouble();
                    controller.updateEmployee(uid, newSal);
                    break;

                case 4:
                    System.out.print("ID: ");
                    int did = sc.nextInt();
                    controller.deleteEmployee(did);
                    break;

                case 5:
                    sc.nextLine();
                    System.out.print("Department: ");
                    String d = sc.nextLine();
                    controller.searchByDepartment(d);
                    break;

                case 6:
                    System.exit(0);
            }
        }
    }
}
