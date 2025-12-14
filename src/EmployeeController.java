import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class EmployeeController {

    private static final Logger logger =
            LoggerUtil.getLogger(EmployeeController.class);

    //ADD
    public void addEmployee(Employee e) {
        String sql = "INSERT INTO employees VALUES (?, ?, ?, ?)";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, e.getId());
            ps.setString(2, e.getName());
            ps.setString(3, e.getDepartment());
            ps.setDouble(4, e.getSalary());

            ps.executeUpdate();
            logger.info("Employee added: " + e.getId());

        } catch (SQLException ex) {
            logger.severe("Add failed: " + ex.getMessage());
        }
    }

    // VIEW
    public void viewEmployees() {
        String sql = "SELECT * FROM employees";

        try (Connection con = DBUtil.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getString("department") + " | " +
                                rs.getDouble("salary")
                );
            }

        } catch (SQLException ex) {
            logger.severe("View failed: " + ex.getMessage());
        }
    }

    // SEARCH BY DEPARTMENT
    public void searchByDepartment(String department) {
        String sql = "SELECT * FROM employees WHERE department=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, department);
            ResultSet rs = ps.executeQuery();

            boolean found = false;

            while (rs.next()) {
                found = true;
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getDouble("salary")
                );
            }

            if (!found)
                System.out.println("No employees found");

            logger.info("Search by department: " + department);

        } catch (SQLException ex) {
            logger.severe("Search failed: " + ex.getMessage());
        }
    }

    // UPDATE
    public void updateEmployee(int id, double salary) {
        String sql = "UPDATE employees SET salary=? WHERE id=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, salary);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();
            logger.info(rows > 0 ? "Updated: " + id : "Not found");

        } catch (SQLException ex) {
            logger.severe("Update failed: " + ex.getMessage());
        }
    }

    // DELETE
    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE id=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            logger.info(rows > 0 ? "Deleted: " + id : "Not found");

        } catch (SQLException ex) {
            logger.severe("Delete failed: " + ex.getMessage());
        }
    }
}
