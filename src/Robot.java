public class Robot {

    int movements = 0;
    char lastMovement = '0';
    int[] pos;
    // Prioritats de moviment
    //Declaram les prioritats de les direccions
    char[] priorityDir1 = new char[]{'S', 'E', 'N', 'W'};
    char[] priorityDir2 = new char[]{'N', 'W', 'S', 'E'};
    char[] priority = priorityDir1;

    public Robot(int[] pos) {
        this.pos = pos; // Assignam la posició inicial del robot
    }

    // Moviments

    // Moure cap al sud
    String[][] moveSouth(String[][] mapa) {
        System.out.println("Moving to the south, n° of movements => " + movements);

        if (mapa[pos[0] + 1][pos[1]].equals("#")) {
            return null;
        } else {
            mapa[pos[0]][pos[1]] = " ";
            mapa[pos[0] + 1][pos[1]] = "X";
            pos[0] = pos[0] + 1;
            lastMovement = 'S';
            movements++;
        }

        return mapa;
    }

    //Funció per moure el Robot cap a l'est
    String[][] moveEast(String[][] mapa) {
        System.out.println("Moving east");
        if (mapa[pos[0]][pos[1] + 1].equals("#")) {
            return null;
        } else {
            mapa[pos[0]][pos[1]] = " ";
            mapa[pos[0]][pos[1] + 1] = "X";
            pos[1] = pos[1] + 1;
            lastMovement = 'E';
            movements++;
        }

        return mapa;
    }

    //Funció per a moure el robot cap al nord
    String[][] moveNorth(String[][] mapa) {
        System.out.println("Moving north");

        if (mapa[pos[0] - 1][pos[1]].equals("#")) {
            return null;
        } else {
            mapa[pos[0]][pos[1]] = " ";
            mapa[pos[0] - 1][pos[1]] = "X";
            pos[0] = pos[0] - 1;
            lastMovement = 'N';
            movements++;
        }

        return mapa;
    }

    //Funcio per a moure el robot cap a l'oest
    String[][] moveWest(String[][] mapa) {
        System.out.println("Moving west");

        if (mapa[pos[0]][pos[1] - 1].equals("#")) {
            return null;
        } else {
            mapa[pos[0]][pos[1]] = " ";
            mapa[pos[0]][pos[1] - 1] = "X";
            pos[1] = pos[1] - 1;
            lastMovement = 'W';
            movements++;
        }
        return mapa;
    }

    //Camviem les prioritats
    public void switchPriority() {
        if (priority == priorityDir1) {
            priority = priorityDir2;
        } else {
            priority = priorityDir1;
        }
    }


}
