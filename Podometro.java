/**
 * La clase modela un sencillo pod�metro que registra informaci�n
 * acerca de los pasos, distancia, ..... que una persona (hombre o mujer)
 * ha dado en una semana. 
 * 
 * @Rub�n Gonz�lez Rivera. 
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
     * Inicializa el pod�metro con la marca indicada por el par�metro.
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
     * Simula la configuraci�n del pod�metro.
     * Recibe como par�metros la altura y el sexo de una persona
     * y asigna estos valores a los atributos correspondiente.
     * Asigna adem�s el valor adecuado al atributo longitudZancada
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
     *  Recibe cuatro par�metros que supondremos correctos:
     *    pasos - el n� de pasos caminados
     *    dia - n� de d�a de la semana en que se ha hecho la caminata 
     *              (1 - Lunes, 2 - Martes - .... - 6 - S�bado, 7 - Domingo)
     *    horaInicio � hora de inicio de la caminata
     *    horaFina � hora de fin de la caminata
     *    
     *    A partir de estos par�metros el m�todo debe realizar ciertos c�lculos
     *    y  actualizar� el pod�metro adecuadamente  
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
     * Muestra en pantalla la configuraci�n del pod�metro
     * (altura, sexo y longitud de la zancada)
     * 
     * (ver enunciado)
     *  
     */
    public void printConfiguracion(){
        System.out.println(" Configuraci�n del pod�metro" +
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
     * Muestra en pantalla informaci�n acerca de la distancia recorrida,
     * pasos, tiempo total caminado, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstad�sticas(){
        System.out.println(" Estadisticas" +
            "\n ***************************" + 
            "\n Distancia recorrida toda la semana:" + 
            (totalDistanciaSemana + totalDistanciaFinSemana) + " Km" + 
            "\n Distancia recorrida fin de semana:" + 
            totalDistanciaFinSemana + "Km" + 
            "\n" + "\n" + 
            " N� pasos d�as laborables:" + totalPasosLaborables +
            "\n N� pasos S�BADO:" + totalPasosSabado +
            "\n N� pasos DOMINGO:" + totalPasosDomingo + 
            "\n" + "\n" + 
            "\n N� caminatas realizadas a partir de las 21h.: "
            + caminatasNoche + 
            "\n" + "\n" + 
            "\n Tiempo total caminado en la semana: " + 
            (tiempo / 60) + "h. y " + (tiempo % 60) + "m.");
    }

    /**
     *  Calcula y devuelve un String que representa el nombre del d�a
     *  en el que se ha caminado m�s pasos - "S�BADO"   "DOMINGO" o  "LABORABLES"
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
     * Restablecer los valores iniciales del pod�metro
     * Todos los atributos se ponen a cero salvo el sexo
     * que se establece a MUJER. La marca no var�a
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
