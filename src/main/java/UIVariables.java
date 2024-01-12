import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class UIVariables {
    public static Font mainFont = new Font("Roboto Mono", Font.BOLD, 15);
    public static Color backgroundColor1 = new Color(235, 235, 235);
    public static Color backgroundColor2 = new Color(177, 186, 196);
    public static Color foregroundColor = new Color(36, 32, 32);
    public static Color borderColor = new Color(83, 137, 150);

    public static Border getButtonBorder(int top, int left, int bottom, int right, int thickness) {
        Border lineBorder = BorderFactory.createLineBorder(borderColor, thickness);
        Border emptyBorder = new EmptyBorder(top, left, bottom, right);
        return BorderFactory.createCompoundBorder(lineBorder, emptyBorder);
    }

}
