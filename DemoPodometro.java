import java.util.Scanner;

/**
 *  class DemoPodometro  
 *  Clase para testear la clase Podometro
 *  
 *  
 */
public class DemoPodometro {

    private Podometro podometro;
    private Scanner teclado;

    /**
     * Constructor de la clase DemoPodometro
     */
    public DemoPodometro() {

        teclado = new Scanner(System.in);
        podometro = new Podometro("Garmin");

    }

    /**
     *  Configurar el podómetro y registrar todas las caminatas
     *  
     */
    public void iniciar() {

        printMarca();
        configurar(157, 'M');
        int[] pasos = { 8900, 7000, 10000, 12700, 4580, 15000, 2000, 2000, 7000,
                6000 };
        int[] dias = { 1, 2, 4, 5, 5, 6, 6, 7, 7, 7 };
        int[] horasInicio = { 1850, 2210, 1300, 1730, 2217, 1900, 2230, 915, 1345, 1835 };
        int[] horasFin = { 1950, 2345, 1400, 1915, 2310, 2100, 2310, 1000, 1500, 1930 };
        registrarCaminatas(pasos, dias, horasInicio, horasFin);
        mostrarEstadisticas();

        pausa();

        podometro.reset();

        printMarca();
        configurar(185, 'H');
        pasos = new int[] { 2340, 4500, 5000, 2000, 3000, 3000 };
        dias = new int[] { 2, 3, 4, 4, 7, 7 };
        horasInicio = new int[] { 2215, 1820, 1000, 2055, 930, 2215 };
        horasFin = new int[] { 2330, 1935, 1045, 2230, 1020, 2310 };
        registrarCaminatas(pasos, dias, horasInicio, horasFin);
        mostrarEstadisticas();

    }

    private void printMarca() {
        System.out.println("*****************************************");
        System.out.println("*******    Podómetro " + podometro.getMarca().toUpperCase()
            + "       *******");
        System.out.println("*****************************************\n");
    }

    private void configurar(double altura, char sexo) {
        podometro.configurar(altura, sexo);
        podometro.printConfiguracion();

    }

    /**
     * registrar los recorridos realizados en el podómetro
     */
    private void registrarCaminatas(int[] pasos, int[] dias, int[] horasInicio,
    int[] horasFin) {

        for (int i = 0; i < pasos.length; i++) {
            podometro.registrarCaminata(pasos[i], dias[i], horasInicio[i],
                horasFin[i]);
        }

    }

    private void mostrarEstadisticas() {

        podometro.printEstadísticas();
        System.out.print(" Día/s con más pasos caminados: "
            + podometro.diaMayorNumeroPasos());

    }

    /**
     *  hacer una pausa
     */
    private void pausa() {

        System.out.println("\n\nPulse <Intro> para continuar");
        teclado.nextLine();
        System.out.println("\u000C");
    }

}
