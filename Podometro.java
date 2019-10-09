/**
 * La clase modela un sencillo podómetro que registra información
 * acerca de los pasos, distancia, ..... que una persona (hombre o mujer)
 * ha dado en una semana. 
 * 
 * @Rubén González Rivera. 
 * 
 */
public class Podometro{
    private final char HOMBRE = 'H'; 
    private final char MUJER = 'M';
    private final double ZANCADA_HOMBRE = 0.45;
    private final double ZANCADA_MUJER = 0.41;
    private final int SABADO = 6;
    private final int DOMINGO = 7;

    private String marca;
    private double altura;
    private char sexo;
    private double longitudZancada;
    private int totalPasosLaborables;
    private int totalPasosSabado;
    private int totalPasosDomingo;
    private double totalDistanciaSemana;
    private double totalDistanciaFinSemana;
    private int tiempo;
    private int caminatasNoche;

    /**
     * Inicializa el podómetro con la marca indicada por el parámetro.
     * El resto de atributos se ponen a 0 y el sexo, por defecto, es mujer
     */
    public Podometro(String queMarca){
        marca = queMarca;
        altura = 0;
        sexo=MUJER;
        longitudZancada = 0;
        totalPasosLaborables = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;
        tiempo = 0;
        caminatasNoche = 0;
    }

    /**
     * accesor para la marca 
     */
    public String getMarca(){
        return marca;
    }

    /**
     * Simula la configuración del podómetro.
     * Recibe como parámetros la altura y el sexo de una persona
     * y asigna estos valores a los atributos correspondiente.
     * Asigna además el valor adecuado al atributo longitudZancada
     * 
     * (leer enunciado)
     *  
     */
    public void configurar(double queAltura, char queSexo){
        sexo = queSexo;
        altura = queAltura;
        
        if (sexo == HOMBRE){
            longitudZancada = Math.ceil(altura * ZANCADA_HOMBRE);
        }else{
            longitudZancada = Math.floor(altura * ZANCADA_MUJER);
        }
    }

    /**
     *  Recibe cuatro parámetros que supondremos correctos:
     *    pasos - el nº de pasos caminados
     *    dia - nº de día de la semana en que se ha hecho la caminata 
     *              (1 - Lunes, 2 - Martes - .... - 6 - Sábado, 7 - Domingo)
     *    horaInicio – hora de inicio de la caminata
     *    horaFina – hora de fin de la caminata
     *    
     *    A partir de estos parámetros el método debe realizar ciertos cálculos
     *    y  actualizará el podómetro adecuadamente  
     *   
     *   (leer enunciado del ejercicio)
     */
    public void registrarCaminata(int pasos, int dia, int horaInicio,int horaFin){
        int inicioHora = horaInicio / 100;
        int finHora = horaFin / 100;
        int inicioMinuto = horaInicio % (inicioHora * 100);
        int finMinuto = horaFin % (finHora * 100);

        if(inicioMinuto < finMinuto){
            tiempo += ((finHora - inicioHora) * 60) + (finMinuto - inicioMinuto);
        }else if(inicioMinuto == finMinuto){
            tiempo += (finHora - inicioHora) * 60;
        }else{
            tiempo += ((finHora - inicioHora - 1) * 60) + (60 + finMinuto - inicioMinuto);
        }

        switch(dia){
            case 1:
            case 2:
            case 3:
            case 4:
            case 5: totalPasosLaborables += pasos;
                    totalDistanciaSemana += (pasos * longitudZancada / 100000);
                    break;
            case SABADO: totalPasosSabado += pasos;
                         totalDistanciaFinSemana += (pasos * longitudZancada / 100000);
                         break;
            case DOMINGO: totalPasosDomingo += pasos;
                          totalDistanciaFinSemana += (pasos * longitudZancada / 100000);
                          break;
        }

        if(inicioHora > 20){
            caminatasNoche++;
        }
    }

    /**
     * Muestra en pantalla la configuración del podómetro
     * (altura, sexo y longitud de la zancada)
     * 
     * (ver enunciado)
     *  
     */
    public void printConfiguracion(){
        System.out.println(" Configuración del podómetro" +
                            "\n ***************************" + 
                            "\n Altura:" + altura / 100 + "mtos");

        if(sexo == HOMBRE){
            System.out.print(" Sexo: HOMBRE");
        }else{
            System.out.print(" Sexo: MUJER");
        }
        
        System.out.println("\n Longitud zancada:" + longitudZancada 
                            / 100 + "mtos");
    }

    /**
     * Muestra en pantalla información acerca de la distancia recorrida,
     * pasos, tiempo total caminado, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstadísticas(){
        System.out.println(" Estadisticas" +
            "\n ***************************" + 
            "\n Distancia recorrida toda la semana:" + 
            (totalDistanciaSemana + totalDistanciaFinSemana) + " Km" + 
            "\n Distancia recorrida fin de semana:" + 
            totalDistanciaFinSemana + "Km" + 
            "\n" + "\n" + 
            " Nº pasos días laborables:" + totalPasosLaborables +
            "\n Nº pasos SÁBADO:" + totalPasosSabado +
            "\n Nº pasos DOMINGO:" + totalPasosDomingo + 
            "\n" + "\n" + 
            "\n Nº caminatas realizadas a partir de las 21h.: "
            + caminatasNoche + 
            "\n" + "\n" + 
            "\n Tiempo total caminado en la semana: " + 
            (tiempo / 60) + "h. y " + (tiempo % 60) + "m.");
    }

    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que se ha caminado más pasos - "SÁBADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroPasos(){
        if(totalPasosLaborables > totalPasosSabado &&
        totalPasosLaborables > totalPasosDomingo){
            return "Laborales";
        }else if(totalPasosSabado > totalPasosLaborables &&
        totalPasosSabado > totalPasosDomingo){
            return "Sabados";
        }else{
            return "Domingo";
        }
    }

    /**
     * Restablecer los valores iniciales del podómetro
     * Todos los atributos se ponen a cero salvo el sexo
     * que se establece a MUJER. La marca no varía
     *  
     */    
    public void reset(){
        altura = 0;
        sexo=MUJER;
        longitudZancada = 0;
        totalPasosLaborables = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;
        tiempo = 0;
        caminatasNoche = 0;
    }
}
