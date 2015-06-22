package uk.co.myexample.jamescoggan.data;

import com.google.gson.annotations.Expose;
import com.j256.ormlite.field.DatabaseField;

/*
 * File: app/src/main/java/uk.co.myexample.jamescoggan/app/data/UserData.java
 * <p/>
 * Description: Data model for database and rest client
 *
 * @author James Coggan
 * @version 1.0
 * @since 2015-05-10
 */
public class UserData {

    @DatabaseField(generatedId = true)
    int db_id;

    @DatabaseField
    @Expose
    private Integer ID;

    @DatabaseField
    @Expose
    private Integer ImageID;

    @DatabaseField
    @Expose
    private String Title;

    @DatabaseField
    @Expose
    private Integer UserID;

    @DatabaseField
    @Expose
    private String UserName;

    UserData() {
    }

    public UserData(final Integer ID, final Integer ImageID, final String Title, final Integer UserID, final String UserName) {
        this.ID = ID;
        this.ImageID = ImageID;
        this.Title = Title;
        this.UserID = UserID;
        this.UserName = UserName;
    }


    /**
     * @return The ID
     */
    public Integer getID() {
        return ID;
    }

    /**
     * @param ID The ID
     */
    public void setID(Integer ID) {
        this.ID = ID;
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
     * @return The Title
     */
    public String getTitle() {
        return Title;
    }

    /**
     * @param Title The Title
     */
    public void setTitle(String Title) {
        this.Title = Title;
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
}
