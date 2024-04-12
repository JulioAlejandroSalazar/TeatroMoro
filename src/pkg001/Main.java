package pkg001;

import java.util.Scanner;

public class Main {

    static int entradasVendidas = 0;
    static String boletaFinal = "";

    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {

        int opcion = 0;

        do {
            System.out.println("Bienvenido al Teatro MORO, elija una de las siguientes opciones:");
            System.out.println("1. Comprar");
            System.out.println("2. Lista de precios y descuentos");
            System.out.println("3. Salir");
            opcion = scan.nextInt();
            scan.nextLine(); // Consume el carácter de nueva línea
            switch (opcion) {
                case 1:
                    comprar();
                    break;
                case 2:
                    descuentos();
                    break;
                case 3:
                    salir();
                    break;
                default:
                    System.out.println("Debe ingresar una opcion valida");    
            }        
        } while (opcion != 3);

    }

    static void comprar() {
        
        String tipoDeEntrada = "";                  //Definiendo las variables necesarias
        String tarifa = "";
        int edad = 0;
        int precioZona = 0;
        double descuentoEstudiante = 0;
        double descuentoEdad = 0;        
        double descuentoEntradasVendidas = 0;
        int precioFinal = 0;
        String aplicaDescuentoEstudiante = "";
        String aplicaDescuentoEdad = "";
        String aplicaDescuentoEntradasVendidas = "";
        String boleta = "";

    
        System.out.println("Por favor ingrese los datos solicitados");

        /* Se hace un do-while loop para preguntar al usuario por el tipo
        de entrada hasta que ingrese una correctamente
        
        En la linea 69 Se lee lo que escribio el usuario,
        se lleva a letras minusculas con "toLowerCase()" y se eliminan
        los espacios vacios del principio y el final con "trim()" */

        boolean match = false;
        
        
        do {
            System.out.print("Indique tipo de entrada (VIP, Platea alta, Platea baja o Palcos): ");  
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

                case "palcos":
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

        if(entradasVendidas >= 3)        //se verifican los descuentos uno por uno independientes del otro
            descuentoEntradasVendidas = precioZona * 0.2;

        if(descuentoEstudiante != 0)
            descuentoEstudiante = precioZona * 0.1;

        if(descuentoEdad != 0)
            descuentoEdad = precioZona * 0.15;

        precioFinal = (int)(precioZona - descuentoEdad - descuentoEstudiante - descuentoEntradasVendidas);
        
        /*
        Y al final le damos el precio al usuario y nos despedimos
        Se repite el mismo proceso hasta que el usuario presione 3 (salir)
        */
        if (descuentoEstudiante != 0)
            aplicaDescuentoEstudiante = "Descuento estudiante: 10%";
        else
            aplicaDescuentoEstudiante = "Descuento estudiante: No aplica";

        if (descuentoEdad != 0)
            aplicaDescuentoEdad = "Descuento mayor de edad: 15%";
        else
            aplicaDescuentoEdad = "Descuento mayor de edad: No aplica";

        if (descuentoEntradasVendidas != 0)
            aplicaDescuentoEntradasVendidas = "Descuento multiples entradas compradas: 20%";
        else
            aplicaDescuentoEntradasVendidas = "Descuento multiples entradas compradas: No aplica";

        boleta = ("\n" + "-------------------------------------------------------------" + "\n" +
                    "Zona: " + tipoDeEntrada + "\n" +
                    "Precio base: " + precioZona + "\n" +
                    aplicaDescuentoEstudiante + "\n" +
                    aplicaDescuentoEdad + "\n" +
                    aplicaDescuentoEntradasVendidas + "\n" +
                    "El total a pagar es : $" + precioFinal + " pesos" + "\n" + "Gracias por su compra, disfrute de la funcion" + "\n" +
                    "-------------------------------------------------------------" + "\n");

        System.out.println(boleta);     //La compra que acaba de hacer

        boletaFinal += boleta;      //Se guarda la compra junto a las otras que haga
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
        System.out.println("Promocion compra 3 entradas o mas: 20%");
        System.out.println("-------------------------------------------------------------" + "\n");

    }
    
    static void salir() {
        System.out.println("Sus boletas:");     //se muestran todas las compras que realizo
        System.out.println(boletaFinal);
        System.out.println("Gracias por preferirnos"); 

    }    
}
