package com.e.study1;

public class List_Item {
    private int id;
    private String server_time;
    private String local_time;
    private String info;

    public List_Item(int id, String server_time, String local_time, String info) {
        this.id = id;
        this.server_time = server_time;
        this.local_time = local_time;
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public String getServer_time() {
        return server_time;
    }

    public String getLocal_time() {
        return local_time;
    }

    public String getInfo() {
        return info;
    }
}
