package model;

public class CycleOnMachine extends Table {

    @Entity(type = "INTEGER", size = 32, primary = true)
    int id;

    @Entity(type = "VARCHAR", size = 150, isnull = false)
    String description;

    @ForeignKey(table = "Cycle", attribute = "id")
    @Entity(type = "INTEGER", size = 32)
    int cycleFk;

    @ForeignKey(table = "Machine", attribute = "id")
    @Entity(type = "INTEGER", size = 32)
    int machineFk;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Cycle getCycleFk() throws Exception{
        return (Cycle) Table.get(Cycle.class, cycleFk);
    }

    public void setCycleFk(int cycleFk) {
        this.cycleFk = cycleFk;
    }

    public Machine getMachineFk() throws Exception{
        return (Machine) Table.get(Machine.class, machineFk);
    }

    public void setMachineFk(int machineFk) {
        this.machineFk = machineFk;
    }
}

