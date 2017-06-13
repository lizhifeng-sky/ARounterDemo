package lzf.baselibrary.bean;

/**
 * Created by Administrator on 2017/6/9 0009.
 */
public class CityBean {
    private String created;
    private String enable;
    private String id;
    private String initial;
    private String name;
    private String updated;

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "CityBean{" +
                "created='" + created + '\n' +
                ", enable='" + enable + '\n' +
                ", id='" + id + '\n' +
                ", initial='" + initial + '\n' +
                ", name='" + name + '\n' +
                ", updated='" + updated + '\n' +
                '}';
    }
}
