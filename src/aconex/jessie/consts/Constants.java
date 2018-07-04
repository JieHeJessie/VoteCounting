package aconex.jessie.consts;

import javax.print.attribute.standard.Finishings;

/**
 * Created by F on 1/7/18.
 */
public class Constants {
    public static final String Default_FilePath = "./TEST.txt";
    public static final String Error_Duplicate_Input = "Input %s is duplicate with other characters.";
    public static final String Error_Invalid_Input = "Can not find index with input of %s.";
    public static final String TALLY = "tally";
    public static final String SUCCESS_MSG = "Selected result: ";
    public static final String FAIL_MSG = "The following candidates have same votes: ";
    public static final char START_INDEX = 'A';
    public static final int MAX_CANDIDATES = 'Z' - 'A' + 1;
}
