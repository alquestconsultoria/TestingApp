package uk.co.myexample.jamescoggan.data;

import com.j256.ormlite.field.DatabaseField;

/*
 * File: app/src/main/java/uk.co.myexample.jamescoggan/app/data/StatisticsData.java
 * <p/>
 * Description: Data model used for statistics
 *
 * @author James Coggan
 * @version 1.0
 * @since 2015-05-10
 */
public class StatisticData {
    @DatabaseField(generatedId = true)
    int db_id;

    @DatabaseField
    private Integer UserID;

    @DatabaseField
    private Integer ImageID;

    @DatabaseField
    private String UserName;

    @DatabaseField
    private Integer NumberOfPosts;

    @DatabaseField
    private double AverageImageSize;

    @DatabaseField
    private Integer MaxPhotoWidth;

    StatisticData() {
    }

    public StatisticData(final Integer UserID, final Integer ImageID, final String UserName, final Integer NumberOfPosts, final double AverageImageSize, final Integer MaxPhotoWidth) {
        this.UserID = UserID;
        this.ImageID = ImageID;
        this.UserName = UserName;
        this.NumberOfPosts = NumberOfPosts;
        this.AverageImageSize = AverageImageSize;
        this.MaxPhotoWidth = MaxPhotoWidth;
    }

    /**
     * @return The UserID
     */
    public Integer getUserID() {
        return UserID;
    }

    /**
     * @param UserID The UserID
     */
    public void setUserID(Integer UserID) {
        this.UserID = UserID;
    }

    /**
     * @return The ImageID
     */
    public Integer getImageID() {
        return ImageID;
    }

    /**
     * @param ImageID The ImageID
     */
    public void setImageID(Integer ImageID) {
        this.ImageID = ImageID;
    }

    /**
     * @return The UserName
     */
    public String getUserName() {
        return UserName;
    }

    /**
     * @param UserName The UserName
     */
    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    /**
     * @return The NumberOfPosts
     */
    public Integer getNumberOfPosts() {
        return NumberOfPosts;
    }

    /**
     * @param NumberOfPosts The NumberOfPosts
     */
    public void setNumberOfPosts(Integer NumberOfPosts) {
        this.NumberOfPosts = NumberOfPosts;
    }

    /**
     * @return The AverageImageSize
     */
    public double getAverageImageSize() {
        return AverageImageSize;
    }

    /**
     * @param AverageImageSize The AverageImageSize
     */
    public void setAverageImageSize(double AverageImageSize) {
        this.AverageImageSize = AverageImageSize;
    }

    /**
     * @return The MaxPhotoWidth
     */
    public Integer getMaxPhotoWidth() {
        return MaxPhotoWidth;
    }

    /**
     * @param MaxPhotoWidth The MaxPhotoWidth
     */
    public void setMaxPhotoWidth(Integer MaxPhotoWidth) {
        this.MaxPhotoWidth = MaxPhotoWidth;
    }
}
