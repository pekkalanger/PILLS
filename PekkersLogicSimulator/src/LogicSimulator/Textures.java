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

import java.io.File;
import java.util.HashMap;
import javafx.scene.image.Image;

public class Textures {

    public static HashMap<String, Image> hm = new HashMap<>();

    public static Image getHmImage(String name) {
        Image i = hm.get(name);
        if (i == null) {
            System.out.println("Missing Texture: " + name);
            return new Image("https://raw.githubusercontent.com/pekkalanger/PekkersLogicSimulator/master/PekkersLogicSimulator/res/noimage.png");
        }
        return i;

    }

    public static void initMap() {
        System.out.println("Initializing Textures");

        File folder = new File("res/");
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                String str = file.getName();
                String str2 = str.substring(0, str.lastIndexOf('.'));
                Image i = new Image("file:res/" + str);
                if (i != null) {
                    hm.put(str2, i);
                    System.out.println("Loaded Resource: " + str2);
                } else {
                    System.out.println("Failed Resource: " + str2);
                }
            }
        }
        System.out.println("Textures Initialized");
    }

    public Textures() {
    }

}
