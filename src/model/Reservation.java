package model;
import java.time.LocalDate;


public class Reservation extends Table {

    @Entity(type = "INTEGER", size = 32, primary = true)
    int id;

    @Entity(type="DATE", size=6, isnull = false)
    LocalDate appointmentDate;

    @ForeignKey(table = "Student", attribute = "id")
    @Entity(type = "INTEGER", size = 32 )
    int studentFk;

    @ForeignKey(table = "Employee", attribute = "id")
    @Entity(type = "INTEGER", size = 32)
    int employeeFk;

    @ForeignKey(table = "CycleOnMachine", attribute = "id")
    @Entity(type = "INTEGER", size = 32)
    int cycleonmachineFk;


    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Student getStudentFk() throws Exception{

        return (Student) Table.get(Student.class, studentFk);
    }

    public void setStudentFk(int studentFk) {
        this.studentFk = studentFk;
    }

    public Employee getEmployeeFk() throws Exception{
        return (Employee) Table.get(Employee.class, employeeFk);
    }

    public void setEmployeeFk(int employeeFk) {
        this.employeeFk = employeeFk;
    }

    public CycleOnMachine getCycleonmachineFk() throws Exception {
        return (CycleOnMachine) Table.get(CycleOnMachine.class, cycleonmachineFk);
    }

    public void setCycleonmachineFk(int cycleonmachineFk) {
        this.cycleonmachineFk = cycleonmachineFk;
    }
}
