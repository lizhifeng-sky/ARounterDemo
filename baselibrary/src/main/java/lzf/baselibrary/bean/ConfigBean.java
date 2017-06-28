package lzf.baselibrary.bean;

/**
 * Created by Administrator on 2017/6/28 0028.
 */
public class ConfigBean {
    private String activityServiceCode;
    private String activityUrl;
    private String appDownload;

    @Override
    public String toString() {
        return "ConfigBean{" +
                "activityServiceCode='" + activityServiceCode + '\'' +
                ", activityUrl='" + activityUrl + '\'' +
                ", appDownload='" + appDownload + '\'' +
                '}';
    }

    public String getActivityServiceCode() {
        return activityServiceCode;
    }

    public void setActivityServiceCode(String activityServiceCode) {
        this.activityServiceCode = activityServiceCode;
    }

    public String getActivityUrl() {
        return activityUrl;
    }

    public void setActivityUrl(String activityUrl) {
        this.activityUrl = activityUrl;
    }

    public String getAppDownload() {
        return appDownload;
    }

    public void setAppDownload(String appDownload) {
        this.appDownload = appDownload;
    }
}
