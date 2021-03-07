import java.util.LinkedList;

public class Bender {

    // Declaram les variables globals de la classe
    String [][] mapa; // Mapa per on es mourà el robot
    Robot robot; // El robot que hem creat que es mourà pel mapa
    String resultat; // Variable a on emmagatzemarem el resultat
    LinkedList<Integer[]> teleport = new LinkedList<>(); // Varirable que emplearem per als transportadors
    LinkedList<Integer[]> inverter = new LinkedList<>(); // Varirable que emplearem per als inversors de direcció



    // Constructor: ens passen el mapa en forma d'String
    public Bender(String mapa) { }



    /**
     * Navegar fins a l'objectiu («$»).
     * El valor retornat pel mètode consisteix en una cadena de
     * caràcters on cada lletra pot tenir
     * els valors «S», «N», «W» o «E»,
     * segons la posició del robot a cada moment.
     * */
    public String run() { return ""; }


    public int bestRun() {
        return 0;
    }
}
