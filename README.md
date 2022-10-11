# Java Chunk Rendering
In order to gain experience with efficient data storage and processing for games and simulation, I am creating a program from scratch using JavaFX used for pratice in loading things in chunks and only activating certain areas localised around the player.

## Status/Versions Completed
Currently working on core elements and creating an efficient base to build the rest of the project upon.

## Goals (✅=completed)
- [ ] Installation & library guides
- [ ] Different tile types (currently under development)

- [ ] Simulation
  - [X] Separation of unprocessed chunks, lazy chunks, and entity chunks with auto creation and removal based on activation and proximity to user.
  - [ ] Agents/Entities
    - [X] Agent interface class
    - [X] Basic Entities class for extending
    - [ ] Collisions between entities
      - [X] Entities not moving onto occupied spaces.
      - [ ] Velocity and pressures
    - [X] Agent movement during tick
    - [X] Chunk restricted movement
    - [ ] Agent pathing (Status=Random Movement)
  - [ ] Gravity

- [ ] Quality of Life
  - [ ] Change from button based control to keypress control
  - [ ] Level Creator Mode
    - [ ] Add spawn control via new selector palate

- [ ] OS compatibility
  - [ ] Linux
    - [ ] Debian (Untested)
    - [X] Xubuntu (Tested/Developed on)
    - [ ] Arch (Untested)
  - [ ] MacOS (M1 chip works without color, intel-based untested)
  - [ ] Windows (Completely untested)

# Visual Preview of Latest Version
![Screenshot taken of latest version](/src/com/company/ChangeLogData/MostRecentDisplay.png)
