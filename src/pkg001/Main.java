package pkg001;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

public class Main {

    static int entradasVendidas = 0;
    static List<String> boletaFinal = new ArrayList<>();
    static int gastoTotal = 0;

    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        
        int opcion = 0;

        do {
            System.out.println("Bienvenido al Teatro MORO, elija una de las siguientes opciones:");
            System.out.println("1. Comprar boleto");
            System.out.println("2. Editar boleto");
            System.out.println("3. Anular boleto");
            System.out.println("4. Lista de precios y descuentos");
            System.out.println("5. Salir y pagar");
            opcion = scan.nextInt();
            scan.nextLine(); // Consume el carácter de nueva línea
            switch (opcion) {
                case 1:
                    comprar();
                    break;
                case 2:
                    editar();
                    break;
                case 3:
                    borrar();
                    break;
                case 4:
                    descuentos();
                    break;
                case 5:
                    salir();
                    break;
                default:
                    System.out.println("Debe ingresar una opcion valida");    
            }        
        } while (opcion != 5);

    }

    static String comprar() {
        
        String tipoDeEntrada = "";                  //Definiendo las variables necesarias
        String tarifa = "";
        int edad = 0;
        int precioZona = 0;
        double descuentoEstudiante = 0;
        double descuentoEdad = 0;        
        int precioFinal = 0;
        String aplicaDescuentoEstudiante = "";
        String aplicaDescuentoEdad = "";
        String boleta = "";

    
        System.out.println("Por favor ingrese los datos solicitados");

        /* Se hace un do-while loop para preguntar al usuario por el tipo
        de entrada hasta que ingrese una correctamente
        
        En la linea 72 Se lee lo que escribio el usuario,
        se lleva a letras minusculas con "toLowerCase()" y se eliminan
        los espacios vacios del principio y el final con "trim()" */

        boolean match = false;
        
        
        do {
            System.out.print("Indique tipo de entrada (VIP, Platea baja, Platea alta o Palco): ");  
            tipoDeEntrada = scan.nextLine().toLowerCase().trim();
            switch (tipoDeEntrada) {
                case "vip":
                    precioZona = 25000;
                    match = true;
                    break;

                case "platea baja":
                    precioZona = 19000;
                    match = true;
                    break;

                case "platea alta":
                    precioZona = 11000;
                    match = true;
                    break;

                case "palco":
                    precioZona = 7200;
                    match = true;
                    break;
            
                default:
                    System.out.println("Por favor ingrese un tipo de entrada valida");
                    
            }   
        } while (match != true);

        match = false;      //Se vuelve a poner match false para entrar en el loop
        do {              //El mismo do-while loop pero para la tarifa
            System.out.print("Indique su tarifa (Estudiante o General): ");
            tarifa = scan.nextLine().toLowerCase().trim();
            switch (tarifa) {
                case "estudiante":
                    descuentoEstudiante = 0.10;
                    match = true;
                    break;

                case "general":
                    match = true;
                    break;

                default:
                    System.out.println("Por favor ingrese una tarifa valida");
                    break;
            }
        } while (match != true);
        
        match = false;
        do {              
            System.out.print("Indique su edad: ");
            edad = scan.nextInt();
            if (edad >= 1 && edad <= 130) {
                match = true;
                if (edad >= 60) {
                    descuentoEdad = 0.15;
                    break;
                }
            } else
            System.out.println("Por favor ingrese una edad valida");
        } while (match != true);

        entradasVendidas++;     //al terminar la compra se suma una entrada vendida

        if(descuentoEstudiante != 0)
            descuentoEstudiante = precioZona * 0.1;

        if(descuentoEdad != 0)
            descuentoEdad = precioZona * 0.15;

        precioFinal = (int)(precioZona - descuentoEdad - descuentoEstudiante);
        
        
        gastoTotal += precioFinal;       //se suma el precio final al gasto total
        
        //Y al final le damos el precio al usuario y nos despedimos
        //Se repite el mismo proceso hasta que el usuario presione 3 (salir)

        if (descuentoEstudiante != 0)
            aplicaDescuentoEstudiante = "Descuento Estudiante: 10%" + "\n";

        if (descuentoEdad != 0)
            aplicaDescuentoEdad = "Descuento Tercera edad: 15%" + "\n";

        boleta = ("----------------------------------"+"\n"+
                    "Zona: " + tipoDeEntrada + "\n" +
                    "Precio base: $ " + precioZona + "\n" +
                    aplicaDescuentoEstudiante +
                    aplicaDescuentoEdad +
                    "Precio final: $ " + precioFinal + " pesos" + "\n" +
                    "----------------------------------"+"\n");

        System.out.println(boleta);     //La compra que acaba de hacer

        boletaFinal.add(boleta);      //Se guarda la compra junto a las otras que haga

        return(boleta);
    }

    static void editar() {
        
        if(boletaFinal.size() == 0) {
            System.out.println("\n" + "-----------------------------------------------------");
            System.out.println("Debe comprar un boleto primero");
            System.out.println("-----------------------------------------------------" + "\n");
        } else {
            System.out.println("Por favor indique el numero del boleto que quiere editar");
            System.out.println("(El primero boleto es el 0, el segundo el 1 y asi sucesivamente)");
            System.out.println("(Los boletos que edite estaran en la posicion mas alta de la lista)");

            int numeroBoleto = -1;
            numeroBoleto = scan.nextInt();
    
            while(numeroBoleto < 0 || numeroBoleto >= boletaFinal.size()) {
                System.out.println("Por favor ingrese un numero valido: ");
                numeroBoleto = scan.nextInt();
            }
            
            scan.nextLine();
            entradasVendidas--;     //se resta una entrada al total de entradas vendidas
            String[] precioBoleta = boletaFinal.get(numeroBoleto).split(" ");      //se hace una lista del boleto separado por espacios
            gastoTotal -= Integer.valueOf(precioBoleta[precioBoleta.length - 2]);     //se resta el precio del boleto al gasto total
            boletaFinal.remove(numeroBoleto);     //se elimina el boleto viejo
            comprar();      //se compra uno nuevo

            System.out.println("-----------------------------------------------------");
            System.out.println("Boleto editado exitosamente");
            System.out.println("-----------------------------------------------------" + "\n");           
            
        }
    }

    static void borrar() {

        if(boletaFinal.size() == 0) {
            System.out.println("\n" + "-----------------------------------------------------");
            System.out.println("Debe comprar un boleto primero");
            System.out.println("-----------------------------------------------------" + "\n");
        } else {
            System.out.println("Por favor indique el numero del boleto que quiere anular");
            System.out.println("(El primer boleto es el 0, el segundo el 1 y asi sucesivamente)");
    
            int numeroBoleto = -1;
            numeroBoleto = scan.nextInt();
    
            while(numeroBoleto < 0 || numeroBoleto >= boletaFinal.size()) {
                System.out.println("Por favor ingrese un numero valido: ");
                numeroBoleto = scan.nextInt();
            }

            entradasVendidas--;     //se resta una entrada al total de entradas vendidas
            String[] precioBoleta = boletaFinal.get(numeroBoleto).split(" ");      //se hace una lista del boleto separado por espacios
            gastoTotal -= Integer.valueOf(precioBoleta[precioBoleta.length - 2]);     //se resta el precio del boleto al gasto total
            boletaFinal.remove(numeroBoleto);       //se elimina el boleto
            System.out.println("\n" + "-----------------------------------------------------");
            System.out.println("Boleto eliminado exitosamente");
            System.out.println("-----------------------------------------------------" + "\n");
        }

    }

    static void descuentos() {

        System.out.println("\n" + "-------------------------------------------------------------");
        System.out.println("TEATRO MORO");
        System.out.println("LISTA DE PRECIOS");
        System.out.println("VIP: $25.000");
        System.out.println("Platea baja: $19.000");
        System.out.println("Platea alta: $11.000");
        System.out.println("Palco: $7.200");
        System.out.println();
        System.out.println("DESCUENTOS");
        System.out.println("Estudiante: 10%");
        System.out.println("Tercera edad (a partir de 60 años): 15%");
        System.out.println("-------------------------------------------------------------" + "\n");

    }
    
    static void salir() {
        String dateTime = DateTimeFormatter.ofPattern("      dd-MM-yyy, HH:mm ").format(LocalDateTime.now());
        System.out.println();
        System.out.println("DETALLE DE COMPRA:");     //se muestran todas las compras que realizo
        int boletaFinalSize = boletaFinal.size();       //se optimiza el codigo
        for(int i = 0; i < boletaFinalSize; i++) {
            System.out.println(boletaFinal.get(i));
        }
        System.out.println("===== BOLETA ELECTRONICA =====");
        System.out.println("=====     TEATRO MORO    =====");
        System.out.println(dateTime);
        System.out.println();
        System.out.println("      Entradas compradas: " + entradasVendidas);
        System.out.println();
        System.out.println("      TOTAL : $" + gastoTotal);
        System.out.println();        
        System.out.println();
        System.out.println("=== GRACIAS POR PREFERIRNOS ==="); 

    }    
}
