package lzf.baselibrary.bean;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
public class GuideBean {
    private String appType;
    private String created;
    private String description;
    private String enabled;
    private String id;
    private String img;
    private String locationId;
    private String sequence;
    private String shareUrl;
    private String text;
    private String type;
    private String updated;
    private String url;

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "GuideBean{" +'\n' +
                "appType='" + appType + '\n' +
                ", created='" + created + '\n' +
                ", description='" + description + '\n' +
                ", enabled='" + enabled + '\n' +
                ", id='" + id + '\n' +
                ", img='" + img + '\n' +
                ", locationId='" + locationId + '\n' +
                ", sequence='" + sequence + '\n' +
                ", shareUrl='" + shareUrl + '\n' +
                ", text='" + text + '\n' +
                ", type='" + type + '\n' +
                ", updated='" + updated + '\n' +
                ", url='" + url + '\n' +
                '}';
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
