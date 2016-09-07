package com.addhen.voto.sdk.model;

/**
 * @author Henry Addo
 */
public class Audio {

    /** The language id of the SMS */
    public Long languageId;

    /** The language name of the SMS */
    public String languageName;

    /** The audio file's ID */
    public Long audioFileId;

    /** The audio file's description */
    public String audioFileDescription;

    public Audio(Long languageId, String languageName, Long audioFileId,
            String audioFileDescription) {
        this.languageId = languageId;
        this.languageName = languageName;
        this.audioFileId = audioFileId;
        this.audioFileDescription = audioFileDescription;
    }

    @Override
    public String toString() {
        return "Audio{"
                + "languageId=" + languageId
                + ", languageName='" + languageName + '\''
                + ", audioFileId=" + audioFileId
                + ", audioFileDescription='" + audioFileDescription + '\''
                + '}';
    }
}
