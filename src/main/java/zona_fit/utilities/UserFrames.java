package zona_fit.utilities;

public class UserFrames {
    private static final String FRAME_TXT = "***********";
    private static final String FRAME_TXT2 = "---------------------------";

    private UserFrames() {
    }

    public static String displayOutputTitle(String newTitle) {
        return FRAME_TXT + "\n" + newTitle + "\n" + FRAME_TXT;
    }

    public static String displayOutputSubTitles(String newTitle) {
        return FRAME_TXT2 + "\n" + newTitle + "\n" + FRAME_TXT2;
    }
}
