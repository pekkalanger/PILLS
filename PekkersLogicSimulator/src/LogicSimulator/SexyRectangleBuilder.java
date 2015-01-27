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

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

@Deprecated
public class SexyRectangleBuilder {

    final Main main;

    @Deprecated
    public SexyRectangleBuilder(final Main main) {
        this.main = main;
    }

    @Deprecated
    public Rectangle createRectangle(final Image i) {
        final Rectangle rectangle = new Rectangle(100, 100);
        rectangle.setLayoutX(100);
        rectangle.setLayoutY(10);
        rectangle.setFill(new ImagePattern(i, 0, 0, 1, 1, true));

        final String name = "a rectangul";

        rectangle.setCursor(Cursor.HAND);
        //add a mouse listeners
        rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (me.getButton() == MouseButton.PRIMARY) {
                    main.showOnConsole("Clicked on" + name + ", " + me.getClickCount() + "times");
                    //the event will be passed only to the circle which is on front
                } else if (me.getButton() == MouseButton.SECONDARY) {
                    main.showOnConsole("Created new rectangle");
                    final Rectangle rectangle = createRectangle(i);
                    rectangle.setTranslateX(300);
                    rectangle.setTranslateY(50);
                    rectangle.toFront();
                    main.schematicGroup.getChildren().add(rectangle);
                    //main.circleList.add(schematicRectangle);

                } else if (me.getButton() == MouseButton.MIDDLE) {

                    main.showOnConsole("Removed specified Rectangle");
                    //main.circleList.remove(schematicRectangle);
                    main.schematicGroup.getChildren().remove(rectangle);

                }
                me.consume();
            }
        });
        rectangle.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {

                if (me.getButton() == MouseButton.PRIMARY) {

                    double dragX = me.getSceneX() - main.dragAnchor.getX();
                    double dragY = me.getSceneY() - main.dragAnchor.getY();
                    //calculate new position of the circle
                    double newXPosition = main.initX + dragX;
                    double newYPosition = main.initY + dragY;
                    //if new position do not exceeds borders of the schematicRectangle, translate to this position
                    //if ((newXPosition>=circle.getRadius()) && (newXPosition<=main.schematicWidth-circle.getRadius())) {
                    rectangle.setTranslateX(newXPosition);
                    //}
                    //if ((newYPosition>=circle.getRadius()) && (newYPosition<=main.schematicHeigth-circle.getRadius())){
                    rectangle.setTranslateY(newYPosition);
                    //}
                    main.showOnConsole(name + " was dragged (x:" + dragX + ", y:" + dragY + ")");

                } else if (me.getButton() == MouseButton.SECONDARY) {
                }
            }
        });
        rectangle.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                //change the z-coordinate of the circle
                //circle.toFront();
                main.showOnConsole("Mouse entered " + name);
            }
        });
        rectangle.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                main.showOnConsole("Mouse exited " + name);
            }
        });
        rectangle.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                rectangle.toFront();
                //when mouse is pressed, store initial position
                main.initX = rectangle.getTranslateX();
                main.initY = rectangle.getTranslateY();
                main.dragAnchor = new Point2D(me.getSceneX(), me.getSceneY());
                main.showOnConsole("Mouse pressed above " + name);
            }
        });
        rectangle.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                main.showOnConsole("Mouse released above " + name);
                /*if (schematicRectangle.getTranslateX() < (150) && schematicRectangle.getTranslateX() > (- 150) && schematicRectangle.getTranslateY() < (150) && schematicRectangle.getTranslateY() > (- 150)) {
                 schematicRectangle.setTranslateX(150);
                 schematicRectangle.setTranslateY(150);
                 }
                 */
            }
        });
        return rectangle;
    }
}
