/*
 * Copyright (C) 2015 PEKKA
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package LogicSimulator;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author PEKKA
 */
public class InfoPopup {

    public static Rectangle infoPopup = new Rectangle(196, 168);
    public static boolean enabled = true;

    public static void setEnabled(boolean e) {
        enabled = e;
    }

    public static boolean getEnabled() {
        return enabled;
    }

    public static void setinfoPopup(Rectangle rectangle, Image image) {
        rectangle.setOnMouseEntered((MouseEvent me) -> {
            setInfoPopup(image);
            me.consume();
        });
        rectangle.setOnMouseExited((MouseEvent me) -> {
            resetInfoPopup();
            me.consume();
        });
    }

    public static void setinfoPopup(Button butt, Image image) {
        butt.setOnMouseEntered((MouseEvent me) -> {
            setInfoPopup(image);
            me.consume();
        });
        butt.setOnMouseExited((MouseEvent me) -> {
            resetInfoPopup();
            me.consume();
        });
    }

    private static void setInfoPopup(Image image) {
        if (!Globals.main.gateGroup.getChildren().contains(infoPopup) && enabled) {
            infoPopup.setTranslateX(750);
            infoPopup.setTranslateY(0);
            if (image != null) {
                infoPopup.setFill(new ImagePattern(image, 0, 0, 1, 1, true));
            }
            //Globals.main.showOnConsole("Open infoPopup");
            Globals.main.gateGroup.getChildren().add(infoPopup);
        }
    }

    public static void resetInfoPopup() {
        if (Globals.main.gateGroup.getChildren().contains(infoPopup)) {
            Globals.main.gateGroup.getChildren().remove(infoPopup);
        }
    }
}
