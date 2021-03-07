import java.util.Arrays;
import java.util.LinkedList;

public class Bender {

    // Declaram les variables globals de la classe
    String [][] mapa; // Mapa per on es mourà el robot
    Robot robot; // El robot que hem creat que es mourà pel mapa
    int[] posFinal; // Posició de destí
    String resultat; // Variable a on emmagatzemarem el resultat
    LinkedList<Integer[]> teleport = new LinkedList<>(); // Varirable que emplearem per als transportadors
    LinkedList<Integer[]> inverter = new LinkedList<>(); // Varirable que emplearem per als inversors de direcció



    // Constructor: ens passen el mapa en forma d'String
    public Bender(String mapa) {

        // Començam fent un recompte del número de col·lumnes que té el mapa
        String[] cols = mapa.split("\n"); // Ho aconseguim per a cada salt de linea

        this.mapa = new String[cols.length][];
        for (int i = 0; i < cols.length; i++) {
            this.mapa[i] = cols[i].split("");
        }

        // Comprovam a la posició que ens trobam
        for (int i = 0; i < this.mapa.length; i++) {
            for (int j = 0; j < this.mapa[0].length; j++) {


                // Si trobam el valor X retornam un objecte Robot
                if(this.mapa[i][j].equals("X")) this.robot = new Robot(new int[]{i, j});

                // Si trobam el valor $ el que retornarem será la posició final, es a dir, el resultat
                if(this.mapa[i][j].equals("$")) this.posFinal = new int[]{i, j};

                // Teleport
                if(this.mapa[i][j].equals("T")) teleport.add(new Integer[]{i, j});

                // Inverter
                if(this.mapa[i][j].equals("I")) inverter.add(new Integer[]{i, j});

            }
        }


    }

    //Teletransportam al robot
    String[][] teleportTo (String[][] mapa){

        System.out.println("You're being transported to the next teleport point");
        for (int i = 0; i < teleport.size(); i++) {
            System.out.println(teleport.get(i)[0] + " - " + teleport.get(i)[1]);
        }

        //Donam una dimensió a l'array de teleportadors
        int[] tp = new int[2];

        if(teleport.size() == 2){
            for (int i = 0; i < teleport.size(); i++) {
                int[] tp2 = new int[]{teleport.get(i)[0], teleport.get(i)[1]};
                if(!Arrays.equals(tp2, robot.pos))tp = tp2;
            }

            robot.pos = tp;
        }

        return mapa;
    }

    //Definim si troban un inversor
    boolean isAnInverter(int[] coorXY){
        System.out.println("I found an inverter at: " + Arrays.toString(coorXY));
        if(inverter.size() == 0)return false;

        for (int i = 0; i < inverter.size(); i++) {
            int coorX = inverter.get(i)[0];
            int coorY = inverter.get(i)[1];

            int[] inver = new int[]{coorX, coorY};

            if(Arrays.equals(inver, coorXY))return true;

        }

        return false;
    }

    //Definim si es un teleportador
    boolean isTeleport(int[] coor) {
        System.out.println("I found a teleport point at: " + Arrays.toString(coor));
        if(teleport.size() == 0)return false;

        for (int i = 0; i < teleport.size(); i++) {
            int coorX = teleport.get(i)[0];
            int coorY = teleport.get(i)[1];

            int[] teleporter = new int[]{coorX,coorY};

            if(Arrays.equals(teleporter, coor))return true;

        }

        return false;
    }


    /**
     * Navegar fins a l'objectiu («$»).
     * El valor retornat pel mètode consisteix en una cadena de
     * caràcters on cada lletra pot tenir
     * els valors «S», «N», «W» o «E»,
     * segons la posició del robot a cada moment.
     * */
    public String run() {
        String res = "";

        // Mentres la prioritat sigui la primera el robot es mourà de la següent manera
        while(robot.priority == robot.priorityDir1) {

            while(robot.moveSouth(this.mapa) != null) {
                move();
                res += "S";

                if(Arrays.equals(robot.pos, posFinal)) return res; // Retornam el resultat si hem trobat la posició final
            }

            if(robot.priority != robot.priorityDir1) break;

            while (robot.moveEast(this.mapa) != null) {
                move();
                res += "E";

                if(Arrays.equals(robot.pos, posFinal)) return res;
            }

            if(robot.priority != robot.priorityDir1) break;

            while (robot.moveSouth(this.mapa) != null) {
                move();
                res += "S";

                if(Arrays.equals(robot.pos, posFinal)) return res;
            }

            if(robot.priority != robot.priorityDir1) break;

            while (robot.moveNorth(this.mapa) != null) {
                move();
                res += "N";

                if(Arrays.equals(robot.pos, posFinal)) return res;
            }

            if(robot.priority != robot.priorityDir1) break;

            while (robot.moveEast(this.mapa) != null) {
                move();
                res += "E";

                if(Arrays.equals(robot.pos, posFinal)) return res;
            }

            if(robot.priority != robot.priorityDir1) break;

            while (robot.moveSouth(this.mapa) != null) {
                move();
                res += "S";

                if(Arrays.equals(robot.pos, posFinal)) return res;
            }

            if(robot.priority != robot.priorityDir1) break;

            while (robot.moveEast(this.mapa) != null) {
                move();
                res += "E";

                if(Arrays.equals(robot.pos, posFinal)) return res;
            }

            if(robot.priority != robot.priorityDir1) break;

            while (robot.moveWest(this.mapa) != null) {
                move();
                res += "W";

                if(Arrays.equals(robot.pos, posFinal)) return res;
            }

            if(robot.priority != robot.priorityDir1) break;

        }

        // Mentres que la prioritat sigui la segona ens mourem de la següent manera
        while (robot.priority == robot.priorityDir2) {

            while (robot.moveNorth(this.mapa) != null) {
                move();
                res += "N";

                if(Arrays.equals(robot.pos, posFinal)) return res;
            }

            if(robot.priority != robot.priorityDir2) break;

        }










        return res;
    }


    public int bestRun() {
        return 0;
    }


    // Funció pera moure el robot
    void move() {
        this.resultat += robot.lastMovement;

        if(isTeleport(robot.pos)) this.mapa = teleportTo(this.mapa); // Teleportam el robot

        if(isAnInverter(robot.pos)) robot.switchPriority(); // Canviam la prioritat de moviment

    }
}
