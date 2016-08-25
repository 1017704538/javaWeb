package com.mr.model;
/**
 * 视频信息实体类
 * @author CHUNBIN
 *
 */
public class MediaInfo {
    private Integer id;                                             //系统编号属性
    private String mediaTitle;                                  //视频标题属性
    private String media_type;                                  //视频类型
    private String mediaSrc;                                    //视频文件地址属性
    private String mediaPic;                                    //视频截图属性
    private String mediaInfo;                                   //视频描述
    private String mediaUptime;                                     //视频上传时间
    private String lookCount;                                       //视频浏览次数
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getMediaTitle() {
        return mediaTitle;
    }
    public void setMediaTitle(String mediaTitle) {
        this.mediaTitle = mediaTitle;
    }
    public String getMedia_type() {
        return media_type;
    }
    public void setMedia_type(String mediaType) {
        media_type = mediaType;
    }
    public String getMediaSrc() {
        return mediaSrc;
    }
    public void setMediaSrc(String mediaSrc) {
        this.mediaSrc = mediaSrc;
    }
    public String getMediaPic() {
        return mediaPic;
    }
    public void setMediaPic(String mediaPic) {
        this.mediaPic = mediaPic;
    }
    public String getMediaInfo() {
        return mediaInfo;
    }
    public void setMediaInfo(String mediaInfo) {
        this.mediaInfo = mediaInfo;
    }
    public String getMediaUptime() {
        return mediaUptime;
    }
    public void setMediaUptime(String mediaUptime) {
        this.mediaUptime = mediaUptime;
    }
    public String getLookCount() {
        return lookCount;
    }
    public void setLookCount(String lookCount) {
        this.lookCount = lookCount;
    }
}
