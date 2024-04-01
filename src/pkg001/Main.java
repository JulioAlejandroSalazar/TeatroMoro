package pkg001;
import java.util.Scanner;
import java.text.DecimalFormat;

public class Main {

    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        
        int opcion = 0;
        do {
            System.out.println("Bienvenido usuario, elija una de las siguientes opciones:");
            System.out.println("1. Comprar");
            System.out.println("2. Salir");
            opcion = scan.nextInt();
            scan.nextLine(); // Consume el carácter de nueva línea
            switch (opcion) {
                case 1:
                    comprar();
                    break;
                case 2:
                    salir();
                    break;
                default:
                    System.out.println("Debe ingresar una opcion valida");    
            }        
        } while (opcion != 2);

    }

    static void comprar() {

        String tipoDeEntrada = "";                  //Definiendo las variables necesarias
        String tarifa = "";
        int edad = 0;
        int precioZona = 0;
        double descuentoEstudiante = 1;
        double descuentoEdad = 1;
        double precioFinal = 0;
        DecimalFormat df = new DecimalFormat("#%");     //Formatea decimales en porcentajes

    
        System.out.println("Por favor ingrese los datos que se le solicitan");

        /* Se hace un do-while loop para preguntar al usuario por el tipo
        de entrada hasta que ingrese una correctamente
        
        En la linea 57 Se lee lo que escribio el usuario,
        se lleva a letras minusculas con "toLowerCase()" y se eliminan
        los espacios vacios del principio y el final con "trim()" */

        boolean match = false;
        
        
        do {
            System.out.print("Indique su tipo de entrada (VIP, Platea alta, Platea baja o Palcos): ");  
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
        
        if (descuentoEdad != descuentoEstudiante)
            precioFinal = precioZona - (precioZona * descuentoEdad * descuentoEstudiante);
        else
            precioFinal = precioZona;
        
        /*
        Y al final le damos el precio al usuario y nos despedimos
        Se repite el mismo proceso hasta que el usuario presione 2 (salir)
        */
        System.out.println("\n" + "-------------------------------------------------------------");
        System.out.println("Zona: " + tipoDeEntrada);
        System.out.println("Precio base: " + precioZona);
        if (descuentoEstudiante != 1)
            System.out.println("Descuento estudiante: " + df.format(descuentoEstudiante));
        else
            System.out.println("Descuento estudiante: No aplica");
        if (descuentoEdad != 1)
            System.out.println("Descuento mayor de edad: " + df.format(descuentoEdad));
        else
            System.out.println("Descuento mayor de edad: No aplica");
        System.out.println("El total a pagar es : $" + precioFinal + " pesos" + "\n" + "Gracias por su compra, disfrute de la funcion");
        System.out.println("-------------------------------------------------------------" + "\n");
    }

    
    static void salir() {
        System.out.println("Gracias por preferirnos");
        
        
        
        
        
        
        
    }
    
}
