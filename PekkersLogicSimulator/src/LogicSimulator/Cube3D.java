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

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Point3D;
import javafx.scene.DepthTest;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author PEKKA
 */
public class Cube3D extends Application {

    private Timeline animation;

    private void init(Stage primaryStage) {
        Group root = new Group();
        root.setDepthTest(DepthTest.ENABLE);
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 400, 150, true));
        primaryStage.getScene().setCamera(new PerspectiveCamera());
        root.getTransforms().addAll(
                new Translate(400 / 2, 150 / 2),
                new Rotate(180, Rotate.X_AXIS)
        );
        root.getChildren().add(create3dContent());
    }

    public Node create3dContent() {
        Cube c = new Cube(50, Color.RED, 1);
        c.rx.setAngle(45);
        c.ry.setAngle(45);
        Cube c2 = new Cube(50, Color.GREEN, 1);
        c2.setTranslateX(100);
        c2.rx.setAngle(45);
        c2.ry.setAngle(45);
        //Cube c3 = new Cube(50,Color.ORANGE,1);
        //c3.setTranslateX(-100);
        //c3.rx.setAngle(45);
        //c3.ry.setAngle(45);

        animation = new Timeline();
        animation.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(c.ry.angleProperty(), 0d),
                        new KeyValue(c2.rx.angleProperty(), 0d)//,
                //new KeyValue(c3.rz.angleProperty(), 0d)
                ),
                new KeyFrame(Duration.seconds(1),
                        new KeyValue(c.ry.angleProperty(), 360d),
                        new KeyValue(c2.rx.angleProperty(), 360d)//,
                //new KeyValue(c3.rz.angleProperty(), 360d)
                ));
        animation.setCycleCount(Animation.INDEFINITE);

        return new Group(c, c2/*,c3*/);
    }

    public void play() {
        animation.play();
    }

    public void stop() {
        animation.pause();
    }

    public class Cube extends Group {

        final Rotate rx = new Rotate(0, Rotate.X_AXIS);
        final Rotate ry = new Rotate(0, Rotate.Y_AXIS);
        final Rotate rz = new Rotate(0, Rotate.Z_AXIS);

        public Cube(double size, Color color, double shade) {

            getTransforms().addAll(rz, ry, rx);
            getChildren().addAll(
                    buildrectangle2(-0.5, -0.5, 0.5, size, color, shade, 0.5, null, 0),
                    buildrectangle2(-0.5, 0, -11, size, color, shade, 0.4, Rotate.X_AXIS, 90),
                    buildrectangle2(-1, -0.5, -11, size, color, shade, 0.3, Rotate.Y_AXIS, 90),
                    buildrectangle2(0, -0.5, -11, size, color, shade, 0.2, Rotate.Y_AXIS, 90),
                    buildrectangle2(-0.5, -1.0, -11, size, color, shade, 0.1, Rotate.X_AXIS, 90),
                    buildrectangle2(-0.5, -0.5, -0.5, size, color, shade, 0, null, 0)
            );
        }
    }

    public Rectangle buildrectangle2(double x, double y, double z, double size, Color color, double shade, double shadeMult, Point3D point3D, int deg) {
        Rectangle rectangle = new Rectangle(size, size, color.deriveColor(0.0, 1.0, (1 - shadeMult * shade), 1.0)); // back face
        if (x != -11) {
            rectangle.setTranslateX(x * size);
        }
        if (y != -11) {
            rectangle.setTranslateY(y * size);
        }
        if (z != -11) {
            rectangle.setTranslateZ(z * size);
        }
        if (point3D != null) {
            rectangle.setRotationAxis(point3D);
        }
        if (deg != 0) {
            rectangle.setRotate(deg);
        }
        return rectangle;
    }

    public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
        play();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
