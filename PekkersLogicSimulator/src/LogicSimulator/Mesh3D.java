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
import javafx.animation.Timeline;
import static javafx.application.Application.launch;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.PointLight;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Shape3D;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author PEKKA
 */
public class Mesh3D {//extends Application {

    public Shape3D[] meshView;
    boolean var;
    Timeline timeline;

    public Group start(Group g, Stage stage) {
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.LIGHTGREEN);
        material.setSpecularColor(Color.rgb(30, 30, 30));

        meshView = new Shape3D[]{new Box(100, 100, 100)};

        for (int i = 0; i != 1; ++i) {
            meshView[i].setMaterial(material);
            meshView[i].setTranslateX((i + 1) * 0);
            meshView[i].setTranslateY(0);
            meshView[i].setTranslateZ(1);
            meshView[i].setDrawMode(DrawMode.FILL);
            meshView[i].setCullFace(CullFace.BACK);
        };

        PointLight pointLight = new PointLight(Color.ANTIQUEWHITE);
        pointLight.setTranslateX(200);
        pointLight.setTranslateY(-100);
        pointLight.setTranslateZ(-100);

        Rotate rxBox2 = new Rotate(0, 0, 0, 0, Rotate.X_AXIS);
        Rotate ryBox2 = new Rotate(0, 0, 0, 0, Rotate.Y_AXIS);
        Rotate rzBox2 = new Rotate(0, 0, 0, 0, Rotate.Z_AXIS);
        rxBox2.setAngle(45);
        ryBox2.setAngle(45);
        rzBox2.setAngle(0);
        meshView[0].getTransforms().addAll(rxBox2, ryBox2, rzBox2);

        buildAndSetLoop(g, stage);
        Group meshGroup = new Group(meshView);
        meshGroup.getChildren().add(pointLight);

        stage.getScene().setFill(Color.rgb(10, 10, 40));
        //scene.setCamera(new PerspectiveCamera(false));
        stage.show();
        return meshGroup;
    }

    protected final void buildAndSetLoop(Group g, Stage s) {        // this vill update everything 100 times per second / once every 1.666 seconds
        Rotate rxBox = new Rotate(0, 0, 0, 0, Rotate.X_AXIS);
        Rotate ryBox = new Rotate(0, 0, 0, 0, Rotate.Y_AXIS);
        Rotate rzBox = new Rotate(0, 0, 0, 0, Rotate.Z_AXIS);
        rxBox.setAngle(1);
        ryBox.setAngle(1);
        rzBox.setAngle(-1);
        final int fps = 60; //  if toggle then 100on + 100off = 200/2 hertz
        final Duration oneFrameAmt = Duration.millis(1000 / fps);  // "100 fps" should be enough.. for nao
        final KeyFrame oneFrame = new KeyFrame(oneFrameAmt, new EventHandler() {
            @Override
            public void handle(Event event) {
                meshView[0].getTransforms().addAll(rxBox, ryBox, rzBox);
                moveCube(g, s);
                event.consume();
            }
        }); // oneFrame
        // sets the apps loop (Timeline)
        timeline = new Timeline(oneFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void moveCube(Group g, Stage s) {
        if (var == false) {
            if (meshView[0].getTranslateX() < g.getScene().getWidth() + 100) {
                System.out.println("Mesh x " + meshView[0].getTranslateX() + " " + g.getScene().getWidth());
                meshView[0].setTranslateX(meshView[0].getTranslateX() + 5);
            } else {
                var = true;
                System.out.println("Mesh true");
            }
        } else if (var == true) {
            if (meshView[0].getTranslateX() > -100) {
                System.out.println("Mesh x " + meshView[0].getTranslateX());
                meshView[0].setTranslateX(meshView[0].getTranslateX() - 5);
            } else {
                var = false;
            }
        }
    }

}
