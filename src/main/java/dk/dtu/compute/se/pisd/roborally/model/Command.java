/*
 *  This file is part of the initial project provided for the
 *  course "Project in Software Development (02362)" held at
 *  DTU Compute at the Technical University of Denmark.
 *
 *  Copyright (C) 2019-2026: Ekkart Kindler, ekki@dtu.dk
 *
 *  This software is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; version 2 of the License.
 *
 *  This project is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this project; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */
package dk.dtu.compute.se.pisd.roborally.model;

import java.util.List;

/**
 * Enumeration of all possible commands of RoboRally used on command cards.
 * Note that commands can be interactive: this means that the command
 * has several options, which the player can choose from, when the command
 * is actually executed during the execution of the robots programs.<p>
 *
 * Note that at this stage (initial version) of RoboRally, this enumeration
 * does not have any interactive commands. This is just preparing for
 * future extensions.
 *
 * @author Ekkart Kindler, ekki@dtu.dk
 *
 */
public enum Command {

    // This is a very simplistic way of realizing different commands.

    FORWARD("Fwd"),
    RIGHT("Turn Right"),
    LEFT("Turn Left"),
    FAST_FORWARD("Fast Fwd");

    // TODO A6c: add new literals to this enumeration for the two commands
    //     BACK and UTURN, and implement the corresponding command in the
    //     class GameController)

    // TODO A6e: add two new commands  here, which are interactive,
    //     which means that the player can chose between two options when
    //     the command is executed.

    /**
     * The name shown for this command on the GUI.
     */
    final public String displayName;

    /**
     * List of command options of an interactive command card. If the
     * list is not empty, the command card is interactive. This list
     * will be unmodifiable, and it will never change.
     */
    final private List<Command> options;

    /**
     * Creates a command as a value in this enumeration. If at least one options
     * is present, this command is an interactive command, with the given option(s);
     * if no options are given, the command is not interactive. Technically,
     * it is possible to have an interactive command with only one option;
     * but this would not make much sense, since the player does not have
     * anything to choose from.
     *
     * @param displayName the display name of the command
     * @param options the different command options for an interactive command
     */
    Command(String displayName, Command... options) {
        this.displayName = displayName;
        this.options = List.of(options);
    }

    /**
     * Returns true if the command is interactive.
     *
     * @return true if the command is interactive
     */
    public boolean isInteractive() {
        return !options.isEmpty();
    }

    /**
     * The list of command options to chose from for an interactive command.
     * Note that the returned list is an unmodifiable list and will never change.
     *
     * @return an unmodifiable list of the options of this interactive command
     */
    public List<Command> getOptions() {
        return options;
    }

}
