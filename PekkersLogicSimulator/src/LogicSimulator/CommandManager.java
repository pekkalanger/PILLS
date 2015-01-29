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

import java.util.Stack;

/**
 *
 * @author PEKKA
 */
public class CommandManager {

    public static CommandManager commandManager;

    private Stack<Command> undos = new Stack<>();
    private Stack<Command> redos = new Stack<>();

    public static void init() {
        commandManager = new CommandManager();
    }

    public CommandManager() {
    }

    public void executeCommand(Command c) {
        c.execute();
        undos.push(c);
        redos.clear();
    }

    public boolean isUndoAvailable() {
        return !undos.empty();
    }

    public void undo() {
        assert (!undos.empty());
        Command command = undos.pop();
        command.undo();
        redos.push(command);
    }

    public boolean isRedoAvailable() {
        return !redos.empty();
    }

    public void redo() {
        assert (!redos.empty());
        Command command = redos.pop();
        command.execute();
        undos.push(command);
    }
}
