package backend.model;

public class Room {
    private String id;
    private String name;
    private String tenantName;

    public Room(String id, String name, String tenantName) {
        this.id = id;
        this.name = name;
        this.tenantName = tenantName;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTenantName() {
        return tenantName;
    }
}
